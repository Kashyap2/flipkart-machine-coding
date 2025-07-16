package com.example.lld.model

case class SeatId(prefix: String = "SEAT") extends Id
case class Seat(id: SeatId = SeatId(), row: Int, col: Int, isOccupied: Boolean = false)
