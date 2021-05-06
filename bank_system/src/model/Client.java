package model;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String pesel;
    private Address address;
    private double money;

    public Client(int id, String name, String surname, String pesel, Address address, double money) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.address = address;
        this.money = money;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + " Pieniądze: " + money + " zł";
    }

    public void depositMoney(double amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Nieprawidłowa ilość");
        this.money += amount;
    }

    public double payOutMoney(double amount) throws InvalidAmountException {
        if (amount <= 0 || amount > this.money) throw new InvalidAmountException("Nieprawidłowa ilość");
        this.money -= amount;
        return amount;
    }

    public void transferMoney(Client client, double amount) throws InvalidAmountException {
        if (amount <= 0 || amount > this.money) throw new InvalidAmountException("Nieprawidłowa ilość");
        double money = this.payOutMoney(amount);
        client.depositMoney(money);
    }

}
