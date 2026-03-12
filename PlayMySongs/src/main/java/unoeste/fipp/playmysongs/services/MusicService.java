package unoeste.fipp.playmysongs.services;

import org.springframework.stereotype.Service;
import unoeste.fipp.playmysongs.entities.Music;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {
    public List<Music> findMusicsByKeyword(String keyword){
        List<Music> musicList = new ArrayList<>();
        musicList.add(new Music("Baby","mpb","Rita Lee"));
        musicList.add(new Music("Malandragem","mpb","Cássia Eller"));

        return musicList;
    }
}
