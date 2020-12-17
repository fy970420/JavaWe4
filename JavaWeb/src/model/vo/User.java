package model.vo;

public class User {
    private String userName;
    private String password;
    private String chrName;
    private String email;
    private String province;
    private int provinceCode;
    private String city;
    private int cityCode;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", chrName='" + chrName + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", provinceCode=" + provinceCode +
                ", city='" + city + '\'' +
                ", cityCode=" + cityCode +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChrName() {
        return chrName;
    }

    public void setChrName(String chrName) {
        this.chrName = chrName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User(String[] userInfo) {
        this.userName = userInfo[0];
        this.chrName = userInfo[1];
        this.email = userInfo[2];
        this.password = userInfo[3];
        this.province = userInfo[4];
        this.provinceCode = Integer.valueOf(userInfo[5]);
        this.city = userInfo[6];
        this.cityCode = Integer.valueOf(userInfo[7]);
    }

    public User() {
    }

    public User(String userName, String password, String chrName, String email, String province, int provinceCode, String city, int cityCode) {
        this.userName = userName;
        this.password = password;
        this.chrName = chrName;
        this.email = email;
        this.province = province;
        this.provinceCode = provinceCode;
        this.city = city;
        this.cityCode = cityCode;
    }
}
