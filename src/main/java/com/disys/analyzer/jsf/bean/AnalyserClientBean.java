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
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.service.AnalyserClientService;

/**
 * @author Sajid
 * @since Nov 3, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserClientBean extends SpringBeanAutowiringSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7240538889507716916L;

	private Logger logger = LoggerFactory.getLogger(AnalyserClientBean.class);

	@Autowired
	private AnalyserClientService service;

	private AnalyserClientDTO analyserClient;

	private List<AnalyserClientDTO> list;
	private List<AnalyserClientDTO> filteredList;

	private List<AnalyserClientDTO> invoiceList;
	private List<AnalyserClientDTO> filteredInvoiceList;
	
	private List<SelectItem> analyserClientList;

	@Autowired
	AnalyserClientService analyserClientService;

	private String userId;
	private String userList;
	private String orderBy;
	private String searchString;
	private String vertical;
	private String product;
	private String branch;
	private String companyCode;

	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public AnalyserClientBean() {

		try {

			analyserClient = new AnalyserClientDTO();
			Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			this.userId = FacesUtils.getCurrentUserId();
			this.userList = params.get("userList");
			this.orderBy = params.get("orderBy");
			this.searchString = params.get("searchString");
			this.vertical = params.get("vertical");
			this.product = params.get("product");
			this.branch = params.get("branch");
			this.companyCode = params.get("companyCode");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @return the analyserClient
	 */
	public AnalyserClientDTO getAnalyserClient() {
		return analyserClient;
	}

	/**
	 * @param analyserClient
	 *            the analyserClient to set
	 */
	public void setAnalyserClient(AnalyserClientDTO analyserClient) {
		this.analyserClient = analyserClient;
	}

	/**
	 * @return the list
	 */
	public List<AnalyserClientDTO> getList() {
		System.out.println("In AnalyserClientBean --> getList()");
		logger.debug("In AnalyserClientBean --> getList()");
		if (list == null) {
			list = new ArrayList<AnalyserClientDTO>();

			/*String userId = "Gregory.Armstrong@DISYS.COM";
			String userList = "dummy";
			String orderBy = "Company";
			String searchString = "ALL";
			String vertical = "Hi Tech";
			String product = "STAFF AUG";*/

			list = service.getAnalyserAllClients(userId, userList, orderBy, searchString, vertical, product, branch, companyCode);
			System.out.println("In AnalyserClientBean --> getList() after Service");
			logger.debug("In AnalyserClientBean --> getList() after Service");
			System.out.println("List Size : " + list.size());
			logger.debug("List Size : " + list.size());
			System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(?, ?, ?, ?, ?, ?, ?, ?)}");
			logger.debug("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(?, ?, ?, ?, ?, ?, ?, ?)}");
			System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(\"" + userId
					+ "\", \"" + userList + "\", \"" + orderBy + "\", \"" + searchString
					+ "\", \"" + vertical + "\", \"" + product + "\", \"" + branch + "\", \"" +companyCode+ "\")}");
			logger.debug("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(\"" + userId
					+ "\", \"" + userList + "\", \"" + orderBy + "\", \"" + searchString
					+ "\", \"" + vertical + "\", \"" + product + "\", \"" + branch + "\", \"" +companyCode+ "\")}");
			System.out.println("List fetched for All clients");
			logger.debug("List fetched for All clients");

		}
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserClientDTO> list) {
		this.list = list;
	}

	public void selectAnalyserClientFromDialog(AnalyserClientDTO analyserClient) {
		RequestContext.getCurrentInstance().closeDialog(analyserClient);
	}

	/**
	 * @return the invoiceList
	 */
	public List<AnalyserClientDTO> getInvoiceList() {
		System.out.println("In AnalyserClientBean --> getInvoiceList()");
		logger.debug("In AnalyserClientBean --> getInvoiceList()");
		if (invoiceList == null) {
			invoiceList = new ArrayList<AnalyserClientDTO>();

			/*String userId = "Gregory.Armstrong@DISYS.COM";
			String userList = "dummy";
			String orderBy = "Company";
			String searchString = "ALL";
			String vertical = "Hi Tech";
			String product = "STAFF AUG";*/

			invoiceList = service.getAnalyserAllClients(userId, userList, orderBy, searchString, vertical, product, branch, companyCode);
			System.out.println("Invoice List Size : " + invoiceList.size());
			System.out.println("Invoice List Size : " + invoiceList.size());
			System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(?, ?, ?, ?, ?, ?, ?, ?)}");
			System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(" + userId
					+ ", " + userList + ", " + orderBy + ", " + searchString
					+ ", " + vertical + ", " + product + ", " + branch + ", " + companyCode + ")}");
			logger.debug("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(" + userId + ", "
					+ userList + ", " + orderBy + ", " + searchString + ", "
					+ vertical + ", " + product + ", " + branch + ", " + companyCode + ")}");
			System.out.println("List fetched for All Client Invoices");
			logger.debug("List fetched for All Client Invoices");
		}
		return invoiceList;
	}

	/**
	 * @param invoiceList
	 *            the invoiceList to set
	 */
	public void setInvoiceList(List<AnalyserClientDTO> invoiceList) {
		this.invoiceList = invoiceList;
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
	 * @return the vertical
	 */
	public String getVertical() {
		return vertical;
	}

	/**
	 * @param vertical
	 *            the vertical to set
	 */
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the filteredList
	 */
	public List<AnalyserClientDTO> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<AnalyserClientDTO> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the filteredInvoiceList
	 */
	public List<AnalyserClientDTO> getFilteredInvoiceList() {
		return filteredInvoiceList;
	}

	/**
	 * @param filteredInvoiceList
	 *            the filteredInvoiceList to set
	 */
	public void setFilteredInvoiceList(
			List<AnalyserClientDTO> filteredInvoiceList) {
		this.filteredInvoiceList = filteredInvoiceList;
	}

	/**
	 * @return the branch
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public List<SelectItem> getAnalyserClientByCompanyCodeList(String compCode) 
	{
		if (analyserClientList == null) 
		{
			analyserClientList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserClientDTO> analyserClientLists = service.getAnalyserClientList(userId, compCode);
				
				if (analyserClientLists != null)
				{
					for (AnalyserClientDTO c:analyserClientLists)
					{						
						analyserClientList.add(new SelectItem(c.getCompany(), c.getCompany()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserClientBean --> getAnalyserClientByCompanyCodeList.");
				logger.debug("Exception in AnalyserClientBean --> getAnalyserClientByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyserClientList;
	}
	
	public String getAnalyserClientDescription(String compCode, String recordCode) 
	{
			List<AnalyserClientDTO> analyserClientDescriptionList = new ArrayList<AnalyserClientDTO>();
			try
			{
				analyserClientDescriptionList = service.getAnalyserClientDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserClientBean --> getAnalyserClientDescription.");
				logger.debug("Exception in AnalyserClientBean --> getAnalyserClientDescription.");
				e.printStackTrace();
			}
			AnalyserClientDTO analyserClientDTO = analyserClientDescriptionList.isEmpty()?null: analyserClientDescriptionList.get(0);
			if(analyserClientDTO != null) {
				return analyserClientDTO.getCompany().isEmpty()? analyserClientDTO.getCompany() : analyserClientDescriptionList.get(0).getCompany();
			}
			return "";
	}
	public List<SelectItem> getAnalyserClientList() {
		return analyserClientList;
	}

	public void setAnalyserClientList(List<SelectItem> analyserClientList) {
		this.analyserClientList = analyserClientList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
}
