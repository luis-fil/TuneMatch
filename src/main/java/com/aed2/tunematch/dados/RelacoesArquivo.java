package com.aed2.tunematch.dados;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RelacoesArquivo {
    private static final String arquivo = "grafo.dat";

        public static void salvarRelacoes(Map<String, Map<String, Double>> relacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(relacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Map<String, Double>> carregarRelacoes() {
        File file = new File(arquivo);
        if (!file.exists()) return new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Map<String, Map<String, Double>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
