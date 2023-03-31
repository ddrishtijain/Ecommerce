

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
</head>
<body>



	<div class="container">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<h2 align="center">Your Cart</h2>
	<div class"row">
	<div class="col-12">
	
	<table class="table table-hover">
		<thead class="bg-info">
		<tr>
			<th>Product Name</th>
			<th>Product Description</th>
			<th>Quantity Ordered</th>
			<th>Amount</th>
			<th>&nbsp;</th>
		</tr>
		</thead>
		
		<tbody>
			<c:forEach var="cart" items="${cartlist}">
			<c:url var="deletelink" value="/delete">
				<c:param name="id" value="${cart.getProduct().getId()}"/>
			</c:url>
				<tr>
					<td>${cart.getProduct().getName()}</td>
					<td>${cart.getProduct().getDescription()}</td>
					<td>${cart.getQtyAdded()}</td>
					<td>${cart.getQtyAdded()*cart.getProduct().getPrice()}</td>
					<td><a href="${deletelink}"
						onClick="if(!confirm('Are you sure to delete'))) return false">
						Remove</a></td>
				</tr>
			</c:forEach>
		
		</tbody>
	
	</table>
	</div>
	</div>
	</div>
	<h2><div style="text-align:center"><a href="placeorder"  class="btn btn-success">Place Your Order</a></h2></div>
</body>
</html>