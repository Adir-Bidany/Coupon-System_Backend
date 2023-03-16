package com.jb.couponSys.beans;

import com.jb.couponSys.dto.CouponPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(length = 40, nullable = false)
    private String description;
    private Date startDate;
    private Date endDate;
    private double price;
    private int amount;
    @Column(length = 100, nullable = false)
    private String image;

    public Coupon(CouponPayload couponPayload) {
        this.category = couponPayload.getCategory();
        this.title = couponPayload.getTitle();
        this.description = couponPayload.getDescription();
        this.startDate = couponPayload.getStartDate();
        this.endDate = couponPayload.getEndDate();
        this.amount = couponPayload.getAmount();
        this.price = couponPayload.getPrice();
        this.image = couponPayload.getImage();
    }
}
