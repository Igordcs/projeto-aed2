package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
	double precoFibra;
	double precoAmplificador;
	int numAmplificador;
	int distanciaSinal;

	public Dijkstra(double precoFibra, double precoAmplificador, int distanciaSinal) {
		this.precoFibra = precoFibra;
		this.precoAmplificador = precoAmplificador;
		this.numAmplificador = 0;
		this.distanciaSinal = distanciaSinal;
	}

	// vai calcular a menor distancia a partir de um vértice origem a todos outros vértices
	public void gerarCaminho(GrafoPonderado gp, Vertice origem) {
		origem.distanciaAcumulada = 0;
		// criar fila de prioridade com o nó origem
		PriorityQueue<Vertice> filaVertices = new PriorityQueue<Vertice>();
		filaVertices.add(origem);

		while (!filaVertices.isEmpty()) {
			// retira o no com menor distancia (primeiro)
			Vertice verticeAux = filaVertices.poll();
			verticeAux.visitado = true;
			List<Vertice> adjacentes = gp.getVerticesAdjacentes(verticeAux.nome);

			// percorre por todas arestas adjacentes
			for (Vertice vizinho : adjacentes) {
				int custo = gp.getMatriz()[gp.getIndexVertice(verticeAux.nome)][gp.getIndexVertice(vizinho.nome)];
				if (!vizinho.visitado && (verticeAux.distanciaAcumulada + custo) < vizinho.distanciaAcumulada) {
					filaVertices.remove(vizinho);
					vizinho.distanciaAcumulada = verticeAux.distanciaAcumulada + custo;
					vizinho.anterior = verticeAux;
					filaVertices.add(vizinho);
				}
			}
		}
	}

	public List<Vertice> getMenorCaminho(GrafoPonderado gp, Vertice origem, Vertice chegada) {
		gerarCaminho(gp, origem);
		List<Vertice> vertices = new ArrayList<Vertice>();
		Vertice v = chegada;
		numAmplificador = v.distanciaAcumulada / distanciaSinal;
		while (v != null) {
			vertices.add(v);
			v = v.anterior;
		}
		Collections.reverse(vertices);
		return vertices;
	}

	public void imprimirDados(List<Vertice> caminho, double precoFibra) {
		Vertice v = caminho.get(caminho.size() - 1);
		double custoFibra = v.distanciaAcumulada * precoFibra;
		double custoAmplificadores = precoAmplificador * numAmplificador;
		double custoTotal = custoFibra + custoAmplificadores;

		System.out.println("Caminho de menor custo: " + caminho);
		System.out.printf("Distância: %d km\n", v.distanciaAcumulada);
		System.out.println("Custos:");
		System.out.printf("- Fibra óptica: R$ %.2f\n", custoFibra);
		System.out.printf("- Amplificadores (%d): R$ %.2f\n", numAmplificador, custoAmplificadores);
		System.out.printf("- Total: R$ %.2f\n", custoTotal);
	}
}