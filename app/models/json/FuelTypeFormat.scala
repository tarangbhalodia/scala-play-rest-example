package models.json

import models.FuelType
import play.api.data.validation.ValidationError
import play.api.libs.json._

import scala.reflect.runtime.universe

object FuelTypeFormat extends Reads[FuelType] with Writes[FuelType] {

  private val tpe = universe.typeOf[FuelType]
  private val subTypes = tpe.typeSymbol.asClass.knownDirectSubclasses
  private val subTypeNames = subTypes.map(_.name.toString)

  override def reads(json: JsValue): JsResult[FuelType] = json match {
    case JsString(fuelTypeString) =>
      if (subTypeNames.contains(fuelTypeString))
        JsSuccess(subTypes.filter(st => st.name.toString.equals(fuelTypeString)).asInstanceOf[FuelType])
      else
        JsError(Seq(JsPath() -> Seq(ValidationError(s"Expected a valid fuel type string. Available fuel types are: ${subTypes.map(_.name.toString).mkString(", ")}"))))
    case _ =>
      JsError(Seq(JsPath() -> Seq(ValidationError(s"Expected a valid fuel type string. Available fuel types are: ${subTypes.map(_.name.toString).mkString(", ")}"))))
  }

  override def writes(o: FuelType): JsValue = {
    JsString(o.toString)
  }
}
