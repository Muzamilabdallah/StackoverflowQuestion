package com.example.stackoverflowquestion.model.answers

data class Answers(
    val backoff: Int,
    val has_more: Boolean,
    val items: List<ItemData>,
    val quota_max: Int,
    val quota_remaining: Int
)