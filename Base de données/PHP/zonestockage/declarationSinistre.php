<?php
include_once("gestionBase.php");
session_start();


	try{
		
		if(!empty($_REQUEST['numeroDecla'])&& !empty($_REQUEST['libelle']) && !empty($_REQUEST['numeroContainer'])){
			
			$numeroDecla=$_REQUEST['numeroDecla'];
			$libelle=$_REQUEST['libelle'];
			$numeroContainer=$_REQUEST['numeroContainer'];
		
			$connexion=connexionPDO();
			
			$req="INSERT INTO `declarationdesinistre` (`libelle`, `numeroDeContainer`, `numeroDeDeclaration`) VALUES ('$libelle', $numeroContainer, $numeroDecla)";
			$prep=$connexion->prepare($req);
			$prep->execute();
			
			if(!empty($prep)){
				echo ("DeclaOK");
			}else{
				echo ("ErreurDecla");
			}	
		}else{
			echo ("Erreur");
		}
	}catch(PDOExeption $e){
		print "Erreur de connexion PDO";
		die();
	}
?>