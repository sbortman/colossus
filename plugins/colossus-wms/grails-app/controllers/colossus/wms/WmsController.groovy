package colossus.wms

class WmsController 
{
	def webMappingService

    def getMap() 
    { 
    	def results = webMappingService.getMap(params)

    	render contentType: results.contentType, file: results.buffer
    }
}
