package ua.kpi.its.lab.rest.init

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import ua.kpi.its.lab.rest.config.RootConfig
import ua.kpi.its.lab.rest.config.WebConfig

class WebInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getServletMappings(): Array<String> = arrayOf("/")

    override fun getRootConfigClasses(): Array<Class<*>> = arrayOf(RootConfig::class.java)

    override fun getServletConfigClasses(): Array<Class<*>> = arrayOf(WebConfig::class.java)
}