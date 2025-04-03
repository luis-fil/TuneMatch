package com.aed2.tunematch.negocio;

import java.util.List;
import java.util.stream.Collectors;

public class Fachada {
    private static Fachada instancia;
    private Grafo grafo;
    private MusicaCadastro musicaCadastro;

    private Fachada(String caminhoCSV) {
        this.grafo = new Grafo(caminhoCSV);
        this.musicaCadastro = new MusicaCadastro(grafo.getMusicas());
    }

    // Implementação Singleton para garantir apenas uma instância
    public static Fachada getInstancia(String caminhoCSV) {
        if (instancia == null) {
            instancia = new Fachada(caminhoCSV);
        }
        return instancia;
    }

    public List<Musica> recomendarMusicas(List<String> idsMusicas) {
        List<Musica> musicasBase = idsMusicas.stream()
                .map(musicaCadastro::buscarPorId)
                .filter(musica -> musica != null)
                .collect(Collectors.toList());

        if (musicasBase.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma música válida foi encontrada.");
        }
        return grafo.recomendarMusicas(musicasBase);
    }

    public List<Musica> listarMusicas() {
        return musicaCadastro.listarMusicas();
    }

    public Musica buscarMusicaPorId(String idMusica) {
        return musicaCadastro.buscarPorId(idMusica);
    }

    public List<Musica> buscarMusicasPorTitulo(String termo){
            return musicaCadastro.buscarSugestoes(termo);
    }

    public List<Musica> buscarMusicasPorGenero(String genero) {
        return grafo.buscarPorGenero(genero);
    }

    
}
