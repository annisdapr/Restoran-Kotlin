fun main() {
    val restoran = Restoran("Restoran")
    var running = true

    while (running) {
        println("Pilih opsi:")
        println("1. Buat pesanan")
        println("2. Tampilkan Daftar Pelanggan")
        println("3. Tambah Stok Makanan/Minuman")
        println("4. Keluar")
        val pilihan = try {
            readlnOrNull()?.toInt() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Pilihan tidak valid. Silakan pilih opsi yang valid.")
            continue
        }
        when (pilihan) {
            1 -> {
                restoran.tampilkanMenu()
                restoran.mengambilPesanan()
            }
            2 -> restoran.tampilkanDaftarPelanggan()
            3 -> restoran.tambahStok()
            4 -> running = false
        }
    }
}

