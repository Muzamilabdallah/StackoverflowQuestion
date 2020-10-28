package com.example.stackoverflowquestion.model

data class Question(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)