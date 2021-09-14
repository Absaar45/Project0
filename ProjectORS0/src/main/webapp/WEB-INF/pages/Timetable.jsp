<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Timetable</title>
<link href='<c:url value = "http://localhost:8080/ProjectORS0/resources/css/style.css"></c:url>' rel="stylesheet" />

</head>
<body>

 
  
   <div class="container  mt-5 mb-6 mx-auto" >
      <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-6 mx-auto">
          <div class="card card-login">
            <sf:form commandName="form" method="post">
              <div class="card-header card-header-primary text-center">
                <h4 class="card-title"><c:choose>
           			<c:when test="${form.id==0}"><s:message code="label.addtimetable" /></c:when>
           			<c:otherwise><s:message code="label.updatetimetable"></s:message></c:otherwise>
           		</c:choose> </h4>
                <div class="social-line">
                </div>
              </div>
               
               <c:if test="${success != null}">
              <div class="alert alert-success">
                <div class="container">
                  <div class="alert-icon">
                    <i class="material-icons">check</i>
                  </div>
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true"><i class="material-icons">clear</i></span>
                  </button>
                  <b>Success :</b> ${success}
                </div>
              </div>
              </c:if>
              
              <c:if test="${error != null}">
              <div class="alert alert-danger">
                <div class="container">
                  <div class="alert-icon">
                    <i class="material-icons">check</i>
                  </div>
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true"><i class="material-icons">clear</i></span>
                  </button>
                  <b>Warning:</b> ${error}
                </div>
              </div>
              </c:if>
              
              <sf:hidden path="id" />
				<sf:hidden path="createdBy" />
				<sf:hidden path="modifiedBy" />
				<sf:hidden path="createdDatetime" />
				<sf:hidden path="modifiedDatetime" />
              
              <div class="card-body">
               
      

    
                
                

            

                <span class="bmd-form-group">
                  <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class=" fa fa-align-justify" aria-hidden="true"></i>
                    </span>
                  </div>
                  <sf:select path="courseId" class="form-control">
					<sf:option value="0"><s:message code="label.selectcourse"/></sf:option>
					<sf:options items="${courseList}" itemValue="id" itemLabel="name"/>
				</sf:select> 
                 
                </div>
                <div style="padding-left: 50px;">
                  <font  style=" color: red;"><sf:errors path="courseId"></sf:errors></font>
                </div>
              </span>
                  
         
              
               <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-align-justify" aria-hidden="true"></i>
                  </span>
                </div>
               <sf:select path="subjectId" class="form-control">
					<sf:option value="0"><s:message code="label.selectsubject"/></sf:option>
					<sf:options items="${subjectList}" itemValue="id" itemLabel="name"/>
				</sf:select>
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="subjectId"></sf:errors></font>
              </div>
            </span>
            
      
             
             

            <span class="bmd-form-group">
              <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fa fa-calendar"></i>
                </span>
              </div>
             
            <sf:input  path="examDate" type="text" class="form-control datetimepicker" value="" placeholder="${enterDate}"/>
                
            
              
            </div>
            <div style="padding-left: 50px;">
              <font  style=" color: red;"><sf:errors path="examDate"></sf:errors></font>
            </div>
          </span>
          
          
              <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-align-justify" aria-hidden="true"></i>
                  </span>
                </div>
              	<sf:select path="examTime" class="form-control">
					<sf:option value=""><s:message code="label.selecttime"/></sf:option>
					<sf:option value="09:00 AM to 12:00 PM" label="09:00 PM to 03:00 PM"></sf:option>
					<sf:option value="10:00 PM to 01:00 PM" label="10:00 PM to 01:00 PM"></sf:option>
					<sf:option value="12:00 PM to 03:00 PM" label="12:00 PM to 03:00 PM"></sf:option>
					<sf:option value="01:00 PM to 04:00 PM" label="02:00 PM to 05:00 PM"></sf:option>
				</sf:select> 
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="examTime"></sf:errors></font>
              </div>
            </span>
             
             
                    <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-align-justify" aria-hidden="true"></i>
                  </span>
                </div>
              <sf:select path="semester" class="form-control">
					<sf:option value=""><s:message code="label.selectsemester"/></sf:option>
					<sf:option value="1st Semester" label="1st Semester"></sf:option>
					<sf:option value="2nd Semester" label="2nd Semester"></sf:option>
					<sf:option value="3rd Semester" label="3rd Semester"></sf:option>
					<sf:option value="4th Semester" label="4th Semester"></sf:option>
					<sf:option value="5th Semester" label="5th Semester"></sf:option>
					<sf:option value="6th Semester" label="6th Semester"></sf:option>
					<sf:option value="7th Semester" label="7th Semester"></sf:option>
					<sf:option value="8th Semester" label="8th Semester"></sf:option>
				</sf:select>
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="semester"></sf:errors></font>
              </div>
            </span>
            
                <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-align-justify" aria-hidden="true"></i>
                  </span>
                </div>
               <sf:input  path="description"  class="form-control" placeholder="${enterdescription}"/>
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="description"></sf:errors></font>
              </div>
            </span>      
            
              </div>

              
              <div class=" text-center">
              	<c:choose>
              		<c:when test="${form.id==0}"> 
						<button type="submit" class="btn btn-primary " name="operation" value="save"><s:message code="label.save"/></button>
						<button type="submit" class="btn btn-danger" name="operation" value="reset"><s:message code="label.reset"/></button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-primary " name="operation" value="save"><s:message code="label.update"/></button>
							<button type="submit" class="btn btn-danger " name="operation" value="cancel"><s:message code="label.cancel"/></button>
						</c:otherwise>
				</c:choose>
              </div>
             
           </sf:form>
          </div>
        </div>
      </div>
    </div>
 


</body>
</html>