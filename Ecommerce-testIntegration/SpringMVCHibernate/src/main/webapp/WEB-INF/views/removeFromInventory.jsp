<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Inventory Removal Form</title>
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
<br><h3>Remove From The Inventory</h3><br><br><br>

<form action="vendorRemove" method="post">
 			<table style="width: 50%">
				<tr>
					<td> Product Name </td>
					<td>
					<select name="product_name" class="form-control">
					    <c:forEach items="${listInventory}" var="products">
					        <option value="${products.product.name}">${products.product.name}</option>
					    </c:forEach>
					</select>	
					</td>
					</tr>
			</table>
			<br><input type="submit" value="Submit" class="btn btn-primary"/></form>
</div>
</body>

</html>