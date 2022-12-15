/**
 * 
 */
package com.disys.analyzer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.repository.CommissionProcessRepository;

/**
 * @author Sajid
 * 
 *
 */
@Component
@Scope("prototype")
public class CommissionProcessExecutionThread extends Thread {
	
	public Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CommissionProcessRepository repository;
	
	CommissionProcess commissionProcess;
	
	/*public CommissionProcessExecutionThread(CommissionProcess commissionProcess){
		this.commissionProcess = commissionProcess;
	}*/
	
	@Override
	public void run() {
		//logger.info("Called from thread");
		System.out.println("About to start executing commission process...");
		repository.executeCommissionProcess(commissionProcess);
	}

	/**
	 * @return the commissionProcess
	 */
	public CommissionProcess getCommissionProcess() {
		return commissionProcess;
	}

	/**
	 * @param commissionProcess the commissionProcess to set
	 */
	public void setCommissionProcess(CommissionProcess commissionProcess) {
		this.commissionProcess = commissionProcess;
	}
}
