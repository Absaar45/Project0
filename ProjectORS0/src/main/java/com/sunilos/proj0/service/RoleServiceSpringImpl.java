package com.sunilos.proj0.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.RoleDAOInt;
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Session facade of Role Service. It is transactional, apply delcarative
 * transactions with help of Spring AOP.
 * 
 * If unchecked exception is propagated from a method then transaction is rolled
 * back.
 * 
 * Default propogation value is Propagation.REQUIRED and readOnly = false
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 */
@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {

    @Autowired
    private RoleDAOInt dao;

    private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);
    
    /**
	 * Adds a Role.
	 * 
	 * @param dto : the dto
	 * @return the long : the long
     * @throws DuplicateRecordException
	 *             : @throws when Role is already exists 
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public long add(RoleDTO dto) throws DuplicateRecordException {
        log.debug("Role Service Add Started");
        RoleDTO dtoExist = dao.findByName(dto.getRoleName());
        if (dtoExist != null) {
            throw new DuplicateRecordException("Role Name already exists");
        }
        long pk = dao.add(dto);
        log.debug("Role Service Add Ended");
        return pk;
    }
    
    
    /**
	 * Updates a Role.
	 * 
	 * @param dto : the dto
	 * @throws DuplicateRecordException
	 *             : @throws when Updated Role  already exists 
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(RoleDTO dto) throws DuplicateRecordException {

        log.debug("Role Service update Started");
        // RoleDTO dtoExist = dao.findByName(dto.getRoleName());
        dao.update(dto);
        log.debug("Role Service update End");
    }
    
    
    /**
	 * Deletes a Role
	 * 
	 * @param id : the id
	 */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(long id) {

        log.debug("Role Service delete Start");
        System.out.println("Delete service Started");
		/* RoleDTO dto = new RoleDTO(); */
        //RoleDTO dtoExist = findById(id);

       // System.out.println(dtoExist);

        dao.delete(id);
        log.debug("Role Service delete End");
    }
    
    
    /**
	 * Finds Role by roleName
	 * 
	 * @param roleName
	 *            : get @parameter
	 * @return dto : the dto
	 */
    @Transactional(readOnly = true)
    public RoleDTO findByName(String roleName) {
        log.debug("Role Service findByName Started");
        RoleDTO dto = dao.findByName(roleName);
        log.debug("Role Service findByName Ended");
        return dto;
    }
    
    
    /**
	 * Finds Role by ID
	 * 
	 * @param id
	 *            : get @parameter
	 * @return dto  :the dto
	 */
    @Transactional(readOnly = true)
    public RoleDTO findById(long id) {
        log.debug("Role Service findById Started");
        System.out.println("Find By Id Started");
        RoleDTO dto = dao.findByPK(id);
        System.out.println("Find By Id End");
        log.debug("Role Service findById Ended");

        return dto;
    }
     
    
    
    /**
	 * Searches Role with pagination
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search @parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 */
    @Transactional(readOnly = true)
    public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {
        return dao.search(dto, pageNo, pageSize);
    }
    
    
    /**
	 * Searches Role
	 * 
	 * @return list : List of Roles
	 * @param dto
	 *            : Search @parameters
	 */
    @Transactional(readOnly = true)
    public List<RoleDTO> search(RoleDTO dto) {
        return dao.search(dto);
    }

}
