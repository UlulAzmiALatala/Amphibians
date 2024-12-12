package ulul.azmi.a.latala.amphibians.ui.theme // Tema aplikasi

import android.app.Activity // Untuk menangani pengaturan tepi-ke-tepi
import android.os.Build // Untuk memeriksa versi API
import androidx.compose.foundation.isSystemInDarkTheme // Untuk memeriksa tema sistem
import androidx.compose.material3.MaterialTheme // Tema Material 3
import androidx.compose.material3.darkColorScheme // Tema warna gelap
import androidx.compose.material3.dynamicDarkColorScheme // Tema warna dinamis untuk Android 12+
import androidx.compose.material3.dynamicLightColorScheme // Tema warna dinamis untuk Android 12+
import androidx.compose.material3.lightColorScheme // Tema warna terang
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect // Efek samping untuk penanganan UI
import androidx.compose.ui.graphics.Color // Warna UI
import androidx.compose.ui.graphics.toArgb // Fungsi untuk mengonversi warna ke integer ARGB
import androidx.compose.ui.platform.LocalContext // Untuk mendapatkan konteks aplikasi
import androidx.compose.ui.platform.LocalView // Untuk mendapatkan tampilan aplikasi
import androidx.core.view.WindowCompat // Untuk mengelola tepi-tepi jendela
import android.view.View // Untuk menangani pengaturan tepi-tepi jendela

// Tema warna gelap
private val DarkColorScheme = darkColorScheme(
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    surfaceVariant = md_theme_dark_surfaceVariant,
)

// Tema warna terang
private val LightColorScheme = lightColorScheme(
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    surfaceVariant = md_theme_light_surfaceVariant,
)

// Fungsi untuk tema aplikasi
@Composable
fun AmphibiansTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Tema dinamis tersedia di Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            setUpEdgeToEdge(view, darkTheme)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Mengatur edge-to-edge untuk jendela dari view ini.
 * Warna ikon sistem diatur berdasarkan apakah tema gelap diaktifkan atau tidak.
 */
private fun setUpEdgeToEdge(view: View, darkTheme: Boolean) {
    val window = (view.context as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.Transparent.toArgb()
    val navigationBarColor = when {
        Build.VERSION.SDK_INT >= 29 -> Color.Transparent.toArgb()
        Build.VERSION.SDK_INT >= 26 -> Color(0xFF, 0xFF, 0xFF, 0x63).toArgb()
        // Versi SDK minimal untuk aplikasi ini adalah 24, blok ini untuk versi SDK 24 dan 25
        else -> Color(0x00, 0x00, 0x00, 0x50).toArgb()
    }
    window.navigationBarColor = navigationBarColor
    val controller = WindowCompat.getInsetsController(window, view)
    controller.isAppearanceLightStatusBars = !darkTheme
    controller.isAppearanceLightNavigationBars = !darkTheme
}
