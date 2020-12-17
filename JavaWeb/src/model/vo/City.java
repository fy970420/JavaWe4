package model.vo;

public class City {
    int provinceCode;
    String city;
    int cityCode;

    @Override
    public String toString() {
        return "City{" +
                "provinceCode=" + provinceCode +
                ", city='" + city + '\'' +
                ", cityCode=" + cityCode +
                '}';
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public City() {
    }

    public City(int provinceCode, String city, int cityCode) {
        this.provinceCode = provinceCode;
        this.city = city;
        this.cityCode = cityCode;
    }
}
