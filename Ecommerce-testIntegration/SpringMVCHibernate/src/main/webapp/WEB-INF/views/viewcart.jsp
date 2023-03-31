

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>



<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />

<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" />
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

 

</head>
<script type="text/javascript">
function refresh(id){
	var qty = document.getElementById("qty"+id).value;
	var price = document.getElementById("price"+id).innerHTML;
	price=price.substring(4);
	var subtotal = document.getElementById("subtotal"+id);
	var finaltotal = qty * price ;
	console.log(subtotal.innerHTML);
	subtotal.innerHTML = "Rs. " + finaltotal;
	console.log(qty , price, finaltotal, subtotal);
	/* var anchor = document.getElementById("updatelink"+id);
	anchor.href="updatecart?id="+id+"&qty="+qty; */
	
	 var url = '${pageContext.request.contextPath}'+"/updatecart?id="+id+"&qty="+qty;
	 setTimeout(
			"location.href = '" + url + "'" ,
			0);
}

</script>

<jsp:include page="navbar.jsp"/>

<body>

<div class="container">
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="cart" items="${cartlist}">
					
						<c:url var="updatelink" value="/updatecart">
							<c:param name="id" value="${cart.getProduct().getId()}"/>
							<c:param name="qty" value="${cart.getQtyAdded()}"/>
						</c:url>
					
						<c:url var="deletelink" value="/delete">
							<c:param name="id" value="${cart.getProduct().getId()}"/>
							<c:param name="qty" value="${cart.getQtyAdded()}"/>
							<c:param name="price" value="${cart.getProduct().getPrice()}"/>
						</c:url>
						
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="${cart.getProduct().getImage_link()}" alt="Image Not Found" class="img-responsive" height="60" width="60"/></div>
									<div class="col-sm-10">
										<h6 class="nomargin">${cart.getProduct().getName()}</h6>
										<p>${cart.getProduct().getDescription()}</p>
									</div>
								</div>
							</td>
							<td id="price${cart.getProduct().getId()}" data-th="Price">Rs. ${cart.getProduct().getPrice()}</td>
							<td data-th="Quantity">
								<input id="qty${cart.getProduct().getId()}" type="number" min="1" class="form-control text-center" onchange="refresh(${cart.getProduct().getId()})" value="${cart.getQtyAdded()}">
							</td>
							<td data-th="Subtotal" id="subtotal${cart.getProduct().getId()}" class="text-center">Rs. ${cart.getQtyAdded()*cart.getProduct().getPrice()}</td>
							<td class="actions" data-th="">
								<%-- <a id="updatelink${cart.getProduct().getId()}" href="#" onclick="refresh(${cart.getProduct().getId()})" class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></a> --%>
								<a href="${deletelink}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></a>								
							</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<%-- <tr class="visible-xs">
							<td class="text-center"><strong>${total}</strong></td>
						</tr> --%>
						<tr>
							<td><a href="products" class="btn btn-warning"> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Rs. ${total}</strong></td>

							<td><a href="placeorder" class="btn btn-success">Checkout</a></td>
						</tr>
					</tfoot>
				</table>
				</div>
				<%-- <form name="tempform" id="tempform" action="updatecart" modelAttribute="Cart" method="POST">
				<!-- <form name="tempform" id="tempform" action="updatecart" method="POST"> -->
				<input type="hidden" name="hiddenqty" value="${cart.getQtyAdded()}"/>
				</form> --%>
				</body>

</html>