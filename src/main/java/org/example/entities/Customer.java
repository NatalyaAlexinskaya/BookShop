package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer_list")
@NamedQuery(name = "Customer", query = "select c from Customer c")
public class Customer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Customer_Name")
    private String name;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private Book book;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + book +
                '}';
    }
}
