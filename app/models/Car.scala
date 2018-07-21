package models

import java.util.{Date, UUID}

import com.wix.accord._
import com.wix.accord.dsl._
import play.api.libs.json._
import json.Implicits._

case class Car(
                id: UUID,
                title: String,
                fuel: FuelType,
                price: Int,
                used: Boolean,
                mileage: Option[Int],
                firstRegistration: Date
              )

object Car {
  implicit val jsonFormat = Json.format[Car]

  implicit val userValidator: Validator[Car] = validator[Car] { u =>
    u.title as "Title" is notBlank
    ()
  }
}