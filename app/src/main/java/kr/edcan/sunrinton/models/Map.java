package kr.edcan.sunrinton.models;

/**
 * Created by Junseok Oh on 2017-07-25.
 */

public class Map {
    private String title, address;
    private double longitude, latitude;

    public Map(String title, String address, double longitude, double latitude) {
        this.title = title;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
