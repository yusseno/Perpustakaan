package com.example.perpustakaan.data

import com.example.perpustakaan.R

// data buku
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val imageId: Int
)

val bookList = listOf(
    Book(
        1,
        "Pemrograman Android dengan Kotlin (2023)",
        "Ivan Lanin",
        "Pemrograman Android dengan Kotlin (2023) adalah panduan praktis yang komprehensif untuk mengembangkan aplikasi Android menggunakan bahasa pemrograman Kotlin. Buku ini mencakup topik-topik penting seperti dasar-dasar Kotlin, arsitektur aplikasi Android, integrasi dengan API, manajemen UI, dan banyak lagi. Dengan tutorial yang jelas dan contoh kode yang relevan, pembaca akan belajar bagaimana membuat aplikasi Android yang kuat dan efisien.",
        R.drawable.cover_kotlin
    ),
    Book(
        2,
        "Belajar Dasar Pemrograman Web (2022)",
        "Gun Gun Febrianza",
        "Buku ini mengajarkan dasar-dasar pemrograman web menggunakan bahasa pemrograman seperti HTML, CSS, dan JavaScript.",
        R.drawable.web
    ),
    Book(
        3,
        "Pemrograman Java Lanjutan (2024)",
        "Rudi Hartono",
        "Buku ini membahas konsep-konsep lanjutan dalam pemrograman Java, termasuk polimorfisme, inheritance, koleksi, dan lainnya. Cocok bagi mereka yang ingin mengambil pemahaman Java mereka ke tingkat berikutnya.",
        R.drawable.java
    ),
    Book(
        4,
        "Belajar Flutter untuk Pemula (2023)",
        "Diana Fitri",
        "Buku ini merupakan panduan praktis untuk mempelajari Flutter, framework UI yang populer untuk pengembangan aplikasi lintas platform. Pembaca akan mempelajari dasar-dasar Flutter serta cara mengembangkan aplikasi yang menarik.",
        R.drawable.flutter
    ),
    Book(
        5,
        "Dasar-Dasar Algoritma dan Struktur Data (2022)",
        "Bambang Setiawan",
        "Buku ini membahas konsep dasar algoritma dan struktur data yang penting dalam pengembangan perangkat lunak. Pembaca akan mempelajari algoritma pencarian, pengurutan, serta struktur data seperti array, linked list, stack, dan queue.",
        R.drawable.struktur_data
    ),
    Book(
        6,
        "Python untuk Ilmu Data (2023)",
        "Siska Wijaya",
        "Buku ini membahas penggunaan bahasa pemrograman Python dalam ilmu data. Pembaca akan mempelajari cara menggunakan Python untuk analisis data, visualisasi, dan pemodelan prediktif.",
        R.drawable.pyton
    ),
    Book(
        7,
        "Pemrograman Mobile dengan React Native (2022)",
        "Hendra Prasetya",
        "Buku ini membahas pengembangan aplikasi mobile menggunakan framework React Native. Pembaca akan mempelajari dasar-dasar React Native serta cara membangun aplikasi mobile yang responsif dan efisien.",
        R.drawable.react
    ),
    Book(
        8,
        "Belajar Docker dan Kubernetes (2023)",
        "Yuda Santoso",
        "Buku ini merupakan panduan lengkap untuk mempelajari Docker dan Kubernetes, dua teknologi yang penting dalam pengembangan dan manajemen aplikasi kontainer. Pembaca akan mempelajari dasar-dasar Docker, manajemen kontainer, dan orchestrasi menggunakan Kubernetes.",
        R.drawable.docker
    ),
    Book(
        9,
        "Pemrograman Game dengan Unity (2024)",
        "Andi Nugraha",
        "Buku ini mengajarkan cara menggunakan Unity, mesin game yang populer, untuk mengembangkan permainan video. Pembaca akan mempelajari dasar-dasar pembuatan game, pembuatan animasi, fisika game, dan lainnya.",
        R.drawable.game
    ),
    Book(
        10,
        "Belajar Node.js dan Express (2023)",
        "Siti Maryam",
        "Buku ini membahas pengembangan aplikasi web menggunakan Node.js dan framework Express. Pembaca akan mempelajari dasar-dasar Node.js, routing, middleware, dan cara mengintegrasikan Express dengan database.",
        R.drawable.node
    )
)