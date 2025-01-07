public class BeasiswaAkademikClass extends BeasiswaClass {
    private float min_IPK;
 
    
    public BeasiswaAkademikClass(int id_beasiswa, String nama_beasiswa, float jumlah_dana, String jenis_beasiswa, String deskripsi, float min_IPK) {
        super(id_beasiswa, nama_beasiswa, jumlah_dana, jenis_beasiswa, deskripsi);
        this.min_IPK = min_IPK;
    }
    
    @Override
    public void daftar_beasiswa() {
        // Implementasi pendaftaran beasiswa S1
    }

    public float getMin_IPK() {
        return min_IPK;
    }

    public void setMin_IPK(float min_IPK) {
        this.min_IPK = min_IPK;
    }

    
    

    

}
