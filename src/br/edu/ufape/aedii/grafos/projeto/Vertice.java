package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	String nome;
    List<Aresta> adjacentes;

    public Vertice(String nome) {
        this.nome = nome;
        this.adjacentes = new ArrayList<Aresta>();
    }

    public void adicionarAdjacente(Aresta e) {
    	adjacentes.add(e);
    }
}
