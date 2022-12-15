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
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.BarChartModel;
import org.chartistjsf.model.chart.BarChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.service.AnalyserChartService;

/**
 * @author Sajid
 *
 */
@RequestScoped
@ManagedBean(name="barChartBean")
public class BarChartDataBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalyserChartService service;
	
	private String commissionPersonEmplIdOrOprId, fiscalYear;
	
	private BarChartModel perMonthBarChart;
	private BarChartModel perMonthComparableBarChart;
	
	private String width;
	private String height;
	
	public BarChartDataBean() {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
			
			
			HttpSession session = FacesUtils.getRequestObject().getSession(false);//  getCurrentRequestFromFacesContext().getSession(false);
			commissionPersonEmplIdOrOprId = (String) session.getAttribute("personId");
			fiscalYear = (String) session.getAttribute("reportingYear");
			
			getPayableCommissionOnPerMonthBasis();
			getComparablePayableCommissionOnPerMonthBasis();
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public void getPayableCommissionOnPerMonthBasis() {
		perMonthBarChart = new BarChartModel();
		perMonthBarChart.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		perMonthBarChart.addLabel("January");
		perMonthBarChart.addLabel("February");
		perMonthBarChart.addLabel("March");
		perMonthBarChart.addLabel("April");
		perMonthBarChart.addLabel("May");
		perMonthBarChart.addLabel("June");
		perMonthBarChart.addLabel("July");
		perMonthBarChart.addLabel("August");
		perMonthBarChart.addLabel("September");
		perMonthBarChart.addLabel("October");
		perMonthBarChart.addLabel("November");
		perMonthBarChart.addLabel("December");
		
		this.width = perMonthBarChart.getWidth();
		System.out.println("Width is : "+width);
		this.height = perMonthBarChart.getHeight();
		System.out.println("Height is : "+height);
		
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("Payable Commission On Per Month Basis");
		
		
		List<ChartDataMapping> list = service.getPayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
		System.out.println("List size is : "+list.size());
		
		for(int i =0; i<list.size();i++){
			series1.set(list.get(i).getTotal());
		}
		
		
		Axis xAxis = perMonthBarChart.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		perMonthBarChart.addSeries(series1);
		perMonthBarChart.setShowTooltip(true);
		perMonthBarChart.setSeriesBarDistance(15);
		perMonthBarChart.setAnimateAdvanced(true);
	}
	
	public void getComparablePayableCommissionOnPerMonthBasis() {
		perMonthComparableBarChart = new BarChartModel();
		perMonthComparableBarChart.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		perMonthComparableBarChart.addLabel("January");
		perMonthComparableBarChart.addLabel("February");
		perMonthComparableBarChart.addLabel("March");
		perMonthComparableBarChart.addLabel("April");
		perMonthComparableBarChart.addLabel("May");
		perMonthComparableBarChart.addLabel("June");
		perMonthComparableBarChart.addLabel("July");
		perMonthComparableBarChart.addLabel("August");
		perMonthComparableBarChart.addLabel("September");
		perMonthComparableBarChart.addLabel("October");
		perMonthComparableBarChart.addLabel("November");
		perMonthComparableBarChart.addLabel("December");
		
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("Comparable Payable Commission - S");
		
		List<ChartDataMapping> list = service.getComparablePayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
		System.out.println("List size is : "+list.size());
		
		for(int i =0; i<list.size();i++){
			if(list.get(i).getPayableCommissionType().equals("S")){
				series1.set(list.get(i).getTotal());
			}	
		}
		
		BarChartSeries series2 = new BarChartSeries();
		series2.setName("Comparable Payable Commission - R");
		for(int i =0; i<list.size();i++){
			if(list.get(i).getPayableCommissionType().equals("R")){
				series2.set(list.get(i).getTotal());
			}	
		}
		
		Axis xAxis = perMonthComparableBarChart.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		perMonthComparableBarChart.addSeries(series1);
		perMonthComparableBarChart.addSeries(series2);
		perMonthComparableBarChart.setShowTooltip(true);
		perMonthComparableBarChart.setSeriesBarDistance(15);
		perMonthComparableBarChart.setAnimateAdvanced(true);
	}

	public BarChartModel getPerMonthBarChart() {
		return perMonthBarChart;
	}

	public void setPerMonthBarChart(BarChartModel perMonthBarChart) {
		this.perMonthBarChart = perMonthBarChart;
	}

	public BarChartModel getPerMonthComparableBarChart() {
		return perMonthComparableBarChart;
	}

	public void setPerMonthComparableBarChart(
			BarChartModel perMonthComparableBarChart) {
		this.perMonthComparableBarChart = perMonthComparableBarChart;
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

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	

	/*public void barItemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Item selected", "Item Value: "
						+ ((ChartSeries) barChartModel.getSeries().get(
								event.getSeriesIndex())).getData().get(
								event.getItemIndex())
						+ ", Series name:"
						+ ((ChartSeries) barChartModel.getSeries().get(
								event.getSeriesIndex())).getName());
		FacesContext.getCurrentInstance().addMessage(
				event.getComponent().getClientId(), msg);
	}*/
}