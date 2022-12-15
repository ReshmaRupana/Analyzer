package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;
import com.disys.analyzer.repository.BullhornBatchInfoRepository;
import com.disys.analyzer.service.BullhornBatchInfoService;
import com.disys.analyzer.thread.BullhornBatchExecutionThread;
/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class BullhornBatchInfoServiceImpl implements BullhornBatchInfoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BullhornBatchInfoRepository bullhornBatchInfoRepository;
	
	@Autowired
    private TaskExecutor taskExecutor;
	
    @Autowired
    private ApplicationContext applicationContext;
	
	@Override
	public List<BullhornBatchInfoDTO> getBullhornBatchInfoList(String userId, String processCode, String batchCode, String searchString) {
		return bullhornBatchInfoRepository.getBullhornBatchInfoList(userId, processCode, batchCode, searchString);
	}

	@Override
	public BullhornBatchInfoDTO getBullhornBatchInfoById(String userId, Integer batchInfoId) {
		return bullhornBatchInfoRepository.getBullhornBatchInfoById(userId, batchInfoId);
	}

	@Override
	public void executeProcess(String userId, String bullhornBatchInfoId) {
		
		BullhornBatchExecutionThread batchThread = applicationContext.getBean(BullhornBatchExecutionThread.class);
		batchThread.setBullhornBatchInfoId(bullhornBatchInfoId);
		batchThread.setUserId(userId);
        taskExecutor.execute(batchThread);
	}
}
