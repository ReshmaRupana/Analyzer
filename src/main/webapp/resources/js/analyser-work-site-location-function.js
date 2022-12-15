function submitForm() {
	
	if ($("#analyserWorkSiteLocationForm\\:state").val() == "") {
		alert("State needs to be Filled in");
		$("#analyserWorkSiteLocationForm\\:state").focus();
		return;
	}
	
	if ($("#analyserWorkSiteLocationForm\\:managerLastName").val() == "") {
		alert("Please Enter Manager Last Name");
		$("#analyserWorkSiteLocationForm\\:managerLastName").focus();
		return;
	}
	
	if ($("#analyserWorkSiteLocationForm\\:managerFirstName").val() == "") {
		alert("Please Enter Manager First Name");
		$("#analyserWorkSiteLocationForm\\:managerFirstName").focus();
		return;
	}
	
	if ($("#analyserWorkSiteLocationForm\\:address1").val() == "") {
		alert("Please Enter Address 1");
		$("#analyserWorkSiteLocationForm\\:address1").focus();
		return;
	}
	
	if ($("#analyserWorkSiteLocationForm\\:city").val() == "") {
		alert("Please Enter City");
		$("#analyserWorkSiteLocationForm\\:city").focus();
		return;
	}
	
	const email =  $("#analyserWorkSiteLocationForm\\:email").val();
	if ($.trim(email).length > 0) {
		if(!validateEmail(email)){
			alert('Email is not valid');
			$("#analyserWorkSiteLocationForm\\:email").focus();
			return;
		}
	}
	else{
		alert('Please enter email');
		$("#analyserWorkSiteLocationForm\\:email").focus();
		return;
	}
	
	const zipCode =  $("#analyserWorkSiteLocationForm\\:zipCode").val();
	const country =  $("#analyserWorkSiteLocationForm\\:country").val();

	if ($.trim(zipCode).length <= 0) 
	{
		alert('Please enter Zip Code');
		$("#analyserWorkSiteLocationForm\\:zipCode").focus();
		return;
	}

	if (country == "USA")
	{
		if ($.trim(zipCode).length > 0) 
		{
			var newStr = zipCode.substring(0, 5);
			if(!validateZipCode(newStr)){
				alert('Zip Code is not valid');
				$("#analyserWorkSiteLocationForm\\:zipCode").focus();
				return;
			}
		}
	}
	
	$("#analyserWorkSiteLocationForm\\:actualSaveButton").click();
}

function disableDropDownSelect(elem) {
	var e = '#analyserWorkSiteLocationForm\\:' + elem;
	$(e).select2().on('select2:selecting', function(e) {
		e.preventDefault();
		$(this).select2('close');
	});
	$(e).select2('enable', false);
}

function disableFields() {
	var action = $("#analyserWorkSiteLocationForm\\:action").val();
	console.log("Action is : "+action);
	if(action == ""){
		action = "Add";
	}
	if(action == "Add"){
		disableDropDownSelect('clientCompany');
	}else{
		disableDropDownSelect('clientCompany');
		disableDropDownSelect('state');
		disableDropDownSelect('country');
	}
}

function validateEmail(email) {
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (filter.test(email)) {
		return true;
	}
	else{
		return false;
	}
}

//console.log(validateZipCode("03201-2150"));
//console.log(validateZipCode("78925"));
function validateZipCode(str)
{
 regexp = /^[0-9]{5}(?:-[0-9]{4})?$/;
  
        if (regexp.test(str))
          {
            return true;
          }
        else
          {
            return false;
          }
}