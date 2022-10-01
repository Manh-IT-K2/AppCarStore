package qvm.m.appcuahang.Model;
import java.util.List;

public class LoaiSpModel {
    boolean succsess;
    String message;
    List<SanPham> result;

    public boolean isSuccsess() {
        return succsess;
    }

    public void setSuccsess(boolean succsess) {
        this.succsess = succsess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SanPham> getResult() {
        return result;
    }

    public void setResult(List<SanPham> result) {
        this.result = result;
    }
}
