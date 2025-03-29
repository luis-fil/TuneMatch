package com.aed2.tunematch.negocio.basica;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;

@Entity
public class Musica {
    @Id
    private String id;
    private String titulo;
    private String genero;
    private float dancebility;
    private float energy;
    private boolean explicit;
    @Relationship(type = "INTERPRETADA_POR", direction = Relationship.Direction.OUTGOING)
    private List<Artista> artistas;

    public Musica(String id, String titulo, List<Artista> artista, String genero, float dancebility, float energy, boolean explicit){
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.dancebility = dancebility;
        this.energy = energy;
        this.explicit = explicit;
    }

    public getId(){
        return id;
    }
    public getTitulo(){
        return titulo;
    }
    public getArtista(){
        return artista;
    }
    public getGenero(){
        return genero;
    }
    public getExplicit(){
        return explicit;
    }
    public getDancebility(){
        return dancebility;
    }
    public getEnergy(){
        return energy;
    }
}
