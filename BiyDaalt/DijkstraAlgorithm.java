package BiyDaalt;

import java.util.*;

public class DijkstraAlgorithm {

    static class Graph {
        private int vertices;
        private List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            adjacencyList.get(source).add(new Edge(destination, weight));
        }

        public void printGraph() {
            System.out.println("\n Графын бүтэц ");
            for (int i = 0; i < vertices; i++) {
                System.out.print("Орой " + i + ": ");
                for (Edge edge : adjacencyList.get(i)) {
                    System.out.print("-> " + edge.destination + "(жин:" + edge.weight + ") ");
                }
                System.out.println();
            }
        }

        public int[] dijkstra(int source){
            int[] distances = new int[vertices];
            Arrays.fill(distances,Integer.MAX_VALUE);
            distances[source] = 0;
            boolean[] visited = new boolean[vertices];
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n->n.distance));
            pq.add(new Node(source,0));
            while(!pq.isEmpty()){
                Node current = pq.poll();
                int u = current.vertex;
                if(visited[u]) continue;
                visited[u] = true;
                for(Edge edge: adjacencyList.get(u)){
                    int v = edge.destination;
                    int weight = edge.weight;
                    if(!visited[v] && distances[u]!=Integer.MAX_VALUE && distances[u]+weight<distances[v]){
                        distances[v] = distances[u] + weight;
                        pq.add(new Node(v,distances[v]));
                    }
                }
            }
            return distances;
        }

        public void printShortestPaths(int source, int[] distances) {
            System.out.println("\n Орой " + source + "-аас бусад орой хүртэлх хамгийн богино зай ");
            for (int i = 0; i < vertices; i++) {
                if (distances[i] == Integer.MAX_VALUE) {
                    System.out.println("Орой " + source + " -> Орой " + i + ": Зам олдсонгүй");
                } else {
                    System.out.println("Орой " + source + " -> Орой " + i + ": " + distances[i]);
                }
            }
        }
    }

    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DIJKSTRA-ИЙН АЛГОРИТМ - Богино зам олох");

        System.out.print("\nОройн тоог оруулна уу: ");
        int vertices = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.print("Ирмэгийн тоог оруулна уу: ");
        int edges = scanner.nextInt();

        System.out.println("\nИрмэгийн мэдээллийг оруулна уу (эх төгсгөл жин):");
        System.out.println("Жишээ: 0 1 4 гэсэн нь 0-ээс 1 рүү 4 жинтэй ирмэг");

        for (int i = 0; i < edges; i++) {
            System.out.print("Ирмэг " + (i + 1) + ": ");
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        graph.printGraph();

        System.out.print("\nЭхлэх оройг сонгоно уу (0-" + (vertices - 1) + "): ");
        int source = scanner.nextInt();

        int[] distances = graph.dijkstra(source);
        graph.printShortestPaths(source, distances);

        System.out.println("\n✓ Амжилттай дууслаа!");
        scanner.close();
    }
}
