package Homework7;

public class Edge {

    private Vertex vertex;
    private int weight;

    public Edge(Vertex vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }
}
