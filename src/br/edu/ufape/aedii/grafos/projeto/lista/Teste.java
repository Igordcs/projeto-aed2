package br.edu.ufape.aedii.grafos.projeto.lista;

import java.util.List;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado();
		g.construirGrafo("grafo.txt");
		System.out.println("Numero de vertices: " + g.getNumeroVertices());
		System.out.println(g);

		Dijkstra dijkstra = new Dijkstra(719.99, 3200.00, 10);
		Vertice origem = g.getVertice("v1");
		Vertice chegada = g.getVertice("v12");
		List<Vertice> caminho = dijkstra.getMenorCaminho(origem, chegada);
		dijkstra.imprimirDados(caminho);
	}

}