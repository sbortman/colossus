import colossus.geodata.City

class GeoDataBootStrap 
{
	def cityService

    def init = { servletContext ->
//    	def csvFile = 'cities.csv' as File
		def filename = 'cities.csv'

		if ( City.count() == 0 )
		{
//	    	cityService.loadCsvFile(csvFile)    		
			cityService.loadCsvResource(filename)
		}
    }

    def destroy = {
    }
}
