package com.example;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;

@ApplicationScoped
public class SecurityIdentityAugmentorImpl implements SecurityIdentityAugmentor {

	@Inject ClientDelegate clientDelegate;

	@Override
	@ActivateRequestContext
	public Uni<SecurityIdentity> augment(SecurityIdentity identity, AuthenticationRequestContext context) {
		SecurityIdentity nextIdentity = QuarkusSecurityIdentity.builder(identity).build();

		boolean testCallInAugmentor = true;

		if (testCallInAugmentor) {
			return clientDelegate.tryCall()
				.replaceWith(nextIdentity);
		} else {
			return Uni.createFrom().item(nextIdentity);
		}
	}
}
