package com.aed2.tunematch.negocio.cadastro;

import com.aed2.tunematch.dados.MusicaArquivo;
import com.aed2.tunematch.negocio.basica.Musica;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {
    private List<Musica> musicas;

    public MusicaService() {
        this.musicas = MusicaArquivo.carregarMusicas();
    }

    public List<Musica> buscarSugestoes(String termo) {
        if (termo == null || termo.isEmpty()) {
            return List.of();
        }

        return musicas.stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(termo.toLowerCase()))
                .limit(5)
                .collect(Collectors.toList());
    }
}