package ulul.azmi.a.latala.amphibians.data // Paket data aplikasi

// Import library yang diperlukan
import ulul.azmi.a.latala.amphibians.network.AmphibiansApiService // API Service untuk mengambil data dari jaringan
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory // Konverter untuk Retrofit
import kotlinx.serialization.json.Json // Library untuk serialisasi JSON
import okhttp3.MediaType.Companion.toMediaType // Ekstensi untuk mengonversi tipe media
import retrofit2.Retrofit // Library Retrofit untuk HTTP client

/**
 * Container untuk Dependency Injection di level aplikasi.
 */
interface AppContainer {
    val amphibiansRepository: AmphibiansRepository // Repository untuk data amphibian
}

/**
 * Implementasi untuk Dependency Injection container di level aplikasi.
 *
 * Variabel-variabel diinisialisasi secara lazy dan instance-nya dibagikan di seluruh aplikasi.
 */
class DefaultAppContainer : AppContainer {
    // URL dasar untuk API
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Menggunakan builder Retrofit untuk membuat objek retrofit dengan konverter kotlinx.serialization
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) // Menambahkan konverter JSON
        .baseUrl(BASE_URL) // Menentukan URL dasar API
        .build()

    /**
     * Objek service Retrofit untuk membuat panggilan API
     * Inisialisasi lazy memastikan bahwa service dibuat hanya saat dibutuhkan.
     */
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java) // Membuat instance dari AmphibiansApiService
    }

    /**
     * Implementasi DI untuk repository Amphibians
     * Inisialisasi lazy memastikan hanya satu instance yang digunakan di seluruh aplikasi.
     */
    override val amphibiansRepository: AmphibiansRepository by lazy {
        DefaultAmphibiansRepository(retrofitService) // Menggunakan retrofitService untuk mendapatkan data dari jaringan
    }
}
