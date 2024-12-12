package ulul.azmi.a.latala.amphibians.data

import ulul.azmi.a.latala.amphibians.model.Amphibian
import ulul.azmi.a.latala.amphibians.network.AmphibiansApiService

/**
 * Repository digunakan untuk mengambil data amphibian dari sumber data utama.
 */
interface AmphibiansRepository {
    /** 
     * Fungsi untuk mengambil daftar amphibian dari sumber data.
     * suspend: Fungsi ini berjalan secara asynchronous untuk mendukung coroutine.
     */
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Implementasi Network untuk Repository, yang mengambil data amphibian dari API.
 */
class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService // Dependency Injection: API Service
) : AmphibiansRepository {
    /** 
     * Mengambil daftar amphibian dari API menggunakan amphibiansApiService.
     * override: Mengimplementasikan fungsi dari interface AmphibiansRepository.
     */
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}
