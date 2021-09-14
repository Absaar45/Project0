package com.sunilos.proj0.ctl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunilos.proj0.dto.TimetableDTO;
import com.sunilos.proj0.dto.UserDTO;
import com.sunilos.proj0.exception.DuplicateRecordException;
import com.sunilos.proj0.form.TimetableForm;
import com.sunilos.proj0.service.CourseServiceInt;
import com.sunilos.proj0.service.SubjectServiceInt;
import com.sunilos.proj0.service.TimetableServiceInt;

/**
 * Timetable functionality Controller. Performs operation for add,update,delete and find list
 * @author Chain of Responsibility
 * @version 1.0 Copyright (c) Chain of Responsibility
 */
@Controller
@RequestMapping(value="/ctl/Timetable")
public class TimetableCtl extends BaseCtl{
	
	private static Logger log = Logger.getLogger(TimetableCtl.class);
	
	@Autowired
	private TimetableServiceInt service;
	
	@Autowired
	private CourseServiceInt courseService;
	
	@Autowired
	private SubjectServiceInt subjectService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void preload(Model model) {
		log.debug("TimetableCtl preload method started");
		model.addAttribute("courseList",courseService.search(null));
    	model.addAttribute("subjectList",subjectService.search(null));
		log.debug("TimetableCtl preload method ended");
	}
	
	/**
     * Displays Timetable View
     * @param locale:
     * 				Object of Locale 
     * @param id:
     * 				Primary Key
     * @param form:
     * 				Object of TimetableForm
     * @param model:
     * 				Object of Model Interface
     * @return Timetable:
     * 					View of Timetable Form
     */
	@RequestMapping(value="/Timetable", method=RequestMethod.GET)
	public String display(@RequestParam(required=false) Long id, @ModelAttribute("form") TimetableForm form, Model model,Locale locale) {
		log.debug("TimetableCtl display method to display TimetableForm started");
		String entername=messageSource.getMessage("label.enterexamdate",null,locale);
		model.addAttribute("enterDate",entername);
		
		String enterdescription=messageSource.getMessage("label.enterdescription",null,locale);
		model.addAttribute("enterdescription",enterdescription);
		if(id!=null && id>0) {
			form.populate(service.findByPK(id));
		}
		log.debug("TimetableCtl display method to display TimetableForm ended");
		return "Timetable";
	}
	
	/**
     * Submit Timetable View
     * @param locale:
     * 				Object of Locale 
     * @param operation:
     * 					Operation given By User
     * @param form:
     * 				Object of TimetableForm
     * @param bindingResult:
     * 						Object of BindingResult
     * @param model:	
     * 				Object of Model Interface
     * @param locale:
     * 					Object of Locale
     * @param session:
     * 					Object of HttpSession
     * @return Timetable:
     * 					View of Timetable Form
     */
	@RequestMapping(value="/Timetable", method=RequestMethod.POST)
	public String submit(@RequestParam String operation, @ModelAttribute("form") @Valid TimetableForm form, BindingResult bindingResult, Model model, Locale locale, HttpSession session) {
		log.debug("TimetableCtl submit method to submit TimetableForm started");
		UserDTO userDto = (UserDTO) session.getAttribute("user"); 
	    TimetableDTO dto=(TimetableDTO) form.getDto();
	    if(OP_SAVE.equalsIgnoreCase(operation)) {
	    	if(bindingResult.hasErrors()) {
	    		return "Timetable";
	    	}
	    	try {
	    		if(dto.getId()>0) {
	    			dto.setModifiedBy(userDto.getLogin());
	      			service.update(dto);
	      			String msg=messageSource.getMessage("message.updatesuccess",null,locale);
	  			    model.addAttribute("success",msg);
	  			    model.addAttribute("id",dto.getId());
	    		}else {
	    			dto.setCreatedBy(userDto.getLogin());
	      			dto.setModifiedBy(userDto.getLogin());
	      			service.add(dto);
	      			String msg=messageSource.getMessage("message.success",null,locale);
	      			model.addAttribute("success", msg);
	    		}
	    	}catch(DuplicateRecordException e) {
	    		String msg=messageSource.getMessage("error.record",null,locale);
	  		    model.addAttribute("error",msg);
	  		    return "Timetable";
	    	}
	    }else if(OP_RESET.equalsIgnoreCase(operation)) {
	    	return "redirect:/ctl/Timetable/Timetable";
	    }else if(OP_CANCEL.equalsIgnoreCase(operation)) {
	    	return "redirect:/ctl/Timetable/Search";
	    }
		log.debug("TimetableCtl submit method to submit TimetableForm ended");
		return "Timetable";
	}
	
	/**
     * Displays TimetableList View
     * @param locale:
     * 				Object of Locale 
     * @param form:
     * 				Object of TimetableForm
     * @param model:
     * 				Object of Model Interface
     * @return TimetableList:
     * 						View of TimetableList
     */
	@RequestMapping(value="/Search",method=RequestMethod.GET)
	public String display(@ModelAttribute("form") TimetableForm form, Model model,Locale locale) {
		log.debug("TimetableCtl display method to display TimetableList started");
		String entername=messageSource.getMessage("label.enterexamdate",null,locale);
		model.addAttribute("enterDate",entername);
		model.addAttribute("list",service.search(null, form.getPageNo(),form.getPageSize()));
		log.debug("TimetableCtl display method to display TimetableList ended");
		return "TimetableList";
	}
	
	/**
     * Submit TimetableList View
      * @param locale:
     * 				Object of Locale 
     * @param operation:
     * 					Operation given by User
     * @param form:
     * 				Object of TimetableForm
     * @param model:
     * 				Object of Model Interface
     * @param locale:
     * 				Object of Locale
     * @return Timetable:
     * 						View of TimetableList
     */
	@RequestMapping(value="/Search",method=RequestMethod.POST)
	public String submit(@RequestParam(required=false) String operation, @ModelAttribute("form") TimetableForm form, Model model, Locale locale) {
		log.debug("TimetableCtl submit method to submit TimetableList started");
		String entername=messageSource.getMessage("label.enterexamdate",null,locale);
		model.addAttribute("enterDate",entername);
		int pageNo=form.getPageNo();
		if(OP_PREVIOUS.equalsIgnoreCase(operation)) {
			pageNo--;
		}else if(OP_NEXT.equalsIgnoreCase(operation)) {
			pageNo++;
		}else if(OP_DELETE.equalsIgnoreCase(operation)) {
			if(form.getChk_1()!=null) {
				for(long id:form.getChk_1()){
	    			service.delete(id);
	    		}
	    		String msg = messageSource.getMessage("message.deleterecord", null, locale);
				model.addAttribute("success", msg);
			}else {
				String msg = messageSource.getMessage("message.atleastone", null, locale);
				model.addAttribute("error1", msg);
			}
		}
		pageNo=(pageNo<1)?1:pageNo;
	    form.setPageNo(pageNo);
	    TimetableDTO dto=(TimetableDTO) form.getDto();
	    List list=service.search(dto, pageNo, form.getPageSize());
	    model.addAttribute("list", list);
	    if(list.size()==0 && !OP_DELETE.equalsIgnoreCase(operation) ){
	    	model.addAttribute("error", messageSource.getMessage("message.norecord", null, locale));
	  	}
	    if(OP_RESET.equalsIgnoreCase(operation)){
	    	return "redirect:/ctl/Timetable/Search";
	    }else if(OP_NEW.equalsIgnoreCase(operation)){
	    	return "redirect:/ctl/Timetable/Timetable";
	    }
		log.debug("TimetableCtl submit method to submit TimetableList ended");
		return "TimetableList";
	}

}
