package com.disys.analyzer.repository.custom.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.repository.custom.TitleRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class TitleRepositoryImpl implements TitleRepositoryCustom
{
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext EntityManager entityManager;
	
	@Override
	public boolean isTitleUnique(String title, Integer id)
	{
		StringBuilder stb = new StringBuilder(100);
		stb.append(" select model.id from Title as model ");
		stb.append(" where trim(lower(model.title)) = :title ");
		if (id != null && id > 0)
		{
			stb.append(" and model.id <> :id ");
		}
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery(stb.toString());
		query.setParameter("title", title.trim().toLowerCase());
		if (id != null && id > 0)
		{
			query.setParameter("id", id);
		}
		return query.uniqueResult() == null;
	}
	
	@Override
	public boolean isResourceNameUnique(String resourceName, Integer id)
	{
		StringBuilder stb = new StringBuilder(100);
		stb.append(" select model.id from Title as model ");
		stb.append(" where trim(lower(model.resourceName)) = :resourceName ");
		if (id != null && id > 0)
		{
			stb.append(" and model.id <> :id ");
		}
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery(stb.toString());
		query.setParameter("resourceName", resourceName.trim().toLowerCase());
		if (id != null && id > 0)
		{
			query.setParameter("id", id);
		}
		return query.uniqueResult() == null;
	}
	
}
