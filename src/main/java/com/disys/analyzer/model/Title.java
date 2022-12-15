/**
 * 
 */
package com.disys.analyzer.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sajid
 * 
 */
@Entity
@Table(name = "Titles")
// ,schema="Analyser.dbo")
public class Title implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8130387037780311536L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "resource_name")
	private String resourceName;
	
	private String title;
	
	@Column(name = "resource_id")
	private Integer resourceId;
	
	@Column(name = "is_menu_item")
	private Integer menuItem;
	
	public Title()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Title(String title, String resourceName)
	{
		this.title = title;
		this.resourceName = "/protected/" + resourceName;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
	
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	/**
	 * @return the resourceName
	 */
	public String getResourceName()
	{
		return resourceName;
	}
	
	/**
	 * @param resourceName
	 *            the resourceName to set
	 */
	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Integer getResourceId()
	{
		return resourceId;
	}
	
	public void setResourceId(Integer resourceId)
	{
		this.resourceId = resourceId;
	}
	
	public Integer getMenuItem()
	{
		return menuItem;
	}
	
	public void setMenuItem(Integer menuItem)
	{
		this.menuItem = menuItem;
	}
	
	public static Comparator<Title> COMPARE_BY_TITLE = new Comparator<Title>()
	{
		public int compare(Title one, Title other)
		{
			return one.title.compareTo(other.title);
		}
	};
	
}