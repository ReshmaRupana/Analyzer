/**
 * 
 */
package com.disys.analyzer.charts;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.service.AnalyserChartService;

/**
 * @author Sajid
 * @since 10th May, 2018
 *
 */
@RequestScoped
@ManagedBean(name="lineChartBean")
public class LineChartBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel lineChartModel;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalyserChartService service;
	
	private String commissionPersonEmplIdOrOprId, fiscalYear;
    
	public LineChartBean () {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
			
			
			HttpSession session = FacesUtils.getRequestObject().getSession(false);//  getCurrentRequestFromFacesContext().getSession(false);
			commissionPersonEmplIdOrOprId = (String) session.getAttribute("personId");
			fiscalYear = (String) session.getAttribute("reportingYear");
			
			getCommissionInEachMonthOnType();
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
		
    }
    
	public void getCommissionInEachMonthOnType() {
        lineChartModel = new LineChartModel();
        
        lineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
        
        
        lineChartModel.addLabel("January");
		lineChartModel.addLabel("February");
		lineChartModel.addLabel("March");
		lineChartModel.addLabel("April");
		lineChartModel.addLabel("May");
		lineChartModel.addLabel("June");
		lineChartModel.addLabel("July");
		lineChartModel.addLabel("August");
		lineChartModel.addLabel("September");
		lineChartModel.addLabel("October");
		lineChartModel.addLabel("November");
		lineChartModel.addLabel("December");
		
        
        LineChartSeries lineChartSeries1 = new LineChartSeries();
        lineChartSeries1.setName("Staffing");
        
        LineChartSeries lineChartSeries2 = new LineChartSeries();
        lineChartSeries2.setName("Services");
        
        
        List<ChartDataMapping> list = service.getCommissionInEachMonthOnType(commissionPersonEmplIdOrOprId, fiscalYear);
		System.out.println("List size is : "+list.size());
		
		for(int i =0; i<list.size();i++){
			if(list.get(i).getPayableCommissionType().equals("S")){
				lineChartSeries1.set(list.get(i).getTotal());	
			}else {
				lineChartSeries2.set(list.get(i).getTotal());
			}
		}
        
        //Axis xAxis = lineChartModel.getAxis(AxisType.X);
        
        lineChartModel.addSeries(lineChartSeries1);
        lineChartModel.addSeries(lineChartSeries2);
        lineChartModel.setAnimateAdvanced(true);
        lineChartModel.setShowTooltip(true);
        lineChartModel.setShowArea(true);
    }
    
    /*public void itemSelect(ItemSelectEvent event) {
       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "+ 
((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
                + ", Series name:" +
 ((ChartSeries) lineChartModel.getSeries().get(event.getSeriesIndex())).getName());
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
    }*/
    
	public LineChartModel getLineChartModel() {
		return lineChartModel;
	}
	public void setLineChartModel(LineChartModel lineChartModel) {
		this.lineChartModel = lineChartModel;
	}

	/**
	 * @return the commissionPersonEmplIdOrOprId
	 */
	public String getCommissionPersonEmplIdOrOprId() {
		return commissionPersonEmplIdOrOprId;
	}

	/**
	 * @param commissionPersonEmplIdOrOprId the commissionPersonEmplIdOrOprId to set
	 */
	public void setCommissionPersonEmplIdOrOprId(
			String commissionPersonEmplIdOrOprId) {
		this.commissionPersonEmplIdOrOprId = commissionPersonEmplIdOrOprId;
	}

	/**
	 * @return the fiscalYear
	 */
	public String getFiscalYear() {
		return fiscalYear;
	}

	/**
	 * @param fiscalYear the fiscalYear to set
	 */
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
}
