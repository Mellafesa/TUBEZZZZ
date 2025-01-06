public abstract class BeasiswaClass {

    protected int id_beasiswa;
    protected String nama_beasiswa;
    protected float jumlah_dana;
    protected String deskripsi;
    
    public BeasiswaClass(int id_beasiswa, String nama_beasiswa, float jumlah_dana, String deskripsi) {
        this.id_beasiswa = id_beasiswa;
        this.nama_beasiswa = nama_beasiswa;
        this.jumlah_dana = jumlah_dana;
        this.deskripsi = deskripsi;
    }

    
    
    public String getnama_beasiswa() {
        return nama_beasiswa;
    }



    public double getjumlah_dana() {
        return jumlah_dana;
    }



    public String getdeskripsi() {
        return deskripsi;
    }



    public abstract void daftar_beasiswa();

}
