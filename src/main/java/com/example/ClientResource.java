package com.example;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ClientResource implements Client {

	@RestClient Client client;

	@Override
	public Uni<String> withAuthorizationPing() {
		Log.infof("Authorized resource reached");
		return Uni.createFrom().item("pong");
	}

	@Override
	public Uni<String> withoutAuthorizationPing() {
		Log.infof("No-auth resource reached");
		return Uni.createFrom().item("pong");
	}

	@Override
	public Uni<String> hello1() {
		Log.infof("Hello resource reached");
		return Uni.createFrom().item("hello");
	}

	@Inject ClientDelegate clientDelegate;

	@Override
	public Uni<String> hello2() {
		return clientDelegate.tryCall()
			.replaceWith("hello");
	}


}
