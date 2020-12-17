package model.vo;

public class Province {
    String province;
    int provinceCode;

    @Override
    public String toString() {
        return "Province{" +
                "province='" + province + '\'' +
                ", provinceCode=" + provinceCode +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Province() {
    }

    public Province(String province, int provinceCode) {
        this.province = province;
        this.provinceCode = provinceCode;
    }
}
