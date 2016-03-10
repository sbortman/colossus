<html>
	<head>
		<meta name='layout' content="main"/>
		<asset:stylesheet src='mapView'/>
	</head>
	<body>
		<div class="nav">
			<ul>
				<li><g:link class='home' uri='/'>Home</g:link>
			</li>
		</div>
		<div class="content">
			<h1>Map View</h1>
			<div id="map"></div>
		</div>
		<asset:javascript src='mapView'/>
		<asset:script>
		$(document).ready(function (){
			MapView.initialize();
		});
		</asset:script>
		<asset:deferredScripts/>
	</body>
</html>