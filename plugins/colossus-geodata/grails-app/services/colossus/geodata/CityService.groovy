package colossus.geodata

import geoscript.GeoScript
import geoscript.geom.Bounds
import geoscript.geom.Point
import geoscript.render.Map as GeoScriptMap
import geoscript.workspace.Workspace
import grails.transaction.Transactional

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import static geoscript.style.Symbolizers.*

import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class CityService
{
	def messageSource
	def dataSource

	enum RenderMethod {
		BLANK, GEOSCRIPT
	}


	def loadCsvFile( File csvFile )
	{
		csvFile.eachLine( 0 ) { line, i ->
			if ( i > 0 )
			{
				processLine( line )
				//println "${city}"
			}
		}
	}

	def loadCsvResource( String filename )
	{
		def reader = new BufferedReader( new InputStreamReader(
				this.class.getClassLoader().getResourceAsStream( filename ) ) )

		reader.eachLine( 0 ) { line, i ->
			if ( i > 0 )
			{
				processLine( line )
				//println "${city}"
			}
		}

		reader.close()
	}

	def processLine( String line )
	{
		def record = line?.split( ',' )

		def city = new City(
				name: record[ 0 ],
				country: record[ 1 ],
				population: record[ 2 ]?.toInteger(),
				capital: record[ 3 ] == 'Y',
				longitude: record[ 4 ]?.toDouble(),
				latitude: record[ 5 ]?.toDouble()
		)

		city.location = GeoScript.unwrap( new Point( city.longitude, city.latitude ) )
		city.location.setSRID( 4326 )

		if ( !city.save() )
		{
			city.errors.allErrors.each { println messageSource.getMessage( it, null ) }
		}
	}


	@Transactional( readOnly = true )
	def plotCities( PlotCitiesCommand plotCmd )
	{
		log.info plotCmd.toString()

		def buffer = new ByteArrayOutputStream()
		def renderMethod = RenderMethod.GEOSCRIPT

		switch ( renderMethod )
		{
		case RenderMethod.BLANK:
			def image = new BufferedImage( plotCmd?.width, plotCmd?.height, BufferedImage.TYPE_INT_ARGB )

			ImageIO.write( image, 'png', buffer )
			break
		case RenderMethod.GEOSCRIPT:
			Workspace.withWorkspace(
					dbtype: 'postgis',

					// All these can be blank (except for port for some reason)
					// The dataSource is provided by Hibernate.
					database: '',
					host: '',
					port: 5432,
					user: '',
					password: '',

					'Data Source': dataSource,
					'Expose primary keys': true
			) { workspace ->
				def bounds = new Bounds( *( plotCmd?.bbox?.split( ',' )*.toDouble() ), plotCmd?.srs )
				def layer = workspace[ plotCmd.layers ?: 'city' ]

				layer?.style = shape( color: 'red', shape: 'star', size: 5 ) + label( property: 'name', font: font( weight: 'bold', size: 18 ) ) + halo( radius: 1 )

				def map = new GeoScriptMap(
						width: plotCmd?.width,
						height: plotCmd?.height,
						proj: bounds?.proj,
						bounds: bounds,
						layers: [ layer ]
				)

				map.render( buffer )
				map.close()
			}
			break
		}

		[ contentType: 'image/png', buffer: buffer.toByteArray() ]
	}
}
