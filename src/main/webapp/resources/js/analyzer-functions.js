//Modified 01/07/2022 Tayyab
function getInitialValues() 
{
	gAndAGlobal = 0.0599;	//04/03/2019
    gAndAGlobalServices = 0.0599;	//04/03/2019
    
	fringeExpenseRateGlobal = 0.13; //04/03/2019
    fringeExpenseRateGlobal1099 = 0.015; //04/03/2019
    fringeExpenseRateGlobalPYS = 0.15; //06/03/2020
    globalEmployeeClassForAdditionalFringe = new Array("PYS", "PUB");	//10/15/2020
    //01/07/2022 Signature Changes
    globalFringeExpenseRateForSignatureW2 = 0.14;		//There is No PYS/PUB Employee class for Signature
    globalFringeExpenseRateForSignatureW2AndH1 = 0.24;	//W2 & H1
    globalFringeExpenseRateForSignature1099 = 0.05;
    
	minHourlyWageForCAGlobal = 41.85;
    minAnnualWageForCAGlobal = 87185.00;
    WAStateTaxRateGlobal = 0.015;
    TXStateTaxRateGlobal = 0.01;
    varGrossProfitGlobal = 0.0;// K12
    minimumSalaryForESEmployee = 35568.0;	//12/11/2019
    globalFAVertical = "Finance and Accounting";	//01/30/2020
    globalFullTimeEmpCategory = "IT";	//01/30/2020
    globalHolidayEligibleCustomerList = new Array(	
    	"502601", "503408", "503409", "503410", "503413", "503417", "503419", "503421", "503424", "503425", "503486", "503487", 
    	"503494", "503499", "503694", "503711", "503833", "503946", "503950", "504094", "504205", "504496", "504652", "504676",
    	"504706", "504722", "504771", "504796", "504852", "504896", "504897", "504898", "504899", "504900", "504901", "504917", 
    	"504954", "504955", "504956", "504976", "505032", "505033", "505034", "505088",	"505175", "505785" 
    	);
         
    getAction();
    if (screen == "Modify") 
    {
    	console.log("getInitialValues --> OLD Profit = varProfitValue = "+varProfitValue);
        varProfitValue = $("#analyserForm\\:varProfitValue").val();
        console.log("getInitialValues --> FORM analyserForm-varProfitValue).val() = "+$("#analyserForm\\:varProfitValue").val());
        console.log("getInitialValues --> RESETTING varProfitValue Value");
        console.log("getInitialValues --> NEW Profit = varProfitValue = "+varProfitValue);
    }

    wsstate = $("#analyserForm\\:wstate").val();
    varCommStartDate = $("#analyserForm\\:varCommStartDate").val();
    // varEffectiveDate = "<%=effectiveDate%>";
    varEffectiveDate = $("#analyserForm\\:varEffectiveDate").val();
    varOldStartDate = $("#analyserForm\\:varOldStartDate").val();

    isRehired = $("#analyserForm\\:isRehired").val();
    console.log('***** is rehired value :: ', isRehired)
    isFalseTerminated = $("#analyserForm\\:isFalseTerminated").val();

    wszipcode = $("#analyserForm\\:siteZipCode").val();

    firstAEValue = $("#analyserForm\\:commName1").val();
    firstRecValue = $("#analyserForm\\:commName2").val();
    firstAE2Value = $("#analyserForm\\:commName3").val();
    firstRec2Value = $("#analyserForm\\:commName4").val();
    
    firstAEOrphan = $("#analyserForm\\:execOrphan").is(':checked');
    firstRecOrphan = $("#analyserForm\\:recruiterOrphan").is(':checked');
    firstAE2Orphan = $("#analyserForm\\:other1Orphan").is(':checked');
    firstRec2Orphan = $("#analyserForm\\:other2Orphan").is(':checked');
    
    $("#analyserForm\\:execOrphan").prop("disabled", true);
    $("#analyserForm\\:recruiterOrphan").prop("disabled", true);
    $("#analyserForm\\:other1Orphan").prop("disabled", true);
    $("#analyserForm\\:other2Orphan").prop("disabled", true);
    if (screen == "Modify" && isRehired > 0) 
    {
    	$("#analyserForm\\:execOrphan").prop("disabled", false);
        $("#analyserForm\\:recruiterOrphan").prop("disabled", false);
        $("#analyserForm\\:other1Orphan").prop("disabled", false);
        $("#analyserForm\\:other2Orphan").prop("disabled", false);
    }
    
    /*
     *	//01/07/2022 COM, GS-Practice, GS-Delivery, GS-BDE, and GS-Proposal fields removed 

    enableDisableComBox();
    
    //12/31/2019 These roles are not required anymore
    $("#analyserForm\\:commName6").attr("readonly", "readonly");
    $("#analyserForm\\:commName7").attr("readonly", "readonly");
    $("#analyserForm\\:commName8").attr("readonly", "readonly");
    $("#analyserForm\\:commName9").attr("readonly", "readonly");
    */
    
    globalCompanyCode = $("#analyserForm\\:companyCode").val();
    console.log("getInitialValues --> globalCompanyCode = "+globalCompanyCode);
    globalSigDupCommPersonsList = new Array(	
    		"Alissa Deuso",			"Jennifer Eaton",		"Stephanie Ferretti",		"Cassie Hale",		"Matthew Hebb",
    		"Whitney Hughes",		"Robert Parker",		"Kristen Tambunga", 		"Disys Orphan"
        	);
    globalDISYSDupCommPersonsList = new Array(	
    		"Conor Alwell",			"Helen Park",			"Melissa Luftman",		"Steve Turner"
        	);
    
    //10/25/2022
	if ($("#analyserForm\\:workFromSource option:selected").val() != 'Hybrid')
	{
		console.log("workFromSource if");
		$("#analyserForm\\:majorityWorkPerformed").attr("readonly",true);
	}else if($("#analyserForm\\:workFromSource option:selected").val() == 'Hybrid'){
		console.log("workFromSource else");
		 $("#analyserForm\\:majorityWorkPerformed").removeAttr("readonly");
	}
}
function validateMajorityWorkPerformed(){
	  if($("#analyserForm\\:workFromSource option:selected").val() != 'Hybrid') 
     {
		  $('#analyserForm\\:majorityWorkPerformed').val('').trigger("change");
	      $("#analyserForm\\:majorityWorkPerformed").attr("readonly",true);   
     }else{
    	 $("#analyserForm\\:majorityWorkPerformed").removeAttr("readonly");
     }
}

function enableDisableComBox() 
{
	/*	//01/07/2022 COM field Retired
 
	//var comValue = $("#analyserForm\\:com").val();
	var comStatus = $("#analyserForm\\:com").is(':checked');
	var vertical = $("#analyserForm\\:vertical option:selected").val();
	console.log("comStatus = "+comStatus);
	console.log("Vertical = "+vertical);
	//if(vertical != null && vertical.trim() != "" && vertical == 'Finance and Accounting')	//01/30/2020
	if(vertical != null && vertical.trim() != "" && vertical == globalFAVertical)
	{
		$("#analyserForm\\:com").prop("disabled", false);
		$("#comDiv > div.icheckbox_flat-blue" ).removeClass( "disabled" );
		if(comStatus)
		{
			$("#analyserForm\\:com").prop("checked", true);
			//$("#analyserForm\\:com").is(':checked') == true;
		}
	}
	else
	{
		$("#analyserForm\\:com").prop("checked", false);
		$("#analyserForm\\:com").prop("disabled", true);
		$("#comDiv > div.icheckbox_flat-blue" ).removeClass( "checked" );
		$("#comDiv > div.icheckbox_flat-blue" ).addClass( "disabled" );
	}
	
	*/
	console.log("Retired enableDisableComBox function called.");
}

function getAge(dateString) {
    var today = new Date();
    var birthDate = new Date(dateString);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

function notZero(n) {
    n = +n; // Coerce to number.
    if (!n) { // Matches +0, -0, NaN
        throw new Error('Invalid dividend ' + n);
    }
    return n;
}

function updateState() {
	const state = $("#analyserForm\\:hiddenState").val();
	console.log('State :: ', state)
	$('#analyserForm\\:state').val(state).trigger("change");
}

var gAndAGlobal;
var fringeExpenseRateGlobal;
var fringeExpenseRateGlobal1099;
var minHourlyWageForCAGlobal;
var minAnnualWageForCAGlobal;
var WAStateTaxRateGlobal;
var TXStateTaxRateGlobal;
var varGrossProfitGlobal;// K12
var gAndAGlobalServices;
var varProfitValue;
var wsstate;
var varCommStartDate;
var varEffectiveDate;
var varOldStartDate;
var isRehired;
var isFalseTerminated;
var varConsolePrintGlobal = true;
var timeGlobal = (new Date());
var wszipcode;

var firstAEValue = "";
var firstAEOrphan = false;
var firstRecValue = "";
var firstRecOrphan = false;
var firstAE2Value = "";
var firstAE2Orphan = false;
var firstRec2Value = "";
var firstRec2Orphan = false;
//var xtremeVerticalGlobal = "XTREME"; // 09292018
var minimumSalaryForESEmployee;
var globalFAVertical;	//01/30/2020
var globalFullTimeEmpCategory;	//01/30/2020
var globalHolidayEligibleCustomerList = new Array();	//04/16/2020

var fringeExpenseRateGlobalPYS = 0.0;	//06/03/2020
//var globalEmployeeClassForAdditionalFringe = ""; //06/03/2020
var globalEmployeeClassForAdditionalFringe = new Array(); //10/15/2020

//01/07/2022 Signature Changes
var globalFringeExpenseRateForSignatureW2 = 0.0;		//There is No PYS/PUB Employee class for Signature
var globalFringeExpenseRateForSignatureW2AndH1 = 0.0;	//W2 & H1
var globalFringeExpenseRateForSignature1099 = 0.0;

var globalCompanyCode = "";
var globalSigDupCommPersonsList = new Array();
var globalDISYSDupCommPersonsList = new Array();
	
function Trim(stringToTrim) {
    return stringToTrim.replace(/^\s+|\s+$/g, "");
}

function LTrim(stringToTrim) {
	if(typeof stringToTrim == "undefined" || stringToTrim == null){
		console.log("String to trim is called for object :: ", stringToTrim);
		stringToTrim = "";
	}
    return stringToTrim.replace(/^\s+/, "");
}

function RTrim(stringToTrim) {
	if(typeof stringToTrim == "undefined" || stringToTrim == null){
		console.log("String to trim is called for object :: ", stringToTrim);
		stringToTrim = "";
	}
    return stringToTrim.replace(/\s+$/, "");
}

function enablePTORadioOptions() 
{
	console.log("enablePTORadioOptions called.");
	$('td > input[type=radio]').attr('disabled', false);
}

function disableRadioOptions() {
	$('td > input[type=radio]').attr('disabled', true);
}

function disableSelectedRadioOption(elem) {
	$("#analyserForm\\:rdoPTO\\:"+elem)[0].disabled = true;
}

function enableSelectedRadioOption(elem) {
	$("#analyserForm\\:rdoPTO\\:"+elem)[0].disabled = false;
}

function enableDisSelectedCheckbox(elem, view) {
    // PF('chkbox').inputs[elem].disabled = view;
    // PF('chkbox').disabled = view;
    $("#analyserForm\\:"+elem+"_input").prop('readonly', view);
}

function selectRadioOption(value) {
    // PF('sel').jq.find('input\\:radio[value=false]').parent().next().trigger('click.selectOneRadio');
    var elem = "input:radio[value=" + value + "]";
    $("#analyserForm\\:rdoPTO\\:"+value)[0].click();
}

function appendSuccessClass(element) {
    if (varConsolePrintGlobal) {
        console.log("Eelemnt Name :" + element);
    }
    var cName = $('#' + element + '').attr('class');
    if (~cName.indexOf("form-group")) {
        cName = "form-group has-success";
    } else if (~cName.indexOf("input-group")) {
        cName = "input-group has-success";
    }
    $('#' + element + '').attr('class', cName);
}

function appendErrorClass(element) {
    var cName = $('#' + element + '').attr('class');
    if (~cName.indexOf("form-group")) {
        cName = "form-group has-error";
    } else if (~cName.indexOf("input-group")) {
        cName = "input-group has-error";
    }
    $('#' + element + '').attr('class', cName);
}

function validateRate(valField) {
    var valid = "0123456789.-";
    if (valField == '') {
        valField.val("0");
    }
    var temp;
    for (var i = 0; i < valField.val().length; i++) {
        temp = "" + valField.val().substring(i, i + 1);
        if (valid.indexOf(temp) == "")
            return false;
    }
    return true;
}

function validateFieldNumericRange(field, min, max) {
    var num = field;// assumption value is valid number

    if ((num < min || num > max)
            && $("#analyserForm\\:empType").val() != "perm") {
        //alert("Total hours worked value must be in range " + min + " - " + max);	//01/01/2019
        return false;
    } else {
        return true;
    }
}

function setIsBonusEligible(){
	 if ($("#analyserForm\\:isBonusEligible").is(':checked') == false) {
         $("#analyserForm\\:bonusPercentage").val("");
         $("#analyserForm\\:bonusAmount").val("0");
         $("#analyserForm\\:bonusPercentage").val("").trigger("change.select2");
     }
}

//11/25/2019	For one time Flsa Status changes, only for one time change call
function flsaStatusChanged() 
{
	var flsaStatus = $("#analyserForm\\:flsaStatus").val();
	var empType = $("#analyserForm\\:empType option:selected").val();
	var totalHolidays = parseInt($("#analyserForm\\:totalHolidays option:selected").val());
	
	console.log("Inside flsaStatusChanged");
	console.log("EmplType = "+empType);
	console.log("flsaStatus = "+flsaStatus);
	console.log("totalHolidays = "+totalHolidays);
	
    if (empType == "w2") 
    {
		if (flsaStatus == "ES" || flsaStatus == "NES")
		{
			if (totalHolidays == "0.0" || isNaN(totalHolidays))
			{
				console.log("Total Holidays VALUE set to 10.0 inside flsaStatusChanged for ES & NES and empType = w2.");
				$("#analyserForm\\:totalHolidays").val(["10.0","10"]).trigger('change.select2',[true]);
		    	$("#analyserForm\\:billableHolidays").val(0);
		    	$("#analyserForm\\:billableHolidayCost").val(0.0);
		    	$("#analyserForm\\:nonBillableHolidays").val(10);
			}
		}
    }
}

function validateFlsa(status) 
{
	console.log("Inside validateFlsa with status = "+status);
    if ($("#analyserForm\\:empType option:selected").val() == "w2") 
    {
    	//var flsaFieldReadOnlyStatus =  $("#analyserForm\\:flsaStatus").attr("readonly");
    	var flsaFieldReadOnlyStatus =  $("#analyserForm\\:flsaStatus").prop("readonly");
        //if(flsaFieldReadOnlyStatus && flsaFieldReadOnlyStatus.toLowerCase()!=='false') 
        if(flsaFieldReadOnlyStatus)
        { 
        	$("#analyserForm\\:flsaStatus").prop("readonly", false);
            $("#analyserForm\\:flsaStatus").removeAttr("disabled");
            //$("#analyserForm\\:flsaStatus").val("0").trigger("change.select2");
            $('#analyserForm\\:flsaStatus').val('0').trigger('change.select2',[true]);
        }
    	var immigrationCostReadOnlyStatus =  $("#analyserForm\\:immigrationCost").prop("readonly");
        if(immigrationCostReadOnlyStatus) 
        { 
        	$("#analyserForm\\:immigrationCost").prop("readonly", false);
            $("#analyserForm\\:immigrationCost").val(0.0);
        }
        
        // start of w2 check
        if ($("#analyserForm\\:flsaStatus option:selected").val() == "") 
        {
            $("#analyserForm\\:flsaReference").prop("readonly", true);
            $("#analyserForm\\:flsaReferenceButton").prop("disabled", "disabled");
            $("#analyserForm\\:flsaReference").val("");
            $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
            console.log("4 called");
            $("#analyserForm\\:isBonusEligible").attr("disabled", true);
            $("#analyserForm\\:bonusPercentage").val('');
            $("#analyserForm\\:bonusPercentage").attr("disabled", true);
            $("#analyserForm\\:bonusAmount").val(0);
            $("#analyserForm\\:bonusAmount").prop("readonly", true);
            $("#analyserForm\\:otb").prop("readonly", true);
            $("#analyserForm\\:otb").val(0);
            $("#analyserForm\\:doubleTime").prop("readonly", true);
            $("#analyserForm\\:doubleTime").val(0);
            $("#analyserForm\\:doubleTimeBill").prop("readonly", true);
            $("#analyserForm\\:doubleTimeBill").val(0);
            $("#analyserForm\\:oneTimeAmount").prop("readonly", true);
            $("#analyserForm\\:oneTimeAmount").val(0);
            if (status == 0) 
            {
                $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
                console.log('2 call to isBonusEligible')
                $('#analyserForm\\:isBonusEligible').iCheck('disable');
            }
            console.log('annual pay 0 - 2')
            $("#analyserForm\\:annualPay").val(0);
            console.log("First time annual pay is disabled.");
            $("#analyserForm\\:annualPay").prop("readonly", true);
        } // end of FLSA status blank check
        else if ($("#analyserForm\\:flsaStatus option:selected").val() == "ES") 
        {
            $("#analyserForm\\:flsaReference").prop("readonly", false);
            $("#analyserForm\\:flsaReferenceButton").prop("disabled", "");
            // $("#analyserForm\\:totHoursWorked").val(2080)
            var Ex = $("#analyserForm\\:annualPay").val();
            var TotalHoursWorked = $("#analyserForm\\:totHoursWorked").val();	// 01/01/2019 Tayyab
            //$("#analyserForm\\:payRate").val( Math.round((Ex / 2080) * 100) / 100);	// 01/01/2019 Tayyab
            // 01/01/2019 Tayyab
            if ( (TotalHoursWorked != 0) && (TotalHoursWorked != 0.0) )
            {
            	$("#analyserForm\\:payRate").val( Math.round((Ex / TotalHoursWorked) * 100) / 100);
            }
            else
            {
            	$("#analyserForm\\:payRate").val(0.0);
            }
            var AnnualSalary = $("#analyserForm\\:annualPay").val();
            var BonusPercentage = $("#analyserForm\\:bonusPercentage").val();
            //var TotalHoursWorked = $("#analyserForm\\:totHoursWorked").val(); // 01/01/2019 Tayyab
            var BonusAmount;
            if (TotalHoursWorked == "0" || TotalHoursWorked == "0.0") 
            {
                BonusAmount = "0.0";
            } 
            else 
            {
                BonusAmount = parseFloat((AnnualSalary * (BonusPercentage / 100)))
                        / TotalHoursWorked;
            }
            $("#analyserForm\\:bonusAmount").val(
                    Math.round(BonusAmount * 100) / 100);
            $("#analyserForm\\:oneTimeAmount").prop("readonly", true);
            $("#analyserForm\\:oneTimeAmount").val(0);
            $("#analyserForm\\:otb").prop("readonly", true);
            $("#analyserForm\\:otb").val(0);
            console.log('3 call to isBonusEligible')
            $("#analyserForm\\:isBonusEligible").removeAttr('disabled');
            $("#analyserForm\\:isBonusEligible").parent().removeClass("disabled");
            $("#analyserForm\\:bonusPercentage").attr("disabled", false);
            
            $("#analyserForm\\:doubleTime").prop("readonly", true);
            $("#analyserForm\\:doubleTime").val(0);
            $("#analyserForm\\:doubleTimeBill").prop("readonly", true);
            $("#analyserForm\\:doubleTimeBill").val(0);
            console.log("Par Rate Status Change Disabled # 1 @ 376");
            $("#analyserForm\\:payRate").prop("readonly", true);
            $("#analyserForm\\:annualPay").prop("readonly", false);
            if ($("#analyserForm\\:isBonusEligible").is(':checked') == false) {
                $("#analyserForm\\:bonusPercentage").val("");
                $("#analyserForm\\:bonusAmount").val("0");
            }
        } // end of flsa status ES
        else if ($("#analyserForm\\:flsaStatus option:selected").val() == "NE") 
        {
            $("#analyserForm\\:flsaReference").prop("readonly", true);
            $("#analyserForm\\:flsaReferenceButton").prop("disabled",
                    "disabled");
            if (wsstate == "CA") 
            { 
                var varPayRate = $("#analyserForm\\:payRate").val();
                var vardoubleTime = ((varPayRate * 2.0));
                vardoubleTime = Math.round(vardoubleTime * 100) / 100;
                //01/18/2019 Tayyab
                if (vardoubleTime < (varPayRate * 2.0))
                {
                	vardoubleTime = vardoubleTime + 0.01;
                }
                $("#analyserForm\\:doubleTime").val(
                        Math.round(vardoubleTime * 100) / 100);
                $("#analyserForm\\:doubleTime").prop("readonly", true);
                $("#analyserForm\\:doubleTimeBill").prop("readonly", false);	//01/18/2019 Tayyab
            } 
            else 
            { 
                $("#analyserForm\\:doubleTime").prop("readonly", true);
                $("#analyserForm\\:doubleTime").val(0);
                $("#analyserForm\\:doubleTimeBill").prop("readonly", true);
                $("#analyserForm\\:doubleTimeBill").val(0);
            } 
            var varPayRate = $("#analyserForm\\:payRate").val();
            var varoneTimeAmount = ((varPayRate * 1.5));
            varoneTimeAmount = Math.round(varoneTimeAmount * 100) / 100;
            //01/18/2019 Tayyab
            if (varoneTimeAmount < (varPayRate * 1.5))
            {
            	varoneTimeAmount = varoneTimeAmount + 0.01;
            }
            $("#analyserForm\\:oneTimeAmount").val(
                    Math.round(varoneTimeAmount * 100) / 100);
            $("#analyserForm\\:oneTimeAmount").prop("readonly", true);
            console.log('annual pay 0 - 3')
            $("#analyserForm\\:annualPay").val(0);
            console.log("Second time annual pay is disabled.");
            $("#analyserForm\\:annualPay").prop("readonly", true);
            if (status == 0) 
            {
                console.log('4 call to isBonusEligible')
                $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
                $('#analyserForm\\:isBonusEligible').iCheck('disable');
            }
            $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
            console.log("5 called");
            
            $("#analyserForm\\:isBonusEligible").attr("disabled", true);
            $("#analyserForm\\:isBonusEligible").parent().addClass("disabled");
            $("#analyserForm\\:bonusPercentage").val('');
            $("#analyserForm\\:bonusPercentage").attr("disabled", true);
            $("#analyserForm\\:bonusAmount").val(0);
            $("#analyserForm\\:bonusAmount").prop("readonly", true);
            
            $("#analyserForm\\:flsaReference").prop("readonly", true);
            $("#analyserForm\\:flsaReferenceButton").prop("disabled",
                    "disabled");
            $("#analyserForm\\:flsaReference").val("");
            $("#analyserForm\\:otb").prop("readonly", false);
            $("#analyserForm\\:payRate").prop("readonly", false);
        }// close of NE status check
      // /**
        else if ( $("#analyserForm\\:flsaStatus option:selected").val() == "NES")
     	 {		
     	 		$("#analyserForm\\:flsaReference").prop("readonly", false);
            	$("#analyserForm\\:flsaReferenceButton").prop("disabled", "");
            	
     	 		// alert("test-3"); // start of flsa status NE
     	 		// document.forms[0].totHoursWorked.value = 2080
 		 		var Ex =  $("#analyserForm\\:annualPay").val();	
 		 		var TotalHoursWorked = $("#analyserForm\\:totHoursWorked").val();	// 01/01/2019 Tayyab
 		 		//$("#analyserForm\\:payRate").val(Math.round((Ex/2080)*100)/100);	// 01/01/2019 Tayyab
 	            // 01/01/2019 Tayyab
 	            if ( (TotalHoursWorked != 0) && (TotalHoursWorked != 0.0) )
 	            {
 	            	$("#analyserForm\\:payRate").val( Math.round((Ex / TotalHoursWorked) * 100) / 100);
 	            }
 	            else
 	            {
 	            	$("#analyserForm\\:payRate").val(0.0);
 	            }
 	     		var AnnualSalary =  $("#analyserForm\\:annualPay").val();
 	     		var BonusPercentage  = $("#analyserForm\\:bonusPercentage").val();
 	     		//var TotalHoursWorked = $("#analyserForm\\:totHoursWorked").val();	// 01/01/2019 Tayyab
 	     		$("#analyserForm\\:isBonusEligible").attr("disabled", true);
 	            $("#analyserForm\\:isBonusEligible").parent().addClass("disabled");
 	     		$("#analyserForm\\:isBonusEligible").prop("checked",false)
 	     		$("#analyserForm\\:bonusPercentage").prop("readonly",true)
 	     		$("#analyserForm\\:bonusPercentage").val("")
 	     		$("#analyserForm\\:bonusAmount").prop("readonly",true)
 	     		$("#analyserForm\\:bonusAmount").val("")
          		
 	     	if(wsstate == "CA")
     		{														
     			 var varPayRate =  $("#analyserForm\\:payRate").val();
                  var varDTPay = ((varPayRate*2.0));
                  varDTPay = Math.round(varDTPay*100)/100;
                  //01/18/2019 Tayyab
                  if (varDTPay < (varPayRate*2.0))
                  {
                	  varDTPay = varDTPay + 0.01;
                  }
                  $("#analyserForm\\:doubleTime").val(Math.round(varDTPay*100)/100);
                  $("#analyserForm\\:doubleTime").prop("readonly", true);
                  $("#analyserForm\\:doubleTimeBill").prop("readonly", false);	//01/18/2019 Tayyab
  			}														
     	 	else 								
     		{														
     			$("#analyserForm\\:doubleTime").prop("readonly", true);
     			$("#analyserForm\\:doubleTime").val(0);
     			$("#analyserForm\\:doubleTimeBill").prop("readonly", true);
    			$("#analyserForm\\:doubleTimeBill").val(0);
     		 } 	
     		 var varPayRate =  $("#analyserForm\\:payRate").val();
             var varOTPay = ((varPayRate*1.5));
             varOTPay = Math.round(varOTPay*100)/100;
             //01/18/2019 Tayyab
             if (varOTPay < (varPayRate * 1.5))
             {
            	 varOTPay = varOTPay + 0.01;
             }
       		
             $("#analyserForm\\:oneTimeAmount").val(Math.round(varOTPay*100)/100);
             $("#analyserForm\\:oneTimeAmount").prop("readonly", true);           	
             $("#analyserForm\\:bonusAmount").prop("readonly", true);   
             $("#analyserForm\\:bonusAmount").val("");
             $("#analyserForm\\:flsaReferance").prop("readonly", true);   
             $("#analyserForm\\:flsaReferance").val("");
             $("#analyserForm\\:annualPay").prop("readonly", false);
             console.log("Par Rate Status Change Disabled # 2 @ 495");
             $("#analyserForm\\:payRate").prop("readonly", true);
             $("#analyserForm\\:otb").prop("readonly", false); //01/18/2019
     	 } 		
        else if ($("#analyserForm\\:flsaStatus option:selected").val() == "EH") {
            $("#analyserForm\\:flsaReference").prop("readonly", false);
            $("#analyserForm\\:flsaReferenceButton").prop("disabled", "");
            // close of EH status check
            console.log("Third time annual pay is disabled.");
            $("#analyserForm\\:annualPay").prop("readonly", true);
            console.log('annual pay 0 - 4')
            $("#analyserForm\\:annualPay").val(0);
            if (status == 0) {
                $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
            }
            
            $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
            console.log("1 called");
            $("#analyserForm\\:isBonusEligible").attr("disabled", true);
            $("#analyserForm\\:isBonusEligible").parent().addClass("disabled");
            $("#analyserForm\\:bonusPercentage").val('');
            $("#analyserForm\\:bonusPercentage").attr("disabled", true);
            $("#analyserForm\\:bonusAmount").val(0);
            $("#analyserForm\\:bonusAmount").prop("readonly", true);
            
            $("#analyserForm\\:doubleTime").prop("readonly", true);
            $("#analyserForm\\:doubleTime").val(0);
            $("#analyserForm\\:doubleTimeBill").prop("readonly", true);
            $("#analyserForm\\:doubleTimeBill").val(0);
            $("#analyserForm\\:oneTimeAmount").prop("readonly", true);
            $("#analyserForm\\:oneTimeAmount").val(0);
            $("#analyserForm\\:otb").prop("readonly", true);
            $("#analyserForm\\:otb").val(0);
            $("#analyserForm\\:payRate").prop("readonly", false);
            // 1920;
        }// close of EH status check
    } // CLOSE OF W2 CHECK
    else  if ($("#analyserForm\\:empType option:selected").val() == "1099") // start of else w2 check //1099 & Perm
    {    
        //01/18/2019
        //$("#analyserForm\\:flsaStatus").val("0").trigger("change.select2");	//Causing Infinite Loop
    	$('#analyserForm\\:flsaStatus').val('0').trigger('change.select2',[true]);  //here is paramater - [true] to avoid infinte loop and stop recursive executions
        $("#analyserForm\\:flsaReference").prop("readonly", true);
        $("#analyserForm\\:flsaReferenceButton").prop("disabled", "disabled");
        $("#analyserForm\\:flsaReference").val("");
        $("#analyserForm\\:otb").prop("readonly", false);
        //$("#analyserForm\\:otb").val(0);	//01/25/2019 Tayyab (Don't override if there is any value saved)
        $("#analyserForm\\:doubleTime").prop("readonly", false);
        //$("#analyserForm\\:doubleTime").val(0);	//01/25/2019 Tayyab (Don't override if there is any value saved)
        $("#analyserForm\\:doubleTimeBill").prop("readonly", false);
        //$("#analyserForm\\:doubleTimeBill").val(0);	//01/25/2019 Tayyab (Don't override if there is any value saved)
        $("#analyserForm\\:oneTimeAmount").prop("readonly", false);
        //$("#analyserForm\\:oneTimeAmount").val(0);	//01/25/2019 Tayyab (Don't override if there is any value saved)
        
    	$("#analyserForm\\:flsaStatus").prop("readonly", true);
        $("#analyserForm\\:flsaStatus").select2("enable", false);
        //$("#analyserForm\\:flsaReference").val("");
        $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
        console.log("FLSA Validates 1099 Else called");
        $("#analyserForm\\:isBonusEligible").attr("disabled", true);
        $("#analyserForm\\:isBonusEligible").parent().addClass("disabled");
        $("#analyserForm\\:bonusPercentage").val('');
        $("#analyserForm\\:bonusPercentage").attr("disabled", true);
        $("#analyserForm\\:bonusAmount").val(0);
        $("#analyserForm\\:bonusAmount").prop("readonly", true);
        //May not be required
        if (status == 0) 
        {
            $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
        }
        console.log('annual pay 0 - 5')
        $("#analyserForm\\:annualPay").val(0);
        console.log("Fourth time annual pay is disabled.");
        $("#analyserForm\\:annualPay").prop("readonly", true);
        $("#analyserForm\\:immigrationCost").prop("readonly", true);
        $("#analyserForm\\:immigrationCost").val(0.0);
    } // close for 1099
    else	//Perm
    {
    	$('#analyserForm\\:flsaStatus').val('0').trigger('change.select2',[true]);
        $("#analyserForm\\:flsaReference").prop("readonly", true);
        $("#analyserForm\\:flsaReferenceButton").prop("disabled", "disabled");
        $("#analyserForm\\:flsaReference").val("");
    	$("#analyserForm\\:flsaStatus").prop("readonly", true);
        $("#analyserForm\\:flsaStatus").select2("enable", false);
        $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
        $("#analyserForm\\:isBonusEligible").attr("disabled", true);
        $("#analyserForm\\:isBonusEligible").parent().addClass("disabled");
        $("#analyserForm\\:bonusPercentage").val('');
        $("#analyserForm\\:bonusPercentage").attr("disabled", true);
        $("#analyserForm\\:bonusAmount").val(0);
        $("#analyserForm\\:bonusAmount").prop("readonly", true);
        $("#analyserForm\\:annualPay").val(0);
        $("#analyserForm\\:annualPay").prop("readonly", true);
        $("#analyserForm\\:immigrationCost").prop("readonly", true);
        $("#analyserForm\\:immigrationCost").val(0.0);
    }
}

function getRadioValue() {
    // alert("Here "+PF('test').inputs[2].checked)
    var val = "0";
    for (var i = 0; i < $('td > input[type=radio]').length; i++) {
        if ($('td > input[type=radio]')[i].checked) {
            val = $('td > input[type=radio]')[i].value;
        }
    }
    if (varConsolePrintGlobal) {
        console.log("Selected value is :" + val);
    }
    return val;
}

// as we are using jsf so we need index of radio button to select it so this
// method is written
function getSelectedRadioIndex(val) {
	var index = 0;
	if(val == 0 || val == "0"){
		index = 0;
	} else if(val == 5 || val == "5"){
		index = 1;
	}  else if(val == 7 || val == "7"){
		index = 2;
	}  else if(val == 10 || val == "10"){
		index = 3;
	}  else if(val == 15 || val == "15"){
		index = 4;
	}  else if(val == 16 || val == "16"){
		index = 5;
	}  else if(val == 17 || val == "17"){
		index = 6;
	}  else if(val == 20 || val == "20"){
		index = 7;
	}  else if(val == 21 || val == "21"){
		index = 8;
	}  else if(val == 23 || val == "23"){
		index = 9;
	}  else if(val == 25 || val == "25"){
		index = 10;
	}  else if(val == 27 || val == "27"){
		index = 11;
	} 
	return index;
}

function calculateAllLeaveFieldsAndCost(fromFrontEnd) 
{
	console.log('calculateAllLeaveFieldsAndCost called, fromFrontEnd = '+fromFrontEnd);
	console.log('Calling controlPTOFields() inside calculateAllLeaveFieldsAndCost.');
	controlPTOFields();	//11/25/2019 to reset it as readonly against client selection
	
	if(fromFrontEnd)
	{
		console.log("This is user generated event.");
	}else
	{
		console.log("This is self generated event.");
	}
	
	var flsaStatus = $("#analyserForm\\:flsaStatus").val();
	var temps = $("#analyserForm\\:employeeCategory option:selected").val();
	//var varVertical =  $("#analyserForm\\:vertical option:selected").val().toUpperCase();	//01/30/2020
	var empType = $("#analyserForm\\:empType option:selected").val();
	var sickLeavePerHourRate = parseFloat($("#analyserForm\\:sickLeavePerHourRate").val());	// hours quantity per hour worked
	var sickLeaveCap = $("#analyserForm\\:sickLeaveCap").val();
	var payRate = parseFloat($("#analyserForm\\:payRate").val());
	var varPayRate = payRate;
	var varTotalHoursWorked = parseInt($("#analyserForm\\:totHoursWorked").val());
	var varPto = parseInt($("input[name='analyserForm:rdoPTO']:checked").val());
	var billablePTO = parseInt($("#analyserForm\\:billablePTO").val());
	var nonBillablePTO =  parseInt($("#analyserForm\\:nonBillablePTO").val());
	var billableHolidays = parseInt($("#analyserForm\\:billableHolidays").val());
	var nonBillableHolidays = parseInt($("#analyserForm\\:nonBillableHolidays").val());
	var totalHolidays = parseInt($("#analyserForm\\:totalHolidays option:selected").val());
	var pTOLimitToOverrideSick = $("#analyserForm\\:pTOLimitToOverrideSick").val();	//12/01/2018 Tayyab
	var sickLeaveType = $("#analyserForm\\:sickLeaveType option:selected").val();
	
	var billablePTOCost = 0.0;	// parseFloat($("#analyserForm\\:billablePTOCost").val());
	var nonBillablePTOCost = 0.0;	// parseFloat($("#analyserForm\\:nonBillablePTOCost").val());
	var billableHolidaysCost = 0.0;	// parseFloat($("#analyserForm\\:billableHolidaysCost").val());
	var nonBillableHolidaysCost = 0.0;	// parseFloat($("#analyserForm\\:nonBillableHolidaysCost").val());
    var sickHours = 0.0;
    var varSickLeaveCost = 0.0;
    
	if (isNaN(sickLeavePerHourRate))
	{
		$("#analyserForm\\:sickLeavePerHourRate").val("0.0");
		sickLeavePerHourRate = 0.0;
	}
	if (isNaN(sickLeaveCap))
	{
		$("#analyserForm\\:sickLeaveCap").val("0.0");
		sickLeaveCap = 0.0;
	}
	if (isNaN(payRate)) 
	{
		$("#analyserForm\\:payRate").val("0.0");
		payRate = 0.0;
  	}
	if (isNaN(varTotalHoursWorked))
	{
		$("#analyserForm\\:totHoursWorked").val("0.0");
		varTotalHoursWorked = 0.0;
	}
	if (isNaN(varPto))
	{
		varPto = 0;
	}
	if (isNaN(billablePTO))
	{
		$("#analyserForm\\:billablePTO").val("0.0");
		billablePTO = 0;
	}
	if (isNaN(nonBillablePTO))
	{
		$("#analyserForm\\:nonBillablePTO").val("0.0");
		nonBillablePTO = 0;
	}
	if (isNaN(billableHolidays))
	{
		$("#analyserForm\\:billableHolidays").val("0");
		billableHolidays = 0;
	}
	if (isNaN(nonBillableHolidays))
	{
		$("#analyserForm\\:nonBillableHolidays").val("0");
		nonBillableHolidays = 0;
	}
	if (isNaN(totalHolidays))
	{
		console.log("Total Holidays VALUE set to 0.0 inside calculateAllLeaveFieldsAndCost if NaN.");
		$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
		totalHolidays = 0.0;
	}
	if (sickLeaveType != "B" && sickLeaveType != "NB")
	{
		sickLeaveType = "NB";
	}
	
	varLeave = 0.0;
	if (empType == "w2")
	{
		/*
		//11/25/2019 Tayyab
		if (flsaStatus == "ES" || flsaStatus == "NES")
		{
	        disableSelectedRadioOption(0);	// None disabled
	        disableSelectedRadioOption(1);	// 5 DISABLED
	        disableSelectedRadioOption(2);	// 7 DISABLED
	        enableSelectedRadioOption(3);	// 10 enabled
	        if(getRadioValue()== "10" || getRadioValue()== "15" || getRadioValue()== "20" || getRadioValue()== "25")
	        {
	        	// do nothing
	        }else
	        {
	        	selectRadioOption(3);			// 10 days PTO selected (default)
	            $("#analyserForm\\:billablePTO").val(0.0);
	            $("#analyserForm\\:nonBillablePTO").val(10.0);	
	        }
	        enableSelectedRadioOption(4);	// 15 enabled
	        enableSelectedRadioOption(5);	// 20 enabled
	        enableSelectedRadioOption(6);	// 25 enabled
	        
	        //12/01/2018	Tayyab
	        varPto = parseInt($("input[name='analyserForm:rdoPTO']:checked").val());
	        if (isNaN(varPto))
	    	{
	    		varPto = 0;
	    	}
		}
		else if (flsaStatus == "NE" || flsaStatus == "EH")
		{
			enablePTORadioOptions();	// ENABLE ALL
		    if(getRadioValue() == "0")
		    {
		    	selectRadioOption(0);	// none selected
                $("#analyserForm\\:billablePTO").val(0.0);
        		$("#analyserForm\\:nonBillablePTO").val(0.0);
        		
        		//12/01/2018	Tayyab
        		billablePTO = parseInt($("#analyserForm\\:billablePTO").val());
        		nonBillablePTO =  parseInt($("#analyserForm\\:nonBillablePTO").val());
        		if (isNaN(billablePTO))
    	    	{
        			billablePTO = 0;
    	    	}
        		if (isNaN(nonBillablePTO))
    	    	{
        			nonBillablePTO = 0;
    	    	}
		    }
		}
		*/
		
		//11/25/2019 Tayyab
		//enablePTORadioOptions();	// ENABLE ALL
		//06/03/2020 Since all are enabling by default
		//Also disabled following line in analyzer page 	//disableRadioOptions();	//06/03/2020
		
		if(getRadioValue() == "0")
	    {
	    	selectRadioOption(0);	// none selected
            $("#analyserForm\\:billablePTO").val(0.0);
    		$("#analyserForm\\:nonBillablePTO").val(0.0);
    		
    		//12/01/2018	Tayyab
    		billablePTO = parseInt($("#analyserForm\\:billablePTO").val());
    		nonBillablePTO =  parseInt($("#analyserForm\\:nonBillablePTO").val());
    		if (isNaN(billablePTO))
	    	{
    			billablePTO = 0;
	    	}
    		if (isNaN(nonBillablePTO))
	    	{
    			nonBillablePTO = 0;
	    	}
	    }
	    
		//Calculate Sick
		sickHours = parseFloat(varTotalHoursWorked * sickLeavePerHourRate);
	    if (sickLeaveCap > 0 && sickHours > sickLeaveCap) 
	    {
	        sickHours = sickLeaveCap;
	    }
	    varSickLeaveCost = (varPayRate * sickHours);	// per deal cost now
		if (isNaN(varSickLeaveCost))
		{
			varSickLeaveCost = 0.0;  
		}
		//if (temps != 'FA' && temps != 'IT')	// Sick applies only to W2 and F&A and IT
		if (temps != globalFullTimeEmpCategory)	// Sick applies only to Full Time	//Full Time & Part Time
		{										
			varSickLeaveCost = 0.0;
		}
		//12/01/2018 Tayyab
		if(parseFloat(pTOLimitToOverrideSick) != 0.0 && parseFloat(varPto) >= parseFloat(pTOLimitToOverrideSick))
		{
			varSickLeaveCost = 0.0;
		}
		
		//Calculate PTO Costs
		nonBillablePTOCost = payRate * 8 * nonBillablePTO;	//Commented since PTO fields are not live
		//nonBillablePTOCost = payRate * 8 * varPto;	//Until we go live with PTO fields, so using PTO field instead of Non Billable PTO
		if (isNaN(nonBillablePTOCost))
		{
		    nonBillablePTOCost = 0.0;  
		}
		nonBillableHolidaysCost = payRate * 8 * nonBillableHolidays;
		if (isNaN(nonBillableHolidaysCost))
		{
	    	nonBillableHolidaysCost = 0.0;  
		}
		billablePTOCost = payRate * 8 * billablePTO;	
		if (isNaN(billablePTOCost))
		{
			billablePTOCost = 0.0;  
		}
		billableHolidaysCost = payRate * 8 * billableHolidays;
		if (isNaN(billableHolidaysCost))
		{
			billableHolidaysCost = 0.0;  
		}
	} 
	else 	// for everything else including 1099
	{	
		billablePTO = 0.0;
		billablePTOCost = 0.0;
		nonBillablePTO = 0.0;
		nonBillablePTOCost = 0.0;
		billableHolidays = 0.0;
		billableHolidaysCost = 0.0;
		nonBillableHolidays = 0.0;
		nonBillableHolidaysCost = 0.0;
		totalHolidays = 0.0;	//01/15/2019
		varSickLeaveCost = 0.0;
		varLeave = 0.0;
		
		selectRadioOption(0);
		disableSelectedRadioOption(1);
		disableSelectedRadioOption(2);
		disableSelectedRadioOption(3);
		disableSelectedRadioOption(4);
		disableSelectedRadioOption(5);
		disableSelectedRadioOption(6);
		disableSelectedRadioOption(7);	//11/25/2019
		disableSelectedRadioOption(8);	//11/25/2019
		disableSelectedRadioOption(9);	//06/03/2020
        disableSelectedRadioOption(10);	//06/03/2020
        disableSelectedRadioOption(11);	//06/03/2020
        
		console.log("Total Holidays VALUE set to 0.0 inside calculateAllLeaveFieldsAndCost for empType=1099.");
		$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
		$("#analyserForm\\:sickLeaveType").val("NB").trigger('change.select2');
		
		/*
        $("#analyserForm\\:billablePTO").val(0.0);
        $("#analyserForm\\:billablePTO").prop("readonly", true);
        $("#analyserForm\\:nonBillablePTO").val(0.0);	
		$("#analyserForm\\:nonBillablePTO").prop("readonly", true);
		$("#analyserForm\\:totalHolidays").val(0.0);
    	$("#analyserForm\\:totalHolidays").prop("readonly", true);
    	$("#analyserForm\\:totalHolidays").prop("disabled", true);
    	$("#analyserForm\\:billableHolidays").val(0.0);
    	$("#analyserForm\\:billableHolidays").prop("readonly", true);
    	$("#analyserForm\\:nonBillableHolidays").val(0.0);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
    	*/
		
    	$("#analyserForm\\:billablePTO").prop("readonly", true);
		$("#analyserForm\\:nonBillablePTO").prop("readonly", true);
		console.log("Total Holidays DISABLED inside calculateAllLeaveFieldsAndCost empType = 1099.");
		$("#analyserForm\\:totalHolidays").prop("disabled", true);
    	$("#analyserForm\\:billableHolidays").prop("readonly", true);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
    	$("#analyserForm\\:sickLeaveType").prop("disabled", true);
	}
	
	holidaysValueChanged(); //11/25/2019
	ptoValueChanged(); //11/25/2019
	
	$("#analyserForm\\:billablePTO").val(billablePTO);
	$("#analyserForm\\:billablePTOCost").val(Math.round(billablePTOCost*100)/100);
	$("#analyserForm\\:nonBillablePTO").val(nonBillablePTO);
	$("#analyserForm\\:nonBillablePTOCost").val(Math.round(nonBillablePTOCost*100)/100); 
	$("#analyserForm\\:billableHolidays").val(billableHolidays);
	$("#analyserForm\\:billableHolidayCost").val(Math.round(billableHolidaysCost*100)/100);
	$("#analyserForm\\:nonBillableHolidays").val(nonBillableHolidays);
	$("#analyserForm\\:nonBillableHolidayCost").val(Math.round(nonBillableHolidaysCost*100)/100);
	$("#analyserForm\\:sickLeaveCost").val(Math.round(varSickLeaveCost * 100) / 100);
	
	//12/01/2018	Tayyab
	if (sickLeaveType != null && sickLeaveType == "NB")
	{
		varLeave = parseFloat(nonBillablePTOCost  + nonBillableHolidaysCost + varSickLeaveCost);
	}
	else
	{
		varLeave = parseFloat(nonBillablePTOCost  + nonBillableHolidaysCost);
	}
	$("#analyserForm\\:leave").val(Math.round(varLeave * 100) / 100);
}

//09/01/2020
function branchChanged()
{
	if ($("#analyserForm\\:branch option:selected").val() == 'Canada')
	{
		alert("Reminder, you need to enter CAD$ for Bill, Pay and OT Bill rates when using the Canada Office selection.")
        $("#analyserForm\\:branch").focus();
        //return;
	}
}

function setValueIfIsNaN(comp){
	var val = $("#analyserForm\\:"+comp).val();
	if(isNaN(val) || val == ""){
		$("#analyserForm\\:"+comp).val(0.0);
	}
}

function CalculateGP() {
	setValueIfIsNaN("commission");
    var commVal = parseFloat($("#analyserForm\\:commission").val());
    setValueIfIsNaN("profit");
    var profitVal = parseFloat($("#analyserForm\\:profit").val());
    setValueIfIsNaN("ga");
    var gaVal = parseFloat($("#analyserForm\\:ga").val());
    setValueIfIsNaN("billRate");
    var billRateVal = parseFloat($("#analyserForm\\:billRate").val());
    setValueIfIsNaN("nonBillableCost");
    var varNonBillableCost = parseFloat($("#analyserForm\\:nonBillableCost").val());	// Actually Billable Cost
    setValueIfIsNaN("totHoursWorked");
    var varTotalHoursWorked = parseInt($("#analyserForm\\:totHoursWorked").val());
    var varNonBillableCostPerHour = 0.0;

    if (commVal == "") {
        commVal = 0.0;
    }
    if (profitVal == "") {
        profitVal = 0.0;
    }
    if (gaVal == "") {
        gaVal = 0.0;
    }
    if (billRateVal == "") {
        billRateVal = 0.0;
    }
    if (varNonBillableCost == "" || isNaN(varNonBillableCost)) {
        varNonBillableCost = 0.0;
    }

    if (isNaN($("#analyserForm\\:commision1").val())){
    	$("#analyserForm\\:commision1").val("0.0");  
    }

    if (isNaN($("#analyserForm\\:commision2").val())){
    	  $("#analyserForm\\:commision2").val("0.0");
    }

    if (isNaN($("#analyserForm\\:commision3").val()))
      {
    	  $("#analyserForm\\:commision3").val("0.0");  
      }

    if (isNaN($("#analyserForm\\:commision4").val()))
      {
    	  $("#analyserForm\\:commision4").val("0.0");  
      }
    if (isNaN($("#analyserForm\\:commision").val()))
      {
    	  $("#analyserForm\\:commision").val("0.0");  
      }

    if (isNaN($("#analyserForm\\:profit").val()))
      {
    	  $("#analyserForm\\:profit").val("0.0");  
      }

    if (isNaN($("#analyserForm\\:spreadWeek").val()))
      {
    	  $("#analyserForm\\:spreadWeek").val("0.0");  
      }
    
    if (varTotalHoursWorked != 0.0) {
        varNonBillableCostPerHour = (varNonBillableCost / varTotalHoursWorked);
    }

    var gProfit = (commVal + profitVal + gaVal);	// $ value of gross profit
    if(billRateVal != 0.0 || varNonBillableCostPerHour != 0.0){
    	var gp = (((commVal + profitVal + gaVal) / (billRateVal + varNonBillableCostPerHour)) * 100);	// gp percentage	
    }else {
    	gp = 0.0;
    }
    	

    if (varConsolePrintGlobal) {
        // console.log(timeGlobal.toLocaleString()+" := "+);
        console.log(timeGlobal.toLocaleString()
                + " :START of Function CalculateGP <---------");
        console.log(timeGlobal.toLocaleString() + " :commVal = " + commVal);
        console.log(timeGlobal.toLocaleString() + " :profitVal = " + profitVal);
        console.log(timeGlobal.toLocaleString() + " :gaVal = " + gaVal);
        console.log(timeGlobal.toLocaleString() + " :billRateVal = "
                + billRateVal);
        console.log(timeGlobal.toLocaleString() + " :varNonBillableCost = "
                + varNonBillableCost);
        console.log(timeGlobal.toLocaleString() + " :varTotalHoursWorked = "
                + varTotalHoursWorked);
        console.log(timeGlobal.toLocaleString()
                + " :varNonBillableCostPerHour = " + varNonBillableCostPerHour);
        console.log(timeGlobal.toLocaleString() + " :gp = " + gp);
        console.log(timeGlobal.toLocaleString() + " :totalHolidays = " + $("#analyserForm\\:totalHolidays option:selected").val());
        console.log(timeGlobal.toLocaleString()
                + " :END of Function CalculateGP --------->");
    }

    if (gp < 22) {
        // .css("background-color", "yellow");
        $("#analyserForm\\:gp").css("background-color", "#FF4500");// style.background
        // =
        // "#FF4500";
        // //
        // orange-red
    } else {
        $("#analyserForm\\:gp").css("background-color", "#7CFC00");// .style.background
        // =
        // "#7CFC00";
        // // green
    }
    if (isNaN(gp)) {
        $("#analyserForm\\:gp").val("0.0");
    } else {
        $("#analyserForm\\:gp").val(Math.round(gp * 100) / 100);
    }
    
    $("#analyserForm\\:gp").css("fontWeight", "bold");// .style.fontWeight =
    // "bold";
    $("#analyserForm\\:gp").css("color", "#000000");// .style.color = "#000000";
    // // black
    
    if (isNaN(gProfit)) {
        $("#analyserForm\\:gProfit").val("0.0");
    } else {
        $("#analyserForm\\:gProfit").val(Math.round(gProfit * 100)/100);
    }
}

function TierCommission() 
{
    var execOrphan = $("#analyserForm\\:execOrphan").val();
    var recruiterOrphan = $("#analyserForm\\:recruiterOrphan").val();
    var commName1 = $("#analyserForm\\:commName1 option:selected").val();
    var commName2 = $("#analyserForm\\:commName2 option:selected").val();
    var commName3 = $("#analyserForm\\:commName3 option:selected").val();
    var commName4 = $("#analyserForm\\:commName4 option:selected").val();
    var commPer1 = parseFloat($("#analyserForm\\:commPer1").val());
    var commPer2 = parseFloat($("#analyserForm\\:commPer2").val());
    var commPer3 = parseFloat($("#analyserForm\\:commPer3").val());
    var commPer4 = parseFloat($("#analyserForm\\:commPer4").val());
    //var temps = $("#analyserForm\\:employeeCategory option:selected").val();	//01/30/2020
    var gp = parseFloat($("#analyserForm\\:gp").val());	// gp percentage
    var commissionModel1 = $("#analyserForm\\:commissionModel1 option:selected").val();
    var commissionModel2 = $("#analyserForm\\:commissionModel2 option:selected").val();
    var commissionModel3 = $("#analyserForm\\:commissionModel3 option:selected").val();
    var commissionModel4 = $("#analyserForm\\:commissionModel4 option:selected").val();
    var oldCommName1 = commName1
    var oldCommName3 = commName3;
    var oldCommissionPersonGrade1 = $("#analyserForm\\:commissionPersonGrade1").val();
    var oldCommissionPersonGrade3 = $("#analyserForm\\:commissionPersonGrade3").val();
    var varae1Grade = $("#analyserForm\\:commissionPersonGrade1").val();
    var varae2Grade = $("#analyserForm\\:commissionPersonGrade3").val();
    
    //12/23/2018 Tayyab
    var ircAE1Status = $("#analyserForm\\:ircCommissionPersonStatusForAe1").val();
    var ircAE2Status = $("#analyserForm\\:ircCommissionPersonStatusForAe2").val();
    var commName5 = $("#analyserForm\\:commName5 option:selected").val();
    
    /*
     *	//01/07/2022 COM, GS-Practice, GS-Delivery, GS-BDE, and GS-Proposal fields removed 
     *	Commenting all Sections referring to the fields COM, ComPer5, Com  
    var commName6 = $("#analyserForm\\:commName6 option:selected").val();
    var commName7 = $("#analyserForm\\:commName7 option:selected").val();
    var commName8 = $("#analyserForm\\:commName8 option:selected").val();
    var commName9 = $("#analyserForm\\:commName9 option:selected").val();
    */
    
    //01/30/2020 Sajid
    var vertical =  $("#analyserForm\\:vertical option:selected").val();
    
    if (varae1Grade == "") 
    {
        varae1Grade = oldCommissionPersonGrade1;
    }
    if (varae2Grade == "") 
    {
        varae2Grade = oldCommissionPersonGrade3;
    }

    if ($("#analyserForm\\:empType").val() != "perm") 
    {
        if (globalCompanyCode == 'Signature')	//03/28/2022 Signature Commission
        {
        	 $("#analyserForm\\:commPer1").val(0);
        	 $("#analyserForm\\:commPer2").val(0);
        	 $("#analyserForm\\:commPer3").val(0);
        	 $("#analyserForm\\:commPer4").val(0);
        }	//End of Signature Commission Percentage Tier Calculations
        else
        {	//Start of DISYS Commission Tier Calculations
	        if (commName1 != "") 
	        { // AE != '' CHECK START
	            //if (temps == 'FA')	//01/30/2020
	            if (vertical == globalFAVertical)
	            { // START vertical == 'FA'
	            	
	                if ($("#analyserForm\\:execOrphan").is(':checked') == false) 
	                { // START AE ORPHAN NOT CHECKED
	                	if (gp < 0.0) 
	                    {
	                        $("#analyserForm\\:commPer1").val("0");
	                    }
	                    if (gp >= 0.0 && gp <= 29.99) 
	                    {
	                        $("#analyserForm\\:commPer1").val("10"); 
	                    }
	                    if (gp >= 30.0)
	                    {
	                        $("#analyserForm\\:commPer1").val("15");
	                    }
	                } // END AE ORPHAN NOT CHECKED
	                else 
	                { // START AE ORPHAN CHECKED
	                    $("#analyserForm\\:commPer1").val(0); // 5;
	                }
	                
	                /*
	                if ($("#analyserForm\\:com").is(':checked') == false) 
	                { // START COM NOT CHECKED
	                    if ($("#analyserForm\\:execOrphan").is(':checked') == false) 
	                    { // START AE ORPHAN NOT CHECKED
	                    	if (gp < 0.0) 
	                        {
	                            $("#analyserForm\\:commPer1").val("0");
	                        }
	                        if (gp >= 0.0 && gp <= 29.99) 
	                        {
	                            $("#analyserForm\\:commPer1").val("10"); 
	                        }
	                        if (gp >= 30.0)
	                        {
	                            $("#analyserForm\\:commPer1").val("15");
	                        }
	                    } // END AE ORPHAN NOT CHECKED
	                    else 
	                    { // START AE ORPHAN CHECKED
	                        $("#analyserForm\\:commPer1").val(0); // 5;
	                    }
	                } // END COM NOT CHECKED
	                else 
	                { // START COM CHECKED
	                    if ($("#analyserForm\\:execOrphan").is(':checked') == false)
	                    {
	                        if (gp <= 9.99)
	                        {
	                            $("#analyserForm\\:commPer1").val(0);
	                        }
	                        if (gp >= 10.0)
	                        {
	                            $("#analyserForm\\:commPer1").val(3);
	                        }
	                    } // END AE ORPHAN NOT CHECKED
	                    else 
	                    { // START AE ORPHAN CHECKED
	                        $("#analyserForm\\:commPer1").val(0);
	                    } // END AE ORPHAN CHECKED
	                } // END COM CHECKED
	                */
	                
	            } // END Vertical == 'FA'
	            else 
	            { // START Vertical != 'FA'
	                if ($("#analyserForm\\:execOrphan").is(':checked') == false) 
	                { // AE ORPHAN NOT CHECKED alert(gp);
	                    if ($("#analyserForm\\:product option:selected").val() == "SERVICES")
	                    {
	                        if (commissionModel1 == 'ITC') 
	                        {
	                            if (gp <= 0.0) 
	                            {
	                                $("#analyserForm\\:commPer1").val(0);// -10;
	                            }
	                            if (gp > 0.0)
	                            {
	                                $("#analyserForm\\:commPer1").val(10); // 0;
	                            }
	                        } 
	                        else 
	                        {
	                            if (varae1Grade == 'A')
	                            {
	                                if (gp <= 0.0) 
	                                {
	                                    $("#analyserForm\\:commPer1").val(0);// -10;
	                                }
	                                if (gp > 0.0)
	                                {
	                                    $("#analyserForm\\:commPer1").val(10); // 0;
	                                }
	                            } 
	                            else 
	                            {
	                                if (gp <= 0.0)
	                                {
	                                    $("#analyserForm\\:commPer1").val(0);// -10;
	                                    // //-5;
	                                }
	                                if (gp > 0.0)
	                                {
	                                    $("#analyserForm\\:commPer1").val(7); // 0;
	                                }
	                            }
	                        }
	                    }
	                    else
	                    { // NOT SERVICES
	                        if (commissionModel1 == 'ITC') 
	                        { // alert(commissionModel1);
	                            if (varae1Grade == 'A') 
	                            {
	                                if (gp <= 0.0) 
	                                {
	                                    $("#analyserForm\\:commPer1").val(0);// -10;
	                                }
	                                if (gp > 0.0) 
	                                {
	                                    $("#analyserForm\\:commPer1").val(10); // 0;
	                                }
	                            }
	                            else
	                            {
	                                if (gp <= 0.0)
	                                {
	                                    $("#analyserForm\\:commPer1").val(0);// -10;
	                                }
	                                if (gp > 0.0)
	                                {
	                                    $("#analyserForm\\:commPer1").val(10); // 0;
	                                }
	                            }
	                        }
	                        else
	                        {
	                            if (gp <= 0.0)
	                            {
	                                $("#analyserForm\\:commPer1").val(0);// -10;
	                            }
	                            if (gp > 0.0)
	                            {
	                                $("#analyserForm\\:commPer1").val(10); // 0;
	                            }
	                        }
	                    } // AE ORPHAN NOT CHECKED
	                }
	                else 
	                { // AE ORPHAN CHECKED
	                    $("#analyserForm\\:commPer1").val(0);
	                } // // AE ORPHAN CHECKED
	            } // END Vertical != 'FA'
	        }
	        // AE CHECK ENDS REC CHECKS START
	        if (commName2 != "")
	        { // if(commPer2 != 0) {
	            //if (temps == 'FA')	//01/30/2020
	            if (vertical == globalFAVertical)
	            { // VERTICAL FNA START
	                if ($("#analyserForm\\:recruiterOrphan").is(':checked') == false) 
	                { // NON ORPHAN START
	                    if ($("#analyserForm\\:commissionModel2").val() == 'IRC') // commission model IRC START
	                    {
	                    	if (gp < 0.0) 
	                        {
	                        	console.log('1 :: CommPer2 value 0 bcz gp < 0.0 ')
	                            $("#analyserForm\\:commPer2").val(0);
	                        }
	                        if (gp >= 0.0 && gp <= 7.99) 
	                        {
	                        	console.log('1 :: CommPer2 value 0 bcz gp < 7.99 ')
	                            $("#analyserForm\\:commPer2").val(0);
	                        }
	                        if (gp >= 8.0 && gp <= 14.99) 
	                        {
	                            $("#analyserForm\\:commPer2").val(1.5);
	                        }
	                        if (gp >= 15.0 && gp <= 24.99)
	                        {
	                            $("#analyserForm\\:commPer2").val(2);
	                        }
	                        if (gp >= 25.0)
	                        {
	                            $("#analyserForm\\:commPer2").val(3);
	                        }
	                    } // COMMISSION MODEL IRC END
	                    else
	                    {
	                        if (gp < 0.0)
	                        {
	                        	console.log('2 :: CommPer2 value 0 bcz gp < 0.0 ')
	                            $("#analyserForm\\:commPer2").val(0);
	                        }
	                        if (gp >= 0.0 && gp <= 9.99)
	                        {
	                        	console.log('2 :: CommPer2 value 0 bcz gp < 9.99 ')
	                            $("#analyserForm\\:commPer2").val(7);
	                        }
		                    if (gp >= 10.0 && gp <= 29.99)
		                    {
		                        $("#analyserForm\\:commPer2").val(7);
		                    }
		                    if (gp >= 30.0)
		                    {
		                        $("#analyserForm\\:commPer2").val(10.5);
		                    }
	                   }
	                   
	                } // END NOT ORPHAN
	                else 
	                { // START ORPHAN = YES
	                	console.log('Executive orphan checked :: ' , $("#analyserForm\\:recruiterOrphan").is(':checked'))
	                	console.log('Executive orphan Value :: ' , $("#analyserForm\\:recruiterOrphan").val())
	                	console.log('3 :: CommPer2 value 0 bcz orphan = yes ')
	                    $("#analyserForm\\:commPer2").val(0);
	                } // END ORPHAN YES
	                
	            } // END FNA
		        else 
		        { // START VERTICAL NOT FNA
		            if ($("#analyserForm\\:recruiterOrphan").is(':checked') == false) 
		            {
		                if ($("#analyserForm\\:commissionModel2").val() == 'IRC') // commission model IRC START
		                {
		                    if (gp < 0.0)
		                    {
		                    	console.log('2 :: CommPer2 value 0 bcz gp < 0.0 ')
		                        $("#analyserForm\\:commPer2").val(0);
		                    }
		                    if (gp >= 0.0 && gp <= 7.99)
		                    {
		                    	console.log('4 :: Model IRC CommPer2 value 0 bcz gp < 7.99 ')
		                        $("#analyserForm\\:commPer2").val(0);
		                    }
		                    if (gp >= 8.0 && gp <= 14.99)
		                    {
		                        $("#analyserForm\\:commPer2").val(1.5);
		                    }
		                    if (gp >= 15.0 && gp <= 24.99)
		                    {
		                        $("#analyserForm\\:commPer2").val(2);
		                    }
		                    if (gp >= 25.0)
		                    {
		                        $("#analyserForm\\:commPer2").val(3);
		                    }
		                } // COMMISSION MODEL IRC END
		                else if (commissionModel2 == 'ITC') 
		                {
		                    if (gp <= 0.0) 
		                    {
		                    	console.log('5 :: Model ITC CommPer2 value 0 bcz gp < 7.99 ')
		                        $("#analyserForm\\:commPer2").val(0);// -10;
		                    }
		                    if (gp > 0.0) 
		                    {
		                        $("#analyserForm\\:commPer2").val(10); // 0;
		                    }
		                }
		                else 
		                {   // START REC-1 NOT ORPHAN alert(commissionModel2);
		                    if (gp <= 0.0) 
		                    {
		                    	console.log('6 :: CommPer2 value 0 bcz gp <= 0.0 ')
		                        $("#analyserForm\\:commPer2").val(0);// -7;
		                    }
		                    if (gp > 0.0) 
		                    {
		                        $("#analyserForm\\:commPer2").val(7); // 0;
		                    }
		                } // END REC-1 NOT ORPHAN
		            } 
		            else 
		            { // START REC-1 ORPHAN YES
		            	console.log('7 :: CommPer2 value 0 bcz rec-orphan = yes ')
		                $("#analyserForm\\:commPer2").val(0);
		            } // END REC-1 ORPHAN YES
		        } 	// END VERTICAL NOT FNA } CommPer != 0
	        }    // AE2 CHECKS START
	        if (commName3 != "") 
	        { // AE != '' CHECK START
	            // if(commPer3 != 0)
	            // { // START commPer3 != 0
	            //if (temps == 'FA') //01/30/2020
	            if (vertical == globalFAVertical)
	            { // START Vertical == 'FA'
	                if ($("#analyserForm\\:other1Orphan").is(':checked') == false)
	                {
	                    // START AE ORPHAN NOT CHECKED
	                    /*
						 * if ($("#analyserForm\\:product option:selected").val() ==
						 * "SERVICES") { if (gp <= 9.99) {
						 * $("#analyserForm\\:commPer3").val(0); } if (gp >= 10) {
						 * $("#analyserForm\\:commPer3").val(7); } } else {
						 */
	                    if (gp < 0.0)
	                    {
	                        $("#analyserForm\\:commPer3").val(0);
	                    }
	                	if (gp >= 0.0 && gp <= 9.99)
	                    {
	                        $("#analyserForm\\:commPer3").val(10);
	                    }
	                    if (gp >= 10.0 && gp <= 29.99)
	                    {
	                        $("#analyserForm\\:commPer3").val(10);
	                    }
	                    if (gp >= 30.0)
	                    {
	                        $("#analyserForm\\:commPer3").val(15);
	                    }
	                } // } // END AE ORPHAN NOT CHECKED
		            else
		            { // START AE ORPHAN CHECKED
		                $("#analyserForm\\:commPer3").val(0);
		            }
	            } // END Vertical == 'FA'
	            else 
	            { // START Vertical != 'FA'
		             if ($("#analyserForm\\:other1Orphan").is(':checked') == false)
		             {
		                // AE ORPHAN NOT CHECKED
		                if ($("#analyserForm\\:product option:selected").val() == "SERVICES")
		                {
		                    if (commissionModel3 == 'ITC') 
		                    {
		                        if (gp <= 0.0)
		                        {
		                            $("#analyserForm\\:commPer3").val(0);// -10;
		                        }
		                        if (gp > 0.0)
		                        {
		                            $("#analyserForm\\:commPer3").val(10); // 0;
		                        }
		                    }
		                    else 
		                    {
		                        if (varae1Grade == 'A') 
		                        {
		                            if (gp <= 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(0);// -10;
		                            }
		                            if (gp > 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(10);
		                            }
		                        }
		                        else 
		                        {
		                            if (gp <= 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(0);
		                            }
		                            if (gp > 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(7);
		                            }
		                        }
		                    }// not ITC
		                }
		                else	//Non Services
		                {
		                    if (commissionModel3 == 'ITC')
		                    {
		                        // alert(commissionModel1);
		                        if (varae1Grade == 'A')
		                        {
		                            if (gp <= 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(0);// -10;
		                            }
		                            if (gp > 0.0)
		                            {
		                                $("#analyserForm\\:commPer3").val(10); // 0;
		                            }
		                        } 
		                        else 
		                        {
		                            if (gp <= 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(0);// -10;
		                            }
		                            if (gp > 0.0) 
		                            {
		                                $("#analyserForm\\:commPer3").val(10); // 0;
		                            }
		                        }
		                    } 
		                    else 
		                    {
		                        if (gp <= 0.0) 
		                        {
		                            $("#analyserForm\\:commPer3").val(0);// -10;
		                        }
		                        if (gp > 0.0) 
		                        {
		                            $("#analyserForm\\:commPer3").val(10); // 0;
		                        }
		                    }
		                }
		            } // AE ORPHAN NOT CHECKED
		            else 
		            { // AE ORPHAN CHECKED
		                $("#analyserForm\\:commPer3").val(0);
		            } // // AE ORPHAN CHECKED
	            } // END Vertical != 'FA'
	        // } //commPer1 != 0
	        // END AE2 CHECKS
	        // REC2 START
	        }
	        if (commName4 != "")
	        {
	            // if(commPer4 != 0) { // COMMISSION % != 0 START
	            //if (temps == 'FA') //01/30/2020
	            if (vertical == globalFAVertical)
	            { // FNA CHECK STARTS
	                if ($("#analyserForm\\:other2Orphan").is(':checked') == false) 
	                { // NON ORPHAN CHECK START
	                    if ($("#analyserForm\\:commissionModel4 option:selected").val() == 'IRC') // commission model IRC START
	                    { // IRC CHECK START
	                    	if (gp < 0.0) 
	                        {
	                        	console.log('1 :: CommPer4 value 0 bcz gp < 0.0 ')
	                        	$("#analyserForm\\:commPer4").val(0);
	                        }
	                        if (gp >= 0.0 && gp <= 7.99) 
	                        {
	                            $("#analyserForm\\:commPer4").val(0);
	                        }
	                        if (gp >= 8.0 && gp <= 14.99) 
	                        {
	                            $("#analyserForm\\:commPer4").val(1.5);
	                        }
	                        if (gp >= 15.0 && gp <= 24.99) 
	                        {
	                            $("#analyserForm\\:commPer4").val(2);
	                        }
	                        if (gp >= 25.0) 
	                        {
	                            $("#analyserForm\\:commPer4").val(3);
	                        }
	                    } // IRC CHECK ENDS
	                    else 
	                    {// NON IRC
	                        if (gp < 0.00) 
	                        {
	                            $("#analyserForm\\:commPer4").val(0);
	                        }
	                        if (gp >= 0.0 && gp <= 9.99) 
	                        {
	                            $("#analyserForm\\:commPer4").val(7);
	                        }
	                        if (gp >= 10.0 && gp <= 29.99) 
	                        {
	                            $("#analyserForm\\:commPer4").val(7);
	                        }
	                        if (gp >= 30.0) 
	                        {
	                            $("#analyserForm\\:commPer4").val(10.5);
	                        }
	                    }// NON IRC
	                } // NON ORPHAN CHECK ENDS
	                else 
	                { // ORPHAN CHECK START
	                    $("#analyserForm\\:commPer4").val(0);
	                } // ORPHAN CHECK ENDS
	            } // FNA CHECK ENDS
		        else 
		        { // NOT FNA START
		            if ($("#analyserForm\\:other2Orphan").is(':checked') == false) 
		            {
		                if ($("#analyserForm\\:commissionModel4 option:selected").val() == 'IRC') 
		                { // IRC CHECK START
		                	if (gp < 0.0) 
		                    {
		                    	console.log('1 :: CommPer4 value 0 bcz gp < 0.0 ')
		                    	$("#analyserForm\\:commPer4").val(0);
		                    }
		                    if (gp >= 0.0 && gp <= 7.99) 
		                    {
		                        $("#analyserForm\\:commPer4").val(0);
		                    }
		                    if (gp >= 8.0 && gp <= 14.99)
		                    {
		                        $("#analyserForm\\:commPer4").val(1.5);
		                    }
		                    if (gp >= 15.0 && gp <= 24.99)
		                    {
		                        $("#analyserForm\\:commPer4").val(2);
		                    }
		                    if (gp >= 25.0) 
		                    {
		                        $("#analyserForm\\:commPer4").val(3);
		                    }
		                } // IRC CHECK ENDS
		                else if (commissionModel4 == 'ITC') 
		                {
		                    if (gp <= 0.0) 
		                    {
		                        $("#analyserForm\\:commPer4").val(0);// -10;
		                    }
		                    if (gp > 0.0) 
		                    {
		                        $("#analyserForm\\:commPer4").val(10); // 0;
		                    }
		                } 
		                else 
		                { // NOT IRC and NOT ITC CHECK START
		                    if (gp <= 0.0) 
		                    {
		                        $("#analyserForm\\:commPer4").val(0); // -3.5;
		                    }
		                    if (gp > 0.0) 
		                    {
		                        $("#analyserForm\\:commPer4").val(7); // 0;
		                    }
		                }
		            } 
		            else 
		            {
		                $("#analyserForm\\:commPer4").val(0);
		            }
		        } // NOT FNA END
	        } // REC2 END
	        if (commName1 == "") 
	        {
	            $("#analyserForm\\:commPer1").val(0);
	        }
	        if (commName2 == "") 
	        {
	        	console.log('8 :: CommPer2 value 0 bcz commName2 == "" ')
	            $("#analyserForm\\:commPer2").val(0);
	        }
	        if (commName3 == "") 
	        {
	            $("#analyserForm\\:commPer3").val(0);
	        }
	        if (commName4 == "") 
	        {
	            $("#analyserForm\\:commPer4").val(0);
	        }
    	}	//End of DISYS Commission Tier Calculations
    }	//end of if check not equal perm
    
    
    /*	//01/07/2022
     * 

    //12/23/2018
    //Calculate commission percentage for remaining commission persons 5 to 9
    if (	$("#analyserForm\\:commName5 option:selected").val() != ''
        && ($("#analyserForm\\:commName1 option:selected").val() != '' 
        	|| $("#analyserForm\\:commName3 option:selected").val() != '')) {
    $("#analyserForm\\:commPer5").val(0.0);
	}
	if ($("#analyserForm\\:commName5 option:selected").val() != ''
	        && $("#analyserForm\\:commName1 option:selected").val() == ''
	        && $("#analyserForm\\:commName3 option:selected").val() == '') {
	    //$("#analyserForm\\:commPer5").val(2.25);	//12/22/2018 Tayyab
		$("#analyserForm\\:commPer5").val(0.0);	//12/22/2018 Tayyab
	}
	if ($("#analyserForm\\:commName6 option:selected").val() != '') { // alert("test--1");
	    $("#analyserForm\\:commPer6").val(3.0);
	}
	if ($("#analyserForm\\:commName7 option:selected").val() != '') { // alert("test--1");
	    $("#analyserForm\\:commPer7").val(2.0);
	}
	if ($("#analyserForm\\:commName8 option:selected").val() != ''
	        && ($("#analyserForm\\:commName1 option:selected").val() != '' || $(
	                "#analyserForm\\:commName3 option:selected").val() != '')) {
	    $("#analyserForm\\:commPer8").val(1.25);
	}
	if ($("#analyserForm\\:commName8 option:selected").val() != ''
	        && $("#analyserForm\\:commName1 option:selected").val() == ''
	        && $("#analyserForm\\:commName3 option:selected").val() == '') {
	    $("#analyserForm\\:commPer8").val(2.25);
	}
	if ($("#analyserForm\\:commName9 option:selected").val() != '') { // alert("test--1");
	    $("#analyserForm\\:commPer9").val(1.0);
	}

     */
    
	//disable commission percentage fields since it's auto calculated from commission person 1 to 9
	 if ($("#analyserForm\\:empType option:selected").val() != "perm") {
	        $("#analyserForm\\:commPer1").prop("readonly", true);
	        $("#analyserForm\\:commPer2").prop("readonly", true);
	        $("#analyserForm\\:commPer3").prop("readonly", true);
	        $("#analyserForm\\:commPer4").prop("readonly", true);
	        $("#analyserForm\\:commPer5").prop("readonly", true);
	        
	        //$("#analyserForm\\:commPer6").prop("readonly", true);
	        //$("#analyserForm\\:commPer7").prop("readonly", true);
	        //$("#analyserForm\\:commPer8").prop("readonly", true);
	        //$("#analyserForm\\:commPer9").prop("readonly", true);
	    }
	 else
		 {
	        $("#analyserForm\\:commPer1").prop("readonly", false);
	        $("#analyserForm\\:commPer2").prop("readonly", false);
	        $("#analyserForm\\:commPer3").prop("readonly", false);
	        $("#analyserForm\\:commPer4").prop("readonly", false);
	        $("#analyserForm\\:commPer5").prop("readonly", false);
		 }
	 //Make percentage and as blank or zero
	 if (commName1 == '') {
	        $("#analyserForm\\:commissionModel1").val('').trigger('change.select2');
	        $("#analyserForm\\:commPer1").val(0);
	        $("#analyserForm\\:splitCommissionPercentage1").val('0').trigger('change.select2');
	 }
	 if (commName2 == '') {
	        $("#analyserForm\\:commissionModel2").val('').trigger('change.select2');
	        $("#analyserForm\\:commPer2").val(0);
	        $("#analyserForm\\:splitCommissionPercentage2").val('0').trigger('change.select2');
	 }
	 if (commName3 == '') {
	        $("#analyserForm\\:commissionModel3").val('').trigger('change.select2');
	        $("#analyserForm\\:commPer3").val(0);
	        $("#analyserForm\\:splitCommissionPercentage3").val('0').trigger('change.select2');
	 }
	 if (commName4 == '') {
	        $("#analyserForm\\:commissionModel4").val('').trigger('change.select2');
	        $("#analyserForm\\:commPer4").val(0);
	        $("#analyserForm\\:splitCommissionPercentage4").val('0').trigger('change.select2');
	 }
	 //01/07/2022	Default Commission person 5 Percentage and Amount is Zero for all
	 //if (commName5 == '')	 
	 {
	        $("#analyserForm\\:commPer5").val(0);
	        $("#analyserForm\\:commision5").val(0);		//04/24/2019
	 }
     
	 /*
	  * 	//01/07/2022

	 if (commName6 == '') {
	        $("#analyserForm\\:commPer6").val(0);
	        $("#analyserForm\\:commision6").val(0);		//04/24/2019
	 }
	 if (commName7 == '') {
	        $("#analyserForm\\:commPer7").val(0);
	        $("#analyserForm\\:commision7").val(0);		//04/24/2019
	 }
	 if (commName8 == '') {
	        $("#analyserForm\\:commPer8").val(0);
	        $("#analyserForm\\:commision8").val(0);		//04/24/2019
	 }
	 if (commName9 == '') {
	        $("#analyserForm\\:commPer9").val(0);
	        $("#analyserForm\\:commision9").val(0);		//04/24/2019
	 }
	 //for F&A COM commission person
     if ($("#analyserForm\\:com").is(':checked') == true) 
     {
         $("#analyserForm\\:splitCommissionPercentage1").val('100').trigger('change.select2');
         $("#analyserForm\\:splitCommissionPercentage1").prop("readonly",true);
         
         //we may set comm model 1 to F& A here @TODO
         console.log("comm name 3 is set blank here");
         $("#analyserForm\\:commName3").val('').trigger('change.select2');
         $("#analyserForm\\:splitCommissionPercentage3").val('0').trigger('change.select2');
         $("#analyserForm\\:splitCommissionPercentage3").prop("readonly",true);
         $("#analyserForm\\:commPer3").val(0);
         $("#analyserForm\\:commissionModel3").val('').trigger('change.select2');
     }
     else
     {
    	 $("#analyserForm\\:splitCommissionPercentage1").prop("readonly", false);
    	 $("#analyserForm\\:splitCommissionPercentage3").prop("readonly", false);
     }

	  */
	 
	 //12/23/2018 No commission for IRC AEs
	 if (commName1 != '' && ircAE1Status == '1')
	 {
			$("#analyserForm\\:splitCommissionPercentage1").prop("readonly", true);
			console.log("About to trigger splitCommissionPercentage1 ");
			$("#analyserForm\\:splitCommissionPercentage1").val(0).trigger('change.select2');
	 }
	 else
	 {
			$("#analyserForm\\:splitCommissionPercentage1").prop("readonly", false);
	 }
	 if (commName3 != '' && ircAE2Status == '1')
	 {
			$("#analyserForm\\:splitCommissionPercentage3").prop("readonly", true);
			$("#analyserForm\\:splitCommissionPercentage3").val(0).trigger('change.select2');
	 }
	 else
	 {
			$("#analyserForm\\:splitCommissionPercentage3").prop("readonly", false);
	 }
	 //No grade for Staff Aug and ITC Commission model for AE1 & AE2
	 if ($("#analyserForm\\:product").val() != "SERVICES"	//01/23/2019 Tayyab
            && $("#analyserForm\\:commissionModel1").val() != 'ITC') 
	 { 
        $("#analyserForm\\:commissionPersonGrade1").val('');
        console.log("commissionPersonGrade1 is cleaned up here");
     }
     if ($("#analyserForm\\:product").val() != "SERVICES"	//01/23/2019 Tayyab
            && $("#analyserForm\\:commissionModel3").val() != 'ITC') 
     {
        $("#analyserForm\\:commissionPersonGrade3").val('');
        console.log("commissionPersonGrade3 is cleaned up here");
     }
}

function showFlsaMessage() {
    var status = $("#analyserForm\\:validFlsaRefernce").val();
    if (status == "true") {
        appendSuccessClass("flsaReferenceDiv");
    } else {
        appendErrorClass("flsaReferenceDiv");
    }
}

function showSsnMessage() {
    var status = $("#analyserForm\\:validSsn").val();
    if (status == "true") {
        appendSuccessClass("ssnDiv");
    } else {
        appendErrorClass("ssnDiv");
    }
}

function showZipCodeMessage() {
    var status = $("#analyserForm\\:validZipCode").val();
    if (status == "true") {
        appendSuccessClass("zipDiv");
    } else {
        appendErrorClass("zipDiv");
    }
}

function showTextBox(checkbox, textbox, action) {
    if (action == "Add" || action == "Modify") {
        if ($("#analyserForm\\:" + checkbox).is(':checked') == true) {
            // $("#analyserForm\\:" + textbox).val("0.0");
            $("#analyserForm\\:" + textbox).prop("readonly", false);
        } else {
            $("#analyserForm\\:" + textbox).val("0.0");
            $("#analyserForm\\:" + textbox).prop("readonly", true);
        }
    } else {
        if ($("#analyserForm\\:" + checkbox).is(':checked') == true) {
            // $("#analyserForm\\:" + textbox).val("0.0");
            $("#analyserForm\\:" + textbox).prop("readonly", false);
        } else {
            // $("#analyserForm\\:" + textbox).val("0.0");
            $("#analyserForm\\:" + textbox).prop("readonly", true);
        }
    }
}

function amtHealth() {
    var tempHealth = 0.0;
    $("#analyserForm\\:health").val("0");// Fringe expenses 02/02/2016
    if (varConsolePrintGlobal) {
        var checked = $("#chkHealth").is(':checked');
        console.log("Status is :" + checked);
    }
    if (!$("#chkHealth").is(':checked')) {
        $("#chkHealth").checked = false;
    }
}

function amtOptimize() {
    if ($("#analyserForm\\:chkOptimize").is(':checked')) {
        $("#analyserForm\\:ga").val("0");
    }
}

function amt401(status) {
    $("#analyserForm\\:k401").val("0"); // Fringe
    // expenses
    // 1 means its from checkbox, dont set its value to false in the function;
    if (status == 0) {
        $('#analyserForm\\:chkHealth').iCheck('uncheck');
        // $("#analyserForm\\:chk401k").checked = false; //
        // Fringe
    }
}

function changeBillRate() {
    if (varGrossProfitGlobal) {
        console.log("Effective date is cleaned up here 1");
    }
    $("#analyserForm\\:effectiveDate").val("");
    calculator();
}

//09/23/2020
function adjustEffectiveDates() 
{
	console.log("CALLED adjustEffectiveDates.")
    var currentTime = new Date();
    var currentMonth = currentTime.getMonth() + 1;
    var currentYear = currentTime.getFullYear();

    var startDate = new Date($("#analyserForm\\:startDate").val());
    //var oldStartDateValue = new Date(varOldStartDate);
    var startTime = new Date(startDate);
    var startMonth = startTime.getMonth() + 1;
    var startYear = startTime.getFullYear();
    
    var newDate = "";
    var lastDayOfMonth = "";
    var newMonth = "";
    var newYear = "";
    var newDay = "";

    if (currentYear == startYear)
    {
    	console.log("adjustEffectiveDates --> Current and Start years are Equal.");
	    if (currentMonth <= startMonth)
	    {
	    	console.log("adjustEffectiveDates --> Current Month is Less than or Equal to Start Month.");
	    	lastDayOfMonth = new Date(startYear, startMonth, 0).getDate();
		    newDay = lastDayOfMonth;
	    	newMonth = startMonth;
		    newYear = startYear;
		    if (newMonth < 10)
		    {
		    	newMonth = "0" + newMonth;
		    }
	    }
	    else if (currentMonth > startMonth)
	    {
	    	console.log("adjustEffectiveDates --> Current Month is Greater than Start Month.");
	    	newDay = "01";
	    	newMonth = currentMonth + 1;
	    	newYear = currentYear;
		    if (newMonth < 10)
		    {
		    	newMonth = "0" + newMonth;
		    }
		    if (newMonth == 13) 
		    {
		    	newMonth = "01";
		    	newYear = newYear + 1;
		    }
	    }
    }
    else if (currentYear > startYear)
    {
    	console.log("adjustEffectiveDates --> Current Year is Greater than Start Year.");
    	newDay = "01";
    	newMonth = currentMonth + 1;
    	newYear = currentYear;
	    if (newMonth < 10)
	    {
	    	newMonth = "0" + newMonth;
	    }
	    if (newMonth == 13) 
	    {
	    	newMonth = "01";
	    	newYear = newYear + 1;
	    }
    }
    else if (currentYear < startYear)
    {
    	console.log("adjustEffectiveDates --> Current Year is Less than Start Year.");
    	lastDayOfMonth = new Date(startYear, startMonth, 0).getDate();
	    newDay = lastDayOfMonth;
    	newMonth = startMonth;
	    newYear = startYear;
	    if (newMonth < 10)
	    {
	    	newMonth = "0" + newMonth;
	    }
    }
    
    newDate = newMonth + "/" + newDay + "/" + newYear;
    
    var newCommEffectiveDate = new Date(newDate);
    var oldCommEffectiveDate = new Date(varCommStartDate);
    newCommEffectiveDate.setHours(0, 0, 0, 0);
    oldCommEffectiveDate.setHours(0, 0, 0, 0);
    if (newCommEffectiveDate < oldCommEffectiveDate)
    {
    	console.log("adjustEffectiveDates --> oldCommEffectiveDate aka varCommStartDate = "+varCommStartDate);
    	console.log("adjustEffectiveDates --> oldCommEffectiveDate = "+oldCommEffectiveDate);
        console.log("adjustEffectiveDates --> OLD newDate = "+newDate);
    	console.log("adjustEffectiveDates --> newCommEffectiveDate = "+newCommEffectiveDate);
    	console.log("adjustEffectiveDates --> New Comm Effective Date is Less Than Old Comm Effective Date. So RESETTING to OLD COMM EFFECTIVE DATE.");
    	newDate = varCommStartDate;
    }
    
    if (varConsolePrintGlobal) 
    {
    	//console.log("adjustEffectiveDates --> oldStartDateValue = "+oldStartDateValue);
        console.log("adjustEffectiveDates --> startDate = "+startDate);
    	
        console.log("adjustEffectiveDates --> currentTime = "+currentTime);
        console.log("adjustEffectiveDates --> currentMonth = "+currentMonth);
        console.log("adjustEffectiveDates --> currentYear = "+currentYear);
        
        console.log("adjustEffectiveDates --> startTime = "+startTime);
        console.log("adjustEffectiveDates --> startMonth = "+startMonth);
        console.log("adjustEffectiveDates --> startYear = "+startYear);
	    
        console.log("adjustEffectiveDates --> newYear = "+newYear);
	    console.log("adjustEffectiveDates --> newMonth = "+newMonth);
	    console.log("adjustEffectiveDates --> newDay = "+newDay);
	    
	    console.log("adjustEffectiveDates --> lastDayOfMonth = "+lastDayOfMonth);
        console.log("adjustEffectiveDates --> newDate = "+newDate);
        
    	console.log("adjustEffectiveDates --> oldCommEffectiveDate = "+oldCommEffectiveDate);
        console.log("adjustEffectiveDates --> newDate = "+newDate);
    	console.log("adjustEffectiveDates --> newCommEffectiveDate = "+newCommEffectiveDate);
        
        console.log("Effective date is cleaned up here 2 - Except for Signature");
    }
    
    if (globalCompanyCode != 'Signature')	//04/11/2022
    {
    	$("#analyserForm\\:effectiveDate").val("");
    }
    $("#analyserForm\\:commEffDate").val(newDate);
    
    console.log("adjustEffectiveDates --> varProfitValue Value CHANGING ...");
    console.log("adjustEffectiveDates --> OLD Profit = varProfitValue = "+varProfitValue);
    
    varProfitValue = $("#analyserForm\\:profit").val();
    
    console.log("adjustEffectiveDates --> FORM analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
    console.log("adjustEffectiveDates --> NEW Profit = varProfitValue = "+varProfitValue);
}

/*
function adjustEffectiveDates() {
    var currentTime = new Date();
    var month = currentTime.getMonth() + 1;
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();

    // we need to set the date as first of next
    month = month + 1;
    day = "01";
    if (month < 10)
        month = "0" + month;

    if (month == 13) {
        month = "01";
        year = year + 1;
    }
    var nextMonth = month + "/" + day + "/" + year;
    if (varConsolePrintGlobal) {
        console.log("Effective date is cleaned up here 2");
    }
    $("#analyserForm\\:effectiveDate").val("");
    $("#analyserForm\\:commEffDate").val(nextMonth);
    varProfitValue = $("#analyserForm\\:profit").val();
}
*/

// 09292018
// Removed all else parts as commission for orphan is zero.
function processOrphanClick(oIndex) {
	// ignore all else part as that is not required.
    if (oIndex == 5) { // AE
    	if ($("#analyserForm\\:execOrphan").is(':checked') == true) {
            $("#analyserForm\\:commPer1").val("0");
            $("#analyserForm\\:commPer1").prop("readonly", true); // readOnly=true
            $("#analyserForm\\:execOrphanHdnVal").val(true);
        } else {
        	$("#analyserForm\\:execOrphanHdnVal").val(false);
        }
    }
    if (oIndex == 6) { // recruiter
        if ($("#analyserForm\\:recruiterOrphan").is(':checked') == true) {
        	console.log('9 :: CommPer2 value 0 bcz orphan = checked ')
            $("#analyserForm\\:commPer2").val(0);
            $("#analyserForm\\:commPer2").prop("readonly", true); // readOnly=true
            $("#analyserForm\\:recruiterOrphanHdnVal").val(true);
        } else {
        	$("#analyserForm\\:recruiterOrphanHdnVal").val(false);
        }
    }
    if (oIndex == 7) { // other1
        if ($("#analyserForm\\:other1Orphan").is(':checked') == true) {
            $("#analyserForm\\:commPer3").val(0);
            $("#analyserForm\\:commPer3").prop("readonly", true); // readOnly=true
            $("#analyserForm\\:other1OrphanHdnVal").val(true);
        } else {
        	$("#analyserForm\\:other1OrphanHdnVal").val(false);
        }
    }
    if (oIndex == 8) { // other2
        if ($("#analyserForm\\:other2Orphan").is(':checked') == true) {
            $("#analyserForm\\:commPer4").val(0);
            $("#analyserForm\\:commPer4").prop("readonly", true); // readOnly=true
            $("#analyserForm\\:other2OrphanHdnVal").val(true);
        } else {
        	$("#analyserForm\\:other2OrphanHdnVal").val(false);
        }
    }
}

function changeAccountExecutive(nextValue) {
	 console.log('Previous value :: ', firstAEValue);
	 console.log('Previous orphan value :: ', firstAEOrphan);
	 if(screen == "Modify"){
		 var isRehireCount = isRehired;
		if(isRehireCount > 0) {
			$("#analyserForm\\:execOrphan").prop("disabled", false);
		}
		else{
			$("#analyserForm\\:execOrphan").prop("disabled", true);
			if(firstAEValue != null && firstAEValue.trim() !== ""){
				if(firstAEValue == nextValue){
					$("#analyserForm\\:execOrphan").prop("checked", firstAEOrphan);
					if(firstAEOrphan){
						$("#execOrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
					}
					else{
						$( "#execOrphanDiv > div.icheckbox_flat-blue" ).removeClass( "checked" );
					}
				}
				else{
					$("#analyserForm\\:execOrphan").prop("checked", true);
					$("#execOrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
				}
				processOrphanClick('5');
			}
		}
	}
}

function changeRecruiter(nextValue) {
	 console.log('Previous value :: ', firstRecValue);
	 console.log('Previous orphan value :: ', firstRecOrphan);
	 if(screen == "Modify"){
		 var isRehireCount = isRehired;
		if(isRehireCount > 0) {
			$("#analyserForm\\:recruiterOrphan").prop("disabled", false);
		}
		else{
			$("#analyserForm\\:recruiterOrphan").prop("disabled", true);
			if(firstRecValue != null && firstRecValue.trim() !== ""){
				if(firstRecValue == nextValue){
					$("#analyserForm\\:recruiterOrphan").prop("checked", firstAEOrphan);
					if(firstAEOrphan){
						$("#recruiterOrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
					}
					else{
						$( "#recruiterOrphanDiv > div.icheckbox_flat-blue" ).removeClass( "checked" );
					}
				}
				else{
					$("#analyserForm\\:recruiterOrphan").prop("checked", true);
					$( "#recruiterOrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
				}
				processOrphanClick('6');
			}
		}
	}
}

function changeAccountExecutive2(nextValue) {
	 console.log('Previous value :: ', firstAE2Value);
	 console.log('Previous orphan value :: ', firstAE2Orphan);
	 if(screen == "Modify"){
		 var isRehireCount = isRehired;
		if(isRehireCount > 0) {
			$("#analyserForm\\:other1Orphan").prop("disabled", false);
		}
		else{
			$("#analyserForm\\:other1Orphan").prop("disabled", true);
			if(firstAE2Value != null && firstAE2Value.trim() !== ""){
				if(firstAE2Value == nextValue){
					$("#analyserForm\\:other1Orphan").prop("checked", firstAE2Orphan);
					if(firstAE2Orphan){
						$("#other1OrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
					}
					else{
						$( "#other1OrphanDiv > div.icheckbox_flat-blue" ).removeClass( "checked" );
					}
				}
				else{
					$("#analyserForm\\:other1Orphan").prop("checked", true);
					$( "#other1OrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
				}
				processOrphanClick('7');
			}
		}
	}
}

function changeRecruiter2(nextValue) {
	 console.log('Previous value :: ', firstRec2Value);
	 console.log('Previous orphan value :: ', firstRec2Orphan);
	 if(screen == "Modify"){
		 var isRehireCount = isRehired;
		if(isRehireCount > 0) {
			$("#analyserForm\\:other2Orphan").prop("disabled", false);
		}
		else{
			$("#analyserForm\\:other2Orphan").prop("disabled", true);
			if(firstRec2Value != null && firstRec2Value.trim() !== ""){
				if(firstRec2Value == nextValue){
					$("#analyserForm\\:other2Orphan").prop("checked", firstRec2Orphan);
					if(firstRec2Orphan){
						$("#other2OrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
					}
					else{
						$( "#other2OrphanDiv > div.icheckbox_flat-blue" ).removeClass( "checked" );
					}
				}
				else{
					$("#analyserForm\\:other2Orphan").prop("checked", true);
					$("#other2OrphanDiv > div.icheckbox_flat-blue" ).addClass( "checked" );
				}
				processOrphanClick('8');
			}
		}
	}
}

function initializeSpecificCheckBox(element) {
    $("#analyserForm\\:" + element).select2("destroy");
    $("#analyserForm\\:" + element).select2({
        allowClear : true,
        placeholder : 'Select...'
    });
}

function calculateProfiBtnClick() {
    $("#calculate").click();
}

function calculator() 
{
    updateGrade();
    //amt401(0);
    //amtHealth();
    calculateAllLeaveFieldsAndCost(0);	// 09292018 calculate all leaves costs
										// and set field values
    
    if (($("#analyserForm\\:wstate").val()) != '') {
        wsstate = ($("#analyserForm\\:wstate").val());
    }
    if (LTrim(RTrim($("#analyserForm\\:perdiem").val())) == "") {
        $("#analyserForm\\:perdiem").val(0);
    }
    
    if ($("#analyserForm\\:empType").val() != "perm") 
    {
        if (LTrim(RTrim($("#analyserForm\\:totHoursWorked").val())) == "") {
            $("#analyserForm\\:totHoursWorked").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:leave").val())) == "") {
            $("#analyserForm\\:leave").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:od").val())) == "") {
            $("#analyserForm\\:od").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:otherAmounts").val())) == "") {
            $("#analyserForm\\:otherAmounts").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:discount").val())) == "") {
            $("#analyserForm\\:discount").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:discountRate").val())) == "") {
            // as this should be zero by default for new records.
            $("#analyserForm\\:discountRate").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:billRate").val())) == "") {
            $("#analyserForm\\:billRate").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:health").val())) == "") {
            $("#analyserForm\\:health").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:k401").val())) == "") {
            $("#analyserForm\\:k401").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:tax").val())) == "") {
            $("#analyserForm\\:tax").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:payRate").val())) == "") {
            $("#analyserForm\\:payRate").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:ga").val())) == "") {
            $("#analyserForm\\:ga").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:commission").val())) == "") {
            $("#analyserForm\\:commission").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:spreadWeek").val())) == "") {
            $("#analyserForm\\:spreadWeek").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:bonusAmount").val())) == "") {
            $("#analyserForm\\:bonusAmount").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:immigrationCost").val())) == "") {
            $("#analyserForm\\:immigrationCost").val(0.0);
        }
        if (LTrim(RTrim($("#analyserForm\\:equipmentCost").val())) == "") {
            $("#analyserForm\\:equipmentCost").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:nonBillableCost").val())) == "") {
            $("#analyserForm\\:nonBillableCost").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:referralFeeAmount").val())) == "") {
            $("#analyserForm\\:referralFeeAmount").val("0.0");
        }
        if (LTrim(RTrim($("#analyserForm\\:projectCost").val())) == "") { // commission
                                                                            // 2018
            $("#analyserForm\\:projectCost").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:sickLeaveCost").val())) == "") {
            $("#analyserForm\\:sickLeaveCost").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:sickLeavePerHourRate").val())) == "") {
            $("#analyserForm\\:sickLeavePerHourRate").val(0);
        }
        if (LTrim(RTrim($("#analyserForm\\:sickLeaveCap").val())) == "") {
            $("#analyserForm\\:sickLeaveCap").val(0);
        }

        var varTotalHoursWorked = parseInt($("#analyserForm\\:totHoursWorked").val()); // D5
        var varLeave = parseFloat($("#analyserForm\\:leave").val());	// load leave cost and all leaves are now already calculated

        var varOtherDollar = parseFloat($("#analyserForm\\:od").val()); // K8
        var varOtherDollarPerHour = parseFloat($("#analyserForm\\:otherAmounts").val()); // K9
        var varBonusAmount = parseFloat($("#analyserForm\\:bonusAmount").val()); // Bonus
        var varBillRate = parseFloat($("#analyserForm\\:billRate").val()); // A12
        var varHealth = parseFloat($("#analyserForm\\:health").val()); // C12
        var varPayRate = parseFloat($("#analyserForm\\:payRate").val()); // I12
        var varEquipmentCost = parseFloat($("#analyserForm\\:equipmentCost").val());
        var varImmigrationCost = parseFloat($("#analyserForm\\:immigrationCost").val());
        var varK401 = parseFloat($("#analyserForm\\:k401").val()); // E12
        var varReferralFeeAmount = parseFloat($("#analyserForm\\:referralFeeAmount").val());
        var varBackgroundCheckAmount = parseFloat($("#analyserForm\\:backgroundCheckAmount").val());
        var varNonBillableCost = parseFloat($("#analyserForm\\:nonBillableCost").val());	// This is actually BillableCostPerHours since the field is incorrectly named
        var commVal = parseFloat($("#analyserForm\\:commission").val());
        var varNonBillableCostPerHour = 0.0;	// This is actually BillableCostPerHours since the field is incorrectly named
        var varProjectCost = parseFloat($("#analyserForm\\:projectCost").val());
        var employeeClass = $("#analyserForm\\:employeeClass option:selected").val(); //06/03/2020
        
        var maxGA = 0.0;
        var varWSStateTax = 0.0; // WaStateTax
        var varDiscount = 0.0; // K10
        var varPerdiem = 0.0; // M12
        var varTaxOrFringe = 0.0; // G12
        var varGA = 0.0; // B14
        var varRevenuePerHour = 0.0;
        var varCostPerDeal = 0.0;
        var varCostPerHour = 0.0;
        var varSpreadWeek = 0.0; // G14
        var varCASickLeaveCost = 0.0;

        if (varConsolePrintGlobal) {
            console.log(timeGlobal.toLocaleString()
                    + " :Start of function calculateSickLeaveCost --------->");
        }
        
        var varServices = $("#analyserForm\\:product option:selected").val();
        maxGA = varBillRate;
        varDiscount = varBillRate * parseFloat($("#analyserForm\\:discountRate").val() / 100); // Discount
        
        //Signature
        //var companyCode = $("#analyserForm\\:companyCode").val();
        var h1Status = $("#analyserForm\\:h1Employee").is(':checked'); 	//true or false
        //var h1Status = $("#analyserForm\\:h1Employee").prop('checked');	//true or false
        console.log("Company Code = "+globalCompanyCode);
        console.log("H1 Status = "+h1Status);
        
        // Calculated
        if (varServices == "SERVICES") {
            varGA = parseFloat(maxGA * gAndAGlobalServices); // GA CALCulated
        } else {
            varGA = parseFloat(maxGA * gAndAGlobal); // GA CALCulated
        }
        varGrossProfitGlobal = 0.0
        varPerdiem = parseFloat($("#analyserForm\\:perdiem").val() / 8.0); // DIVIDED BY 8 FOR NEW PER DIEM VALUE
        
        varHealth = 0.0;	
        varK401 = 0.0;

        if (varConsolePrintGlobal) {
            console.log(timeGlobal.toLocaleString()
                    + " :START of Function Calculator <---------");
        }
        
        if ($("#analyserForm\\:empType option:selected").val() == "w2") 
        {
         	console.log("Employee Type = w2 and Employee Class = "+employeeClass);
            //console.log("Employee Type = w2 and Company Code = "+companyCode);
            console.log("Employee Type = w2 and Company Code = "+globalCompanyCode);
            console.log("Employee Type = w2 and H1 Status = "+h1Status);
                        
            if (globalCompanyCode == "Signature")
            {
            	if (h1Status == true)
            	{
            		console.log("W2, Signature and H1 Fringe Rate Applied = "+globalFringeExpenseRateForSignatureW2AndH1);
            		varTaxOrFringe = parseFloat(varPayRate * globalFringeExpenseRateForSignatureW2AndH1);
            	}
            	else
            	{
            		console.log("W2, Signature and Non-H1 Fringe Rate Applied = "+globalFringeExpenseRateForSignatureW2);
            		varTaxOrFringe = parseFloat(varPayRate * globalFringeExpenseRateForSignatureW2);
            	}
            }
            else
           	{
	        	if (globalEmployeeClassForAdditionalFringe.includes(employeeClass))
	        	{
	        		console.log("W2, DISYS and Employee Classs Based Fringe Rate Applied = "+fringeExpenseRateGlobalPYS);
	        		varTaxOrFringe = parseFloat(varPayRate * fringeExpenseRateGlobalPYS);
	        	}
	        	else
	        	{
	        		console.log("W2, DISYS and Non-Employee Classs Based Fringe Rate Applied = "+fringeExpenseRateGlobal);
	        		varTaxOrFringe = parseFloat(varPayRate * fringeExpenseRateGlobal);
	        	}
           	}
	        
            if (wsstate == "WA") 
            {
                varWSStateTax = Math.round(varBillRate * WAStateTaxRateGlobal * 100) / 100; // 1.5% of Bill rate * Total Hours
            }
            /*
			 * if (wsstate == "CA") { varCASickLeaveCost = parseFloat(varPayRate *
			 * 3 * 8); // Additional cost for 3 sick leaves in CA }
			 */
            // alert(varCASickLeaveCost);
            varTaxOrFringe = varTaxOrFringe + varWSStateTax; // added state
            // tax to fringe
            // per hour cost
            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varNonBillableCostPerHour = 0.0;
            } else {
                varNonBillableCostPerHour = (varNonBillableCost / varTotalHoursWorked);
            }
            varRevenuePerHour = varBillRate + varNonBillableCostPerHour;	// Adding billable cost

            varCostPerHour = (varPayRate + varTaxOrFringe + varGA
                    + varOtherDollarPerHour + varHealth + varBonusAmount
                    + varDiscount + varPerdiem + varNonBillableCostPerHour
                    // + varProjectCost + varSickLeaveCost); //09262018
                    + varProjectCost);	// 09292018 since sick leave cost is now
										// added to the Leave Cost AS PER DEAL

            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varCostPerDeal = 0.0;
            } else {
                varCostPerDeal = ((varK401 + varLeave + varOtherDollar
                        + varImmigrationCost + varEquipmentCost
                        // + varReferralFeeAmount + varCASickLeaveCost) /
						// varTotalHoursWorked); //09262018
                		+ varReferralFeeAmount) / varTotalHoursWorked);	// 09262018 since no CA sick and all leave cost is now in Leave Cost
            }

            varGrossProfitGlobal = varRevenuePerHour - (varCostPerHour + varCostPerDeal);

            varSpreadWeek = parseFloat(varGrossProfitGlobal * 40);
            $("#analyserForm\\:discount").val(Math.round(varDiscount * 100) / 100);
            $("#analyserForm\\:k401").val(Math.round(varK401 * 100) / 100);
            $("#analyserForm\\:tax").val(Math.round(varTaxOrFringe * 100) / 100);
            $("#analyserForm\\:ga").val(Math.round(varGA * 100) / 100);
            $("#analyserForm\\:spreadWeek").val(Math.round(varSpreadWeek * 100) / 100);
            // 09262018
            // $("#analyserForm\\:cmProfit").val(Math.round(varGrossProfitGlobal
			// * 100)/100); //setting global profit to commissionable profit
        }
        if ($("#analyserForm\\:empType option:selected").val() == "1099") 
        {
            //console.log("Employee Type = 1099 and Company Code = "+companyCode);
            console.log("Employee Type = 1099 and Company Code = "+globalCompanyCode);
                        
            if (globalCompanyCode == "Signature")
            {
        		console.log("1099 and Signature Fringe Rate Applied= "+globalFringeExpenseRateForSignature1099);
        		varTaxOrFringe = parseFloat(varPayRate * globalFringeExpenseRateForSignature1099);
            }
            else
           	{
        		console.log("1099 and DISYS Fringe Rate Applied = "+fringeExpenseRateGlobal1099);
            	varTaxOrFringe = parseFloat(varPayRate * fringeExpenseRateGlobal1099);
           	}
        	
            var startDate = new Date($("#analyserForm\\:startDate").val());
            var applicationDate = new Date("12/31/2012");

            varWSStateTax = 0.0;
            varNonBillableCostPerHour = 0.0;
            if (wsstate == "TX") {
                varWSStateTax = Math.round(varBillRate * TXStateTaxRateGlobal * 100) / 100; // 1% of Bill rate
            } else if (wsstate == "WA") {
                varWSStateTax = Math.round(varBillRate * WAStateTaxRateGlobal * 100) / 100; // 1.5% of Bill rate * Total Hours
            }
            // alert(varWSStateTax);
            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varNonBillableCostPerHour = 0.0;
            } else {
                varNonBillableCostPerHour = (varNonBillableCost / varTotalHoursWorked);
            }
            varRevenuePerHour = varBillRate + varNonBillableCostPerHour;	// adding
																			// billable
																			// cost

            /*
			 * varCostPerHour = (varPayRate + varWSStateTax + varGA +
			 * varOtherDollarPerHour + varDiscount + varNonBillableCostPerHour);
			 */

            // alert("varCostPerHour 1099-1=:="+varCostPerHour);
            
            varTaxOrFringe = varTaxOrFringe + varWSStateTax;
            
            varCostPerHour = (varPayRate + varTaxOrFringe + varGA
                    + varOtherDollarPerHour + varDiscount
                    + varNonBillableCostPerHour + varProjectCost);

            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varCostPerDeal = 0.0;
            } else {
                varCostPerDeal = ((varOtherDollar + varEquipmentCost + varReferralFeeAmount) / varTotalHoursWorked);
            }

            varGrossProfitGlobal = varRevenuePerHour - (varCostPerHour + varCostPerDeal);
            varSpreadWeek = parseFloat(varGrossProfitGlobal * 40);
            $("#analyserForm\\:discount").val(Math.round(varDiscount * 100) / 100);
            $("#analyserForm\\:k401").val(0);
            $("#analyserForm\\:tax").val(Math.round(varTaxOrFringe * 100) / 100);
            $("#analyserForm\\:ga").val(Math.round(varGA * 100) / 100);
            $("#analyserForm\\:spreadWeek").val(Math.round(varSpreadWeek * 100) / 100);
            // $("#analyserForm\\:leave").val(0); //09292018 not required as
			// this will be set in new leave calculations function
            $("#analyserForm\\:health").val(0);
            // $("#analyserForm\\:profit").val() =
            // varGrossProfitGlobal; //06/29/2016 //Assign gross profit for
            // profit calculatioons
        } // end of 1099
        /*	//04/03/2019
        if ($("#analyserForm\\:employeeCategory option:selected").val() == "Projects") 
        {
            /
			 * var jobPay = parseFloat($("#analyserForm\\:jobPayRate").val());
			 * var jobBill = parseFloat($("#analyserForm\\:jobBillRate").val());
			 /

            var jobPay = 0.0;// parseFloat(document.forms[0].jobPayRate.value);
            var jobBill = 0.0;// parseFloat(document.forms[0].jobBillRate.value);

            $("#analyserForm\\:billRate").val(0);
            $("#analyserForm\\:payRate").val(0);
            varWSStateTax = 0;
            varOtherDollarPerHour = parseFloat(jobPay * 0.10); // otherAmt
            varGA = parseFloat(jobBill * gAndAGlobal); // GA CALCulated
            // varBonusAmount = 0.0;
            // varLeave = 0.0;

            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varNonBillableCostPerHour = 0.0;
            } else {
                varNonBillableCostPerHour = (varNonBillableCost / varTotalHoursWorked);
            }
            varRevenuePerHour = varBillRate + varNonBillableCostPerHour;

            /
			 * varCostPerHour = (varPayRate + varTaxOrFringe + varGA +
			 * varOtherDollarPerHour + varHealth + varBonusAmount + varDiscount +
			 * varPerdiem + varNonBillableCostPerHour);
			 /

            varCostPerHour = (varPayRate + varTaxOrFringe + varGA
                    + varOtherDollarPerHour + varHealth + varBonusAmount
                    + varDiscount + varPerdiem + varNonBillableCostPerHour + varProjectCost);

            if (varTotalHoursWorked == "0" || varTotalHoursWorked == "0.0") {
                varCostPerDeal = 0.0;
            } else {
                varCostPerDeal = ((varK401 + varLeave + varOtherDollar
                        + varImmigrationCost + varEquipmentCost + varReferralFeeAmount) / varTotalHoursWorked);
            }

            varGrossProfitGlobal = varRevenuePerHour - (varCostPerHour + varCostPerDeal);

            varSpreadWeek = parseFloat(varGrossProfitGlobal * 40);
            $("#analyserForm\\:discount").val(
                    Math.round(varDiscount * 100) / 100);
            $("#analyserForm\\:ga").val(Math.round(varGA * 100) / 100);
            $("#analyserForm\\:spreadWeek").val(
                    Math.round(varSpreadWeek * 100) / 100);
            $("#analyserForm\\:k401").val(0);
            $("#analyserForm\\:tax").val(varTaxOrFringe);
            $("#analyserForm\\:leave").val(0);
            $("#analyserForm\\:health").val(0);
            $("#analyserForm\\:otherAmounts").val(
                    Math.round(varOtherDollarPerHour * 100) / 100);
        }
    	*/
    } // End condition for != "perm"
    else 
    {
        sa = $("#analyserForm\\:salaryAmount").val();
        pp = $("#analyserForm\\:placementPercentage").val();
        if (sa == null || sa == "" || pp == null || pp == "") {
            // alert("Salary Amount and PlacementPercentage can't be null");
        } else {
            var sa_float = parseFloat(sa);
            var pp_float = parseFloat(pp);
            $("#analyserForm\\:placementAmount").val(
                    (sa_float * pp_float) / 100);
        }
    }

    if (varConsolePrintGlobal) 
    {
        console.log(timeGlobal.toLocaleString() + " :varGrossProfitGlobal = "
                + varGrossProfitGlobal);
        console.log(timeGlobal.toLocaleString() + " :varGA = " + varGA);
        console.log(timeGlobal.toLocaleString() + " :commVal = " + commVal);
        console.log(timeGlobal.toLocaleString() + " :varBillRate = "
                + varBillRate);
        console.log(timeGlobal.toLocaleString() + " :varNonBillableCost = "
                + varNonBillableCost);
        console.log(timeGlobal.toLocaleString() + " :varTotalHoursWorked = "
                + varTotalHoursWorked);
        console.log(timeGlobal.toLocaleString()
                + " :varNonBillableCostPerHour = " + varNonBillableCostPerHour);
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING CalculateGP Function INSIDE Calculator <---------");
    }

    CalculateGP();

    if (varConsolePrintGlobal) 
    {
        console.log(timeGlobal.toLocaleString()
                + " :CALLED CalculateGP Function INSIDE Calculator --------->");
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING TierCommission Function INSIDE Calculator <---------");
    }

    if (varConsolePrintGlobal) 
    {
        console.log(timeGlobal.toLocaleString()+ " :CALLING TierCommission & OTHER COMMISSION FUNCTIONS INSIDE Calculator --------->");
    }
        
    TierCommission();
    
    if (varConsolePrintGlobal) 
    {
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLED TierCommission Function INSIDE Calculator --------->");
        console.log(timeGlobal.toLocaleString()
                + " :END of Function Calculator --------->");
    }
}

function calculatePlacement() {
    sa = $("#analyserForm\\:salaryAmount").val();
    pp = $("#analyserForm\\:placementPercentage").val();
    if (sa == null || sa == "" || pp == null || pp == "") {
        // alert("Salary Amount and PlacementPercentage can't be null");
    } else {
        var sa_float = parseFloat(sa);
        var pp_float = parseFloat(pp);
        $("#analyserForm\\:placementAmount").val((sa_float * pp_float) / 100);

    }
}

function searchContractor() {
    if ($("#analyserForm\\:empType").val() == "1099" && isRehired == 1) {
        $("#analyserForm\\:subContractorSearch").click();
        appendSuccessClass("empTypeDiv")
        /*
		 * var page = "<%=request.getContextPath()%>/analyserreport/createAnalyserContractorSearchReport.jsp?so=LegalName&recordStatus=1&status=FORACOMPANYACTIVE";
		 * window.open(page, 'new');
		 */
    } else {
        alert("Please terminate the Analyzer before changing Subcontractor.");
        appendErrorClass("empTypeDiv");
        $("#analyserForm\\:empType").focus();
    }
}

function searchClient() {
    if (isRehired == 1) {
        if ($("#analyserForm\\:vertical").val() == "") {
            alert("Please select Vertical");
            $("#analyserForm\\:vertical").focus();
            appendErrorClass("verticalDiv");
            return;
        }
        if ($("#analyserForm\\:product").val() == "") {
            alert("Please select Product");
            $("#analyserForm\\:product").focus();
            appendErrorClass("productDiv");
            return;
        }
        $("#analyserForm\\:clientCompanyButton").click();
    } else {
        alert("Please terminate the Analyzer before changing Client.");
    }
}

function searchClientAddress() {
    if ($("#analyserForm\\:vertical").val() == "") {
        alert("Please select Vertical");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        return;
    }
    if ($("#analyserForm\\:product").val() == "") {
        alert("Please select Product");
        $("#analyserForm\\:product").focus();
        appendErrorClass("productDiv");
        return;
    }
    if ($("#analyserForm\\:clientCompanyName").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientCompanyNameDiv").focus();
        appendErrorClass("clientCompanyNameDiv");
        return;
    }
    if ($("#analyserForm\\:clientAddressId").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientAddressId").focus();
        appendErrorClass("clientAddressIdDiv");
        return;
    }
    $("#analyserForm\\:clientInvoiceButton").click();
}

function searchClientSite() {
    if ($("#analyserForm\\:vertical").val() == "") {
        alert("Please select Vertical");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        return;
    }
    if ($("#analyserForm\\:product").val() == "") {
        alert("Please select Product");
        $("#analyserForm\\:product").focus();
        appendErrorClass("productDiv");
        return;
    }
    if ($("#analyserForm\\:clientCompanyName").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientCompanyNameDiv").focus();
        appendErrorClass("clientCompanyNameDiv");
        return;
    }
    
    if ($("#analyserForm\\:clientAddressId").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientAddressId").focus();
        appendErrorClass("clientAddressIdDiv");
        return;
    }
    $("#analyserForm\\:workSiteButton").click();
}

function addWorkSite() {
    if ($("#analyserForm\\:vertical").val() == "") {
        alert("Please select Vertical");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        return;
    }
    if ($("#analyserForm\\:product").val() == "") {
        alert("Please select Product");
        $("#analyserForm\\:product").focus();
        appendErrorClass("productDiv");
        return;
    }
    
    if ($("#analyserForm\\:clientCompanyName").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientCompanyNameDiv").focus();
        appendErrorClass("clientCompanyNameDiv");
        return;
    }
    if ($("#analyserForm\\:clientAddressId").val() == "") {
        alert("Please select Client Company");
        $("#analyserForm\\:clientAddressId").focus();
        appendErrorClass("clientAddressIdDiv");
        return;
    }
    $("#analyserForm\\:addWorkSiteButton").click();
    
}

function disableFields() {
    getAction();

    console.log("disableFields() is called.");
    if (screen == "Add") 
    {
        var currentDate = new Date();
        // alert(currentDate);
        // currentDate =
        // currentDate.toJSON().slice(0,10).split('-').reverse().join('/');
        var from = currentDate.toJSON().slice(0, 10).split('-');
        currentDate = from[1] + "/" + from[2] + "/" + from[0];

        /*
		 * var from = $("#datepicker").val().split("-"); var f = new
		 * Date(from[2], from[1] - 1, from[0]);
		 */

        // alert("After processing : "+currentDate);
        $("#analyserForm\\:startDate").val(currentDate);
        //$("#analyserForm\\:startDate").prop("readonly", true); //01/18/2019

        $("#analyserForm\\:referralFeeAmount").val("0.0");
        $("#analyserForm\\:referralFeeAmount").prop("readonly", true);
        $('#analyserForm\\:isBonusEligible').iCheck('uncheck');
        $("#analyserForm\\:isBonusEligible").attr("disabled", true);
        $("#analyserForm\\:bonusPercentage").val('');
        $("#analyserForm\\:bonusPercentage").attr("disabled", true);
        $("#analyserForm\\:bonusAmount").val(0);
        $("#analyserForm\\:bonusAmount").prop("readonly", true);
        console.log('annual pay 0 - 6')
        $("#analyserForm\\:annualPay").val(0);
        console.log("Sixth time annual pay is disabled.");
        $("#analyserForm\\:annualPay").prop("readonly", true);
        // $("#analyserForm\\:bonusPercentage option:selected").val() == "3"
        
        //06/03/2020
        controlEmployeeClassPYSPTOFields();
    }
    // TODO need to check rehire status here, in that case, these will be
    // enabled
    if (screen == "Modify" || screen == "View") 
    {
        console.log("disableFields --> Action is "+screen + " and I am about to disable some fields.");
        // $("#analyserForm\\:vertical").select2("enable",false);
        
        if(isRehired != 1 || isRehired != '1'){
        	 disableDropDownSelect('vertical');
             disableDropDownSelect('product');
          // disableDropDownSelect('branch');
             disableDropDownSelect('employeeCategory');
             disableDropDownSelect('empType');
             $("#analyserForm\\:commEffDate").prop("readonly", true); 
        }else{
        	//enable Commission Effective Date
            $("#analyserForm\\:commEffDate").prop("readonly", false);
        }
        // Enable SSN, last name, first name and dob and recruiting teams
        /*$("#analyserForm\\:ssn").prop("readonly", true);
        $("#analyserForm\\:ssnButton").prop("disabled", "disabled");*/
        
        console.log("disableFields --> SSN & SSNButton Fields are now Enabled.");
        $("#analyserForm\\:ssn").prop("readonly", false);
        $("#analyserForm\\:ssnButton").prop("disabled", "");
        
        /*$("#analyserForm\\:dob").prop("readonly", true);*/
        $("#analyserForm\\:dob").prop("readonly", false);
        
        $("#analyserForm\\:firstName").prop("readonly", false);
        $("#analyserForm\\:lastName").prop("readonly", false);
        
        $("#analyserForm\\:recruitingClassification").prop("readonly", false);
        // selectRadioOption(getRadioValue());
        selectRadioOption(getSelectedRadioIndex(getRadioValue()));
    }
    
    //01/21/2022
    controlNonW2FieldsOnPageLoad();
}

function disableModificationFields() {
    getAction();

    if (screen == "Modify" || screen == "View") {
        console.log("Action is "+screen + " and I am about to disable some fields.");
        // $("#analyserForm\\:vertical").select2("enable",false);
        if(isRehired != 1 || isRehired != '1'){
	        disableDropDownSelect('vertical');
	        disableDropDownSelect('product');
	        // disableDropDownSelect('branch');
	        disableDropDownSelect('employeeCategory');
	        disableDropDownSelect('empType');
        }
        
        var roleId = parseInt($("#analyserForm\\:roleId").val());
        if(roleId != 3 && roleId != 447 && roleId != 448 && roleId != 453){
        	console.log("disableModificationFields --> SSN & SSNButton Fields are Disabled.");
        	$("#analyserForm\\:ssn").prop("readonly", true);
        	$("#analyserForm\\:ssnButton").prop("disabled", "disabled");
        }
        $("#analyserForm\\:dob").prop("readonly", true);
        
        selectRadioOption(getSelectedRadioIndex(getRadioValue()));
    }

}

function disableDropDownSelect(elem){
    /*
	 * $('#analyserForm\\:branch').select2().on('select2:selecting', function(e) {
	 * e.preventDefault(); $(this).select2('close'); } );
	 */
    var e = '#analyserForm\\:'+elem;
    $(e).attr('title', "Can't edit this field.");
    $(e).select2().on('select2:selecting',
        function(e) {
            e.preventDefault();
            $(this).select2('close');
        }
    );
    // $(e).select2('enable',false);
}

function adjustPTOFields()
{
	var empType = $("#analyserForm\\:empType option:selected").val();
	var employeeClass = $("#analyserForm\\:employeeClass option:selected").val();	//06/03/2020
	console.log("ADJUSTPTOFIELDS Function Called .....");
	console.log("empType = "+empType);
	console.log("In adjustPTOFields Employee Class = "+employeeClass);
	console.log("In adjustPTOFields globalEmployeeClassForAdditionalFringe = "+globalEmployeeClassForAdditionalFringe);
	
	if (empType == "perm")
	{
		console.log("Total Holidays VALUE set to 0.0 inside adjustPTOFields empType = perm.");
    	$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
    	$("#analyserForm\\:sickLeaveType").val("NB").trigger('change.select2');
		selectRadioOption(0);
        $("#analyserForm\\:billablePTO").val(0.0);
        $("#analyserForm\\:nonBillablePTO").val(0.0);	
    	$("#analyserForm\\:billableHolidays").val(0.0);
    	$("#analyserForm\\:nonBillableHolidays").val(0.0);
        $("#analyserForm\\:sickLeaveCost").val(0.0);
        $("#analyserForm\\:leave").val(0.0);
        
    	$("#analyserForm\\:billablePTO").prop("readonly", true);
		$("#analyserForm\\:nonBillablePTO").prop("readonly", true);
		console.log("Total Holidays DISABLED inside adjustPTOFields empType = perm.");
		$("#analyserForm\\:totalHolidays").prop("disabled", true);
    	$("#analyserForm\\:billableHolidays").prop("readonly", true);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
    	$("#analyserForm\\:sickLeaveType").prop("disabled", true);
	}
	else if (empType == "w2")
	{
    	enablePTORadioOptions();
        $("#analyserForm\\:rdoPTO\\:0").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:1").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:2").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:3").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:4").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:5").prop("readonly", false);
        $("#analyserForm\\:rdoPTO\\:6").prop("readonly", false);	//06/03/2020
        $("#analyserForm\\:rdoPTO\\:7").prop("readonly", false);	//11/25/2019
        $("#analyserForm\\:rdoPTO\\:8").prop("readonly", false);	//11/25/2019
        $("#analyserForm\\:rdoPTO\\:9").prop("readonly", false);	//06/03/2020
        $("#analyserForm\\:rdoPTO\\:10").prop("readonly", false);	//06/03/2020
        $("#analyserForm\\:rdoPTO\\:11").prop("readonly", false);	//06/03/2020
                
    	$("#analyserForm\\:billablePTO").prop("readonly", false);
		$("#analyserForm\\:nonBillablePTO").prop("readonly", false);
		//console.log("Total Holidays set to READONLY false inside adjustPTOFields empType = w2.");
    	//$("#analyserForm\\:totalHolidays").prop("readonly", false);	//Doesn't work on Select Lists
    	console.log("Total Holidays ENABLED inside adjustPTOFields empType = w2.");
    	$("#analyserForm\\:totalHolidays").prop("disabled", false);
    	$("#analyserForm\\:billableHolidays").prop("readonly", false);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", false);
    	$("#analyserForm\\:sickLeaveType").prop("readonly", false);
    	$("#analyserForm\\:sickLeaveType").prop("disabled", false);
    	
    	selectRadioOption(0);
    	$("#analyserForm\\:billablePTO").val(0.0);
        $("#analyserForm\\:nonBillablePTO").val(0.0);	
    	$("#analyserForm\\:billableHolidays").val(0.0);
    	$("#analyserForm\\:nonBillableHolidays").val(0.0);
    	$("#analyserForm\\:sickLeaveType").val("NB").trigger('change.select2');
    	$("#analyserForm\\:sickLeaveCost").val(0.0);
    	console.log("Total Holidays VALUE set to 0.0 inside adjustPTOFields empType = w2.");
    	$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
    	
    	console.log('Calling controlPTOFields() inside adjustPTOFields and emptype = w2.');
   		controlPTOFields();    	
	}
	else if (empType == "1099")
	{
        //selectRadioOption(5);
        //disableSelectedRadioOption(0);
    	$("#analyserForm\\:rdoPTO\\:0").prop("checked", true);
    	$("#analyserForm\\:rdoPTO\\:0").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:1").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:2").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:3").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:4").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:5").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:6").prop("disabled", true);
		$("#analyserForm\\:rdoPTO\\:7").prop("disabled", true);	//11/25/2019
		$("#analyserForm\\:rdoPTO\\:8").prop("disabled", true);	//11/25/2019
		$("#analyserForm\\:rdoPTO\\:9").prop("disabled", true);	//06/03/2020
        $("#analyserForm\\:rdoPTO\\:10").prop("disabled", true);	//06/03/2020
        $("#analyserForm\\:rdoPTO\\:11").prop("disabled", true);	//06/03/2020
        
    	$("#analyserForm\\:billablePTO").prop("readonly", true);
		$("#analyserForm\\:nonBillablePTO").prop("readonly", true);
		console.log("Total Holidays DISABLED inside adjustPTOFields empType = 1099.");
		$("#analyserForm\\:totalHolidays").prop("disabled", true);
    	$("#analyserForm\\:billableHolidays").prop("readonly", true);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
    	$("#analyserForm\\:sickLeaveType").prop("disabled", true);
    	
    	selectRadioOption(0);
    	$("#analyserForm\\:billablePTO").val(0.0);
        $("#analyserForm\\:nonBillablePTO").val(0.0);	
    	$("#analyserForm\\:billableHolidays").val(0.0);
    	$("#analyserForm\\:nonBillableHolidays").val(0.0);
    	$("#analyserForm\\:sickLeaveType").val("NB").trigger('change.select2');
    	$("#analyserForm\\:sickLeaveCost").val(0.0);
    	console.log("Total Holidays VALUE set to 0.0 inside adjustPTOFields empType = 1099.");
    	$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
    	$("#analyserForm\\:leave").val(0.0);
	}
}

function ptoValueChanged()
{
	var varPto = parseInt($("input[name='analyserForm:rdoPTO']:checked").val());
	var billablePTO = parseInt($("#analyserForm\\:billablePTO").val());
	var nonBillablePTO =  parseInt($("#analyserForm\\:nonBillablePTO").val());

	console.log("PTOVALUECHANGED Function Called .....");
	console.log("varPto = "+varPto);
	
	if (isNaN(varPto))
	{
		selectRadioOption(0);
		varPto = 0;
	}
	if (isNaN(billablePTO))
	{
		$("#analyserForm\\:billablePTO").val("0.0");
		billablePTO = 0;
	}
	if (isNaN(nonBillablePTO))
	{
		$("#analyserForm\\:nonBillablePTO").val("0.0");
		nonBillablePTO = 0;
	}

	if (varPto == 0)
	{
		billablePTO = 0.0;
		nonBillablePTO = 0.0;
	}
	else if (billablePTO <= 0)
	{
		billablePTO = 0.0;
		nonBillablePTO = varPto;
	}
	else if (billablePTO >= varPto)
	{
		billablePTO = varPto;
		nonBillablePTO = 0.0;
	}
	else if (billablePTO < varPto && billablePTO > 0)
	{
		nonBillablePTO = varPto - billablePTO;
	}
	$("#analyserForm\\:billablePTO").val(billablePTO);
	$("#analyserForm\\:nonBillablePTO").val(nonBillablePTO);
}

function holidaysValueChanged()
{
	var billableHolidays = parseInt($("#analyserForm\\:billableHolidays").val());
	var nonBillableHolidays = parseInt($("#analyserForm\\:nonBillableHolidays").val());
	var totalHolidays = parseInt($("#analyserForm\\:totalHolidays option:selected").val());

	console.log("HOLIDAYSVALUECHANGED Function Called .....");
	console.log("totalHolidays = "+totalHolidays);
	
	if (isNaN(billableHolidays))
	{
		$("#analyserForm\\:billableHolidays").val("0");
		billableHolidays = 0;
	}
	if (isNaN(nonBillableHolidays))
	{
		$("#analyserForm\\:nonBillableHolidays").val("0");
		nonBillableHolidays = 0;
	}
	if (isNaN(totalHolidays))
	{
		console.log("Total Holidays VALUE set to 0.0 inside holidaysValueChanged when NaN.");
		$("#analyserForm\\:totalHolidays").val("0.0").trigger('change.select2');
		totalHolidays = 0.0;
	}
	
	if (totalHolidays == 0)
	{
		billableHolidays = 0.0;
		nonBillableHolidays = 0.0;
	}
	else if (billableHolidays <= 0)
	{
		billableHolidays = 0.0;
		nonBillableHolidays = totalHolidays;
	}
	else if (billableHolidays >= totalHolidays)
	{
		billableHolidays = totalHolidays;
		nonBillableHolidays = 0.0;
	}
	else if (billableHolidays < totalHolidays && billableHolidays > 0)
	{
		nonBillableHolidays = totalHolidays - billableHolidays;
	}
	$("#analyserForm\\:billableHolidays").val(billableHolidays);
	$("#analyserForm\\:nonBillableHolidays").val(nonBillableHolidays);
}

function controlPTOFields()
{
	var roleId = parseInt($("#analyserForm\\:roleId").val());
	
	if (roleId != 3)
	{
    	$("#analyserForm\\:billablePTO").prop("readonly", true);
		$("#analyserForm\\:nonBillablePTO").prop("readonly", true);
		$("#analyserForm\\:billableHolidays").prop("readonly", true);
    	$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
    	
    	//$("#analyserForm\\:sickLeaveType").prop("disabled", true);	//DOESn'T Work on Select Lists as form may get null value
    	console.log("Sick Leave Type ATRIBUTE set to READONLY inside controlPTOFields for roleid != 3.");
    	$("#analyserForm\\:sickLeaveType").attr("readonly", "readonly");
    	console.log("Total Holidays ATRIBUTE set to READONLY inside controlPTOFields for roleid != 3.");
    	//$("#analyserForm\\:totalHolidays").prop("disabled", true);	//DOESn'T Work on Select Lists as form may get null value
    	//$("#analyserForm\\:totalHolidays").attr("readonly", "readonly");	//12/23/2019 Tayyab
    	//$("#analyserForm\\:totalHolidays").removeAttr("readonly")	//To undo the read only attribute
	}
    
    //06/03/2020
	controlEmployeeClassPYSPTOFields();
}

//06/03/2020
function controlEmployeeClassPYSPTOFields()
{
	var roleId = parseInt($("#analyserForm\\:roleId").val());
	var employeeClass = $("#analyserForm\\:employeeClass option:selected").val();	//06/03/2020
	
    console.log("In controlEmployeeClassPYSPTOFields Employee Class = "+employeeClass);
	console.log("In controlEmployeeClassPYSPTOFields globalEmployeeClassForAdditionalFringe = "+globalEmployeeClassForAdditionalFringe);
	if (	globalEmployeeClassForAdditionalFringe.includes(employeeClass)
			&& 
			(roleId == 3 || roleId == 4)
		)
	{
		console.log("In enablePYSPTOFields Enabling 17, 23 and 27 Fields. ");
		enableSelectedRadioOption(6);	// 17 ENABLED
		enableSelectedRadioOption(9);	// 23 ENABLED
		enableSelectedRadioOption(11);	// 27 ENABLED
	}
	else
	{
		if(getRadioValue()== "17" || getRadioValue()== "23" || getRadioValue()== "27")
		{
			console.log("PTO value changed and set to Zero in controlEmployeeClassPYSPTOFields .....");
			selectRadioOption(0);
		}
		console.log("In disablePYSPTOFields Disabling 17, 23 and 27 Fields. ");
		disableSelectedRadioOption(6);	// 17 DISABLED
		disableSelectedRadioOption(9);	// 23 DISABLED
		disableSelectedRadioOption(11);	// 27 DISABLED
	}
}

function controlNonW2FieldsOnPageLoad() 
{
	var eType = $("#analyserForm\\:empType option:selected").val();
	console.log("controlNonW2FieldsOnPageLoad() is Called.");
	
    if (eType == "w2") 
    {
    	//Default Employee Type is W2 and On page load disabling Perm Fields
        $("#analyserForm\\:placementAmount").prop("readonly", true);
        $("#analyserForm\\:placementDate").prop("readonly", true);
        $("#analyserForm\\:salaryAmount").prop("readonly", true);
        $("#analyserForm\\:placementPercentage").prop("readonly", true);
        $("#analyserForm\\:placementAmount").val("");
        $("#analyserForm\\:placementDate").val("");
        $("#analyserForm\\:salaryAmount").val("");
        $("#analyserForm\\:placementPercentage").val("");
        
        //Default if W2 then removing all 1099 values
        $("#analyserForm\\:contractorId").val("");
        $("#analyserForm\\:contCompanyName").val("");
        $("#analyserForm\\:contAddress").val("");
        $("#analyserForm\\:contFin").val("");
        $("#analyserForm\\:contPocName").val("");
        $("#analyserForm\\:contPayTerm").val("");
        $("#analyserForm\\:contEmail").val("");
        $("#analyserForm\\:contPhone").val("");
    }
}

function empTypeChanged() 
{
    getAction();
    
    adjustPTOFields();
    
    console.log("empTypeChanged() is Called.");
    if ($("#analyserForm\\:empType option:selected").val() == "perm") 
    {
        if (screen != "View") 
        {
            $("#analyserForm\\:salaryAmount").prop("readonly", false);
            $("#analyserForm\\:placementPercentage").prop("readonly", false);
            $("#analyserForm\\:placementDate").prop("readonly", false);
            $("#analyserForm\\:commPer1.disabled").prop("readonly", false);
            $("#analyserForm\\:commPer2.disabled").prop("readonly", false);
            $("#analyserForm\\:commPer3").prop("readonly", false);
            $("#analyserForm\\:commPer4").prop("readonly", false);
            $("#analyserForm\\:splitCommissionPercentage1").prop("readonly",
                    false);
            $("#analyserForm\\:splitCommissionPercentage2").prop("readonly",
                    false);
            $("#analyserForm\\:splitCommissionPercentage3").prop("readonly",
                    false);
            $("#analyserForm\\:splitCommissionPercentage4").prop("readonly",
                    false);
        }
        
        if (screen == "View") 
        {
            $("#analyserForm\\:salaryAmount").prop("readonly", true);
            $("#analyserForm\\:placementPercentage").prop("readonly", true);
            $("#analyserForm\\:placementAmount").prop("readonly", true);
            $("#analyserForm\\:placementDate").prop("readonly", true);
            $("#analyserForm\\:startDate").datepicker("destroy");
            $("#analyserForm\\:endDate").datepicker("destroy");
        }
        
        $("#analyserForm\\:totHoursWorked").prop("readonly", true);
        $("#analyserForm\\:billRate").prop("readonly", true);
        console.log("Par Rate Status Change Disabled # 3 @ 2850");
        $("#analyserForm\\:payRate").prop("readonly", true);
        $("#analyserForm\\:od").prop("readonly", true);
        $("#analyserForm\\:otherAmounts").prop("readonly", true);
        $("#analyserForm\\:discountRate").prop("readonly", true);
        $("#analyserForm\\:startDate").prop("readonly", true);
        $("#analyserForm\\:endDate").prop("readonly", true);
        $("#analyserForm\\:effectiveDate").prop("readonly", true);
        $("#analyserForm\\:otb").prop("readonly", true);
        $("#analyserForm\\:oneTimeAmount").prop("readonly", true);
        console.log("Fifth time annual pay is disabled.");
        $("#analyserForm\\:annualPay").prop("readonly", true);
        $("#analyserForm\\:doubleTime").prop("readonly", true);
        $("#analyserForm\\:doubleTimeBill").prop("readonly", true);
        $("#analyserForm\\:perdiem").prop("readonly", true);
        $("#analyserForm\\:commPer1").prop("readonly", false);
        $("#analyserForm\\:commPer2").prop("readonly", false);
        $("#analyserForm\\:commPer3").prop("readonly", false);
        $("#analyserForm\\:commPer4").prop("readonly", false);
    	$("#analyserForm\\:projectCost").prop("readonly", true);
    	$("#analyserForm\\:nonBillableCost").prop("readonly", true);
    	$("#analyserForm\\:equipmentCost").prop("readonly", true);
   	
        $("#analyserForm\\:totHoursWorked").val("");
        $("#analyserForm\\:billRate").val("");
        $("#analyserForm\\:payRate").val("");
        $("#analyserForm\\:od").val("");
        $("#analyserForm\\:otherAmounts").val("");
        $("#analyserForm\\:discountRate").val("");
        $("#analyserForm\\:startDate").val("");
        $("#analyserForm\\:endDate").val("");
        if (varConsolePrintGlobal) 
        {
            console.log("Effective date is cleaned up here 3");
        }
        $("#analyserForm\\:effectiveDate").val("");
        $("#analyserForm\\:otb").val("");
        $("#analyserForm\\:oneTimeAmount").val("");
        console.log('annual pay 0 - 7')
        $("#analyserForm\\:annualPay").val("");
        $("#analyserForm\\:doubleTime").val("");
        $("#analyserForm\\:doubleTimeBill").val("");
        $("#analyserForm\\:perdiem").val("");
        //05/21/2019
        $("#analyserForm\\:validSsn").val(true);
    }	//End of PERM in Employee Type Change 
    else 
    {	//Non Perm
    	
        $("#analyserForm\\:placementAmount").prop("readonly", true);
        $("#analyserForm\\:placementDate").prop("readonly", true);
        $("#analyserForm\\:salaryAmount").prop("readonly", true);
        $("#analyserForm\\:placementPercentage").prop("readonly", true);
        $("#analyserForm\\:placementAmount").val("");
        $("#analyserForm\\:placementDate").val("");
        $("#analyserForm\\:salaryAmount").val("");
        $("#analyserForm\\:placementPercentage").val("");
        
        // enable the Analyser details stuff
        if (screen != "View") 
        {
            $("#analyserForm\\:totHoursWorked").prop("readonly", false);
            $("#analyserForm\\:billRate").prop("readonly", false);
            console.log("Par Rate Status Change Disabled # 4 @ 2935");
            $("#analyserForm\\:payRate").prop("readonly", false);	//01/01/2019
            $("#analyserForm\\:od").prop("readonly", false);
            $("#analyserForm\\:otherAmounts").prop("readonly", false);
            $("#analyserForm\\:startDate").prop("readonly", false);
            $("#analyserForm\\:endDate").prop("readonly", false);
            $("#analyserForm\\:effectiveDate").prop("readonly", false);
            $("#analyserForm\\:otb").prop("readonly", false);
            $("#analyserForm\\:oneTimeAmount").prop("readonly", false);
            $("#analyserForm\\:annualPay").prop("readonly", false);
            $("#analyserForm\\:doubleTime").prop("readonly", false);
            $("#analyserForm\\:doubleTimeBill").prop("readonly", false);
            $("#analyserForm\\:perdiem").prop("readonly", false);
            $("#calculate").removeAttr("disabled");
            $("#analyserForm\\:commPer2").prop("readonly", true);
            $("#analyserForm\\:commPer3").prop("readonly", true);
            $("#analyserForm\\:commPer4").prop("readonly", true);
            
            if (LTrim(RTrim($("#analyserForm\\:totHoursWorked").val())) == "") 
            {
                $("#analyserForm\\:totHoursWorked").val(1000);
            }
            if (LTrim(RTrim($("#analyserForm\\:discountRate").val())) == "") 
            {
                $("#analyserForm\\:discountRate").val(0);
            }
        	$("#analyserForm\\:projectCost").prop("readonly", false);
        	$("#analyserForm\\:nonBillableCost").prop("readonly", false);
        	$("#analyserForm\\:equipmentCost").prop("readonly", false);

            if ($("#analyserForm\\:empType option:selected").val() == "w2") 
            {
                $("#analyserForm\\:contractorId").val("");
                $("#analyserForm\\:contCompanyName").val("");
                $("#analyserForm\\:contAddress").val("");
                $("#analyserForm\\:contFin").val("");
                $("#analyserForm\\:contPocName").val("");
                $("#analyserForm\\:contPayTerm").val("");
                $("#analyserForm\\:contEmail").val("");
                $("#analyserForm\\:contPhone").val("");
            }
        }
    }
}

//Not Used 
function changeBranch() {
    // var isRehireCount = <%=isRehired%>;
    // alert ("test value = "+isRehireCount);
    if (isRehireCount != 1)

    {
        $("#analyserForm\\:branch").prop("readonly", true);
        // alert("Please terminate the Analyzer before changing CL.");
    }
}

function changeEmptype() {
    var isRehireCount = isRehired;
    if (isRehireCount != 1) {
        // $("#analyserForm\\:empType").prop("readonly", true);
        console.log("about to disable employee type");
        // $("#analyserForm\\:empType").select2("enable",false);

        $('#analyserForm\\:empType').select2().on('select2:selecting',
                function(e) {
                    e.preventDefault();
                    $(this).select2('close');
                });

        $("#analyserForm\\:execOrphan").prop("readonly", true);
        $("#analyserForm\\:recruiterOrphan").prop("readonly", true);
        $("#analyserForm\\:other2Orphan").prop("readonly", true);
        $("#analyserForm\\:other1Orphan").prop("readonly", true);

        if ($("#analyserForm\\:recruitingClassification").val() != '') {
            $("#analyserForm\\:recruitingClassification")
                    .prop("readonly", true);
        }

        if ($("#analyserForm\\:product").val() != 'SERVICES') {
            $("#analyserForm\\:projectCost").prop("readonly", true);
        }
    }
}

function disableOrphan() {
    if ($("#analyserForm\\:execOrphan").is(':checked') == true
            && $("#analyserForm\\:commPer1").val() == 0) {
        $("#analyserForm\\:commPer1").prop("readonly", true);
        // $("#analyserForm\\:commPer1").val(99;
    }
    if ($("#analyserForm\\:recruiterOrphan").is(':checked') == true
            && $("#analyserForm\\:commPer2").val() == 0) {
        $("#analyserForm\\:commPer2").prop("readonly", true);
    }
    if ($("#analyserForm\\:other1Orphan").is(':checked') == true
            && $("#analyserForm\\:commPer3").val() == 0) {
        $("#analyserForm\\:commPer3").prop("readonly", true);
    }
    if ($("#analyserForm\\:other2Orphan").is(':checked') == true
            && $("#analyserForm\\:commPer4").val() == 0) {
        $("#analyserForm\\:commPer4").prop("readonly", true);
    }
}

function disableVertical() {
    // var isRehireCount = <%=isRehired%>;
    // alert ("test value = "+isRehireCount);
    if (isRehireCount != 1) {
        $("#analyserForm\\:vertical").prop("readonly", true);
        $("#analyserForm\\:product").prop("readonly", true);
    }

    if ($("#analyserForm\\:product").val() != 'SERVICES') {
        $("#analyserForm\\:projectCost").prop("readonly", true);
        $("#analyserForm\\:projectCost").val(0.0);
    } else {
        $("#analyserForm\\:projectCost").prop("readonly", false);
    }
}

function changeVertical() {
	var   visible = "";
	$("#analyserForm\\:distance").val("0.0");
    $("#analyserForm\\:clientCompanyName").val("");
    $("#analyserForm\\:invoiceAddress").val("");
	$("#analyserForm\\:poContractVehicle").val("");
	$("#analyserForm\\:attention").val("");
    $("#analyserForm\\:paymentTerms").val("");
    $("#analyserForm\\:distributionMethod").val("");
	$("#analyserForm\\:specialNotes").val("");
	$("#analyserForm\\:invoiceEmail").val("");
	$("#analyserForm\\:invoiceFrequency").val("");
    $("#analyserForm\\:billingType").val("");
    $("#analyserForm\\:deliveryType").val("");
    $("#analyserForm\\:practiceArea").val("");
    
    $("#analyserForm\\:siteLocation").val("");
	$("#analyserForm\\:managerPhone").val("");
	$("#analyserForm\\:clientManagerName").val("");
	$("#analyserForm\\:managerEmail").val("");
	$("#analyserForm\\:termDate").val("");
	$("#analyserForm\\:reason").val("");
	$("#analyserForm\\:backgroundCheckAmount").val("");
	$("#analyserForm\\:parentId").val("");
	$("#analyserForm\\:analyzerId").val("");
    
    var a = $("#analyserForm\\:clientCompanyName");
    var evnt = a["onchange"];
    if (typeof (evnt) == "function") {
        evnt.call(a);
    }
    
    var b = $("#analyserForm\\:invoiceAddress");
    var evnt = b["onchange"];
    if (typeof (evnt) == "function") {
        evnt.call(b);
    }
    

    // Commission 2018
    if ($("#analyserForm\\:product").val() != 'SERVICES') { // alert("test--1");
        $("#analyserForm\\:projectCost").prop("readonly", true);
        $("#analyserForm\\:projectCost").val(0.0);
        $("#analyserForm\\:commissionPersonGrade1").val('').trigger(
                'change.select2');
        $("#analyserForm\\:commissionPersonGrade3").val('').trigger(
                'change.select2');
        console.log("commissionPersonGrade1 and commissionPersonGrade3 are cleaned up here");
    } else {
        $("#analyserForm\\:projectCost").prop("readonly", false);
    }
    
    //enableDisableComBox();	//01/07/2022 COM field removed
}

/*
//--- modified 20-11-2018 --- //
function showHidePtoValues(){
	getAction();
	if (($("#analyserForm\\:vertical option:selected").val().toUpperCase() == xtremeVerticalGlobal) 
			&& ($("#analyserForm\\:empType option:selected").val() == "w2")) { 
		// If Holiday (total) list value is zero for any Analyzer Add/Modify
		// record then Billable Holiday Field and Non Billable Holiday Field
		// (both)
		// should be disabled and set to 0
		if($("#analyserForm\\:totalHolidays option:selected").val() == "0.0"){
			$("#analyserForm\\:billableHolidays").val("0");
			$("#analyserForm\\:billableHolidays").prop("readonly", true);
			
			$("#analyserForm\\:nonBillableHolidays").val("0");
			$("#analyserForm\\:nonBillableHolidays").prop("readonly", true);
		}else{
			// If Holiday (total) list value is anything except zero (0) for any
			// Analyzer Add/Modify record then both Billable Holiday Field and
			// Non Billable Holiday Field should be enabled
			$("#analyserForm\\:billableHolidays").prop("readonly", false);
			$("#analyserForm\\:nonBillableHolidays").prop("readonly", false);
		}
		$("#analyserForm\\:billablePTO").val("0");
		$("#analyserForm\\:nonBillablePTO").val("0");
	}else{
		if(screen == "Add"){
			$("#analyserForm\\:billableHolidays").val("0");
			$("#analyserForm\\:nonBillableHolidays").val("0");
			
			$("#analyserForm\\:billablePTO").val("0");
			$("#analyserForm\\:nonBillablePTO").val("0");
		}
	}
}
*/

//--- modified 20-11-2018 --- //
// remove pto checks always show in all vertical and also make its fields disabled
/*
function hidePTOSection() {	
	if (($("#analyserForm\\:vertical option:selected").val().toUpperCase() == xtremeVerticalGlobal) 
			&& ($("#analyserForm\\:empType option:selected").val() == "w2")) { 
		document.getElementById('billablePTODiv').style.display = 'block';
		document.getElementById('nonBillablePTODiv').style.display = 'block';
		document.getElementById('billablePTOCostDiv').style.display = 'block';
		document.getElementById('nonBillablePTOCostDiv').style.display = 'block';
		document.getElementById('totalHolidaysDiv').style.display = 'block';
		document.getElementById('billableHolidaysDiv').style.display = 'block';
		document.getElementById('nonBillableHolidaysDiv').style.display = 'block';
		document.getElementById('billableHolidayCostDiv').style.display = 'block';
		document.getElementById('nonBillableHolidayCostDiv').style.display = 'block';
	} else {
		document.getElementById('billablePTODiv').style.display = 'none';
		document.getElementById('nonBillablePTODiv').style.display = 'none';
		document.getElementById('billablePTOCostDiv').style.display = 'none';
		document.getElementById('nonBillablePTOCostDiv').style.display = 'none';
		document.getElementById('totalHolidaysDiv').style.display = 'none';
		document.getElementById('billableHolidaysDiv').style.display = 'none';
		document.getElementById('nonBillableHolidaysDiv').style.display = 'none';
		document.getElementById('billableHolidayCostDiv').style.display = 'none';
		document.getElementById('nonBillableHolidayCostDiv').style.display = 'none';
	}  
	
	$("#analyserForm\\:billablePTO").prop('readonly', true);
	$("#analyserForm\\:nonBillablePTO").prop('readonly', true);
	$("#analyserForm\\:totalHolidays").prop('disabled', true);
	$("#analyserForm\\:billableHolidays").prop('readonly', true);
	$("#analyserForm\\:nonBillableHolidays").prop('readonly', true);
}
*/

function clientChanged() {

    // alert("client 1");
    $("#analyserForm\\:clientSiteId").val("");
    $("#analyserForm\\:clientManagerName").val("");
    $("#analyserForm\\:managerPhone").val("");
    $("#analyserForm\\:siteLocation").val("");
    $("#analyserForm\\:projectCost").val("0.0");
    /*
	 * initializeComponents(); bindCheckboxes();
	 */
}

/*
 * function subCompanyChanged(){ initializeComponents(); bindCheckboxes(); }
 * 
 * function siteChanged() { initializeComponents(); bindCheckboxes(); }
 */

function changePay() {
    var D5 = parseInt($("#analyserForm\\:totHoursWorked").val());
    var pRate = parseFloat($("#analyserForm\\:annualPay").val()) / D5;
    $("#analyserForm\\:payRate").val(Math.round(pRate * 100) / 100);
}

function calculateProfit() {
	// selectRadioOption(getSelectedRadioIndex(getRadioValue()));
    getAction();
    if (varConsolePrintGlobal) {
        // console.log(timeGlobal.toLocaleString()+" := "+);
        console.log(timeGlobal.toLocaleString()
                + " :START OF FUNCTION CalculateProfit -<--------");
    }
    // amtHealth(); //As This is called in calculator
    if (varConsolePrintGlobal) {
         console.log(timeGlobal.toLocaleString()+" := ");
        // console.log(timeGlobal.toLocaleString()+ " :CALLING flsaPTOModify
		// Function INSIDE CalculateProfit --------->");
    }

    // flsaPTOModify(); //09292018 replaced by one function for all leaves
	// calculations

    if (varConsolePrintGlobal) {
        // console.log(timeGlobal.toLocaleString()+" := "+);
        // console.log(timeGlobal.toLocaleString()+ " :CALLED flsaPTOModify
		// Function INSIDE CalculateProfit --------->");
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING validateFlsa Function INSIDE CalculateProfit <---------");
    }

    validateFlsa(0);
    if (varConsolePrintGlobal) {
        // console.log(timeGlobal.toLocaleString()+" := "+);
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLED validateFlsa Function INSIDE CalculateProfit --------->");
        // console.log(timeGlobal.toLocaleString()+ " :CALLING calculateLeave
		// Function INSIDE CalculateProfit <---------");
    }

    // 09262018 since leave is now part of new leave calculations function
    // calculateLeave(getRadioValue()); // to incorporate updated leave value in
    
    // the profit calculation
    if (varConsolePrintGlobal) {
        // console.log(timeGlobal.toLocaleString()+" := "+);
        console.log(timeGlobal.toLocaleString()+ " :CALLED calculateLeave Function INSIDE CalculateProfit --------->");
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING Calculator Function INSIDE CalculateProfit <---------");
    }

    calculator();
   
    var K12 = varGrossProfitGlobal;

    // 09262018
    // setting commissionable profit
    if(isNaN(K12)){
    	$("#analyserForm\\:cmProfit").val(0.0);
    	K12 = 0.0;
    }else{
    	$("#analyserForm\\:cmProfit").val(Math.round(K12 * 100)/100);	// setting global profit to commissionable
		// profit (at this point commission
		// is not subtracted
    }
    
    // 06/29/2016
    if (LTrim(RTrim($("#analyserForm\\:commPer1").val())) == "") {
        $("#analyserForm\\:commPer1").val(0);
    }
    if (LTrim(RTrim($("#analyserForm\\:commPer2").val())) == "") {
    	console.log('14 :: CommPer2 value 0 bcz commPer2 = "" ')
        $("#analyserForm\\:commPer2").val(0);
    }
    if (LTrim(RTrim($("#analyserForm\\:commPer3").val())) == "") {
        $("#analyserForm\\:commPer3").val(0);
    }
    if (LTrim(RTrim($("#analyserForm\\:commPer4").val())) == "") {
        $("#analyserForm\\:commPer4").val(0);
    }
    if (LTrim(RTrim($(
            "#analyserForm\\:splitCommissionPercentage1 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage1").val(0);
    }
    if (LTrim(RTrim($(
            "#analyserForm\\:splitCommissionPercentage2 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage2").val(0).trigger(
                'change.select2');
//    	PF("splitCommissionPercentage2").panel.find(".ui-selectonemenu-item:eq(0)").click();
        
    }
    if ($("#analyserForm\\:splitCommissionPercentage3 option:selected").val() === undefined
            || $("#analyserForm\\:splitCommissionPercentage3 option:selected")
                    .val() == "") {
        $("#analyserForm\\:splitCommissionPercentage3").val(0).trigger(
                'change.select2');
        
//        PF("splitCommissionPercentage3").panel.find(".ui-selectonemenu-item:eq(0)").click();
    }
    if (LTrim(RTrim($(
            "#analyserForm\\:splitCommissionPercentage4 option:selected").val())) == "") {
    	// 0 - no value
    	// 1 - 100%
    	// 2 - 75%
    	// 3 - 50%
    	// 4 - 25%
    	// 5 - 0%
        $("#analyserForm\\:splitCommissionPercentage4").val(0).trigger(
                'change.select2');
        // https://stackoverflow.com/questions/29657946/change-item-of-pselectonemenu-using-jquery/41451965
        // Changed the way Split commission percentage value was triggered. Replaced jsf select menu component with primefaces
//        PF("splitCommissionPercentage4").panel.find(".ui-selectonemenu-item:eq(0)").click();
    }
    //
    if (LTrim(RTrim($("#analyserForm\\:commName1 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage1").val(0).trigger(
                'change.select2');
        //PF("splitCommissionPercentage1").panel.find(".ui-selectonemenu-item:eq(0)").click();
    }
    if (LTrim(RTrim($("#analyserForm\\:commName2 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage2").val(0).trigger(
                'change.select2');
        
    }
    if (LTrim(RTrim($("#analyserForm\\:commName3 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage3").val(0).trigger(
                'change.select2');
    }
    if (LTrim(RTrim($("#analyserForm\\:commName4 option:selected").val())) == "") {
        $("#analyserForm\\:splitCommissionPercentage4").val(0).trigger(
                'change.select2');
    }

    if ($("#analyserForm\\:empType option:selected").val() == "perm") {
    	setValueIfIsNaN("placementAmount");
        K12 = $("#analyserForm\\:placementAmount").val();
    }

    console.log('comm-1 :: ', $("#analyserForm\\:commPer1").val());
    console.log('comm-2 :: ', $("#analyserForm\\:commPer2").val());
    console.log('comm-3 :: ', $("#analyserForm\\:commPer3").val());
    console.log('comm-4 :: ', $("#analyserForm\\:commPer4").val());
    
    // 09262018
    // changed all commPerX to commAmtX since these are all amounts
    setValueIfIsNaN("billRate");
    var varBillRate = parseFloat($("#analyserForm\\:billRate").val());
    var commAmt1 = parseFloat(($("#analyserForm\\:commPer1").val() * K12) / 100);
    var commAmt2 = parseFloat(($("#analyserForm\\:commPer2").val() * K12) / 100);
    var commAmt3 = parseFloat(($("#analyserForm\\:commPer3").val() * K12) / 100);
    var commAmt4 = parseFloat(($("#analyserForm\\:commPer4").val() * K12) / 100);

    // Commission 2018
    var commAmt5 = parseFloat(($("#analyserForm\\:commPer5").val() * K12) / 100);
    
    //01/07/2022 Fields removed
    //var commAmt6 = parseFloat(($("#analyserForm\\:commPer6").val() * K12) / 100);
    //var commAmt7 = parseFloat(($("#analyserForm\\:commPer7").val() * K12) / 100);
    //var commAmt8 = parseFloat(($("#analyserForm\\:commPer8").val() * varBillRate) / 100);
    //var commAmt9 = parseFloat(($("#analyserForm\\:commPer9").val() * K12) / 100);
    
    var commSplit1 = parseFloat($(
            "#analyserForm\\:splitCommissionPercentage1 option:selected").val());
    var commSplit2 = parseFloat($(
            "#analyserForm\\:splitCommissionPercentage2 option:selected").val());
    var commSplit3 = parseFloat($(
            "#analyserForm\\:splitCommissionPercentage3 option:selected").val());
    var commSplit4 = parseFloat($(
            "#analyserForm\\:splitCommissionPercentage4 option:selected").val());

    // --Applying split percentage and splitting commission amount as per the
	// percentage
    commAmt1 = ((commAmt1 * commSplit1) / 100);
    commAmt2 = ((commAmt2 * commSplit2) / 100);
    commAmt3 = ((commAmt3 * commSplit3) / 100);
    commAmt4 = ((commAmt4 * commSplit4) / 100);

    /*
	 * var varTotalCommission = parseFloat(commPer1 + commPer2 + commPer3 +
	 * commPer4); // E14
	 */
    //var varTotalCommission = parseFloat(commAmt1 + commAmt2 + commAmt3 + commAmt4 + commAmt5 + commAmt6 + commAmt7 + commAmt8 + commAmt9); // E14	//01/07/2022 Fields removed
    var varTotalCommission = parseFloat(commAmt1 + commAmt2 + commAmt3 + commAmt4 + commAmt5); // E14
    
    K12 = K12 - varTotalCommission;
    $("#analyserForm\\:commision1").val(Math.round(commAmt1 * 100) / 100);
    $("#analyserForm\\:commision2").val(Math.round(commAmt2 * 100) / 100);
    $("#analyserForm\\:commision3").val(Math.round(commAmt3 * 100) / 100);
    $("#analyserForm\\:commision4").val(Math.round(commAmt4 * 100) / 100);

    // Commission 2018
    $("#analyserForm\\:commision5").val(Math.round(commAmt5 * 100) / 100);
    
    //01/07/2022 Fields removed
    //$("#analyserForm\\:commision6").val(Math.round(commAmt6 * 100) / 100);
    //$("#analyserForm\\:commision7").val(Math.round(commAmt7 * 100) / 100);
    //$("#analyserForm\\:commision8").val(Math.round(commAmt8 * 100) / 100);
    //$("#analyserForm\\:commision9").val(Math.round(commAmt9 * 100) / 100);

    $("#analyserForm\\:commission").val(
            Math.round(varTotalCommission * 100) / 100);
    
	console.log("calculateProfit --> Profit CALCULATED ...");
	console.log("calculateProfit --> OLD FORM Profit = analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
	
    $("#analyserForm\\:profit").val(Math.round(K12 * 100) / 100);
    
	console.log("calculateProfit --> NEW FORM Profit = analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
	
    // added for new profit change check
    if (screen == "Modify") {
		if ($("#analyserForm\\:profit").val() != varProfitValue) 
		{
			console.log("calculateProfit --> Profit CHANGED calling adjustEffectiveDates.");
			console.log("calculateProfit --> OLD Profit = varProfitValue = "+varProfitValue);
            adjustEffectiveDates();
			console.log("calculateProfit --> FORM analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
			console.log("calculateProfit --> NEW Profit = varProfitValue = "+varProfitValue);
		}
    }

    if (varConsolePrintGlobal) {
        console.log(timeGlobal.toLocaleString() + " :varGrossProfitGlobal = "
                + varGrossProfitGlobal);
        console.log(timeGlobal.toLocaleString() + " :commPer1 = " + commAmt1);
        console.log(timeGlobal.toLocaleString() + " :commPer2 = " + commAmt2);
        console.log(timeGlobal.toLocaleString() + " :commPer3 = " + commAmt3);
        console.log(timeGlobal.toLocaleString() + " :commPer4 = " + commAmt4);
        console.log(timeGlobal.toLocaleString() + " : commPer5 = " + commAmt5);
        //console.log(timeGlobal.toLocaleString() + " : commPer6 = " + commAmt6);
        //console.log(timeGlobal.toLocaleString() + " : commPer7 = " + commAmt7);
        //console.log(timeGlobal.toLocaleString() + " : commPer8 = " + commAmt8);
        //console.log(timeGlobal.toLocaleString() + " : commPer9 = " + commAmt9);

        console.log(timeGlobal.toLocaleString() + " :commSplit1 = "
                + commSplit1);
        console.log(timeGlobal.toLocaleString() + " :commSplit2 = "
                + commSplit2);
        console.log(timeGlobal.toLocaleString() + " :commSplit3 = "
                + commSplit3);
        console.log(timeGlobal.toLocaleString() + " :commSplit4 = "
                + commSplit4);
        console.log(timeGlobal.toLocaleString() + " :varTotalCommission = "
                + varTotalCommission);
        console.log(timeGlobal.toLocaleString() + " :K12 = " + K12);
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING CalculateGP Function INSIDE CalculateProfit <---------");
    }
    CalculateGP();
    if (varConsolePrintGlobal) {
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLED CalculateGP Function INSIDE CalculateProfit --------->");
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLING TierCommission Function INSIDE CalculateProfit <---------");
    }
    
    // TierCommission(); //09292018 As Calculator function is calling it and
	// percentages already calcuated in Calculator through this call
    // commission
    if (varConsolePrintGlobal) {
        console
                .log(timeGlobal.toLocaleString()
                        + " :CALLED TierCommission Function INSIDE CalculateProfit --------->");
    }
}

function submitForm() {
    updateGrade();
    getAction();
    
    //02/09/2019 Tayyab
    var commValidationPassed = validateCommissionData();
    console.log("commValidationPassed = " + commValidationPassed);
    if (commValidationPassed == false)
    {
    	console.log("Validation FAILED in validateCommissionData()");
    	return;
    }
    
    //05/15/2019
    /*
    // console.log('age: ' + getAge("1986/30/06"));
    var ageDiff = getAge($("#analyserForm\\:dob").val());
    if(ageDiff < 18){
        alert("Age should be equal or greater than 18 years.");
        appendErrorClass("dobDiv");
        return;
    } else {
        appendSuccessClass("dobDiv");
    }
    */
    
    calculateProfit();
    
    const workEmail =  $("#analyserForm\\:workEmail").val();
	if ($.trim(workEmail).length > 0) {
		if(!validateEmail(workEmail)){
			alert('Work email is not valid');
			$("#analyserForm\\:workEmail").focus();
			appendErrorClass("workEmailDiv");
			return
		}
		else{
			appendSuccessClass("workEmailDiv");
		}
	}
	
	 const personalEmail =  $("#analyserForm\\:email").val();
		if ($.trim(personalEmail).length > 0) {
			if(!validateEmail(personalEmail)){
				alert('Personal email is not valid');
				$("#analyserForm\\:email").focus();
				appendErrorClass("emailDiv");
				return;
			}
			else{
				appendSuccessClass("emailDiv");
			}
		}
		else{
			alert('Please enter personal email');
			$("#analyserForm\\:email").focus();
			appendErrorClass("emailDiv");
			return;
		}
    
		if ($("#analyserForm\\:address1").val() == null || $.trim($("#analyserForm\\:address1").val()).length == 0) {
            alert("Please enter address 1");
            $("#analyserForm\\:address1").focus();
            appendErrorClass("address1Div");
            return;
        } else {
            appendSuccessClass("address1Div");
        }
		
		if ($("#analyserForm\\:city").val() == null || $.trim($("#analyserForm\\:city").val()).length == 0) {
            alert("Please enter city");
            $("#analyserForm\\:city").focus();
            appendErrorClass("cityDiv");
            return;
        } else {
            appendSuccessClass("cityDiv");
        }
		
        if ($("#analyserForm\\:state option:selected").val() == "") {
            alert("State needs to be Filled in");
            $("#analyserForm\\:state").focus();
            // $("#analyserForm\\:state").select();
            appendErrorClass("stateDiv");
            return;
        } else {
            appendSuccessClass("stateDiv");
        }
        
        if ($("#analyserForm\\:branch option:selected").val() == "") {
            alert("Office needs to be Filled in");
            $("#analyserForm\\:branch").focus();
            appendErrorClass("branchDiv");
            return;
        } else {
            appendSuccessClass("branchDiv");
        }
        
		if ($("#analyserForm\\:zip").val() == null || $.trim($("#analyserForm\\:zip").val()).length == 0) {
            alert("Please enter zip code");
            $("#analyserForm\\:zip").focus();
            appendErrorClass("zipDiv");
            return;
        } else {
            appendSuccessClass("zipDiv");
        }
		
        if ($("#analyserForm\\:vertical option:selected").val() == "") {
            alert("Vertical needs to be Filled in");
            $("#analyserForm\\:vertical").focus();
            appendErrorClass("verticalDiv");
            return;
        } else {
            appendSuccessClass("verticalDiv");
        }

        if ($("#analyserForm\\:product option:selected").val() == "") {
            alert("Product needs to be Filled in");
            $("#analyserForm\\:product").focus();
            appendErrorClass("productDiv");
            return;
        } else {
            appendSuccessClass("productDiv");
        }
        
        if ($("#analyserForm\\:managingDirector option:selected").val() == "") {
        	alert("Managing Director is Required.");
        	$("#analyserForm\\:managingDirector").focus();
        	return;
        }
        
        if ($("#analyserForm\\:employeeCategory option:selected").val() == "") {
            alert("Employee Category must be chosen. Note:Most anlyzer employees will be Full Time.");
            $("#analyserForm\\:employeeCategory").focus();
            appendErrorClass("employeeCategoryDiv");
            return;
        } else {
            appendSuccessClass("employeeCategoryDiv");
        }
        
        if ($("#analyserForm\\:recruitingClassification option:selected").val() == '') {
            alert("Recruiting Team needs to be filled in.")
            $("#analyserForm\\:recruitingClassification").focus();
            // $("#analyserForm\\:travelRequired").select();
            appendErrorClass("recruitingClassificationDiv");
            return;
        } else {
            appendSuccessClass("recruitingClassificationDiv");
        }
        
        if ($("#analyserForm\\:liaison").val() == "") {
            alert("HR Business Partner needs to be Filled in");
            $("#analyserForm\\:liaison").focus();
            appendErrorClass("liaisonDiv");
            return;
        } else {
            appendSuccessClass("liaisonDiv");
        }
        
        if ($("#analyserForm\\:lastName").val() == "") {
            alert("Last Name needs to be Filled in");
            $("#analyserForm\\:lastName").focus();
            appendErrorClass("lastNameDiv");
            return;
        } else {
            appendSuccessClass("lastNameDiv");
        }

        if ($("#analyserForm\\:firstName").val() == "") {
            alert("First Name needs to be Filled in");
            $("#analyserForm\\:firstName").focus();
            appendErrorClass("firstNameDiv");
            return;
        } else {
            appendSuccessClass("firstNameDiv");
        }

        // Job Board is required
        if ($("#analyserForm\\:consultantJobBoard option:selected").val() == "") {
            alert("Job Board is Required.");
            $("#analyserForm\\:consultantJobBoard").focus();
            appendErrorClass("consultantJobBoardDiv");
            return;
        } else {
            appendSuccessClass("consultantJobBoardDiv");
        }

        if ($("#analyserForm\\:gender option:selected").val() == "") {
            alert("Gender is Required.");
            $("#analyserForm\\:gender").focus();
            appendErrorClass("genderDiv");
            return;
        } else {
            appendSuccessClass("genderDiv");
        }

        // Job Title is required
        if ($("#analyserForm\\:jobTitle").val() == "") {
            alert("Job Title is Required.");
            $("#analyserForm\\:jobTitle").focus();
            appendErrorClass("jobTitleDiv");
            return;
        } else {
            appendSuccessClass("jobTitleDiv");
        }
        
        /*
        if ($("#analyserForm\\:travelRequired option:selected").val() == '') {
            alert("Travel Required needs to be filled in.")
            $("#analyserForm\\:travelRequired").focus();
            // $("#analyserForm\\:travelRequired").select();
            appendErrorClass("travelRequiredDiv");
            return;
        } else {
            appendSuccessClass("travelRequiredDiv");
        }
        */
        
        if ($("#analyserForm\\:clientManagerName").val() == "" || $("#analyserForm\\:clientSiteId").val() == "") {
		    alert("Please select Worksite Location");
		    $("#analyserForm\\:clientSiteId").focus();
		    appendErrorClass("siteLocationDiv");
		    appendErrorClass("clientSiteIdDiv");
		    //appendErrorClass("clientManagerNameDiv");
		    return;
		} else {
		    appendSuccessClass("clientManagerNameDiv");
		}
		
		if ($("#analyserForm\\:clientAddressId").val() == "") {
		    alert("Client Invoice Address needs to be Filled in");
		    $("#analyserForm\\:clientAddressId").focus();
		    appendErrorClass("clientAddressIdDiv");
		    return;
		} else {
		    appendSuccessClass("clientAddressIdDiv");
		}
		
		//12/31/2019 Tayyab
        if ($("#analyserForm\\:product option:selected").val() == "perm" ||
        		$("#analyserForm\\:empType option:selected").val() == "perm"
        	) 
        {
            alert("PERM Transactions are temporarily blocked. Please utilize interim process and fill out the Analyzer PERM Template and send to FieldOperations@disys.com.");
            $("#analyserForm\\:product").focus();
            appendErrorClass("productDiv");
            return;
        } 
        else 
        {
            appendSuccessClass("productDiv");
        }
        
    if ($("#analyserForm\\:empType option:selected").val() == "perm") 
    {
        if (parseFloat($("#analyserForm\\:commPer1").val()) > 0
                && ($("#analyserForm\\:commName1 option:selected").val() == "")) {
            alert("Account Exec Commission amount present but no person selected");
            $("#analyserForm\\:commPer1").focus();
            // $("#analyserForm\\:commPer1").select();
            appendErrorClass("commPer1Div");
            return;
        } else {
            appendSuccessClass("commPer1Div");
        }

        if (parseFloat($("#analyserForm\\:commPer2").val()) > 0
                && ($("#analyserForm\\:commName2 option:selected").val() == "")) {
            alert("Recruiter Commission amount present but no person selected");
            $("#analyserForm\\:commPer2").focus();
            // $("#analyserForm\\:commPer2").select();
            appendErrorClass("commPer2Div");
            return;
        } else {
            appendSuccessClass("commPer2Div");
        }

        if (parseFloat($("#analyserForm\\:commPer3").val()) > 0
                && ($("#analyserForm\\:commName3 option:selected").val() == "")) {
            alert("Other1 Commission amount present but no person selected");
            $("#analyserForm\\:commPer3").focus();
            // $("#analyserForm\\:commPer3").select();
            appendErrorClass("commPer3Div");
            return;
        } else {
            appendSuccessClass("commPer3Div");
        }

        if (parseFloat($("#analyserForm\\:commPer4").val()) > 0
                && ($("#analyserForm\\:commName4 option:selected").val() == "")) {
            alert("Other2 Commission amount present but no person selected");
            $("#analyserForm\\:commPer4").focus();
            // $("#analyserForm\\:commPer4").select();
            appendErrorClass("commPer4Div");
            return;
        } else {
            appendSuccessClass("commPer4Div");
        }

        //05/15/2019
        /*
        if ($("#analyserForm\\:employeeCategory option:selected").val() == "") {
            alert("Employee Category must be chosen. Note:Most analyzer employees will be IT.");
            $("#analyserForm\\:employeeCategory").focus();
            // $("#analyserForm\\:employeeCategory").select();
            appendErrorClass("employeeCategoryDiv");
            return;
        } else {
            appendSuccessClass("employeeCategoryDiv");
        }
		*/
        
        /* As worksite is mandatory for every case 16-10-2018 */
       /* if ($("#analyserForm\\:clientAddressId").val() == "") {
            alert("Client Invoice Address needs to be Filled in");
            $("#analyserForm\\:clientAddressId").focus();
            appendErrorClass("clientAddressIdDiv");
            return;
        } else {
            appendSuccessClass("clientAddressIdDiv");
        }*/

        //05/15/2019
        /*
        if ($("#analyserForm\\:lastName").val() == "") {
            alert("Last Name needs to be Filled in");
            $("#analyserForm\\:lastName").focus();
            appendErrorClass("lastNameDiv");
            return;
        } else {
            appendSuccessClass("lastNameDiv");
        }
		*/
        
        if ($("#analyserForm\\:commEffDate").val() == "") {
            alert("Commission Effective Date needs to be Filled in");
            $("#analyserForm\\:commEffDate").focus();
            appendErrorClass("commEffDateDiv");
            return;
        } else {
            appendSuccessClass("commEffDateDiv");
        }

        //01/18/2019 
        /*
        if ($("#analyserForm\\:effectiveDate").val() == "") {
            alert("Effective Date needs to be Filled in");
            $("#analyserForm\\:effectiveDate").focus();
            appendErrorClass("effectiveDateDiv");
            return;
        } else {
            appendSuccessClass("effectiveDateDiv");
        }
        
        if ($("#analyserForm\\:firstName").val() == "") {
            alert("First Name needs to be Filled in");
            $("#analyserForm\\:firstName").focus();
            appendErrorClass("firstNameDiv");
            return;
        } else {
            appendSuccessClass("firstNameDiv");
        }
		*/
        
        //05/07/2019 Tayyab
        /*
        if ($("#analyserForm\\:state").val() == "") {
            alert("State needs to be Filled in");
            $("#analyserForm\\:state").focus();
            // $("#analyserForm\\:state").select();
            appendErrorClass("stateDiv");
            return;
        } else {
            appendSuccessClass("stateDiv");
        }
        
        if ($("#analyserForm\\:empType option:selected").val() != "perm"
                && $("#analyserForm\\:dob").val() == "") {
            alert("Date of Birth needs to be Filled in");
            $("#analyserForm\\:dob").focus();
            appendErrorClass("dobDiv");
            return;
        } else {
            appendSuccessClass("dobDiv");
        }
        */

        //05/07/2019 Tayyab
        /*
        if (screen == "Add") {
            if ($("#analyserForm\\:empType option:selected").val() != "perm"
                    && ($("#analyserForm\\:ssn").val() == "" || $(
                            "#analyserForm\\:ssn").val() == "0")) {
                alert("SSN needs to be Filled in");
                $("#analyserForm\\:ssn").focus();
                appendErrorClass("ssnDiv");
                return;
            } else {
                appendSuccessClass("ssnDiv");
            }
        }
        */
        
        $("#analyserForm\\:placementDate").prop("readonly", false);
        $("#analyserForm\\:salaryAmount").prop("readonly", false);
        $("#analyserForm\\:placementPercentage").prop("readonly", false);
        $("#analyserForm\\:placementAmount").prop("readonly", false);

        $("#analyserForm\\:totHoursWorked").val("0");
        $("#analyserForm\\:billRate").val("0");
        $("#analyserForm\\:payRate").val("0");

        $("#analyserForm\\:od").val("0");
        $("#analyserForm\\:otherAmounts").val("0");
        $("#analyserForm\\:discountRate").val("0");

        $("#analyserForm\\:startDate").val("");
        $("#analyserForm\\:endDate").val("");
        if (varConsolePrintGlobal) {
            console.log("Effective date is cleaned up here 5");
        }
        $("#analyserForm\\:effectiveDate").val("");
        $("#analyserForm\\:otb").val("0");
        $("#analyserForm\\:oneTimeAmount").val("0");
        console.log('annual pay 0 - 8')
        $("#analyserForm\\:annualPay").val("0");

        /*
		 * $("#analyserForm\\:jobBillRate").val("0");
		 * $("#analyserForm\\:jobPayRate").val("0");
		 */
        $("#analyserForm\\:doubleTime").val("0");
        $("#analyserForm\\:doubleTimeBill").val("0");
        $("#analyserForm\\:perdiem").val("0");

        $("#analyserForm\\:health").val("0");
        $("#analyserForm\\:k401").val("0");
        $("#analyserForm\\:discount").val("0");

        $("#analyserForm\\:rdoPTO").val("0");
        $("#analyserForm\\:leave").val("0");
        $("#analyserForm\\:tax").val("0");
        $("#analyserForm\\:ga").val("0");
        $("#analyserForm\\:spreadWeek").val("0");
    	console.log("submitForm --> Profit SET TO ZERO FOR PERM ...");
    	console.log("submitForm --> OLD FORM Profit = analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
    	
        $("#analyserForm\\:profit").val("0");
        
    	console.log("submitForm --> NEW FORM Profit = analyserForm-profit).val() = "+$("#analyserForm\\:profit").val());
    	
        $("#analyserForm\\:cmProfit").val("0");	// 09262018
    } //End of Perm
    else 		//W2 & 1099
    {
    	//05/15/2019
        var ageDiff = getAge($("#analyserForm\\:dob").val());
        if(ageDiff < 18){
            alert("Age should be equal or greater than 18 years.");
            appendErrorClass("dobDiv");
            return;
        } else {
            appendSuccessClass("dobDiv");
        }

    	if ($("#analyserForm\\:startDate").val() == "") {
            alert("Start Date needs to be Filled in");
            $("#analyserForm\\:startDate").focus();
            appendErrorClass("startDateDiv");
            return;
        } else {
            appendSuccessClass("startDateDiv");
        }
    	
    	//05/15/2019
    	/*
        if ($("#analyserForm\\:vertical option:selected").val() == "") {
            alert("Vertical needs to be Filled in");
            $("#analyserForm\\:vertical").focus();
            appendErrorClass("verticalDiv");
            return;
        } else {
            appendSuccessClass("verticalDiv");
        }

        if ($("#analyserForm\\:product option:selected").val() == "") {
            alert("Product needs to be Filled in");
            $("#analyserForm\\:product").focus();
            appendErrorClass("productDiv");
            return;
        } else {
            appendSuccessClass("productDiv");
        }
		*/
    	
        $("#analyserForm\\:email").val(Trim($("#analyserForm\\:email").val()));
        if ($("#analyserForm\\:totHoursWorked").val() == ""
                || $("#analyserForm\\:totHoursWorked").val() == "0"
                || $("#analyserForm\\:totHoursWorked").val() == "0.0") {
            alert("Total Hours Worked Needs to be Filled in or is invalid");
            $("#analyserForm\\:totHoursWorked").focus();
            appendErrorClass("totHoursWorkedDiv");
            return;
        } else {
            appendSuccessClass("totHoursWorkedDiv");
        }
        // flsa
        if (($("#analyserForm\\:flsaStatus option:selected").val() == "ES" || $("#analyserForm\\:flsaStatus option:selected").val() == "NES")
                && $("#analyserForm\\:empType option:selected").val() == "w2"
                && ($("#analyserForm\\:annualPay").val() == "" || $(
                        "#analyserForm\\:annualPay").val() == 0)) {
            alert("Please enter the Annual Salary");
            $("#analyserForm\\:annualPay").prop("readonly", false);
            $("#analyserForm\\:payRate").attr("readonly", true);
            $("#analyserForm\\:annualPay").focus();
            appendErrorClass("annualPayDiv");
            return;
        } else {
            appendSuccessClass("annualPayDiv");
        }

        //if ($("#analyserForm\\:employeeCategory option:selected").val() != "Projects") 
        //{
	    	 if ( $("#analyserForm\\:payRate").val() == "0"  && $("#analyserForm\\:flsaStatus").val() != "ES")
	 		{
	 			alert("Please enter Pay rate");
	 			$("#analyserForm\\:payRate").focus();
	 			$("#analyserForm\\:payRate").select();
	 			return;
	 		}
	    	 
	        if ( $("#analyserForm\\:payRate").val() == "" || $("#analyserForm\\:payRate").val() == "0"
	                || parseFloat($("#analyserForm\\:payRate").val()) == 0.0 ) {
	            alert("Pay Rate Needs to be Filled in");
	            $("#analyserForm\\:payRate").title = "Pay Rate Needs to be Filled in";
	            $("#analyserForm\\:payRate").focus();
	            $("#analyserForm\\:payRate").prop("readonly", false);
	            $("#analyserForm\\:payRate").select();
	            appendErrorClass("payRateDiv");
	            return;
	        } else {
	            appendSuccessClass("payRateDiv");
	        }
	
	        if (($("#analyserForm\\:billRate").val() == "" ||  parseFloat($("#analyserForm\\:billRate").val()) == 0.0)) {
	            alert("Bill Rate Needs to be Filled in");
	            $("#analyserForm\\:billRate").focus();
	            appendErrorClass("billRateDiv");
	            return;
	        } else {
	            appendSuccessClass("billRateDiv");
	        }  
       // }
        
        if (parseFloat($("#analyserForm\\:commPer1").val()) > 0
                && ($("#analyserForm\\:commName1 option:selected").val() == "")) 
        {
            alert("Account Exec Commission amount present but no person selected");
            $("#analyserForm\\:commPer1").focus();
            // $("#analyserForm\\:commPer1").select();

            appendErrorClass("commPer1Div");
            return;
        } else {
            appendSuccessClass("commPer1Div");
        }

        if (parseFloat($("#analyserForm\\:commPer2").val()) > 0
                && ($("#analyserForm\\:commName2 option:selected").val() == "")) {
            alert("Recruiter Commission amount present but no person selected");
            $("#analyserForm\\:commPer2").focus();
            // $("#analyserForm\\:commPer2").select();
            appendErrorClass("commPer2Div");
            return;
        } else {
            appendSuccessClass("commPer2Div");
        }

        if (parseFloat($("#analyserForm\\:commPer3").val()) > 0
                && ($("#analyserForm\\:commName3 option:selected").val() == "")) {
            alert("Other1 Commission amount present but no person selected");
            $("#analyserForm\\:commPer3").focus();
            // $("#analyserForm\\:commPer3").select();
            appendErrorClass("commPer3Div");
            return;
        } else {
            appendSuccessClass("commPer3Div");
        }

        if (parseFloat($("#analyserForm\\:commPer4").val()) > 0
                && ($("#analyserForm\\:commName4 option:selected").val() == "")) {
            alert("Other2 Commission amount present but no person selected");
            $("#analyserForm\\:commPer4").focus();
            // $("#analyserForm\\:commPer4").select();
            appendErrorClass("commPer4Div");
            return;
        } else {
            appendSuccessClass("commPer4Div");
        }

        /* As worksite is mandatory for every case 16-10-2018 */
        /*if ($("#analyserForm\\:clientManagerName").val() == ""
                || $("#analyserForm\\:clientSiteId").val() == "") {
            alert("Client Manager needs to be Filled in");
            $("#analyserForm\\:clientManagerName").focus();
            appendErrorClass("clientManagerNameDiv");
            return;
        } else {
            appendSuccessClass("clientManagerNameDiv");
        }
        
        if ($("#analyserForm\\:clientAddressId").val() == "") {
            alert("Client Invoice Address needs to be Filled in");
            $("#analyserForm\\:clientAddressId").focus();
            appendErrorClass("clientAddressIdDiv");
            return;
        } else {
            appendSuccessClass("clientAddressIdDiv");
        }*/

        if ($("#analyserForm\\:effectiveDate").val() == "") {
            alert("Effective Date needs to be Filled in");
            $("#analyserForm\\:effectiveDate").focus();
            appendErrorClass("effectiveDateDiv");
            return;
        } else {
            appendSuccessClass("effectiveDateDiv");
        }

        if ($("#analyserForm\\:commEffDate").val() == "") {
            alert("Commission Effective Date needs to be Filled in");
            $("#analyserForm\\:commEffDate").focus();
            appendErrorClass("commEffDateDiv");
            return;
        } else {
            appendSuccessClass("commEffDateDiv");
        }

        if ($("#analyserForm\\:empType option:selected").val() != "perm"
                && $("#analyserForm\\:dob").val() == "") {
            alert("Date of Birth needs to be Filled in");
            $("#analyserForm\\:dob").focus();
            appendErrorClass("dobDiv");
            return;
        } else {
            appendSuccessClass("dobDiv");
        }

        if ($("#analyserForm\\:endDate").val() == "") {
            alert("End Date needs to be Filled in");
            $("#analyserForm\\:endDate").focus();
            appendErrorClass("endDateDiv");
            return;
        } else {
            appendSuccessClass("endDateDiv");
        }

        if (screen == "Add") 
        {
            /*
			 * if ($("#analyserForm\\:empType").val() != "perm" &&
			 * $("#analyserForm\\:ssn").val() == "" ||
			 * $("#analyserForm\\:ssn").val() == "0" ||
			 * !validateRate($("#analyserForm\\:ssn"))) {
			 */
            if ($("#analyserForm\\:empType").val() != "perm"
                    && $("#analyserForm\\:ssn").val() == ""
                    || $("#analyserForm\\:ssn").val() == "0") {
                alert("SSN needs to be Filled in or is invalid");
                $("#analyserForm\\:ssn").focus();
                appendErrorClass("ssnDiv");
                return;
            } else {
                appendSuccessClass("ssnDiv");
            }
            
            /*
            if ($("#analyserForm\\:empType").val() != "perm"
                && $("#analyserForm\\:ssn").val() != ""
                	&& $("#analyserForm\\:ssn").val().match(/^999.*$/)) {
            alert("SSN cannot start with 999-xx-xxxx ");
            $("#analyserForm\\:ssn").focus();
            appendErrorClass("ssnDiv");
            return;
	        } else {
	            appendSuccessClass("ssnDiv");
	        }
	        */
        }

        if ($("#analyserForm\\:empType option:selected").val() == "1099"
                && $("#analyserForm\\:contCompanyName").val() == "") {
            alert("Must choose a subcontractor for 1099 employee.");
            $("#analyserForm\\:contCompanyName").focus();
            appendErrorClass("contCompanyNameDiv");
            return;
        } else {
            appendSuccessClass("contCompanyNameDiv");
        }

        console.log("Check for the email address here...");
        /*
		 * if (emailCheck(document.forms[0].email.value) == false) { return; }
		 * 
		 * if (document.forms[0].workEmail.value != "") { if
		 * (emailCheck(document.forms[0].workEmail.value) == false) { return; } }
		 */

	    //06/03/2020
	    if 	(	$("#analyserForm\\:empType option:selected").val() == "1099" 
	    		&& 
	    		$("#analyserForm\\:employeeClass option:selected").val() != ""
	    	)
	    {      	
	        alert("Employee class selection is not allowed for 1099 employees, please reach out to Field Operations for details.");
	        $("#analyserForm\\:employeeClass").focus();
	        appendErrorClass("employeeClassDiv");
		    return;
	    }
	    else 
	    {
			appendSuccessClass("employeeClassDiv");
	    }
	   
        $("#analyserForm\\:placementDate").prop("readonly", true);
        $("#analyserForm\\:salaryAmount").prop("readonly", true);
        $("#analyserForm\\:placementPercentage").prop("readonly", true);
        $("#analyserForm\\:placementAmount").prop("readonly", true);
        
        if ($("#analyserForm\\:empType option:selected").val() == "1099") {
        	if(getRadioValue() != '0' || getRadioValue() != 0){
        		alert("PTO Days should be zero");
                $("#analyserForm\\:rdoPTODiv").focus();
                appendErrorClass("rdoPTODiv");
                return;
        	}
        }
        else {
            appendSuccessClass("rdoPTODiv");
        }
        
        //12/11/2019 Tayyab
        if (	$("#analyserForm\\:empType option:selected").val() == "w2" && 
        		(
        			$("#analyserForm\\:flsaStatus option:selected").val() == 'ES' 
        			|| $("#analyserForm\\:flsaStatus option:selected").val() == 'NES'
        		)
        	) 
        {
        	//if(getRadioValue() == '0' || getRadioValue() == 0)
        	if(parseInt($("#analyserForm\\:totalHolidays option:selected").val()) == 0)
        	{
        		alert("Holiday Days can't be Zero for W2 and Salaried Employees.");
                $("#analyserForm\\:totalHolidays").focus();
                appendErrorClass("totalHolidaysDiv");
                return;
        	}
        }
        else 
        {
            appendSuccessClass("totalHolidaysDiv");
        }
        
        if (	$("#analyserForm\\:empType option:selected").val() == "w2" && 
        		(
        			$("#analyserForm\\:flsaStatus option:selected").val() == 'ES' 
        			|| $("#analyserForm\\:flsaStatus option:selected").val() == 'NES'
        		)
        		&& 
        		(
        			$("#analyserForm\\:product option:selected").val() == "STAFF AUG"
        			|| $("#analyserForm\\:product option:selected").val() == "STAFF AUG NON-IT"
        		)
        		&& $("#analyserForm\\:gp").val() < 25.0
        	)
        {
        	//var roleId = parseInt($("#analyserForm\\:roleId").val());
        	
        	if (parseInt($("#analyserForm\\:roleId").val()) != 3)
        	{
	            alert("The GP% on this deal is not high enough to sustain a Staff Aug placement with a Salaried FLSA selection without approval. Please adjust your bill rate or salary to continue or discuss with management.");
	            $("#analyserForm\\:annualPay").prop("readonly", false);
	            $("#analyserForm\\:annualPay").focus();
	            appendErrorClass("annualPayDiv");
	            return;
        	}
        } 
        else 
        {
            appendSuccessClass("annualPayDiv");
        }
        
        //04/16/2020
        if (	$("#analyserForm\\:empType option:selected").val() == "w2" 
        		&& 
        		parseInt($("#analyserForm\\:totalHolidays option:selected").val()) != 0
        		&&
        		(	
        			$("#analyserForm\\:flsaStatus option:selected").val() == 'EH' 
	        		|| 
	        		$("#analyserForm\\:flsaStatus option:selected").val() == 'NE'
	        	)
        	)
        {       	
	        if ( !(globalHolidayEligibleCustomerList.includes($("#analyserForm\\:clientId").val())) )
	        {
	        	if ( parseInt($("#analyserForm\\:roleId").val()) != 3 )
	        	{
	        		alert("You have selected a customer that does not allow holidays for Hourly Exempt or Hourly Non-Exempt placements. If you want to provide PTO to this consultant in place of holidays please adjust the PTO leave section. If you have a scenario that requires the holidays to be updated on this record in absence of this rule, please reach out to Field Operations.");
	                $("#analyserForm\\:totalHolidays").focus();
	                appendErrorClass("totalHolidaysDiv");
		            return;
	        	}
		        else 
		        {
		            appendSuccessClass("totalHolidaysDiv");
		        }
	        }
       }
       
       //10/15/2020
       var employeeClass = $("#analyserForm\\:employeeClass option:selected").val();
       console.log("In SUBMITFORM Employee Class = "+employeeClass);
	   console.log("In SUBMITFORM globalEmployeeClassForAdditionalFringe = "+globalEmployeeClassForAdditionalFringe);
	   //06/03/2020
	   if 	(	$("#analyserForm\\:empType option:selected").val() == "w2" 
	    		&& 
	    		globalEmployeeClassForAdditionalFringe.includes(employeeClass)
	    	)
	   {
	   		//var employeeClass = $("#analyserForm\\:employeeClass option:selected").val();       	
	        if ( parseInt($("#analyserForm\\:roleId").val()) != 3  && parseInt($("#analyserForm\\:roleId").val()) != 4)
	        {
	        	alert("Employee class PYS selection is not allowed, please reach out to Field Operations for details.");
	            $("#analyserForm\\:employeeClass").focus();
	            appendErrorClass("employeeClassDiv");
		        return;
	        }
		    else 
		    {
		    	appendSuccessClass("employeeClassDiv");
		    }
	   }
        
		//02/18/2022
	   //if ($("#analyserForm\\:companyCode").val() == 'Signature')
	   if (globalCompanyCode == 'Signature')
	   {
		   	if ($("#analyserForm\\:analyzerCategory1 option:selected").val() == '' && $("#analyserForm\\:empType option:selected").val() == "w2") 
		    {
		        alert("For Signature Deals, Benefits Group is a required field. Please select a Benefits Group Value.");
		        $("#analyserForm\\:analyzerCategory1").focus();
		        appendErrorClass("analyzerCategory1Div");
		        return;
		    } else {
		        appendSuccessClass("analyzerCategory1Div");
		    }
		   	
		   /*	if ($("#analyserForm\\:portfolio").val() == '') 
		    {
		        alert("For Signature Deals, Portfolio is required. Please enter a value.");
		        $("#analyserForm\\:portfolio").focus();
		        appendErrorClass("portfolioDiv");
		        return;
		    } else {
		        appendSuccessClass("portfolioDiv");
		    }
		   	
		   	if ($("#analyserForm\\:portfolioDescription").val() == '') 
		    {
		        alert("For Signature Deals, Portfolio Description is required. Please enter a value.");
		        $("#analyserForm\\:portfolioDescription").focus();
		        appendErrorClass("portfolioDescriptionDiv");
		        return;
		    } else {
		        appendSuccessClass("portfolioDescriptionDiv");
		    }*/
	   }
    }		//end of W2 & 1099 Else part

    /*
	 * if (!validateAllNumericFields()) return;
	 */

    /*	//01/07/2020
    // check for selection of un-employed for 60 days
    if (($("#analyserForm\\:empType option:selected").val() == "w2" || $(
            "#analyserForm\\:empType option:selected").val() == "W2")
            && ($("#analyserForm\\:unEmployedForTwoMonths option:selected")
                    .val() != "N" && $(
                    "#analyserForm\\:unEmployedForTwoMonths option:selected")
                    .val() != "Y")) {
        alert("Un-Employed for Last 60 Days - Value must be selected for W2 Employees.");
        $("#analyserForm\\:unEmployedForTwoMonths").focus();
        // $("#analyserForm\\:unEmployedForTwoMonths").select();
        appendErrorClass("unEmployedForTwoMonthsDiv");
        return;
    } else {
        appendSuccessClass("unEmployedForTwoMonthsDiv");
    }
	*/
    
    //if ($("#analyserForm\\:flsaStatus option:selected").val() != "ES"	// 01/01/2019
    if ( ($("#analyserForm\\:flsaStatus option:selected").val() != "ES" && $("#analyserForm\\:flsaStatus option:selected").val() != "NES")
            && (!validateFieldNumericRange($("#analyserForm\\:totHoursWorked")
                    .val(), 1000, 1920))) {
        alert("Total hours must be between 1000 and 1920");
        $("#analyserForm\\:totHoursWorked").focus();
        $("#analyserForm\\:totHoursWorked").select();
        appendErrorClass("totHoursWorkedDiv");
        return;
    } else {
        appendSuccessClass("totHoursWorkedDiv");
    }
    
    //if ($("#analyserForm\\:empType option:selected").val() == "w2" && $("#analyserForm\\:flsaStatus option:selected").val() == "ES"	//01/01/2019
    if ($("#analyserForm\\:empType option:selected").val() == "w2" && ( $("#analyserForm\\:flsaStatus option:selected").val() == "ES" || $("#analyserForm\\:flsaStatus option:selected").val() == "NES" )
        && (!validateFieldNumericRange($("#analyserForm\\:totHoursWorked")
                .val(), 1920, 2080))) {
    	alert("Total hours must be between 1920 and 2080");
	    $("#analyserForm\\:totHoursWorked").focus();
	    $("#analyserForm\\:totHoursWorked").select();
	    appendErrorClass("totHoursWorkedDiv");
	    return;
	} else {
	    appendSuccessClass("totHoursWorkedDiv");
	}
    
    // COMM DATE CHECK
    var newCommDate = new Date($("#analyserForm\\:commEffDate").val());
    var oldCommDate = new Date(varCommStartDate);
    // alert(oldCommDate);
    // alert(newCommDate);
    var osd1 = new Date(varOldStartDate);
    
    if (newCommDate > osd1) {
        if (newCommDate < oldCommDate) {
            alert("Commission Effective Date must be greater than or equal to Cuurent Commission Effective Date which is :"
                    + oldCommDate);
            /*
			 * alert("Effective Date must be greater than or equal to Cuurent
			 * Effective Date. (Cuurent Effective Date=" + newCommDate + ").");
			 */
            $("#analyserForm\\:commEffDate").prop("readonly", false);
            $("#analyserForm\\:commEffDate").focus();
            $("#analyserForm\\:commEffDate").select();
            appendErrorClass("commEffDateDiv");
            return;
        } else {
            appendSuccessClass("commEffDateDiv");
        }
    }

    // EFF DATE CHECK varEffectiveDate
    var newEffectiveDate = new Date($("#analyserForm\\:effectiveDate").val());
    var oldEffectiveDate = new Date(varEffectiveDate);

    //01/23/2019 Tayyab
    //Effective Date must be same as start date for new adds so check required here
    oldEffectiveDate.setHours(0, 0, 0, 0);
    var allowedOldEffectiveDate = new Date(oldEffectiveDate.getTime() - (10 * 24 * 60 * 60 * 1000));
    //if (newEffectiveDate < oldEffectiveDate)	//01/23/2019
    if (newEffectiveDate < allowedOldEffectiveDate && globalCompanyCode != 'Signature') 
    {
        alert("Effective Date must be greater than or equal to Current Effective Date (minus 10 days). (Current Effective Date = " + oldEffectiveDate.toLocaleString() 
        		+ ", allowed Minimum Effective Date = " + allowedOldEffectiveDate.toLocaleString() +").");
        $("#analyserForm\\:effectiveDate").focus();
        appendErrorClass("effectiveDateDiv");
        return;
    } 
    else 
    {
        appendSuccessClass("effectiveDateDiv");
    }

    // StarDate less than EndDate
    var sd = new Date($("#analyserForm\\:startDate").val());
    var ed = new Date($("#analyserForm\\:endDate").val());
    var fd = new Date($("#analyserForm\\:effectiveDate").val());
    var osd = new Date(varOldStartDate);	


    var currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);
    
    //01/23/2019 Tayyab
    //Subtracting 10 days to allow back dating upto 10 days
    osd.setHours(0, 0, 0, 0);
    var allowedOsd = new Date(osd.getTime() - (10 * 24 * 60 * 60 * 1000));
    var allowedCurrentDate = new Date(currentDate.getTime() - (10 * 24 * 60 * 60 * 1000));

    /*
    console.log("currentDate.toLocaleString() = "+currentDate.toLocaleString());
    console.log("currentDate.getTime() = "+currentDate.getTime());
    console.log("osd.toLocaleString() = "+osd.toLocaleString());
    console.log("osd.getTime() = "+osd.getTime());
    console.log("sd.toLocaleString() = "+sd.toLocaleString());
    console.log("sd.getTime() = "+sd.getTime());
    console.log("allowedOsd.toLocaleString() = "+allowedOsd.toLocaleString());
    console.log("allowedOsd.getTime() = "+allowedOsd.getTime());
    console.log("allowedCurrentDate.toLocaleString() = "+allowedCurrentDate.toLocaleString());
    console.log("allowedCurrentDate.getTime() = "+allowedCurrentDate.getTime());
    console.log("sd.getTime() != osd.getTime() = "+sd.getTime() != osd.getTime());
    console.log("sd.getTime() < allowedOsd.getTime() = "+sd.getTime() < allowedOsd.getTime());
    */
    
    //if (sd.getTime() != osd.getTime() && sd.getTime() < currentDate.getTime())	//01/23/2019 Tayyab 
    //01/23/2019 Tayyab
    if (screen == "Add")
    {
	    if (sd.getTime() < allowedCurrentDate.getTime())		
	    {
	        alert("Start Date must be greater than or equal to Current Date (minus 10 days) (Current Date = "+ currentDate.toLocaleString() + ", allowed Minimum Start Date = "+ allowedCurrentDate.toLocaleString() + ")");
	        $("#analyserForm\\:startDate").focus();
	        $("#analyserForm\\:startDate").select();
	        appendErrorClass("startDateDiv");
	        return;
	    } 
	    else 
	    {
	        appendSuccessClass("startDateDiv");
	    }
    }
    else if (sd.getTime() != osd.getTime() && globalCompanyCode != 'Signature')
    {
	    if (sd.getTime() < allowedOsd.getTime())		
	    {
	        alert("Start Date must be greater than or equal to Current Start Date (minus 10 days) (Old Start Date = "+ osd.toLocaleString() + ", allowed Minimum Start Date = "+ allowedOsd.toLocaleString() + ")");
	        $("#analyserForm\\:startDate").focus();
	        $("#analyserForm\\:startDate").select();
	        appendErrorClass("startDateDiv");
	        return;
	    } 
	    else 
	    {
	        appendSuccessClass("startDateDiv");
	    }
    }
    
    if (sd > ed) {
        alert("End Date must be greater than or equal to Start Date.");
        $("#analyserForm\\:endDate").focus();
        $("#analyserForm\\:endDate").select();
        appendErrorClass("endDateDiv");
        return;
    } else {
        appendSuccessClass("endDateDiv");
    }

    if (fd > ed && $("#analyserForm\\:empType").val() != "perm") {
        alert("Effective Date must be between Start Date and End Date.");
        $("#analyserForm\\:effectiveDate").focus();
        $("#analyserForm\\:effectiveDate").select();
        appendErrorClass("effectiveDateDiv");
        return;
    } else {
        appendSuccessClass("effectiveDateDiv");
    }

    if (fd < sd && $("#analyserForm\\:empType").val() != "perm") {
        alert("Effective Date must be between Start Date and End Date.");
        $("#analyserForm\\:startDate").focus();
        $("#analyserForm\\:startDate").select();
        appendErrorClass("startDateDiv");
        return;
    } else {
        appendSuccessClass("startDateDiv");
    }

    // FLSA CHECKS

    if ($("#analyserForm\\:flsaStatus option:selected").val() == ""
            && $("#analyserForm\\:empType option:selected").val() == "w2") {
        alert("Please Select the FLSA Status");
        $("#analyserForm\\:flsaStatus").focus();
        // $("#analyserForm\\:flsaStatus").select();
        appendErrorClass("flsaStatusDiv");
        return;
    } else {
        appendSuccessClass("flsaStatusDiv");
    }

    if (	(	$("#analyserForm\\:flsaStatus option:selected").val() == "EH" 
    			|| $("#analyserForm\\:flsaStatus option:selected").val() == "ES"
    		 	|| $("#analyserForm\\:flsaStatus option:selected").val() == "NES" 
    		 )
            && $("#analyserForm\\:empType option:selected").val() == "w2"
            && $.trim($("#analyserForm\\:flsaReference").val()).length != 10 	//$("#analyserForm\\:flsaReference").val() == ""
        ) {
        alert("Please enter the (10 Characters) FLSA Reference");
        $("#analyserForm\\:flsaReference").prop("readonly", false);
        $("#analyserForm\\:flsaReferenceButton").prop("disabled", "");
        // $("#analyserForm\\:flsaReference").attr("readonly", false);
        $("#analyserForm\\:flsaReference").focus();
        // flsaPTOModify(); //09292018 replaced by one function for all leaves
		// calculations
        appendErrorClass("flsaReferenceDiv");
        return;
    } else {
        appendSuccessClass("flsaReferenceDiv");
    }

    if (($("#analyserForm\\:flsaStatus option:selected").val() == "NE" || $("#analyserForm\\:flsaStatus option:selected").val() == "NES")
            && $("#analyserForm\\:empType option:selected").val() == "w2"
            && ($("#analyserForm\\:otb").val() == "" || $("#analyserForm\\:otb")
                    .val() == 0)) {
        alert("Please enter OT Bill");
        $("#analyserForm\\:otb").prop("readonly", false);
        $("#analyserForm\\:otb").focus();
        $("#analyserForm\\:otb").select();
        appendErrorClass("otbDiv");
        return;
    } else {
        appendSuccessClass("otbDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() == "w2"
            && ($("#analyserForm\\:flsaStatus option:selected").val() == "NE" || $("#analyserForm\\:flsaStatus option:selected").val() == "NES")
            && wsstate == "CA"
            && ($("#analyserForm\\:doubleTimeBill").val() == "" || $(
                    "#analyserForm\\:doubleTimeBill").val() == 0)) {
        alert("Please enter DT Bill");
        $("#analyserForm\\:doubleTimeBill").prop("readonly", false);
        $("#analyserForm\\:doubleTimeBill").focus();
        $("#analyserForm\\:doubleTimeBill").select();
        appendErrorClass("doubleTimeBillDiv");
        return;
    } else {
        appendSuccessClass("doubleTimeBillDiv");
    }
    //if ($("#analyserForm\\:employeeCategory option:selected").val() != "Projects") 
    //{
	    if ($("#analyserForm\\:empType option:selected").val() == "w2"
	            && $("#analyserForm\\:flsaStatus option:selected").val() == "EH"
	            && wsstate == "CA"
	            && (parseFloat($("#analyserForm\\:payRate").val()) < minHourlyWageForCAGlobal)) {
	        var message = "In California (CA) worksite state, pay rate can't be less than "
	                + minHourlyWageForCAGlobal
	                + "  for FLSA Exempt-Hourly employees. Please recheck your transaction and fix the issue before saving";
	        alert(message);
	        $("#analyserForm\\:payRate").prop("readonly", false);
	        $("#analyserForm\\:payRate").title = message;
	        $("#analyserForm\\:payRate").focus();
	        $("#analyserForm\\:payRate").select();
	        appendErrorClass("payRateDiv");
	        return;
	    } else {
	        appendSuccessClass("payRateDiv");
	    }
	
	    
	    if ($("#analyserForm\\:flsaStatus option:selected").val() == "EH"
	            && wsstate != "CA"
	            && (parseFloat($("#analyserForm\\:payRate").val()) < 27.63)) {
	        alert("Pay rate can't be less than $27.63 for FLSA Exempt-Hourly employees. Please recheck your transaction and fix the issue before saving.");
	        $("#analyserForm\\:payRate").prop("readonly", false);
	        $("#analyserForm\\:payRate").title = "Pay rate can't be less than $27.63 for FLSA Exempt-Hourly employees. Please recheck your transaction and fix the issue before saving.";
	        $("#analyserForm\\:payRate").focus();
	        $("#analyserForm\\:payRate").select();
	        appendErrorClass("payRateDiv");
	        return;
	    } else {
	        appendSuccessClass("payRateDiv");
	    }
    //}
    
    if (($("#analyserForm\\:flsaStatus option:selected").val() == "ES"  || $("#analyserForm\\:flsaStatus option:selected").val() == "NES")
            && $("#analyserForm\\:empType option:selected").val() == "w2"
            && ($("#analyserForm\\:annualPay").val() == "" || $(
                    "#analyserForm\\:annualPay").val() == 0)) {
        $("#analyserForm\\:annualPay").prop("readonly", false);
        console.log("Par Rate Status Change Disabled # 5 @ 4528");
        $("#analyserForm\\:payRate").prop("readonly", true);
        alert("Please enter the Annual Salary");
        $("#analyserForm\\:annualPay").focus();
        appendErrorClass("annualPayDiv");
        return;
    } else {
        appendSuccessClass("annualPayDiv");
    }

    //12/11/2019 Tayyab
    if (	$("#analyserForm\\:flsaStatus option:selected").val() == "ES"
            && $("#analyserForm\\:empType option:selected").val() == "w2"
            && (	$("#analyserForm\\:annualPay").val() == "" || 
            		$("#analyserForm\\:annualPay").val() < minimumSalaryForESEmployee
            	)
        ) 
    {
        $("#analyserForm\\:annualPay").prop("readonly", false);
        console.log("Par Rate Status Change Disabled # x @ 4593");
        $("#analyserForm\\:payRate").prop("readonly", true);
        alert("Minimum required Annual Salary for any W2 & Exempt-Salaried Employee must be Equal or Greater than "+minimumSalaryForESEmployee);
        $("#analyserForm\\:annualPay").focus();
        appendErrorClass("annualPayDiv");
        return;
    } else {
        appendSuccessClass("annualPayDiv");
    }
    
    if ($("#analyserForm\\:empType option:selected").val() == "w2"
            && $("#analyserForm\\:flsaStatus option:selected").val() == "ES"
            && $("#analyserForm\\:bonusPercentage").val() == ""
            && $("#analyserForm\\:isBonusEligible").is(':checked')) {
        alert("Please select Annual Bonus Percentage Value");
        $("#analyserForm\\:bonusPercentage").focus();
        // flsaPTOModify(); //09292018 replaced by one function for all leaves
		// calculations
        appendErrorClass("bonusPercentageDiv");
        return;
    } else {
        appendSuccessClass("bonusPercentageDiv");
    }

    //if ($("#analyserForm\\:employeeCategory option:selected").val() != "Projects") 
    //{
	    if ( $("#analyserForm\\:empType option:selected").val() != "perm" && $("#analyserForm\\:payRate").val() == "0"  && ($("#analyserForm\\:flsaStatus option:selected").val() != "ES"))
		{
			alert("Please enter Pay rate");
			appendErrorClass("payRateDiv");
			return;
		}
	    else {
	        appendSuccessClass("payRateDiv");
	    }
    //}

    // FLSA CHECKS ENDS
    // NEW COMMISSION CHECKS

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commName1 option:selected").val() != ""
            && $("#analyserForm\\:splitCommissionPercentage1 option:selected")
                    .val() == "") {
        alert("Please select AE1 Split value");
        $("#analyserForm\\:splitCommissionPercentage1").focus();
        // $("#analyserForm\\:splitCommissionPercentage1").select();
        appendErrorClass("splitCommissionPercentage1Div");
        return;
    } else {
        appendSuccessClass("splitCommissionPercentage1Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commName2 option:selected").val() != ""
            && $("#analyserForm\\:splitCommissionPercentage2 option:selected")
                    .val() == "") {
        alert("Please select Recruter1 Split value");
        $("#analyserForm\\:splitCommissionPercentage2").focus();
        // $("#analyserForm\\:splitCommissionPercentage2").select();
        appendErrorClass("splitCommissionPercentage2Div");
        return;
    } else {
        appendSuccessClass("splitCommissionPercentage2Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commName3 option:selected").val() != ""
            && $("#analyserForm\\:splitCommissionPercentage3 option:selected")
                    .val() == "") {
        alert("Please select AE2 Split value");
        $("#analyserForm\\:splitCommissionPercentage3").focus();
        // $("#analyserForm\\:splitCommissionPercentage3").select();
        appendErrorClass("splitCommissionPercentage3Div");
        return;
    } else {
        appendSuccessClass("splitCommissionPercentage3Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commName4 option:selected").val() != ""
            && $("#analyserForm\\:splitCommissionPercentage4 option:selected")
                    .val() == "") {
        alert("Please select Recruter2 Split value");
        $("#analyserForm\\:splitCommissionPercentage4").focus();
        // $("#analyserForm\\:splitCommissionPercentage4").select();
        appendErrorClass("splitCommissionPercentage4Div");
        return;
    } else {
        appendSuccessClass("splitCommissionPercentage4Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:vertical option:selected").val() == "") {
        alert("Vertical is Required.");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        return;
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:product option:selected").val() == "") {
        alert("Product is Required.");
        $("#analyserForm\\:product").focus();
        appendErrorClass("productDiv");
        return;
    }

    // END NEW COMMISION CHECKS
    if ($("#analyserForm\\:clientCompanyName").val() == "") {
        alert("Client Company NAME is Required.");
        $("#analyserForm\\:clientCompanyName").focus();
        appendErrorClass("clientCompanyNameDiv");
        return;
    } else {
        appendSuccessClass("clientCompanyNameDiv");
    }

    /*//01/30/2020 Sajid
    if ($("#analyserForm\\:vertical option:selected").val() == 'Finance and Accounting'
    	 	&& ($("#analyserForm\\:employeeCategory option:selected").val() != 'FA'))
            //&& ($("#analyserForm\\:employeeCategory option:selected").val() == 'IT')) 		//Not Applied as commission tiers need to be changed as well
    {
        //alert("If Vertical is FNA then Employee Category Should be Either F&A or Part Time)");
        alert("If Vertical is FNA then Employee Category Should also be F&A or Non-IT.");
        $("#analyserForm\\:vertical").focus();
        // $("#analyserForm\\:vertical").select();
        appendErrorClass("verticalDiv");
        return;
    } 
    else 
    {
        appendSuccessClass("verticalDiv");
    }
    
    if ($("#analyserForm\\:employeeCategory option:selected").val() == 'FA'
            && $("#analyserForm\\:vertical option:selected").val() != 'Finance and Accounting') {
        alert("If Employee Category is FNA then Vertical Should also be F&A or Non-IT.");
        $("#analyserForm\\:vertical").focus();
        // $("#analyserForm\\:vertical").select();
        appendErrorClass("verticalDiv");
        return;
    } else {
        appendSuccessClass("verticalDiv");
    }
    */
    
    // END // ADDED CHECK IF BRANCH FNA, VERTICAL, CATEGORY ALSO FNA
    // 20161209
    // commission update
    if ($("#analyserForm\\:commName1 option:selected").val() != ''
            && $("#analyserForm\\:commissionModel1 option:selected").val() == '') {
        alert("Commission Model-1 is required.");
        $("#analyserForm\\:commissionModel1").focus();
        // $("#analyserForm\\:commissionModel1").select();
        appendErrorClass("commissionModel1Div");
        return;
    } else {
        appendSuccessClass("commissionModel1Div");
    }

    if ($("#analyserForm\\:commName2 option:selected").val() != ''
            && $("#analyserForm\\:commissionModel2 option:selected").val() == '') {
        alert("Commission Model-2 is required.");
        $("#analyserForm\\:commissionModel2").focus();
        // $("#analyserForm\\:commissionModel2").select();
        appendErrorClass("commissionModel2Div");
        return;
    } else {
        appendSuccessClass("commissionModel2Div");
    }

    if ($("#analyserForm\\:commName3 option:selected").val() != ''
            && $("#analyserForm\\:commissionModel3 option:selected").val() == '') {
        alert("Commission Model-3 is required.");
        $("#analyserForm\\:commissionModel3").focus();
        // $("#analyserForm\\:commissionModel3").select();
        appendErrorClass("commissionModel3Div");
        return;
    } else {
        appendSuccessClass("commissionModel3Div");
    }

    if ($("#analyserForm\\:commName4 option:selected").val() != ''
            && $("#analyserForm\\:commissionModel4 option:selected").val() == '') {
        alert("Commission Model-4 is required.");
        $("#analyserForm\\:commissionModel4").focus();
        // $("#analyserForm\\:commissionModel4").select();
        appendErrorClass("commissionModel4Div");
        return;
    } else {
        appendSuccessClass("commissionModel4Div");
    }

    if ($("#analyserForm\\:commissionModel1 option:selected").val() != 'perm' && $("#analyserForm\\:dealType option:selected").val() == '') {
        alert("Deal Type is required.");
        $("#analyserForm\\:dealType").focus();
        // $("#analyserForm\\:dealType").select();
        appendErrorClass("dealTypeDiv");
        return;
    } else {
        appendSuccessClass("dealTypeDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            //&& $("#analyserForm\\:employeeCategory option:selected").val() == 'FA'
    		&& $("#analyserForm\\:vertical option:selected").val() == globalFAVertical
            && ($("#analyserForm\\:commissionModel1 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel1 option:selected").val() == 'PRM')) {
        alert("If Vertical is "+globalFAVertical+" or Non-IT then Commission Model can't be Perm.)");
        $("#analyserForm\\:commissionModel1").focus();
        appendErrorClass("commissionModel1Div");
        return;
    } else {
        appendSuccessClass("commissionModel1Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commissionModel1 option:selected").val() == 'F&A'
            //&& $("#analyserForm\\:employeeCategory option:selected").val() != 'FA'
            && $("#analyserForm\\:vertical option:selected").val() != globalFAVertical
        ) 
    {
        alert("If Commission Model is F&A or Non-IT then Vertical should also be "+globalFAVertical+" or Non-IT");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        //$("#analyserForm\\:employeeCategory").focus();
        //appendErrorClass("employeeCategoryDiv");
        return;
    } else 
    {
    	appendSuccessClass("verticalDiv");
        //appendSuccessClass("employeeCategoryDiv");
    }
    
    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            //&& $("#analyserForm\\:employeeCategory option:selected").val() != 'FA'
            && $("#analyserForm\\:vertical option:selected").val() != globalFAVertical
            && $("#analyserForm\\:commissionModel2 option:selected").val() == 'F&A') 
    {
        //alert("If Commission Model is F&A or Non-IT then Employee Category Should also be F&A or Non-IT")
    	alert("If Commission Model is F&A or Non-IT then Vertical should also be "+globalFAVertical+" or Non-IT");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        //$("#analyserForm\\:employeeCategory").focus();
        //appendErrorClass("employeeCategoryDiv");
        return;
    } else {
    	appendSuccessClass("verticalDiv");
        //appendSuccessClass("employeeCategoryDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            //&& $("#analyserForm\\:employeeCategory option:selected").val() == 'FA'
            && $("#analyserForm\\:vertical option:selected").val() == globalFAVertical
            && ($("#analyserForm\\:commissionModel3 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel3 option:selected").val() == 'PRM')) {
        //alert("Commission Model F&A or Non-IT is not allowed with Perm.")
        alert("If Vertical is "+globalFAVertical+" or Non-IT then Commission Model can't be Perm.)");
        $("#analyserForm\\:commissionModel3").focus();
        appendErrorClass("commissionModel3Div");
        return;
    } else {
        appendSuccessClass("commissionModel3Div");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commissionModel3 option:selected").val() == 'F&A'
            //&& $("#analyserForm\\:employeeCategory option:selected").val() != 'FA'
            && $("#analyserForm\\:vertical option:selected").val() != globalFAVertical
        ) 
    {
        //alert("If Commission Model is F&A or Non-IT then  Employee Category Should also be F&A or Non-IT")
    	alert("If Commission Model is F&A or Non-IT then Vertical should also be "+globalFAVertical+" or Non-IT");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        //$("#analyserForm\\:employeeCategory").focus();
        //appendErrorClass("employeeCategoryDiv");
        return;
    } else {
    	appendSuccessClass("verticalDiv");
        //appendSuccessClass("employeeCategoryDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() != "perm"
            && $("#analyserForm\\:commissionModel4 option:selected").val() == 'F&A'
            //&& $("#analyserForm\\:employeeCategory option:selected").val() != 'FA'
            && $("#analyserForm\\:vertical option:selected").val() != globalFAVertical
        ) 
    {
        //alert("If Commission Model is F&A or Non-IT then  Employee Category Should also be F&A or Non-IT")
        alert("If Commission Model is F&A or Non-IT then Vertical should also be "+globalFAVertical+" or Non-IT");
        $("#analyserForm\\:vertical").focus();
        appendErrorClass("verticalDiv");
        //$("#analyserForm\\:employeeCategory").focus();
        //appendErrorClass("employeeCategoryDiv");
        return;
    } else {
    	appendSuccessClass("verticalDiv");
        //appendSuccessClass("employeeCategoryDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() == 'perm'
            && ($("#analyserForm\\:commissionModel1 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel1 option:selected").val() != 'PRM')) {
        alert("If Employee Type is Perm then Commission Model Should also be Perm")
        $("#analyserForm\\:commissionModel1").focus();
        // $("#analyserForm\\:commissionModel1").select();
        appendErrorClass("commissionModel1Div");
        return;
    } else {
        appendSuccessClass("commissionModel1Div");
    }

    if ($("#analyserForm\\:commissionModel1 option:selected").val() == 'PRM'
            && $("#analyserForm\\:empType option:selected").val() != 'perm') {
        alert("If Commission Model is Perm then Employee Type Should also be Perm")
        $("#analyserForm\\:empType").focus();
        // $("#analyserForm\\:empType").select();
        appendErrorClass("empTypeDiv");
        return;
    } else {
        appendSuccessClass("empTypeDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() == 'perm'
            && ($("#analyserForm\\:commissionModel2 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel2 option:selected").val() != 'PRM')) {
        alert("If Employee Type is Perm then Commission Model Should also be Perm")
        $("#analyserForm\\:commissionModel2").focus();
        // $("#analyserForm\\:commissionModel2").select();
        appendErrorClass("commissionModel2Div");
        return;
    } else {
        appendSuccessClass("commissionModel2Div");
    }

    if ($("#analyserForm\\:commissionModel2 option:selected").val() == 'PRM'
            && $("#analyserForm\\:empType option:selected").val() != 'perm') {
        alert("If Commission Model is Perm then Employee Type Should also be Perm")
        $("#analyserForm\\:empType").focus();
        // $("#analyserForm\\:empType").select();
        appendErrorClass("empTypeDiv");
        return;
    } else {
        appendSuccessClass("empTypeDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() == 'perm'
            && ($("#analyserForm\\:commissionModel3 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel3 option:selected").val() != 'PRM')) {
        alert("If Employee Type is Perm then Commission Model Should also be Perm")
        $("#analyserForm\\:commissionModel3").focus();
        // $("#analyserForm\\:commissionModel3").select();
        appendErrorClass("commissionModel3Div");
        return;
    } else {
        appendSuccessClass("commissionModel3Div");
    }

    if ($("#analyserForm\\:commissionModel3 option:selected").val() == 'PRM'
            && $("#analyserForm\\:empType option:selected").val() != 'perm') {
        alert("If Commission Model is Perm then Employee Type Should also be Perm")
        $("#analyserForm\\:empType").focus();
        // $("#analyserForm\\:empType").select();
        appendErrorClass("empTypeDiv");
        return;
    } else {
        appendSuccessClass("empTypeDiv");
    }

    if ($("#analyserForm\\:empType option:selected").val() == 'perm'
            && ($("#analyserForm\\:commissionModel4 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel4 option:selected").val() != 'PRM')) {
        alert("If Employee Type is Perm then Commission Model Should also be Perm")
        $("#analyserForm\\:commissionModel4").focus();
        // $("#analyserForm\\:commissionModel4").select();
        appendErrorClass("commissionModel4Div");
        return;
    } else {
        appendSuccessClass("commissionModel4Div");
    }

    if ($("#analyserForm\\:commissionModel4 option:selected").val() == 'PRM'
            && $("#analyserForm\\:empType option:selected").val() != 'perm') {
        alert("If Commission Model is Perm then Employee Type Should also be Perm")
        $("#analyserForm\\:empType").focus();
        // $("#analyserForm\\:empType").select();
        appendErrorClass("empTypeDiv");
        return;
    } else {
        appendSuccessClass("empTypeDiv");
    }

    if ($("#analyserForm\\:product option:selected").val() == 'PERM'
            && ($("#analyserForm\\:commissionModel1 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel1 option:selected").val() != 'PRM')) {
        alert("Only Perm Model is allowed under product PERM.")
        $("#analyserForm\\:commissionModel1").focus();
        // $("#analyserForm\\:commissionModel1").select();
        appendErrorClass("commissionModel1Div");
        return;
    } else {
        appendSuccessClass("commissionModel1Div");
    }

    if ($("#analyserForm\\:product option:selected").val() == 'PERM'
            && ($("#analyserForm\\:commissionModel2 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel2 option:selected").val() != 'PRM')) {
        alert("Only Perm Model is allowed under product PERM.");
        $("#analyserForm\\:commissionModel2").focus();
        // $("#analyserForm\\:commissionModel2").select();
        appendErrorClass("commissionModel2Div");
        return;
    } else {
        appendSuccessClass("commissionModel2Div");
    }

    if ($("#analyserForm\\:product option:selected").val() == 'PERM'
            && ($("#analyserForm\\:commissionModel3 option:selected").val() != '' && $(
                    "#analyserForm\\:commissionModel3 option:selected").val() != 'PRM')) {
        alert("Only Perm Model is allowed under product PERM.");
        $("#analyserForm\\:commissionModel3").focus();
        appendErrorClass("commissionModel3Div");
        return;
    } else {
        appendSuccessClass("commissionModel3Div");
    }

    if ($("#analyserForm\\:product option:selected").val() == 'PERM'
            && $("#analyserForm\\:commissionModel4 option:selected").val() != ''
            && $("#analyserForm\\:commissionModel4 option:selected").val() != 'PRM') {
        alert("Only Perm Model is allowed under product PERM.");
        $("#analyserForm\\:commissionModel4").focus();
        appendErrorClass("commissionModel4Div");
        return;
    } else {
        appendSuccessClass("commissionModel4Div");
    }

    // Commission 2018

    if ($("#analyserForm\\:product option:selected").val() != 'PERM'
            && $("#analyserForm\\:commissionModel2 option:selected").val() == 'PRM') {
        alert("Only Perm Model is allowed under product PERM.")
        $("#analyserForm\\:commissionModel2").focus();
        $("#analyserForm\\:commissionModel2").select();
        return;
    }
    if ($("#analyserForm\\:product option:selected").val() != 'PERM'
            && $("#analyserForm\\:commissionModel3 option:selected").val() == 'PRM') {
        alert("Perm  model is allowed only under product PERM.")
        $("#analyserForm\\:commissionModel3").focus();
        $("#analyserForm\\:commissionModel3").select();
        return;
    }
    if ($("#analyserForm\\:product option:selected").val() != 'PERM'
            && $("#analyserForm\\:commissionModel4 option:selected").val() == 'PRM') {
        alert("Perm  model is allowed only under product PERM.")
        $("#analyserForm\\:commissionModel4").focus();
        $("#analyserForm\\:commissionModel4").select();
        return;
    }
    if ($("#analyserForm\\:product option:selected").val() != 'PERM'
            && $("#analyserForm\\:commissionModel1 option:selected").val() == 'PRM') {
        alert("Perm  model is allowed only under product PERM.")
        $("#analyserForm\\:commissionModel1").focus();
        $("#analyserForm\\:commissionModel1").select();
        return;
    }

	//09/01/2020
    if ($("#analyserForm\\:branch option:selected").val() == 'Canada' && $("#analyserForm\\:analyzerCategory2 option:selected").val() != 'Canada') 
    {
        alert("If Office is Canada then Classification should be Canada.")
        $("#analyserForm\\:analyzerCategory2").focus();
        appendErrorClass("analyzerCategory2Div");
        return;
    } else {
        appendSuccessClass("analyzerCategory2Div");
    }
    
    //01/25/2021
    if ($("#analyserForm\\:vertical option:selected").val() == globalFAVertical && $("#analyserForm\\:product option:selected").val() == 'STAFF AUG') 
    {
        alert("If Vertical is "+globalFAVertical+" the product can only be Staff Aug Non-IT or Services.")
        $("#analyserForm\\:product").focus();
        appendErrorClass("productDiv");
        return;
    } else {
        appendSuccessClass("productDiv");
    }
    
  //10/25/2022
    if ($("#analyserForm\\:workFromSource option:selected").val() == '') 
    {
        alert("The Work Performed At field value must be selected.");
        $("#analyserForm\\:workFromSource").focus();
        appendErrorClass("workFromSourceDiv");
        return;
    } else {
        appendSuccessClass("workFromSourceDiv");
    }
    if ($("#analyserForm\\:workFromSource option:selected").val() == 'Hybrid' && $("#analyserForm\\:majorityWorkPerformed option:selected").val() == '') 
    {
        alert("The Majority Work Performed field value must be selected.");
        $("#analyserForm\\:majorityWorkPerformed").focus();
        appendErrorClass("majorityWorkPerformedDiv");
        return;
    } else {
        appendSuccessClass("majorityWorkPerformedDiv");
    }
    
   if ( ($("#analyserForm\\:bullhornPlacementId").val() == "" || $("#analyserForm\\:bullhornPlacementId").val()<=0 ) 
    		   && globalCompanyCode == 'Signature') {
        alert("Bullhorn Placement ID is Required.");
        $("#analyserForm\\:bullhornPlacementId").focus();
        appendErrorClass("bullhornPlacementIdDiv");
        return;
    } else {
        appendSuccessClass("bullhornPlacementIdDiv");
    }

    //05/07/2019 Tayyab
	var varPto = $("input[name='analyserForm:rdoPTO']:checked").val();
	var billablePTO = $("#analyserForm\\:billablePTO").val();
	var nonBillablePTO =  $("#analyserForm\\:nonBillablePTO").val();
	var totalHolidays = $("#analyserForm\\:totalHolidays option:selected").val();
	var billableHolidays = $("#analyserForm\\:billableHolidays").val();
	var nonBillableHolidays =  $("#analyserForm\\:nonBillableHolidays").val();
    if ($("#analyserForm\\:empType option:selected").val() == "w2") {
    	var sumOfPTO = parseFloat(billablePTO) + parseFloat(nonBillablePTO);
    	var sumOfHolidays = parseFloat(billableHolidays) + parseFloat(nonBillableHolidays);
    	
		if(sumOfPTO != varPto)
		{
			alert("Sum of Billable PTO and Non-Billable PTO must Match with Selected PTO Number");
			$("#analyserForm\\:nonBillablePTO").focus();
			return;
		}
		
		if(sumOfHolidays != totalHolidays)
		{
			alert("Sum of Billable Holidays and Non-Billable Holidays must Match with selected Total Holidays Number");
			$("#analyserForm\\:nonBillableHolidays").select();
			return;
		}
    }

    var commName1 = $("#analyserForm\\:commName1 option:selected").val();
    var commName2 = $("#analyserForm\\:commName2 option:selected").val();
    var commName3 = $("#analyserForm\\:commName3 option:selected").val();
    console.log("comm name 3 is "+commName3);
    var commName4 = $("#analyserForm\\:commName4 option:selected").val();
    var commPer1 = parseFloat($("#analyserForm\\:commPer1").val());
    var commPer2 = parseFloat($("#analyserForm\\:commPer2").val());
    var commPer3 = parseFloat($("#analyserForm\\:commPer3").val());
    var commPer4 = parseFloat($("#analyserForm\\:commPer4").val());
    var gp = parseFloat($("#analyserForm\\:gp").val());
    //12/23/2018
    var commissionModel1 = $("#analyserForm\\:commissionModel1 option:selected").val();
    var commissionModel2 = $("#analyserForm\\:commissionModel2 option:selected").val();
    var commissionModel3 = $("#analyserForm\\:commissionModel3 option:selected").val();
    var commissionModel4 = $("#analyserForm\\:commissionModel4 option:selected").val();
    var empType = $("#analyserForm\\:empType  option:selected").val();
    //var empCategory = $("#analyserForm\\:employeeCategory option:selected").val();
    var vertical = $("#analyserForm\\:vertical option:selected").val();
    var splitPercentage1 = $("#analyserForm\\:splitCommissionPercentage1 option:selected").val();
    var splitPercentage2 = $("#analyserForm\\:splitCommissionPercentage2 option:selected").val();
    var splitPercentage3 = $("#analyserForm\\:splitCommissionPercentage3 option:selected").val();
    var splitPercentage4 = $("#analyserForm\\:splitCommissionPercentage4 option:selected").val();
    
	//04/24/2019
	//Temporarily disabled the popup for Selenoium
	
    if (gp >= 10) 
    {
        if (commName1 != "" && commPer1 == 0 && globalCompanyCode != 'Signature') {
            var con = confirm("Commission Person " + commName1
                    + " has Zero commission on this deal.")
            if (con == false) {
                return;
            }
        }
        if (commName2 != "" && commPer2 == 0 && globalCompanyCode != 'Signature') {
            var con = confirm("Commission Person " + commName2
                    + " has Zero commission on this deal.")
            if (con == false) {
                return;
            }
        }
        if (commName3 != "" && commPer3 == 0 && globalCompanyCode != 'Signature') {
            var con = confirm("Commission Person " + commName3
                    + "has Zero commission on this deal.")
            if (con == false) {
                return;
            }
        }
        if (commName4 != "" && commPer4 == 0 && globalCompanyCode != 'Signature') {
            var con = confirm("Commission Person " + commName4
                    + " has Zero commission on this deal.")
            if (con == false) {
                return;
            }
        }
    } // CLOSE IF gp >= 10.00
    
    
    if (wsstate == "") {
        wsstate = $("#analyserForm\\:wstate").val();
    }
    
    if (screen == "Add") {
        if ($("#analyserForm\\:startDate").val() != $(
                "#analyserForm\\:effectiveDate").val()) {
            alert("Effective Date must be equal to Start date");
            $("#analyserForm\\:effectiveDate").focus();
            $("#analyserForm\\:effectiveDate").select();
            appendErrorClass("effectiveDateDiv");
            return;
        } else {
            appendSuccessClass("effectiveDateDiv");
        }
    }
    
    const flsaRefVal = $("#analyserForm\\:flsaReference").val();

    if( flsaRefVal != null && $.trim(flsaRefVal).length > 0){
    	const valid = $("#analyserForm\\:validFlsaRefernce").val();
    	if(valid == 'true'){
    		const prevFlsaRef = $("#analyserForm\\:prevFlsaRef").val();
    		if(flsaRefVal != prevFlsaRef){
    			 alert("Please validate flsa reference");
                 $("#analyserForm\\:flsaReference").focus();
                 $("#analyserForm\\:flsaReference").select();
                 appendErrorClass("flsaReferenceDiv");
                 return;
    		}
    		else{
    			appendSuccessClass("effectiveDateDiv");
    		}
    	}
    	else{
    		 alert("Please validate flsa reference");
             $("#analyserForm\\:flsaReference").focus();
             $("#analyserForm\\:flsaReference").select();
             appendErrorClass("flsaReferenceDiv");
             return;
    	}
    }
    	
    const validSsn = $("#analyserForm\\:validSsn").val();
    
    //05/21/2019
    if ($("#analyserForm\\:product option:selected").val() != 'PERM')
    {
	    if(validSsn == 'true'){
	    	const ssn = $("#analyserForm\\:ssn").val();
			const prevSsn = $("#analyserForm\\:prevSsn").val();
			var roleId = $("#analyserForm\\:roleId").val();
			if(roleId == 3 || roleId == 447 || roleId == 448 || roleId == 453){
				if(ssn != prevSsn){
					alert("Please validate SSN");
		            $("#analyserForm\\:ssn").focus();
		            $("#analyserForm\\:ssn").select();
		            appendErrorClass("ssnDiv");
		            return;
				}
				else{
					appendSuccessClass("ssnDiv");
				}
				/*
				if(ssn != prevSsn && ssn.match(/^999.*$/)){
					alert("SSN cannot start with 999-xx-xxxx ");
		            $("#analyserForm\\:ssn").focus();
		            $("#analyserForm\\:ssn").select();
		            appendErrorClass("ssnDiv");
		            return;
				}
				else{
					appendSuccessClass("ssnDiv");
				}
				*/
			}
	    }
	    else{
	    	 alert("Please validate SSN");
	         $("#analyserForm\\:ssn").focus();
	         $("#analyserForm\\:ssn").select();
	         appendErrorClass("ssnDiv");
	         return;
	    }
    }
    
    
    var con = confirm("Please confirm that Work Site State = " + wsstate + " ?");
    if (con == false) {
        // flsaPTOModify(); //09292018 replaced by one function for all leaves
		// calculations
        return;
    }

    // Revenue Calculation for Texax 1099 Employee; TX is subject to 1%
    // revenue deduction as tax
    var applicationDate = new Date("12/31/2012");

    // Enable all fields before submission
    enableAllFields();
    
    $('#analyserForm input').removeAttr('readonly');
     
    /*
	 * $("#analyserForm\\:submitType").val( $("#analyserForm\\:screen").val();
	 */
    // alert(screen);
    console.log('save button disabled')
	$("#saveButton").attr("disabled", true);
    if (screen == "Add") {
        $("#analyserForm\\:actualSaveButton").click();
    } else if (screen == "Modify") {
        $("#analyserForm\\:actualUpdateButton").click();
    }

    // $("#analyserForm").submit();
}	//End of SUBMIT FORM

function enableAllFields() {
    $('input:disabled, select:disabled').each(function () {
       $(this).removeAttr('disabled');
    });
}

function getAction() {
    screen = $("#analyserForm\\:action").val();
}

function searchCL() // CL DISABLED CHECK REMOVED FROM ANALYZER 11/01/2015 JUST
// REMOVED FUNCTION CALL
{
    // var isRehireCount = <%=isRehired%>;
    // alert ("test value = "+isRehireCount);
    if (isRehireCount != 1) {
        $("#analyserForm\\:liaisonName").disabled = true;
    }
}

// / 2018-01-30
function updateGrade() {
    // alert(" updateGrade 1.... : ");
    // var oldCommName1 = "<%=commName1%>";
    var newCommName1 = $("#analyserForm\\:commName1").val();
    // var oldCommName3 = "<%=commName3%>";
    var newCommName3 = $("#analyserForm\\:commName3").val();
    var falseTermination = isFalseTerminated;
    var oldCommissionPersonGrade1 = $("#analyserForm\\:commissionPersonGrade1")
            .val();
    var oldCommissionPersonGrade3 = $("#analyserForm\\:commissionPersonGrade3")
            .val();
    var isRehireCount = isRehired;
    if ($("#analyserForm\\:product").val() == "SERVICES") {
        if ($("#analyserForm\\:commName1").val() != '') {
            // /alert("test---2");
            if ($("#analyserForm\\:commissionPersonGrade1").val() == '') {
                // alert("test---3");
                ae1Grade();
            }
        }
        if($("#analyserForm\\:commName3").val() != ''){ 
            // alert("test---5");
            if($("#analyserForm\\:commissionPersonGrade1").val() == ''){ 
                // alert("test---6");
                ae2Grade();
            }
        }
    }
    
    if ($("#analyserForm\\:product").val() != "SERVICES")	//01/23/2019 Tayyab
    {
        if($("#analyserForm\\:commName1").val() != '' && $("#analyserForm\\:commissionModel1").val() == "ITC")
        { 
            if($("#analyserForm\\:commissionPersonGrade1").val() == '')
            {
                    ae1Grade();
                }
        }
        if ($("#analyserForm\\:commName3").val() != ''
        	&& $("#analyserForm\\:commissionModel3").val() == "ITC"	//01/23/2019 Tayyab
        	) 
        { 
            if ($("#analyserForm\\:commissionPersonGrade3").val() == '') 
            { 
                ae2Grade();
            }
        }
    }
    
    if ($("#analyserForm\\:commName1").val() == '' || ($("#analyserForm\\:product").val() != "SERVICES" && $("#analyserForm\\:commissionPersonGrade1").val() != '' && $("#analyserForm\\:commissionModel1").val() != "ITC"))	//01/23/2019 Tayyab
    {
        $("#analyserForm\\:commissionPersonGrade1").val('');
        console.log("commissionPersonGrade1 is cleaned here 3");
    }
    if ($("#analyserForm\\:commName3").val() == '' || ($("#analyserForm\\:product").val() != "SERVICES" &&  $("#analyserForm\\:commissionModel3").val() != "ITC"))		//01/23/2019 Tayyab
    {
        $("#analyserForm\\:commissionPersonGrade3").val('');
        console.log("commissionPersonGrade3 is cleaned here 3");
    }
}

function ae1Grade() {
    $("#analyserForm\\:ae1Grade1Button").click();
}

function ae2Grade() {
    $("#analyserForm\\:ae1Grade2Button").click();
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

//12/23/2018 Tayyab
//To give user realtime alerts but only for one time and submit forms stops the users, if not corrected
function validateCommissionData()
{
    var commName1 = $("#analyserForm\\:commName1 option:selected").val();
    var commName2 = $("#analyserForm\\:commName2 option:selected").val();
    var commName3 = $("#analyserForm\\:commName3 option:selected").val();
    var commName4 = $("#analyserForm\\:commName4 option:selected").val();
    var commPer1 = parseFloat($("#analyserForm\\:commPer1").val());
    var commPer2 = parseFloat($("#analyserForm\\:commPer2").val());
    var commPer3 = parseFloat($("#analyserForm\\:commPer3").val());
    var commPer4 = parseFloat($("#analyserForm\\:commPer4").val());
    var commissionModel1 = $("#analyserForm\\:commissionModel1 option:selected").val();
    var commissionModel2 = $("#analyserForm\\:commissionModel2 option:selected").val();
    var commissionModel3 = $("#analyserForm\\:commissionModel3 option:selected").val();
    var commissionModel4 = $("#analyserForm\\:commissionModel4 option:selected").val();
    var vertical = $("#analyserForm\\:vertical option:selected").val();
    var splitPercentage1 = $("#analyserForm\\:splitCommissionPercentage1 option:selected").val();
    var splitPercentage2 = $("#analyserForm\\:splitCommissionPercentage2 option:selected").val();
    var splitPercentage3 = $("#analyserForm\\:splitCommissionPercentage3 option:selected").val();
    var splitPercentage4 = $("#analyserForm\\:splitCommissionPercentage4 option:selected").val();
    //var companyCode = $("#analyserForm\\:companyCode").val();
    
	//All New Checks will go here
	//Split between PERM models can't be mised & matched	//11/30/2018
	if( 	
			(commissionModel1 == 'PRM' || commissionModel2 == 'PRM' || commissionModel3 == 'PRM' || commissionModel4 == 'PRM') &&
			(	(commissionModel1 != 'PRM' && commissionModel1 != '') ||
				(commissionModel2 != 'PRM' && commissionModel2 != '') ||
				(commissionModel3 != 'PRM' && commissionModel3 != '') ||
				(commissionModel4 != 'PRM' && commissionModel4 != '')
			)
		)
 	{
		alert("Commissin Model PERM ia allowed with only Commission Model PERM for All Commission Persosn ...");
		$("#analyserForm\\:commissionModel2").val('').trigger('change.select2');
		$("#analyserForm\\:commissionModel3").val('').trigger('change.select2');
		$("#analyserForm\\:commissionModel4").val('').trigger('change.select2');
		$("#analyserForm\\:commPer2").val(0);
		$("#analyserForm\\:commPer3").val(0);
		$("#analyserForm\\:commPer4").val(0);
		$("#analyserForm\\:commissionModel2").focus();
		//return;
		return false; //02/09/2019 Tayyab
 	} 
	//Split Not allowed for ITC Commission for both AEs & Recruiters
	if (	(commName1  != '' || commName3  != '') && (commissionModel1 == 'ITC' && commissionModel3 == 'ITC'))
	{
		$("#analyserForm\\:splitCommissionPercentage3").val(0).trigger('change.select2');
		$("#analyserForm\\:commissionModel3").val('').trigger('change.select2');
		alert("Commission Split between AE1 & AE2 for ITC Commission Model is not allowed. Please fix.")
        //return;
		return false; //02/09/2019 Tayyab
	}
	if (	(commName2  != '' || commName4  != '') && (commissionModel2 == 'ITC' && commissionModel4 == 'ITC'))
	{
		$("#analyserForm\\:splitCommissionPercentage4").val(0).trigger('change.select2');
		$("#analyserForm\\:commissionModel4").val('').trigger('change.select2');
		alert("Commission Split between Recruiter1 & Recruiter2 for ITC Commission Model is not allowed. Please fix.")
        //return;
		return false; //02/09/2019 Tayyab
	}
	//Total Split percentage can't be greater than 100
    var totalAESplitPercenatge = parseFloat(splitPercentage1) + parseFloat(splitPercentage3);
    var totalRecruiterSplitPercenatge = parseFloat(splitPercentage2) + parseFloat(splitPercentage4);
    if (totalAESplitPercenatge > 100) 
    {
        alert("Total split commission percentage for AE1 and AE2 should not exceed 100%.");
        $("#analyserForm\\:splitCommissionPercentage1").focus();
        appendErrorClass("splitCommissionPercentage1Div");
        appendErrorClass("splitCommissionPercentage3Div");
        //return;
        return false; //02/09/2019 Tayyab
    }
    if (totalRecruiterSplitPercenatge > 100) 
    {
        alert("Total split commission percentage for Recruiter1 and Recruiter2 should not exceed 100%.");
        $("#analyserForm\\:splitCommissionPercentage2").focus();
        appendErrorClass("splitCommissionPercentage2Div");
        appendErrorClass("splitCommissionPercentage4Div");
        //return;
        return false; //02/09/2019 Tayyab
    }
    
    
    //Commented by Reshma Rupanagudi as part of portfolio Project
    /*if (globalCompanyCode == 'Signature')	//01/07/2022 Signature details
    {
    	if( commName1 == '' )
    	{
    		alert("AE-1 commission person is missing, please select s commission person from the list?");
    		$("#analyserForm\\:commName1").focus();
    		appendErrorClass("commName1Div");
    		return false;
    	}
    	if( commName2 == '' )
        {
        	alert("Recruiter 1 commission person is missing, please select s commission person from the list?");
        	$("#analyserForm\\:commName2").focus();
        	appendErrorClass("commName2Div");
        	return false;
        }
    	if( commName3 == '' )
        {
    		$("#analyserForm\\:dealPortfolio3AE2").val("");
        }
    	if( commName4 == '' )
        {
    		$("#analyserForm\\:dealPortfolio4REC2").val("");
        }

    	if (totalAESplitPercenatge != 100) 
        {
            alert("Total split commission percentage for AE1 and AE2 should be 100% for Signature deals.");
            $("#analyserForm\\:splitCommissionPercentage1").focus();
            appendErrorClass("splitCommissionPercentage1Div");
            appendErrorClass("splitCommissionPercentage3Div");
            return false;
        }
        if (totalRecruiterSplitPercenatge != 100) 
        {
            alert("Total split commission percentage for Recruiter1 and Recruiter2 should be 100% for Signature deals.");
            $("#analyserForm\\:splitCommissionPercentage2").focus();
            appendErrorClass("splitCommissionPercentage2Div");
            appendErrorClass("splitCommissionPercentage4Div");
            return false;
        }
        
        var ae1Portfolio = $("#analyserForm\\:dealPortfolio1AE1").val();
        var rec1Portfolio = $("#analyserForm\\:dealPortfolio2REC1").val();
        var ae2Portfolio = $("#analyserForm\\:dealPortfolio3AE2").val();
        var rec2Portfolio = $("#analyserForm\\:dealPortfolio4REC2").val();
        
        if (ae1Portfolio == '') 
        {
            alert("AE1 Portfolio must be entered. Please enter a value.");
            $("#analyserForm\\:dealPortfolio1AE1").focus();
            appendErrorClass("dealPortfolio1AE1Div");
            return false;
        }
        if (rec1Portfolio == '') 
        {
            alert("Recruiter 1 Portfolio must be entered. Please enter a value.");
            $("#analyserForm\\:dealPortfolio2REC1").focus();
            appendErrorClass("dealPortfolio2REC1Div");
            return false;
        }
        
        if (ae2Portfolio == '' && commName3 != '') 
        {
            alert("AE2 Portfolio must be entered. Please enter a value.");
            $("#analyserForm\\:dealPortfolio3AE2").focus();
            appendErrorClass("dealPortfolio3AE2Div");
            return false;
        }
        if (rec2Portfolio == '' && commName4 != '') 
        {
            alert("Recruiter 2 Portfolio must be entered. Please enter a value.");
            $("#analyserForm\\:dealPortfolio4REC2").focus();
            appendErrorClass("dealPortfolio4REC2Div");
            return false;
        }
        
    	if( 
    			(commName1 != "" && commName2 != "" && commName1 == commName2 && !globalSigDupCommPersonsList.includes(commName1))
    			||
    			(commName1 != "" && commName4 != "" && commName1 == commName4 && !globalSigDupCommPersonsList.includes(commName1))
    			||
    			(commName3 != "" && commName2 != "" && commName3 == commName2 && !globalSigDupCommPersonsList.includes(commName3))
    			||
    			(commName3 != "" && commName4 != "" && commName3 == commName4 && !globalSigDupCommPersonsList.includes(commName3))
    	  )
    	{
    			alert("Same commission person will not be allowed to be listed for more than one commission roles (i.e. twice).");
    			$("#analyserForm\\:commName1").focus();
    			appendErrorClass("commName1Div");
    			return false;
    	}
    }
    
    //Duplicate commission person is only allowed for F&A Vertical
    //if ( vertical != "Finance and Accounting")
    if ( vertical != globalFAVertical && globalCompanyCode != 'Signature')
    {
    	if( 
    			(commName1 != "" && commName2 != "" && commName1 == commName2 && !globalDISYSDupCommPersonsList.includes(commName1))
    			||
    			(commName1 != "" && commName4 != "" && commName1 == commName4 && !globalDISYSDupCommPersonsList.includes(commName1))
    			||
    			(commName3 != "" && commName2 != "" && commName3 == commName2 && !globalDISYSDupCommPersonsList.includes(commName3))
    			||
    			(commName3 != "" && commName4 != "" && commName3 == commName4 && !globalDISYSDupCommPersonsList.includes(commName3))
    	  )
    	{
    			alert("Same commission person will not be allowed to be listed for more than one commission roles (i.e. twice)");
    			$("#analyserForm\\:commName1").focus();
    			appendErrorClass("commName1Div");
    			return false;
    	}
    }*/
	return true; //all passed	//01/07/2021 Moved out of the last if condition
}