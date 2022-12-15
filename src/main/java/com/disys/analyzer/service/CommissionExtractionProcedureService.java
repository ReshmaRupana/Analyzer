/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;
import java.util.Map;

import com.disys.analyzer.model.CommissionExtractionProcedure;

/**
 * @author Sajid
 *
 */
public interface CommissionExtractionProcedureService {
	public List<Map<String, Object>> findAllProcedures() throws Exception;

	public void executeSingleProcedure(String procedureName) throws Exception;
	
	public List<CommissionExtractionProcedure> findAll();
}
