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
<title>Marksheet List</title>
<link href='<c:url value = "http://localhost:8080/ProjectORS0/resources/css/style.css"></c:url>' rel="stylesheet" />
</head>
<body>

<sf:form method="post" commandName="form">
 
 <c:set value="${((form.pageNo-1)*form.pageSize)+1}" var="index"></c:set>
				<sf:hidden path="pageNo" />
				<sf:hidden path="pageSize" />

 
  
  <div class="container mt-12 mb-6 col-lg-12" >
    
   


         <div class="card card-table">
          
           <div class="card-header card-header-primary text-center col-sm-3 mx-auto">
           <h3 class="card-title">
				<s:message code="label.marksheetlist"></s:message>
			</h3>
              </div> 
           

           
           <div class=" align-self-center text-center col-lg-6"
           style="min-height: 15px; ">
           
           <c:if test="${success != null}">
           <div class="alert alert-success">
            <div class="container" style="padding-bottom: 5%">
              <div class="alert-icon">
                <i class="material-icons">check</i>
              </div>
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true"><i class="material-icons">clear</i></span>
              </button>
              <b>Success :</b>${success}
            </div>
          </div>
          </c:if>
          
            <c:if test="${error != null }">
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
          
           </div>

            
             
           
           
           
           <c:if test="${!empty list}">
             <div class=" row p-2 d-flex justify-content-center">
               <div class="col-lg-3">
             
             <sf:input path="studentName" class="form-control" placeholder="${enterName}"/>
               </div>
               
                  <div class="col-lg-3">
             
              <sf:input path="rollNo" class="form-control" placeholder="${enterRollNo}"/>
               </div>

               <div class="col-lg-3">

              <div class="row">
             
                <div class="col-4">
              <button class="btn btn-success" name="operation" value="Search">Search</button>
            </div>

            <div class="col-4">
              <button class="btn btn-warning" name="operation" value="Reset">Reset</button>
            </div>

              </div>

              </div>


             </div>
          
             
             
    
             <div class="row d-flex justify-content-center mt-6 mb-6">
             <div class="col-lg-12 table-responsive">
              <table class="table table-hover table-striped border border-secondary">
               <thead class="bg-primary">
								<tr>
									<th scope="col" class="text-center text-white"><input
										type="checkbox" id="selectall" /></th>
									<th scope="col" class="text-center">S.No.</th>
									<th scope="col" class="text-center"><s:message code="label.name"/></th>
									<th scope="col" class="text-center"><s:message code="label.rollNo"/></th>
									<th scope="col" class="text-center"><s:message code="label.physics"/></th>
									<th scope="col" class="text-center"><s:message code="label.chemistry"/></th>
									<th scope="col" class="text-center"><s:message code="label.maths"/></th>
									<th scope="col" class="text-center"><s:message code="label.total"/></th>
									<th scope="col" class="text-center"><s:message code="label.percentage"/></th>
									<th scope="col" class="text-center"><s:message code="label.edit"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="marksheet" varStatus="ct">
							<c:set var="percentage"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(marksheet.physics+marksheet.chemistry+marksheet.maths)/3}" /></c:set>
								<tr>
									<td class="text-center">
										<input class="case" name="chk_1" type="checkbox" value="${marksheet.id}">
									</td>
									<td class="text-center">
										<c:out value="${(form.pageSize * (form.pageNo-1))+ct.index+1}" />
										<c:set var="index" value="${(form.pageSize * (form.pageNo-1))+ct.index+1}" />
									</td>
									<td class="text-center">${marksheet.name}</td>
									<td class="text-center">${marksheet.rollNo}</td>
									<td class="text-center">${marksheet.physics}</td>
									<td class="text-center">${marksheet.chemistry}</td>
									<td class="text-center">${marksheet.maths}</td>
									<td class="text-center">${marksheet.physics+marksheet.chemistry+marksheet.maths}</td>
									<td class="text-center">${percentage}%</td>
									<td class="text-center">
										<c:url var="editUrl" value="/ctl/Marksheet/Marksheet?id=" />
              							<a class="btn btn-outline-warning btn-block btn-round w-100" href="${editUrl}${marksheet.id}"><i class="fa fa-trash" aria-hidden="true"></i><s:message code="label.edit"/></a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
              </table>
             </div>

            

             </div>
             
            
             
               
               <div class="row d-flex justify-content-center"> 
              
                       
              <div class="col-lg-2 col-3">
              
               <c:choose>
              <c:when test="${form.pageNo > 1 }">
              <button class="btn btn-danger" name="operation" value="Previous">Previous</button>
              </c:when>
              
              <c:otherwise>
              <button class="btn btn-warning"name="operation" value="Previous" disabled>Previous</button>
              </c:otherwise>
              </c:choose>
              
              </div>
              <br>
              
              <div class="col-lg-2 col-3">
              <button class="btn btn-success" type="submit" name="operation" value="New">New</button>
              </div>
              
               <div class="col-lg-2 col-3">
              <button class="btn btn-danger" type="submit" name="operation" value="Delete">Delete</button>
              </div>
              
              <div class="col-lg-1 col-3">
               <c:choose>
					<c:when test="${nextlistsize != 0}">
					 <button class="btn btn-success" name="operation" value="Next">Next</button>
					 </c:when>
						 
				 <c:otherwise>
			 <button class="btn btn-success" name="operation" value="Next" disabled>Next</button>
			 </c:otherwise>
			 </c:choose>
              </div>
            
             </div>
          </c:if>
          
         <c:if test="${empty list}">
          <div class="row d-flex justify-content-center">
         
          <div class="col-lg-2 col-6">
           <button type="submit" class="btn btn-outline-warning btn-block btn-round w-100" name="operation" value="Back">
		   <i class="fa fa-reply" aria-hidden="true"></i><s:message code="label.back"></s:message>
          </div>
          
          </div>
          </c:if>
          
         </div>
         
     
   </div>
 

 </sf:form> 

</body>
</html>