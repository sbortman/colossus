package colossus.wms

import colossus.core.ToStringUtil

trait GetMapTrait implements ToStringUtil
{
	String service = 'WMS'
	String version = '1.1.1'
	String request = 'GetMap'

	String bbox
	String srs

	String layers
	String styles

	Integer width
	Integer height
	String format
	Boolean transparent

}
