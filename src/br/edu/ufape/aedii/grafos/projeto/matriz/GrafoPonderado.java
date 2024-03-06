package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrafoPonderado {
	List<Vertice> vertices;
	private double[][] matriz;

	public GrafoPonderado(String nomeArquivo) {
		vertices = new ArrayList<Vertice>();
		construirGrafo(nomeArquivo);
	}

	public void construirGrafo(String nomeArquivo) {
		try (Scanner sc = new Scanner(getClass().getResourceAsStream(nomeArquivo))) {
			int qtdVertices = sc.nextInt();
			int qtdArestas = sc.nextInt();
			this.matriz = new double[qtdVertices][qtdVertices];
			for (int i = 0; i < qtdVertices; i++) {
				this.matriz[i][i] = 0;
			}
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
				double peso = Double.parseDouble(partes[1]);
				String destino = partes[2];
				adicionarAresta(origem, peso, destino);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Vertice getVertice(String nome) {
		for (Vertice vertice : vertices)
			if (vertice.nome.equals(nome))
				return vertice;
		return null;
	}

	public int getIndexVertice(String nome) {
		return vertices.indexOf(getVertice(nome));
	}

	public Vertice adicionarVertice(String nome) {
		Vertice v = new Vertice(nome);
		vertices.add(v);
		return v;
	}

	public void adicionarAresta(String origem, double peso, String chegada) {
		int indexOrigem = getIndexVertice(origem);
		int indexChegada = getIndexVertice(chegada);
		this.matriz[indexOrigem][indexChegada] = peso;
		this.matriz[indexChegada][indexOrigem] = peso;
	}

	public List<Vertice> getVerticesAdjacentes(String origem) {
		List<Vertice> adjacentes = new ArrayList<>();
		int indexVertice = getIndexVertice(origem);
		if (indexVertice != -1) {
			for (int i = 0; i < getNumeroVertices(); i++) {
				if (matriz[indexVertice][i] != 0 && indexVertice != i) {
					adjacentes.add(vertices.get(i));
				}
			}
		}
		return adjacentes;
	}

	public int getNumeroVertices() {
		return vertices.size();
	}

	public double[][] getMatriz() {
		return this.matriz;
	}

	public String toString() {
		String str = "";
		for (Vertice u : vertices) {
			str += u.nome + ": ";
			List<Vertice> adjacentes = getVerticesAdjacentes(u.nome);
			for (Vertice vizinho : adjacentes) {
				str += "[" + vizinho.nome + "]";
			}
			str += "\n";
		}
		return str;
	}
}