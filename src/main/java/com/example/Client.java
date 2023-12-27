package com.example;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.awt.*;

@Path("/")
@ApplicationScoped
@RegisterClientHeaders(ClientHeadersFactoryImpl.class)
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface Client {

	@GET
	@Path("/ping/auth")
	@RolesAllowed("USER")
	@Produces(MediaType.TEXT_PLAIN)
	Uni<String> withAuthorizationPing();

	@GET
	@Path("/ping/no-auth")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	Uni<String> withoutAuthorizationPing();

	@GET
	@Path("/hello1")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	Uni<String> hello1();

	@GET
	@Path("/hello2")
	@PermitAll
	@Produces(MediaType.TEXT_PLAIN)
	Uni<String> hello2();


}
