package models.json

import models.{Diesel, FuelType, Gasoline}
import play.api.data.validation.ValidationError
import play.api.libs.json._

object FuelTypeFormat extends Reads[FuelType] with Writes[FuelType] {

  override def reads(json: JsValue): JsResult[FuelType] = json match {
    case JsString(fuelTypeString) =>
      fuelTypeString match {
        case "Gasoline" => JsSuccess(Gasoline)
        case "Diesel" => JsSuccess(Diesel)
      }

    case _ =>
      JsError(Seq(JsPath() -> Seq(ValidationError("Expected a valid fuel type string"))))
  }

  override def writes(o: FuelType): JsValue = {
    JsString(o.toString)
  }
}
