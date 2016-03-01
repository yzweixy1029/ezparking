package com.jsnu.yls.graduation.persistence.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * 管理员实体类
 * <p>
 * Created by WeiXY on 2016/2/22.
 */

@Entity
@Table(name = "ADMIN")
public class Admin {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name = "USER_NAME")
    @NotEmpty
    private String userName;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Admin() {
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
