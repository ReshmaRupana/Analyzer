package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ManagerGroups database table.
 * 
 */
@Entity
@Table(name="Manager_Groups")//,schema="Analyser.dbo")
@NamedQuery(name="ManagerGroup.findAll", query="SELECT m FROM ManagerGroup m")
public class ManagerGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="managerGroupId")
	private int managerGroupId;*/
	
	@EmbeddedId
	private ManagerGroupPK id;

	//bi-directional one-to-one association to UserGroup
	@OneToOne(mappedBy="managerGroup")
	private UserGroup userGroup;

	public ManagerGroup() {
	}

	/**
	 * @return the id
	 */
	public ManagerGroupPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ManagerGroupPK id) {
		this.id = id;
	}

	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}