/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.service.CommissionProcessService;

/**
 * @author Sajid
 * @since May 26, 2018
 *
 */
@ManagedBean
@ViewScoped
public class CommissionProcessBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562465364193731981L;
	private static Logger logger = LoggerFactory
			.getLogger(CommissionProcessBean.class);

	@Autowired
	private CommissionProcessService service;

	private CommissionProcess commissionProcess;
	private List<CommissionProcess> list;
	private List<CommissionProcess> filteredList;

	private List<SelectItem> accountingPeriods;
	private List<SelectItem> fiscalYears;

	private String searchString;

	private List<String> monthsName;

	@PostConstruct
	public void init() {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public CommissionProcessBean() {
		String userId = FacesUtils.getCurrentUserId();
		System.out.println("User id : " + userId);
		List<CommissionProcess> newList = new ArrayList<CommissionProcess>();
		List<Map<String, Object>> hashList = service.findAccountingPeriod();
		CommissionProcess process = new CommissionProcess();
		for (int i = 0; i < hashList.size(); i++) {
			Map<String, Object> obj = hashList.get(i);

			Iterator<Map.Entry<String, Object>> iterator = obj.entrySet()
					.iterator();
			process = new CommissionProcess();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				if (entry.getKey().equals("ACCOUNTINGPERIOD")) {
					process.setAccountingPeriod((Integer) entry.getValue());
				}
				if (entry.getKey().equals("FISCALYEAR")) {
					process.setFiscalYear((Integer) entry.getValue());
				}
			}
			newList.add(i, process);
		}

		if (newList.size() > 0) {
			accountingPeriods = new ArrayList<SelectItem>();
			fiscalYears = new ArrayList<SelectItem>();

			Integer monthVal = newList.get(0).getAccountingPeriod();
			Integer yearVal = newList.get(0).getFiscalYear();
			if (monthVal == 12) {
				accountingPeriods.add(new SelectItem("1", getMonthName(0)));
				yearVal = yearVal + 1;
				fiscalYears.add(new SelectItem(yearVal.toString(), yearVal
						.toString()));
			} else {
				monthVal = monthVal + 1;
				accountingPeriods.add(new SelectItem(monthVal.toString(),
						getMonthName(monthVal - 1)));
				fiscalYears.add(new SelectItem(yearVal.toString(), yearVal
						.toString()));
			}
		} else {
			accountingPeriods = new ArrayList<SelectItem>();
			accountingPeriods.add(new SelectItem("1", "Jan"));

			fiscalYears = new ArrayList<SelectItem>();
			fiscalYears.add(new SelectItem("2018", "2018"));
		}

		commissionProcess = new CommissionProcess();
	}

	public void refreshData() {
		list = new ArrayList<CommissionProcess>();
		List<Map<String, Object>> hashList = service
				.findAllOrderByAccountingPeriod();
		System.out.println("List size is : " + hashList.size());
		CommissionProcess process = new CommissionProcess();
		for (int i = 0; i < hashList.size(); i++) {
			Map<String, Object> obj = hashList.get(i);

			Iterator<Map.Entry<String, Object>> iterator = obj.entrySet()
					.iterator();
			process = new CommissionProcess();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();

				if (entry.getKey().equals("ACCOUNTINGPERIOD")) {
					process.setAccountingPeriod((Integer) entry.getValue());
				}
				if (entry.getKey().equals("COMMISSIONPROCESSID")) {
					process.setCommissionProcessId((Integer) entry.getValue());
				}
				if (entry.getKey().equals("FISCALYEAR")) {
					process.setFiscalYear((Integer) entry.getValue());
				}
				if (entry.getKey().equals("COMMISSIONMODE")) {
					process.setCommissionMode((String) entry.getValue());
				}
				if (entry.getKey().equals("EXECUTEDBY")) {
					process.setExecutedBy((String) entry.getValue());
				}
				if (entry.getKey().equals("EXECUTIONDATE")) {
					process.setExecutionDate((Timestamp) entry.getValue());
				}
				if (entry.getKey().equals("COMPLETIONDATE")) {
					process.setCompletionDate((Timestamp) entry.getValue());
				}
				if (entry.getKey().equals("COMPLETIONPERCENTAGE")) {
					process.setCompletionPercentage((Double) entry.getValue());
				}
				// System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			list.add(i, process);
		}
	}

	public void execute() {

		CommissionProcess process = service
				.findByAccountingPeriodAndFiscalYear(
						commissionProcess.getAccountingPeriod(),
						commissionProcess.getFiscalYear());
		if (process == null) {
			String userId = FacesUtils.getCurrentUserId();

			System.out.println("Accounting period is : "
					+ commissionProcess.getAccountingPeriod());
			System.out.println("Fiscal Year : "
					+ commissionProcess.getFiscalYear());
			System.out.println("Commisson Mode : "
					+ commissionProcess.getCommissionMode());
			System.out.println("User id : " + userId);

			commissionProcess.setExecutedBy(userId);

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			commissionProcess.setExecutionDate(timeStamp);
			commissionProcess.setCompletionPercentage(0.0);

			// TODO dont call saveCommissionProcess, rather call
			// executeCommissionProcess that will save it in the database.
			// Integer id = service.saveCommissionProcess(commissionProcess);
			/*String response = service
					.executeCommissionProcess(commissionProcess);*/
			String response = "Commission Process started successfully!";
			
			service.executeAsynchronously(commissionProcess);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Success", response);
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
			/*if(id > 0){
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Success",
						"Process saved successfully!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;	
			}else{
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Error",
						"Process couldn't be started!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}*/
		} else {
			if (process.getCommissionMode().equals("DRAFT")
					&& process.getCompletionDate() == null) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Error",
						"Another process for this period is already in progress. Kindly wait for it to complete!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			} else if (process.getCommissionMode().equals("DRAFT")
					&& process.getCompletionDate() != null) {
				String userId = FacesUtils.getCurrentUserId();
				Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

				process.setCompletionDate(null);
				process.setExecutedBy(userId);
				process.setExecutionDate(timeStamp);
				process.setCompletionPercentage(0.0);

				String response = "Commission Process started successfully!";
				
				service.executeAsynchronously(process);
				
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, "Success", response);
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
				/*if(id > 0){
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Success",
							"Commission Process updated successfully!");
					FacesUtils.getFacesContext().addMessage(null, message);
					return;	
				}else{
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Error",
							"Commission Process couldn't be started!");
					FacesUtils.getFacesContext().addMessage(null, message);
					return;
				}*/
			} else if (process.getCommissionMode().equals("FINAL")) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Error",
						"This commission process is already marked as FINAl by someone else, kindly refresh your page.!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		}
	}

	public void finalizeProcess() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		Integer accountingPeriod = Integer.valueOf(params
				.get("accountingPeriod"));
		Integer fiscalYear = Integer.valueOf(params.get("fiscalYear"));

		String response = service.finalizeCommissionProcess(accountingPeriod,
				fiscalYear);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Response", response);
		FacesUtils.getFacesContext().addMessage(null, message);
		return;
	}

	public String getMonthName(Integer monthNumber) {
		if (monthsName == null) {
			monthsName = getMonthsName();
		}
		return monthsName.get(monthNumber);
	}

	/**
	 * @return the list
	 */
	public List<CommissionProcess> getList() {
		if (list == null) {
			list = new ArrayList<CommissionProcess>();
			List<Map<String, Object>> hashList = service
					.findAllOrderByAccountingPeriod();
			System.out.println("List size is : " + hashList.size());
			CommissionProcess process = new CommissionProcess();
			for (int i = 0; i < hashList.size(); i++) {
				Map<String, Object> obj = hashList.get(i);

				Iterator<Map.Entry<String, Object>> iterator = obj.entrySet()
						.iterator();
				process = new CommissionProcess();
				while (iterator.hasNext()) {
					Map.Entry<String, Object> entry = iterator.next();

					/*
					 * ACCOUNTINGPERIOD:3
						COMMISSIONPROCESSID:39
						FISCALYEAR:2018
						COMMISSIONMODE:FINAL
						EXECUTEDBY:amy.domagala-parks@@disys.com
						EXECUTIONDATE:2018-04-25 14:12:05.497
					 * 
					 */
					if (entry.getKey().equals("ACCOUNTINGPERIOD")) {
						process.setAccountingPeriod((Integer) entry.getValue());
					}
					if (entry.getKey().equals("COMMISSIONPROCESSID")) {
						process.setCommissionProcessId((Integer) entry
								.getValue());
					}
					if (entry.getKey().equals("FISCALYEAR")) {
						process.setFiscalYear((Integer) entry.getValue());
					}
					if (entry.getKey().equals("COMMISSIONMODE")) {
						process.setCommissionMode((String) entry.getValue());
					}
					if (entry.getKey().equals("EXECUTEDBY")) {
						process.setExecutedBy((String) entry.getValue());
					}
					if (entry.getKey().equals("EXECUTIONDATE")) {
						process.setExecutionDate((Timestamp) entry.getValue());
					}
					if (entry.getKey().equals("COMPLETIONDATE")) {
						process.setCompletionDate((Timestamp) entry.getValue());
					}
					if (entry.getKey().equals("COMPLETIONPERCENTAGE")) {
						process.setCompletionPercentage((Double) entry
								.getValue());
					}
					// System.out.println(entry.getKey() + ":" +
					// entry.getValue());
				}
				list.add(i, process);
			}
			// list.sort(arg0);
			// list = service.findAllOrderByAccountingPeriod();
		}
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<CommissionProcess> list) {
		this.list = list;
	}

	/**
	 * @return the commissionProcess
	 */
	public CommissionProcess getCommissionProcess() {
		return commissionProcess;
	}

	/**
	 * @param commissionProcess
	 *            the commissionProcess to set
	 */
	public void setCommissionProcess(CommissionProcess commissionProcess) {
		this.commissionProcess = commissionProcess;
	}

	/**
	 * @return the filteredList
	 */
	public List<CommissionProcess> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<CommissionProcess> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the accountingPeriods
	 */
	public List<SelectItem> getAccountingPeriods() {
		return accountingPeriods;
	}

	/**
	 * @param accountingPeriods
	 *            the accountingPeriods to set
	 */
	public void setAccountingPeriods(List<SelectItem> accountingPeriods) {
		this.accountingPeriods = accountingPeriods;
	}

	/**
	 * @return the fiscalYears
	 */
	public List<SelectItem> getFiscalYears() {
		return fiscalYears;
	}

	/**
	 * @param fiscalYears
	 *            the fiscalYears to set
	 */
	public void setFiscalYears(List<SelectItem> fiscalYears) {
		this.fiscalYears = fiscalYears;
	}

	/**
	 * @return the monthsName
	 */
	public List<String> getMonthsName() {
		if (monthsName == null) {
			monthsName = new ArrayList<String>(12);
			monthsName.add("Jan");
			monthsName.add("Feb");
			monthsName.add("Mar");
			monthsName.add("Apr");
			monthsName.add("May");
			monthsName.add("Jun");
			monthsName.add("Jul");
			monthsName.add("Aug");
			monthsName.add("Sep");
			monthsName.add("Oct");
			monthsName.add("Nov");
			monthsName.add("Dec");
		}
		return monthsName;
	}

	/**
	 * @param monthsName
	 *            the monthsName to set
	 */
	public void setMonthsName(List<String> monthsName) {
		this.monthsName = monthsName;
	}

}
