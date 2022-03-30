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
	
	function getNumUtlisateur($login){
		$connexion=connexionPDO();
		
		$sql = "SELECT numUtilisateur From utilisateur where login ='$login'";
		$pdostatement = $connexion->query($sql);
		$num = $pdostatement->fetch(PDO::FETCH_ASSOC);
		$strNum=implode($num);
		
		
		return $strNum;
	}
	
	function getReservation($numCli){
		$connexion=connexionPDO();
		$tableauReservation = array();
	
		$req="SELECT * from reservation where numClient='$numCli'";		
		foreach  ($connexion->query($req) as $row) {
		$tableauReservation[] = $row;
		}
 
		//var_dump($tableauReservation[1]['id']);
		$tableauReservationJson = json_encode($tableauReservation);
		
		
		
		return $tableauReservationJson;
	}
	
	function suppressionReservation(){
		$connexion=connexionPDO();
		
		$idReservation=$_SESSION["idReservation"];
		
		$req="DELETE from reservation where id=$idReservation";
		$pdostatement = $connexion->query($req);
		
		return "Réservation supprimé";
	}
	

?>