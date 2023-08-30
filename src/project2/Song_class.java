/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2;

/**
 *
 * @author yarenk
 */
public class Song_class {
     private String id;
    private String songName;
    private String Artist;
    private String Genre;
    private String Year;

    public Song_class(String songName, String Artist,String id, String Genre, String Year) {
        this.id = id;
        this.songName = songName;
        this.Artist = Artist;
        this.Genre = Genre;
        this.Year = Year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String Artist) {
        this.Artist = Artist;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }
     @Override
    public String toString() {
        String rst =  songName + ";";
        rst +=  Artist + ";";
        rst +=  id + ";";
        rst +=  Genre + ";";
        rst +=  Year + ";";
        return rst;
    }


}
