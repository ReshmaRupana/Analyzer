/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserClientAddress;
import com.disys.analyzer.service.AnalyserClientAddressService;
import com.disys.analyzer.service.AnalyserClientService;

/**
 * @author Sajid
 * @since Oct 31, 201
 */
@ManagedBean
@ViewScoped
public class AnalyserClientAddressBean extends SpringBeanAutowiringSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AnalyserClientAddressBean.class);

	@Autowired
	private AnalyserClientAddressService service;

	@Autowired
	private AnalyserClientService analyserClientService;

	private AnalyserClientAddress analyserClientAddress;

	private List<SelectItem> uSAStates;

	private List<SelectItem> clientNames;

	private Map<Integer, String> clientNamesMap;

	private String action;
	private String analyserAddressId;
	private Integer clientId;

	private boolean showReadOnly;
	
	private String url;

	@PostConstruct
	private void postConstruct() {
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	/**
	 * 
	 */
	public AnalyserClientAddressBean() {
		super();
		logger.info("******** Initializing AnalyserClientAddressBean ********");

		try {
			FacesContext fc = FacesUtils.getFacesContext();
			ExternalContext externalContext = fc.getExternalContext();
			Map<String, String> requestMap = externalContext.getRequestParameterMap();

			requestMap.entrySet().forEach(entry -> {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (entry.getKey().equals("analyserAddressId")) {
					analyserAddressId = entry.getValue();
				} else if (entry.getKey().equals("action")) {
					action = entry.getValue();
				}
			});
			if (action.equals("Add")) {
				showReadOnly = false;
				analyserClientAddress = new AnalyserClientAddress();
				analyserClientAddress.setClientId(Integer.valueOf(analyserAddressId));
			} else if (analyserAddressId != null) {
				analyserClientAddress = service.getAnalyserClientAddressInfo(analyserAddressId);
				if (action.equalsIgnoreCase("Modify")) {
					showReadOnly = false;
					clientId = analyserClientAddress.getClientId();
				} else {
					showReadOnly = true;
				}
				logger.debug("Analyser Client Address found : " + analyserClientAddress.getContactName());
			} else {
				analyserClientAddress = new AnalyserClientAddress();
				/*
				 * clientNamesMap =
				 * analyserClientService.getClientList(FacesUtils.getCurrentUserId());
				 * iterateUsingIteratorAndEntry(clientNamesMap);
				 */
				showReadOnly = false;
			}
			clientNamesMap = new HashMap<Integer, String>();

			List<Object[]> clients = analyserClientService.getClientList(FacesUtils.getCurrentUserId());

			clientNames = new ArrayList<SelectItem>();

			for (Object[] a : clients) {
				System.out.println("client " + a[0] + " " + a[1]);
				clientNames.add(new SelectItem(a[0], a[1].toString()));

			}
			
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();


			url = req.getRequestURL().toString();
			System.out.println("Calling url is : "+url);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateAnalyserClientCompanyAddress() throws IOException {
		analyserClientAddress.setClientId(clientId);
		logger.debug("About to update company address for the company id : " + analyserClientAddress.getClientId());
		String result = service.saveAnalyserClientCompanyAddress(2, analyserClientAddress);
		System.out.println("Update status : " + result);
		
		String detail = "";
		String title = "Success";
		FacesMessage message = null;
		if (result.equals("0")) {
			detail = "Successfully updated";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		} else {
			detail = "Failded to update analyser company address.";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", detail);
		}

		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		
		String defaultSuccessURL = "/protected/analyser-client-addresses.xhtml?clientId="+clientId+"&action=ClientAddress";
		
		FacesUtils.redirect(defaultSuccessURL, message);
		//response.sendRedirect(defaultSuccessURL);
		//http://localhost:8080/Analyzer/protected/analyser-client-addresses.xhtml?clientId=502367&action=ClientAddress
		
	}

	public void saveAnalyserClientCompanyAddress() {
		analyserClientAddress.setClientId(Integer.valueOf(analyserAddressId));
		logger.debug("About to save company address for the company id : " + analyserClientAddress.getClientId());
		String result = service.saveAnalyserClientCompanyAddress(1, analyserClientAddress);
		System.out.println("Save status : " + result);

		String title = "Success";
		String detail = "";
		// Returns: 0 for Success 1 for Failure and 2 Already Exists
		FacesMessage message = null;
		if (result.equals("0")) {
			detail = "Successfully created";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		} else {
			detail = "Failded to create analyser company address.";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", detail);
		}

		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		closeAnalyserAddressDialog(analyserClientAddress);
	}

	public void closeAnalyserAddressDialog(AnalyserClientAddress analyserClientAddress) {
		RequestContext.getCurrentInstance().closeDialog(analyserClientAddress);
	}

	/**
	 * @return the uSAStates
	 */
	public List<SelectItem> getuSAStates() {
		if (uSAStates == null) {
			uSAStates = new ArrayList<SelectItem>();
			uSAStates.add(new SelectItem("AL", "Alabama"));
			uSAStates.add(new SelectItem("AK", "Alaska"));
			uSAStates.add(new SelectItem("AZ", "Arizona"));
			uSAStates.add(new SelectItem("AR", "Arkansas"));
			uSAStates.add(new SelectItem("CA", "California"));
			uSAStates.add(new SelectItem("CO", "Colorado"));
			uSAStates.add(new SelectItem("CT", "Connecticut"));
			uSAStates.add(new SelectItem("DE", "Delaware"));
			uSAStates.add(new SelectItem("DC", "District Of Columbia"));
			uSAStates.add(new SelectItem("FL", "Florida"));
			uSAStates.add(new SelectItem("GA", "Georgia"));
			uSAStates.add(new SelectItem("HI", "Hawaii"));
			uSAStates.add(new SelectItem("ID", "Idaho"));
			uSAStates.add(new SelectItem("IL", "Illinois"));
			uSAStates.add(new SelectItem("IN", "Indiana"));
			uSAStates.add(new SelectItem("IA", "Iowa"));
			uSAStates.add(new SelectItem("KS", "Kansas"));
			uSAStates.add(new SelectItem("KY", "Kentucky"));
			uSAStates.add(new SelectItem("LA", "Louisiana"));
			uSAStates.add(new SelectItem("ME", "Maine"));
			uSAStates.add(new SelectItem("MD", "Maryland"));
			uSAStates.add(new SelectItem("MA", "Massachusetts"));
			uSAStates.add(new SelectItem("MI", "Michigan"));
			uSAStates.add(new SelectItem("MN", "Minnesota"));
			uSAStates.add(new SelectItem("MS", "Mississippi"));
			uSAStates.add(new SelectItem("MO", "Missouri"));
			uSAStates.add(new SelectItem("MT", "Montana"));
			uSAStates.add(new SelectItem("NE", "Nebraska"));
			uSAStates.add(new SelectItem("NV", "Nevada"));
			uSAStates.add(new SelectItem("NH", "New Hampshire"));
			uSAStates.add(new SelectItem("NJ", "New Jersey"));
			uSAStates.add(new SelectItem("NM", "New Mexico"));
			uSAStates.add(new SelectItem("NY", "New York"));
			uSAStates.add(new SelectItem("NC", "North Carolina"));
			uSAStates.add(new SelectItem("ND", "North Dakota"));
			uSAStates.add(new SelectItem("OH", "Ohio"));
			uSAStates.add(new SelectItem("OK", "Oklahoma"));
			uSAStates.add(new SelectItem("OR", "Oregon"));
			uSAStates.add(new SelectItem("PA", "Pennsylvania"));
			uSAStates.add(new SelectItem("RI", "Rhode Island"));
			uSAStates.add(new SelectItem("SC", "South Carolina"));
			uSAStates.add(new SelectItem("SD", "South Dakota"));
			uSAStates.add(new SelectItem("TN", "Tennessee"));
			uSAStates.add(new SelectItem("TX", "Texas"));
			uSAStates.add(new SelectItem("UT", "Utah"));
			uSAStates.add(new SelectItem("VT", "Vermont"));
			uSAStates.add(new SelectItem("VA", "Virginia"));
			uSAStates.add(new SelectItem("WA", "Washington"));
			uSAStates.add(new SelectItem("WV", "West Virginia"));
			uSAStates.add(new SelectItem("WI", "Wisconsin"));
			uSAStates.add(new SelectItem("WY", "Wyoming"));

		}
		return uSAStates;
	}

	/**
	 * @param uSAStates the uSAStates to set
	 */
	public void setuSAStates(List<SelectItem> uSAStates) {
		this.uSAStates = uSAStates;
	}

	/**
	 * @return the clientNames
	 */
	public List<SelectItem> getClientNames() {
		return clientNames;
	}

	/**
	 * @param clientNames the clientNames to set
	 */
	public void setClientNames(List<SelectItem> clientNames) {
		this.clientNames = clientNames;
	}

	/**
	 * Reading hashmap with Iterator and Entry Set
	 * 
	 */
	public void iterateUsingIteratorAndEntry(Map<Integer, String> map) {

		clientNames = new ArrayList<SelectItem>();

		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			clientNames.add(new SelectItem(entry.getKey(), entry.getValue().toString()));
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	/**
	 * Reading hashmap with With Lambdas
	 * 
	 */

	public void iterateUsingLambda(Map<Integer, String> map) {
		clientNames = new ArrayList<SelectItem>();
		map.forEach((k, v) -> clientNames.add(new SelectItem(k, v.toString())));
	}

	/**
	 * Reading hashmap with With Streams
	 * 
	 */
	public void iterateUsingStreamAPI(Map<Integer, String> map) {
		clientNames = new ArrayList<SelectItem>();
		map.entrySet().stream().forEach(e -> clientNames.add(new SelectItem(e.getKey(), e.getValue().toString())));
	}

	/**
	 * @return the analyserClientAddress
	 */
	public AnalyserClientAddress getAnalyserClientAddress() {
		return analyserClientAddress;
	}

	/**
	 * @param analyserClientAddress the analyserClientAddress to set
	 */
	public void setAnalyserClientAddress(AnalyserClientAddress analyserClientAddress) {
		this.analyserClientAddress = analyserClientAddress;
	}

	/**
	 * @return the clientNamesMap
	 */
	public Map<Integer, String> getClientNamesMap() {
		return clientNamesMap;
	}

	/**
	 * @param clientNamesMap the clientNamesMap to set
	 */
	public void setClientNamesMap(Map<Integer, String> clientNamesMap) {
		this.clientNamesMap = clientNamesMap;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the analyserAddressId
	 */
	public String getAnalyserAddressId() {
		return analyserAddressId;
	}

	/**
	 * @param analyserAddressId the analyserAddressId to set
	 */
	public void setAnalyserAddressId(String analyserAddressId) {
		this.analyserAddressId = analyserAddressId;
	}

	/**
	 * @return the showReadOnly
	 */
	public boolean isShowReadOnly() {
		return showReadOnly;
	}

	/**
	 * @param showReadOnly the showReadOnly to set
	 */
	public void setShowReadOnly(boolean showReadOnly) {
		this.showReadOnly = showReadOnly;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}
