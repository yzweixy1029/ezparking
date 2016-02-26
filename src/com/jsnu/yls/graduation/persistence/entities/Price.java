package com.jsnu.yls.graduation.persistence.entities;

import javax.persistence.*;

/**
 * 停车价格实体类
 *
 * Created by Administrator on 2016/2/22.
 */
@Entity
@Table(name = "PRICE")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PRICE")
    private float price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
