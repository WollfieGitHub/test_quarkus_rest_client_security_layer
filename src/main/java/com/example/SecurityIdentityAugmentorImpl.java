package com.example;

import io.quarkus.logging.Log;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

@ApplicationScoped
public class SecurityIdentityAugmentorImpl implements SecurityIdentityAugmentor {

	@Inject ClientDelegate clientDelegate;

	private boolean firstCall = true;

	@Override
	@ActivateRequestContext
	public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
		Log.infof("[Augmentor] Invoked: Received request with %s", identity);

		SecurityIdentity nextIdentity = QuarkusSecurityIdentity.builder(identity).build();

		// Change this for both scenarios
		boolean giveRole = false;

		if (firstCall) {
			firstCall = false;
			return clientDelegate.tryCall()
					.replaceWith(nextIdentity);
		} else {
			return Uni.createFrom().item(
				QuarkusSecurityIdentity.builder(nextIdentity)
					.addRole(giveRole ? "USER" : "dummy")
					.build()
			);
		}
	}
}
