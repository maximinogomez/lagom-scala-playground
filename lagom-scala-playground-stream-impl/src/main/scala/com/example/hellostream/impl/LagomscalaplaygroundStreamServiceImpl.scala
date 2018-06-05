package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.LagomscalaplaygroundStreamService
import com.example.hello.api.LagomscalaplaygroundService

import scala.concurrent.Future

/**
  * Implementation of the LagomscalaplaygroundStreamService.
  */
class LagomscalaplaygroundStreamServiceImpl(lagomscalaplaygroundService: LagomscalaplaygroundService) extends LagomscalaplaygroundStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(lagomscalaplaygroundService.hello(_).invoke()))
  }
}
