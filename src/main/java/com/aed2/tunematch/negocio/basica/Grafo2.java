package com.aed2.tunematch.negocio.basica;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;

public class Grafo2 {
    private Map<String, Musica> musicas;
    private Map<String, Map<String, Double>> listaAdjacencia;

    public Grafo2(String caminhoCSV) {
        musicas = new HashMap<>();
        listaAdjacencia = new HashMap<>();
        carregarGrafo(caminhoCSV);
    }

    private void carregarGrafo(String caminhoCSV) {
        try (Reader leitor = new FileReader(caminhoCSV);
             CSVParser csvParser = new CSVParser(leitor, CSVFormat.DEFAULT.withQuote('"'))) {
            
            Iterator<CSVRecord> iterator = csvParser.iterator();
            if (!iterator.hasNext()) return;
            
            CSVRecord primeiraLinha = iterator.next();
            int numMusicas = Integer.parseInt(primeiraLinha.get(0));
            int numArestas = Integer.parseInt(primeiraLinha.get(1));

            for (int i = 0; i < numMusicas && iterator.hasNext(); i++) {
                CSVRecord registro = iterator.next();
                String id = registro.get(0);
                String titulo = registro.get(1);
                List<String> artistas = Arrays.asList(registro.get(2).split(";"));
                String genero = registro.get(3);
                int popularidade = Integer.parseInt(registro.get(4));
                double danceability = Double.parseDouble(registro.get(5));
                double energy = Double.parseDouble(registro.get(6));
                double valence = Double.parseDouble(registro.get(7));

                Musica musica = new Musica(id, titulo, artistas, genero, popularidade, danceability, energy, valence);
                musicas.put(id, musica);
                listaAdjacencia.put(id, new HashMap<>());
            }

            for (int i = 0; i < numArestas && iterator.hasNext(); i++) {
                CSVRecord registro = iterator.next();
                String idMusica1 = registro.get(0);
                String idMusica2 = registro.get(1);
                double peso = Double.parseDouble(registro.get(2));

                listaAdjacencia.get(idMusica1).put(idMusica2, peso);
                listaAdjacencia.get(idMusica2).put(idMusica1, peso);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Map<String, Double>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public Map<String, Musica> getMusicas() {
        return musicas;
    }
}
