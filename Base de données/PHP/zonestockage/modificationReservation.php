<?php
include_once("gestionBase.php");
session_start();

	try{
		$login=$_REQUEST['login'];
		$numReservation=$_REQUEST['numReserv'];
		$duréeJ=$_REQUEST['duréeJ'];
		$quantite=$_REQUEST['quantite'];
		
		if(!empty($duréeJ) && !empty($numReservation) && !empty($duréeJ) && !empty($quantite)){
		
			$dateReservation=date('20y-m-d');
		
			$connexion=connexionPDO();
		
			$strDuréeJ="+".$duréeJ." days";
			$dateFinReservation=date('20y-m-d', strtotime($strDuréeJ));
			
			$numUtilisateur=getNumUtlisateur($login);
			
			$etat="encours";
			
			$req="UPDATE reservation SET dateReservation='$dateReservation', datePrevueStockage='$dateFinReservation', nbJoursDeStockagePrevu='$duréeJ', quantite='$quantite', etat='$etat', numClient='$numUtilisateur' WHERE id='$numReservation'";
			$prep=$connexion->prepare($req);
			$prep->execute();
			
			if(!empty($prep)){
				echo ("UpdateOk");
			}else{
				echo ("ErreurRes");
			}	
		}else{
			echo ("Erreur demande réservation !");
		}
	}catch(PDOExeption $e){
		print "Erreur de connexion PDO";
		die();
	}
?>