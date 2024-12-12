package ulul.azmi.a.latala.amphibians.ui.screens // Paket untuk antarmuka pengguna

import androidx.compose.foundation.Image // Komponen untuk menampilkan gambar
import androidx.compose.foundation.layout.Arrangement // Layout untuk mengatur susunan elemen
import androidx.compose.foundation.layout.Column // Layout kolom
import androidx.compose.foundation.layout.PaddingValues // Padding untuk elemen
import androidx.compose.foundation.layout.fillMaxSize // Mengisi maksimum ukuran
import androidx.compose.foundation.layout.fillMaxWidth // Mengisi lebar penuh
import androidx.compose.foundation.layout.padding // Menambahkan padding
import androidx.compose.foundation.layout.size // Mengatur ukuran komponen
import androidx.compose.foundation.lazy.LazyColumn // Kolom yang dapat di-scroll
import androidx.compose.foundation.lazy.items // Untuk menampilkan item dalam lazy column
import androidx.foundation.shape.RoundedCornerShape // Bentuk sudut bulat pada card
import androidx.material3.Button // Tombol untuk tindakan ulang
import androidx.material3.Card // Komponen card
import androidx.material3.MaterialTheme // Tema material desain
import androidx.material3.Text // Komponen teks
import androidx.compose.runtime.Composable // Fungsi composable
import androidx.compose.ui.Alignment // Posisi komponen di dalam layout
import androidx.compose.ui.Modifier // Modifikasi komponen UI
import androidx.compose.ui.layout.ContentScale // Skala konten gambar
import androidx.compose.ui.platform.LocalContext // Context aplikasi
import androidx.compose.ui.res.dimensionResource // Mendapatkan ukuran resource dimensi
import androidx.compose.ui.res.painterResource // Untuk mendapatkan gambar dari resource
import androidx.compose.ui.res.stringResource // Untuk mengambil string dari resource
import androidx.compose.ui.text.font.FontWeight // Bobot font untuk teks
import androidx.compose.ui.text.style.TextAlign // Aligment teks
import androidx.compose.ui.tooling.preview.Preview // Fungsi preview untuk komponen composable
import androidx.compose.ui.unit.dp // Unit ukuran dalam desimal untuk padding
import coil.compose.AsyncImage // Komponen untuk memuat gambar asinkron dari URL
import coil.request.ImageRequest // Request image dari URL
import ulul.azmi.a.latala.amphibians.R // Resource ID dari resource aplikasi
import ulul.azmi.a.latala.amphibians.model.Amphibian // Model data untuk amfibi
import ulul.azmi.a.latala.amphibians.ui.theme.AmphibiansTheme // Tema aplikasi

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is AmphibiansUiState.Success ->
            AmphibiansListScreen(
                amphibians = amphibiansUiState.amphibians,
                modifier = modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium)
                    ),
                contentPadding = contentPadding
            )
        else -> ErrorScreen(retryAction, modifier)
    }
}

/**
 * Layar beranda menampilkan pesan loading.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

/**
 * Layar beranda menampilkan pesan error dengan tombol re-attempt.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.amphibian_title, amphibian.name, amphibian.type),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun AmphibiansListScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = amphibians,
            key = { amphibian ->
                amphibian.name
            }
        ) { amphibian ->
            AmphibianCard(amphibian = amphibian, modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansListScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) {
            Amphibian(
                "Lorem Ipsum - $it",
                "$it",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                        " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                        " minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                        " ex ea commodo consequat.",
                imgSrc = ""
            )
        }
        AmphibiansListScreen(mockData, Modifier.fillMaxSize())
    }
}
