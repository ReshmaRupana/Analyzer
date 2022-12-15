/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;
import java.util.Map;

/**
 * @author Sajid
 * 
 *
 */
public interface CommissionExtractionProcedureRepositoryCustom {
	public List<Map<String, Object>> findAllByProcedureName() throws Exception;
	
	public List<Map<String, Object>> executeProcedure(String procedureName) throws Exception;

//	public void executeProcedureName(String procedureName) throws Exception;
}
