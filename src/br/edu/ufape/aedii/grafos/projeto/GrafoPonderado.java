package br.edu.ufape.aedii.grafos.projeto;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GrafoPonderado {
	List<Vertice> vertices;
	List<Aresta> arestas;

	public GrafoPonderado() {
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
		carregarArquivo("C:/Users/beljo/Downloads/grafos/src/br/edu/ufape/aedii/grafos/projeto/grafo.txt");
	}

	public void carregarArquivo(String dir) {
		try (Scanner sc = new Scanner(new FileReader(dir))) {
			int qtd_vertices = sc.nextInt();
			int qtd_arestas = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < qtd_vertices; i++) {
				String nome = sc.nextLine();
				Vertice v = new Vertice(nome);
				vertices.add(v);
			}
			for (int i = 0; i < qtd_arestas; i++) {
				String linha = sc.nextLine();
				String partes[] = linha.split(",");

				String origem = partes[0];
				int peso = Integer.parseInt(partes[1]);
				String destino = partes[2];

				// talvez uma abordagem com um HashMap<String, Vertice> fosse mais eficiente não sei
				Vertice v_origem = this.getVertice(origem);
				Vertice v_destino = this.getVertice(destino);
				this.adicionarAresta(v_origem, peso, v_destino);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Vertice getVertice(String nome) {
		for(Vertice vertice: this.vertices) 
			if(vertice.nome.equals(nome))
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
