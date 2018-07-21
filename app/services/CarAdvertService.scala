package services

import java.util.{Date, UUID}

import models.{Car, Diesel}

class CarAdvertService {

  def getAllCars: List[Car] = {
    List(Car(
      id = UUID.randomUUID(),
      title = "Safari Storme",
      fuel = Diesel,
      price = 100000,
      used = false,
      mileage = None,
      firstRegistration = new Date()
    ))
  }
}
