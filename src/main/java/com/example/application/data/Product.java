package com.example.application.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Component
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "deadline_date")
    private String deadlineDate;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "start_price")
    private Double startPrice;

    @Column(name = "actual_price")
    private Double actualPrice;

    @Column(name = "bid")
    private Double bid;

    public Product(String productName, String startDate, String deadlineDate,
                   String picUrl, Double startPrice, Double actualPrice, Double bid) {
        this.productName = productName;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.picUrl = picUrl;
        this.startPrice = startPrice;
        this.actualPrice = actualPrice;
        this.bid = bid;
    }

}
