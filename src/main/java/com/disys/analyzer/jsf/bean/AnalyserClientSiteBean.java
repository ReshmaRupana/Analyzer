/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;
import com.disys.analyzer.service.AnalyserClientSiteService;

/**
 * @author Sajid
 * @since Nov 6, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserClientSiteBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8604798337947011819L;

	private Logger logger = LoggerFactory
			.getLogger(AnalyserClientSiteBean.class);

	@Autowired
	private AnalyserClientSiteService service;

	AnalyserClientSiteDTO clientSite;

	List<AnalyserClientSiteDTO> list;
	List<AnalyserClientSiteDTO> filteredList;

	String userId;
	String userList;
	String orderBy;
	String searchString;

	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public AnalyserClientSiteBean() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		this.userId = FacesUtils.getCurrentUserId();
		this.userList = params.get("userList");
		this.orderBy = params.get("orderBy");
		this.searchString = params.get("searchString");
	}

	/**
	 * @return the clientSite
	 */
	public AnalyserClientSiteDTO getClientSite() {
		return clientSite;
	}

	/**
	 * @param clientSite
	 *            the clientSite to set
	 */
	public void setClientSite(AnalyserClientSiteDTO clientSite) {
		this.clientSite = clientSite;
	}

	/**
	 * @return the list
	 */
	public List<AnalyserClientSiteDTO> getList() {
		if (list == null) {
			list = new ArrayList<AnalyserClientSiteDTO>();

			/*String userId = "Gregory.Armstrong@DISYS.COM";
			String userList = "1";
			String orderBy = "Company";
			String searchString = "ALL";*/

			list = service.getAnalyserAllSites(userId, userList, orderBy, searchString);
			//userId, clientId, homezip, workFromSource
			//list = service.getAnalyserAllSitesSickCaluculations(userId, userList, orderBy, searchString);
			
		//	getAnalyserAllSitesSickCaluculations

			logger.debug("AnalyserClientSiteDTO List size is : " + list.size());
			logger.debug("call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllSite {?, ?, ?, ?}");
			logger.debug("call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllSite {'" + userId + "', "
					+ userList + ", '" + orderBy + "', '" + searchString + "'}");

			System.out.println("AnalyserClientSiteDTO List size is : "
					+ list.size());
			
			System.out.println("call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllSite {'" + userId + "', "
					+ userList + ", '" + orderBy + "', '" + searchString + "'}");

		}
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserClientSiteDTO> list) {
		this.list = list;
	}

	public void selectAnalyserClientSiteFromDialog(
			AnalyserClientSiteDTO analyserClientSiteDTO) {
		RequestContext.getCurrentInstance().closeDialog(analyserClientSiteDTO);
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userList
	 */
	public String getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(String userList) {
		this.userList = userList;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy
	 *            the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the filteredList
	 */
	public List<AnalyserClientSiteDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<AnalyserClientSiteDTO> filteredList) {
		this.filteredList = filteredList;
	}
}
