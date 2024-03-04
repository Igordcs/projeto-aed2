package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrafoPonderado {
	List<Vertice> vertices;
	List<Aresta> arestas;

	public GrafoPonderado() {
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}

	public void construirGrafo(String nomeArquivo) {
		try (Scanner sc = new Scanner(getClass().getResourceAsStream(nomeArquivo))) {
			int qtdVertices = sc.nextInt();
			int qtdArestas = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < qtdVertices; i++) {
				String nome = sc.nextLine();
				Vertice v = new Vertice(nome);
				vertices.add(v);
			}
			for (int i = 0; i < qtdArestas; i++) {
				String linha = sc.nextLine();
				String partes[] = linha.split(",");

				String origem = partes[0];
				int peso = Integer.parseInt(partes[1]);
				String destino = partes[2];

				Vertice v1 = this.getVertice(origem);
				Vertice v2 = this.getVertice(destino);
				adicionarAresta(v1, peso, v2);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Vertice getVertice(String nome) {
		for(Vertice vertice: vertices) 
			if (vertice.nome.equals(nome))
				return vertice;
		return null;
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

	public int getNumeroVertices() {
		return vertices.size();
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
}
