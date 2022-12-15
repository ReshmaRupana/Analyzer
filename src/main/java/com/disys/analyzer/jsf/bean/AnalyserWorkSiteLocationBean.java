/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.model.dto.AnalyserUspsAddress;
import com.disys.analyzer.security.service.util.SmartyStreetService;
import com.disys.analyzer.service.AnalyserClientService;
import com.disys.analyzer.service.AnalyserClientSiteService;

/**
 * @author Sajid
 * @since Nov 25, 2019
 */
@ManagedBean
@ViewScoped
public class AnalyserWorkSiteLocationBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 6779880473476059148L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<SelectItem> uSAStates;
	private List<SelectItem> clientCompany;
	private List<SelectItem> countryNames;
	private Map<Integer, String> clientNamesMap;
	private AnalyserClientSite analyserClientSite;
	@Autowired private AnalyserClientService analyserClientService;
	@Autowired private AnalyserClientSiteService analyserClientSiteService;
	private String clientId;
	private String analyserSiteId;
	private String action;
	private boolean showReadOnly;
	private boolean showUpdateButton;
	private List<AnalyserUspsAddress> oldAddressList;
	private List<AnalyserUspsAddress> newAddressList;
	private String state;
	private Integer validated;
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserWorkSiteLocationBean()
	{
		logger.debug("Starting Analyser Work Site Location Bean");
		try
		{
			FacesContext fc = FacesUtils.getFacesContext();
			ExternalContext externalContext = fc.getExternalContext();
			Map<String, String> requestMap = externalContext.getRequestParameterMap();
			
			requestMap.entrySet().forEach(entry ->
			{
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (entry.getKey().equals("analyserSiteId"))
				{
					analyserSiteId = entry.getValue();
				}
				else if (entry.getKey().equals("action"))
				{
					action = entry.getValue();
				}
				else if (entry.getKey().equals("clientId"))
				{
					clientId = entry.getValue();
				}
			});
			
			if (clientId != null && analyserSiteId == null)
			{
				analyserClientSite = new AnalyserClientSite();
				analyserClientSite.setClientId(Integer.valueOf(clientId));
				showReadOnly = false;
				showUpdateButton = false;
			}
			else if (analyserSiteId != null)
			{
				if (action.equals("View"))
				{
					showReadOnly = true;
				}
				else
				{
					showReadOnly = false;
					showUpdateButton = true;
				}
				analyserClientSite = analyserClientSiteService.getAnalyserClientSiteInfo(analyserSiteId);
				logger.debug("Client site found  : " + analyserClientSite.getCountry());
			}
			
			List<Object[]> clients = analyserClientService.getClientList(FacesUtils.getCurrentUserId());
			
			clientCompany = new ArrayList<SelectItem>();
			
			for (Object[] a : clients)
			{
				// System.out.println("client " + a[0] + " " + a[1]);
				clientCompany.add(new SelectItem(a[0], a[1].toString()));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void saveAnalyserWorkSite()
	{
		logger.debug("About to save Analyser Work Site...");
		if (analyserClientSite.getSiteId() == null)
		{
			analyserClientSite.setSiteId(0);
		}
		else
		{
			analyserClientSite.setClientId(Integer.valueOf(clientId));
		}
		
		if(analyserClientSite.getIsAddressUSPSValidated() != null && analyserClientSite.getIsAddressUSPSValidated().equals("1"))
		{
			analyserClientSite.setValidatedBy(FacesUtils.getCurrentUserId());
			Instant now = Instant.now();
		    Timestamp current = Timestamp.from(now);
			analyserClientSite.setuSPSAddressValidationDate(current);
		}
		else
		{
			analyserClientSite.setIsAddressUSPSValidated("0");
		}
		if(analyserClientSite.getLatitude() == null)
		{
			analyserClientSite.setLatitude("0.0");
		}
		if(analyserClientSite.getLongitude() == null)
		{
			analyserClientSite.setLongitude("0.0");
		}
		
		// action type 1 for insert
		int actionType = 1;
		String result = analyserClientSiteService.addUpdateAnalyserSite(analyserClientSite, FacesUtils.getCurrentUserId(), actionType);
		
		String title = "Success";
		String detail = "";
		// Returns: 0 for Success 1 for Failure and 2 Already Exists
		FacesMessage message = null;
		if (result.equals("0"))
		{
			detail = "Already Exists";
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failure", detail);
		}
		else if (result.equals("1"))
		{
			detail = "Successfully created";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		}
		else if (result.equals("2"))
		{
			detail = "Failded to create analyser work site location.";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", detail);
		}
		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		closeAnalyserWorkSiteFromDialog(analyserClientSite);
	}
	
	public void updateAnalyserWorkSite()
	{
		logger.debug("About to update Analyser Work Site...");
		// action type 2 for update
		int actionType = 2;
		analyserClientSite.setClientId(Integer.valueOf(clientId));
		String result = analyserClientSiteService.addUpdateAnalyserSite(analyserClientSite, FacesUtils.getCurrentUserId(), actionType);
		
		String title = "Success";
		String detail = "";
		// Returns: 0 for Success 1 for Failure and 2 Already Exists
		FacesMessage message = null;
		if (result.equals("0"))
		{
			detail = "Successfully updated";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		}
		else
		{
			detail = "Failded to update analyser work site location.";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", detail);
		}
		
		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		
		String defaultSuccessURL = "/protected/analyser-client-sites.xhtml?clientId=" + clientId + "&action=ClientSites";
		
		FacesUtils.redirect(defaultSuccessURL, message);
		
		// http://localhost:8080/Analyzer/protected/analyser-client-sites.xhtml?clientId=502367&action=ClientSites
		
	}
	
	public void discardChanges()
	{
		// http://localhost:8080/Analyzer/protected/analyser-client-sites.xhtml?clientId=502248&action=ClientSites
		
		// TODO send the page back to the referring url.
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes Discarded", "Changes discarded!");
		FacesUtils.redirect("/protected/analyser-list.xhtml", message);
	}
	
	public void closeAnalyserWorkSiteFromDialog(AnalyserClientSite analyserClientSite)
	{
		RequestContext.getCurrentInstance().closeDialog(analyserClientSite);
	}
	
	public void validateZipCodeDlg()
	{
		try
		{
			System.out.println("Zip Code is : " + analyserClientSite.getZipCode());
			if (analyserClientSite.getZipCode() == null || analyserClientSite.getZipCode().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Zip Code is required", "Enter zip code and then try again!");
				return;
			}
			else if (analyserClientSite.getAddress1() == null || analyserClientSite.getAddress1().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Address not entered", "Enter Address 1 and then try again!");
				return;
			}
			SmartyStreetService objSmarty = new SmartyStreetService();
			Map<Integer, String> status = objSmarty.validateAddress(analyserClientSite.getAddress1(), analyserClientSite.getAddress2(),
			        analyserClientSite.getCity(), analyserClientSite.getState(), analyserClientSite.getZipCode());
			Integer code = 0;
			String response = "";
			for (Entry<Integer, String> entry : status.entrySet())
			{
				code = entry.getKey();
				response = entry.getValue();
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			}
			if (code == 200)
			{
				if (!response.startsWith("["))
				{
					response = "[" + response + "]";
				}
				JSONArray jsonArray = new JSONArray(response.toString());
				JSONObject myResponse = jsonArray.getJSONObject(0);
				JSONObject longLat = myResponse.getJSONObject("metadata");
				String latitude = "0.0";
				String longitude = "0.0";
				
				String newAddress1 = "", newAddress2 = "", lastLine = "";
				String city = "", zipCode = "";
				state = "";
				try
				{
					latitude = String.valueOf(longLat.getDouble("latitude"));
					longitude = String.valueOf(longLat.getDouble("longitude"));
					newAddress1 = myResponse.getString("delivery_line_1");
					lastLine = myResponse.getString("last_line");
					if (lastLine != "" || lastLine != null && lastLine.length() > 0)
					{
						zipCode = lastLine.substring(lastLine.lastIndexOf(' '), lastLine.length());
						lastLine = lastLine.substring(0, lastLine.lastIndexOf(' '));
						state = lastLine.substring(lastLine.lastIndexOf(' '), lastLine.length());
						lastLine = lastLine.substring(0, lastLine.lastIndexOf(' '));
						city = lastLine;
						if (zipCode != null)
						{
							zipCode = zipCode.trim();
						}
						if (state != null)
						{
							state = state.trim();
						}
						if (city != null)
						{
							city = city.trim();
						}
						System.out.println("Zip Code :: " + zipCode);
						System.out.println("State    :: " + state);
						System.out.println("City     :: " + city);
					}
				}
				catch (org.json.JSONException ex)
				{
					System.err.println("Json exception...");
				}
				oldAddressList = new ArrayList<AnalyserUspsAddress>();
				AnalyserUspsAddress oldAddress = new AnalyserUspsAddress();
				oldAddress.setAddress1(analyserClientSite.getAddress1());
				oldAddress.setAddress2(analyserClientSite.getAddress2());
				oldAddress.setCity(analyserClientSite.getCity());
				oldAddress.setState(analyserClientSite.getState());
				oldAddress.setZipCode(analyserClientSite.getZipCode());
				oldAddress.setLatitude("0.0");
				oldAddress.setLongitude("0.0");
				oldAddress.setValidated(0);
				oldAddressList.add(oldAddress);
				
				newAddressList = new ArrayList<AnalyserUspsAddress>();
				AnalyserUspsAddress validatedAddress = new AnalyserUspsAddress();
				validatedAddress.setAddress1(newAddress1);
				validatedAddress.setAddress2(newAddress2);
				validatedAddress.setCity(city);
				validatedAddress.setState(state);
				validatedAddress.setZipCode(zipCode);
				validatedAddress.setLatitude(latitude);
				validatedAddress.setLongitude(longitude);
				validatedAddress.setValidated(1);
				newAddressList.add(validatedAddress);
				
				//analyserClientSite.setIsAddressUSPSValidated("1");
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('validateZipDlg').show();");
			}
			else
			{
				analyserClientSite.setIsAddressUSPSValidated("0");
				String summary = "Invalid Address";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
		}
		catch (Exception ex)
		{
			analyserClientSite.setIsAddressUSPSValidated("0");
			ex.printStackTrace();
			String summary = "Error validating Zip Code";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		
	}
	
	public void selectAnalyserUspsAddressFromDialog(AnalyserUspsAddress analyserUspsAddress)
	{
		analyserClientSite.setAddress1(analyserUspsAddress.getAddress1());
		analyserClientSite.setAddress2(analyserUspsAddress.getAddress2());
		analyserClientSite.setCity(analyserUspsAddress.getCity());
		analyserClientSite.setState(analyserUspsAddress.getState());
		analyserClientSite.setZipCode(analyserUspsAddress.getZipCode());
		analyserClientSite.setLatitude(analyserUspsAddress.getLatitude());
		analyserClientSite.setLongitude(analyserUspsAddress.getLongitude());
		logger.debug("Is address validated"+analyserUspsAddress.getValidated());
		analyserClientSite.setIsAddressUSPSValidated(analyserUspsAddress.getValidated().toString());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('validateZipDlg').hide();");
	}
	
	/**
	 * @return the uSAStates
	 */
	public List<SelectItem> getuSAStates()
	{
		if (uSAStates == null)
		{
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
			
			uSAStates.add(new SelectItem("AB", "Alberta"));
			uSAStates.add(new SelectItem("BC", "British Columbia"));
			uSAStates.add(new SelectItem("MB", "Manitoba"));
			uSAStates.add(new SelectItem("NB", "New Brunswick"));
			uSAStates.add(new SelectItem("NL", "Newfoundland and Labrador"));
			uSAStates.add(new SelectItem("NT", "Northwest Territories"));
			uSAStates.add(new SelectItem("NS", "Nova Scotia"));
			uSAStates.add(new SelectItem("NU", "Nunavut"));
			uSAStates.add(new SelectItem("ON", "Ontario"));
			uSAStates.add(new SelectItem("PE", "Prince Edward Island"));
			uSAStates.add(new SelectItem("QC", "Quebec"));
			uSAStates.add(new SelectItem("SK", "Saskatchewan"));
			uSAStates.add(new SelectItem("YT", "Yukon"));
			
			uSAStates.add(new SelectItem("PR", "Puerto Rico"));
		}
		return uSAStates;
	}
	
	/**
	 * @param uSAStates
	 *            the uSAStates to set
	 */
	public void setuSAStates(List<SelectItem> uSAStates)
	{
		this.uSAStates = uSAStates;
	}
	
	/**
	 * @return the clientCompany
	 */
	public List<SelectItem> getClientCompany()
	{
		if (clientCompany == null)
		{
			clientCompany = new ArrayList<SelectItem>();
		}
		return clientCompany;
	}
	
	/**
	 * @param clientCompany
	 *            the clientCompany to set
	 */
	public void setClientCompany(List<SelectItem> clientCompany)
	{
		this.clientCompany = clientCompany;
	}
	
	/**
	 * @return the countryNames
	 */
	public List<SelectItem> getCountryNames()
	{
		if (countryNames == null)
		{
			countryNames = new ArrayList<SelectItem>();
			countryNames.add(new SelectItem("USA", "USA"));
			countryNames.add(new SelectItem("Canada", "Canada"));
			//countryNames.add(new SelectItem("Brazil", "Brazil"));
			//countryNames.add(new SelectItem("Hungary", "Hungary"));
			//countryNames.add(new SelectItem("Singapore", "Singapore"));
		}
		return countryNames;
	}
	
	/**
	 * @param countryNames
	 *            the countryNames to set
	 */
	public void setCountryNames(List<SelectItem> countryNames)
	{
		this.countryNames = countryNames;
	}
	
	/**
	 * @return the analyserClientSite
	 */
	public AnalyserClientSite getAnalyserClientSite()
	{
		return analyserClientSite;
	}
	
	/**
	 * @param analyserClientSite
	 *            the analyserClientSite to set
	 */
	public void setAnalyserClientSite(AnalyserClientSite analyserClientSite)
	{
		this.analyserClientSite = analyserClientSite;
	}
	
	/**
	 * @return the clientNamesMap
	 */
	public Map<Integer, String> getClientNamesMap()
	{
		return clientNamesMap;
	}
	
	/**
	 * @param clientNamesMap
	 *            the clientNamesMap to set
	 */
	public void setClientNamesMap(Map<Integer, String> clientNamesMap)
	{
		this.clientNamesMap = clientNamesMap;
	}
	
	/**
	 * @return the clientId
	 */
	public String getClientId()
	{
		return clientId;
	}
	
	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	
	/**
	 * @return the analyserSiteId
	 */
	public String getAnalyserSiteId()
	{
		return analyserSiteId;
	}
	
	/**
	 * @param analyserSiteId
	 *            the analyserSiteId to set
	 */
	public void setAnalyserSiteId(String analyserSiteId)
	{
		this.analyserSiteId = analyserSiteId;
	}
	
	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}
	
	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	
	/**
	 * @return the showReadOnly
	 */
	public boolean isShowReadOnly()
	{
		return showReadOnly;
	}
	
	/**
	 * @param showReadOnly
	 *            the showReadOnly to set
	 */
	public void setShowReadOnly(boolean showReadOnly)
	{
		this.showReadOnly = showReadOnly;
	}
	
	/**
	 * @return the showUpdateButton
	 */
	public boolean isShowUpdateButton()
	{
		return showUpdateButton;
	}
	
	/**
	 * @param showUpdateButton
	 *            the showUpdateButton to set
	 */
	public void setShowUpdateButton(boolean showUpdateButton)
	{
		this.showUpdateButton = showUpdateButton;
	}

	public List<AnalyserUspsAddress> getOldAddressList()
	{
		return oldAddressList;
	}

	public void setOldAddressList(List<AnalyserUspsAddress> oldAddressList)
	{
		this.oldAddressList = oldAddressList;
	}

	public List<AnalyserUspsAddress> getNewAddressList()
	{
		return newAddressList;
	}

	public void setNewAddressList(List<AnalyserUspsAddress> newAddressList)
	{
		this.newAddressList = newAddressList;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the validated
	 */
	public Integer getValidated()
	{
		return validated;
	}

	/**
	 * @param validated the validated to set
	 */
	public void setValidated(Integer validated)
	{
		this.validated = validated;
	}
	
}
