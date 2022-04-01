<?php
include_once("gestionBase.php");
session_start();


		try{			
			$idReservation=$_REQUEST['numSuppReserv'];
			//$idReservation=$_REQUEST['idReservation'];
				
			if(!empty($idReservation)){
				$connexion=connexionPDO();
				
				//$_SESSION["idReservation"]=$idReservation;
				
				$reponseSuppression=suppressionReservation($idReservation);
				
				if(!empty($reponseSuppression==True)){
					echo("SuppOk");
				}else{
					echo("ErreurSupp");
				}					
			}else{
				echo ("Erreur Suppression réservation !");
			}
		}catch(PDOExeption $e){
			print "Erreur de connexion PDO";
			die();
		}		

?>