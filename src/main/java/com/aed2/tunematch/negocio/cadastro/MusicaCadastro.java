package com.aed2.tunematch.negocio.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aed2.tunematch.negocio.basica.Musica;

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
}
