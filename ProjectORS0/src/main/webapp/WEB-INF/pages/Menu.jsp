<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: "Lato", sans-serif;
}

.sidenav {
  height: 85%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 90;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}


.sidenav a:hover {
  color: #f1f1f1;
}

.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 45px;
  font-size: 36px;
  margin-left: 0px;
}



@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
  
}
</style>
</head>
<body>


<c:if test="${not empty sessionScope.user}">
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href='<c:url value = "/ctl/Marksheet/Search" ></c:url>'><s:message code="label.marksheetlist" /></a>
  <a href="<c:url value = "/ctl/User/Search" ></c:url>"><s:message code="label.userlist" /></a>
  <a href="<c:url value = "/ctl/Role/Search" ></c:url>"><s:message code="label.rolelist" /></a>
  <a href="<c:url value = "/ctl/College/Search" ></c:url>"><s:message code="label.collegelist" /></a>
  <a href="<c:url value = "/ctl/Course/Search" ></c:url>"><s:message code="label.courselist" /></a>
  <a href="<c:url value = "/ctl/Subject/Search" ></c:url>"><s:message code="label.subjectlist" /></a>
  <a href="<c:url value = "/ctl/Faculty/Search" ></c:url>"><s:message code="label.facultylist" /></a>
  <a href="<c:url value = "/ctl/Student/Search" ></c:url>"><s:message code="label.studentlist" /></a>
  <a href="<c:url value = "/ctl/Timetable/Search" ></c:url>"><s:message code="label.timetablelist" /></a>
 
  
  
</div>
</c:if>


<span style="font-size:30px; margin-bottom: 600px; cursor:pointer" onclick="openNav()">&#9776;</span>

<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
</script>
   
</body>
</html> 