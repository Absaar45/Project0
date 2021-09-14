package com.sunilos.proj0.form;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.sunilos.proj0.dto.BaseDTO;
import com.sunilos.proj0.dto.RoleDTO;

/**
 * Contains Role form elements and their declarative input validations.
 * 
 * @author Chain of Responsibility
 * @version 1.0 Copyright (c) Chain of Responsibility
 * 
 */
public class RoleForm extends BaseForm{
	
	/**
	 * rolename of RoleForm
	 */
    @NotEmpty
    private String name;
    /**
	 * roledescription of RoleForm
	 */
    @NotEmpty
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BaseDTO getDto() {

        RoleDTO dto = new RoleDTO();
        dto.setId(id);
        dto.setRoleName(name);
        dto.setRoleDescription(description);
        dto.setCreatedBy(createdBy);
        dto.setModifiedBy(modifiedBy);
        dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
        dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

        return dto;
    }

    @Override
    public void populate(BaseDTO bDto) {

        if (bDto == null) {
            return;
        }

        RoleDTO dto = (RoleDTO) bDto;

        id = dto.getId();
        name = dto.getRoleName();
        description = dto.getRoleDescription();
        createdBy = dto.getCreatedBy();
        modifiedBy = dto.getModifiedBy();
        if(dto.getCreatedDatetime()!=null)
        createdDatetime = dto.getCreatedDatetime().getTime();
        if(dto.getModifiedDatetime()!=null)
        modifiedDatetime = dto.getModifiedDatetime().getTime();
    }

}

