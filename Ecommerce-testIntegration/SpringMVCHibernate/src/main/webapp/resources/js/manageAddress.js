$(document).ready(function(){

	$('#example').DataTable({
		"paging": true,
		"ordering": false
	});
	
	$('.displayPdts').DataTable({
		"paging": false,
		"ordering": true,
		"info": false
	});
	
	$(".setDefault").click(function(event){
		event.preventDefault();		
		var temp = $(this).attr('data-id');		
		$.ajax({
			url: 'setDefaultAddress',
			type: "POST",
			dataType: "text",
			contentType: "application/json",
			data: JSON.stringify(temp),
			success: function(result){
				$(".setDefault").removeClass("disabled");
				$(".deleteAdd").removeClass("disabled");
				$("[data-id="+temp+"]").addClass("disabled");
			}
		});
	});
	
	$(".deleteAdd").click(function(event){
		event.preventDefault();
		var temp = $(this).attr('data-id');		
		$.ajax({
			url: 'deleteAddress',
			type: "POST",
			dataType: "text",
			contentType: "application/json",
			data: JSON.stringify(temp),
			success: function(result){
				$("[data-rem="+temp+"]").remove();
			}
		});
	});
	
});