package Homework7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Graph {

    private static Map<Homework7.Vertex, List<Edge>> adjMap;

    public static void main(String[] args) {

        int size = 10;
        Graph graph = new Graph(size);
        IntStream.range(0, size).forEach(graph::addVertex);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);
        graph.addEdge(4, 5);
        graph.addEdge(7, 8);
        graph.addEdge(6, 9);
        graph.addEdge(6, 7);
        graph.addEdge(5, 2);

        graph.inDepth();
        System.out.print(System.lineSeparator());
        graph.inWidth(true, null);
        System.out.print(System.lineSeparator());
        Vertex graphVertex = graph.getVertex(9);
        graph.showPath(graphVertex);
        System.out.print(System.lineSeparator());

        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");

        graph.addEdge("1", "2", 7);
        graph.addEdge("1", "3", 9);
        graph.addEdge("1", "6", 14);
        graph.addEdge("2", "3", 10);
        graph.addEdge("2", "4", 15);
        graph.addEdge("3", "6", 2);
        graph.addEdge("3", "4", 11);
        graph.addEdge("4", "5", 6);
        graph.addEdge("5", "6", 9);
        graph.addEdge("6", "7", 5);

        System.out.println(Graph.dijkstra("1", "5"));
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
        adjMap = new HashMap<>();
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        quantity = 0;
    }

    void addVertex(int label) {
        if (vertices.length > MAX_VERTICES)
            throw new IndexOutOfBoundsException("Max vertices is " + MAX_VERTICES);
        vertices[quantity++] = new Vertex(label);
    }

    void addVertex(String label) {
        adjMap.putIfAbsent(new Homework7.Vertex(label), new ArrayList<>());
    }

    void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    void addEdge(String label1, String label2, int weight) {
        Homework7.Vertex v1 = new Homework7.Vertex(label1);
        Homework7.Vertex v2 = new Homework7.Vertex(label2);
        Edge e1 = new Edge(v1, weight);
        Edge e2 = new Edge(v2, weight);
        adjMap.get(v1).add(e2);
        adjMap.get(v2).add(e1);
    }

    private void displayVertex(int index) {
        System.out.printf("[p: %s, w: %s]%n", vertices[index].payload, vertices[index].weight);
    }

    private int getIndexFromVertices(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++)
            if (vertices[i].equals(vertex))
                return i;
        return -1;
    }

    private int getUnvisited(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (adjMatrix[getIndexFromVertices(vertex)][i] == 1 && !vertices[i].isVisited()) {
                return i;
            }
        }
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

    private void refreshFlags() {
        for (Vertex vertex : vertices) vertex.setVisited(false);
    }

    void inDepth() {
        System.out.println("In depth: ");
        vertices[0].setVisited(true);
        displayVertex(0);
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(vertices[0]);
        while (!stack.isEmpty()) {
            int v = getUnvisited(stack.peek());
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

    private int inWidth(boolean printable, Vertex target) {
        if (printable) System.out.println("In width: ");
        LinkedList<Vertex> queue = new LinkedList<>();
        vertices[0].setVisited(true);
        queue.add(vertices[0]);
        int next;
        int weight = 0;
        vertices[0].setWeight(weight);
        if (printable) displayVertex(0);
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            while ((next = getUnvisited(current)) != -1) {
                vertices[next].setWeight(current.getWeight() + 1);
                if (vertices[next] == target)
                    return next;
                vertices[next].setVisited(true);
                if (printable) displayVertex(next);
                queue.add(vertices[next]);
            }
        }
        refreshFlags();
        return -1;
    }

    public static Map<Homework7.Vertex, Integer> dijkstra(String startLabel, String endLabel) {
        Homework7.Vertex startVertex = new Homework7.Vertex(startLabel);
        Map<Homework7.Vertex, Integer> result = adjMap.keySet().stream()
                .collect(Collectors.toMap(
                        vert -> vert,
                        vert -> vert.equals(startVertex) ? 0 : Integer.MAX_VALUE));
        Map<Homework7.Vertex, Homework7.Vertex> prevMap = new HashMap<>();

        PriorityQueue<Homework7.Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(result::get));
        queue.add(startVertex);

        Set<Homework7.Vertex> settled = new HashSet<>();
        while (!queue.isEmpty()) {
            Homework7.Vertex currVert = queue.poll();
            int currDist = result.get(currVert);
            if (settled.contains(currVert)) {
                continue;
            }
            settled.add(currVert);
            for (Edge edge : adjMap.get(currVert)) {
                Homework7.Vertex neighborVert = edge.getVertex();
                int neighborDist = result.get(neighborVert);
                if (settled.contains(edge.getVertex())) {
                    continue;
                }

                if (neighborDist == Integer.MAX_VALUE || neighborDist > currDist + edge.getWeight()) {
                    result.put(neighborVert, currDist + edge.getWeight());
                    queue.add(neighborVert);
                    prevMap.put(neighborVert, currVert);
                }
            }
        }

        Stack<Homework7.Vertex> stack = new Stack<>();
        stack.push(new Homework7.Vertex(endLabel));
        Homework7.Vertex curr = prevMap.get(new Homework7.Vertex(endLabel));
        while (curr != null) {
            stack.push(curr);
            curr = prevMap.get(curr);
        }

        List<Homework7.Vertex> path = new ArrayList<>();
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        System.out.println("Shortest path: " + path.toString());

        return result;
    }

    void showPath(Vertex target) {
        System.out.print("Path: ");
        if (vertices[0] == target)
            System.out.println(target);
        LinkedList<Vertex> stack = new LinkedList<>();
        int current;
        if ((current = inWidth(false, target)) >= 0) {
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