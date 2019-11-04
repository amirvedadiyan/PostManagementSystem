package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Classes.*;

import java.net.URL;
import java.util.ResourceBundle;

public class addPersonController implements Initializable {

    public static ObservableList<City> citys = FXCollections.observableArrayList();
    public static ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.MALE,Gender.FEMALE,Gender.UNKNOWN);


    @FXML
    private  TextField firstName;

    @FXML
    private  TextField lastName;

    @FXML
    private  TextField nationalCode;

    @FXML
    private  TextField phoneNumber;

    @FXML
    private  TextField email;

    @FXML
    private  Button cancel;

    @FXML
    private  Button save;

    @FXML
    private ComboBox<City> selectCity;

    @FXML
    private ComboBox<Gender> selectGender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectCity.setItems(citys);
        selectGender.setItems(genders);
    }


    @FXML
    void close(ActionEvent event){
        Stage current = (Stage) cancel.getScene().getWindow();
        current.close();

    }

    @FXML
    void save(ActionEvent event){
        String first = firstName.getText();
        String last = lastName.getText();
        String national = nationalCode.getText();
        String mail = email.getText();
        String phone = phoneNumber.getText();

        boolean flag = true;

        if(!Validator.checkName(first)){
            flag = false;
            firstName.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            firstName.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkName(last)){
            flag = false;
            lastName.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            lastName.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkNationalCode(national)){
            flag = false;
            nationalCode.setStyle("-fx-control-inner-background: #ff0000");
        } else{
            nationalCode.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkEmail(mail)){
            flag = false;
            email.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            email.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkMobile(phone)){
            flag = false;
            phoneNumber.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            phoneNumber.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(selectCity.getSelectionModel().isEmpty()){
            flag = false;
            selectCity.setStyle("-fx-background-color: #ff0000");
        }else {
            selectCity.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n" +
                    "    -fx-background-radius: 3px, 3px, 2px, 1px;\n" +
                    "    -fx-text-fill: -fx-text-base-color;\n" +
                    "    -fx-alignment: CENTER;\n" +
                    "    -fx-content-display: LEFT;");
        }

        if(selectGender.getSelectionModel().isEmpty()){
            flag = false;
            selectGender.setStyle("-fx-background-color: #ff0000");
        }else{
            selectGender.setStyle("-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;\n" +
                    "    -fx-background-radius: 3px, 3px, 2px, 1px;\n" +
                    "    -fx-text-fill: -fx-text-base-color;\n" +
                    "    -fx-alignment: CENTER;\n" +
                    "    -fx-content-display: LEFT;");
        }

        if(flag){
            Helper.addCustomer(firstName.getText(),lastName.getText(),nationalCode.getText(),phoneNumber.getText(),email.getText(), selectGender.getSelectionModel().getSelectedItem() ,selectCity.getSelectionModel().getSelectedItem());
            Stage current = (Stage) save.getScene().getWindow();
            current.close();
        }

    }

}
