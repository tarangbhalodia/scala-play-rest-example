package models

import java.util.{Date, UUID}

import org.scalatest._
import com.wix.accord._

class CarValidationSpec extends FlatSpec {
  implicit val validator = Car.carValidator

  "A car " should " contain non-empty title" in {
    val car = Car(
      id = UUID.randomUUID(),
      title = "",
      fuel = Diesel,
      price = 100000,
      used = false,
      mileage = None,
      firstRegistration = Some(new Date())
    )
    val result = validate(car)
    println(result.toFailure.get.violations.mkString(", "))
    assert(result.isFailure)
  }

  it should " contain price greater than 0" in {
    val car = Car(
      id = UUID.randomUUID(),
      title = "Test car",
      fuel = Diesel,
      price = 0,
      used = false,
      mileage = None,
      firstRegistration = Some(new Date())
    )
    val result = validate(car)
    println(result.toFailure.get.violations.mkString(", "))
    assert(result.isFailure)
  }

  "A used car" should "contain non-empty mileage" in {
    val car = Car(
      id = UUID.randomUUID(),
      title = "Test car",
      fuel = Diesel,
      price = 10000,
      used = true,
      mileage = None,
      firstRegistration = None
    )
    val result = validate(car)
    println(result.toFailure.get.violations.mkString(", "))
    assert(result.isFailure)
  }

  it should "contain non-empty firstRegistration" in {
    val car = Car(
      id = UUID.randomUUID(),
      title = "Test car",
      fuel = Diesel,
      price = 10000,
      used = true,
      mileage = Some(8),
      firstRegistration = None
    )
    val result = validate(car)
    println(result.toFailure.get.violations.mkString(", "))
    assert(result.isFailure)
  }
}

