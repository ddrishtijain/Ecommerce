

function checkPasswords(){

	var pass = document.getElementById("psw");	
	var confirmPass = document.getElementById("confirmpsw");		
	if(pass.value === confirmPass.value){
		return true;
	}
//	confirmPass.setCustomValidity("Passwords do not match");
	return false;
}

function validatePassword(){
	var pass = document.getElementById("psw");	
	var confirmPass = document.getElementById("confirmpsw");
	if(pass.value != confirmPass.value){
		confirmPass.setCustomValidity("Passwords do not match");
	}
	else{
		confirmPass.setCustomValidity("");
	}
}

$(document).ready(function(){
	var temp = $("#role").val();
	if(temp=="customer"){

		$("#addressForm > input").attr("required",true);		
		$("#addressForm").show();
		$("#noDeliveryExec").show();
		
	}
	else if(temp=="delivery"){
		$("#addressForm > input").attr("required",false);
		$("#zipcode").attr("required",true);
		$("#addressForm").show();
		$("#noDeliveryExec").hide();
	}
	else{
		$("#addressForm > input").attr("required",false);
		$("#zipcode").attr("required",false);
		$("#addressForm").hide();
		$("#noDeliveryExec").hide();
	}
	$("#role").on("change",function(){
		var temp = $(this).val();
		if(temp=="customer"){
			$("#addressForm > input").attr("required",true);		
			$("#addressForm").show();
			$("#noDeliveryExec").show();
			
		}
		else if(temp=="delivery"){
			$("#addressForm > input").attr("required",false);
			$("#zipcode").attr("required",true);
			$("#addressForm").show();
			$("#noDeliveryExec").hide();
		}
		else{
			$("#addressForm > input").attr("required",false);
			$("#zipcode").attr("required",false);
			$("#addressForm").hide();
			$("#noDeliveryExec").hide();
		}
	})
});