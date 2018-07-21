package models

import java.util.{Date, UUID}

import com.wix.accord._
import com.wix.accord.dsl._
import io.swagger.annotations._
import play.api.libs.json._
import json.Implicits._

@ApiModel
case class Car(
                id: UUID,
                title: String,
                @ApiModelProperty(value = "Mileage for used cars", dataType = "String", example = "Gasoline, Diesel") fuel: FuelType,
                price: Int,
                used: Boolean,
                @ApiModelProperty(value = "Mileage for used cars", dataType = "Integer") mileage: Option[Int],
                @ApiModelProperty(value = "First registration date for used cars", dataType = "Date") firstRegistration: Option[Date]
              )

object Car {
  implicit val jsonFormat = Json.format[Car]

  implicit val carValidator: Validator[Car] = validator[Car] { u =>
    u.title as "Title" is notBlank
    u.price as "Price" should be > 0
    (u.used as "isUsed" is false) or (u.mileage as "Mileage" is notEmpty)
    (u.used as "isUsed" is false) or (u.firstRegistration is notEmpty)
    ()
  }
}