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
			return null;
		}
	}


?>