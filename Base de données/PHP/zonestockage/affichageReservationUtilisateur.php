<?php
include_once("gestionBase.php");
session_start();

		try{
			
			$login=$_REQUEST['login'];
			if(!empty($login)){
				
				$numCli=getNumUtlisateur($login);
			
				$reservationClient=getReservation($numCli);			
				
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