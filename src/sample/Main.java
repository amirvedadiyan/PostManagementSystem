package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Classes.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Post Office");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        Helper.addCity("Tehran",35.6,51.38);
        Helper.addCity("Isfahan",32.65,51.6);
        Helper.addCity("Mashhad",36.2,59.6);
        Helper.addCity("Shiraz",29.5,52.58);
        Helper.addCity("Rasht",37.2,49.5);


        Helper.addCustomer("pegah", "taghavi", "0440896770", "09126654902", "pegah@hotmail.com", Gender.FEMALE, new City("Tehran",35.6,51.38));
        Helper.addCustomer("behroz", "naji", "0872772713", "09125962001", "behroz_naji@gmail.com", Gender.MALE, new City("Isfahan",32.65,51.6));
        Helper.addCustomer("dadmehr", "aghili", "0272292222", "09125546208", "dadmehr1377@yahoo.com", Gender.MALE, new City("Mashhad",36.2,59.6));
        Helper.addCustomer("mojdeh", "haghani", "0306169509", "09120054630", "Mojdeh8898@mail.com", Gender.FEMALE, new City("Shiraz",29.5,52.58));
        Helper.addCustomer("armin", "yazdani", "0116525770", "09129985674", "armin_yazdani@kntu.ac.ir", Gender.MALE, new City("Rasht",37.2,49.5));

        launch(args);
    }
}
