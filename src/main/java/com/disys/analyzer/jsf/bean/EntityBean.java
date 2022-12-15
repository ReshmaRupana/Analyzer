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
import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.service.EntityService;

@ManagedBean
@ViewScoped
public class EntityBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(EntityBean.class);
	
	private List<SelectItem> entityList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private EntityService service;
	
	public EntityBean() 
	{
		System.out.println("EntityBean constructor called....");
		logger.info("EntityBean constructor called....");
	}
	
	public List<SelectItem> getEntityByCompanyCodeList(String compCode) 
	{
		if (entityList == null) 
		{
			entityList = new ArrayList<SelectItem>();
			try
			{
				List<EntityDTO> entityLists = service.getEntityList(userId, compCode);
				if (entityLists != null)
				{
					for (EntityDTO c:entityLists)
					{						
						entityList.add(new SelectItem(c.getEntityCode(), c.getEntityDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EntityBean --> getEntityByCompanyCodeList.");
				logger.debug("Exception in EntityBean --> getEntityByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return entityList;
	}
	
	public String getEntityDescription(String compCode, String recordCode) 
	{
			List<EntityDTO> entityDescriptionList = new ArrayList<EntityDTO>();
			try
			{
				entityDescriptionList = service.getEntityDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in EntityBean --> getEntityDescription.");
				logger.debug("Exception in EntityBean --> getEntityDescription.");
				e.printStackTrace();
			}
			EntityDTO entityDTO = entityDescriptionList.isEmpty()?null: entityDescriptionList.get(0);
			if(entityDTO != null) {
				return entityDTO.getEntityDescription().isEmpty()? entityDTO.getEntityCode() : entityDescriptionList.get(0).getEntityDescription();
			}
			return "";
	}

	/**
	 * @return the entityList
	 */

	public List<SelectItem> getEntityList() {
		return entityList;
	}

	/**
	 * @param entityList the entityList to set
	 */

	public void setEntityList(List<SelectItem> entityList) {
		this.entityList = entityList;
	}

	
}
