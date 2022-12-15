/**
 * 
 */
package com.disys.analyzer.repository.custom;

import com.disys.analyzer.model.AnalyserClientAddress;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientAddressRepositoryCustom {
	public AnalyserClientAddress getAnalyserClientAddressInfo(String analyserAddressId);
	
	public String saveAnalyserClientCompanyAddress(Integer actionType,AnalyserClientAddress clientAddress);
}
