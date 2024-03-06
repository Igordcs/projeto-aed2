package br.edu.ufape.aedii.grafos.projeto.lista;

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
	public void gerarCaminho(Vertice origem) {
		origem.distanciaAcumulada = 0;
		// criar fila de prioridade com o nó origem
		PriorityQueue<Vertice> filaVertices = new PriorityQueue<Vertice>();
		filaVertices.add(origem);

		while (!filaVertices.isEmpty()) {
			// retira o no com menor distancia (primeiro)
			Vertice verticeAux = filaVertices.poll();
			verticeAux.visitado = true;

			// percorre por todas arestas adjacentes
			for (Aresta a : verticeAux.adjacentes) {
				Vertice vizinho = a.origem;
				Vertice vizinho2 = a.destino;
				int custo = a.peso;
				if (verticeAux.nome.equals(vizinho.nome)) {
					if (!vizinho2.visitado && (verticeAux.distanciaAcumulada + custo) < vizinho2.distanciaAcumulada) {
						filaVertices.remove(vizinho2); // retira da fila o elemento já vizitado
						vizinho2.distanciaAcumulada = verticeAux.distanciaAcumulada + custo; // calcula a distancia acumulada (Distancia da origem até o vértice)
						vizinho2.anterior = verticeAux;
						filaVertices.add(vizinho2); // Adiciona o vértice vizinho2 para o novo loop
					}
				} else {
					if (!vizinho.visitado && (verticeAux.distanciaAcumulada + custo) < vizinho.distanciaAcumulada) {
						filaVertices.remove(vizinho); // retira da fila o elemento já vizitado
						vizinho.distanciaAcumulada = verticeAux.distanciaAcumulada + custo; // calcula a distancia acumulada (Distancia da origem até o vértice)
						vizinho.anterior = verticeAux;
						filaVertices.add(vizinho); // Adiciona o vértice vizinho para o novo loop
					}
				}
			}
		}
	}

	public List<Vertice> getMenorCaminho(Vertice origem, Vertice chegada) {
		gerarCaminho(origem);
		List<Vertice> vertices = new ArrayList<Vertice>();
		Vertice v = chegada;
		numAmplificador = (int) v.distanciaAcumulada / distanciaSinal;
		while (v != null) {
			vertices.add(v);
			v = v.anterior;
		}
		Collections.reverse(vertices);
		return vertices;
	}
		
	public void imprimirDados(List<Vertice> caminho) {
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
