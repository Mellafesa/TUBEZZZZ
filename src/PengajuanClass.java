public class PengajuanClass {
    private int id_pengajuan;
    private BeasiswaClass id_beasiswa;
    private MahasiswaClass nim_mhs;
    private boolean accepted;

    public PengajuanClass(int id_pengajuan, BeasiswaClass id_beasiswa, MahasiswaClass nim_mhs, boolean accepted) {
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

    public BeasiswaClass getId_beasiswa() {
        return id_beasiswa;
    }

    public void setId_beasiswa(BeasiswaClass id_beasiswa) {
        this.id_beasiswa = id_beasiswa;
    }

    public MahasiswaClass getNim_mhs() {
        return nim_mhs;
    }

    public void setNim_mhs(MahasiswaClass nim_mhs) {
        this.nim_mhs = nim_mhs;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    


}
