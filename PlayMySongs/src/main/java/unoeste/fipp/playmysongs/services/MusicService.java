package unoeste.fipp.playmysongs.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Service;
import unoeste.fipp.playmysongs.entities.Music;
import unoeste.fipp.playmysongs.entities.Style;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MusicService {
    public List<Music> findMusicsByKeyWord(String keyword){
        List<Music> musicList = new ArrayList<>();
        musicList.add(new Music("Oceano","MPB","Djavan"));
        musicList.add(new Music("Águas de Março","MPB","Elis Regina"));
        return musicList;
    }

    public List<Style> findMusicStyles(){
        List<Style> styleList = new ArrayList<>();
        String connectionString = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase("my_musics");
        MongoCollection<Document> collection = database.getCollection("styles");
        MongoCursor<Document> mongoCursor = collection.find().sort(eq("nome",1L)).iterator();

        while(mongoCursor.hasNext()){
            styleList.add(new Gson().fromJson(mongoCursor.next().toJson(), Style.class));
            // String JSON -> Objeto = não tem um metodo no JDK que faça isso, por isso é usado o Gson
        }

        return styleList;
    }
}
