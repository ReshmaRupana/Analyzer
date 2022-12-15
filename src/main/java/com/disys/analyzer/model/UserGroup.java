package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the UserGroups database table.
 * 
 */
@Entity
@Table(name="User_Groups")//,schema="Analyser.dbo")
@NamedQuery(name="UserGroup.findAll", query="SELECT u FROM UserGroup u")
public class UserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserGroupPK id;

	//bi-directional one-to-one association to ManagerGroup
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="Group_ID", referencedColumnName="Group_ID"),
		@JoinColumn(name="User_ID", referencedColumnName="Group_ID")
		})
	private ManagerGroup managerGroup;

	public UserGroup() {
	}

	public UserGroupPK getId() {
		return this.id;
	}

	public void setId(UserGroupPK id) {
		this.id = id;
	}

	public ManagerGroup getManagerGroup() {
		return this.managerGroup;
	}

	public void setManagerGroup(ManagerGroup managerGroup) {
		this.managerGroup = managerGroup;
	}

}