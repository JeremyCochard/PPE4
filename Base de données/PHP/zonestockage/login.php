<?php
include("gestionBase.php");
session_start();
		try{
			$connexion=connexionPDO();
			
			$login=$_REQUEST['login'];
			$password=$_REQUEST['password'];
				
			if(!empty($login) && !empty($password)){
				
				$req="SELECT count(*) as nb From utilisateur where login='$login' and password ='$password'";

				$prep=$connexion->prepare($req);
				$prep->execute();
				
				$_SESSION["login"]=$login;
				
				$numCli=getNumUtlisateur();
				$_SESSION["numCli"]=$numCli;
		
				$resultat = $prep->fetch();
						
				if($resultat['nb']==1){
					echo('Authentification');
				}else{
					echo('Aucune Authentification');
				}					
			}else{
				echo ("Erreur Authentification !");
			}
		}catch(PDOExeption $e){
			print "Erreur de connexion PDO";
			die();
		}		
?>
