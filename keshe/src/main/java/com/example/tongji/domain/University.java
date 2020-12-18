package com.example.tongji.domain;
/**
 * 参数配置表 University
 *
 */
public class University {
    private Long id;
    private String Province;
    private String Name;
    private String City;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        this.Province = province;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public University(Long id, String province, String name, String city) {
        this.id = id;
        this.Province = province;
        this.Name = name;
        this.City = city;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", Province='" + Province + '\'' +
                ", Name='" + Name + '\'' +
                ", City='" + City + '\'' +
                '}';
    }
}
