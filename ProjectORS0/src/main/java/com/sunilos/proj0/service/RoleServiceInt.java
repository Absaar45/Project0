package com.sunilos.proj0.service;

import java.util.List;

import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.exception.DuplicateRecordException;

/**
 * Role Service interface.
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 */

public interface RoleServiceInt {
	/**
	 * Adds a Role.
	 * 
	 * @param dto : the dto
	 * @return the long : the long
     * @throws DuplicateRecordException
	 *             : @throws when Role is already exists 
	 */
	public long add(RoleDTO dto) throws DuplicateRecordException ;

	/**
	 * Updates a Role.
	 * 
	 * @param dto : the dto
	 * @throws DuplicateRecordException
	 *             : @throws when Updated Role  already exists 
	 */
	public void update(RoleDTO dto) throws DuplicateRecordException;

	/**
	 * Deletes a Role
	 * 
	 * @param id : the id
	 */
	public void delete(long id);

	 /**
		 * Finds Role by roleName
		 * 
		 * @param roleName
		 *            : get @parameter
		 * @return dto : the dto
		 */
	public RoleDTO findByName(String roleName);

	/**
	 * Finds Role by ID
	 * 
	 * @param id
	 *            : get @parameter
	 * @return dto : the dto
	 */
	public RoleDTO findById(long id);

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
	public List search(RoleDTO dto, int pageNo, int pageSize);

	 /**
		 * Searches Role
		 * 
		 * @return list : List of Roles
		 * @param dto
		 *            : Search @parameters
		 */
	public List search(RoleDTO dto);
}
