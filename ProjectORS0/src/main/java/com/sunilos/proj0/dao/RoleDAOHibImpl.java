package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.RoleDTO;
/**
 * Hibernate implementation of Role DAO.
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 * 
 */

@Repository("roleDAO")
public class RoleDAOHibImpl implements RoleDAOInt {
	@Autowired
	SessionFactory sessionFactory = null;

	private static Logger log = Logger.getLogger(RoleDAOHibImpl.class);
    
	/**
     * Add a Role
     * 
     * @param dto : the dto
     * @return long : the long
     *
     */
	public long add(RoleDTO dto) {
		log.debug("Role Dao Add Started");
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("Role Dao Add End");
		return pk;
	}
    
	/**
     * Update a Role
     * 
     * @param dto : the dto
     *
     */
	public void update(RoleDTO dto) {

		log.debug("Role Dao Update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("Role Dao Update End");

	}
    
	/**
     * Delete a Role
     * 
     * @param id : the id
     *
     */
	public void delete(long id) {
		log.debug("Role Dao Delete Started");
		Session session = sessionFactory.getCurrentSession();
		session.delete(id);
		log.debug("Role Dao Delete End");

	}
    
	/**
     * Finds Role by roleName
     * 
     * @param roleName
     *            : get @parameter
     * @return dto : the dto
     *
     */
	public RoleDTO findByName(String roleName) {
		log.debug("Role DAO Find by Name Started");
		RoleDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class)
				.add(Restrictions.eq("roleName", roleName)).list();

		System.out.println("List Size in Find By Name Dao" + list.size());

		if (list.size() == 1) {
			dto = (RoleDTO) list.get(0);
			System.out.println("DTO Not Null");
		}
		log.debug("Role DAO Find by Name Ended");
		return dto;

	}
    
	/**
     * Finds Role by PK
     * 
     * @param pk
     *            : get @parameter
     * @return dto : the dto
     *
     */
	public RoleDTO findByPK(long pk) {
		log.debug("RoleDAO Find by PK Started");
		System.out.println("in find by pk dao start");
		RoleDTO dto = null;
		dto = (RoleDTO) sessionFactory.getCurrentSession().get(RoleDTO.class, pk);

		log.debug("RoleDAO Find by PK Ended");
		System.out.println("in find by pk dao end=" + dto.getId());
		return dto;
	}
    
	/**
     * Searches Role with pagination
     * 
     * @return list : List of Role
     * @param dto
     *            : Search @parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     */
	public List search(RoleDTO dto, int pageNo, int pageSize) throws DataAccessException {
		log.debug("DAO search Started");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RoleDTO.class);

		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName() + "%"));
			}
			if (dto.getRoleDescription() != null && dto.getRoleDescription().length() > 0) {
				criteria.add(Restrictions.like("roleDescription", dto.getRoleDescription() + "%"));
			}
		}

		// if page size is greater than zero the apply pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}

		List list = criteria.list();

		log.debug("DAO search End");

		return list;

	}
    
	/**
     * Searches Role
     * 
     * @return list : List of Role
     * @param dto
     *            : Search @parameters
     *
     */
	public List search(RoleDTO dto) throws DataAccessException {
		return search(dto, 0, 0);
	}

}
