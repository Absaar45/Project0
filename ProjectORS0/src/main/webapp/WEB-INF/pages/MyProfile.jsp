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
<title>My Profile</title>
<link href='<c:url value = "http://localhost:8080/ProjectORS0/resources/css/style.css"></c:url>' rel="stylesheet" />
</head>
<body>

 
  
   <div class="container  mt-5 mb-6 mx-auto" >
      <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-6 mx-auto">
          <div class="card card-login">
            <sf:form commandName="form" method="post">
              <div class="card-header card-header-primary text-center">
                <h4 class="card-title">Change Profile </h4>
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
                      <i class="fa fa-user" aria-hidden="true"></i>
                    </span>
                  </div>
                  <sf:input  path="firstName"  class="form-control" placeholder="${enterfirstName}"/>
                  
                  
                </div>
                <div style="padding-left: 50px;">
                  <font  style=" color: red;"> <sf:errors path="firstName"></sf:errors> </font>
                </div>
              </span>

                <span class="bmd-form-group">
                  <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fa fa-user" aria-hidden="true"></i>
                    </span>
                  </div>
                 <sf:input  path="lastName"  class="form-control" placeholder="${enterLastName}"/>
                  
                </div>
                <div style="padding-left: 50px;">
                  <font  style=" color: red;"><sf:errors path="lastName"></sf:errors></font>
                </div>
              </span>
                
                <span class="bmd-form-group">
                  <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fa fa-sign-in"></i>
                    </span>
                  </div>
                   <sf:input  path="login"  class="form-control" placeholder="${enteremail}"/>
                 
                </div>
                <div style="padding-left: 50px;">
                  <font  style=" color: red;"><sf:errors path="login"></sf:errors></font>
                </div>
              </span>

            

          
                  
            
              
            
            
              <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-mobile" aria-hidden="true"></i>
                  </span>
                </div>
               <sf:input  path="mobileNo"  class="form-control" placeholder="${enterMobile}"/>
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="mobileNo"></sf:errors></font>
              </div>
            </span>
             
             

            <span class="bmd-form-group">
              <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fa fa-calendar"></i>
                </span>
              </div>
             
            <sf:input  path="dob" type="text" class="form-control datetimepicker" value="" placeholder="${enterdob}"/>
                
            
              
            </div>
            <div style="padding-left: 50px;">
              <font  style=" color: red;"><sf:errors path="dob"></sf:errors></font>
            </div>
          </span>
          
          
              <span class="bmd-form-group">
                <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="fa fa-mercury" aria-hidden="true"></i>
                  </span>
                </div>
              		<sf:select path="gender" class="form-control">
					<sf:option value="">
						<s:message code="label.selectgender"></s:message>
					</sf:option>
					<sf:options items="${genderList}" />
				</sf:select>
                
              </div>
              <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="gender"></sf:errors></font>
              </div>
            </span>

              </div>

              
              <div class=" text-center">
              <button type="submit" class="btn btn-primary " name="operation" value="save"><s:message code="label.save"/></button>
              <button type="submit" class="btn btn-danger " name="operation" value="ChangePassword"><s:message code="label.changepassword"/></button>
              </div>
             
           </sf:form>
          </div>
        </div>
      </div>
    </div>
 


</body>
</html>