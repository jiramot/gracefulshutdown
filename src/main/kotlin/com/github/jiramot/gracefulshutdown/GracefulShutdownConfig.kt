package com.github.jiramot.gracefulshutdown

import org.springframework.context.annotation.Bean


class GracefulShutdownConfig {
  @Bean
  fun gracefulShutdown(): GracefulShutdown {
    return GracefulShutdown()
  }

}