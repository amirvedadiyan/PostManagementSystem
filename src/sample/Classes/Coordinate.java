package sample.Classes;

import java.util.Objects;

/**
 * This is coordinate class that use in City class
 * @author Amir Vedadiyan
 * @version 1.0
 */

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(){
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "latitude=" + latitude +
                ", longitude=" + longitude ;
    }
}
