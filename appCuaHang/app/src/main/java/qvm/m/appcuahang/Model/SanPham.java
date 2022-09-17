package qvm.m.appcuahang.Model;

public class SanPham {
    public int id;
    public String tenSp;
    public Integer giaSp;
    public String hinhAnhSp;
    public String moTaSp;
    public int idSanPham;

    public SanPham(int id, String tenSp, Integer giaSp, String hinhAnhSp, String moTaSp, int idSanPham) {
        this.id = id;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
        this.hinhAnhSp = hinhAnhSp;
        this.moTaSp = moTaSp;
        this.idSanPham = idSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Integer getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(Integer giaSp) {
        this.giaSp = giaSp;
    }

    public String getHinhAnhSp() {
        return hinhAnhSp;
    }

    public void setHinhAnhSp(String hinhAnhSp) {
        this.hinhAnhSp = hinhAnhSp;
    }

    public String getMoTaSp() {
        return moTaSp;
    }

    public void setMoTaSp(String moTaSp) {
        this.moTaSp = moTaSp;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }
}
