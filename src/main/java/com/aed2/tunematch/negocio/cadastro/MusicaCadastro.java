package com.aed2.tunematch.negocio.cadastro;

import java.util.List;

import com.aed2.tunematch.dados.MusicaArquivo;
import com.aed2.tunematch.negocio.basica.Musica;

public class MusicaCadastro {
    private List<Musica> musicas = MusicaArquivo.carregarMusicas();

    public Musica buscarPorId(String id) {
        return musicas.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Musica> listarMusicas(){
        return musicas;
    }
}
