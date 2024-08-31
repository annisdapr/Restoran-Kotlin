import Menu.*

class Restoran(val nama: String) {
    val daftarMenu: List<ItemMenu> = listOf(
        Makanan("Spaghetti", 10, "Pasta lezat dengan saus tomat", 5),
        Makanan("Pizza", 12, "Pizza panggang dengan berbagai toping", 4),
        Makanan("Burger", 8, "Burger juicy dengan keju dan sayuran", 4),
        Makanan("Salad", 6, "Salad segar dari taman", 3),
        Minuman("Coca-Cola", 2, "Biasa", 10),
        Minuman("Air mineral", 2, "Biasa",  10),
        Minuman("Es Teh", 3, "Manis", 9)
    )
    val daftarPaket: List<PaketMenu> = listOf(
        PaketMenu("Paket BS", daftarMenu[2].harga+daftarMenu[3].harga, setOf(
            daftarMenu[2], daftarMenu[3]
        ))
    )
    private val  daftarpelanggan: MutableList<Pelanggan> = mutableListOf()

    fun tampilkanMenu() {
        println("Menu $nama:")
        for ((index,item) in daftarMenu.withIndex()) {
            println("${index + 1} ${item.nama} - Rp${item.harga}")
        }
        for ((index,item) in daftarPaket.withIndex()) {
            println("${index + 1+daftarMenu.size} ${item.nama} - Rp${item.harga}")
        }
    }
    fun mengambilPesanan() {
        println("Selamat datang di $nama! Silakan masukkan nama:")
        val namaPelanggan = readLine()
        val pelanggan = Pelanggan(namaPelanggan ?: "Tamu")

        var sedangMemesan = true
        val pesananPelanggan = mutableListOf<ItemMenu>()

        println("Masukkan nomor item yang ingin dipesan (0 untuk selesai):")
        while (sedangMemesan) {
            val pilihan = readLine()?.toInt() ?: 0
            if (pilihan in 1..daftarMenu.size) {
                val itemDipesan = daftarMenu[pilihan - 1]
                if (itemDipesan is Makanan && itemDipesan.stok > 0) {
                    itemDipesan.kurangStok(1)
                    pesananPelanggan.add(itemDipesan)
                } else if (itemDipesan is Minuman && itemDipesan.stok > 0) {
                    itemDipesan.kurangStok(1)
                    pesananPelanggan.add(itemDipesan)
                } else {
                    println("Maaf, stok item tidak mencukupi atau item tidak tersedia.")
                }
            }
            else if(pilihan in (daftarMenu.size+1)..(daftarMenu.size+daftarPaket.size)){
                val paketDipesan = daftarPaket[pilihan-daftarMenu.size-1]
                if (paketDipesan.items.all { it.stok > 0 }) {
                    for (item in paketDipesan.items) {
                        item.kurangStok(1)
                    }
                    pesananPelanggan.add(paketDipesan)
                } else {
                    println("Maaf, stok item dalam paket tidak mencukupi atau item tidak tersedia.")
                }
            }else if (pilihan == 0) {
                sedangMemesan = false
            } else {
                println("Pilihan tidak valid. Silakan pilih item menu yang valid.")
            }
        }
        if (pesananPelanggan.isNotEmpty()) {
            for (itemDipesan in pesananPelanggan){
                if (itemDipesan is ItemMenu){pelanggan.memesan(itemDipesan)}
                else if(itemDipesan is PaketMenu){pelanggan.memesanPaket(itemDipesan)}
            }
            pelanggan.lihatPesanan()
            val existingPelanggan = daftarpelanggan.find {it.nama.equals(namaPelanggan) }
            if (existingPelanggan == null) {
                daftarpelanggan.add(pelanggan)
            }
//            if (existingPelanggan != null) {}
//            else{ daftarpelanggan.add(pelanggan)}
        } else {
            println("Pesanan kosong. Terima kasih!")
        }
    }

    fun tampilkanDaftarPelanggan() {
        println("Daftar Pelanggan:")
        for ((index, customer) in daftarpelanggan.withIndex()) {
            println("${index+1} ${customer.nama}")
        }
    }
    fun tambahStok() {
        println("Pilih opsi:")
        println("1. Tambah Stok Makanan")
        println("2. Tambah Stok Minuman")
        val pilihan = readLine()?.toIntOrNull()

        when (pilihan) {
            1 -> {
                println("Pilih Makanan:")
                for ((index, item) in daftarMenu.filterIsInstance<Makanan>().withIndex()) {
                    println("${index + 1} ${item.nama}")
                }
                val makananIndex = readLine()?.toIntOrNull()
                if (makananIndex != null && makananIndex in 1..daftarMenu.filterIsInstance<Makanan>().size) {
                    val makanan = daftarMenu.filterIsInstance<Makanan>()[makananIndex - 1]

                    println("Masukkan jumlah stok yang ingin ditambahkan:")
                    val jumlahStok = readLine()?.toIntOrNull()

                    if (jumlahStok != null && jumlahStok > 0) {
                        makanan.tambahStok(jumlahStok)
                    } else {
                        println("Input tidak valid. Masukkan jumlah stok yang valid.")
                    }
                } else {
                    println("Pilihan tidak valid. Silakan pilih Makanan yang valid.")
                }
            }
            2 -> {
                println("Pilih Minuman:")
                for ((index, item) in daftarMenu.filterIsInstance<Minuman>().withIndex()) {
                    println("${index + 1} ${item.nama}")
                }
                val minumanIndex = readLine()?.toIntOrNull()

                if (minumanIndex != null && minumanIndex in 1..daftarMenu.filterIsInstance<Minuman>().size) {
                    val minuman = daftarMenu.filterIsInstance<Minuman>()[minumanIndex - 1]

                    println("Masukkan jumlah stok yang ingin ditambahkan:")
                    val jumlahStok = readLine()?.toIntOrNull()

                    if (jumlahStok != null && jumlahStok > 0) {
                        minuman.tambahStok(jumlahStok)
                    } else {
                        println("Input tidak valid, masukkan jumlah stok yang valid.")
                    }
                } else {
                    println("Pilihan tidak valid, silakan pilih Minuman yang valid.")
                }
            }
            else -> println("Pilihan tidak valid, silakan pilih opsi yang valid.")
        }
    }
}