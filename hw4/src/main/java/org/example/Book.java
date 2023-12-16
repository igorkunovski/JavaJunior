package org.example;

public class Book{

    private int id;
    private String name;
    private String author;
    private int year;
    private int price;

    public Book() {
    }

    public Book(int id, String name, String author, int year, int price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
