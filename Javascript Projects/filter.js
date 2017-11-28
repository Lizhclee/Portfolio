function postReply() {
	var row1 = document.createElement("tr");
	var row2 = document.createElement("tr");
	var th = document.createElement("th");
	var td = document.createElement("td");
	var img = document.createElement("img");
	var username = document.createElement("span");
	var timestamp = document.createElement("span");
	var message;
	var text = document.getElementById("reply").value.split("\n");
	var date = new Date();
	
	img.setAttribute("src", "user.png");
	username.setAttribute("class", "username");
	timestamp.setAttribute("class", "date");
	
	username.appendChild(document.createTextNode("Anonymous"));
	timestamp.appendChild(document.createTextNode("Posted on " + (date.getMonth()+1) + "-" + date.getDate() + "-" +
			date.getFullYear() + " at " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()));
	th.appendChild(img);
	th.appendChild(username);
	th.appendChild(document.createElement("br"));
	th.appendChild(timestamp);
	row1.appendChild(th);
	
	text = filterText(text);
	
	for (var i = 0; i < text.length; i++) {
		message = document.createElement("p");
		message.appendChild(document.createTextNode(text[i]));
		td.appendChild(message);
	}
	row2.appendChild(td);
	
	document.getElementById("forum").appendChild(row1);
	document.getElementById("forum").appendChild(row2);
	
	document.getElementById("reply").value = "";
}

function filterText(text) {
	for (var i = 0; i < text.length; i++) {
		text[i] = text[i].replace(/star wars+/ig, "Danganronpa");
		text[i] = text[i].replace(/luke+/ig, "Makoto");
		text[i] = text[i].replace(/skywalker+/ig, "Naegi");
		text[i] = text[i].replace(/(princess ?)?leia+/ig, "Kyoko Kirigiri");
		text[i] = text[i].replace(/han+/ig, "Mondo");
		text[i] = text[i].replace(/solo+/ig, "Owada");
		text[i] = text[i].replace(/1138+/ig, "11037");
	}
	
	return text;
}