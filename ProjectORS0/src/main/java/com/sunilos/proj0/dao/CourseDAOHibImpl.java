package com.sunilos.proj0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.CourseDTO;

/**
 * Hibernate implementation of Course DAO.
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 * 
 */
@Repository("courseDAO")
public class CourseDAOHibImpl implements CourseDAOInt{
	private static Logger log = Logger.getLogger(CourseDAOHibImpl.class);

    @Autowired
    private SessionFactory sessionFactory = null;

    /**
     * Adds a Course
     * 
     * @param dto : the dto
     *@return long : the long
     */
    public long add(CourseDTO dto) {

        log.debug("DAO add Started");
        long pk = 0;
        pk = (Long) sessionFactory.getCurrentSession().save(dto);
        return pk;
    }

    /**
     * Updates a Course
     * 
     * @param dto : the dto
     *
     */

    public void update(CourseDTO dto) {
        log.debug("DAO update Started");
        sessionFactory.getCurrentSession().update(dto);
    }

    /**
     * Deletes a Course
     * 
     * @param id : the id
     *
     */

    public void delete(long id) {
        log.debug("DAO delete Started");
        CourseDTO dto = findByPK(id);
        sessionFactory.getCurrentSession().delete(dto);
    }

    /**
     * Finds Course by PK
     * 
     * @param pk
     *            : get @parameter
     * @return dto : the dto
     *
     */

    public CourseDTO findByPK(long pk) {

        Session session = sessionFactory.getCurrentSession();
        CourseDTO dto = (CourseDTO) session.get(CourseDTO.class, pk);
       
        return dto;
    }

    /**
     * Finds Course by Name
     * 
     * @param name
     *            : get @parameter
     * @return dto  : the dto
     *
     */

    public CourseDTO findByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(CourseDTO.class).add(
                Restrictions.eq("name", name));
        List list = criteria.list();

        CourseDTO dto = null;
        if (list.size() == 1) {
            dto = (CourseDTO) list.get(0);
            session.evict(dto);
        }

        return dto;
    }

    /**
     * Searches Courses
     * 
     * @return list : List of Courses
     * @param dto
     *            : Search @parameters
     *
     */

    public List search(CourseDTO dto) {
        return search(dto, 0, 0);
    }

    /**
     * Searches Courses with pagination
     * 
     * @return list : List of Courses
     * @param dto
     *            : Search @parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     */

    public List search(CourseDTO dto, int pageNo, int pageSize) {

        log.debug("DAO search Started");

        Criteria c = sessionFactory.getCurrentSession().createCriteria(
                CourseDTO.class);

        if (dto != null) {
            if (dto.getId() > 0) {
                c.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                c.add(Restrictions.like("name", dto.getName() + "%"));
            }
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            c.setFirstResult((pageNo - 1) * pageSize);
            c.setMaxResults(pageSize);
        }
        return c.list();
    }
}
