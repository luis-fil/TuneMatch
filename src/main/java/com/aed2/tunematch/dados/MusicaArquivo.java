package com.aed2.tunematch.dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aed2.tunematch.negocio.basica.Musica;

public class MusicaArquivo {
    private static final String arquivo = "musicas.csv";
    private static final String delimitador = ";";

    //Cria uma base de dados das músicas formatadas e isoladas
    public static void salvarMusicas(List<Musica> musicas){
        try(PrintWriter pWriter = new PrintWriter(new FileWriter(arquivo))){
            pWriter.println("id;titulo;genero;popularidade;dancebility;energy;artistas");
            
            for(Musica musica: musicas){
                String linha = String.format("%s;%s;%s;%d;%.2f;%.2f;%s", 
                musica.getId(),
                musica.getTitulo(),
                musica.getGenero(),
                musica.getPopularidade(),
                musica.getDancebility(),
                musica.getEnergy(),
                String.join(",", musica.getArtistas()));
                pWriter.println(linha);
            }
        } catch(IOException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static List<Musica> carregarMusicas(){
        List<Musica> musicas = new ArrayList<>();
        File file = new File(arquivo);

        if (!file.exists()) return musicas;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                
                String[] partes = linha.split(delimitador);
                if (partes.length < 7) continue;
                
                String id = partes[0];
                String titulo = partes[1];
                String genero = partes[2];
                int popularidade = Integer.parseInt(partes[3]);
                float dancebility = Float.parseFloat(partes[4]);
                float energy = Float.parseFloat(partes[5]);
                List<String> artistas = Arrays.asList(partes[6].split(","));

                Musica musica = new Musica(id, titulo, artistas, genero, popularidade, dancebility, energy);
                musicas.add(musica);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return musicas;
    }   
}
