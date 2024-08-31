package Menu
open class ItemMenu(val nama: String, var harga: Int, open var stok: Int) {
    fun kurangStok(jumlah: Int) {
        if (stok - jumlah >= 0) {
            stok -= jumlah
        } else {
            println("Stok tidak mencukupi.")
        }
    }
    open fun tambahStok(jumlah: Int){}
}
