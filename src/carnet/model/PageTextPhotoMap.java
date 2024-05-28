package carnet.model;

import java.net.URI;
import java.time.LocalDate;

public class PageTextPhotoMap extends PageTextPhoto{

    private Double marker_long;
    private Double marker_lat;

    private Double center_long;
    private Double center_lat;

    private Double zoom;

    public PageTextPhotoMap(String contenu, String imgPath, LocalDate date, Double markerLong, Double marketLat, Double centerLong , Double centerLat, Double zoom) {
        super(date,contenu,imgPath);

        this.marker_lat = marketLat;
        this.marker_long = markerLong;

        this.center_lat = centerLat;
        this.center_long = centerLong;

        this.zoom = zoom;
    }

    public PageTextPhotoMap() {
        super();

        URI uri = URI.create(getClass().getResource("/image/page/imgBasePetit.png").toExternalForm());
        this.setImgPath(uri.getPath());

        this.marker_lat = 48.6937;
        this.marker_long = 6.185;

        this.center_lat = 48.6937;
        this.center_long = 6.185;

        this.zoom = 15.0;
    }

    public Double getMarker_long() {
        return marker_long;
    }

    public Double getMarker_lat() {
        return marker_lat;
    }


    public String getOpenStreetMapLink(){
        StringBuilder link = new StringBuilder().append("https://www.openstreetmap.org/export/embed.html?bbox=");
        Float zoomOffset = 0.05f;
        link.append(marker_long + zoomOffset).append("%2C");
        link.append(marker_lat + zoomOffset).append("%2C");
        link.append(marker_long - zoomOffset).append("%2C");
        link.append(marker_lat - zoomOffset).append("&amp;layer=mapnik");
        return link.toString();
    }

    public boolean estTextPhotoMap() {
        return true;
    }

    public boolean estTextPhoto() {
        return false;
    }

    public Double getZoom() {
        return zoom;
    }

    public void setMarker_long(Double v) {
        this.marker_long = v;
    }

    public void setMarker_lat(Double v) {
        this.marker_lat = v;
    }

    public void setZoom(Double v) {
        this.zoom = v;
    }

    public Double getCenter_long() {
        return center_long;
    }

    public Double getCenter_lat() {
        return center_lat;
    }

    public void setCenter_long(Double v) {
        this.center_long = v;
    }

    public void setCenter_lat(Double v) {
        this.center_lat = v;
    }

    public String toJson(){
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"type\": \"textPhotoMap\",\n");
        json.append("  \"date\": \"").append(getDate()).append("\",\n");
        json.append("  \"contenu\": \"").append(getContenu()).append("\",\n");
        json.append("  \"imgPath\": \"").append(getImgPath()).append("\",\n");
        json.append("  \"marker_long\": ").append(marker_long).append(",\n");
        json.append("  \"marker_lat\": ").append(marker_lat).append(",\n");
        json.append("  \"center_long\": ").append(center_long).append(",\n");
        json.append("  \"center_lat\": ").append(center_lat).append(",\n");
        json.append("  \"zoom\": ").append(zoom).append("\n");
        json.append("}");
        return json.toString();
    }

    public void setData(String contenu, LocalDate date, String imgPath, Double markerLong, Double marketLat, Double centerLong , Double centerLat, Double zoom){
        super.setData(contenu, date, imgPath);
        this.marker_lat = marketLat;
        this.marker_long = markerLong;
        this.center_lat = centerLat;
        this.center_long = centerLong;
        this.zoom = zoom;
    }
}
