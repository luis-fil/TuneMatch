package com.aed2.tunematch.negocio.cadastro;

import com.aed2.tunematch.negocio.basica.Grafo;
import com.aed2.tunematch.negocio.basica.Musica;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MusicaService {
    private Map<String, Musica> musicas;

    public MusicaService(Grafo grafo) {
        this.musicas = grafo.getMusicas();
    }

    public List<Musica> buscarSugestoes(String termo) {
        if (termo == null || termo.isEmpty()) {
            return List.of();
        }

        return musicas.values().stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(termo.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }
}