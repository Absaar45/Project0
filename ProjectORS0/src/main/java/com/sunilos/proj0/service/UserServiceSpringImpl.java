package com.sunilos.proj0.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sunilos.proj0.dao.UserDAOInt;
import com.sunilos.proj0.dto.RoleDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.ApplicationException;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.util.EmailBuilder;

/**
 * User Service interface.
 * 
 * @author Chain of Responsibility
 * @version 1.0
 * Copyright (c) Chain of Responsibility
 */

@Service(value = "userService")
public class UserServiceSpringImpl implements UserServiceInt {

	 @Autowired
	 private UserDAOInt dao = null;
	 
	  @Autowired
	  private RoleServiceInt roleService;
	  
	    @Autowired
	    private JavaMailSenderImpl mailSender;
	    

	    public void setMailSender(JavaMailSenderImpl mailSender) {
	        this.mailSender = mailSender;
	    }

		/*
		 * public void setDao(UserDAOInt dao) { this.dao = dao; }
		 */

	    private static Logger log = Logger.getLogger(UserServiceSpringImpl.class);
        
	    /**
		 * Adds a User
		 * 
		 * @param dto  :the dto
		 * 
		 *  
		 */
	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	    public long add(UserDTO dto) {
	        long pk = dao.add(dto);
	        return pk;
	    }
        
	    /**
	     * Registers a user
	     * 
	     * @param dto : the dto
	      * 
		 *     */
	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	    public long registerUser(UserDTO dto) {

	        long id = add(dto);

	        HashMap<String, String> map = new HashMap<String, String>();
	        map.put("login", dto.getLogin());
	        map.put("password", dto.getPassword());

	        String message = EmailBuilder.getUserRegistrationMessage(map);

	        MimeMessage msg = mailSender.createMimeMessage();

	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(msg);
	            helper.setTo(dto.getLogin());
	            helper.setSubject("Registration is successful for ORS Project SUNRAYS Technologies.");
	            // use the true flag to indicate the text included is HTML
	            helper.setText(message, true);
	            mailSender.send(msg);
	        } catch (MessagingException e) {
	            System.out.println("Mail Sending Failed");
	            e.printStackTrace();
	        }

	        return id;
	    }
        
	    /**
		 * Updates a User
		 * 
		 * @param dto  :the dto
		 * 
		 *
		 */
	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	    public void update(UserDTO dto) {
	        log.debug("Service update Started");
	        dao.update(dto);
	        log.debug("Service update End");
	    }
        
	    /**
	     * Deletes a user
	     * 
	     * @param id  :the id
	     */

	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	    public void delete(long id) {
	        log.debug("Service Delete Started");
	        dao.delete(id);
	        log.debug("Service delete End");

	    }
        
	    /**
	     * Finds user by Login
	     * 
	     * @param login
	     *            : get @parameter
	     * @return dto : the dto
	     */
	    @Transactional(readOnly = true)
	    public UserDTO findByLogin(String login) {
	        log.debug("Service findByLogin Started");
	        UserDTO dto = dao.findByLogin(login);
	        return dto;
	    }
        
	    /**
	     * Finds user by PK
	     * 
	     * @param pk
	     *            : get @parameter
	     * @return dto  :the dto
	     */
	    @Transactional(readOnly = true)
	    public UserDTO findByPK(long pk) {

	        log.debug("Service findByPK Started");
	        UserDTO dto = dao.findByPK(pk);
	        log.debug("Service findByPK End");
	        return dto;
	    }
        
	    /**
	     * Searches Users with pagination
	     * 
	     * @return list : List of Users
	     * @param dto
	     *            : Search @parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     */
	    @Transactional(readOnly = true)
	    public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
	        return dao.search(dto, pageNo, pageSize);
	    }
        
	    /**
	     * Searches Users
	     * 
	     * @return list : List of Users
	     * @param dto
	     *            : Search @parameters
	     */
	    @Transactional(readOnly = true)
	    public List<UserDTO> search(UserDTO dto) {
	        return dao.search(dto);
	    }
        
	    /**
	     * Change Password By pk
	     * 
	     * @param id
	     *            ,oldPassword,newPassword : get @parameter
	     * @return dto : the dto
	     */
	    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	    public boolean changePassword(Long id, String oldPassword,
	            String newPassword) {
	        UserDTO dto = findByPK(id);
	        if (oldPassword.equals(dto.getPassword())) {
	            dto.setPassword(newPassword);
	            dao.update(dto);
	            return true;
	        } else {
	            return false;
	        }
	    }
        
	    /**
	     * User Authentication
	     * 
	     * @return dto : Contains User's information
	     * @param dto  :the dto
	     */
	    @Transactional(readOnly = true)
	    public UserDTO authenticate(UserDTO dto) {
	        UserDTO dtoExist = dao.findByLogin(dto.getLogin());
	        if (dtoExist != null
	                && dtoExist.getPassword().equals(dto.getPassword())) {
	            // Set last login date
	            dtoExist.setLastLogin(new Timestamp(new Date().getTime()));
	            dao.update(dtoExist);
	            return dtoExist;
	        }
	        return null;
	    }
        
	    /**
	     * Lock User for certain time duration
	     * 
	     * @return boolean : true if success otherwise false
	     * @param login
	     *            : User Login
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	    public boolean lock(String login) {
	        log.debug("Service lock Started");
	        boolean flag = false;
	        UserDTO dtoExist = null;
	        dtoExist = findByLogin(login);
	        if (dtoExist != null) {
	            dtoExist.setLock(UserDTO.ACTIVE);
	            dao.update(dtoExist);
	            flag = true;
	        } else {
	        }
	        log.debug("Service lock End");
	        return flag;
	    }
         
	    /**
	     * Reset Password of User with auto generated Password
	     * 
	     * @return boolean : true if success otherwise false
	     * @param login
	     *            : User Login
	     * @throws ApplicationException : Application Exception
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	    public boolean resetPassword(String login) throws ApplicationException {
	        log.debug("Service resetPassword Started");
	        boolean flag = false;
	        UserDTO dtoExist = null;
	        dtoExist = dao.findByLogin(login);
	        if (dtoExist != null) {
	            String newPassword = String.valueOf(new Date().getTime())
	                    .substring(0, 4);
	            dtoExist.setPassword(newPassword);
	            dao.update(dtoExist);

	            HashMap<String, String> map = new HashMap<String, String>();
	            map.put("login", dtoExist.getLogin());
	            map.put("password", dtoExist.getPassword());
	            map.put("firstName", dtoExist.getFirstName());
	            map.put("lastName", dtoExist.getLastName());
	            String message = EmailBuilder.getForgetPasswordMessage(map);

	            MimeMessage msg = mailSender.createMimeMessage();

	            // use the true flag to indicate you need a multipart message
	            MimeMessageHelper helper;
	            try {
	                helper = new MimeMessageHelper(msg, true);
	                helper.setTo(dtoExist.getLogin());
	                helper.setSubject("Password has been reset.");
	                // use the true flag to indicate the text included is HTML
	                helper.setText(message, true);
	            } catch (MessagingException e) {
	                System.out.println("Mail Sending Failed");
	                e.printStackTrace();
	            }
	            mailSender.send(msg);

	            flag = true;
	        } else {
	        }
	        log.debug("Service restPassword End");
	        return flag;
	    }
        
	    /**
	     * Send the password of User to his Email
	     * 
	     * @return boolean : true if success otherwise false
	     * @param login
	     *            : User Login
	     * @throws ApplicationException : Application exception
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	    public boolean forgetPassword(String login) throws ApplicationException {
	        log.debug("Service forgetPassword Started");

	        UserDTO dtoExist = dao.findByLogin(login);

	        if (dtoExist != null) {

	            HashMap<String, String> map = new HashMap<String, String>();
	            map.put("firstName", dtoExist.getFirstName());
	            map.put("lastName", dtoExist.getLastName());
	            map.put("login", dtoExist.getLogin());
	            map.put("password", dtoExist.getPassword());

	            String message = EmailBuilder.getForgetPasswordMessage(map);

	            MimeMessage msg = mailSender.createMimeMessage();

	            try {
	                MimeMessageHelper helper = new MimeMessageHelper(msg);
	                helper.setTo(login);
	                helper.setSubject("SunilOS ORS Password reset");
	                // use the true flag to indicate the text included is HTML
	                helper.setText(message, true);
	                mailSender.send(msg);
	            } catch (MessagingException e) {
	                e.printStackTrace();
	                log.error(e);
	                return false;
	            }
	        } else {
	            return false;
	        }
	        log.debug("Service forgetPassword End");
	        return true;
	    }
        
	    /**
	     * Get User Roles
	     * 
	     * @return RoleDTO : User Role
	     * @param dto : the dto
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	    public RoleDTO getRole(UserDTO dto) {
	        return roleService.findById(dto.getRoleId());
	    }
        
	    /**
	     * Update User access
	     * 
	     * @return dto : the dto
	     * @param dto : the dto
	     * 
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	    public UserDTO updateAccess(UserDTO dto) {
	        return null;
	    }
}
