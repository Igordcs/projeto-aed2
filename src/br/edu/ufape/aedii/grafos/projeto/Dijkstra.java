package br.edu.ufape.aedii.grafos.projeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
    
    // vai calcular a menor distancia a partir de um vértice origem a todos outros vértices
	public void geraCaminho(Vertice origem) {
        origem.distancia_acumulada = 0;
        // criar fila de prioridade com o nó origem
        PriorityQueue<Vertice> fila_vertices = new PriorityQueue<Vertice>();
        fila_vertices.add(origem);

        while(!fila_vertices.isEmpty()) {
            // retira o no com menor distancia (primeiro)
            Vertice vertice_aux = fila_vertices.poll();
            vertice_aux.visitado = true;

            // percorre por todas arestas adjacentes
            for (Aresta a : vertice_aux.adjacentes) {
                Vertice vizinho = a.origem;
                Vertice vizinho2 = a.destino;
                int custo = a.peso;
                if (vertice_aux.nome.equals(vizinho.nome)) {
                    if (!vizinho2.visitado && (vertice_aux.distancia_acumulada + custo) < vizinho2.distancia_acumulada) {
                        fila_vertices.remove(vizinho2); // retira da fila o elemento já vizitado
                        vizinho2.distancia_acumulada = vertice_aux.distancia_acumulada + custo; // calcula a distancia acumulada (Distancia da origem até o vértice)
                        vizinho2.anterior = vertice_aux;
                        fila_vertices.add(vizinho2); // Adiciona o vértice vizinho2 para o novo loop
                    }
                } else {
                    if (!vizinho.visitado && (vertice_aux.distancia_acumulada + custo) < vizinho.distancia_acumulada) {
                        fila_vertices.remove(vizinho); // retira da fila o elemento já vizitado
                        vizinho.distancia_acumulada = vertice_aux.distancia_acumulada + custo; // calcula a distancia acumulada (Distancia da origem até o vértice)
                        vizinho.anterior = vertice_aux;
                        fila_vertices.add(vizinho); // Adiciona o vértice vizinho para o novo loop
                    }
                }
            }
        }
    }
    public ArrayList<Vertice> getMenorCaminho(Vertice chegada) {
        ArrayList<Vertice> list_vertices = new ArrayList<Vertice>();
        Vertice v = chegada;
        while (v != null) {
            list_vertices.add(v);
            v = v.anterior;
        }
        Collections.reverse(list_vertices);
        return list_vertices;
    }
}
