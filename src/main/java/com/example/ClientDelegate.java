package com.example;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@RequestScoped
public class ClientDelegate {

	@RestClient Client client;

	@ActivateRequestContext
	public Uni<Void> tryCall() {
		return Uni.createFrom().voidItem()
			.invoke(() -> Log.info("Call to no-auth..."))
			.chain(() -> client.withoutAuthorizationPing())
			.invoke(text -> Log.infof("Response from no-auth endpoint: \"%s\"", text))
			.invoke(() -> Log.info("Call to authorized..."))
			.chain(() -> client.withAuthorizationPing())
			.invoke(text -> Log.infof("Response from authorized endpoint: \"%s\"", text))
			.replaceWithVoid();
	}

}
