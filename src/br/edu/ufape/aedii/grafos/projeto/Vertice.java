package br.edu.ufape.aedii.grafos.projeto;

public class Vertice implements Comparable<Vertice> {
	String nome;
	Vertice anterior;
	int distanciaAcumulada;
	boolean visitado;

	public Vertice(String nome) {
		this.nome = nome;
		this.distanciaAcumulada = Integer.MAX_VALUE;
		this.visitado = false;
		this.anterior = null;
	}

	@Override
	public int compareTo(Vertice v1) {
		return Integer.compare(distanciaAcumulada, v1.distanciaAcumulada);
	}

	@Override
	public String toString() {
		return nome;
	}
}
