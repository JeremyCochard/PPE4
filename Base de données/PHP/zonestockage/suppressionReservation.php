<?php
include_once("gestionBase.php");
session_start();


		try{			
			//$idReservation=$_POST['idReservation']="14";
			$idReservation=$_REQUEST['idReservation'];
				
			if(!empty($idReservation)){
				$connexion=connexionPDO();
				
				$_SESSION["idReservation"]=$idReservation;
				
				$suppressionReservation=suppressionReservation();
				
				if(!empty($suppressionReservation)){
					echo($suppressionReservation);
				}else{
					echo("Aucune réservation n'a été trouvée");
				}					
			}else{
				echo ("Erreur Suppression réservation !");
			}
		}catch(PDOExeption $e){
			print "Erreur de connexion PDO";
			die();
		}		

?>