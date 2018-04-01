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
		
		<script src="data/mnprecinctparty.geojson"></script>
		<style type="text/css">
				#map { height : 720px; width : 1200px;}
				.info { padding: 6px 8px; font: 14px/16px Arial, Helvetica, sans-serif; background: white; background: rgba(255,255,255,0.8); box-shadow: 0 0 15px rgba(0,0,0,0.2); border-radius: 5px; } .info h4 { margin: 0 0 5px; color: #777; }
				.legend { text-align: left; line-height: 18px; color: #555; } .legend i { width: 18px; height: 18px; float: left; margin-right: 8px; opacity: 0.7; }
		</style>
	</head>
	<title>
	Interactive map
	</title>
	<body style="background-color:#ffffff;">
	<nav class="navbar navbar-inverse">
	<div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">MN 2016 Election Results</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
		  <li><a href="#">Recommendation</a></li>
		</ul>
		<form action="http://www.google.com/search" class="navbar-form navbar-left" method="get" name="searchform" target="_blank">
			<input class="form-controls search" name="search" placeholder="Search in google" type="text">
			<button class="btn btn-default" type="submit">
			<i class="glyphicon glyphicon-search"></i>Search</button>
		</form>
	  </div>
	</nav>
	<div class="jumbotron text-center"  style="background-color:#f0f0f0;">
	  <h1>Gerrymandering Project</h1>
	</div>
	<div align="center">Click on the buttons below to view the election results</div>
	<br>
	<div align="center">
	<a target="_blank" href="usCongress.html" class="btn btn-default"> U.S. Congress </a>
	<a target="_blank" href="presidential.html" class="btn btn-default">Presidential</a>
	<a target="_blank" href="mnSenate.html" class="btn btn-default">MN Senate</a>
	<a target="_blank" href="mnHouse.html" class="btn btn-default"> MN House </a>
	</div>
	
	<div class="container">
	<h1>Map of Minnesota</h1>
		<div id="map"></div>
		<script>
		/**
		* For GeoJson reference-properties: CountyID
		*/
		//Initializing the map
			var mapboxAccessToken = 'pk.eyJ1IjoiYmJhanJhIiwiYSI6ImNqY3Q2eDYycTBmZ3kyeHZ0ajZsNGtvajIifQ.tKzBEEfc7mCC0HmVy-KYuw';
			var map = L.map('map').setView([44.9375, -93.2010],14);
		
		//Loading the title layer
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
				//maxZoom: 90,
				attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
					'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
					'Imagery © <a href="http://mapbox.com">Mapbox</a>',
				id: 'mapbox.light'
			}).addTo(map);
		
		//Loading geoJson from an external file
			var precinctLayer = L.geoJson(minnesota).addTo(map);
			map.fitBounds(precinctLayer.getBounds());
			
		// control that shows map information from GeoJson file when hovering
		var mapLayerInformation = L.control();

		mapLayerInformation.onAdd = function (map) {
			this._div = L.DomUtil.create('div', 'info');
			this.update();
			return this._div;
		};

		mapLayerInformation.update = function (props) {
			this._div.innerHTML = '<h4>Minnesota County</h4>' +  (props ?
				'<b>' + 'County:' + props.CountyID + '</b><br />' + 'County: ' + props.County + '</b><br/>'
				: 'Hover over an area');
		};

		mapLayerInformation.addTo(map);

		/*
		*Function to differentiate the districts by color
		*Add boundaries to the layer
		*/

		function getColor(district){
			var purple = ["#f7fcfd","#e0ecf4","#bfd3e6","#9ebcda","#8c96c6","#8c6bb1","#88419d","#810f7c","#4d004b"];
				return district % 10 ==1  ? purple[0] :
						district % 10 ==2 ? purple[1] :
						district % 10 ==3 ? purple[2] :
						district % 10 ==4 ? purple[3] :
						district % 10 ==5 ? purple[4] :
						district % 10 ==6 ? purple[5] :
						district % 10 ==7 ? purple[6] :
						district % 10 ==8 ? purple[7] :
										   purple[8] ;
		}
							
		function style(feature) {
			return {
				fillColor: getColor(feature.properties.CountyID),
				weight: 0.5,
				opacity: 1,
				color: 'black',
				dashArray: '0.3',
				fillOpacity: 0.7				
			};
		}
		/*
		* Adding interaction to map with hovering and zooming feature
		*/
		function highlightFeature(e) {
			var mapLayer = e.target;

			mapLayer.setStyle({
				weight: 5,
				color: '#330066',
				opacity: 0.9,
				dashArray: '',
				fillOpacity: 0.7
			});

			if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
				mapLayer.bringToFront();
			}

			mapLayerInformation.update(mapLayer.feature.properties);
		}
			
		var colorLayer;

		function resetHighlight(e) {
			colorLayer.resetStyle(e.target);
			mapLayerInformation.update();
		}

		function zoomToFeature(e) {
			map.fitBounds(e.target.getBounds());
		}

		function onEachFeature(feature, mapLayer) {
			mapLayer.on({
				mouseover: highlightFeature,
				mouseout: resetHighlight,
				click: zoomToFeature
			});
		}
		/*
		* Adding two layers to the map
		*/

		var colorLayer = L.geoJson(minnesota, {
			style: style,
			onEachFeature: onEachFeature
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
		  <h3>Districts</h3>        
		  <p>Election results for each districts can be viewed by clicking on the respective buttons.</p>
		</div>
	  </div>
	</div>
	</body>
</html>