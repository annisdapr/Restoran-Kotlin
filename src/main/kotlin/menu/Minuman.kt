package menu
class Minuman(nama: String, harga: Int, val deskripsi: String, override var stok: Int) : ItemMenu(nama, harga, stok) {
    override fun tambahStok(jumlah: Int) {
        stok += jumlah
        print("Stok $nama ditambah sebanyak $jumlah")
    }
}
