/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.CommissionExtractionProcedure;
import com.disys.analyzer.repository.CommissionExtractionProcedureRepository;
import com.disys.analyzer.service.CommissionExtractionProcedureService;

/**
 * @author Sajid
 *
 */
@Service
@Transactional(readOnly=true)
public class CommissionExtractionProcedureServiceImpl implements
		CommissionExtractionProcedureService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	CommissionExtractionProcedureRepository repository;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.CommissionExtractionProcedureService#findAllProcedures()
	 */
	@Override
	public List<Map<String, Object>> findAllProcedures() throws Exception {
		return repository.findAllByProcedureName();
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.CommissionExtractionProcedureService#executeSingleProcedure(java.lang.String)
	 */
	@Override
	public void executeSingleProcedure(String procedureName) throws Exception {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public List<CommissionExtractionProcedure> findAll(){
		return repository.findAll();
	}

}
