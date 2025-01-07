public abstract class BeasiswaClass {

    protected int id_beasiswa;
    protected String nama_beasiswa;
    protected float jumlah_dana;
    protected String jenis_beasiswa;
    protected String deskripsi;
    
    public BeasiswaClass(int id_beasiswa, String nama_beasiswa, float jumlah_dana, String jenis_beasiswa, String deskripsi) {
        this.id_beasiswa = id_beasiswa;
        this.nama_beasiswa = nama_beasiswa;
        this.jumlah_dana = jumlah_dana;
        this.jenis_beasiswa = jenis_beasiswa;
        this.deskripsi = deskripsi;
    }

    public int getId_beasiswa() {
        return id_beasiswa;
    }


    public void setId_beasiswa(int id_beasiswa) {
        this.id_beasiswa = id_beasiswa;
    }

    public void setNama_beasiswa(String nama_beasiswa) {
        this.nama_beasiswa = nama_beasiswa;
    }

    public void setJumlah_dana(float jumlah_dana) {
        this.jumlah_dana = jumlah_dana;
    }

    public void setJenis_beasiswa(String jenis_beasiswa) {
        this.jenis_beasiswa = jenis_beasiswa;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama_beasiswa() {
        return nama_beasiswa;
    }



    public double getJumlah_dana() {
        return jumlah_dana;
    }



    public String getDeskripsi() {
        return deskripsi;
    }

    public String getJenis_beasiswa() {
        return jenis_beasiswa;
    }
    
    
    public void setJenisBeasiswa(String jenis_beasiswa) {
        this.jenis_beasiswa = jenis_beasiswa;
    }


    public abstract void daftar_beasiswa();




    

}
