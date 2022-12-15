/**
 * 
 */
package com.disys.analyzer.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.disys.analyzer.model.Resource;
import com.disys.analyzer.model.RolePrivilege;
import com.disys.analyzer.model.User;
import com.disys.analyzer.repository.ApplicationUserRepository;
import com.disys.analyzer.repository.ResourceRepository;
import com.disys.analyzer.repository.RolePrivilegesRepository;
import com.disys.analyzer.repository.RoleRepository;

import com.disys.analyzer.config.util.Constants;

/**
 * @author Sajid
 * @since Oct 3, 2019
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationUserDetailsService.class);

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RolePrivilegesRepository rolePrivilegesRepository;

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
	{
		logger.warn("In ApplicationUserDetailsService --> loadUserByUsername ");
		System.out.println("In ApplicationUserDetailsService --> loadUserByUsername ");
		logger.warn("username = "+username);
		System.out.println("username = "+username);
		try 
		{
			username = username + Constants.DISYS_DOMAIN;
			logger.warn("User to be Checked username = "+username);
			System.out.println("User to be Checked username = "+username);
			User user = applicationUserRepository.findByUserId(username);
			//User user = applicationUserRepository.findByUserId(username+Constants.DISYS_DOMAIN);
			if (user == null) {
				logger.warn("In ApplicationUserDetailsService --> loadUserByUsername --> Username {} not found", username);
				System.out.println("In ApplicationUserDetailsService --> loadUserByUsername --> Username {} not found = "+username);
				throw new UsernameNotFoundException("Username " + username
						+ " not found");
			}

			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			Integer roleId = user.getRoleId();
			List<RolePrivilege> rolePrivileges = rolePrivilegesRepository
					.findByIdRoleId(roleId);
			for (RolePrivilege rolePrivilege : rolePrivileges) {
				Resource res = resourceRepository.findByResId(rolePrivilege
						.getId().getResId());
				grantedAuthorities.add(new SimpleGrantedAuthority(res
						.getResDesc()));
			}

			return new org.springframework.security.core.userdetails.User(
					user.getUserId(), user.getPassword(), grantedAuthorities);

			// return user;
		} catch (UsernameNotFoundException e) {
			logger.debug(e.getMessage());
			logger.error("In ApplicationUserDetailsService --> loadUserByUsername --> Exception while loading user {}", username);
			System.out.println("In ApplicationUserDetailsService --> loadUserByUsername --> Exception while loading user {} = "+username);
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}
	
	public Collection<GrantedAuthority> loadGrantedAuthoritiesByUsername(String username)
			throws UsernameNotFoundException 
	{
		logger.warn("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername ");
		System.out.println("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername ");
		logger.warn("username = "+username);
		System.out.println("username = "+username);
		try 
		{
			username = username + Constants.DISYS_DOMAIN;
			User user = applicationUserRepository.findByUserId(username);
			if (user == null) 
			{
				logger.warn("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername --> Username {} not found", username);
				System.out.println("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername --> Username {} not found = "+username);
				throw new UsernameNotFoundException("Username " + username
						+ " not found");
			}

			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			Integer roleId = user.getRoleId();
			List<RolePrivilege> rolePrivileges = rolePrivilegesRepository
					.findByIdRoleId(roleId);
			for (RolePrivilege rolePrivilege : rolePrivileges) {
				Resource res = resourceRepository.findByResId(rolePrivilege
						.getId().getResId());
				grantedAuthorities.add(new SimpleGrantedAuthority(res
						.getResDesc()));
			}
			return grantedAuthorities;

			// return user;
		} catch (UsernameNotFoundException e) {
			logger.debug(e.getMessage());
			logger.error("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername --> Exception while loading user {}", username);
			System.out.println("In ApplicationUserDetailsService --> loadGrantedAuthoritiesByUsername --> Exception while loading user {} = "+username);
			throw new UsernameNotFoundException("Username " + username
					+ " not found");
		}
	}
	/*class CustomUser extends User implements UserDetails{
		
		private String company;
		public CustomUser() {
			super();
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}*/

}
