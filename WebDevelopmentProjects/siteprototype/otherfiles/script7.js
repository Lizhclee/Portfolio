/* Helper function */
function $(id){
	var element = document.getElementById(id);
	if( element == null )
		alert( 'Programmer error: ' + id + ' does not exist.' );
	return element;
}

/* Validate the entire form; Must clear error message if input is valid */
function formValidate() {
	var valid = true;
	
	// Clear error messages
	$('itmPosition').className = "";
	$('errPosition').className = "";
	$('errPosition').innerHTML = "";
	
	$('itmPassedWeb').className = "";
	$('errPassedWeb').className = "";
	$('errPassedWeb').innerHTML = "";
	
	$('itmStartDate').className = "";
	$('errStartDate').className = "";
	$('errStartDate').innerHTML = "";
	
	$('itmResume').className = "";
	$('errResume').className = "";
	$('errResume').innerHTML = "";
	
	$('itmName').className = "";
	$('errName').className = "";
	$('errName').innerHTML = "";
	
	$('itmEmail').className = "";
	$('errEmail').className = "";
	$('errEmail').innerHTML = "";
	
	$('itmPhone').className = "";
	$('errPhone').className = "";
	$('errPhone').innerHTML = "";
	
	$('errSubmit').className = "";
	$('errSubmit').innerHTML = "";
	
	// Add error messages for invalid fields
	// check the selected position
	if ( !testPositionValid('selPosition') ) {
		$('itmPosition').className = "error";
		$('errPosition').className = "errorMessage";
		$('errPosition').innerHTML = "Please select a position";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the radio buttons
	if ( !testRadioSelected('radPassedWeb_0', 'radPassedWeb_1') ) {
		$('itmPassedWeb').className = "error";
		$('errPassedWeb').className = "errorMessage";
		$('errPassedWeb').innerHTML = "Warning: you will not be considered if you have not passed COMP 1536";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the date
	if ( !testValidDate('txtStartDate_0', 'txtStartDate_1', 'txtStartDate_2') ) {
		$('itmStartDate').className = "error";
		$('errStartDate').className = "errorMessage";
		$('errStartDate').innerHTML = "Please enter a valid date after Dec. 31, 2012";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the file
	if ( !testValidFile_2('filResume') ) {
		$('itmResume').className = "error";
		$('errResume').className = "errorMessage";
		$('errResume').innerHTML = "Please enter a resume in Word or PDF format";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the name
	if ( !testValidName_2('txtFirstName', 'txtLastName') ) {
		$('itmName').className = "error";
		$('errName').className = "errorMessage";
		$('errName').innerHTML = "Please enter your first and last name with only alphabetic characters";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the email
	if ( !testValidEmail('txtEmail') ) {
		$('itmEmail').className = "error";
		$('errEmail').className = "errorMessage";
		$('errEmail').innerHTML = "Email must not be blank and end in .com, .ca, or .org";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}
	
	// check the phone number
	if ( !testValidPhone_2('txtPhone_0', 'txtPhone_1', 'txtPhone_2') ) {
		$('itmPhone').className = "error";
		$('errPhone').className = "errorMessage";
		$('errPhone').innerHTML = "Please enter a valid phone number in the format ###-###-####";
		$('errSubmit').className = "errorMessage";
		$('errSubmit').innerHTML = "Please correctly fill out the highlighted field(s)";
		valid = false;
	}

	return valid;
}

/* Select list */
function testPositionValid(id){
	return ($(id).selectedIndex != 0);
}

/* Radio buttons */
function testRadioSelected(id1, id2){
	return ( $(id1).checked && !$(id2).checked );
}

/* Date */
function testValidDate(id1, id2, id3){
	return ( ( $(id1).value >= 1 && $(id1).value <= 12 ) &&
	( $(id2).value >= 1 && $(id2).value <= 31 ) && 
	( $(id3).value >= 2013 && $(id3).value <= 2999 ) );
}

/* Uploaded Resume */
function testValidFile(id){
	var str = $(id).value;
	return ( str.length < 1 ||
		str.substring(str.length-3) == "doc" || 
		str.substring(str.length-4) == "docx" || 
		str.substring(str.length-3) == "pdf"  );
}

function testValidFile_2(id){
	pattern = /\.(doc|docx|pdf)$/;
	return ( $(id).value.length < 1 || pattern.test($(id).value) );
}

/* Name */
function testValidName(id1, id2){
	return ( $(id1).value.length > 0 && $(id2).value.length > 0 );
}

function testValidName_2(id1, id2){
	pattern = /\W+/;
	if ( $(id1).value.length < 1 || $(id2).value.length < 1 || pattern.test($(id1).value) || pattern.test($(id2).value) ) {
		return false;
	} else {
		return true;
	}
}

/* Email */
function testValidEmail(id){
	var str = $(id).value;
	return ( str.length > 0 &&
		(str.substring(str.length-4) == ".com" ||
		str.substring(str.length-3) == ".ca" ||
		str.substring(str.length-4) == ".org") );
}

/* Phone number */
function testValidPhone(id1, id2, id3){
	return ( ( $(id1).value.length < 1 || $(id2).value.length < 1 || $(id3).value.length < 1 ) ||
		( $(id1).value >= 100 && $(id1).value <= 999 &&
		$(id2).value >= 100 && $(id2).value <= 999 &&
		$(id3).value >= 1000 && $(id3).value <= 9999 ) );
}

function testValidPhone_2(id1, id2, id3){
	pattern1 = /^[\d]{3}$/;
	pattern2 = /^[\d]{4}$/;
	
	return ( pattern1.test($(id1).value) && pattern1.test($(id2).value) && pattern2.test($(id3).value) );
}