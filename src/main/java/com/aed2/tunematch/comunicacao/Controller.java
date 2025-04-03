package com.aed2.tunematch.comunicacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import com.aed2.tunematch.negocio.Fachada;
import com.aed2.tunematch.negocio.Musica;

@RestController
@RequestMapping("/musicas")
public class Controller {
    private final Fachada fachada;

    public Controller() {
        this.fachada = Fachada.getInstancia("dados/grafo.csv");
    }

    @GetMapping
    public List<Musica> listarMusicas() {
        return fachada.listarMusicas();
    }

    @GetMapping("/{id}")
    public Musica buscarMusicaPorId(@PathVariable String id) {
        return fachada.buscarMusicaPorId(id);
    }

    @GetMapping("/genero/{genero}")
    public List<Musica> buscarMusicasPorGenero(@PathVariable String genero) {
        return fachada.buscarMusicasPorGenero(genero);
    }

    @PostMapping("/recomendar")
    public List<Musica> recomendarMusicas(@RequestBody List<String> idsMusicas) {
        return fachada.recomendarMusicas(idsMusicas);
    }

    @GetMapping("/pesquisa/{termo}")
    public List<Musica> buscarMusicasPorTitulo(@PathVariable String termo) {
        if (termo == null || termo.trim().isEmpty()) return Collections.emptyList();
        return fachada.buscarMusicasPorTitulo(termo);
    }  
}
