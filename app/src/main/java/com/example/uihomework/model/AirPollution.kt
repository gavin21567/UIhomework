package com.example.uihomework.model

import com.google.gson.annotations.SerializedName

data class AirPollution(
    val fields: List<Field>,
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("__extras")
    val extras: Extra,
    @SerializedName("include_total")
    val includeTotal: Boolean,
    val total: Int,
    @SerializedName("resource_format")
    val resourceFormat: String,
    val limit: Int,
    val offset: Int,
    @SerializedName("_links")
    val links: Link,
    val records: List<Record>
)

data class Field(
    val id: String,
    val type: String,
    val info: Info
)

data class Info(
    val label: String
)

data class Extra(
    @SerializedName("api_key")
    val apiKey: String,
)

data class Link(
    val start: String,
    val next: String,
)

data class Record(
    @SerializedName("sitename")
    val siteName: String,
    val county: String,
    val aqi: String,
    val pollutant: String,
    val status: String,
    val so2: String,
    val co: String,
    val o3: String,
    @SerializedName("o3_8hr")
    val o38hr: String,
    val pm10: String,
    @SerializedName("pm2.5")
    val pm25: String,
    val no2: String,
    val nox: String,
    val no: String,
    @SerializedName("wind_speed")
    val windSpeed: String,
    @SerializedName("wind_direc")
    val windDirec: String,
    @SerializedName("publishtime")
    val publishTime: String,
    @SerializedName("co_8hr")
    val co8hr: String,
    @SerializedName("pm2.5_avg")
    val pm25Avg: String,
    @SerializedName("pm10_avg")
    val pm10Avg: String,
    @SerializedName("so2_avg")
    val so2Avg: String,
    val longitude: String,
    val latitude: String,
    @SerializedName("siteid")
    val siteId: String,
)