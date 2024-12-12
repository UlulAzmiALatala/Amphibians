package ulul.azmi.a.latala.amphibians.model // Paket untuk model data aplikasi

// Import library yang diperlukan untuk serialisasi data
import kotlinx.serialization.SerialName // Digunakan untuk memberi nama khusus pada properti JSON
import kotlinx.serialization.Serializable // Menandakan kelas dapat diserialisasi

/**
 * Data class yang mendefinisikan struktur data untuk amphibian, termasuk:
 * - Nama (name)
 * - Jenis (type)
 * - Deskripsi (description)
 * - URL gambar (imgSrc)
 */
@Serializable // Menandakan bahwa kelas ini dapat digunakan untuk serialisasi/deserialisasi JSON
data class Amphibian(
    val name: String, // Nama dari amphibian
    val type: String, // Jenis dari amphibian (misalnya katak, salamander)
    val description: String, // Deskripsi mengenai amphibian
    @SerialName("img_src") val imgSrc: String // URL gambar, dipetakan ke "img_src" dari JSON
)
