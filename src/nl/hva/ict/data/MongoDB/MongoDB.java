package nl.hva.ict.data.MongoDB;

import com.mongodb.client.MongoCollection;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import nl.hva.ict.MainApplication;
import nl.hva.ict.data.Data;
import org.bson.Document;

public abstract class MongoDB implements Data {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    protected MongoCollection<Document> collection;


    public MongoDB() {
        connect();
    }

    // connect database
    private void connect() {
        if (this.mongoClient == null) {
            try {
                this.mongoClient = MongoClients.create(MainApplication.getNosqlHost());
                this.mongoDatabase = mongoClient.getDatabase(MainApplication.getNosqlDatabase());
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

    }


    public void selectedCollection(String collection){
        this.collection = mongoDatabase.getCollection(collection);
    }




}
