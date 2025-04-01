package com.aed2.tunematch.negocio.cadastro;

import com.aed2.tunematch.dados.RelacoesArquivo;
import com.aed2.tunematch.negocio.basica.Musica;

import java.util.HashMap;
import java.util.Map;

public class RelacaoCadastro {
    private Map<String, Map<String, Double>> relacoes = RelacoesArquivo.carregarRelacoes();

    public void conectarMusicas(Musica musica1, Musica musica2, double peso) {
        if (peso > 0.7) {
            relacoes.putIfAbsent(musica1.getId(), new HashMap<>());
            relacoes.putIfAbsent(musica2.getId(), new HashMap<>());

            relacoes.get(musica1.getId()).put(musica2.getId(), peso);
            relacoes.get(musica2.getId()).put(musica1.getId(), peso);

            RelacoesArquivo.salvarRelacoes(relacoes);
        }
    }

    public Map<String, Double> buscarRelacoes(String idMusica) {
        return relacoes.getOrDefault(idMusica, new HashMap<>());
    }
}

