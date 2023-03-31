<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Inventory Addition Form</title>

<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" /> 

<script type="text/javascript">

	$(document).ready(function (){
		$('#name').keyup(function(){
			var userName = $(this).val;
			{
			$.ajax({
				url: "checkProductName",
				data:{"product_name":$("#name").val()},
				type: "GET",
				success:function(data){
				var divElement = $("#errormsg");
				if(data == "Y"){
					divElement.text("This name is already in use");
					divElement.css('color', 'red');
				}
				else{
					divElement.text("");
				}
				},
				error:function (){}
				});
			}
		})
	}) 

</script>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<div align="center">

<br><h3>Add Product To Inventory</h3><br><br><br>

<form action="vendorAdd" method="post">
  <div class="form-group">
			<table style="width: 50%">
				<tr>
					<td>Name of the Product:</td>


					<td colspan="1"><input type="text" name="name" class="form-control" id="name" required="true"/></td>
					<td><div id="errormsg"></div></td>

				</tr>
				<tr>
				<td> Category: </td>
				<td colspan="2">
				<select name="category" class="form-control" required="true">
				    <c:forEach items="${categories}" var="category">
				        <option value="${category}">${category}</option>
				    </c:forEach>
				</select>	
				</td>
				</tr>
				<tr>
					<td>Quantity:</td>

					<td colspan="2"><input type="number" name="quantity" required="true" class="form-control"/></td>

				</tr>
				
				<tr>
					<td>Price:</td>

					<td colspan="2"><input type="text" name="price" required="true" class="form-control"/></td>

				</tr>
				
				<tr>
					<td>Image Link:</td>

					<td colspan="2"><input type="text" name="imageLink" required="true" class="form-control"/></td>
				</tr>
				<tr>
					<td>Description of the product:</td>
					<td colspan="2"><textarea name="description" required="true" class="form-control" rows="6"></textarea></td>

				</tr>
			</table>
			<br><br>
			<input type="submit" value="Submit" class="btn btn-primary"/>
			</div>
			</form>
			
			
			<div align="center"> 
		<c:if test="${not empty valid}">
		<p style="color:red">${valid}</p>	
		</c:if>
	</div>

			
</div>
</body>
</html>

