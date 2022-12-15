package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;
import com.disys.analyzer.repository.BullhornBatchProcessRepository;
import com.disys.analyzer.service.BullhornBatchProcessService;
/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class BullhornBatchProcessServiceImpl implements BullhornBatchProcessService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BullhornBatchProcessRepository bullhornBatchProcessRepository;
	
	@Override
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList(String userId, String processCode, String batchCode, String searchString) {
		// TODO Auto-generated method stub
		return bullhornBatchProcessRepository.getBullhornBatchProcessList(userId, processCode, batchCode, searchString);
	}
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessDescription(String userId, String recordCode){
		// TODO Auto-generated method stub
		return bullhornBatchProcessRepository.getBullhornBatchProcessDescription(userId, recordCode);
	}
}
