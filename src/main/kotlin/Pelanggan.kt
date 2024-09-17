import menu.*

data class Pelanggan(val nama: String) {
    var pesanan: MutableList<Any?> = mutableListOf()
    var totalTagihan: Int = 0

    fun memesanPaket(itemPaket: PaketMenu?){
        pesanan.add(itemPaket)
        // NPE dapat terjadi di sini jika itemPaket adalah null
        totalTagihan += itemPaket?.harga ?: 0
    }

    fun memesan(itemMenu: ItemMenu?) {
        pesanan.add(itemMenu)
        // NPE dapat terjadi di sini jika itemMenu adalah null
        totalTagihan += itemMenu?.harga ?: 0
    }

    fun lihatPesanan() {
        println("Pesanan $nama:")
        for (item in pesanan) {
            if (item is PaketMenu) {
                println("${item.nama} - Rp${item.harga}")
                println("Berisi:")
                for (paketItem in item.items) {
                    println("${paketItem.nama} - Rp${paketItem.harga}")
                }
            }
            else{
                if (item is Makanan) {
                    println("${item.nama} (${item.deskripsi}) - Rp${item.harga}")
                } else if (item is Minuman) {
                    println("${item.nama} (${item.deskripsi}) - Rp${item.harga}")
                }
            }
        }
        println("Total Tagihan: Rp$totalTagihan")
    }
}
