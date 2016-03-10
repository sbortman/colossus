package colossus.geodata

import colossus.wms.GetMapTrait

import grails.validation.Validateable

//import groovy.transform.ToString

//@ToString( includeNames = true )
class PlotCitiesCommand implements Validateable, GetMapTrait
{
	static mapWith = 'none'

	static mapping = {
		version false
	}
}