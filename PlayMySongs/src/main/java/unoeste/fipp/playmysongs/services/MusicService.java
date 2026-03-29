package unoeste.fipp.playmysongs.services;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.playmysongs.entities.Music;
import unoeste.fipp.playmysongs.entities.Style;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MusicService {

    public List<Style> findMusicStyles(){
        List<Style> styleList = new ArrayList<>();
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("my_musics");
            MongoCollection<Document> collection = database.getCollection("styles");
            MongoCursor<Document> mongoCursor = collection.find().sort(eq("nome",1L)).iterator();

            while(mongoCursor.hasNext())
                styleList.add(new Gson().fromJson(mongoCursor.next().toJson(), Style.class));
        }
        return styleList;
    }

    public List<Music> findMusicsByKeyWord(String keyword){
        List<Music> musicList = new ArrayList<>();
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("my_musics");
            MongoCollection<Document> collection = database.getCollection("musicas");

            Bson filtroBusca = Filters.or(
                    Filters.regex("titulo", ".*" + keyword + ".*", "i"),
                    Filters.regex("estilo", ".*" + keyword + ".*", "i"),
                    Filters.regex("artista", ".*" + keyword + ".*", "i")
            );

            MongoCursor<Document> cursor = collection.find(filtroBusca).iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();

                Music musicaEncontrada = new Music(
                        doc.getString("titulo"),
                        doc.getString("estilo"),
                        doc.getString("artista")
                );

                musicaEncontrada.setMusicFileName(doc.getString("musicFileName"));

                musicList.add(musicaEncontrada);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar no banco: " + e.getMessage());
        }

        return musicList;
    }

    public Music salvarMusica(MultipartFile file, String titulo, String estilo, String artista) throws Exception {

        String tituloFmt = formatarString(titulo);
        String estiloFmt = formatarString(estilo);
        String artistaFmt = formatarString(artista);

        String originalName = file.getOriginalFilename();
        String extensao = "";

        if (originalName != null && originalName.contains("."))
            extensao = originalName.substring(originalName.lastIndexOf("."));

        String novoNomeArquivo = tituloFmt + "_" + estiloFmt + "_" + artistaFmt + extensao;

        String UPLOAD_FOLDER = "src/main/resources/static/uploads/";
        File uploadFolder = new File(UPLOAD_FOLDER);

        if (!uploadFolder.exists())
            uploadFolder.mkdirs();

        file.transferTo(new File(uploadFolder.getAbsolutePath() + "/" + novoNomeArquivo));

        Music novaMusica = new Music(titulo, estilo, artista);
        novaMusica.setMusicFileName(novoNomeArquivo);

        String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("my_musics");
            MongoCollection<Document> collection = database.getCollection("musicas"); // Nome da coleção

            Document doc = new Document("titulo", novaMusica.getTitulo())
                    .append("estilo", novaMusica.getEstilo())
                    .append("artista", novaMusica.getArtista())
                    .append("musicFileName", novaMusica.getMusicFileName());

            collection.insertOne(doc);
        }

        return novaMusica;
    }

    private String formatarString(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);

        return normalizado
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase();
    }
}