<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/dataTables.bootstrap4.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap4.min.js" />"></script>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />


	<title>Inventory Products</title>
	<style type="text/css">
		
		
		
		
		
			.button-container form,
	.button-container form div {
   	 display: inline;
	}
			.button-container button {
    display: inline;
	}

	   .dataTables_filter, .dataTables_paginate  {float:right; padding-right:170px;}
    .dataTables_length, .dataTables_info{float:left; padding-left:170px}
	</style>
	
	

<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" /> 
<script>
	$(document).ready(function() {
	    $('#product').DataTable();
	} );
</script>

</head>
<body> 

<jsp:include page="navbar.jsp"/>


<div>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>


</div>
<div align="center"> 
	<h3> Product List </h3>
	<c:if test="${!empty listInventory}">
	<div class="row"><div class="col">
	<table id="product" class="table table-striped table-bordered" style="width:80%;">
		<thead>
			<tr> 
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Product Quantity</th>
			</tr>
		</thead>

	<tbody>		
		<c:forEach items="${listInventory}" var="products" >
		<tr>
			<td>${products.product.id}</td>
			<td>${products.name}</td>
			<td>${products.quantity}</td>
		</tr>
		</c:forEach>
	</tbody>	
	
	</table>
	</div>
	</div>
	</c:if>
	
	 <form  action = "addToInventory"  >
     
         <table>
            <tr>
               <td>

                  <br><input type = "submit" value = "Add a Product" class="btn btn-primary" />
               </td>
            </tr>
         </table>  
      </form>
      
      
       <form  action = "updateToInventory" >
         <table>
            <tr>
               <td>

                  <input type = "submit" value = "Update a Product" class="btn btn-primary"/>

               </td>
            </tr>
         </table>  
      </form>
      
      
      
      <form  action = "removeFromInventory" >
         <table >
            <tr>
               <td >
                  <input type = "submit" value = "Remove a Product" class="btn btn-primary" style="color:white; background:red;"/>
               </td>
            </tr>
         </table>  
      </form>
	
	   	<div align="center"> 
		<c:if test="${not empty success}">
		<p style="color:green">${success}</p>	
		</c:if>
	</div>
	
	<div align="center"> 
		<c:if test="${not empty failure}">
		<p style="color:red">${failure}</p>	
		</c:if>
	</div>
	
		<div align="center"> 
		<c:if test="${not empty valid}">
		<p style="color:red">${valid}</p>	
		</c:if>
	</div>
	</div>

</body>
</html>