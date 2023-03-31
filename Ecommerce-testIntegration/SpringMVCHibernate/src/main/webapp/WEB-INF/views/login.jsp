<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
<title>Login</title>
<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<link href="${bootstrapcss}" rel="stylesheet" /> 
</head>



<body>
<jsp:include page="navbar.jsp"/>
<div  class="container">

<div style="height:70vh;" class="row align-items-center">
	<div class="col"></div>
	<div class="col">
		<h2 style="text-align:center" >Login</h2>
		<form action="login" method="POST">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input value="${email}" name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
		    
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Password</label>
		    <input value="${password}" name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required>
		  </div>
		  
		  <button type="submit" class="btn btn-primary">Submit</button> <a class="btn btn-primary" href="${pageContext.request.contextPath}/signup" role="button">Signup</a>
		  	<div style="margin-top:10px" class="alert-danger" role="alert">
  				${errorMsg}
			</div>
		</form>
	</div>
	<div class="col"></div>
</div>	

</div>
</body>
</html>