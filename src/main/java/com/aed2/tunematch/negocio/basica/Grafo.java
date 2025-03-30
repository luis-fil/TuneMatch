package com.aed2.tunematch.negocio.basica;

import java.util.*;

public class Grafo {
    private Map<String, Musica> musicas = new HashMap<>();
    private Map<String, Map<String, Double>> adjacentes = new HashMap<>();

    public void inserirMusica(Musica musica){
        musicas.putIfAbsent(musica.getId(), musica);
        //Cria um mapa dentro mapa que com as músicas e os pesos de cada uma
        adjacentes.putIfAbsent(musica.getId(), new HashMap<String, Double>());
    }

    public void conectarMusicas(Musica musica1, Musica musica2, double peso){
        if(musicas.containsKey(musica1.getId()) && musicas.containsKey(musica2.getId())){
            if(peso > 0.7){
                adjacentes.get(musica1.getId()).put(musica2.getId(), peso);
                adjacentes.get(musica2.getId()).put(musica1.getId(), peso);
            }
        }
    }

    public List<Musica> recomendarMusicas(Musica musica){
        if(!musicas.containsKey(musica.getId())) return Collections.emptyList();

        
    }
}