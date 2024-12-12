package ulul.azmi.a.latala.amphibians.network // Paket untuk pengelolaan API di aplikasi

// Import model data dan anotasi yang diperlukan
import ulul.azmi.a.latala.amphibians.model.Amphibian // Model data untuk menyimpan informasi tentang amphibian
import retrofit2.http.GET // Anotasi Retrofit untuk permintaan HTTP GET

/**
 * Interface untuk mendefinisikan layanan API Amphibians.
 * Digunakan oleh Retrofit untuk mengelola panggilan jaringan.
 */
interface AmphibiansApiService {
    /**
     * Fungsi untuk mendapatkan daftar amphibian dari endpoint "amphibians".
     * 
     * @return List<Amphibian>: Daftar objek Amphibian yang diterima dari API.
     * suspend: Fungsi berjalan secara asynchronous untuk mendukung coroutine.
     */
    @GET("amphibians") // Anotasi untuk menetapkan endpoint HTTP GET
    suspend fun getAmphibians(): List<Amphibian> // Mengembalikan daftar Amphibian
}
