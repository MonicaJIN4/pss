package bean;

public class Supplier {
    private int supId;
    private String name;
    private String tel;
    private String address;

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSupId() {
        return supId;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }
}
