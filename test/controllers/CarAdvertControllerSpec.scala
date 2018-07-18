package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class CarAdvertControllerSpec extends PlaySpec {

  "CarAdvertController" should {
    "render the index page" in {

      running(FakeApplication()) {
        val request = FakeRequest(GET, "/").withHeaders(HOST -> "localhost:9000")
        val home = route(request).get
        contentAsString(home) must include ("This is a placeholder page to show you the REST API.")
      }
    }
  }
}