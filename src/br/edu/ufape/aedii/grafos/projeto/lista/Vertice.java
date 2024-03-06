package br.edu.ufape.aedii.grafos.projeto.lista;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
	String nome;
	List<Aresta> adjacentes;
	Vertice anterior;
	double distanciaAcumulada;
	boolean visitado;

	public Vertice(String nome) {
		this.nome = nome;
		this.adjacentes = new ArrayList<Aresta>();
		this.distanciaAcumulada = Double.POSITIVE_INFINITY;
		this.visitado = false;
		this.anterior = null;
	}

	@Override
	public int compareTo(Vertice v1) {
		return Double.compare(distanciaAcumulada, v1.distanciaAcumulada);
	}

		public void adicionarAdjacente(Aresta e) {
		adjacentes.add(e);
	}

	@Override
	public String toString() {
		return nome;
	}

}