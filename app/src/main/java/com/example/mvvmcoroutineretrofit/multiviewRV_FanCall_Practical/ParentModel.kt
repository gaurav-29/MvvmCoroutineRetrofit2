package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

data class ParentModel(
    val viewType: Int,
    var heading: String? = null,
    var bannerList: ArrayList<BannerModel>? = null,
    var categoryList: ArrayList<CategoryModel>? = null,
    var topSearchedList: ArrayList<TopSearchedModel>? = null
)
data class BannerModel(
    var bannerImage: Int
)
data class CategoryModel(
    var categoryImage: Int,
)
data class TopSearchedModel(
    var topSearchedImage: Int,
    var name: String,
    var type: String,
    var subscribers: String,
    var rating: Float
)
