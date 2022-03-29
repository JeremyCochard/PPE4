<?php
	
	function connexionPDO(){
		$login="root";
		$password="";
		$dbname="zonestockage";
		$serveur="localhost";
		
		try{
			
			$connect = new PDO('mysql:host=localhost;dbname=zonestockage', "root", "");
			return $connect;
		}catch(PDOExeption $e){
			print "Erreur de connexion PDO";
			die();
		}
	}
	
	function getNumUtlisateur(){
		$connexion=connexionPDO();
		
		$login=$_SESSION["login"];
		
		$sql = "SELECT numUtilisateur From utilisateur where login ='$login'";
		$pdostatement = $connexion->query($sql);
		$num = $pdostatement->fetch(PDO::FETCH_ASSOC);
		$strNum=implode($num);
		
		return $strNum;
	}
	
	function getReservation(){
		$connexion=connexionPDO();
		
		$numClient=$_SESSION["numClient"];
		
		$req="SELECT * from reservation where numClient='$numClient'";
		$pdostatement = $connexion->query($req);
		$reservationClient = $pdostatement->fetch(PDO::FETCH_ASSOC);
		$strReservationClient=implode("|", $reservationClient);
		
		return $strReservationClient;
	}
	
	function suppressionReservation(){
		$connexion=connexionPDO();
		
		$idReservation=$_SESSION["idReservation"];
		
		$req="DELETE from reservation where id=$idReservation";
		$pdostatement = $connexion->query($req);
		
		return "Réservation supprimé";
	}
	

?>