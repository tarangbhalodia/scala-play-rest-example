package controllers

import io.swagger.annotations._
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._
import services.CarAdvertService
import scala.concurrent.ExecutionContext.Implicits.global

@Api(value = "/")
class CarAdvertController @Inject()(carAdvertService: CarAdvertService) extends BaseController {

  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "No car found")))
  def index = Action.async { implicit request =>
    val cars = carAdvertService.getAllCars
    cars.map(result => Ok(Json.toJson(result)))
  }

  def redirectDocs = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }
}
