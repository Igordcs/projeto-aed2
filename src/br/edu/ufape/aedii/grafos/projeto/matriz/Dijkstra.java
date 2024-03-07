package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
	double precoFibra;
	double precoAmplificador;
	int numAmplificadores;
	double alcanceSinal;

	public Dijkstra(double precoFibra, double precoAmplificador, double alcanceSinal) {
		this.precoFibra = precoFibra;
		this.precoAmplificador = precoAmplificador;
		this.numAmplificadores = 0;
		this.alcanceSinal = alcanceSinal;
	}

	public void inicializarVertices(GrafoPonderado gp) {
		for (Vertice v: gp.vertices) {
			v.distanciaAcumulada = Double.POSITIVE_INFINITY;
			v.visitado = false;
			v.anterior = null;
		}
	}
	
	public void gerarCaminho(GrafoPonderado gp, Vertice origem) {
		inicializarVertices(gp);
		origem.distanciaAcumulada = 0;
		PriorityQueue<Vertice> filaVertices = new PriorityQueue<Vertice>();
		filaVertices.add(origem);
		
		while (!filaVertices.isEmpty()) {
			Vertice verticeAux = filaVertices.poll();
			verticeAux.visitado = true;
			List<Vertice> adjacentes = gp.getVerticesAdjacentes(verticeAux.nome);

			for (Vertice vizinho : adjacentes) {
				double custo = gp.getMatriz()[gp.getIndexVertice(verticeAux.nome)][gp.getIndexVertice(vizinho.nome)];
				if (!vizinho.visitado && (verticeAux.distanciaAcumulada + custo) < vizinho.distanciaAcumulada) {
					filaVertices.remove(vizinho);
					vizinho.distanciaAcumulada = verticeAux.distanciaAcumulada + custo;
					vizinho.anterior = verticeAux;
					filaVertices.add(vizinho);
				}
			}
		}
	}

	public void posicionarAmplificadores(List<Vertice> caminho) {
		double posicaoAmplificador = alcanceSinal;
		double distAmplificador;
		int cont = 0;

		for (Vertice vertice : caminho) {
			Vertice anterior = vertice.anterior;
			while (posicaoAmplificador < vertice.distanciaAcumulada) {
				distAmplificador = posicaoAmplificador - anterior.distanciaAcumulada;
				System.out.printf("Amplificador %d a %.1f km de %s\n", ++cont, distAmplificador, anterior.nome);
				posicaoAmplificador += alcanceSinal;
			}
			if (posicaoAmplificador == vertice.distanciaAcumulada) {
				System.out.printf("Amplificador %d a 0,0 km de %s\n", ++cont, vertice.nome);
				posicaoAmplificador += alcanceSinal;
			}
		}
	}

	public List<Vertice> getMenorCaminho(GrafoPonderado gp, Vertice origem, Vertice chegada) {
		gerarCaminho(gp, origem);
		List<Vertice> vertices = new ArrayList<Vertice>();
		Vertice v = chegada;
		numAmplificadores = (int) (chegada.distanciaAcumulada / alcanceSinal);
		while (v != null) {
			vertices.add(v);
			v = v.anterior;
		}
		Collections.reverse(vertices);
		return vertices;
	}

	public void imprimirResultados(List<Vertice> caminho) {
		Vertice v = caminho.get(caminho.size() - 1);
		double custoFibra = v.distanciaAcumulada * precoFibra;
		double custoAmplificadores = precoAmplificador * numAmplificadores;
		double custoTotal = custoFibra + custoAmplificadores;

		System.out.println("Caminho de menor custo: " + caminho);
		System.out.printf("Distância: %.1f km\n", v.distanciaAcumulada);
		posicionarAmplificadores(caminho);
		System.out.println("Custos:");
		System.out.printf("- Fibra óptica: R$ %.2f\n", custoFibra);
		System.out.printf("- Amplificadores (%d): R$ %.2f\n", numAmplificadores, custoAmplificadores);
		System.out.printf("- Total: R$ %.2f\n", custoTotal);
		System.out.println();
	}
}