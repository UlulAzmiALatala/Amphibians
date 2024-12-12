package ulul.azmi.a.latala.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ulul.azmi.a.latala.amphibians.ui.AmphibiansApp
import ulul.azmi.a.latala.amphibians.ui.theme.AmphibiansTheme

// Kelas utama yang mewarisi ComponentActivity
class MainActivity : ComponentActivity() {
    // Fungsi yang dijalankan saat activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur konten UI menggunakan Jetpack Compose
        setContent {
            AmphibiansTheme { // Menerapkan tema custom untuk aplikasi
                // Surface digunakan sebagai kontainer utama
                Surface(
                    modifier = Modifier.fillMaxSize(), // Mengatur agar layout memenuhi layar
                    color = MaterialTheme.colorScheme.background // Menggunakan warna background dari tema
                ) {
                    AmphibiansApp() // Memanggil fungsi utama aplikasi yang mendefinisikan UI
                }
            }
        }
    }
}
