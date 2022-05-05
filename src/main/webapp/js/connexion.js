var print =true;

function printPassword() {
	if (print){
		document.getElementById("mdp").type = "text";
	document.getElementById("oeil").src = "img/oeilouvert.png";
	print =false;
	} else {
		document.getElementById("mdp").type = "password";
	document.getElementById("oeil").src = "img/oeilferme.png";
	print =true;
	}
	
}
