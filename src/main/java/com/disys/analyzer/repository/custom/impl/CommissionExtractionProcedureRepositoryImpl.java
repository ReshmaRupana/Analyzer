/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DbWork;
import com.disys.analyzer.repository.custom.CommissionExtractionProcedureRepositoryCustom;

/**
 * @author Sajid
 *
 */
@Repository
@Transactional(readOnly = true)
public class CommissionExtractionProcedureRepositoryImpl implements
		CommissionExtractionProcedureRepositoryCustom {

	DbWork dataBaseObject = new DbWork();
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.CommissionExtractionProcedureCustom#findAllProcedures()
	 */
	@Override
	public List<Map<String, Object>> findAllByProcedureName() throws Exception {
		String queryString = "Select * from CommissionExtractionProcedure order by CommissionExtractionProcedureId";
		return dataBaseObject.getResultFromCommission(queryString);
	}
	@Override
	public List<Map<String, Object>> executeProcedure(String procedureName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.CommissionExtractionProcedureCustom#executeSingleProcedure(java.lang.String)
	 */
	/*@Override
	public void executeProcedureName(String procedureName) throws Exception {
		// TODO Auto-generated method stub

	}*/

}
