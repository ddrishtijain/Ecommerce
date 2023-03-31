<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add To Cart</title>
</head>

<script>
	function added(){
		alert("Item successfully added to your cart!");
	}
</script>
<div class="container">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	<div class="row">
		<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<h2 class="page-header" align="center">Please fill the Product details</h2>
		</div>
	</div>
	
	<div class="row">
		<div class="col-6">
			<form:form action="addtocart" modelAttribute="Cart" method="POST"
				cssClass="form-horizontal" role="form">
			
			<div class="form-group row">
			<Label for="productId" class="col-6 col-form-label">Product Id </Label>
			<div class="col-6">
				<form:input path="product.id" size="30" cssClass="form-control"/>
			</div>
			</div>
			
			<div class="form-group row">
			<Label for="userId" class="col-6 col-form-label">User Id </Label>
			<div class="col-6">
				<form:input path="userId" size="30" cssClass="form-control"/>
			</div>
			</div>
			
			<div class="form-group row">
			<Label for="quantity" class="col-6 col-form-label">Quantity</Label>
			<div class="col-6">
				<form:input path="qtyAdded" size="30" cssClass="form-control"/>
			</div>
			</div>
				
				<div class="form-group row">
				<div class="offset-2 col-4">
				<div style="text-align:center"><h3><a href="viewcart" class="btn btn-primary">View Cart</a></h3></div>
				</div>
				<div class="col-4">

					<input type="submit" onClick="added()"value="Add To Cart" class="btn btn-primary" name="btnSubmit"/>
					
				</div>
				</div>
			</form:form>
		</div>
	</div>
	
</div>
</body>
</html>