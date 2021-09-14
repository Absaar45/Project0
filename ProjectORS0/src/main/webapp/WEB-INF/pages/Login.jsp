<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link href='<c:url value = "http://localhost:8080/ProjectORS0/resources/css/style.css"></c:url>' rel="stylesheet" />
</head>
<body>

 
   <sf:form commandName="form" method="post">
   <div class="container mt-5 mb-6">
      <div class="row">
        <div class="col-lg-4 col-md-6 mx-auto">
          <div class="card card-login">
         
           
              <div class="card-header card-header-primary text-center">
                <h4 class="card-title">Login</h4>
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
              
              <div class="card-body">
               
               <%-- <sf:hidden path="uri" value="${uri}"/> --%>
                <span class="bmd-form-group">
                  <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fa fa-sign-in" aria-hidden="true"></i>
                    </span>
                  </div>
                  <sf:input type="text" path="emailId" class="form-control"
					placeholder="${enteremail}" />
                </div>
                <div style="padding-left: 50px;">
                <font  style=" color: red;"><sf:errors path="emailId"/></font>
              </div>
              </span>


                <span class="bmd-form-group">
                  <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">
                      <i class="fa fa-lock" aria-hidden="true"></i>
                    </span>
                  </div>
                 <sf:input type="password" path="password" class="form-control"
					placeholder="${enterpassword}" />
                </div>
                <div style="padding-left: 50px;">
                  <font  style=" color: red;"><sf:errors path="password"/></font>
                </div>
              </span>
              
               <div class=" text-center">
               <button type="submit" name="operation" value="signIn" class="btn btn-primary btn-lg" ><s:message code="label.signIn"/></button>
              </div>
              
              <div class=" text-center">
                <a href='<c:url value = "/ForgetPassword"></c:url>' class="btn btn-primary btn-link btn-wd btn-lg"><s:message
						code="label.forgetpassword" /></a>
              </div>
              
              
              </div>
              
             
             
           
          </div>
        </div>
      </div>
    </div>
 

</sf:form>
</body>
</html>