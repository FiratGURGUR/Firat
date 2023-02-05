package com.app.firat.gurgurfirat.model

data class PositionItemModel(
    val list : List<Position>
)

data class Position(
    val id: String,
    val positions: List<PositionX>
)

data class PositionX(
    val posX: Double,
    val posY: Double
)