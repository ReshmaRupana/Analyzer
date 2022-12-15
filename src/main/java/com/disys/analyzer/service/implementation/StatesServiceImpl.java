package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.model.dto.StatesAllDTO;
import com.disys.analyzer.repository.StatesRepository;
import com.disys.analyzer.service.StatesService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class StatesServiceImpl implements StatesService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StatesRepository statesRepository;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.StatesService#getAllStatess(java.lang.String)
	 */
	@Override
	public List<StatesAllDTO> getAllStatess(String userId) 
	{
		return statesRepository.getAllStatess(userId);
	}
	
}
