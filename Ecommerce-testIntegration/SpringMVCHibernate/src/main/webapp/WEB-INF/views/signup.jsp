<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
<title>Signup</title>

<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" /> 
<spring:url value="/resources/js/checkPasswords.js" var="checkPasswordsjs" />
<script src="${checkPasswordsjs}"></script>
</head>

<jsp:include page="navbar.jsp"/>

<body>

<div class="container">

	<div>
		
		<form action="signup" method="POST" onsubmit="return checkPasswords();">
		  <div class="form-row">
		  	<div class="col-3"></div>
		  	<div style="margin-bottom:20px;" class="col-6"><h2  >Signup</h2></div>
		  	<div class="col-3"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
		      		<label for="firstName">First Name</label>
		      		<input value="${firstName}" id="firstName" name="firstName" type="text" class="form-control" placeholder="First name" required>
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="lastName">Last Name</label>
		      		<input value="${lastName}" name="lastName" type="text" class="form-control" placeholder="Last name" required>
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col-3"></div>
		  	<div class="col">
		  		<div class="form-group">
		      		<label for="email">Email</label>
		      		<input id="email" name="email" type="email" class="form-control" placeholder="Email" required>
		   		</div>
		  	</div>
		  	<div class="col-3"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
		      		<label for="psw">Password</label>
		      		<input id="psw" name="psw" type="password" class="form-control" placeholder="Password" required>
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="confirmpsw">Confirm Password</label>
		      		<input id="confirmpsw" name="psw-repeat" type="password" oninput="validatePassword()" class="form-control" placeholder="Confirm Password" required>
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
					<label for="role">Role</label>
      				<select name="role" id="role" class="form-control">
        				<option value="customer" selected="${customer}" >Customer</option>
        				<option value="manager" selected="${manager}">Manager/Vendor</option>
        				<option value="delivery" selected="${delivery}">Delivery Executive</option>
        				<option value="da" selected="${da}">Delivery Admin</option>
     				</select>
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="phone">Mobile</label>
		      		<input value="${phone}" id="phone" name="phone" type="tel" class="form-control" placeholder="Mobile" pattern="[0-9]{10}" required>
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>

		  
		  <div id="noDeliveryExec">
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
		      		<label for="recipientName">Recipient Name</label>
		      		<input value="${recipientName}" id="recipientName" name="recipientName" type="text" class="form-control" placeholder="Recipient name">
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="recipientPhone">Recipient Mobile</label>
		      		<input value="${recipientPhone}" name="recipientPhone" type="tel" pattern="[0-9]{10}" class="form-control" placeholder="Recipient Mobile">
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>
		  </div>
		  
		  <div id="addressForm">
		  
		  <div class="form-row">
		  	<div class="col-3"></div>
		  	<div class="col">
		  		<div class="form-group">
		      		<label for="address">Address</label>
		      		<input value="${address}" id="address" name="address" type="text" class="form-control" placeholder="Address" >
		   		</div>
		  	</div>
		  	<div class="col-3"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
		      		<label for="city">City</label>
		      		<input value="${city}" id="city" name="city" type="text" class="form-control" placeholder="City" >
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="state">State</label>
		      		<input value="${state}" name="state" type="text" class="form-control" placeholder="State" >
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>
		  <div class="form-row">
		  	<div class="col"></div>
		    <div class="col">
		      	<div class="form-group">
		      		<label for="country">Country</label>
		      		<input value="${country}" id="country" name="country" type="text" class="form-control" placeholder="Country" >
		   		</div>
		    </div>
		    <div class="col">
		    	<div class="form-group">
		    		<label for="zipcode">Zipcode</label>
		      		<input id="zipcode" value="${zipcode}" name="zipcode" type="text" class="form-control" placeholder="Zipcode" pattern="[0-9]{6}" required>
		      	</div>
		    </div>
		    <div class="col"></div>
		  </div>
		  </div>
		  <div class="form-row">
		  		<div class="col"></div>
		  		<div class="col">
		  			<button type="submit" class="btn btn-primary">SIGNUP</button>
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