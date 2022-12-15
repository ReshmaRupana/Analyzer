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

import org.chartistjsf.model.chart.PieChartModel;
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
@ManagedBean(name = "pieChartBean")
public class PieChartBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AnalyserChartService service;

	private String commissionPersonEmplIdOrOprId, fiscalYear;

	private PieChartModel pieChartModel;

	public PieChartBean() {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);

			HttpSession session = FacesUtils.getRequestObject().getSession(
					false);// getCurrentRequestFromFacesContext().getSession(false);
			commissionPersonEmplIdOrOprId = (String) session
					.getAttribute("personId");
			fiscalYear = (String) session.getAttribute("reportingYear");

			getTotalCommissionInYearOnCommissionType();
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}

	}

	public void getTotalCommissionInYearOnCommissionType() {
		pieChartModel = new PieChartModel();
		pieChartModel.addLabel("Staffing");
		pieChartModel.addLabel("Services");

		List<ChartDataMapping> list = service
				.getTotalCommissionInYearOnCommissionType(
						commissionPersonEmplIdOrOprId, fiscalYear);
		System.out.println("List size is : " + list.size());

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPayableCommissionType().equals("S")) {
				pieChartModel.set(list.get(i).getTotal());
			} else {
				pieChartModel.set(list.get(i).getTotal());
			}
		}
		pieChartModel.setShowTooltip(true);
	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}

	/**
	 * @return the commissionPersonEmplIdOrOprId
	 */
	public String getCommissionPersonEmplIdOrOprId() {
		return commissionPersonEmplIdOrOprId;
	}

	/**
	 * @param commissionPersonEmplIdOrOprId
	 *            the commissionPersonEmplIdOrOprId to set
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
	 * @param fiscalYear
	 *            the fiscalYear to set
	 */
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
}