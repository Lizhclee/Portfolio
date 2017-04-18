<?php
	$fname = $_POST['first'];
	$lname = $_POST['last'];
	$email = $_POST['email'];
	
	$confirm = "sorry.html";

	$users = fopen("users.txt", "r");

	while (!feof($users) && $confirm != "thankyou.html") {
		$line = fgets($users);
		$first = lcfirst( strtok($line, " ") );
		$last = lcfirst( strtok(" ") );
		$address = trim( strtok(" ") );
		
		if ( strtolower($fname) == $first && strtolower($lname) == $last && strtolower($email) == $address ) {
			$confirm = "thankyou.html";
		}
	}

	fclose($users);
?>
<script type='text/javascript'>
	window.location.replace('<?=$confirm?>');
</script>