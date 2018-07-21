package services

import java.util.{Date, UUID}

import models.{Car, Diesel}

import scala.concurrent.Future

class CarAdvertService {

  def getAllCars: Future[List[Car]] = {
    val car = Car(
      id = UUID.randomUUID(),
      title = "Safari Storme",
      fuel = Diesel,
      price = 100000,
      used = false,
      mileage = None,
      firstRegistration = Some(new Date())
    )
    Future.successful(List(car))
  }
}
