package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.List;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado("municipiospe1.txt");
		System.out.println("Numero de vertices: " + g.getNumeroVertices());
	//	System.out.println(g);

		Dijkstra dijkstra = new Dijkstra(719.99, 3200.00, 10);
		Vertice origem = g.getVertice("Buíque");
		Vertice chegada = g.getVertice("Garanhuns");
		List<Vertice> caminho = dijkstra.getMenorCaminho(g, origem, chegada);
		dijkstra.imprimirDados(caminho, 719.99);
		
	}

}