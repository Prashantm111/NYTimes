package com.example.prashantmtask.models

data class MainResponseNYTimes(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)