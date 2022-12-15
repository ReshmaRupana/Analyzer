package com.disys.analyzer.config;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import com.disys.analyzer.model.User;

/**
 * @author Sajid Qureshi
 *
 */
public class UserAttributesMapper implements AttributesMapper {

	private String birthdayAttribute = LDAPConstants.BIRTHDAY_ATTRIBUTE;

	private String emailAttribute = LDAPConstants.EMAIL_ATTRIBUTE;

	private String firstNameAttribute = LDAPConstants.FIRST_NAME_ATTRIBUTE;

	private String genderAttribute = LDAPConstants.GENDER_ATTRIBUTE;

	private String lastNameAttribute = LDAPConstants.LAST_NAME_ATTRIBUTE;

	private String passwordAttribute = LDAPConstants.PASSWORD_ATTRIBUTE;

	private String phoneAttribute = LDAPConstants.PHONE_ATTRIBUTE;

	private String statusAttribute = LDAPConstants.USER_STATUS;

	private String userNameAttribute = LDAPConstants.USER_NAME_ATTRIBUTE;
	
	public String getUserIDAttribute() {
		return userIDAttribute;
	}

	public void setUserIDAttribute(String userIDAttribute) {
		this.userIDAttribute = userIDAttribute;
	}

	private String userIDAttribute = LDAPConstants.USER_ID;

	public String getBirthdayAttribute() {
		return birthdayAttribute;
	}

	public String getEmailAttribute() {
		return emailAttribute;
	}

	public String getFirstNameAttribute() {
		return firstNameAttribute;
	}

	public String getGenderAttribute() {
		return genderAttribute;
	}

	public String getLastNameAttribute() {
		return lastNameAttribute;
	}

	public String getPasswordAttribute() {
		return passwordAttribute;
	}

	public String getPhoneAttribute() {
		return phoneAttribute;
	}

	public String getStatusAttribute() {
		return statusAttribute;
	}

	public String getUserNameAttribute() {
		return userNameAttribute;
	}

	/*
	 * @Override public Object mapFromAttributes(Attributes attributes) throws
	 * NamingException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public Object mapFromAttributes(Attributes attributes) throws NamingException {
		// String userName = (String) attributes.get(userNameAttribute).get();
		// String email = (String) attributes.get(emailAttribute).get();
		// String password = (String) attributes.get(passwordAttribute).get();
		// String phone = (String) attributes.get(phoneAttribute).get();
		if (attributes.get(userIDAttribute).get() != null) {
			String userId = (String) attributes.get(userIDAttribute).get();
			String firstName = (String) (attributes.get(firstNameAttribute) != null ? attributes.get(firstNameAttribute).get() : "");
			String lastName = (String) (attributes.get(lastNameAttribute) != null ? attributes.get(lastNameAttribute).get() : "");
			// String birthday = (String) attributes.get(birthdayAttribute).get();
			// String gender = (String) attributes.get(genderAttribute).get();
			int status = 0;

			switch ((String) (String) (attributes.get(statusAttribute) != null ? attributes.get(statusAttribute).get() : "")) {
			case "512":
			case "544":
			case "66048":
			case "66080":
				status = 1;
				break;
			default:
				status = 0;
			}

			User ret = new User();

			ret.setUserId(userId);
			// ret.setPassword(password);
			ret.setFirstName(firstName);
			ret.setLastName(lastName);
			ret.setStatus(status);
			return ret;
		} else {
			return null;
		}
	}

	public void setBirthdayAttribute(String birthdayAttribute) {
		this.birthdayAttribute = birthdayAttribute;
	}

	public void setEmailAttribute(String emailAttribute) {
		this.emailAttribute = emailAttribute;
	}

	public void setFirstNameAttribute(String firstNameAttribute) {
		this.firstNameAttribute = firstNameAttribute;
	}

	public void setGenderAttribute(String genderAttribute) {
		this.genderAttribute = genderAttribute;
	}

	public void setLastNameAttribute(String lastNameAttribute) {
		this.lastNameAttribute = lastNameAttribute;
	}

	public void setPasswordAttribute(String passwordAttribute) {
		this.passwordAttribute = passwordAttribute;
	}

	public void setPhoneAttribute(String phoneAttribute) {
		this.phoneAttribute = phoneAttribute;
	}

	public void setStatusAttribute(String statusAttribute) {
		this.statusAttribute = statusAttribute;
	}


	public void setUserNameAttribute(String userNameAttribute) {
		this.userNameAttribute = userNameAttribute;
	}

	@Override
	public String toString() {
		return "UserAttributesMapper [EMAIL_ATTRIBUTE=" + emailAttribute + ", PHONE_ATTRIBUTE=" + phoneAttribute
				+ ", PASSWORD_ATTRIBUTE=" + passwordAttribute + ", USER_NAME_ATTRIBUTE=" + userNameAttribute
				+ ", FIRST_NAME_ATTRIBUTE=" + firstNameAttribute + ", LAST_NAME_ATTRIBUTE=" + lastNameAttribute
				+ ", BIRTHDAY_ATTRIBUTE=" + birthdayAttribute + ", GENDER_ATTRIBUTE=" + genderAttribute + "]";
	}
}