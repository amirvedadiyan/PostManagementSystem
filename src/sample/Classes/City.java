package sample.Classes;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * This is City class with cityName and coordinate fields and calculateDistance static method to use calculate distance between 2 cites
 * @author Amir Vedadiyan
 * @version 1.0
 */

public class City {
    private SimpleStringProperty cityName;
    private Coordinate coordinate;

    public City(String cityName) {
        this.cityName = new SimpleStringProperty(cityName);
        coordinate = new Coordinate();
    }

    public City(String cityName, Coordinate coordinate) {
        this.cityName = new SimpleStringProperty(cityName);
        this.coordinate = coordinate;
    }

    public City(String cityName, double latitude, double longitude){
        this.cityName = new SimpleStringProperty(cityName);
        coordinate = new Coordinate(latitude,longitude);
    }

    public String getCityName() { return cityName.get(); }

    public void setCityName(String cityName) {
        this.cityName = new SimpleStringProperty(cityName);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public static double calculateDistance(City firstCity,City secondCity){
        double cosb = Math.cos(firstCity.getCoordinate().getLongitude());
        double cosc = Math.cos(secondCity.getCoordinate().getLongitude());
        double sinb = Math.sin(firstCity.getCoordinate().getLongitude());
        double sinc = Math.sin(secondCity.getCoordinate().getLongitude());
        double cosA = Math.cos(firstCity.getCoordinate().getLatitude() - secondCity.getCoordinate().getLatitude());
        double cosa = (cosb * cosc) + (sinb * sinc * cosA);
        double distance = Math.acos(cosa) * Math.PI / 180 * 6400;
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityName.equals(city.cityName) &&
                Objects.equals(coordinate, city.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, coordinate);
    }

    @Override
    public String toString() {
        return
                 cityName.get() ;

    }
}
