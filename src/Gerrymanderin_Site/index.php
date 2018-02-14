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
			//Initializing the map
			var mapboxAccessToken = 'pk.eyJ1IjoiYmJhanJhIiwiYSI6ImNqY3Q2eDYycTBmZ3kyeHZ0ajZsNGtvajIifQ.tKzBEEfc7mCC0HmVy-KYuw';
			var map = L.map('map').setView([44.9375, -93.2010],7);
		
		//Loading the title layer
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapboxAccessToken, {
				id: 'mapbox.light',
				attribution: 'State map data',
			}).addTo(map);
			
		//Loading geoJson from an external file
			var precinctLayer = L.geoJson(mn).addTo(map);
			map.fitBounds(precinctLayer.getBounds());
			
		/*
		*Function to color the layer by CountyID
		* Comment out the function to get the open street map
		* The function is currently not working
		*/
		/* >>>>DELETE THIS LINE TO USE THE FUNCTION
		
		$.getJSON("mnprecinct.geojson",function(mnData){
				L.geoJson(mnData,{
					style: function(feature){
						var fillColor,
						density = feature.properties.density;
						CountyID = feature.properties.CountyID;
						if(CountyID === 1) fillColor = "#006837";
						else if ( CountyID === 2 ) fillColor = "#31a354";
						else if ( CountyID == 3 ) fillColor = "#78c679";
						else if ( CountyID == 4 ) fillColor = "#c2e699";
						else if ( CountyID > 4 ) fillColor = "#ffffcc";
						return { color: "#999", weight: 1, fillColor: fillColor, fillOpacity: .6 };

					},
					onEachFeature: function(feature, layer){
						layer.bindPopup( "<strong>" + feature.properties.Precinct + "</strong><br/>" + feature.properties.County + " rats per square mile" )
					}
				}).addTo(map);
			});
		*/

		
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