package com.kodlamaio.paymentservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "cardExpirationYear")
    private int cardExpirationYear;
    @Column(name = "cardExpirationMonth")
    private int cardExpirationMonth;
    @Column(name = "cardCvv")
    private String cardCvv;
    @Column(name = "balance")
    private double balance;
}
