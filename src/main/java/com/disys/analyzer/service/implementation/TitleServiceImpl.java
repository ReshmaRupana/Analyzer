/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.Title;
import com.disys.analyzer.repository.TitleRepository;
import com.disys.analyzer.service.TitleService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class TitleServiceImpl implements TitleService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TitleRepository titleRepository;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.TitleService#findByResourceName(java.lang.String)
	 */
	@Override
	public Title findByResourceName(String resourceName) {
		return titleRepository.findByResourceName(resourceName);
	}

	@Override
	public void saveTitle(Title title) {
		titleRepository.save(title);
	}

	@Override
	public Title updateTitle(Title title) {
		Title t = titleRepository.save(title);
		return t;
	}

	@Override
	public List<Title> getAllTitles() {
		return titleRepository.findAll();
	}

	@Override
	public Title findByTitle(String title) {
		return titleRepository.findByTitle(title);
	}

	@Override
	public boolean isTitleUnique(String title, Integer id)
	{
		return titleRepository.isTitleUnique(title, id);
	}

	@Override
	public boolean isResourceNameUnique(String resourceName, Integer id)
	{
		return titleRepository.isResourceNameUnique(resourceName, id);
	}
}
