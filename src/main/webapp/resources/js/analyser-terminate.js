//01/23/2019 Tayyab
//var oldTermDate = $("#analyserTerminationForm\\:terminateDate").val();
//console.log("oldTermDate = "+oldTermDate);

var oldTermDate = "";
var oldTermDateValue;

function getOldTermDateValue()
{
	oldTermDate = $("#analyserTerminationForm\\:terminateDate").val();	//01/23/2019 Tayyab
	console.log("oldTermDate = "+oldTermDate);
	oldTermDateValue = new Date(oldTermDate);
	oldTermDateValue.setHours(0, 0, 0, 0);
	console.log("oldTermDateValue.toLocaleString() = "+oldTermDateValue.toLocaleString());
}

function submitForm() 
{
	var msg = "";
	if ($("#analyserTerminationForm\\:terminateDate").val() == ""
			|| $("#analyserTerminationForm\\:terminateDate").val().length != 10) 
	{
		alert("Please select a valid Termination Date before terminating an Analyzer.");
		return;
	}
	
	//01/23/2019 Tayyab
	var termDate = new Date($("#analyserTerminationForm\\:terminateDate").val());	//current term date
	//var varStartDate = $("#analyserTerminationForm\\:oldTerminateDate").val();	//actually start date
	var varStartDate = $("#analyserTerminationForm\\:startDate").val();	//actually start date
	var startDateValue = new Date(varStartDate);
	var currentDate = new Date();
	var allowedCurrentDate = new Date(currentDate.getTime() - (10 * 24 * 60 * 60 * 1000));
	var allowedOldTermDateValue = new Date(oldTermDateValue.getTime() - (10 * 24 * 60 * 60 * 1000));
	var companyCode = $("#analyserTerminationForm\\:companyCode").val();
	
	termDate.setHours(0, 0, 0, 0);
	currentDate.setHours(0, 0, 0, 0);
	allowedCurrentDate.setHours(0, 0, 0, 0);
	startDateValue.setHours(0, 0, 0, 0);
	allowedOldTermDateValue.setHours(0, 0, 0, 0);
	
	console.log("In-Submit Form: oldTermDateValue.toLocaleString() = "+oldTermDateValue.toLocaleString());
	console.log("In-Submit Form: varStartDate = "+varStartDate);
	console.log("In-Submit Form: NEW termDate.toLocaleString() = "+termDate.toLocaleString());
	console.log("In-Submit Form: currentDate.toLocaleString() = "+currentDate.toLocaleString());
	console.log("In-Submit Form: allowedCurrentDate.toLocaleString() = "+allowedCurrentDate.toLocaleString());
	console.log("In-Submit Form: startDateValue.toLocaleString() = "+startDateValue.toLocaleString());
	console.log("In-Submit Form: allowedOldTermDateValue.toLocaleString() = "+allowedOldTermDateValue.toLocaleString());
	
	console.log("Analyzer Termination Page companyCode = "+companyCode);
	
	if (termDate.getTime() < startDateValue.getTime())
	{
		alert("Terminate Date cannot be earlier than Start Date (Start Date = " + startDateValue.toLocaleString() + ")");
		$("#analyserTerminationForm\\:terminateDate").focus();
		$("#analyserTerminationForm\\:terminateDate").select();
		return;
	}
	//For first time termination, when there is no terminate date
	if (oldTermDate == null || oldTermDate == "")
	{
		if (termDate.getTime() < allowedCurrentDate.getTime() && companyCode != "Signature")
		{
			alert("Terminate Date cannot be earlier than 10 days from Current Date (Current Date=" + currentDate.toLocaleString() + ", allowed Terminate Date = " + allowedCurrentDate.toLocaleString() + ")");
			$("#analyserTerminationForm\\:terminateDate").focus();
			$("#analyserTerminationForm\\:terminateDate").select();
			return;
		}
	}
	else if (termDate.getTime() < allowedOldTermDateValue.getTime() && companyCode != "Signature") 
	{
		alert("Terminate Date cannot be earlier than last/rejected Terminate date (minus 10 days) (Older Terminate Date=" + oldTermDateValue.toLocaleString() + ", allowed Terminate Date = " + allowedOldTermDateValue.toLocaleString() + ")");
		$("#analyserTerminationForm\\:terminateDate").focus();
		$("#analyserTerminationForm\\:terminateDate").select();
		return;
	}

	if ($("#analyserTerminationForm\\:reason").val() == null || $("#analyserTerminationForm\\:reason").val() == "") {
		alert("Please select a reason before terminating an Analyzer.");
		return;
	}
	
	if ($("#analyserTerminationForm\\:eligibleForReHire")[0].checked == true) {
		msg = msg + "You have selected candidate as Eligible For Re-Hire.\n";
	}
	if ($("#analyserTerminationForm\\:dentalInsurance")[0].checked == true) {
		msg = msg + "You have selected Dental Insurance for the candidate.\n";
	}
	
	if ($("#analyserTerminationForm\\:falseTermination")[0].checked == true) {
		msg = msg + "You have selected False termination for the candidate.\n";
	}

//	if ($("#analyserTerminationForm\\:healthInsurance")[0].checked == true) {
//		msg = msg
//				+ "Candidate has the Health Insurance, please take the appropriate action.\n";
//	}
//	if ($("#analyserTerminationForm\\:chk401K")[0].checked == true) {
//		msg = msg
//				+ "Candidate is enrolled in the 401K Savings Plan, please take the appropriate action.\n";
//	}

	if (confirm(msg + "\nAre you sure to Terminate Analyzer with these Options?")) 
	{
		$("#analyserTerminationForm\\:submitType").val("terminate");
		var showUpdateButton = $('#analyserTerminationForm\\:showUpdateButton').val();
	    if (showUpdateButton == "false") 
	    {
	    	$("#analyserTerminationForm\\:actualSaveButton").click();
		}
	    else
	    {
			$("#analyserTerminationForm\\:actualUpdateButton").click();
		}
	} 
	else
	{
		return;
	}
}


function LTrim(str)
{
	var whitespace = new String(" \t\n\r");

	var s = new String(str);

	if (whitespace.indexOf(s.charAt(0)) != -1) {
		// We have a string with leading blank(s)...

		var j = 0, i = s.length;

		// Iterate from the far left of string until we
		// don't have any more whitespace...
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
			j++;

		// Get the substring from the first non-whitespace
		// character to the end of the string...
		s = s.substring(j, i);
	}
	return s;
}

function RTrim(str)
{
	// We don't want to trip JUST spaces, but also tabs,
	// line feeds, etc.  Add anything else you want to
	// "trim" here in Whitespace
	var whitespace = new String(" \t\n\r");

	var s = new String(str);

	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		// We have a string with trailing blank(s)...

		var i = s.length - 1; // Get length of string

		// Iterate from the far right of string until we
		// don't have any more whitespace...
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
			i--;

		// Get the substring from the front of the string to
		// where the last non-whitespace character is...
		s = s.substring(0, i + 1);
	}

	return s;
}

function Trim(str)
{
	return RTrim(LTrim(str));
}
