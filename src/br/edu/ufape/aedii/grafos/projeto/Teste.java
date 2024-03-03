package br.edu.ufape.aedii.grafos.projeto;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado();

		Vertice a = g.adicionarVertice("a");
		Vertice b = g.adicionarVertice("b");
		Vertice c = g.adicionarVertice("c");
		Vertice d = g.adicionarVertice("d");

		g.adicionarAresta(a, 1, b);
		g.adicionarAresta(a, 2, c);
		g.adicionarAresta(b, 3, c);
		g.adicionarAresta(a, 2, d);

		System.out.println("Numero de vertices: " + g.getNumeroVertices());
		System.out.println(g);
	}

}
