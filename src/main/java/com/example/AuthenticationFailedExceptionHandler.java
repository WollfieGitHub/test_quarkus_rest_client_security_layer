package com.example;

import io.quarkus.logging.Log;
import io.quarkus.security.AuthenticationFailedException;
import io.vertx.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class AuthenticationFailedExceptionHandler {

	public void init(@Observes Router router) {
		router.route().failureHandler(event -> {
			if (event.failure() instanceof AuthenticationFailedException) {
				Log.info("Authentication failed !");
				event.response().end("CUSTOMIZED_RESPONSE");
			} else {
				event.next();
			}
		});
	}
}
