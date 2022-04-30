package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.exception.DataException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "transactions")
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String date;

    String type;

    double amount;

    Long account_id;

    public Transaction() {
        this.date = String.valueOf(new Date());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
