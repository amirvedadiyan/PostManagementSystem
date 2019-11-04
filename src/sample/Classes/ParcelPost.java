package sample.Classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * This is parcels post class
 * @author Amir Vedadiyan
 * @version 1.0
 */
public class ParcelPost {
    private SimpleStringProperty parcelName;
    private Customer senderCustomer;
    private Customer receiverCustomer;
    private City senderCity;
    private City receiverCity;
    private City currentCity;
    private SimpleDoubleProperty weight;
    private SimpleDoubleProperty price;
    private Date sendDate;
    private Date receiveDate;
    private Date duration;
    private SendingMethod sendingMethod;
    private PostingMethod postingMethod;
    private ParcelStatus parcelStatus;

    public ParcelPost(String parcelName, Customer senderCustomer, Customer receiverCustomer, City senderCity, City receiverCity, double weight, double price, Date sendDate, Date receiveDate, SendingMethod sendingMethod, PostingMethod postingMethod, ParcelStatus parcelStatus) {
        this.parcelName = new SimpleStringProperty(parcelName);
        this.senderCustomer = senderCustomer;
        this.receiverCustomer = receiverCustomer;
        this.senderCity = senderCity;
        this.currentCity = senderCity;
        this.receiverCity = receiverCity;
        this.sendDate = sendDate;
        this.receiveDate = receiveDate;
        this.weight = new SimpleDoubleProperty(weight);
        this.price = new SimpleDoubleProperty(price);
        this.sendingMethod = sendingMethod;
        this.postingMethod = postingMethod;
        this.currentCity = new City(senderCity.getCityName(),senderCity.getCoordinate());
        this.parcelStatus = parcelStatus;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getParcelName() {
        return parcelName.get();
    }

    public void setParcelName(String parcelName) {
        this.parcelName = new SimpleStringProperty(parcelName);
    }

    public Customer getSenderCustomer() {
        return senderCustomer;
    }

    public void setSenderCustomer(Customer senderCustomer) {
        this.senderCustomer = senderCustomer;
    }

    public Customer getReceiverCustomer() {
        return receiverCustomer;
    }

    public void setReceiverCustomer(Customer receiverCustomer) {
        this.receiverCustomer = receiverCustomer;
    }

    public City getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(City senderCity) {
        this.senderCity = senderCity;
    }

    public City getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(City receiverCity) {
        this.receiverCity = receiverCity;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public double getWeight() {
        return weight.get();
    }

    public void setWeight(double weight) {
        this.weight = new SimpleDoubleProperty(weight);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public SendingMethod getSendingMethod() {
        return sendingMethod;
    }

    public void setSendingMethod(SendingMethod sendingMethod) {
        this.sendingMethod = sendingMethod;
    }

    public PostingMethod getPostingMethod() {
        return postingMethod;
    }

    public void setPostingMethod(PostingMethod postingMethod) {
        this.postingMethod = postingMethod;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }


    public void send(){
        setParcelStatus(ParcelStatus.NOT_RECEIVED);
    }


    public void receive(){
        setParcelStatus(ParcelStatus.RECEIVED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcelPost that = (ParcelPost) o;
        return Objects.equals(parcelName, that.parcelName) &&
                Objects.equals(senderCustomer, that.senderCustomer) &&
                Objects.equals(receiverCustomer, that.receiverCustomer) &&
                Objects.equals(senderCity, that.senderCity) &&
                Objects.equals(receiverCity, that.receiverCity) &&
                Objects.equals(currentCity, that.currentCity) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(price, that.price) &&
                Objects.equals(sendDate, that.sendDate) &&
                Objects.equals(receiveDate, that.receiveDate) &&
                Objects.equals(duration, that.duration) &&
                sendingMethod == that.sendingMethod &&
                postingMethod == that.postingMethod &&
                parcelStatus == that.parcelStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parcelName, senderCustomer, receiverCustomer, senderCity, receiverCity, currentCity, weight, price, sendDate, receiveDate, duration, sendingMethod, postingMethod, parcelStatus);
    }

    @Override
    public String toString() {
        return "parcelName=" + parcelName.get() +
                ", senderCustomer=" + senderCustomer +
                ", senderCity=" + senderCity;
    }
}
