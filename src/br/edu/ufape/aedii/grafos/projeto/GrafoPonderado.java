package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;
import java.util.List;

public class GrafoPonderado {
	List<Vertice> vertices;
	List<Aresta> arestas;

	public GrafoPonderado() {
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}

	public Vertice adicionarVertice(String nome) {
		Vertice v = new Vertice(nome);
		vertices.add(v);
		return v;
	}
	
	public void adicionarAresta(Vertice v1, int peso, Vertice v2) {
		Aresta e = new Aresta(v1, peso, v2);
		v1.adicionarAdjacente(e);
		v2.adicionarAdjacente(e);
		arestas.add(e);
	}

	public String toString() {
		String str = "";
		for (Vertice u : vertices) {
			str += u.nome + ": ";
			for (Aresta e : u.adjacentes) {
				Vertice v1 = e.origem;
				Vertice v2 = e.destino;
				if(u.nome.equals(v1.nome))
					str += "["+v2.nome+"]";
				else
					str += "["+v1.nome+"]";
			}
			str += "\n";
		}
		return str;
	}

	public int getNumeroVertices() {
		return vertices.size();
	}

}
