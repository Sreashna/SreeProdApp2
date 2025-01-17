data class Product(
    val id: String,
    val name: String,
    val imageRes: Int,
    val originalPrice: Double = 0.0,
    val discountedPrice: Double = 0.0,
    val discountPercentage: Double = 0.0,
    val details: String = "",
    val avgReview: Float = 0f,
    val numReviews: Int = 0,
    val deliveryDate: String = ""
)
