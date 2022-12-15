package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the Roles database table.
 * 
 */
@Entity
@Table(name = "Roles")//,schema="Analyser.dbo")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROL_ID")
	private Integer roleId;

	@Column(name = "Role_Abbrev")
	private String roleAbbrev;

	@Column(name = "Role_Desc")
	private String roleDesc;

	@Column(name = "ORG_ID")
	private Integer orgId;

	public Role() {
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleAbbrev
	 */
	public String getRoleAbbrev() {
		return roleAbbrev;
	}

	/**
	 * @param roleAbbrev
	 *            the roleAbbrev to set
	 */
	public void setRoleAbbrev(String roleAbbrev) {
		this.roleAbbrev = roleAbbrev;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc
	 *            the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleAbbrev=" + roleAbbrev
				+ ", roleDesc=" + roleDesc + ", orgId=" + orgId + "]";
	}

}