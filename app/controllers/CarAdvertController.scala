package controllers

import java.util.UUID

import io.swagger.annotations._
import javax.inject.Inject
import models.{Car, PageResult}
import play.api.libs.json.Json
import play.api.mvc._
import services.CarAdvertService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionException

@Api("Car advertisements")
class CarAdvertController @Inject()(carAdvertService: CarAdvertService) extends BaseController {

  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Cars found", response = classOf[PageResult])
  ))
  @ApiOperation("get all cars")
  def getAllCars(pageNumber: Int,
                 pageSize: Int,
                 sortField: String,
                 @ApiParam(name = "sortOrder", defaultValue = "asc", allowableValues = "desc") sortOrder: String): Action[AnyContent] = Action.async { implicit request =>
    val cars = carAdvertService.getAllCars(pageNumber, pageSize, sortField, sortOrder)
    cars.map {
      result => Ok(Json.toJson(result))
    }
  }

  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Car not found"),
    new ApiResponse(code = 200, message = "Car found", response = classOf[Car])
  ))
  @ApiOperation("get car by its id")
  def getById(id: String): Action[AnyContent] = Action.async { implicit request =>
    val cars = carAdvertService.findById(UUID.fromString(id))
    cars.map {
      case Some(car) => Ok(Json.toJson(car))
      case None => NotFound(s"Car: $id not found")
    }
  }

  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Car not found"),
    new ApiResponse(code = 200, message = "Car deleted")
  ))
  @ApiOperation("delete car")
  def delete(id: String): Action[AnyContent] = Action.async { implicit request =>
    val uuid = UUID.fromString(id)
    val cars = carAdvertService.findById(uuid)
    cars.map {
      case Some(car) =>
        carAdvertService.remove(uuid)
        Ok(s"car: $uuid successfully deleted")
      case None => NotFound(s"Car: $uuid not found")
    }
  }

  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Car saved successfully", response = classOf[Car])
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      value = "Saves new car",
      required = true,
      dataType = "models.Car", // complete path
      paramType = "body"
    )
  ))
  @ApiOperation("save new car")
  def saveCar: Action[Car] = Action.async(BodyParsers.parse.json[Car]) { implicit request =>
    val car = request.body.copy(id = Some(UUID.randomUUID()))
    val result = carAdvertService.insert(car)(Car.carValidator)
    result.map(car => Ok(Json.toJson(car))).recover {
      case eE: ExecutionException =>
        eE.getCause match {
          case ae: AssertionError => BadRequest(ae.getMessage)
          case e: Exception => InternalServerError(e.getMessage)
        }
      case e: Exception => InternalServerError(e.getMessage)
    }
  }

  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Car not found"),
    new ApiResponse(code = 200, message = "Car updated successfully", response = classOf[Car])
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      value = "Update car",
      required = true,
      dataType = "models.Car", // complete path
      paramType = "body"
    )
  ))
  @ApiOperation("update car")
  def updateCar(id: String): Action[Car] = Action.async(BodyParsers.parse.json[Car]) { implicit request =>
    val uuid = UUID.fromString(id)
    val car = request.body
    val result = carAdvertService.update(car, uuid)(Car.carValidator)
    result.map {
      case Some(car) => Ok(Json.toJson(car))
      case None => NotFound(s"Car: $id not found")
    }.recover {
      case eE: ExecutionException =>
        eE.getCause match {
          case ae: AssertionError => BadRequest(ae.getMessage)
          case e: Exception => InternalServerError(e.getMessage)
        }
      case e: Exception => InternalServerError(e.getMessage)
    }
  }

  def redirectDocs = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }
}
