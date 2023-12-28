package com.example;

import io.quarkus.arc.Arc;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.server.core.CurrentRequestManager;

@RequestScoped
public class ClientDelegate {

	@RestClient Client client;

	@ActivateRequestContext
	public Uni<Void> tryCall() {
		return Uni.createFrom().voidItem()
			.invoke(() -> Log.info("[Delegate]: Calling authorized..."))
			.chain(() -> client.withAuthorizationPing())
			.invoke(text -> Log.infof("[Delegate]: Response from authorized endpoint: \"%s\"", text))
			.replaceWithVoid();
	}

}
