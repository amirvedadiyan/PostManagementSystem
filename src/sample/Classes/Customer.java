package sample.Classes;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * This is Customer class that have ordinary details like first Name,last name ,.... and store the number of orders and if it will be greater than 5 get 10% off for her/him
 *
 * @author Amir Vedadiyan
 * @version 1.0
 */
public class Customer {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty nationalCode;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty emailAddress;
    private Gender gender;
    private int orderNumbers;
    private boolean discount;
    private City city;


    public Customer(String firstName, String lastName, String nationalCode, String phoneNumber, String emailAddress, Gender gender, City city) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nationalCode = new SimpleStringProperty(nationalCode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.gender = gender;
        this.city = city;
        orderNumbers = 0;
        discount = false;

    }

    public Customer(String firstName, String lastName, String nationalCode, String phoneNumber, String emailAddress, Gender gender) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nationalCode = new SimpleStringProperty(nationalCode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.gender = gender;
        orderNumbers = 0;
        discount = false;

    }


    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getNationalCode() {
        return nationalCode.get();
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = new SimpleStringProperty(nationalCode);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = new SimpleStringProperty(emailAddress);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getOrderNumbers() {
        return orderNumbers;
    }

    public void setOrderNumbers(int orderNumbers) {
        this.orderNumbers = orderNumbers;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    /**
     * This increase orderNumber of this customer and if they are more than 5 get off to her/him
     */
    public void order() {
        setOrderNumbers(orderNumbers + 1);
        if (getOrderNumbers() > 5) {
            setDiscount(true);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                nationalCode.equals(customer.nationalCode) &&
                emailAddress.equals(customer.emailAddress) &&
                phoneNumber.equals(customer.phoneNumber) &&
                Objects.equals(city, customer.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, nationalCode, phoneNumber, emailAddress, city);
    }

    @Override
    public String toString() {
        return "FirstName='" + firstName.get() + '\'' +
                ", LastName='" + lastName.get() + '\'' +
                ", City=" + city.getCityName();
    }
}
