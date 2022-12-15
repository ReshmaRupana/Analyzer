package com.disys.analyzer.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the Resources database table.
 * 
 */
@Entity
@Table(name = "Resources")//,schema="Analyser.dbo")
@NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RES_ID")
	private Integer resId;

	@Column(name = "Edit_Allowed")
	private Boolean editAllowed;

	@Column(name = "Priv_Abbr")
	private String privAbbr;

	@Column(name = "Res_Abbr")
	private String resAbbr;

	@Column(name = "RES_Desc")
	private String resDesc;

	@Column(name = "Res_URL")
	private String resURL;

	@Column(name = "Parent_Id")
	private Integer parentId;
	
	@Column(name = "Title_Id")
	private Integer titleId;
	
	@Column(name = "is_menu_item")
	private Integer menuItem;
	
	private Integer status;
	
	@Transient
	private String firstParent;
	
	@Transient
	private String secondParent;
	
	@Transient
	private String resWithParentName;
	
	@Transient
	private String pageName;
	
	@Transient
	private Integer assigned;
	
	@Transient
	private Integer secondParentId;
	
	public Resource() {
	}

	/**
	 * @return the resId
	 */
	public Integer getResId() {
		return resId;
	}

	/**
	 * @param resId
	 *            the resId to set
	 */
	public void setResId(Integer resId) {
		this.resId = resId;
	}

	/**
	 * @return the editAllowed
	 */
	public Boolean isEditAllowed() {
		return editAllowed;
	}

	/**
	 * @param editAllowed
	 *            the editAllowed to set
	 */
	public void setEditAllowed(Boolean editAllowed) {
		this.editAllowed = editAllowed;
	}

	/**
	 * @return the privAbbr
	 */
	public String getPrivAbbr() {
		return privAbbr;
	}

	/**
	 * @param privAbbr
	 *            the privAbbr to set
	 */
	public void setPrivAbbr(String privAbbr) {
		this.privAbbr = privAbbr;
	}

	/**
	 * @return the resAbbr
	 */
	public String getResAbbr() {
		return resAbbr;
	}

	/**
	 * @param resAbbr
	 *            the resAbbr to set
	 */
	public void setResAbbr(String resAbbr) {
		this.resAbbr = resAbbr;
	}

	/**
	 * @return the resDesc
	 */
	public String getResDesc() {
		return resDesc;
	}

	/**
	 * @param resDesc
	 *            the resDesc to set
	 */
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	/**
	 * @return the resURL
	 */
	public String getResURL() {
		return resURL;
	}

	/**
	 * @param resURL
	 *            the resURL to set
	 */
	public void setResURL(String resURL) {
		this.resURL = resURL;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public Boolean getEditAllowed()
	{
		return editAllowed;
	}

	public Integer getTitleId()
	{
		return titleId;
	}

	public void setTitleId(Integer titleId)
	{
		this.titleId = titleId;
	}

	public String getResWithParentName()
	{
		return resWithParentName;
	}

	public void setResWithParentName(String resWithParentName)
	{
		this.resWithParentName = resWithParentName;
	}

	public String getPageName()
	{
		return pageName;
	}

	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}

	public Integer getMenuItem()
	{
		return menuItem;
	}

	public void setMenuItem(Integer menuItem)
	{
		this.menuItem = menuItem;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getFirstParent()
	{
		return firstParent;
	}

	public void setFirstParent(String firstParent)
	{
		this.firstParent = firstParent;
	}

	public String getSecondParent()
	{
		return secondParent;
	}

	public void setSecondParent(String secondParent)
	{
		this.secondParent = secondParent;
	}

	public Integer getAssigned()
	{
		return assigned;
	}

	public void setAssigned(Integer assigned)
	{
		this.assigned = assigned;
	}

	public Integer getSecondParentId()
	{
		return secondParentId;
	}

	public void setSecondParentId(Integer secondParentId)
	{
		this.secondParentId = secondParentId;
	}

}