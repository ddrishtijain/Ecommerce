<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deliveries for today</title>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<!-- 
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
 -->
 
<!-- Bootstrap Date-Picker Plugin -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/deliveryHome.css" />"/>
<script type="text/javascript" src="<c:url value="/resources/js/deliveryHome.js" />" > </script>

<script>
    $(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>

</head>


<body>
<jsp:include page="navbar.jsp"/>

<div  class="container">

    
    <div class="custyle">
    
    <h2>DeliveryForce</h2>
    
    <c:if test = "${totalOrders != 0}">
    	<h3>Hi, ${deliveryDetails.get(0).getDeliveryExecutiveId().getFirstName()}</h3>
    </c:if>
    
    <c:if test = "${totalOrders == 0}">
    	<h3>Hi, ${deliveryExeName}</h3>
    </c:if>
    
    </br>
    
    
    <div style="padding-top:4cm;" class="container-fluid">
	  <div class="row">
	   <div class="col-md-6 col-sm-6 col-xs-12">
	
	    <!-- Form code begins -->
	    <form action="" method="GET">
	      <div class="form-group"> <!-- Date input -->
	        <label class="control-label" for="date">Pick a date to see your delivery history or assigned orders</label>
	        <input class="form-control" id="date" name="date" placeholder="YYYY/MM/DD" type="text" required/>
	      </div>
	      <div class="form-group"> <!-- Submit button -->
	        <button class="btn btn-primary " type="submit">Submit</button>
	      </div>
	     </form>
	     <!-- Form code ends --> 
	
	    </div>
	  </div>    
	 </div>
    
    
    <c:if test = "${totalOrders != 0}">
    
    	<c:if test = "${date == null}">
    		<h4>Here are your deliveries for today!</h4>
    	</c:if>
    	
    	<c:if test = "${date != null}">
    		<h4>Here are your deliveries on ${date}!</h4>
    	</c:if>
    
    </br>
    
    <h5>Orders Completed: ${ordersCompleted}</h5>
    <h5>Orders Pending: ${ordersPending}</h5>  
    
    
    <table class="table table-striped custab">
    <thead>
        <tr>
            <th class="text-center">Order ID</th>
            <th class="text-center">Customer Name</th>
            <th class="text-center">Customer Address</th>
            <th class="text-center">Customer Contact</th>
            <th class="text-center">Amount</th>
            <th class="text-center">Status</th>
            <th class="text-center">Update Status</th>
            
        </tr>
    </thead>
    
    <c:forEach items="${deliveryDetails}" var="d">
            
            <tr class="text-center">
                <td>${d.getOrderId().getOrderId()}</td>
                <td>${d.getOrderId().getRecipientName()}</td>
                <td>${d.getOrderId().getAddress()}</td>
                <td>${d.getOrderId().getRecipientPhone()}</td>
                <td>${d.getOrderId().getTotalAmount()}</td>
                <td>${d.getOrderId().getStatus()}</td>
                
                
                <c:set var = "statusDelivery" scope = "session" value = "Out for delivery"/>
                <c:if test = "${d.getOrderId().getStatus().equals(statusDelivery)}">
	                <td class="text-center">
	                	
	                	<c:if test = "${date != null}">
	                		<a class='btn btn-info btn-xs' href="${d.getDeliveryExecutiveId().getId()}/updateStatus/${d.getOrderId().getOrderId()}/delivered?date=${date}">
	                	</c:if>
	                	
	                	<c:if test = "${date == null}">
	                		<a class='btn btn-info btn-xs' href="${d.getDeliveryExecutiveId().getId()}/updateStatus/${d.getOrderId().getOrderId()}/delivered">
	                	</c:if>
	                	
	                		<span class="glyphicon glyphicon-edit"></span> Mark Deliver</a>
	                </td>
                </c:if>
                
                <c:set var = "statusDelivered" scope = "session" value = "Delivered"/>
                <c:if test = "${d.getOrderId().getStatus().equals(statusDelivered)}">
	                <td class="text-center">
	                	<a class='btn btn-info btn-xs' >
	                		 Delivered!</a>
	                </td>
                </c:if>
                
                <c:if test = "${d.getOrderId().getStatus().compareTo(statusDelivery) != 0 && d.getOrderId().getStatus().compareTo(statusDelivered) != 0}">
	                <td class="text-center">
	                	<a class='btn btn-info btn-xs' >
	                		 Nothing to do!</a>
	                </td>
                </c:if>
                
                
            </tr>
    
    </c:forEach>
            
    </table>
    
    </c:if>
    
    <c:if test = "${totalOrders == 0}">
    	
    	<c:if test = "${date == null}">
    		<h3>No deliveries for today!</h3>
    	</c:if>
    	
    	<c:if test = "${date != null}">
    		<h3>No deliveries on ${date}!</h3>
    	</c:if>
    	
    </c:if>
    
    </div>
</div>


</body>
</html>