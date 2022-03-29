<?php
include_once("gestionBase.php");
//session_start();

		try{
			$connexion=connexionPDO();
			
			//$numClient=$_POST['numClient']="3";
			$login=$_REQUEST['login'];
				
			if(!empty($numClient)){
				
				//$_SESSION["numClient"]=$numClient;
				
				$_SESSION["login"]=$login;
				
				$numCli=getNumUtlisateur();
				$_SESSION["numCli"]=$numCli;
				$reservationClient=getReservation()
				
				//$numReservationClient=getReservation();
				
				//$numReservationClient=$_SESSION["numCli"];
				
				
				if(!empty($reservationClient)){
					echo($reservationClient);
				}else{
					echo("Aucune résertion");
				}
			}else{
				echo ("Erreur Recherche réservation !");
			}
		}catch(PDOExeption $e){
			print "Erreur de connexion PDO";
			die();
		}		

?>