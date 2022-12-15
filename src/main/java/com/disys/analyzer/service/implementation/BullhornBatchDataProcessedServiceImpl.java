package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;
import com.disys.analyzer.repository.BullhornBatchDataProcessedRepository;
import com.disys.analyzer.service.BullhornBatchDataProcessedService;
/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class BullhornBatchDataProcessedServiceImpl implements BullhornBatchDataProcessedService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BullhornBatchDataProcessedRepository bullhornBatchDataProcessedRepository;
	
	@Override
	public List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(String userId,
			Integer bullhornBatchInfoId) {
		return bullhornBatchDataProcessedRepository.getBullhornBatchDataProcessedList(userId,
				bullhornBatchInfoId);
	}
}
