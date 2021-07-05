package Homework7;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraph {

    private Map<Vertex, List<Edge>> adjMap;

    public WeightedGraph() {
        this.adjMap = new HashMap<>();
    }

    public void addVertex(String label) {
        adjMap.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void addEdge(String label1, String label2, int weight) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        Edge e1 = new Edge(v1, weight);
        Edge e2 = new Edge(v2, weight);
        adjMap.get(v1).add(e2);
        adjMap.get(v2).add(e1);
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Edge> ev1 = adjMap.get(v1);
        List<Edge> ev2 = adjMap.get(v2);
        if (ev1 != null) {
            ev1.removeAll(ev1.stream()
                    .filter(edge -> edge.getVertex().equals(v2))
                    .collect(Collectors.toList()));
        }
        if (ev2 != null) {
            ev2.removeAll(ev2.stream()
                    .filter(edge -> edge.getVertex().equals(v1))
                    .collect(Collectors.toList()));
        }
    }

    public Map<Vertex, Integer> dijkstra(String startLabel, String endLabel) {
        Vertex startVertex = new Vertex(startLabel);
        Map<Vertex, Integer> result = adjMap.keySet().stream()
                .collect(Collectors.toMap(
                        vert -> vert,
                        vert -> vert.equals(startVertex) ? 0 : Integer.MAX_VALUE));
        Map<Vertex, Vertex> prevMap = new HashMap<>();

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(result::get));
        queue.add(startVertex);

        Set<Vertex> settled = new HashSet<>();
        while (!queue.isEmpty()) {
            Vertex currVert = queue.poll();
            int currDist = result.get(currVert);
            if (settled.contains(currVert)) {
                continue;
            }
            settled.add(currVert);
            for (Edge edge : adjMap.get(currVert)) {
                Vertex neighborVert = edge.getVertex();
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

        Stack<Vertex> stack = new Stack<>();
        stack.push(new Vertex(endLabel));
        Vertex curr = prevMap.get(new Vertex(endLabel));
        while (curr != null) {
            stack.push(curr);
            curr = prevMap.get(curr);
        }

        List<Vertex> path = new ArrayList<>();
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        System.out.println("Shortest path: " + path.toString());

        return result;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();
        weightedGraph.addVertex("1");
        weightedGraph.addVertex("2");
        weightedGraph.addVertex("3");
        weightedGraph.addVertex("4");
        weightedGraph.addVertex("5");
        weightedGraph.addVertex("6");
        weightedGraph.addVertex("7");

        weightedGraph.addEdge("1", "2", 7);
        weightedGraph.addEdge("1", "3", 9);
        weightedGraph.addEdge("1", "6", 14);
        weightedGraph.addEdge("2", "3", 10);
        weightedGraph.addEdge("2", "4", 15);
        weightedGraph.addEdge("3", "6", 2);
        weightedGraph.addEdge("3", "4", 11);
        weightedGraph.addEdge("4", "5", 6);
        weightedGraph.addEdge("5", "6", 9);
        weightedGraph.addEdge("6", "7", 5);

        System.out.println(weightedGraph.dijkstra("1", "5"));
    }
}
