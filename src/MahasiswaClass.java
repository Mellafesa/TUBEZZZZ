public class MahasiswaClass extends UserClass {
    private String nim;
    private String universitas;
    private String alamat;

    public MahasiswaClass(String username, String password, String nim, String universitas, String alamat) {
        super(username, password);
        this.nim = nim;
        this.universitas = universitas;
        this.alamat = alamat;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getUniversitas() {
        return universitas;
    }

    public void setUniversitas(String universitas) {
        this.universitas = universitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    
  
}