package com.github.jiramot.gracefulshutdown

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.stereotype.Component

@Component
class CustomContainer : WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
  @Autowired
  lateinit var gracefulShutdown: GracefulShutdown

  override fun customize(factory: ConfigurableServletWebServerFactory?) {
    if (factory is TomcatServletWebServerFactory) {
      factory.addConnectorCustomizers(gracefulShutdown)
    }
  }
}