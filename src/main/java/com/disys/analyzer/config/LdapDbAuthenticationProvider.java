package com.disys.analyzer.config;

import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;


/**
 * Extended LdapAuthenticationProvider, which enriches the
 * UserDetails with properties and roles held in another
 * datastore (e.g. a database). If there's no entry for 
 * this username in the store, it will be added transparently,
 * so the initial administration for setting up is a non-issue.
 * 
 * @author Sajid Qureshi
 *
 */
//@Component
public class LdapDbAuthenticationProvider extends LdapAuthenticationProvider {

	public LdapDbAuthenticationProvider(
			org.springframework.security.ldap.authentication.LdapAuthenticator authenticator) {
		super(authenticator);
		// TODO Auto-generated constructor stub
	}

//	public LdapDbAuthenticationProvider(LdapAuthenticator authenticator,
//			LdapAuthoritiesPopulator authoritiesPopulator) {
//		super(authenticator, authoritiesPopulator);
//		// TODO Auto-generated constructor stub
//	}
	/*

	*//** Logger *//*
	protected final transient Log log = LogFactory.getLog(getClass());
	
	*//** A user DAO for generating cached entries *//*
	private UserDao userDao;
	
	*//** A role DAO for retrieving additional roles *//*
	private RoleDao roleDao;
	
	*//** The password encryption algorithm *//*
	private String algorithm = "SHA";
	
	
	*//** The encoder used to encrypt the passwords in the store *//* 
	private PasswordEncoder passwordEncoder;
	
	*//**
	 * Setter for the userDao, needed to create cached entries
	 * for LDAP-users in the Database.
	 * 
	 * @param userDao user-DAO
	 *//*
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	*//**
	 * Setter for the roleDao, needed to retrieve the default
	 * user role for creating cached user entries.
	 * 
	 * @param roleDao role-DAO
	 *//*
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	*//**
	 * Sets the password-encoder used for encrypting passes in
	 * the datastore.
	 * 
	 * @param passwordEncoder the needed password encoder
	 *//*
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
        if (passwordEncoder instanceof Md5PasswordEncoder) {
            algorithm = "MD5";
        }
	}
	
	
	*//**
	 * Constructor, which takes a LdapAuthenticator (for binding to
	 * the LDAP-Server) and a LdapAuthoritiesPopulator which will
	 * enrich the user's details with role-information taken from
	 * the LDAP-server.
	 * 
	 * @param authenticator a LdapAuthenticator
	 * @param populator a LdapAuthoritiesPopulator
	 *//*
	public LdapDbAuthenticationProvider(LdapAuthenticator authenticator, LdapAuthoritiesPopulator populator) {
		super(authenticator, populator);
	}
	
	*//**
	 * Overriden createUserDetails() method, where we actually hook in
	 * to convert our LdapUserDetails to a UserDetails of type
	 * org.appfuse.model.User and enrich it with some properties
	 * and roles from the database.
	 * 
	 * @see LdapAuthenticationProvider#createUserDetails()
	 *//*
	@Override
	protected UserDetails createUserDetails(LdapUserDetails ldapUser, String username, String password) {
		UserDetails userDetails = super.createUserDetails(ldapUser, username, password);
		User user = null;
		
		try {
			user = (User) userDao.loadUserByUsername(username);
			
			// update password if necessary
			if (!passwordEncoder.isPasswordValid(user.getPassword(), password, null)) {
		        user.setPassword(StringUtil.encodePassword(password, algorithm));
				user = userDao.save(user);
			}
			
		} catch (UsernameNotFoundException e) {
			// user not found in db, create a new one
			user = new User();
			user.setUsername(username);
			user.setPassword(StringUtil.encodePassword(password, algorithm));
			user.setFirstName(username);
			user.setLastName("LDAP-User");
			user.setFromLdap(true);
			user.setEnabled(true);
			user.setAccountExpired(false);
			user.setAccountLocked(false);
			user.setCredentialsExpired(false);
			user.setEmail(username + "@company.com");
			
			user.addRole(roleDao.getRoleByName(Constants.USER_ROLE));
			user = userDao.save(user);
		}
		
		// add roles from active directory
		GrantedAuthority[] authorities = userDetails.getAuthorities();
		HashSet<Role> adRoles = new HashSet<Role>();
		for (int i = 0; i < authorities.length; i++) {
			Role adRole = new Role();
			adRole.setName(authorities[i].getAuthority());
			adRoles.add(adRole);
		}
		user.setLdapRoles(adRoles);
		
		return user;
	}
*/}

