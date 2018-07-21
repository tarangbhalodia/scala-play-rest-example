package models

sealed trait FuelType

case object Gasoline extends FuelType

case object Diesel extends FuelType