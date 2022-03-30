<?php
include_once("gestionBase.php");
session_start();


	try{
		$login=$_REQUEST['login'];
		$nbEmplacement=$_REQUEST['nbEmplacement'];
		$duréeJ=$_REQUEST['duréeJ'];
		$quantite=$_REQUEST['quantite'];
		if(!empty($nbEmplacement) && !empty($duréeJ) && !empty($quantite) && !empty($login)){
		
			$dateReservation=date('20y-m-d');
		
			$connexion=connexionPDO();
		
			$strDuréeJ="+".$duréeJ." days";
			$dateFinReservation=date('20y-m-d', strtotime($strDuréeJ));
			
			$numUtilisateur=getNumUtlisateur($login);
			
			$etat="encours";
			
			$req="INSERT INTO `reservation` (`datePrevueStockage`, `dateReservation`, `nbJoursDeStockagePrevu`, `quantite`, `etat`, `numClient`) VALUES ( DATE '$dateFinReservation', DATE '$dateReservation', $duréeJ, $quantite, '$etat', $numUtilisateur)";
			$prep=$connexion->prepare($req);
			$prep->execute();
			
			if(!empty($prep)){
				echo ("ReservOk");
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