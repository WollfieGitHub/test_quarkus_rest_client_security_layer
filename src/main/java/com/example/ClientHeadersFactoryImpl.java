package com.example;

import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.ReactiveClientHeadersFactory;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

@RequestScoped
public class ClientHeadersFactoryImpl extends ReactiveClientHeadersFactory {

	@Override
	public Uni<MultivaluedMap<String, String>> getHeaders(
		MultivaluedMap<String, String> incomingHeaders,
		MultivaluedMap<String, String> clientOutgoingHeaders
	) {
		Log.infof("[Factory] Invoked: Incoming headers : %s", incomingHeaders.entrySet());

		return Uni.createFrom().item(new MultivaluedHashMap<>());
	}

}
