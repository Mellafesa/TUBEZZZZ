public class BeasiswaNonAkademikClass extends BeasiswaClass {
    private String bidang_beasiswa;
    
    public BeasiswaNonAkademikClass(int id_beasiswa, String nama_beasiswa, float jumlah_dana, String deskripsi, String bidang_beasiswa) {
        super(id_beasiswa, nama_beasiswa, jumlah_dana, deskripsi);
        this.bidang_beasiswa = bidang_beasiswa;
    }
    
    @Override
    public void daftar_beasiswa() {
        // Implementasi pendaftaran beasiswa SMA
    }

    public String getBidang_beasiswa() {
        return bidang_beasiswa;
    }

    public void setBidang_beasiswa(String bidang_beasiswa) {
        this.bidang_beasiswa = bidang_beasiswa;
    }
    

}
