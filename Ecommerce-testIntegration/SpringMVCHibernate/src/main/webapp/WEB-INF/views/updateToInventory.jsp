<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Inventory Updation Form</title>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" /> 

</head>

<body>
<jsp:include page="navbar.jsp"/>
<div align="center">
<br><h3>Update To The Inventory</h3><br><br><br>

<form action="vendorUpdate" method="post">
<div class="form-group">
 			<table style="width: 50%">
				<tr>
					<td> Product Name </td>
					<td>
					<select name="product_name" class="form-control" required="true">
					    <c:forEach items="${listInventory}" var="products">
					        <option value="${products.product.name}">${products.product.name}</option>
					    </c:forEach>
					</select>	
					</td>
					</tr>
 				<tr>
				 	<td>Choose Operation</td>
				 	<td>
				 	<label class="radio-inline">
				 	<input type="radio" name="operation" value="Add" class="form-control" checked="true"> Add</label></td>
				 	
				 	
				  <td>
				  <label class="radio-inline">
				  <input type="radio" name="operation" value="Subtract" class="form-control"> Subtract</label></td>
				 	</tr>
				<tr>
					<td>Quantity</td>

					<td><input type="text" name="quantity" class="form-control" required="true" value="0"/></td>

				</tr>
				<tr>
					<td>Price</td>

					<td><input type="text" name="price" class="form-control"  /></td>

				</tr>
				<tr>
					<td>Description</td>

					<td><textarea type="text" name="description" class="form-control" rows="6" ></textarea></td>

				</tr>
			</table>
			<br><input type="submit" value="Submit" class="btn btn-primary"/>
			</div>
			</form>
			
</div>
</body>

</html>