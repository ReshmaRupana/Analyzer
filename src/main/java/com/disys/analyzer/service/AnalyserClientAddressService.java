/**
 * 
 */
package com.disys.analyzer.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.AnalyserClientAddress;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientAddressService {
	public Page<AnalyserClientAddress> findAll(
			Example<AnalyserClientAddress> example, Pageable pageable);
	
	public AnalyserClientAddress getAnalyserClientAddressInfo(String analyserAddressId);
	
	public String saveAnalyserClientCompanyAddress(Integer actionType,AnalyserClientAddress clientAddress);
}
