package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado();
		System.out.println("Numero de vertices: " + g.getNumeroVertices());
		System.out.println(g);

		Dijkstra dijkstra = new Dijkstra();
		Vertice origem = g.getVertice("A");
		Vertice chegada = g.getVertice("F");
		dijkstra.geraCaminho(origem);
		ArrayList<Vertice> caminho = dijkstra.getMenorCaminho(chegada);
		for (Vertice v:caminho) {
			System.out.printf("["+v.nome+"] ");
		}
	}

}
