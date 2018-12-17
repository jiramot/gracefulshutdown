package com.github.jiramot.gracefulshutdown

import org.apache.catalina.connector.Connector
import org.apache.tomcat.util.threads.ThreadPoolExecutor
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import java.util.concurrent.TimeUnit

class GracefulShutdown : TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
  private val log by LoggerDelegate()
  lateinit var connector: Connector

  override fun customize(connector: Connector) {
    this.connector = connector
  }

  override fun onApplicationEvent(event: ContextClosedEvent) {
    connector.pause()
    val executor = connector.protocolHandler.executor
    if (executor is ThreadPoolExecutor) {
      try {
        executor.shutdown()
        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
          log.warn("Tomcat thread pool did not shut down gracefully within " + "30 seconds. Proceeding with forceful shutdown")
        }
      } catch (ex: InterruptedException) {
        Thread.currentThread().interrupt()
      }
    }
  }
}