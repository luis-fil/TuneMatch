package com.aed2.tunematch.negocio.basica;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;

@Entity
public class Artista {
    @Id
    private String id;
    private String nome;
    private List<Artista> artistas_relacionados;

    
}
