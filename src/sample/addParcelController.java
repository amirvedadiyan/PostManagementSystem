package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Classes.*;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class addParcelController implements Initializable {

    @FXML private TextField parcelNameTextField;
    @FXML private TextField weightTextField;
    @FXML private ComboBox<Customer> senderCombo;
    @FXML private ComboBox<Customer> receiverCombo;
    @FXML private ComboBox<City> sendCityCombo;
    @FXML private ComboBox<City> receiverCityCombo;
    @FXML private ComboBox<SendingMethod> sendMethodeCombo;
    @FXML private ComboBox<PostingMethod> postMethodeCombo;
    @FXML private ComboBox<ParcelStatus> statusCombo;
    @FXML private DatePicker sendDatePicker;
    @FXML private DatePicker receiveDatePicker;
    @FXML private Button saveParcelbtn;
    @FXML private Button cancelParcelbtn;


    public static ObservableList<Customer> customersobObservableList = FXCollections.observableArrayList();
    public static ObservableList<City> citiesoCityObservableList = FXCollections.observableArrayList();
    public static ObservableList<SendingMethod> sendingMethodsoObservableList = FXCollections.observableArrayList(SendingMethod.LANDING_SEND,SendingMethod.AIR_SEND,SendingMethod.SEA_SEND);
    public static ObservableList<PostingMethod> postingMethodObservableList = FXCollections.observableArrayList(PostingMethod.NORMAL_POST,PostingMethod.SPECIAL_POST);
    public static ObservableList<ParcelStatus> parcelStatusObservableList = FXCollections.observableArrayList(ParcelStatus.NOT_SEND,ParcelStatus.NOT_RECEIVED,ParcelStatus.RECEIVED);


    @FXML
    void exit(ActionEvent event){
        Stage currnStage = (Stage) cancelParcelbtn.getScene().getWindow();
        currnStage.close();
    }

    @FXML
    void saveParcel(ActionEvent event){
        String parcelName = parcelNameTextField.getText();
        String weight = weightTextField.getText();
        Customer sender = senderCombo.getSelectionModel().getSelectedItem();
        Customer receiver = receiverCombo.getSelectionModel().getSelectedItem();
        City sendCity = sendCityCombo.getSelectionModel().getSelectedItem();
        City receiverCity = receiverCityCombo.getSelectionModel().getSelectedItem();
        SendingMethod sendingMethod = sendMethodeCombo.getSelectionModel().getSelectedItem();
        PostingMethod postingMethod = postMethodeCombo.getSelectionModel().getSelectedItem();
        ParcelStatus parcelStatus = statusCombo.getSelectionModel().getSelectedItem();

        //Checking and Validating
        boolean flag = true;

        if(!Validator.checkName(parcelName)){
            flag = false;
            parcelNameTextField.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            parcelNameTextField.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkNumberOnly(weight)){
            flag = false;
            weightTextField.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            weightTextField.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(senderCombo.getSelectionModel().isEmpty()){
            flag = false;
            senderCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            senderCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(receiverCombo.getSelectionModel().isEmpty() || senderCombo.getSelectionModel().getSelectedItem().equals(receiverCombo.getSelectionModel().getSelectedItem())){
            flag = false;
            receiverCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            receiverCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(sendCityCombo.getSelectionModel().isEmpty()){
            flag = false;
            sendCityCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            sendCityCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(receiverCityCombo.getSelectionModel().isEmpty()){
            flag = false;
            receiverCityCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            receiverCityCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(sendMethodeCombo.getSelectionModel().isEmpty()){
            flag = false;
            sendMethodeCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            sendMethodeCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(postMethodeCombo.getSelectionModel().isEmpty()){
            flag = false;
            postMethodeCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            postMethodeCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(statusCombo.getSelectionModel().isEmpty()){
            flag = false;
            statusCombo.setStyle("-fx-background-color: #ff0000");
        }else{
            statusCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(sendDatePicker.getValue() == null){
            flag = false;
            sendDatePicker.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            sendDatePicker.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(receiveDatePicker.getValue() == null){
            flag = false;
            receiveDatePicker.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            receiveDatePicker.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(City.calculateDistance(sendCity,receiverCity) > 500 && sendingMethod.equals(SendingMethod.LANDING_SEND)){
            flag = false;
            sendMethodeCombo.setStyle("-fx-background-color: #ff0000");
        }else if(sendMethodeCombo.getSelectionModel().getSelectedItem()!= null){
            sendMethodeCombo.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n");;
        }

        if(flag){
            JalaliCalendar sendJalaliCalendar = new JalaliCalendar(new GregorianCalendar(sendDatePicker.getValue().getYear(),sendDatePicker.getValue().getMonth().getValue() - 1,sendDatePicker.getValue().getDayOfMonth()));
            Date sendDate = new Date(sendJalaliCalendar.getYear(),sendJalaliCalendar.getMonth(),sendJalaliCalendar.getDay());
            JalaliCalendar receiveJalaliCalender = new JalaliCalendar(new GregorianCalendar(receiveDatePicker.getValue().getYear(),receiveDatePicker.getValue().getMonth().getValue() - 1,receiveDatePicker.getValue().getDayOfMonth()));
            Date receiveDate = new Date(receiveJalaliCalender.getYear(),receiveJalaliCalender.getMonth(),receiveJalaliCalender.getDay());
            double price = 0;
            price += Double.valueOf(weight) * 1000;
            price += City.calculateDistance(sendCity,receiverCity) * 100;
            if(sendingMethod.equals(SendingMethod.AIR_SEND)){
                price *= 2;
            }
            if(sendingMethod.equals(SendingMethod.SEA_SEND)){
                price *= 1.5;
            }
            if(postingMethod.equals(PostingMethod.SPECIAL_POST)){
                price *= 2;
            }
            if(sender.isDiscount()){
                price *= 0.9;
            }

            ParcelPost temp = new ParcelPost(parcelName,sender,receiver,sendCity,receiverCity,Double.valueOf(weight),price,sendDate,receiveDate,sendingMethod,postingMethod,parcelStatus);
            Helper.addParcel(temp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Price Detail");
            alert.setHeaderText(null);
            alert.setContentText("10% OFF : " + sender.isDiscount() + "\n" +"Price : " + price + " Toman");
            alert.showAndWait();
            Stage currnStage = (Stage) saveParcelbtn.getScene().getWindow();
            currnStage.close();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        senderCombo.setItems(customersobObservableList);
        receiverCombo.setItems(customersobObservableList);
        sendCityCombo.setItems(citiesoCityObservableList);
        receiverCityCombo.setItems(citiesoCityObservableList);
        sendMethodeCombo.setItems(sendingMethodsoObservableList);
        postMethodeCombo.setItems(postingMethodObservableList);
        statusCombo.setItems(parcelStatusObservableList);


    }
}
