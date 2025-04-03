package com.aed2.tunematch.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MusicaCadastro {
    private Map<String, Musica> musicas;

    public MusicaCadastro(Map<String, Musica> musicas){
        this.musicas = musicas;
    }

    public Musica buscarPorId(String id) {
        return musicas.values().stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Musica> listarMusicas(){
        return new ArrayList<Musica>(musicas.values());
    }

    public List<Musica> buscarSugestoes(String termo) {
        if (termo == null || termo.isEmpty()) {
            return List.of();
        }
        termo.trim().toLowerCase();
        return musicas.values().stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(termo.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
