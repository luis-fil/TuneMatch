package com.aed2.tunematch.negocio.basica;

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
    private float dancebility;
    private float energy;
    private List<String> artistas;

    public Musica(String id, String titulo, List<String> artistas, String genero, int popularidade, float dancebility, float energy, boolean explicit){
        this.id = id;
        this.titulo = titulo;
        this.artistas = artistas;
        this.genero = genero;
        this.popularidade = popularidade;
        this.dancebility = dancebility;
        this.energy = energy;
    }

    public String getId(){
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public List<String>getArtista(){
        return artistas;
    }
    public String getGenero(){
        return genero;
    }
    public int getPopularidade(){
        return popularidade;
    }
    public float getDancebility(){
        return dancebility;
    }
    public float getEnergy(){
        return energy;
    }
}
