package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.File

data class SingleArticle(

        @field:JsonProperty("articleId")
        val id: Long? = null,

        @field:JsonProperty("lastModifiedAt")
        val modified: String? = null,

        @field:JsonProperty("title")
        val title: String? = null,

        @field:JsonProperty("content")
        val content: String? = null,

        @field:JsonProperty("files")
        val files: MutableList<File>? = null)