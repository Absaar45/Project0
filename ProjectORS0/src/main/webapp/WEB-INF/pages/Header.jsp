<%@page import="com.sunilos.proj0.dto.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-primary">
  <div class="container-fluid">

      
      
      <%-- <a class="navbar-brand" href="" >
      <img class="w-50"
	src='<c:url value="http://localhost:8080/ProjectORS0/resources/img/customLogoT.png" ></c:url>'></a> --%>
    <a class="navbar-brand" href="#">
    <strong><font color="red">R</font>ays</strong>
    </a>
      
      <a class="text-dark bg-warning circle p-1  mr-auto"  href='<c:url value="?lang=en"></c:url>'><strong>English</strong></a>
     
      <a class="text-dark bg-success circle p-1  mr-auto" href="<c:url value="?lang=hi"></c:url>"><strong>हिन्दी</strong></a>
      
    
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="sr-only">Toggle navigation</span>
      <span class="navbar-toggler-icon"></span>
      <span class="navbar-toggler-icon"></span>
      <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
         
       
          
         <c:if test ="${ not empty sessionScope.user}">

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <s:message code = "label.addmarksheet"></s:message>
            </a>
            

            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            
            <c:if test="${sessionScope.user.roleId == 1}">
             <a class="dropdown-item" href='<c:url value ="/ctl/Marksheet/Marksheet"></c:url>'> <s:message code="label.addmarksheet"></s:message></a>
              </c:if>
              
              <a class="dropdown-item" href="<c:url value ="/ctl/Marksheet/GetMarksheet"></c:url>"><s:message code="label.getmarksheet"></s:message></a>
             
              <a class="dropdown-item" href="<c:url value ="/ctl/Marksheet/MarksheetMeritList"></c:url>"><s:message code="label.marksheetmeritlist"></s:message></a>
            </div>
          </li>


        
            <c:if test="${sessionScope.user.roleId == 1}">
            
          <li class="nav-item">
		  <a class="nav-link" href="<c:url value ="/ctl/College/College"></c:url>"><s:message code="label.addcollege"></s:message></a>
		</li>

         <li class="nav-item">
          <a class="nav-link" href="<c:url value ="/ctl/User/User"></c:url>"><s:message code="label.adduser"></s:message></a>
          </li>


        <li class="nav-item">
        <a class="nav-link" href="<c:url value ="/ctl/Role/Role"></c:url>"><s:message code="label.addrole"></s:message></a>
       </li>
    
         </c:if>
    
       <c:if test="${sessionScope.user.roleId == 1 || sessionScope.user.roleId == 3}">
       
       
       <li class="nav-item">
         <a class="nav-link" href="<c:url value ="/ctl/Course/Course"></c:url>"><s:message code="label.addcourse"></s:message></a>
       </li>
         
      <li class="nav-item">
      <a class="nav-link" href="<c:url value ="/ctl/Subject/Subject"></c:url>"><s:message code="label.addsubject"></s:message></a>
       </li>
  
  
      <li class="nav-item">
      <a class="nav-link" href="<c:url value ="/ctl/Student/Student"></c:url>"><s:message code="label.addstudent"></s:message></a>
     </li>
     
     <li class="nav-item">
	<a class="nav-link" href="<c:url value="/ctl/Faculty/Faculty"/>"><s:message code="label.addfaculty" /></a>
	</li>
	
    </c:if>
     
      <c:if test="${sessionScope.user.roleId == 1 || sessionScope.user.roleId == 3 || sessionScope.user.roleId == 5 }">
     <li class="nav-item">
      <a class="nav-link" href="<c:url value ="/ctl/Timetable/Timetable"></c:url>"><s:message code="label.addtimetable"></s:message></a>
     </li>
     </c:if>
      <c:if test="${sessionScope.user.roleId == 1}">
				</c:if> 
				</c:if>
     
          <li class="nav-item dropdown border-left border-primary">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <c:if test="${ not empty sessionScope.user}">
              <c:set var="name" value="${sessionScope.user.firstName}"></c:set>
              <c:set var="role" value='${sessionScope["role"]}'></c:set>
              <c:out value="${name} ${role}"></c:out>
              </c:if>
              <c:if test="${empty sessionScope.user}"> 
             <s:message code="label.hiGuest"></s:message>
               </c:if> -
              
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
           	<c:if test="${not empty sessionScope.user}">
						<a class="dropdown-item" href="<c:url value="/ctl/User/Myprofile" />"><s:message code="label.myprofile" /></a> 
						<a class="dropdown-item" href="<c:url value="/ctl/User/ChangePassword"/>"><s:message code="label.changepassword"/></a>
						<a class="dropdown-item" href="<c:url value="/resources/doc/index.html"/>" target="blank"><s:message code="label.javadoc"/></a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="<c:url value="/Login"/>"><s:message code="label.logout" /></a>
						</c:if>
						<c:if test="${empty sessionScope.user}">
						<a class="dropdown-item" href='<c:url value="/Login"></c:url>'>
						<s:message code="label.login" /></a> 
						
						<a class="dropdown-item" href='<c:url value="/UserRegistration"></c:url>'>
						<s:message code="label.signup" /></a> 
						</c:if>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</body>
</html>