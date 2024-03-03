package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
	String nome;
    List<Aresta> adjacentes;
    Vertice anterior;
    int distancia_acumulada;
    boolean visitado;

    public Vertice(String nome) {
        this.nome = nome;
        this.adjacentes = new ArrayList<Aresta>();
        this.distancia_acumulada = Integer.MAX_VALUE;
        this.visitado = false;
        this.anterior = null; 
    }

    @Override
    public int compareTo(Vertice v1) {
        return Integer.compare(distancia_acumulada, v1.distancia_acumulada);
    }

    public void adicionarAdjacente(Aresta e) {
    	adjacentes.add(e);
    }
}
