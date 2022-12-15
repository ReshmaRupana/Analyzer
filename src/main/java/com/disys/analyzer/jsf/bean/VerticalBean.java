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
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.service.VerticalService;

@ManagedBean
@ViewScoped
public class VerticalBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(VerticalBean.class);
	
	private List<SelectItem> verticalList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private VerticalService service;
	
	public VerticalBean() 
	{
		System.out.println("VerticalBean constructor called....");
		logger.info("VerticalBean constructor called....");
	}
	
	public List<SelectItem> getVerticalByCompanyCodeList(String compCode) 
	{
		if (verticalList == null) 
		{
			verticalList = new ArrayList<SelectItem>();
			try
			{
				List<VerticalDTO> verticalLists = service.getVerticalList(userId, compCode);
				if (verticalLists != null)
				{
					for (VerticalDTO c:verticalLists)
					{						
						verticalList.add(new SelectItem(c.getVerticalDescription(), c.getVerticalDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in VerticalBean --> getVerticalByCompanyCodeList.");
				logger.debug("Exception in VerticalBean --> getVerticalByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return verticalList;
	}
	
	public String getVerticalDescription(String compCode, String recordCode) 
	{
			List<VerticalDTO> verticalDescriptionList = new ArrayList<VerticalDTO>();
			try
			{
				verticalDescriptionList = service.getVerticalDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in VerticalBean --> getVerticalDescription.");
				logger.debug("Exception in VerticalBean --> getVerticalDescription.");
				e.printStackTrace();
			}
			VerticalDTO verticalDTO = verticalDescriptionList.isEmpty()?null: verticalDescriptionList.get(0);
			if(verticalDTO != null) {
				return verticalDTO.getVerticalDescription().isEmpty()? verticalDTO.getVerticalDescription() : verticalDescriptionList.get(0).getVerticalDescription();
			}
			return "";
	}

	/**
	 * @return the verticalList
	 */

	public List<SelectItem> getVerticalList() {
		return verticalList;
	}
	/**
	 * @param verticalList the verticalList to set
	 */
	public void setVerticalList(List<SelectItem> verticalList) {
		this.verticalList = verticalList;
	}

	
}
