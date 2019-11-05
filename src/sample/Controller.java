package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.Classes.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer,String> firstName;
    @FXML private TableColumn<Customer,String> lastName;
    @FXML private TableColumn<Customer,String> nationalCode;
    @FXML private TableColumn<Customer,String> phoneNumber;
    @FXML private TableColumn<Customer,String> email;
    @FXML private TableColumn<Customer,String> city;
    public static ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();


    @FXML private TableView<City> cityTable;
    @FXML private TableColumn<City,String> cityName;
    @FXML private TableColumn<City,String> coordinate;
    public static ObservableList<City> cityObservableList = FXCollections.observableArrayList();

    @FXML private TextField cityNameTextField;
    @FXML private TextField cityLatitudeTextField;
    @FXML private TextField cityLongitudeTextField;

    @FXML private TableView<ParcelPost> parcelTable;
    @FXML private TableColumn<ParcelPost,String> parcelName;
    @FXML private TableColumn<ParcelPost,String> sender;
    @FXML private TableColumn<ParcelPost,String> receiver;
    @FXML private TableColumn<ParcelPost,String> sendCity;
    @FXML private TableColumn<ParcelPost,String> receiverCity;
    @FXML private TableColumn<ParcelPost,String> currentCity;
    @FXML private TableColumn<ParcelPost,String> weight;
    @FXML private TableColumn<ParcelPost,String> sendDate;
    @FXML private TableColumn<ParcelPost,String> receiveDate;
    @FXML private TableColumn<ParcelPost,String> sendingMethod;
    @FXML private TableColumn<ParcelPost,String> postingMethod;
    @FXML private TableColumn<ParcelPost,String> status;
    public static ObservableList<ParcelPost> parcelPostObservableList = FXCollections.observableArrayList();

    @FXML private TextField searchCustomerTextField;
    @FXML private ComboBox<String> searchCustomerWithCombo;
    public static ObservableList<String> searchObservableList = FXCollections.observableArrayList("First Name", "Last Name","National Code","Phone Number","Email");

    @FXML private ComboBox<City> searchParcelWithSendCity;
    @FXML private ComboBox<City> SearchParcelWithReceiverCity;
    @FXML private ComboBox<SendingMethod> searchParcelWithSending;
    @FXML private ComboBox<ParcelStatus> searchParcelWithStatus;
    @FXML private ComboBox<Customer> searchParcelWithCustomer;





    @FXML
    void addPerson(ActionEvent event) {
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("addPersonFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage addPersonStage = new Stage();
        addPersonStage.setTitle("Add Person");
        addPersonStage.setScene(new Scene(root1));
        addPersonStage.show();
    }

    @FXML
    void addParcelPost(ActionEvent event){
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("addParcelFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage addPersonStage = new Stage();
        addPersonStage.setTitle("Add Parcel");
        addPersonStage.setScene(new Scene(root1));
        addPersonStage.show();
    }

    @FXML
    void postalTracking(ActionEvent event){
        ArrayList<ParcelPost> choices = Helper.getParcelPosts();
        if(choices.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setHeaderText(null);
            alert.setContentText("Not Any Parcel Found!");
            alert.showAndWait();
            return;
        }
        ChoiceDialog<ParcelPost> dialog = new ChoiceDialog<ParcelPost>(choices.get(0),choices);
        dialog.setTitle("Parcel Tracking");
        dialog.setHeaderText("Track Parcel");
        dialog.setContentText("Select Parcel:");

        // Traditional way to get the response value.
        Optional<ParcelPost> result = dialog.showAndWait();
        if (result.isPresent()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Status");
            alert.setHeaderText(null);
            alert.setContentText("Status : " + result.get().getParcelStatus());
            alert.showAndWait();
            System.out.println(result.get().getCurrentCity().getCityName());
            MapConsumer.getInstance().accept(result.get().getCurrentCity().getCityName());
        }
    }

    @FXML
    void exit(ActionEvent event) {
       System.exit(0);
    }

    @FXML
    void saveCity(ActionEvent event){
        String name = cityNameTextField.getText();
        boolean flag = true;

        if(!Validator.checkName(name)){
            flag = false;
            cityNameTextField.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            cityNameTextField.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkNumberOnly(cityLatitudeTextField.getText())){
            flag = false;
            cityLatitudeTextField.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            cityLatitudeTextField.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(!Validator.checkNumberOnly(cityLongitudeTextField.getText())){
            flag = false;
            cityLongitudeTextField.setStyle("-fx-control-inner-background: #ff0000");
        }else{
            cityLongitudeTextField.setStyle("-fx-control-inner-background: #ffffff");
        }

        if(flag){
            double latitude = Double.valueOf(cityLatitudeTextField.getText());
            double longitude = Double.valueOf(cityLongitudeTextField.getText());
            Helper.addCity(name,latitude,longitude);
            cityNameTextField.setText("");
            cityLatitudeTextField.setText("");
            cityLongitudeTextField.setText("");
        }

    }

    @FXML
    public void sendParcels(){
        Helper.sendParcels();
        parcelTable.refresh();
    }

    @FXML
    public void updateParcel(MouseEvent event){
        if (event.getClickCount() == 2){
            ArrayList<City> choices = Helper.getCities();
            ChoiceDialog<City> dialog = new ChoiceDialog<City>(choices.get(0),choices);
            dialog.setTitle("Update City");
            dialog.setHeaderText("Change Current City");
            dialog.setContentText("Select New City:");
            Optional<City> result = dialog.showAndWait();
            if (result.isPresent()){
                parcelTable.getSelectionModel().getSelectedItem().setCurrentCity(result.get());
                parcelTable.refresh();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize Customer Table
        firstName.setCellValueFactory(new PropertyValueFactory<Customer,String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customer,String>("lastName"));
        nationalCode.setCellValueFactory(new PropertyValueFactory<Customer,String>("nationalCode"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Customer,String>("phoneNumber"));
        email.setCellValueFactory(new PropertyValueFactory<Customer,String>("emailAddress"));
        city.setCellValueFactory(new PropertyValueFactory<Customer,String>("city"));
        customerTable.setItems(customerObservableList);

        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        nationalCode.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        //Initialize City Table
        cityName.setCellValueFactory(new PropertyValueFactory<City,String>("cityName"));
        coordinate.setCellValueFactory(new PropertyValueFactory<City,String>("coordinate"));
        cityTable.setItems(cityObservableList);
        cityName.setCellFactory(TextFieldTableCell.forTableColumn());
        //Initialize Parcel Table
        parcelName.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("parcelName"));
        sender.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("senderCustomer"));
        receiver.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("receiverCustomer"));
        currentCity.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("currentCity"));
        sendCity.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("senderCity"));
        receiverCity.setCellValueFactory(new PropertyValueFactory<ParcelPost,String >("receiverCity"));
        weight.setCellValueFactory(new PropertyValueFactory<ParcelPost,String >("weight"));
        sendDate.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("sendDate"));
        receiveDate.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("receiveDate"));
        sendingMethod.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("sendingMethod"));
        postingMethod.setCellValueFactory(new PropertyValueFactory<ParcelPost,String>("postingMethod"));

        status.setCellValueFactory(new PropertyValueFactory("parcelStatus"));
        parcelTable.setItems(parcelPostObservableList);

        searchCustomerWithCombo.setItems(searchObservableList);

        searchParcelWithSendCity.setItems(cityObservableList);
        SearchParcelWithReceiverCity.setItems(cityObservableList);
        ObservableList<SendingMethod> sendingMethodObservableList = FXCollections.observableArrayList(SendingMethod.LANDING_SEND,SendingMethod.AIR_SEND,SendingMethod.SEA_SEND);
        searchParcelWithSending.setItems(sendingMethodObservableList);
        ObservableList<ParcelStatus> statusObservableList = FXCollections.observableArrayList(ParcelStatus.NOT_SEND,ParcelStatus.NOT_RECEIVED,ParcelStatus.RECEIVED);
        searchParcelWithStatus.setItems(statusObservableList);
        searchParcelWithCustomer.setItems(customerObservableList);
    }

    @FXML
    public void firstNameEdit(TableColumn.CellEditEvent<Customer,String> customerStringCellEditEvent){
        Customer edit = customerTable.getSelectionModel().getSelectedItem();
        edit.setFirstName(customerStringCellEditEvent.getNewValue());

    }

    @FXML
    public void lastNameEdit(TableColumn.CellEditEvent<Customer,String> customerStringCellEditEvent){
        Customer edit = customerTable.getSelectionModel().getSelectedItem();
        edit.setFirstName(customerStringCellEditEvent.getNewValue());
    }

    @FXML
    public void nationalCodeEdit(TableColumn.CellEditEvent<Customer,String> customerStringCellEditEvent){
        Customer edit = customerTable.getSelectionModel().getSelectedItem();
        edit.setFirstName(customerStringCellEditEvent.getNewValue());
    }

    @FXML
    public void phoneNumberEdit(TableColumn.CellEditEvent<Customer,String> customerStringCellEditEvent){
        Customer edit = customerTable.getSelectionModel().getSelectedItem();
        edit.setFirstName(customerStringCellEditEvent.getNewValue());
    }

    @FXML
    public void emailEdit(TableColumn.CellEditEvent<Customer,String> customerStringCellEditEvent){
        Customer edit = customerTable.getSelectionModel().getSelectedItem();
        edit.setFirstName(customerStringCellEditEvent.getNewValue());
    }

    @FXML
    public void editCustomerCity(MouseEvent event){
        if(event.getClickCount() == 2){
            Customer edit = customerTable.getSelectionModel().getSelectedItem();
            ArrayList<City> choices = Helper.getCities();
            ChoiceDialog<City> dialog = new ChoiceDialog<City>(choices.get(0),choices);
            dialog.setTitle("Edit City");
            dialog.setHeaderText("Change  City");
            dialog.setContentText("Select New City:");
            Optional<City> result = dialog.showAndWait();
            if (result.isPresent()){
                edit.setCity(result.get());
                customerTable.refresh();
            }

        }
    }

    @FXML
    public void cityNameEdit(TableColumn.CellEditEvent<City,String> cityStringCellEditEvent){
        City edit = cityTable.getSelectionModel().getSelectedItem();
        edit.setCityName(cityStringCellEditEvent.getNewValue());
    }

    @FXML
    public void editCoordinate(MouseEvent event){
            if(event.getClickCount() == 2){
                City edit = cityTable.getSelectionModel().getSelectedItem();
                TextInputDialog dialog = new TextInputDialog("25.6 85.6");
                dialog.setTitle("Edit Coordinate");
                dialog.setHeaderText("Edit Latitude and Longitude");
                dialog.setContentText("Enter new Values:");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    String newCoordinate = result.get();
                    int spacePlace = newCoordinate.indexOf(" ");
                    String latitude = newCoordinate.substring(0,spacePlace);
                    String longitude = newCoordinate.substring(spacePlace);
                    edit.setCoordinate(new Coordinate(Double.valueOf(latitude),Double.valueOf(longitude)));
                    cityTable.refresh();
                }
            }
    }

    @FXML
    public void searchCustomer(KeyEvent event){
        ObservableList<Customer> filterCustomers;
        String s = searchCustomerTextField.getText();
        if(s.equals("")){
            customerTable.setItems(customerObservableList);
        }else if(searchCustomerWithCombo.getSelectionModel().isEmpty()){
            filterCustomers = Helper.searchCustomer(s);
            customerTable.setItems(filterCustomers);
        }else {
            filterCustomers = Helper.searchCustomer(s,searchCustomerWithCombo.getSelectionModel().getSelectedItem());
            customerTable.setItems(filterCustomers);
        }

    }

    @FXML
    public void searchWithSendCity(ActionEvent event){
        ObservableList<ParcelPost> filterParcelObservableList;
        filterParcelObservableList = Helper.searchParcelWithSendCity(searchParcelWithSendCity.getSelectionModel().getSelectedItem());
        parcelTable.setItems(filterParcelObservableList);
    }

    @FXML
    public void searchWithReceiverCity(ActionEvent event){
        ObservableList<ParcelPost> filterParcelObservableList;
        filterParcelObservableList = Helper.searchParcelWithReceiveCity(SearchParcelWithReceiverCity.getSelectionModel().getSelectedItem());
        parcelTable.setItems(filterParcelObservableList);
    }

    @FXML
    public void searchWithSending(ActionEvent event){
        ObservableList<ParcelPost> filterParcelObservableList;
        filterParcelObservableList = Helper.searchParcelWithSending(searchParcelWithSending.getSelectionModel().getSelectedItem());
        parcelTable.setItems(filterParcelObservableList);
    }

    @FXML
    public void searchWithStatus(ActionEvent event){
        ObservableList<ParcelPost> filterParcelObservableList;
        filterParcelObservableList = Helper.searchParcelWithStatus(searchParcelWithStatus.getSelectionModel().getSelectedItem());
        parcelTable.setItems(filterParcelObservableList);
    }

    @FXML
    public void searchWithCustomer(ActionEvent event){
        ObservableList<ParcelPost> filterParcelObservableList;
        filterParcelObservableList = Helper.searchParcelWithCustomer(searchParcelWithCustomer.getSelectionModel().getSelectedItem());
        parcelTable.setItems(filterParcelObservableList);
    }

    @FXML
    public void clearFilter(ActionEvent event){
        parcelTable.setItems(parcelPostObservableList);
    }

    @FXML
    public void reportCustomers(){
        String starthtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "  text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Customer Report</h2>\n" +
                "<table style=\"width:100%\">\n" +
                "<tr>\n" +
                "<th>First Name</th>\n" +
                "<th>Last Name</th>\n" +
                "<th>National Code</th>\n" +
                "<th>Phone Number</th>\n" +
                "<th>Email Address</th>\n" +
                "</tr>\n";
        String endHtml = "</table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if(file!=null){
            String path = file.getAbsolutePath() + "\\CustomerReport.html";
            System.out.println("File Saved in :" + path);
            File report = new File(path);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(report));
                starthtml += Helper.getreportCustomer();
                String html = starthtml + endHtml;
                writer.write(html);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void reportParcel(){
        String starthtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "  text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Parcel Report</h2>\n" +
                "<table style=\"width:100%\">\n" +
                "<tr>\n" +
                "<th>Parcel Name</th>\n" +
                "<th>Send City</th>\n" +
                "<th>Receive City</th>\n" +
                "<th>Send Date</th>\n" +
                "<th>Receive Date</th>\n" +
                "<th>Weight</th>\n" +
                "<th>Sending Method</th>\n" +
                "<th>Posting Method</th>\n" +
                "<th>Status</th>\n" +
                "<th>Sender</th>\n" +
                "<th>Receiver</th>\n" +
                "</tr>\n";
        String endHtml = "</table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if(file!=null){
            String path = file.getAbsolutePath() + "\\ParcelReport.html";
            System.out.println("File Saved in :" + path);
            File report = new File(path);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(report));
                starthtml += Helper.getreportParcel();
                String html = starthtml + endHtml;
                writer.write(html);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void reportCity(){
        String starthtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "  padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "  text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>City Report</h2>\n" +
                "<table style=\"width:100%\">\n" +
                "<tr>\n" +
                "<th>City Name</th>\n" +
                "<th>City Reports</th>\n" +
                "<th>Number of Receive Parcel's</th>\n" +
                "</tr>\n";
        String endHtml = "</table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if(file!=null){
            String path = file.getAbsolutePath() + "\\CityReport.html";
            System.out.println("File Saved in :" + path);
            File report = new File(path);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(report));
                starthtml += Helper.getreportCity();
                String html = starthtml + endHtml;
                writer.write(html);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
