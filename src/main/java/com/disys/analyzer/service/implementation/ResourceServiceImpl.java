/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.Resource;
import com.disys.analyzer.repository.ResourceRepository;
import com.disys.analyzer.service.ResourceService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService, Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	@Autowired ResourceRepository resourceRepository;
	
	@Override
	public void save(Iterable<Resource> resources)
	{
		logger.debug("Saving Resources ");
		resourceRepository.save(resources);
	}
	
	@Override
	public Resource save(Resource resources)
	{
		logger.debug("Saving Resources ");
		return resourceRepository.save(resources);
		
	}
	
	@Override
	public Long count(Example<Resource> example)
	{
		long count = resourceRepository.count();
		logger.debug("Total count : " + count);
		return count;
	}
	
	@Override
	public Page<Resource> findAll(Example<Resource> example, Pageable pageable)
	{
		return resourceRepository.findAll(example, pageable);
	}
	
	@Override
	public List<Resource> getRolePrivileges(String userId, Integer roleId)
	{
		return resourceRepository.getRolePrivileges(userId, roleId);
	}
	
	@Override
	public List<Resource> findAll()
	{
		try
		{
			Iterable<Resource> it = resourceRepository.findAll();
			List<Resource> list = null;
			if (it != null)
			{
				Iterator<Resource> iter = it.iterator();
				list = new ArrayList<Resource>();
				while (iter.hasNext())
				{
					Resource next = iter.next();
					list.add(next);
				}
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String setPrivilege(String userId, Integer roleId, Integer resId, int act)
	{
		return resourceRepository.setPrivilege(userId, roleId, resId, act);
	}
	
	@Override
	public Integer getMaxPK()
	{
		return resourceRepository.getMaxPK();
	}
	
	@Override
	public void delete(Resource resources)
	{
		logger.debug("deleting Resources ");
		resourceRepository.delete(resources);
		
	}
	
	@Override
	public boolean isRecordUnique(String resDesc, Integer resId)
	{
		logger.debug("uniqueness check Resources ");
		return resourceRepository.isRecordUnique(resDesc, resId);
	}
	
	@Override
	public boolean isReferenceFound(Integer resId)
	{
		logger.debug("Reference check Resources ");
		return resourceRepository.isReferenceFound(resId);
	}
	
	@Override
	public List<Resource> getParentResources()
	{
		return resourceRepository.getParentResources();
	}
	
	@Override
	public String spAddUpdateResources(String userId, Resource resource, Integer actionType)
	{
		return resourceRepository.spAddUpdateResources(userId, resource, actionType);
	}

	@Override
	public List<Resource> findAllWithParent(String userId)
	{
		return resourceRepository.findAllWithParent(userId);
	}

	@Override
	public List<Resource> spGetUserRolePrivileges(String userId, Integer actionType)
	{
		return resourceRepository.spGetUserRolePrivileges(userId, actionType);
	}

	@Override
	public String spAddCustomUserPrivileges(String loggedInUser, String userId, Integer resourceId, Integer parentId, Integer actionType)
	{
		return resourceRepository.spAddCustomUserPrivileges(loggedInUser, userId, resourceId, parentId, actionType);
	}

	@Override
	public List<Resource> spGetLoginUserRolePrivileges(String userId, Integer roleId)
	{
		return resourceRepository.spGetLoginUserRolePrivileges(userId, roleId);
	}
}
