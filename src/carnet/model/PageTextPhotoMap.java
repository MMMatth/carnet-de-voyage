package carnet.model;

import java.time.LocalDate;

public class PageTextPhotoMap extends PageTextPhoto{

    private Float latitude;
    private Float longitude;
    private Float zoom;

    public PageTextPhotoMap(String contenu, String imgPath, LocalDate date, Float latitude, Float longitude, Float zoom) {
        super(date,contenu,imgPath);
        this.latitude = latitude;
        this.longitude = longitude;
        this.zoom = zoom * 0.001f;
    }

    public PageTextPhotoMap() {
        super();
        this.latitude = 48.6937F;
        this.longitude = 6.185F;
        this.zoom = 0.01f;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getOpenStreetMapLink(){
        StringBuilder link = new StringBuilder().append("https://www.openstreetmap.org/export/embed.html?bbox=");
        link.append(longitude + zoom).append("%2C");
        link.append(latitude + zoom).append("%2C");
        link.append(longitude - zoom).append("%2C");
        link.append(latitude - zoom).append("&amp;layer=mapnik");
        return link.toString();
    }

    public boolean estTextPhotoMap() {
        return true;
    }

    public boolean estTextPhoto() {
        return false;
    }

    public Float getZoom() {
        return zoom;
    }

    public void setLatitude(float v) {
        this.latitude = v;
    }

    public void setLongitude(float v) {
        this.longitude = v;
    }

    public void setZoom(int v) {
        if ( v <= 0){
            v = 1;
        }
        this.zoom = 0.01f / v;
    }
}
