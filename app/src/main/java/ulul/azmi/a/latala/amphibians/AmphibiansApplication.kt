package ulul.azmi.a.latala.amphibians

import android.app.Application
import ulul.azmi.a.latala.amphibians.data.AppContainer
import ulul.azmi.a.latala.amphibians.data.DefaultAppContainer

// Kelas utama aplikasi yang mewarisi Application
class AmphibiansApplication : Application() {
    /** 
     * Instance AppContainer digunakan oleh kelas lain untuk mendapatkan dependensi.
     * Menggunakan lateinit karena diinisialisasi nanti dalam `onCreate`.
     */
    lateinit var container: AppContainer

    // Fungsi yang dipanggil saat aplikasi dibuat
    override fun onCreate() {
        super.onCreate()
        // Inisialisasi container dengan implementasi DefaultAppContainer
        container = DefaultAppContainer()
    }
}
