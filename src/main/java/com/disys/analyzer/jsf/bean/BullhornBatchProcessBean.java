package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.BarChartModel;
import org.chartistjsf.model.chart.BarChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.chartistjsf.model.chart.PieChartModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;
import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;
import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;
import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;
import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.service.BullhornBatchDataIssuesService;
import com.disys.analyzer.service.BullhornBatchDataProcessedService;
import com.disys.analyzer.service.BullhornBatchInfoService;
import com.disys.analyzer.service.BullhornBatchProcessService;

/**
 * @author Sajid
 * 
 */

@ManagedBean
@ViewScoped
public class BullhornBatchProcessBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;
	
	private String bullhornBatchCode;
	
	private String bullhornBatchProcessId;
	
	private String bullhornBatchInfoId;
	
	private String bullhornBatchDataProcessedId;
	
	private Boolean showProcessCharts = false;
	
	private Boolean showBatchCharts = false;
	
	private String searchString = "All";
	private String startDate;
	private String endDate;
	
//	Process
	
	private List<BullhornBatchProcessDTO> filteredList;
	
	private List<BullhornBatchProcessDTO> bullhornBatchProcessList;
	
	private BullhornBatchProcessDTO bullhornBatchProcessDTO;
	
//	Batch
	private List<BullhornBatchInfoDTO> filteredBatchInfoList;
	
	private List<BullhornBatchInfoDTO> batchInfoData;
	
	private BullhornBatchInfoDTO bullhornBatchInfoDTO;	
	
//	Records Data
	private List<BullhornBatchDataProcessedDTO> filteredBatchDataProcessedList;
	
	private List<BullhornBatchDataProcessedDTO> batchDataProcessedData;
	
	private BullhornBatchDataProcessedDTO bullhornBatchDataProcessedDTO;	
	
//	Issues and Errors
	private List<BullhornBatchDataIssuesDTO> filteredBatchDataIssuesList;
	
	private List<BullhornBatchDataIssuesDTO> batchDataIssuesData;
	
	private BullhornBatchDataIssuesDTO bullhornBatchDataIssuesDTO;
	
	
//	Charts
	private BarChartModel bhBatchBarChart;
	private BarChartModel bhProcessBarChart;
	private LineChartModel bhBatchLineChartModel;
	private PieChartModel bhIssuePieChartModel;
	
	
//	private List<Map<String, Object>> reportData;

	private Logger logger = LoggerFactory.getLogger(BullhornBatchProcessBean.class);
	
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private BullhornBatchProcessService processService;	
	@Autowired 
	private BullhornBatchInfoService infoService;
	@Autowired 
	private BullhornBatchDataProcessedService dataProcessedService;
	@Autowired 
	private BullhornBatchDataIssuesService dataIssuesService;
	
	public BullhornBatchProcessBean() 
	{
		System.out.println("BullhornBatchProcessBean constructor called....");
		logger.info("BullhornBatchProcessBean constructor called....");
	
	}

	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList(String processCode, String batchCode, String searchString) 
	{
		List<BullhornBatchProcessDTO> bullhornBatchProcesssList = new ArrayList<BullhornBatchProcessDTO>();
			try
			{

				bullhornBatchProcesssList = processService.getBullhornBatchProcessList(this.userId, processCode, batchCode, searchString);
				
			}
			catch (Exception e)
			{
				System.out.println("Exception in BullhornBatchProcessBean --> getBullhornBatchProcessLists.");
				logger.debug("Exception in BullhornBatchProcessBean --> getBullhornBatchProcessLists.");
				e.printStackTrace();
			}
			
			return bullhornBatchProcesssList;
	}
	
	
	public void generateReport()
	{
		logger.debug("About to create BullhornBatchProcess Report");
		System.out.println("About to create BullhornBatchProcess Report");

		try {
			this.getBatchInfoList(this.startDate, this.endDate, this.searchString);
			/*this.bullhornBatchProcessList = this.getBullhornBatchProcessList(
					this.bullhornBatchProcessId,
					this.bullhornBatchInfoId,
					this.bullhornBatchDataProcessedId);*/
			this.showProcessCharts = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * @return the bullhornBatchProcessList
	 */
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList() {
		return bullhornBatchProcessList;
	}
	/**
	 * @param bullhornBatchProcessList the bullhornBatchProcessList to set
	 */
	public void setBullhornBatchProcessList(List<BullhornBatchProcessDTO> bullhornBatchProcessList) {
		this.bullhornBatchProcessList = bullhornBatchProcessList;
	}

	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}

	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}
	
	public void onRowSelect(SelectEvent event)
	{
		System.out.println("onRowSelect bullhornBatchProcessId : " + bullhornBatchProcessDTO.getBullhornBatchProcessId());
		getBatchInfoList(bullhornBatchProcessDTO.getBullhornBatchProcessId());
		this.showBatchCharts = true;
		//this.showProcessCharts=true;
		
	}
	
	public void getBatchInfoList(String bullhornBatchProcessId)
	{

		logger.debug("About to create Batch Info Report");
		System.out.println("About to create Batch Info Report");

		batchInfoData = new ArrayList<BullhornBatchInfoDTO>();
		try {
			batchInfoData = infoService.getBullhornBatchInfoList(this.userId,
					opt(() ->this.bullhornBatchProcessDTO.getBullhornBatchProcessId()),
					opt(() ->this.bullhornBatchInfoDTO.getBullhornBatchInfoId().toString()),
					opt(() ->this.bullhornBatchDataProcessedDTO.getBullhornBatchDataProcessedId().toString()));
			System.out.println("Child Report List Size = " + batchInfoData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getBatchInfoList(String startDate, String endDate, String searchKey)
	{

		logger.debug("About to create Batch Info Report");
		System.out.println("About to create Batch Info Report");

		batchInfoData = new ArrayList<BullhornBatchInfoDTO>();
		try {
			batchInfoData = infoService.getBullhornBatchInfoList(this.userId,
					startDate, endDate, searchString);
			System.out.println("Child Report List Size = " + batchInfoData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onBatchInfoRowSelect(SelectEvent event)
	{
		System.out.println("onRowSelect bullhornBatchInfoId : " + bullhornBatchInfoDTO.getBullhornBatchInfoId());
		getBatchDataProcessedList(bullhornBatchInfoDTO.getBullhornBatchInfoId().toString());
		
	}
	
	public void getBatchDataProcessedList(String bullhornBatchDataProcessedId)
	{

		logger.debug("About to create Batch Data Processed Report");
		System.out.println("About to create Batch Data Processed Report");

//		batchInfoData = new ArrayList<BullhornBatchInfoDTO>();
		try {
			batchDataProcessedData = dataProcessedService.getBullhornBatchDataProcessedList(this.userId,
					opt(() ->this.bullhornBatchInfoDTO.getBullhornBatchInfoId()));
			System.out.println("batchDataProcessedData Report List Size = " + batchDataProcessedData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getBatchInfo(int bhBatchInfoId)
	{
		logger.debug("Batch Info Pie Chart");
		System.out.println("Batch Info Pie Chart");

		BullhornBatchInfoDTO bhBatchInfoDTO = new BullhornBatchInfoDTO();
		try {
			bhBatchInfoDTO = infoService.getBullhornBatchInfoById(this.userId,	bhBatchInfoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bhBatchInfoDTO.getCompletionPercentage().intValue();
	}
	
	
	public void onBatchDataProcessedRowSelect(SelectEvent event)
	{
		System.out.println("onRowSelect getBullhornBatchDataProcessedId : " + opt(() ->this.bullhornBatchDataProcessedDTO.getBullhornBatchDataProcessedId()));
		getBatchDataIssuesList(opt(() ->this.bullhornBatchDataProcessedDTO.getBullhornBatchDataProcessedId().toString()));
		
	}
	
	public void getBatchDataIssuesList(String bullhornBatchDataProcessedId)
	{
		
		logger.debug("About to create Batch Data Processed Report");
		System.out.println("About to create Batch Data Processed Report");		
		
//		batchDataProcessedData = new ArrayList<BullhornBatchDataProcessedDTO>();
		try
		{
			batchDataIssuesData = dataIssuesService.getBullhornBatchDataIssuesList(this.userId,
					opt(() ->this.bullhornBatchDataProcessedDTO.getBullhornBatchDataProcessedId()));
			System.out.println("batchDataIssuesData Report List Size = "+batchDataIssuesData.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void onRowUnselect(UnselectEvent event)
	{
		System.out.println("unselect");
	}

	public List<BullhornBatchProcessDTO> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<BullhornBatchProcessDTO> filteredList) {
		this.filteredList = filteredList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BullhornBatchProcessService getProcessService() {
		return processService;
	}

	public String getBullhornBatchProcessId() {
		return bullhornBatchProcessId;
	}

	public void setBullhornBatchProcessId(String bullhornBatchProcessId) {
		this.bullhornBatchProcessId = bullhornBatchProcessId;
	}
	
	public void eraseFilter()
	{
		this.bullhornBatchProcessList = null;
		this.batchInfoData = null;
		this.batchDataProcessedData = null;
		this.batchDataIssuesData = null;
		this.searchString = "All";
		this.showProcessCharts = false;
		this.showBatchCharts = false;
		this.bhProcessBarChart=null;

	}

	public List<BullhornBatchInfoDTO> getBatchInfoData() {
		return batchInfoData;
	}

	public void setBatchInfoData(List<BullhornBatchInfoDTO> batchInfoData) {
		this.batchInfoData = batchInfoData;
	}

	public BullhornBatchInfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(BullhornBatchInfoService infoService) {
		this.infoService = infoService;
	}

	public void setProcessService(BullhornBatchProcessService processService) {
		this.processService = processService;
	}

	public BullhornBatchProcessDTO getBullhornBatchProcessDTO() {
		return bullhornBatchProcessDTO;
	}

	public void setBullhornBatchProcessDTO(BullhornBatchProcessDTO bullhornBatchProcessDTO) {
		this.bullhornBatchProcessDTO = bullhornBatchProcessDTO;
	}

	public List<BullhornBatchInfoDTO> getFilteredBatchInfoList() {
		return filteredBatchInfoList;
	}

	public void setFilteredBatchInfoList(List<BullhornBatchInfoDTO> filteredBatchInfoList) {
		this.filteredBatchInfoList = filteredBatchInfoList;
	}

	public BullhornBatchInfoDTO getBullhornBatchInfoDTO() {
		return bullhornBatchInfoDTO;
	}

	public void setBullhornBatchInfoDTO(BullhornBatchInfoDTO bullhornBatchInfoDTO) {
		this.bullhornBatchInfoDTO = bullhornBatchInfoDTO;
	}

	public BullhornBatchDataProcessedService getDataProcessedService() {
		return dataProcessedService;
	}

	public void setDataProcessedService(BullhornBatchDataProcessedService dataProcessedService) {
		this.dataProcessedService = dataProcessedService;
	}

	public List<BullhornBatchDataProcessedDTO> getFilteredBatchDataProcessedList() {
		return filteredBatchDataProcessedList;
	}

	public void setFilteredBatchDataProcessedList(List<BullhornBatchDataProcessedDTO> filteredBatchDataProcessedList) {
		this.filteredBatchDataProcessedList = filteredBatchDataProcessedList;
	}

	public BullhornBatchDataProcessedDTO getBullhornBatchDataProcessedDTO() {
		return bullhornBatchDataProcessedDTO;
	}

	public void setBullhornBatchDataProcessedDTO(BullhornBatchDataProcessedDTO bullhornBatchDataProcessedDTO) {
		this.bullhornBatchDataProcessedDTO = bullhornBatchDataProcessedDTO;
	}

	public List<BullhornBatchDataProcessedDTO> getBatchDataProcessedData() {
		return batchDataProcessedData;
	}

	public void setBatchDataProcessedData(List<BullhornBatchDataProcessedDTO> batchDataProcessedData) {
		this.batchDataProcessedData = batchDataProcessedData;
	}

	public List<BullhornBatchDataIssuesDTO> getFilteredBatchDataIssuesList() {
		return filteredBatchDataIssuesList;
	}

	public void setFilteredBatchDataIssuesList(List<BullhornBatchDataIssuesDTO> filteredBatchDataIssuesList) {
		this.filteredBatchDataIssuesList = filteredBatchDataIssuesList;
	}

	public List<BullhornBatchDataIssuesDTO> getBatchDataIssuesData() {
		return batchDataIssuesData;
	}

	public void setBatchDataIssuesData(List<BullhornBatchDataIssuesDTO> batchDataIssuesData) {
		this.batchDataIssuesData = batchDataIssuesData;
	}

	public BullhornBatchDataIssuesDTO getBullhornBatchDataIssuesDTO() {
		return bullhornBatchDataIssuesDTO;
	}

	public void setBullhornBatchDataIssuesDTO(BullhornBatchDataIssuesDTO bullhornBatchDataIssuesDTO) {
		this.bullhornBatchDataIssuesDTO = bullhornBatchDataIssuesDTO;
	}

	public BullhornBatchDataIssuesService getDataIssuesService() {
		return dataIssuesService;
	}

	public void setDataIssuesService(BullhornBatchDataIssuesService dataIssuesService) {
		this.dataIssuesService = dataIssuesService;
	}
	
	public static <T> T opt(Supplier<T> statement) {       
	    try {
	        return statement.get();
	    } catch (NullPointerException exc) {
	        return null;
	    }   
	}

	public String getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}

	public void setBullhornBatchInfoId(String bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}

	public String getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}

	public void setBullhornBatchDataProcessedId(String bullhornBatchDataProcessedId) {
		this.bullhornBatchDataProcessedId = bullhornBatchDataProcessedId;
	}

	public Boolean getShowProcessCharts() {
		return showProcessCharts;
	}
	
	public Boolean isShowProcessCharts() {
		return showProcessCharts;
	}

	public void setShowProcessCharts(Boolean showCharts) {
		this.showProcessCharts = showCharts;
	}

	public Boolean getShowBatchCharts() {
		return showBatchCharts;
	}
	
	public Boolean isShowBatchCharts() {
		return showBatchCharts;
	}	

	public void setShowBatchCharts(Boolean showBatchCharts) {
		this.showBatchCharts = showBatchCharts;
	}
	
	public String displayBatchBarChart() {
		return this.showBatchCharts ? "display:block" : "display:none";
	}

	public BarChartModel getBhBatchBarChart() {
		this.setUpBhBatchBarchart();
		return bhBatchBarChart;
	}

	public void setBhBatchBarChart(BarChartModel bhBatchBarChart) {
		this.bhBatchBarChart = bhBatchBarChart;
	}

	public BarChartModel getBhProcessBarChart() {
		this.setUpBhProcessBarchart();
		return bhProcessBarChart;
	}

	public void setBhProcessBarChart(BarChartModel bhProcessBarChart) {
		this.bhProcessBarChart = bhProcessBarChart;
	}
	
	public void setUpBhProcessBarchart() {
		bhProcessBarChart = new BarChartModel();
		bhProcessBarChart.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		bhProcessBarChart.addLabel("Jan");
		bhProcessBarChart.addLabel("Feb");
		bhProcessBarChart.addLabel("Mar");
		bhProcessBarChart.addLabel("Apr");
		bhProcessBarChart.addLabel("May");
		bhProcessBarChart.addLabel("Jun");
		bhProcessBarChart.addLabel("Jul");
		bhProcessBarChart.addLabel("Aug");
		bhProcessBarChart.addLabel("Sep");
		bhProcessBarChart.addLabel("Oct");
		bhProcessBarChart.addLabel("Nov");
		bhProcessBarChart.addLabel("Dec");
		
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("Bullhorn Process Chart");
		
		List<ChartDataMapping> list = new ArrayList<ChartDataMapping>();
		for (int i=1; i<11; i++) {
			ChartDataMapping cdm = new ChartDataMapping();
			cdm.setTotal(i * 8.9);
			cdm.setFiscalYear(i + 2000);
			cdm.setAccountingPeriod(i);
			list.add(cdm);
		}
		
		
//		List<ChartDataMapping> list = service.getPayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
//		System.out.println("List size is : "+list.size());
		
		for(int i =0; i<list.size();i++){
			series1.set(list.get(i).getTotal());
		}
		
		
		Axis xAxis = bhProcessBarChart.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		bhProcessBarChart.addSeries(series1);
		bhProcessBarChart.setShowTooltip(true);
		bhProcessBarChart.setSeriesBarDistance(15);
		bhProcessBarChart.setAnimateAdvanced(true);
	}
	
	public void setUpBhBatchBarchart() {
		bhBatchBarChart = new BarChartModel();
		bhBatchBarChart.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		bhBatchBarChart.addLabel("Jan");
		bhBatchBarChart.addLabel("Feb");
		bhBatchBarChart.addLabel("Mar");
		bhBatchBarChart.addLabel("Apr");
		bhBatchBarChart.addLabel("May");
		bhBatchBarChart.addLabel("Jun");
		bhBatchBarChart.addLabel("Jul");
		bhBatchBarChart.addLabel("Aug");
		bhBatchBarChart.addLabel("Sep");
		bhBatchBarChart.addLabel("Oct");
		bhBatchBarChart.addLabel("Nov");
		bhBatchBarChart.addLabel("Dec");
		
		
		BarChartSeries series1 = new BarChartSeries();
		series1.setName("Bullhorn Batch Chart");
		
		List<ChartDataMapping> list = new ArrayList<ChartDataMapping>();
		for (int i=1; i<11; i++) {
			ChartDataMapping cdm = new ChartDataMapping();
			cdm.setTotal(i * 8.9);
			cdm.setFiscalYear(i + 2000);
			cdm.setAccountingPeriod(i);
			list.add(cdm);
		}
		
		
//		List<ChartDataMapping> list = service.getPayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
//		System.out.println("List size is : "+list.size());
		
		for(int i =0; i<list.size();i++){
			series1.set(list.get(i).getTotal());
		}
		
		
		Axis xAxis = bhBatchBarChart.getAxis(AxisType.X);
		xAxis.setShowGrid(false);
		bhBatchBarChart.addSeries(series1);
		bhBatchBarChart.setShowTooltip(true);
		bhBatchBarChart.setSeriesBarDistance(15);
		bhBatchBarChart.setAnimateAdvanced(true);
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public LineChartModel getBhBatchLineChartModel() {
		

        bhBatchLineChartModel = new LineChartModel();
        
        bhBatchLineChartModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		
        
        LineChartSeries lineChartSeriesAccepted = new LineChartSeries();
        lineChartSeriesAccepted.setName("Accepted");
        
        LineChartSeries lineChartSeriesRejected = new LineChartSeries();
        lineChartSeriesRejected.setName("Rejected");
        
        LineChartSeries lineChartSeriesProcessed = new LineChartSeries();
        lineChartSeriesProcessed.setName("Processed");
        
        if(batchInfoData != null)
        for (BullhornBatchInfoDTO batch : batchInfoData) {
        	bhBatchLineChartModel.addLabel(batch.getBullhornBatchCode());
        	lineChartSeriesAccepted.set(batch.getAcceptedRecords());
        	lineChartSeriesRejected.set(batch.getRejectedRecords());
        	lineChartSeriesProcessed.set(batch.getProcessedRecords());
        }
        
        bhBatchLineChartModel.addSeries(lineChartSeriesAccepted);
        bhBatchLineChartModel.addSeries(lineChartSeriesRejected);
        bhBatchLineChartModel.addSeries(lineChartSeriesProcessed);
        bhBatchLineChartModel.setAnimateAdvanced(true);
        bhBatchLineChartModel.setShowTooltip(true);
        bhBatchLineChartModel.setShowArea(true);    		
		return bhBatchLineChartModel;
	}

	public void setBhBatchLineChartModel(LineChartModel bhBatchLineChartModel) {
		this.bhBatchLineChartModel = bhBatchLineChartModel;
	}

	public PieChartModel getBhIssuePieChartModel() {

		bhIssuePieChartModel = new PieChartModel();
		

		if (bullhornBatchInfoDTO != null) {
			List<Number> values = new ArrayList<>();
			values.add(bullhornBatchInfoDTO.getAcceptedRecords());
			values.add(bullhornBatchInfoDTO.getRejectedRecords());		
			bhIssuePieChartModel.setData(values);
		}
		bhIssuePieChartModel.addLabel("Accepted");
		bhIssuePieChartModel.addLabel("Rejected");
		bhIssuePieChartModel.setShowTooltip(true);
		return bhIssuePieChartModel;
	}

	public void setBhIssuePieChartModel(PieChartModel bhIssuePieChartModel) {
		this.bhIssuePieChartModel = bhIssuePieChartModel;
	}
	
	public void executeProcess() {
		System.out.println("Starting execute ....");
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String bullhornBatchInfoId = (String) params.get("bullhornBatchInfoId");
		
		if (bullhornBatchInfoId != null)
		{
			if (bullhornBatchInfoId.trim().length() != 0)
			{
				System.out.println("Starting execution for bullhornBatchInfoId : " + bullhornBatchInfoId);
				String response = "Commission Process started successfully!";
				infoService.executeProcess(this.userId, bullhornBatchInfoId);
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Success", response);
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		}
	}
	
	
}
