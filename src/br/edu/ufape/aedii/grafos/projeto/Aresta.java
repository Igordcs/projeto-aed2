package br.edu.ufape.aedii.grafos.projeto;

public class Aresta {
    Vertice origem;
    Vertice destino;
    int peso;

    public Aresta(Vertice origem, int peso, Vertice destino) {
        this.origem = origem;
        this.peso = peso;
        this.destino = destino;
    }
}
