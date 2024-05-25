package carnet.outils;

import carnet.exceptions.LoadNotWork;
import carnet.exceptions.SaveNotWork;
import carnet.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonManager {
    private GestionnairePage gPage;

    public JsonManager(GestionnairePage gPage) {
        this.gPage = gPage;
    }

    public void save(String path) throws SaveNotWork {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        for (Page page : gPage) {
            if (page.estUnePageContenu()) {
                json.append(page.toJson());
                json.append(",\n");
            }
        }
        if (json.length() > 2)
            json.deleteCharAt(json.length() - 2);
        json.append("]");

        try {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toString());
            writer.close();
        } catch (Exception e) {
            throw new SaveNotWork("Problème sur la sauvegarde du carnet");
        }
    }

    public void load(String path) throws LoadNotWork {
        try {
            String cotent = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(cotent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                switch (jsonObject.getString("type")) {
                    case "accueil":
                        ajouterPageAccueil(jsonObject);
                        break;
                    case "textPhoto":
                        ajouterPageTextPhoto(jsonObject);
                        break;
                    case "textPhotoMap":
                        ajouterPageTextPhotoMap(jsonObject);
                        break;
                }
            }
        } catch (IOException e) {
            throw new LoadNotWork("Problème sur le chargement du carnet");
        }
    }

    private void ajouterPageAccueil(JSONObject jsonObject) throws LoadNotWork{
        String titre, auteur, strDateDebut, stdDateFin;
        String[] participants;
        LocalDate dateDebut, dateFin;
        try {
            titre = jsonObject.getString("titre");
            auteur = jsonObject.getString("auteur");
            strDateDebut = jsonObject.getString("dateDebut");
            stdDateFin = jsonObject.getString("dateFin");

            dateDebut = strDateDebut.equals("null") ? null : LocalDate.parse(strDateDebut);
            dateFin = stdDateFin.equals("null") ? null : LocalDate.parse(stdDateFin);

            participants = jsonObject.getJSONArray("participants").toList().toArray(new String[0]);
        } catch (Exception e) {
            throw new LoadNotWork("Problème sur le chargement d'une page accueil");
        }
        gPage.ajouterPage(new PageAccueil(titre, auteur, dateDebut, dateFin, participants));
    }

    private void ajouterPageTextPhoto(JSONObject jsonObject) throws LoadNotWork{
        String imgPath, contenu, strDate;
        LocalDate date;
        try {
            imgPath = jsonObject.getString("imgPath");
            contenu = jsonObject.getString("contenu");
            strDate = jsonObject.getString("date");
            date = strDate.equals("null") ? null : LocalDate.parse(strDate);
        } catch (Exception e) {
            throw new LoadNotWork("Problème sur le chargement d'une page textPhoto");
        }
        gPage.ajouterPage(new PageTextPhoto(date, contenu, imgPath));
    }

    private void ajouterPageTextPhotoMap(JSONObject jsonObject) throws LoadNotWork{
        String imgPath, contenu, strDate;
        LocalDate date;
        Double marker_long, marker_lat, center_long, center_lat, zoom;
        try {
            imgPath = jsonObject.getString("imgPath");
            contenu = jsonObject.getString("contenu");
            strDate = jsonObject.getString("date");

            marker_long = jsonObject.getDouble("marker_long");
            marker_lat = jsonObject.getDouble("marker_lat");

            center_long = jsonObject.getDouble("center_long");
            center_lat = jsonObject.getDouble("center_lat");
            zoom = jsonObject.getDouble("zoom");
            date = strDate.equals("null") ? null : LocalDate.parse(strDate);
        }catch (Exception e){
            throw new LoadNotWork("Problème sur le chargement d'une page textPhotoMap");
        }
        gPage.ajouterPage(new PageTextPhotoMap(contenu, imgPath, date, marker_long, marker_lat, center_long, center_lat, zoom));
    }
}