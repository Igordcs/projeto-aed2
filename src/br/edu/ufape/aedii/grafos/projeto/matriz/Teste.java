package br.edu.ufape.aedii.grafos.projeto.matriz;

import java.util.List;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		GrafoPonderado g = new GrafoPonderado("municipiospe.txt");
		int opcao = 0;
		String provedor, destino;
		Scanner sc = new Scanner(System.in, "UTF-8");

		Dijkstra dijkstra = new Dijkstra(719.99, 3200.00, 40);
		while(opcao != 99) {
			System.out.println("1. Escolher locais");
			System.out.println("99. Sair");
			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
				case 1:
					System.out.println("Digite a cidade do provedor: ");
					provedor = sc.nextLine();
					System.out.println(provedor);
					System.out.println("Digite a cidade de destino: ");
					destino = sc.nextLine();
					Vertice origem = g.getVertice(provedor);
					Vertice chegada = g.getVertice(destino);
					List<Vertice> caminho = dijkstra.getMenorCaminho(g, origem, chegada);
					dijkstra.imprimirResultados(caminho, 719.99);
					break;
			
				default:
					break;
			}
		}
	}

}