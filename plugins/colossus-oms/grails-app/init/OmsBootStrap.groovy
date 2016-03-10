import joms.oms.Init

class OmsBootStrap {

    def init = { servletContext ->
    	Init.instance().initialize()
    }
    def destroy = {
    }
}
