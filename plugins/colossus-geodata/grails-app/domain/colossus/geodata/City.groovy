package colossus.geodata

import com.vividsolutions.jts.geom.Point
import groovy.transform.ToString
import org.hibernate.spatial.GeometryType

@ToString( includeNames = true )
class City
{
	String name
	String country
	Integer population
	Boolean capital
	Double longitude
	Double latitude
	Point location

	static constraints = {
		name( nullable: true )
		country( nullable: true )
		population( nullable: true )
		capital( nullable: true )
		longitude( nullable: true )
		latitude( nullable: true )
		location( nullable: true )
	}

	static mapping = {
		location type: GeometryType, sqlType: 'geometry(Point, 4326)'
	}
}
