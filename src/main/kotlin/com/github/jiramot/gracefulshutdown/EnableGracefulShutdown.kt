package com.github.jiramot.gracefulshutdown

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Retention(value = AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Configuration
@Import(GracefulShutdownConfig::class)
annotation class EnableGracefulShutdown