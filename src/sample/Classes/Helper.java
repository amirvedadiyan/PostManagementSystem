package sample.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controller;
import sample.addParcelController;
import sample.addPersonController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This is Helper class that manage all classes and store needed data
 * @author Amir Vedaidyan
 * @version 1.0
 */

public class Helper {

    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<City> citys = new ArrayList<>();
    public static ArrayList<ParcelPost> parcelPosts = new ArrayList<>();

    public static void addCustomer(String firstName, String lastName, String nationalCode, String phoneNumber, String emailAddress, Gender gender, City city){
        Customer temp = new Customer(firstName,lastName,nationalCode,phoneNumber,emailAddress,gender,city);
        customers.add(temp);
        Controller.customerObservableList.add(temp);
        addParcelController.customersobObservableList.add(temp);
    }

    public static void removeCustomer(Customer remove){
        customers.remove(remove);
        Controller.customerObservableList.remove(remove);
        addParcelController.customersobObservableList.remove(remove);
    }

    public static void addCity(String cityName, double latitude, double longitude){
        City temp = new City(cityName,latitude,longitude);
        citys.add(temp);
        addPersonController.citys.add(temp);
        Controller.cityObservableList.add(temp);
        addParcelController.citiesoCityObservableList.add(temp);
    }

    public static void removeCity(City remove){
        citys.remove(remove);
        addPersonController.citys.remove(remove);
        Controller.cityObservableList.remove(remove);
        addParcelController.citiesoCityObservableList.remove(remove);
    }

    public static void addParcel(ParcelPost add){
        add.getSenderCustomer().order();
        Controller.parcelPostObservableList.add(add);
        parcelPosts.add(add);
    }

    public static void removeParcel(ParcelPost remove){
        parcelPosts.remove(remove);
        Controller.parcelPostObservableList.remove(remove);
    }

    public static void sendParcels(){
        LocalDateTime now = LocalDateTime.now();
        JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar(now.getYear(),now.getMonthValue(),now.getDayOfMonth()));
        Date nowDate = new Date(jalaliCalendar.getYear() ,jalaliCalendar.getMonth() - 1,jalaliCalendar.getDay());
        for(int i=0; i<parcelPosts.size() ;i++){
            if(Comparison(parcelPosts.get(i),now) == -1){
                if(parcelPosts.get(i).getParcelStatus() == ParcelStatus.NOT_SEND){
                    parcelPosts.get(i).setParcelStatus(ParcelStatus.NOT_RECEIVED);
                }else if(parcelPosts.get(i).getParcelStatus() == ParcelStatus.NOT_RECEIVED){
                    parcelPosts.get(i).setParcelStatus(ParcelStatus.RECEIVED);
                }
            }
        }
    }

    public static int Comparison(ParcelPost temp,LocalDateTime now){
            Date tempDate = temp.getSendDate();
            if(tempDate.getYear() > now.getYear()){
                return 1;
            }else if(tempDate.getYear() < now.getYear()){
                return -1;
            }else if(tempDate.getMonth() > now.getMonthValue()){
                return 1;
            }else if(tempDate.getMonth() < now.getMonthValue()){
                return -1;
            }else if(tempDate.getDay() > now.getDayOfMonth()){
                return 1;
            }else if(tempDate.getDay() < now.getDayOfMonth()){
                return -1;
            }else{
                return 0;
            }
    }

    public static ArrayList<City> getCities(){
        return citys;
    }

    public static ArrayList<ParcelPost> getParcelPosts(){ return parcelPosts; }

    public static ObservableList<Customer> searchCustomer(String s,String by){
        ObservableList<Customer> filter = FXCollections.observableArrayList();
        if(by.equals("First Name")){
            for(int i=0 ;i<customers.size() ;i++){
                if(customers.get(i).getFirstName().contains(s)){
                    filter.add(customers.get(i));
                }
            }
        }else if(by.equals("Last Name")){
            for(int i=0 ;i<customers.size() ;i++){
                if(customers.get(i).getLastName().contains(s)){
                    filter.add(customers.get(i));
                }
            }
        }else if(by.equals("National Code")){
            for(int i=0 ;i<customers.size() ;i++){
                if(customers.get(i).getNationalCode().contains(s)){
                    filter.add(customers.get(i));
                }
            }
        }else if(by.equals("Phone Number")){
            for(int i=0 ;i<customers.size() ;i++){
                if(customers.get(i).getPhoneNumber().contains(s)){
                    filter.add(customers.get(i));
                }
            }
        }else {
            for(int i=0 ;i<customers.size() ;i++){
                if(customers.get(i).getEmailAddress().contains(s)){
                    filter.add(customers.get(i));
                }
            }
        }



        return filter;
    }

    public static ObservableList<Customer> searchCustomer(String s)
    {
        ObservableList<Customer> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<customers.size() ;i++){
            if(customers.get(i).getFirstName().contains(s)){
                filter.add(customers.get(i));
            }
        }
        return filter;
    }

    public static ObservableList<ParcelPost> searchParcelWithSendCity(City s){
        ObservableList<ParcelPost> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getSenderCity().equals(s)){
                filter.add(parcelPosts.get(i));
            }
        }
        return  filter;
    }

    public static ObservableList<ParcelPost> searchParcelWithReceiveCity(City s){
        ObservableList<ParcelPost> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getReceiverCity().equals(s)){
                filter.add(parcelPosts.get(i));
            }
        }
        return  filter;
    }

    public static ObservableList<ParcelPost> searchParcelWithSending(SendingMethod s){
        ObservableList<ParcelPost> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getSendingMethod() == s){
                filter.add(parcelPosts.get(i));
            }
        }
        return  filter;
    }

    public static ObservableList<ParcelPost> searchParcelWithStatus(ParcelStatus s){
        ObservableList<ParcelPost> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getParcelStatus() == s){
                filter.add(parcelPosts.get(i));
            }
        }
        return  filter;
    }

    public static ObservableList<ParcelPost> searchParcelWithCustomer(Customer s){
        ObservableList<ParcelPost> filter = FXCollections.observableArrayList();
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getSenderCustomer().equals(s)){
                filter.add(parcelPosts.get(i));
            }
        }
        return  filter;
    }

    public static String getreportCustomer(){
        String report = "";
        for(int i=0 ;i<customers.size(); i++){
            report += "\n<tr>";
            report += "\n<td>" + customers.get(i).getFirstName() + "</td>";
            report += "\n<td>" + customers.get(i).getLastName() + "</td>";
            report += "\n<td>" + customers.get(i).getNationalCode() + "</td>";
            report += "\n<td>" + customers.get(i).getEmailAddress() + "</td>";
            report += "\n<td>" + customers.get(i).getPhoneNumber() + "</td>";
            report += "</tr>";
        }
        return report;
    }

    public static String getreportParcel(){
        String report = "";
        for(int i=0 ;i<parcelPosts.size() ;i++){
            report += "\n<tr>";
            report += "\n<td>" + parcelPosts.get(i).getParcelName() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getSenderCity() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getReceiverCity() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getSendDate() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getReceiveDate() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getWeight() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getSendingMethod() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getPostingMethod() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getParcelStatus() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getSenderCustomer() + "</td>";
            report += "\n<td>" + parcelPosts.get(i).getReceiverCustomer() + "</td>";
            report += "</tr>";
        }
        return report;
    }

    public static String getreportCity(){
        String report = "";
        for(int i=0 ;i<citys.size() ;i++){
            report += "\n<tr>";
            report += "\n<td>" + citys.get(i).getCityName() + "</td>";
            report += "\n<td>" + numberOfSend(citys.get(i)) + "</td>";
            report += "\n<td>" + numberOfReceive(citys.get(i)) + "</td>";
            report += "</tr>";
        }
        return report;
    }

    public static int numberOfSend(City temp){
        int number = 0;
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getSenderCity().equals(temp)){
                number ++;
            }
        }
        return number;
    }

    public static int numberOfReceive(City temp){
        int number = 0;
        for(int i=0 ;i<parcelPosts.size() ;i++){
            if(parcelPosts.get(i).getReceiverCity().equals(temp)){
                number ++;
            }
        }
        return number;
    }

}