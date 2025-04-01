package com.aed2.tunematch.negocio.basica;

import java.util.*;

public class Grafo {
    //Lista de Vertices
    private Map<String, Musica> musicas;
    //Lista de adjacencias (arestas nao direcionadas)
    private Map<String, Map<Musica, Double>> adjacentes = new HashMap<>();

    public Grafo(){
        musicas = new HashMap<>();
    }

    public Grafo(List<Musica> musicas){

    }
    
    public void inserirMusica(Musica musica){
        //Inserir cria um vértice e cria um array de vértices, como se fosse uma lista de adjacencia
        musicas.putIfAbsent(musica.getId(), musica);
        adjacentes.putIfAbsent(musica.getId(), new HashMap<Musica, Double>());
    }

    public void conectarMusicas(Musica musica1, Musica musica2, double peso){
        if(musicas.containsKey(musica1.getId()) && musicas.containsKey(musica2.getId())){
            if(peso > 0.7){
                adjacentes.get(musica1.getId()).put(musica2, peso);
                adjacentes.get(musica2.getId()).put(musica1, peso);
            }
        }
    }

    //A recomendação das músicas deve pegar a lista de adjacência e retornar as mais próximas com maior peso
    public List<Musica> recomendarMusicas(Musica musica){
        if(!adjacentes.containsKey(musica.getId())) return Collections.emptyList();
        PriorityQueue<Map.Entry<Musica, Double>> fila_de_prioridade = new PriorityQueue<>(
            (a,b) -> Double.compare(b.getValue(), a.getValue())
        );

        Set<Musica> visitados = new HashSet<>();
        visitados.add(musica);

        //Todas as músicas diretamente relaconadas
        for(Map.Entry<Musica, Double> entrada : adjacentes.get(musica.getId()).entrySet()){
            fila_de_prioridade.offer(entrada);
        }

        List<Musica> recomendacoes = new ArrayList<>();
        while(!fila_de_prioridade.isEmpty() && recomendacoes.size() < 30){
            // Recupera a música e insere em recomendacoes e em visitados
            Map.Entry<Musica, Double> maisRelacionado  = fila_de_prioridade.poll();
            recomendacoes.add(maisRelacionado.getKey());
            visitados.add(maisRelacionado.getKey());
            
            // Explora os vizinhos das músicas relacionadas
            for (Map.Entry<Musica, Double> entrada: adjacentes.get(maisRelacionado.getKey().getId()).entrySet()) {
                if (!visitados.contains(entrada.getKey())) {
                    fila_de_prioridade.offer(entrada);
                }
            }
        }
        return recomendacoes;
    }
}