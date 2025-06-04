package org.rothurtech.httpservletCRUD;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generate unique id
    private int id;

    private String item;
    private double amount;

//    @ManyToOne // many to one relationship, many order can associate to one user
//    @JoinColumn(name = "user_id") // this is the foreign key column that link to id of AppUser
//    private AppUser user;

    public Order() {}

    public Order(int id, String item, double amount) {
        this.id = id;
        this.item = item;
        this.amount = amount;
    }

// Getters and setters...

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public double getAmount() {
        return amount;
    }
}
