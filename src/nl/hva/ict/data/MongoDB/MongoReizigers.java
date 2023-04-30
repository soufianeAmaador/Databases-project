package nl.hva.ict.data.MongoDB;

import com.mongodb.client.MongoCursor;
import nl.hva.ict.models.Reiziger;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

public class MongoReizigers extends MongoDB {

    private List<Reiziger> reizigers;

    public MongoReizigers() {
        this.reizigers = new ArrayList<>();
        load();
    }

    public void load(){
        this.selectedCollection("reizigers");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {while (cursor.hasNext()) {
            Document tempReiziger = cursor.next();
            String reizigerCode = (String)tempReiziger.get("Reizigercode");
            String voornaam = (String)tempReiziger.get("Voornaam");
            String achternaam = (String)tempReiziger.get("Achternaam");
            String adres = (String)tempReiziger.get("Adres");
            String postcode = (String)tempReiziger.get("Postcode");
            String plaats = (String)tempReiziger.get("Plaats");
            String land = (String)tempReiziger.get("Land");
            String hoofdreiziger = (String)tempReiziger.get("Hoofdreiziger");
            Reiziger reiziger = new Reiziger(reizigerCode, voornaam, achternaam, adres, postcode, plaats, land, hoofdreiziger);
            reizigers.add(reiziger);

        }
        } finally {
            cursor.close();
        }
    }

    @Override
    public List getAll() {
        return reizigers;
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void remove(Object object) {

    }
}
