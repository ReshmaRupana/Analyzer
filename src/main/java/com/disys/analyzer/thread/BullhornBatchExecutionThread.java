/**
 * 
 */
package com.disys.analyzer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.disys.analyzer.repository.BullhornBatchInfoRepository;

/**
 * @author Sajid
 * 
 *
 */
@Component
@Scope("prototype")
public class BullhornBatchExecutionThread extends Thread {
	
	public Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BullhornBatchInfoRepository bullhornBatchInfoRepository;
	
	private String userId;
	private String bullhornBatchInfoId;
	
	@Override
	public void run() {
		//logger.info("Called from thread");
		System.out.println("About to start executing bullhorn batch process...");
		bullhornBatchInfoRepository.executeProcess(userId, bullhornBatchInfoId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}

	public void setBullhornBatchInfoId(String bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}

}
