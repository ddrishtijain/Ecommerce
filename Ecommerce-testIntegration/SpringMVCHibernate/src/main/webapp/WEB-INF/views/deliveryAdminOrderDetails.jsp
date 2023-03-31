<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Information</title>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/deliveryHome.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/deliveryHome.js" />" > </script>


</head>
<body>
<jsp:include page="navbar.jsp"/>
<div  class="container">
	 
    <div class="custyle">
	
	<h2>DeliveryForce</h2>
	
	<br>
	
	<h3>Details for order-id #${deliveryDetail.getOrderId().getOrderId()}</h3>

<table class="table table-striped custab">
    
    <tr class="text-center">   
    	<td>Order Status</td>
    	<td>${deliveryDetail.getOrderId().getStatus() }</td>
    </tr>
    
    <tr class="text-center">	
    	<td>Customer Name</td>
    	<td>${deliveryDetail.getOrderId().getRecipientName() }</td>
    </tr>
    
    <tr class="text-center">	
    	<td>Customer Address</td>
    	<td>${deliveryDetail.getOrderId().getAddress() }</td>
    </tr>
    
    <tr class="text-center">	
    	<td>Customer Contact</td>
    	<td>${deliveryDetail.getOrderId().getRecipientPhone() }</td>
    </tr>
    
    <!-- 
    <tr class="text-center">	
    	<td>Product</td>
    	<td>NA</td>
    </tr>
    -->
    
    <tr class="text-center">	
    	<td>Assigned Delivery Executive Id</td>
    	<td>${deliveryDetail.getDeliveryExecutiveId().getId() }</td>
    </tr>
    
    <tr class="text-center">	
    	<td>Assigned Delivery Executive Name</td>
    	<td>${deliveryDetail.getDeliveryExecutiveId().getFirstName() }</td>
    </tr>
    
    <tr class="text-center">	
    	<td>Assigned Delivery Executive Contact</td>
    	<td>${deliveryDetail.getDeliveryExecutiveId().getPhoneNumber() }</td>   
    </tr> 
            
</table>

</div>
</div>    

</body>
</html>