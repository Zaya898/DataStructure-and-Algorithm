package BiyDaalt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    private DijkstraAlgorithm.Graph graph;

    @BeforeEach
    void setUp() {
        graph = new DijkstraAlgorithm.Graph(5);
    }

    @Test
    void testSimpleGraph() {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0], "Эхлэх цэг рүү зай 0 байх ёстой");
        assertEquals(3, distances[1], "0 -> 1 зай 3 байх ёстой (0->2->1)");
        assertEquals(1, distances[2], "0 -> 2 зай 1 байх ёстой");
        assertEquals(4, distances[3], "0 -> 3 зай 4 байх ёстой (0->2->1->3)");
        assertEquals(7, distances[4], "0 -> 4 зай 7 байх ёстой (0->2->1->3->4)");
    }

    @Test
    void testDirectPath() {
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 1);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(5, distances[1]);
        assertEquals(8, distances[2]);
        assertEquals(10, distances[3]);
        assertEquals(11, distances[4]);
    }

    @Test
    void testDisconnectedVertex() {
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(2, distances[1]);
        assertEquals(5, distances[2]);
        assertEquals(Integer.MAX_VALUE, distances[3], "Холбоогүй орой руу зам байхгүй");
        assertEquals(Integer.MAX_VALUE, distances[4], "Холбоогүй орой руу зам байхгүй");
    }

    @Test
    void testSingleVertex() {
        DijkstraAlgorithm.Graph singleGraph = new DijkstraAlgorithm.Graph(1);
        int[] distances = singleGraph.dijkstra(0);

        assertEquals(0, distances[0], "Ганц оройн хувьд зай 0 байх ёстой");
    }

    @Test
    void testMultiplePaths() {
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 5);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 10);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(7, distances[1], "Богино замыг сонгох ёстой (0->2->1)");
        assertEquals(5, distances[2]);
        assertEquals(8, distances[3], "1-ээр дамжих нь богино (0->2->1->3)");
    }

    @Test
    void testDifferentSource() {
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);

        int[] distances = graph.dijkstra(2);

        assertEquals(Integer.MAX_VALUE, distances[0], "Буцах зам байхгүй");
        assertEquals(Integer.MAX_VALUE, distances[1], "Буцах зам байхгүй");
        assertEquals(0, distances[2], "Эхлэх цэг");
        assertEquals(1, distances[3]);
        assertEquals(3, distances[4]);
    }

    @Test
    void testEqualWeights() {
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 1);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(1, distances[1]);
        assertEquals(2, distances[2]);
        assertEquals(3, distances[3]);
        assertEquals(4, distances[4]);
    }

    @Test
    void testLargeWeights() {
        graph.addEdge(0, 1, 1000);
        graph.addEdge(1, 2, 2000);
        graph.addEdge(0, 2, 2500);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(1000, distances[1]);
        assertEquals(2500, distances[2], "Шууд зам илүү богино (0->2)");
    }

    @Test
    void testGraphWithCycle() {
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 3, 4);

        int[] distances = graph.dijkstra(0);

        assertEquals(0, distances[0]);
        assertEquals(2, distances[1]);
        assertEquals(5, distances[2]);
        assertEquals(9, distances[3]);
    }
}