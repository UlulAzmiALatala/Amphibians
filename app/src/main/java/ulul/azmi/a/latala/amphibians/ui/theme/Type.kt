package ulul.azmi.a.latala.amphibians.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Font default yang digunakan
        fontWeight = FontWeight.Normal, // Berat font normal
        fontSize = 16.sp, // Ukuran font 16 spasi
        lineHeight = 24.sp, // Tinggi baris 24 spasi
        letterSpacing = 0.5.sp // Spasi antar huruf 0.5 spasi
    )
)
