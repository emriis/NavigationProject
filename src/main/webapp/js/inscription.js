var print =true;

function verifPrenom() {
	let saisie = document.getElementById("prenom").value;
	if (saisie.length < 3) {
		document.getElementById("spanPrenom").innerHTML
			= "Le pr&eacute;nom est trop court !";
	}else {
		document.getElementById("spanPrenom").innerHTML
			= "";
	}
}

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

function printPasswordConf() {
	if (print){
		document.getElementById("mdpConf").type = "text";
	document.getElementById("oeilConf").src = "img/oeilouvert.png";
	print =false;
	} else {
		document.getElementById("mdpConf").type = "password";
	document.getElementById("oeilConf").src = "img/oeilferme.png";
	print =true;
	}
}
