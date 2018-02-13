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
	<script src="data/mnprecinct.geojson"></script>
	<style type="text/css">
			#map { height : 720px; width : 1200px;}
	</style>
		
	<?php
	/*    Using "mysqli" instead of "mysql" that is obsolete.
	* Change the value of parameter 3 if you have set a password on the root userid
	* Add port number 3307 in parameter number 5 to use MariaDB instead of MySQL
	*
	*     Utilisation de "mysqli" à la place de "mysql" qui est obsolète.
	* Changer la valeur du 3e paramètre si vous avez mis un mot de passe à root
	* Ajouter le port 3307 en paramètre 5 si vous voulez utiliser MariaDB
	* Use your USER NAME and PASSWORD
	*/
	$mysqli = new mysqli('127.0.0.1', 'root', '');

	if ($mysqli->connect_error) {
		die('Connect Error (' . $mysqli->connect_errno . ') '
				. $mysqli->connect_error);
	}
	$sql ="select pctname ,USPRSR, USPRsDFL, usprstotal from ics499.results";
	$result = $mysqli->query($sql);
	
	if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
         echo "<br> Precint name: ". $row["pctname"]. " - Preidential Republican Votes: ". $row["USPRSR"]. " Presidental Democrat Votes: " . $row["USPRsDFL"] . " Total Number of Voters: ". $row["usprstotal"]. "<br>";
    }
} else {
    echo "0 results";
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
			var mapboxAccessToken = 'pk.eyJ1IjoiYmJhanJhIiwiYSI6ImNqY3Q2eDYycTBmZ3kyeHZ0ajZsNGtvajIifQ.tKzBEEfc7mCC0HmVy-KYuw';
			var map = L.map('map').setView([44.9375, -93.2010],7);
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapboxAccessToken, {
				id: 'mapbox.light',
				attribution: 'State map data',
			}).addTo(map);
			/*var stateLayer = L.geoJson(precincts).addTo(map);
			$.getJSON('mn-precincts.json', function (data) {
			// Define the geojson layer and add it to the map
			L.geoJson(data).addTo(map);
			});*/
			var precinctLayer = L.geoJson(mn).addTo(map);
			//var countriesLayer = L.geoJson(countries).addTo(map);
			map.fitBounds(precinctLayer.getBounds());
			//map.fitBounds(countriesLayer.getBounds());
			//countriesLayer.addTo(map);
						
			// get color depending on population density value
			function getColor(d) {
				return d > 70 ? '#800026' :
						d > 60  ? '#BD0026' :
						d > 50  ? '#E31A1C' :
						d > 40  ? '#FC4E2A' :
						d > 30   ? '#FD8D3C' :
						d > 20   ? '#FEB24C' :
						d > 10   ? '#FED976' :
									'#FFEDA0';
			}

			function style(feature) {
				return {
					weight: 2,
					opacity: 1,
					color: 'white',
					dashArray: '3',
					fillOpacity: 0.7,
					fillColor: getColor(Feature.properties.CountyID)
				};
			}

			var geojson = L.geoJson(mn, {
				style: style,
			}).addTo(map);

		
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