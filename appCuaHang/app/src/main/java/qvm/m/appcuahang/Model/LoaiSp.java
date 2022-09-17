package qvm.m.appcuahang.Model;

public class LoaiSp {
    public int id;
    public String tenLoaiSp;
    public String hinhAnhSp;

    public LoaiSp(int id, String tenLoaiSp, String hinhAnhSp) {
        this.id = id;
        this.tenLoaiSp = tenLoaiSp;
        this.hinhAnhSp = hinhAnhSp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSp() {
        return tenLoaiSp;
    }

    public void setTenLoaiSp(String tenLoaiSp) {
        this.tenLoaiSp = tenLoaiSp;
    }

    public String getHinhAnhSp() {
        return hinhAnhSp;
    }

    public void setHinhAnhSp(String hinhAnhSp) {
        this.hinhAnhSp = hinhAnhSp;
    }
}
