package com.example.perpustakaan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perpustakaan.data.Book
import com.example.perpustakaan.data.Card
import com.example.perpustakaan.data.bookList
import com.example.perpustakaan.data.cardExample
import com.example.perpustakaan.ui.theme.PerpustakaanTheme

// Activity utama
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content menggunakan Compose
        setContent {
            PerpustakaanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Buat NavController
                    val navController = rememberNavController()

                    // Tentukan destinasi di dalam NavHost
                    NavHost(navController, startDestination = "bookList") {
                        // Destinasi untuk menampilkan daftar buku
                        composable("bookList") {
                            BookListScreen(navController = navController)
                        }
                        // Destinasi untuk menampilkan detail buku
                        composable(
                            "bookDetail/{id}",
                        ) { backStackEntry ->
                            val arguments = requireNotNull(backStackEntry.arguments)
                            val bookId = arguments.getString("id")
                            val book = bookList.find { it.id == bookId!!.toInt() }
                            if (book != null) {
                                BookDetailScreen(book)
                            } else {
                                // Jika buku tidak ditemukan, tampilkan pesan
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Buku tidak ditemukan",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Red
                                    )
                                    Text(
                                        text = "ID Buku: $bookId",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Komponen untuk header
@Composable
fun Header(card: Card) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.utdi_logo),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = card.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = card.subtitle,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
        }

        // Divider antara header dan konten buku
        Divider(
            color = Color.Gray.copy(alpha = 0.2f),
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .shadow(4.dp)
        )
    }
}

// Komponen untuk menampilkan daftar buku
@Composable
fun BookListScreen(navController: NavHostController) {
    Column {
        // Tampilkan header
        Header(Card(cardExample.title, cardExample.subtitle))
        // Tampilkan konten buku
        BookContent { book ->
            navController.navigate("bookDetail/${book.id}")
        }
    }
}

// Komponen untuk konten buku
@Composable
fun BookContent(onItemClick: (Book) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        item {
            // Judul daftar buku
            Text(
                text = "Daftar Buku Tersedia : ",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            )        }
        // Tampilkan item buku
        items(bookList) { book ->
            Spacer(modifier = Modifier.height(10.dp))
            BookItem(book = book, onItemClick = onItemClick)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

// Komponen untuk menampilkan item buku
@Composable
fun BookItem(book: Book, onItemClick: (Book) -> Unit) {
    Surface(
        color = Color.Gray.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                onItemClick(book)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = book.imageId),
                contentDescription = "Book cover",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = book.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )
            }
        }
    }
}

// Komponen untuk menampilkan detail buku
@Composable
fun BookDetailScreen(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tampilkan header
        Header(Card(cardExample.title, cardExample.subtitle))
        // Spacer
        Spacer(modifier = Modifier.height(20.dp))
        // Tampilkan gambar buku
        Image(
            painter = painterResource(id = book.imageId),
            contentDescription = "Book cover",
            modifier = Modifier
                .width(180.dp)
                .height(240.dp)
        )
        // Spacer
        Spacer(modifier = Modifier.height(20.dp))
        // Tampilkan judul buku
        Text(
            text = book.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 6.dp
            ),
        )
        // Tampilkan penulis buku
        Text(
            text = "Penulis : ${book.author}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.8f),
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 4.dp
            ),
        )
        // Tampilkan deskripsi buku
        Text(
            text = "Deskripsi :",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 4.dp
            ),
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                    append(" "+ book.description)
                }
            },
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black.copy(alpha = 0.8f),
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 4.dp,
            )
        )
    }
}

// Preview untuk tampilan detail buku
@Preview(showBackground = true, widthDp = 420, heightDp = 731)
@Composable
fun PreviewBookDetailScreen() {
    PerpustakaanTheme {
        Surface {
            val book1 = bookList.find { it.id == 1 }
            if (book1 != null) {
                BookDetailScreen(book1)
            }
        }
    }
}

