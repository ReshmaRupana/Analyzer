package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the RolePrivileges database table.
 * 
 */
@Entity
@Table(name = "Role_Privileges")//,schema="Analyser.dbo")
@NamedQuery(name = "RolePrivilege.findAll", query = "SELECT r FROM RolePrivilege r")
public class RolePrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePrivilegePK id;

	/*
	 * @Column(name = "ROL_ID") private Integer roleId;
	 * 
	 * @Column(name = "RES_ID") private Integer resId;
	 */

	public RolePrivilege() {
	}

	public RolePrivilegePK getId() {
		return this.id;
	}

	public void setId(RolePrivilegePK id) {
		this.id = id;
	}

}