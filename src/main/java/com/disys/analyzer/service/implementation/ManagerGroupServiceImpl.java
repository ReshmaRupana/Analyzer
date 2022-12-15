/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.ManagerGroup;
import com.disys.analyzer.repository.ManagerGroupRepository;
import com.disys.analyzer.service.ManagerGroupService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class ManagerGroupServiceImpl implements ManagerGroupService, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ManagerGroupRepository managerGroupRepository;
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.ManagerGroupService#findAll()
	 */
	@Override
	public List<ManagerGroup> findAll() {
		return managerGroupRepository.findAll();
	}

}
