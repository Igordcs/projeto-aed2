package br.edu.ufape.aedii.grafos.projeto.matriz;

public class Vertice implements Comparable<Vertice> {
	public String nome;
	Vertice anterior;
	double distanciaAcumulada;
	boolean visitado;

	public Vertice(String nome) {
		this.nome = nome;
		this.distanciaAcumulada = Double.POSITIVE_INFINITY;
		this.visitado = false;
		this.anterior = null;
	}

	@Override
	public int compareTo(Vertice v1) {
		return Double.compare(distanciaAcumulada, v1.distanciaAcumulada);
	}

	@Override
	public String toString() {
		return nome;
	}
}