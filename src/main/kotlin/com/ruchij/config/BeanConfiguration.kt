package com.ruchij.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.IdGenerator
import org.springframework.util.JdkIdGenerator
import java.time.Clock

@Configuration
class BeanConfiguration {
    @Bean
    fun clock(): Clock = Clock.systemUTC()

    @Bean
    fun idGenerator(): IdGenerator = JdkIdGenerator()
}