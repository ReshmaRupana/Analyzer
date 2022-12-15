package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.PtoDTO;
import com.disys.analyzer.service.PtoService;

@ManagedBean
@ViewScoped
public class PtoBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(PtoBean.class);
	
	private List<SelectItem> ptoList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private PtoService service;
	
	public PtoBean() 
	{
		System.out.println("PtoBean constructor called....");
		logger.info("PtoBean constructor called....");
	}
	
	public List<SelectItem> getPtoByCompanyCodeList(String compCode) 
	{
		if (ptoList == null) 
		{
			ptoList = new ArrayList<SelectItem>();
			try
			{
				List<PtoDTO> ptoLists = service.getPtoList(userId, compCode);
				if (ptoLists != null)
				{
					for (PtoDTO c:ptoLists)
					{						
						ptoList.add(new SelectItem(c.getPtoCode(), c.getPtoDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in PtoBean --> getPtoByCompanyCodeList.");
				logger.debug("Exception in PtoBean --> getPtoByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return ptoList;
	}
	
	public String getPtoDescription(String compCode, String recordCode) 
	{
			List<PtoDTO> ptoDescriptionList = new ArrayList<PtoDTO>();
			try
			{
				ptoDescriptionList = service.getPtoDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in PtoBean --> getPtoDescription.");
				logger.debug("Exception in PtoBean --> getPtoDescription.");
				e.printStackTrace();
			}
			PtoDTO ptoDTO = ptoDescriptionList.isEmpty()?null: ptoDescriptionList.get(0);
			if(ptoDTO != null) {
				return ptoDTO.getPtoDescription().isEmpty()? ptoDTO.getPtoCode() : ptoDescriptionList.get(0).getPtoDescription();
			}
			return "";
	}

	/**
	 * @return the ptoList
	 */
	public List<SelectItem> getPtoList() {
		return ptoList;
	}
	/**
	 * @param ptoList the ptoList to set
	 */
	public void setPtoList(List<SelectItem> ptoList) {
		this.ptoList = ptoList;
	}

	
}
