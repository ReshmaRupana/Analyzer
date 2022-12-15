package com.disys.analyzer.config;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.disys.analyzer.security.service.ApplicationUserDetailsService;

@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		return applicationUserDetailsService.loadGrantedAuthoritiesByUsername(username);
	}
}