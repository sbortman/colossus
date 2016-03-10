package colossus.geodata

import colossus.core.BindUtil

class CityController
{
	static scaffold = City

	def cityService

	def plotCities(PlotCitiesCommand plotCmd)
	{
		def newParams = BindUtil.fixParamNames(PlotCitiesCommand, params)

		bindData(plotCmd, newParams)

		def results = cityService.plotCities( plotCmd )

		render contentType: results.contentType, file: results.buffer
	}
}