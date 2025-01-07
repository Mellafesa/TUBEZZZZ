public class PengajuanClass {
    private int id_pengajuan;
    private int id_beasiswa;
    private String nim_mhs;
    private boolean accepted;

    public PengajuanClass(int id_pengajuan, int id_beasiswa, String nim_mhs, boolean accepted) {
        this.id_pengajuan = id_pengajuan;
        this.id_beasiswa = id_beasiswa;
        this.nim_mhs = nim_mhs;
        this.accepted = accepted;
    }

    public int getId_pengajuan() {
        return id_pengajuan;
    }

    public void setId_pengajuan(int id_pengajuan) {
        this.id_pengajuan = id_pengajuan;
    }

    public int getId_beasiswa() {
        return id_beasiswa;
    }

    public void setId_beasiswa(int id_beasiswa) {
        this.id_beasiswa = id_beasiswa;
    }

    public String getNim_mhs() {
        return nim_mhs;
    }

    public void setNim_mhs(String nim_mhs) {
        this.nim_mhs = nim_mhs;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    


}
