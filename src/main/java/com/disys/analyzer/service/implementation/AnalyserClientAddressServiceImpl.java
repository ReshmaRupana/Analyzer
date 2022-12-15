/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.AnalyserClientAddress;
import com.disys.analyzer.repository.AnalyserClientAddressRepository;
import com.disys.analyzer.service.AnalyserClientAddressService;

/**
 * @author Sajid
 * @since Oct 31, 2017
 */
@Service
@Transactional
public class AnalyserClientAddressServiceImpl implements AnalyserClientAddressService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	AnalyserClientAddressRepository repository;

	@Override
	public Page<AnalyserClientAddress> findAll(Example<AnalyserClientAddress> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	@Override
	public AnalyserClientAddress getAnalyserClientAddressInfo(String analyserAddressId) {
		return repository.getAnalyserClientAddressInfo(analyserAddressId);
	}

	@Override
	public String saveAnalyserClientCompanyAddress(Integer actionType,AnalyserClientAddress clientAddress) {
		return repository.saveAnalyserClientCompanyAddress(actionType, clientAddress);
	}
}
