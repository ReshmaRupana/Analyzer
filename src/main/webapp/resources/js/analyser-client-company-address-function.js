var screen = "Add";
function submitForm() {
	if ($("#analyserClientAddressForm\\:state").val() == "") {
		alert("State needs to be Filled in");
		$("#analyserClientAddressForm\\:state").focus();
		return;
	}
	$("#analyserClientAddressForm\\:actualSaveButton").click();
}

function disableDropDownSelect(elem){
    var e = '#analyserClientAddressForm\\:'+elem;
    $(e).select2().on('select2:selecting',
        function(e) {
            e.preventDefault();
            $(this).select2('close');
        }
    );
    $(e).select2('enable',false);
}

function disableFields(){
	getAction();
	if(screen == "Add"){
		disableDropDownSelect('clientCompany');
	}
}

function getAction() {
    screen = $("#analyserClientAddressForm\\:action").val();
}
