package ulul.azmi.a.latala.amphibians.ui.screens // Paket untuk UI layer aplikasi

// Import library dan dependensi yang diperlukan
import androidx.compose.runtime.getValue // Mengamati perubahan state dalam Jetpack Compose
import androidx.compose.runtime.mutableStateOf // Mendefinisikan state yang dapat berubah
import androidx.compose.runtime.setValue // Mengatur perubahan pada state
import androidx.lifecycle.ViewModel // Base class untuk menahan data UI
import androidx.lifecycle.ViewModelProvider // Untuk menghasilkan instance ViewModel
import androidx.lifecycle.viewModelScope // Coroutine scope untuk ViewModel
import androidx.lifecycle.viewmodel.initializer // Digunakan untuk inisialisasi ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory // Membuat ViewModel dengan parameter
import ulul.azmi.a.latala.amphibians.AmphibiansApplication // Kelas aplikasi utama
import ulul.azmi.a.latala.amphibians.data.AmphibiansRepository // Repositori untuk data amphibians
import ulul.azmi.a.latala.amphibians.model.Amphibian // Model data Amphibian
import kotlinx.coroutines.launch // Meluncurkan coroutine
import retrofit2.HttpException // Menangani kesalahan HTTP dari Retrofit
import java.io.IOException // Menangani kesalahan I/O

/**
 * UI state untuk layar Home.
 * Menggunakan sealed interface untuk mendefinisikan tiga kemungkinan state:
 * - Success: Data berhasil diambil
 * - Error: Terjadi kesalahan saat mengambil data
 * - Loading: Proses pengambilan data sedang berlangsung
 */
sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState // State sukses dengan data list amphibians
    object Error : AmphibiansUiState // State error
    object Loading : AmphibiansUiState // State loading
}

/**
 * ViewModel untuk mengelola data dan logika terkait pengambilan data amphibians.
 */
class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    // State yang dapat diamati oleh UI
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set // Setter bersifat privat untuk menjaga enkapsulasi

    // Inisialisasi untuk langsung memulai pengambilan data ketika ViewModel dibuat
    init {
        getAmphibians()
    }

    /**
     * Fungsi untuk mengambil data amphibians dari repositori.
     * Menggunakan coroutine dalam `viewModelScope` untuk operasi asynchronous.
     */
    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading // Set state ke Loading
            amphibiansUiState = try {
                AmphibiansUiState.Success(amphibiansRepository.getAmphibians()) // Data berhasil diambil
            } catch (e: IOException) {
                AmphibiansUiState.Error // Kesalahan I/O
            } catch (e: HttpException) {
                AmphibiansUiState.Error // Kesalahan HTTP
            }
        }
    }

    /**
     * Factory untuk menghasilkan instance [AmphibiansViewModel].
     * Menggunakan [AmphibiansRepository] sebagai dependensi.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Mendapatkan instance aplikasi
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as AmphibiansApplication)
                // Mendapatkan repositori dari container aplikasi
                val amphibiansRepository = application.container.amphibiansRepository
                // Mengembalikan instance ViewModel
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
