package com.example.halfway.data.network

import com.example.halfway.model.Receipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("recipes/complexSearch")
    suspend fun getFactsFromService(
        @Query("apiKey") apikey: String,
        @Query("addRecipeNutrition") addRecipeInformation: Boolean,
        @Query("fillIngredients") fillIngredients: Boolean,
        @Query("number") number: Int,
        @Query("offset") offset: Int
    ): Receipe

    @GET("recipes/complexSearch")
    suspend fun getFactsFromService(
        @QueryMap queryMap: HashMap<String, String>
    ): Response<Receipe>

    @GET("recipes/complexSearch")
    suspend fun getFactsFromService(
        @Query("apiKey") apikey: String,
        @Query("addRecipeNutrition") addRecipeInformation: Boolean,
        @Query("fillIngredients") fillIngredients: Boolean,
        @Query("number") number: Int,
        @Query("diet") diet: String
    ): Response<Receipe>
}