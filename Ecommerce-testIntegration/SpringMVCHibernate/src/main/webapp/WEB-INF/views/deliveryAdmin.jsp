<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delivery Admin Page</title>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>


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
<div   class="container">
	 
    <div class="custyle">
    
    <br>
    
    <h2>DeliveryForce</h2>
    <h4>Manage all your deliveries here!</h4>
    
    <br><br>
    
    <div style="padding-top:4cm;" class="container-fluid">
	  <div class="row">
	   <div class="col-md-6 col-sm-6 col-xs-12">
	
	    <!-- Form code begins -->
	    <form action="" method="GET">
	      <div class="form-group"> <!-- Date input -->
	        <label class="control-label" for="date">Pick a date to see assigned deliveries</label>
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
    
	    <c:if test = "${date != null}">
	    	<h3>Deliveries on ${date}</h3>
	    </c:if>
	    
	    <c:if test = "${date == null}">
	    	<h3>Orders to be delivered today!</h3>
	    </c:if>
    
    
    <h5>Orders Completed: ${ordersCompleted}</h5>
    <h5>Orders Pending: ${ordersPending}</h5>  
    
    <br>
    
    <table class="table table-striped custab">
    
    <thead>
        <tr>
        	<th class="text-center">Order Id</th>
            <th class="text-center">Assigned Delivery Agent Id</th>
            <th class="text-center">Name</th>
            <th class="text-center">Contact</th>
            <th class="text-center">Status</th>
            <th class="text-center">Details</th>
        </tr>
    </thead>
    
    <c:forEach items="${allDeliveries}" var="d">
            
            <tr class="text-center">
            	<td>${d.getOrderId().getOrderId()}</td>
                <td>${d.getDeliveryExecutiveId().getId()}</td>
                <td>${d.getDeliveryExecutiveId().getFirstName()}</td>
                <td>${d.getDeliveryExecutiveId().getPhoneNumber()}</td>              
                
                <td >
                <div class="dropdown">
				  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">${d.getOrderId().getStatus()}
				  <span class="caret"></span></button>
				  <ul class="dropdown-menu">
				  
				  	<c:if test = "${date != null}">
				  	
				    <li class="dropdown-item" ><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/shipped?date=${date}">Shipped</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/aas?date=${date}">Arrived at Salesforce E-commerce</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/lfs?date=${date}">Left from Salesforce E-commerce</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/fds?date=${date}">Arrived at final delivery station</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/ofd?date=${date}">Out for delivery</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/delivered?date=${date}">Delivered</a></li>
				    
				    </c:if>
				    
				    <c:if test = "${date == null}">
				  	
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/shipped">Shipped</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/aas">Arrived at Salesforce E-commerce</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/lfs">Left from Salesforce E-commerce</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/fds">Arrived at final delivery station</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/ofd">Out for delivery</a></li>
				    <li class="dropdown-item"><a href="deliveryAdmin/updateStatus/${d.getOrderId().getOrderId()}/delivered">Delivered</a></li>
				    
				    </c:if>
				    
				  </ul>
				</div>
                </td>
                
	            <td class="text-center">
	                <a class='btn btn-info btn-xs' href="deliveryAdmin/viewOrderInfo/${d.getId()}">
	                	<span class="glyphicon glyphicon-info-sign"></span> View Info</a>
	            </td>
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