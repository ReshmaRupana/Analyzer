/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserUspsAddress;

/**
 * @author Sajid
 * @since 11th May, 2018
 *
 */
@ManagedBean
@RequestScoped
public class AnalyserUSPSAddressBean extends SpringBeanAutowiringSupport implements
Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3622789846569934153L;

	private static Logger logger = LoggerFactory.getLogger(AnalyserUSPSAddressBean.class);
	
	List<AnalyserUspsAddress> analyserUspsAddressList;
	
	List<AnalyserUspsAddress> oldAddress;
	List<AnalyserUspsAddress> newAddress;
	
	@PostConstruct
	public void init() {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
			// TODO check if both the services are autowired or not
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserUSPSAddressBean(){
		System.out.println("In the constructor of AnalyserUSPSAddressBean ");
		HttpSession session = FacesUtils.getRequestObject().getSession(false);//  getCurrentRequestFromFacesContext().getSession(false);
		analyserUspsAddressList = (List<AnalyserUspsAddress>) session.getAttribute("uspsList");
		System.out.println("analyserUspsAddressList size is : "+analyserUspsAddressList.size());
		
		if(analyserUspsAddressList != null && analyserUspsAddressList.size() > 1){
			oldAddress = new ArrayList<AnalyserUspsAddress>();
			oldAddress.add(0,analyserUspsAddressList.get(0));
			newAddress =  new ArrayList<AnalyserUspsAddress>();
			newAddress.add(0,analyserUspsAddressList.get(1));
		}
		
	}
	
	
	public void selectAnalyserUspsAddressFromDialog(AnalyserUspsAddress analyserUspsAddress) {
		System.out.println("Closed pop up.");
		RequestContext.getCurrentInstance().closeDialog(analyserUspsAddress);
	}

	public List<AnalyserUspsAddress> getAnalyserUspsAddressList() {
		return analyserUspsAddressList;
	}

	public void setAnalyserUspsAddressList(
			List<AnalyserUspsAddress> analyserUspsAddressList) {
		this.analyserUspsAddressList = analyserUspsAddressList;
	}

	public List<AnalyserUspsAddress> getOldAddress() {
		return oldAddress;
	}

	public void setOldAddress(List<AnalyserUspsAddress> oldAddress) {
		this.oldAddress = oldAddress;
	}

	public List<AnalyserUspsAddress> getNewAddress() {
		return newAddress;
	}

	public void setNewAddress(List<AnalyserUspsAddress> newAddress) {
		this.newAddress = newAddress;
	}

}
