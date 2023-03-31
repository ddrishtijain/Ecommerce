<%@page import="com.futureforce.spring.model.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/dataTables.bootstrap4.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/dataTables.bootstrap4.min.js" />"></script>
<script src="<c:url value="/resources/js/manageAddress.js" />"></script>
</head>
<body>

<jsp:include page="navbar.jsp"/>
<br><br>
<div style="padding-left:1cm;padding-right:1cm;" class="container">

	<div class="row">
		<div class="col"></div>
		<div class="col">
		<div class="card" >
		  <img src="<c:url value="/resources/img/profile.png" />" class="card-img-top" alt="Profile pic">
		  <div class="card-body">
		    <h5 class="card-title">${currUser.firstName} ${currUser.lastName}</h5>
		    
		    <p class="card-text">
		    	Email : ${currUser.email }<br>
		    	Phone : ${currUser.phoneNumber }<br>		    	 
		    	<c:if test="${isDelivery}">
		    	Delivers To :
					<c:forEach var="listValue" items="${currUser.addresses}">${listValue.zipcode} </c:forEach>
				</c:if>
		    </p>
		    
		  </div>
		</div>		
		</div>
		<div class="col"></div>
	</div>
	<br>
	<br>
	<hr class="my-4">
	<c:if test="${currUser.role == 'customer'}">
	<div id="yourOrders" >
	<div class="col">
	<h3 >Your Orders</h3>
	
	<table id="example" class="table table-bordered" style="width:100%">
		<thead>
			<tr>
				<th>Orders</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach var= "orders" items="${userOrders}">
				<tr>
					<td>
						<div class="row">
							<div class="col">
								<h4>Order Details:</h4>
								<p>
									OrderID : #${orders.order.orderId}<br>
									Total Cost : ${orders.order.totalAmount}<br>
									Ordered on : ${orders.order.orderDate}<br>
									Delivery Date : ${orders.order.deliveryDate}<br>
									Status : <b>${orders.order.status}</b>									
								</p>
							</div>
							<div class="col">
								<h4>Delivery Address:</h4>
								<p>	
									<b>${orders.order.recipientName}</b><br>
									Phone : ${orders.order.recipientPhone}<br>
									${orders.order.address}, ${orders.order.city}<br>
									${orders.order.state} ${orders.order.zipcode}<br>
									${orders.order.country}
								</p>
							</div>
						</div>
						<br>
						<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${orders.order.orderId}" aria-expanded="false" aria-controls="collapse${orders.order.orderId}">
							    Products
						</button><br>
						<div class="collapse" id="collapse${orders.order.orderId}">
						<div class="row">
							
							
							<div class="col">
								<table class="displayPdts table table-striped table-bordered table-sm" style="width:100%">
									<thead>
										<tr>
											<th>Product Name</th>
											<th>Quantity</th>
											<th>Amount</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var= "details" items="${orders.orderDetails}">
											<tr>
												<td>${details.productName}</td>
												<td>${details.qty}</td>
												<td>${details.amount}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
						</div>	
						</div>				
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table> 
	<br>
	</div>
	</div>
	<br>
	<hr class="my-4">
	<div id="manageAddresses" >
	<h3 >Manage Addresses</h3><a style="margin-bottom:5px;margin-top:5px;" class="btn btn-primary" href="addAddress" role="button">Add Address</a><br>
		<div class="row">
			<c:forEach var="listValue" items="${currUser.addresses}">
				<div data-rem="${listValue.id}" class="col-4">
					<br>
					<div class="card" >
					  <div class="card-body">
					  
					  					    				    
					    <p class="card-text">
					    	
					    	<c:choose>
						    	<c:when test="${listValue.recipientName == '' && listValue.recipientPhone == ''}">
							    	<font size="6">${currUser.firstName}&nbsp;${currUser.lastName}</font><br>
							    	Phone : ${currUser.phoneNumber}<br>
						    	</c:when>
						    	<c:when test="${listValue.recipientName == ''}" >
						    		<font size="6">${currUser.firstName}&nbsp;${currUser.lastName}</font><br>
							    	Phone : ${listValue.recipientPhone}<br>
						    	</c:when>
						    	<c:when test="${listValue.recipientPhone == ''}" >
						    		<font size="6">${listValue.recipientName}</font><br>
							    	Phone : ${currUser.phoneNumber}<br>
						    	</c:when>
						    	<c:otherwise>						    		
							    	<font size="6">${listValue.recipientName}</font><br>
							    	Phone : ${listValue.recipientPhone}<br>
						    	</c:otherwise>
						    </c:choose>
								${listValue.address},
						    	${listValue.city}, <span style="text-transform: uppercase;">${listValue.state} ${listValue.zipcode}</span>
						    	${listValue.country}
								
					    </p>
					    <c:choose>
					    	<c:when test="${listValue.isDefault}">
					    		<a data-id="${listValue.id}" href="#" class="setDefault card-link btn btn-primary btn-sm disabled">Set as Default</a>
					    		<a data-id="${listValue.id}" href="#" class="deleteAdd card-link btn btn-danger btn-sm disabled">Delete</a>
					    	</c:when>
					    	<c:otherwise>
					    		<a data-id="${listValue.id}" href="#" class="setDefault card-link btn btn-primary btn-sm">Set as Default</a>
					    		<a data-id="${listValue.id}" href="#" class="deleteAdd card-link btn btn-danger btn-sm">Delete</a>
					    	</c:otherwise>
					    </c:choose>				    
					  </div>
					</div>
				</div>
			</c:forEach>
		</div>
		</div>
		<br>
	
	</c:if>
</div>





</body>
</html>

