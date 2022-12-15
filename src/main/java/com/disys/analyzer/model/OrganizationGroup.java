package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the OrganizationGroups database table.
 * 
 */
@Entity
@Table(name = "Organization_Groups")//,schema="Analyser.dbo")
@NamedQuery(name = "OrganizationGroup.findAll", query = "SELECT o FROM OrganizationGroup o")
public class OrganizationGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @EmbeddedId private OrganizationGroupPK id;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Group_ID")
	private Integer groupId;

	@Column(name = "Group_Description")
	private String groupDescription;

	@Column(name = "Org_ID")
	private Integer orgId;

	@Column(name = "Active")
	private Boolean active;

	public OrganizationGroup() {
	}
	
	/**
	 * @param groupId
	 * @param groupDescription
	 * @param orgId
	 * @param active
	 */
	public OrganizationGroup(Integer groupId, String groupDescription,
			Integer orgId, Boolean active) {
		super();
		this.groupId = groupId;
		this.groupDescription = groupDescription;
		this.orgId = orgId;
		this.active = active;
	}




	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}


	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @param groupDescription
	 *            the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
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

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}