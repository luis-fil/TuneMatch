package com.aed2.tunematch.dados;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;
import com.aed2.tunematch.negocio.basica.Musica;

public class PreProcessadorGrafo {
    private static final double LIMIAR = 0.685;

    public static void processarMusicas(String caminhoCSV, String caminhoSaida, int maxGeneros, int maxMusicasPorGenero) {
        Map<String, Integer> contadorGeneros = new LinkedHashMap<>();
        Set<String> titulosArtistasUnicos = new HashSet<>();
        List<Musica> musicas = new ArrayList<>();
        
        try (Reader leitor = new FileReader(caminhoCSV);
             CSVParser csvParser = new CSVParser(leitor, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreSurroundingSpaces()
                .withTrim()
                .withIgnoreHeaderCase())) {

            for (CSVRecord registro : csvParser) {
                String id = registro.get("track_id");
                String titulo = registro.get("track_name");
                String genero = registro.get("track_genre");
                List<String> artistas = Arrays.asList(registro.get("artists").split(";"));
                int popularidade = Integer.parseInt(registro.get("popularity"));
                double danceability = Double.parseDouble(registro.get("danceability"));
                double energy = Double.parseDouble(registro.get("energy"));
                double valence = Double.parseDouble(registro.get("valence"));
                
                String chaveUnica = titulo + "_" + String.join("_", artistas);
                if (titulosArtistasUnicos.contains(chaveUnica)) continue;

                contadorGeneros.putIfAbsent(genero, 0);
                if (contadorGeneros.get(genero) < maxMusicasPorGenero && contadorGeneros.size() <= maxGeneros) {
                    Musica musica = new Musica(id, titulo, artistas, genero, popularidade, danceability, energy, valence);
                    musicas.add(musica);
                    contadorGeneros.put(genero, contadorGeneros.get(genero) + 1);
                    titulosArtistasUnicos.add(chaveUnica);
                }

                if (contadorGeneros.size() == maxGeneros && contadorGeneros.get(genero) == maxMusicasPorGenero) {
                    break;
                }
            }

            salvarGrafoEmArquivo(musicas, caminhoSaida);
            System.out.println("Músicas e arestas geradas e salvas em " + caminhoSaida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarGrafoEmArquivo(List<Musica> musicas, String caminhoSaida) {
        List<String> arestas = new ArrayList<>();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoSaida))) {
            for (int i = 0; i < musicas.size(); i++) {
                for (int j = i + 1; j < musicas.size(); j++) {
                    Musica m1 = musicas.get(i);
                    Musica m2 = musicas.get(j);
                    double peso = calcularPeso(m1, m2);
                    
                    if (peso >= LIMIAR) {
                        arestas.add(m1.getId() + "," + m2.getId() + "," + peso);
                    }
                }
            }
            
            // Escrevendo a quantidade de músicas e arestas na primeira linha
            writer.write(musicas.size() + "," + arestas.size());
            writer.newLine();
            
            // Escrevendo os dados das músicas, formatando corretamente
            for (Musica musica : musicas) {
                writer.write(String.format("\"%s\",\"%s\",\"%s\",%s,%d,%.2f,%.2f,%.2f",
                        musica.getId(),
                        musica.getTitulo().replace("\"", "\"\""), // Escapando aspas
                        String.join(";", musica.getArtistas()).replace("\"", "\"\""), // Escapando aspas
                        musica.getGenero(),
                        musica.getPopularidade(),
                        musica.getDanceability(),
                        musica.getEnergy(),
                        musica.getValence()));
                writer.newLine();
            }
            
            // Escrevendo as arestas
            for (String aresta : arestas) {
                writer.write(aresta);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calcularPeso(Musica m1, Musica m2) {
        double peso = 0.0;

        if (m1.getGenero().equals(m2.getGenero())) {
            peso += 0.25;
        }

        peso += (1.0 - Math.abs(m1.getValence() - m2.getValence())) * 0.25;
        peso += (1.0 - Math.abs(m1.getDanceability() - m2.getDanceability())) * 0.2;
        peso += (1.0 - Math.abs(m1.getEnergy() - m2.getEnergy())) * 0.2;
        peso += (1.0 - Math.abs(m1.getPopularidade() - m2.getPopularidade()) / 100.0) * 0.1;

        return Math.max(0.0, peso);
    }
}
