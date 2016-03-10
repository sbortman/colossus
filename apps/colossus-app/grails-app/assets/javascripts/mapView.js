//= require jquery-2.2.0.min.js
//= require webjars/openlayers/3.13.0/ol.js
//= require_self

var MapView = (function()
{
	function initialize(params)
	{
		var layers = [
	        new ol.layer.Tile({
	          source: new ol.source.TileWMS({
	            url: 'http://demo.boundlessgeo.com/geoserver/wms',
	            params: {
	              LAYERS: 'ne:NE1_HR_LC_SR_W_DR',
	              FORMAT: 'image/jpeg'
	            }
	          })
	        }),
          new ol.layer.Tile({
            source: new ol.source.TileWMS({
              url: '/city/plotCities',
              params: {
                VERSION: '1.1.1'
              }
            })
          })          
      ];

      var map = new ol.Map({
        controls: ol.control.defaults().extend([
          new ol.control.ScaleLine({
            units: 'degrees'
          })
        ]),
        layers: layers,
        target: 'map',
        view: new ol.View({
          projection: 'EPSG:4326',
          center: [0, 0],
          zoom: 2
        })
      });	
  	}

	return {
		initialize: initialize			
	};
})();