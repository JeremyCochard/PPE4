<?php
//include("gestionBase.php");
//contrôle de réception de paramètre
//if(isset($_REQUEST["opperation"])){
	
	//demande de récupération du dernier profil
	//if($_REQUEST["operation"]=="test"){
		//try{
			$connexion=connexionPDO();
			$req=$connexion->prepare("INSERT INTO `utilisateur` (`numUtilisateur`, `raisonSociale`, `login`, `password`, `type`) VALUES (4, 'SA THOLDI', 'acar', '123', 'client');");
			$req->execute();
			
			if($req){
				$reponse="ok";
			}
			
			//echo (£reponse);
		/*}catch(PDOExeption $e){
			print("Erreur !%".$e->getMessage()
			return null;
		}*/
		
	//enregistrement nouveau profil
/*	}elseif($_REQUEST["operation"]=="enRegle"){
		
		try{
			//récupération des données POST
			$lesdonnes=$_REQUEST["lesdonnées"];
			$donne=json_decode($lesdonnes);
			//données
			
			print("enregistrer%")
			$connexion=connexionPDO();
			$larequete="inset into";
			$req=$connexion->prepare($larequete);
			$req->execute();
			
		}catch(PDOExeption $e){
			print("Erreur !%".$e->getMessage();
			return null;
		}
		
	}*/
//}


?>