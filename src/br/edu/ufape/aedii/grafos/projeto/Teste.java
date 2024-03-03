package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado();

		Vertice a = g.adicionarVertice("a");
		Vertice b = g.adicionarVertice("b");
		Vertice c = g.adicionarVertice("c");
		Vertice d = g.adicionarVertice("d");
		Vertice e = g.adicionarVertice("e");
		Vertice f = g.adicionarVertice("f");

		g.adicionarAresta(a, 4, b);
		g.adicionarAresta(a, 2, c);
		g.adicionarAresta(b, 1, c);
		g.adicionarAresta(b, 5, d);
		g.adicionarAresta(c, 8, d);
		g.adicionarAresta(c, 10, e);
		g.adicionarAresta(d, 2, e);
		g.adicionarAresta(d, 6, f);
		g.adicionarAresta(e, 2, f);

		System.out.println("Numero de vertices: " + g.getNumeroVertices());
		System.out.println(g);

		Dijkstra dijkstra = new Dijkstra();
		dijkstra.geraCaminho(a);
		ArrayList<Vertice> caminho = dijkstra.getMenorCaminho(f);
		for (Vertice v:caminho) {
			System.out.printf("["+v.nome+"] ");
		}
	}

}
