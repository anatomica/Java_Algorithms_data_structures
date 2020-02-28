package Homework7;

import java.util.LinkedList;
import java.util.stream.IntStream;

class Graph {

    public static void main(String[] args) {

        int size = 10;
        Graph graph = new Graph(size);
        IntStream.range(0, size).forEach(graph::addVertex);

        graph.adj(0, 1);
        graph.adj(1, 2);
        graph.adj(0, 3);
        graph.adj(3, 6);
        graph.adj(4, 7);
        graph.adj(4, 5);
        graph.adj(7, 8);
        graph.adj(6, 9);
        graph.adj(6, 7);
        graph.adj(5, 2);

        graph.inDepth();
        System.out.print(System.lineSeparator());
        graph.broadwise(true, null);
        System.out.print(System.lineSeparator());
        Graph.Vertex graphVertex = graph.getVertex(9);
        graph.showPath(graphVertex);
    }

    static class Vertex {
        private int payload;
        private boolean visited;
        private int weight;

        Vertex(int payload) {
            this.payload = payload;
            this.visited = false;
            this.weight = -1;
        }

        boolean isVisited() {
            return this.visited;
        }

        int getWeight() {
            return this.weight;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        void setWeight(int weight) {
            this.weight = weight;
        }
    }

    private final int MAX_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int quantity;

    Graph(int size) {
        MAX_VERTICES = size;
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        quantity = 0;
    }

    void addVertex(int label) {
        if (vertices.length > MAX_VERTICES)
            throw new IndexOutOfBoundsException("Max vertices is " + MAX_VERTICES);
        vertices[quantity++] = new Vertex(label);
    }

    void adj(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    private void displayVertex(int index) {
        System.out.printf("[p: %s, w: %s]%n", vertices[index].payload, vertices[index].weight);
    }

    void inDepth() {
        System.out.println("In depth: ");
        vertices[0].setVisited(true);
        displayVertex(0);
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(vertices[0]);
        while (!stack.isEmpty()) {
            int v = getUnvisitedNeighbor(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertices[v].setVisited(true);
                displayVertex(v);
                stack.push(vertices[v]);
            }
        }
        refreshFlags();
    }

    private int getIndexFromVertices(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++)
            if (vertices[i].equals(vertex))
                return i;
        return -1;
    }

    private int getUnvisitedNeighbor(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (adjMatrix[getIndexFromVertices(vertex)][i] == 1 && !vertices[i].isVisited()) {
                return i;
            }
        }
        return -1;
    }

    private void refreshFlags() {
        for (int i = 0; i < vertices.length; i++)
            vertices[i].setVisited(false);
    }

    int broadwise(boolean printable, Vertex target) {
        if (printable)
            System.out.println("Broadwise: ");
        LinkedList<Vertex> queue = new LinkedList<>();
        vertices[0].setVisited(true);
        queue.add(vertices[0]);
        int next;
        int weight = 0;
        vertices[0].setWeight(weight);
        if (printable)
            displayVertex(0);
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            while ((next = getUnvisitedNeighbor(current)) != -1) {
                vertices[next].setWeight(current.getWeight() + 1);
                if (vertices[next] == target)
                    return next;
                vertices[next].setVisited(true);
                if (printable)
                    displayVertex(next);
                queue.add(vertices[next]);
            }
        }
        refreshFlags();
        return -1;
    }

    private int getPrevious(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (adjMatrix[getIndexFromVertices(vertex)][i] == 1) {
                if (vertex.getWeight() - vertices[i].getWeight() == 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    Vertex getVertex(int i) {
        return vertices[i];
    }

    void showPath(Vertex target) {
        System.out.print("Path: ");
        if (vertices[0] == target)
            System.out.println(target);
        LinkedList<Vertex> stack = new LinkedList<>();
        int current;
        if ((current = broadwise(false, target)) >= 0) {
            int previous;
            while ((previous = getPrevious(vertices[current])) != -1) {
                current = previous;
                stack.push(vertices[current]);
            }
        } else
            System.out.println("Not Path!");
        StringBuilder result = new StringBuilder();
        for (Vertex vertex : stack) {
            result.append(vertex.payload);
            result.append(" > ");
        }
        System.out.println(result.toString());
    }
}