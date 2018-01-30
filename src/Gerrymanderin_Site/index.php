<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="lib/leaflet/leaflet.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="lib/leaflet/leaflet.js"></script>
	<script src="data/countries.geojson"></script>
	<style type="text/css">
			#map { height : 400px; }
	</style>
		
	<?php
	/*    Using "mysqli" instead of "mysql" that is obsolete.
	* Change the value of parameter 3 if you have set a password on the root userid
	* Add port number 3307 in parameter number 5 to use MariaDB instead of MySQL
	*
	*     Utilisation de "mysqli" à la place de "mysql" qui est obsolète.
	* Changer la valeur du 3e paramètre si vous avez mis un mot de passe à root
	* Ajouter le port 3307 en paramètre 5 si vous voulez utiliser MariaDB
	*/
	$mysqli = new mysqli('127.0.0.1', 'root', '');

	if ($mysqli->connect_error) {
		die('Connect Error (' . $mysqli->connect_errno . ') '
				. $mysqli->connect_error);
	}
	echo '<p>Connection OK '. $mysqli->host_info.'</p>';
	echo '<p>Server '.$mysqli->server_info.'</p>';
	$mysqli->close();
	?>
	</head>
	<title>
	Gerrymanderin interactive map
	</title>
	<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
		<div class="navbar-header">Gerrymanderin</div>
		<ul class="nav navbar-nav">
		  <li class="active"><a href="#">Home</a></li>
		  <li><a href="#">Recommendation</a></li>
		</ul>
		<form action="http://www.google.com/search" class="navbar-form navbar-left" method="get" name="searchform" target="_blank">
			<input class="form-controls search" name="search" placeholder="Search in google" type="text">
			<button class="btn btn-default" type="submit">
			<i class="glyphicon glyphicon-search"></i>Search</button>
		</form>
	  </div>
	</nav>
	<div class="jumbotron text-center" style="background-color:#ebf0fa;">
	  <h1>Gerrymanderin Project</h1>
	</div>
	<div class="btn-group btn-group-lg">
	<button type="button" class="btn btn-primary"><span class="glyphicons glyphicons-map"></span> Map </button>
	<button type="button" class="btn btn-primary"><span class="glyphicons glyphicons-pie-chart"></span>Chart</button>
	<button type="button" class="btn btn-info">Info</button>
	<a href="#" class="btn btn-success btn-lg">
      <span class="glyphicon glyphicon-print"></span> Print 
    </a>
	</div>
	
	<div class="container">
	<!Interactive Map Here...Source: https://www.youtube.com/watch?v=yVnE1UWOn7I>
	<h1>World Map</h1>
		<div id="map"></div>
		<script>
			var map = L.map('map').setView([44.9375, -93.2010],13);
			/*var stateLayer = L.geoJson(precincts).addTo(map);
			$.getJSON('mn-precincts.json', function (data) {
			// Define the geojson layer and add it to the map
			L.geoJson(data).addTo(map);
			});*/
			var countriesLayer = L.geoJson(countries).addTo(map);
			map.fitBounds(stateLayer.getBounds());
			map.fitBounds(countriesLayer.getBounds());
			
		</script>
	</div>
	
	<div class="container">
	  <div class="row">
		<div class="col-sm-4">
		  <h3>About</h3>
		  <p>This project analyses the precinct data from Minnesota state.</p>
		</div>
		<div class="col-sm-4">
		  <h3>Interactive Map</h3>
		  <p>The use of interactive map helps to visualize the data in a convenient way.</p>
		</div>
		<div class="col-sm-4">
		  <h3>Chart</h3>        
		  <p>The chart form helps to further analize the data.</p>
		</div>
	  </div>
	</div>
	</body>
</html>