package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.List;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		int opcao = 0;
		String provedor, destino;
		Vertice origem, chegada;
		Scanner sc = new Scanner(System.in);
		GrafoPonderado g = new GrafoPonderado("municipiospe.txt");

		// custoFibraKm, custoAmplificador, alcanceSinal
		Dijkstra dijkstra = new Dijkstra(719.99, 3200.00, 40);

		while (opcao != 99) {
			System.out.println("1. Buscar menor custo para ampliar a rede");
			System.out.println("2. Exibir quantidade de municípios");
			System.out.println("3. Exibir municípios limítrofes");
			System.out.println("4. Exibir todos os municípios");
			System.out.println("99. Sair");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
				case 1:
					System.out.println("Insira o município do provedor: ");
					provedor = sc.nextLine();
					System.out.println("Insira o município de destino: ");
					destino = sc.nextLine();
					origem = g.getVertice(provedor);
					chegada = g.getVertice(destino);
	
					if (origem != null && chegada != null) {
						List<Vertice> caminho = dijkstra.getMenorCaminho(g, origem, chegada);
						dijkstra.imprimirResultados(caminho);
					} else {
						System.out.println("Erro ao encontrar os municípios.\n");
					}
					break;
	
				case 2:
					System.out.println("Quantidade de municípios: " + g.getNumeroVertices() + "\n");
					break;
	
				case 3:
					System.out.println("Insira o município: ");
					destino = sc.nextLine();
					List<Vertice> limitrofes = g.getVerticesAdjacentes(destino);
					if (limitrofes != null) {
						System.out.println(limitrofes + "\n");
					} else {
						System.out.println("Município não encontrado.\n");
					}
					break;
	
				case 4:
					g.imprimirVertices();
					System.out.println();
					break;
	
				default:
					break;
			}
		}
	}

}