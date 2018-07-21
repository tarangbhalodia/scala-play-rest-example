package models

import play.api.libs.json._

case class PageResult(
                       cars: List[Car],
                       size: Int
                     )

object PageResult {
  implicit val pageResultFormat = Json.format[PageResult]
}