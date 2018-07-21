package controllers

import io.swagger.annotations._
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc._
import services.CarAdvertService

@Api(value = "/")
class CarAdvertController @Inject()(carAdvertService: CarAdvertService) extends BaseController {

  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "No car found")))
  def index = Action { implicit request =>
    val cars = carAdvertService.getAllCars
    Ok(Json.toJson(cars))
  }

  def redirectDocs = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }
}
