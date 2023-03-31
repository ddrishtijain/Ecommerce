
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" />
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="container">

	<div style="padding:1cm;">
	
		<form action="addAddress" method="POST">
		<div class="form-row">
			  	<div class="col"></div>
			    <div class="col">
			    	<div class="form-group">
			    		<label for="state">Recipient Name</label>
			      		<input value="${recipientName}" name="recipientName" type="text" class="form-control" placeholder="Recipient Name" >
			      	</div>
			    </div>
			    
			    <div class="col">
			    	<div class="form-group">
			    		<label for="state">Recipient Phone</label>
			      		<input value="${recipientPhone}" name="recipientPhone" type="tel" pattern="[0-9]{10}" class="form-control" placeholder="Recipient Phone" >
			      	</div>
			    </div>
			    <div class="col"></div>
		  </div>
		<div class="form-row">
			<div class="col-3"></div>
			  	<div class="col">
			  		<div class="form-group">
			      		<label for="address">Address</label>
			      		<input value="${address}" id="address" name="address" type="text" class="form-control" placeholder="Address" required>
			   		</div>
			  	</div>
			  	<div class="col-3"></div>
		  </div>
		  <div class="form-row">
			  	<div class="col"></div>
			    <div class="col">
			      	<div class="form-group">
			      		<label for="city">City</label>
			      		<input value="${city}" id="city" name="city" type="text" class="form-control" placeholder="City" required>
			   		</div>
			    </div>
			    <div class="col">
			    	<div class="form-group">
			    		<label for="state">State</label>
			      		<input value="${state}" name="state" type="text" class="form-control" placeholder="State" required>
			      	</div>
			    </div>
			    <div class="col"></div>
		  </div>
		  <div class="form-row">
			  	<div class="col"></div>
			    <div class="col">
			      	<div class="form-group">
			      		<label for="country">Country</label>
			      		<input value="${country}" id="country" name="country" type="text" class="form-control" placeholder="Country" required>
			   		</div>
			    </div>
			    <div class="col">
			    	<div class="form-group">
			    		<label for="zipcode">Zipcode</label>
			      		<input value="${zipcode}" name="zipcode" type="text" class="form-control" placeholder="Zipcode" pattern="[0-9]{6}" required>
			      	</div>
			    </div>
	    			    			  

			    <div class="col"></div>
		  </div>
		  <div class="form-row">
		  		<div class="col"></div>
		  		<div class="col">
		  			<button type="submit" class="btn btn-primary">Add Address</button>
		  			<div style="margin-top:10px" class="alert-danger" role="alert">
  						${errorMsg}
					</div>
		  		</div>
		  		<div class="col"></div>
		  		<div class="col"></div>
		  </div>		  		
		</form>
	
	</div>

</div>

</body>

</html>