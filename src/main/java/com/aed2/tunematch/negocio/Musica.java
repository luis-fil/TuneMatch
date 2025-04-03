package com.aed2.tunematch.negocio;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import java.util.List;

@Entity
public class Musica {
    @Id
    private String id;
    private String titulo;
    private String genero;
    private int popularidade;
    private double danceability;
    private double energy;
    private double valence;
    private List<String> artistas;

    public Musica(String id, String titulo, List<String> artistas, String genero, int popularidade, double danceability, double energy, double valence) {
        this.id = id;
        this.titulo = titulo;
        this.artistas = artistas;
        this.genero = genero;
        this.popularidade = popularidade;
        this.danceability = danceability;
        this.energy = energy;
        this.valence = valence;
    }

    public Musica() {}

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<String> getArtistas() { return artistas; }
    public String getGenero() { return genero; }
    public int getPopularidade() { return popularidade; }
    public double getDanceability() { return danceability; }
    public double getEnergy() { return energy; }
    public double getValence() { return valence; }

    @Override
    public String toString() {
        return "Musica{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artistas=" + artistas +
                ", genero='" + genero + '\'' +
                ", popularidade=" + popularidade +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", valence=" + valence +
                '}';
    }
}
