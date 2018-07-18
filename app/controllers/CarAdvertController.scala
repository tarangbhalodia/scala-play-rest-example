package controllers

import play.api.mvc._

object CarAdvertController extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }
}
