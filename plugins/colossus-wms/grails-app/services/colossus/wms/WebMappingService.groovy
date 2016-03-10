package colossus.wms

import grails.transaction.Transactional

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Transactional(readOnly = true)
class WebMappingService 
{

    def getMap(def params) 
    {	
    	println params

    	def width = params.WIDTH.toInteger()	
    	def height = params.HEIGHT.toInteger()
    	def contentType = params.FORMAT
    	def image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    	def ostream = new ByteArrayOutputStream()

    	ImageIO.write(image, contentType.split('/').last(), ostream)

    	[contentType: contentType, buffer: ostream.toByteArray()]
    }
}
