package com.sunilos.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sunilos.proj0.dto.CollegeDTO;
import com.sunilos.proj0.dto.StudentDTO;

/**
 * Hibernate implementation of Student DAO.
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 * 
 */

@Repository(value = "studentDAO")
public class StudentDAOHibImpl implements StudentDAOInt{
	
	@Autowired
    private SessionFactory sessionFactory = null;
	
	@Autowired
	CollegeDAOInt cdao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Add a Student
     * 
     * @param dto : the dto
     * @return long : the long
     *
     */
    public long add(StudentDTO dto) {
        Session session = sessionFactory.getCurrentSession();
        CollegeDTO cdto = cdao.findByPK(dto.getCollegeId());
		dto.setCollegeName(cdto.getName());
        session.save(dto);
        return dto.getId();
    }

    /**
     * Add a Student
     * 
     * @param dto : the dto
     * @return long : the long
     *
     */    public long update(StudentDTO dto) {
        Session session = sessionFactory.getCurrentSession();
        CollegeDTO cdto = cdao.findByPK(dto.getCollegeId());
        dto.setCollegeName(cdto.getName());
        session.update(dto);
        return dto.getId();
    }

     /**
      * Add a Student
      * 
      * @param id : the id
      *
      */
    public void delete(long id) {
        StudentDTO dto = findByPK(id);
        Session session = sessionFactory.getCurrentSession();
        session.delete(dto);

    }
   
    /**
     * Finds Student by Email
     * 
     * @param email
     *            : get @parameter
     * @return dto : the dto
     *
     */
    public StudentDTO findByEmail(String email) {
        System.out.println("in findBy email");
        StudentDTO dto = null;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(StudentDTO.class);
        List list = (List) criteria.add(Restrictions.eq("email", email)).list();

        if (list.size() == 1) {
            dto = (StudentDTO) list.get(0);
        }
        System.out.println("in finby email");
        return dto;

    }
    
    /**
     * Finds Student by PK
     * 
     * @param id
     *            : get @parameter
     * @return dto : the dto
     *
     */
    public StudentDTO findByPK(long id) {

        System.out.println("in DAO findBYPK before session");
        Session session = sessionFactory.getCurrentSession();
        System.out.println("in DAO findBYPK after session");

        System.out.println("in DAO findByPK");
        StudentDTO dto = (StudentDTO) session.get(StudentDTO.class, id);
        System.out.println(dto.getFirstName());
        return dto;
    }
    
    /**
     * Searches Student with pagination
     * 
     * @return list : List of Students
     * @param dto
     *            : Search @parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     */
    public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {
        System.out.println("DAO Search pagination");
        List<StudentDTO> list = null;

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(StudentDTO.class);
        if (dto != null) {
        	if(dto.getCollegeId()!= null){
        		criteria.add(Restrictions.eq("collegeId", dto.getCollegeId()));
        	}

            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                criteria.add(Restrictions.like("firstName", dto.getFirstName()
                        + "%"));
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                criteria.add(Restrictions.like("lastName", dto.getLastName()
                        + "%"));
            }
            if (dto.getDob() != null) {
                criteria.add(Restrictions.eq("dob", dto.getDob()));
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                criteria.add(Restrictions.eq("mobileNo", dto.getMobileNo()
                        + "%"));
            }
            if (dto.getEmail() != null && dto.getEmail().length() > 0) {
                criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
            }
            if (dto.getGender() != null && dto.getGender().length() > 0) {
                criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
            }
        }
        // if page size is greater than zero the apply pagination
        if (pageSize > 0) {

            criteria.setFirstResult((pageNo - 1) * pageSize);
            criteria.setMaxResults(pageSize);
        }
        
        System.out.println("after DAO Search pagination");
     System.out.println(pageSize);
        list = criteria.list();
        System.out.println("after criteria");
        System.out.println(list);
        return list;

    }
    
    /**
     * Searches Student
     * 
     * @return list : List of Students
     * @param dto
     *            : Search @parameters
     *
     */
    public List search(StudentDTO dto) {
        System.out.println("DAO Search");
        return search(dto, 0, 0);

    }
	
}
