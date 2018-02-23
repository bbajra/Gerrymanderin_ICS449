<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!link rel="stylesheet" href="lib/leaflet/leaflet.css">
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin=""/>
	 <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
	
	<!script src="lib/leaflet/leaflet.js"></script>
	
	<script src="data/countries.geojson"></script>
	<script src="data/mnprecinct.geojson"></script>
	<style type="text/css">
			#map { height : 720px; width : 1200px;}
			.info { padding: 6px 8px; font: 14px/16px Arial, Helvetica, sans-serif; background: white; background: rgba(255,255,255,0.8); box-shadow: 0 0 15px rgba(0,0,0,0.2); border-radius: 5px; } .info h4 { margin: 0 0 5px; color: #777; }
			.legend { text-align: left; line-height: 18px; color: #555; } .legend i { width: 18px; height: 18px; float: left; margin-right: 8px; opacity: 0.7; }
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
	
	/* 
	* Database Queries
	*/
	/*
	$sql ="select pctname ,USPRSR, USPRsDFL, usprstotal from ics499.results";
	$result = $mysqli->query($sql);
	
	if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
         echo "<br> Precint name: ". $row["pctname"]. " - Preidential Republican Votes: ". $row["USPRSR"]. " Presidental Democrat Votes: " . $row["USPRsDFL"] . " Total Number of Voters: ". $row["usprstotal"]. "<br>";
    }
	} else {
		echo "0 results";
	}*/
	
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
	<h1>World Map</h1>
		<div id="map"></div>
		<script>
		//Initializing the map
			var mapboxAccessToken = 'pk.eyJ1IjoiYmJhanJhIiwiYSI6ImNqY3Q2eDYycTBmZ3kyeHZ0ajZsNGtvajIifQ.tKzBEEfc7mCC0HmVy-KYuw';
			var map = L.map('map').setView([44.9375, -93.2010],7);
		
		//Loading the title layer
			/*L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapboxAccessToken, {
				id: 'mapbox.light',
				attribution: 'State map data',
			}).addTo(map);*/
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
				maxZoom: 18,
				attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
					'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
					'Imagery © <a href="http://mapbox.com">Mapbox</a>',
				id: 'mapbox.light'
			}).addTo(map);
			
		//Loading geoJson from an external file
			var precinctLayer = L.geoJson(mn).addTo(map);
			map.fitBounds(precinctLayer.getBounds());
		
		// control that shows state info on hover
		var info = L.control();

		info.onAdd = function (map) {
			this._div = L.DomUtil.create('div', 'info');
			this.update();
			return this._div;
		};

		info.update = function (props) {
			this._div.innerHTML = '<h4>Election Results Precinct</h4>' +  (props ?
				'<b>' + props.properties.Precinct + '</b><br />' + props.properties.County 
				: 'Hover over an area');
		};

		info.addTo(map);

		/*
		*Function to differentiate the districts by color
		*Add boundaries to the layer
		*/

		function getColor(d){
				return d==8 ? '#800026' :
						d==7 ? '#BD0026' :
						d==6 ? '#FC4E2A' :
						d==5 ? '#E31A1C' :
						d==4 ? '#FD8D3C' :
						d==3 ? '#2b8cbe' :
						d==2 ? '#2ca25f' :
						d==1 ? '#FFEDA0' :
							   '#ff0000' ;
		}

		function style(feature) {
			return {
				fillColor: getColor(feature.properties.CongDist),
				weight: 1,
				opacity: 1,
				color: 'black',
				dashArray: '2',
				fillOpacity: 0.7				
			};
		}

		var colorLayer = L.geoJson(mn, {
			style: style
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