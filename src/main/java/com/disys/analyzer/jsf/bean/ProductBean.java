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
import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.service.ProductService;

@ManagedBean
//@SessionScoped	//so that it will initialize at the session level and don't call this for each page
@ViewScoped
public class ProductBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 8485963768383048533L;

	private static final Logger logger = LoggerFactory.getLogger(ProductBean.class);
	
	private List<SelectItem> list;
	private List<SelectItem> uSAStates;
	
	private List<SelectItem> productList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired
	ProductService productService;
	
	public ProductBean() 
	{
		System.out.println("ProductBean constructor called....");
		logger.info("ProductBean constructor called....");
	}

	/**
	 * @return the list
	 */
	public List<SelectItem> getList() 
	{
		if (list == null) 
		{
			list = new ArrayList<SelectItem>();
			try
			{
				List<ProductDTO> products = productService.getProductList(FacesUtils.getCurrentUserId());
				if (products != null)
				{
					for (ProductDTO p:products)
					{
						//list.add(new SelectItem(p.getProductDescription(), p.getProductDescription()));
						list.add(new SelectItem(p.getProductDescription(), p.getProductLabel()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in ProductBean --> getList.");
				logger.debug("Exception in ProductBean --> getList.");
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<SelectItem> list) {
		this.list = list;
	}

	/**
	 * @return the uSAStates
	 */
	public List<SelectItem> getuSAStates()
	{
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
	public void setuSAStates(List<SelectItem> uSAStates)
	{
		this.uSAStates = uSAStates;
	}

	public List<SelectItem> getProductByCompanyCodeList(String compCode) 
	{
		if (productList == null) 
		{
			productList = new ArrayList<SelectItem>();
			try
			{
				List<ProductDTO> productLists = productService.getProductList(userId, compCode);
				if (productLists != null)
				{
					for (ProductDTO p:productLists)
					{						
						productList.add(new SelectItem(p.getProductDescription(), p.getProductLabel()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in ProductBean --> getProductByCompanyCodeList.");
				logger.debug("Exception in ProductBean --> getProductByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return productList;
	}
	
	public String getProductDescription(String compCode, String recordCode) 
	{
			List<ProductDTO> productDescriptionList = new ArrayList<ProductDTO>();
			try
			{
				productDescriptionList = productService.getProductDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in ProductBean --> getProductDescription.");
				logger.debug("Exception in ProductBean --> getProductDescription.");
				e.printStackTrace();
			}
			ProductDTO productDTO = productDescriptionList.isEmpty()?null: productDescriptionList.get(0);
			if(productDTO != null) {
				return productDTO.getProductLabel().isEmpty()? productDTO.getProductDescription() : productDescriptionList.get(0).getProductLabel();
			}
			return "";
	}
		
	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}
	
	
}
