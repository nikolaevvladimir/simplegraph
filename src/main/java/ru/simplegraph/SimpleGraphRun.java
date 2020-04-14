package ru.simplegraph;

import java.util.List;

public class SimpleGraphRun {
    public static void main(String[] args) {
        testUndirectedSimpleGraph();
        testDirectedSimpleGraph();
    }

    private static void testDirectedSimpleGraph() {
        SimpleGraph<Integer> graph = new DirectedSimpleGraph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(0, 3);
//        graph.addEdge(2, 0);
//        graph.addEdge(2, 1);
//        graph.addEdge(1, 3);

//        graph.addEdge(2, 0);
        graph.addEdge(0, 3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);

        int s = 2;
        int d = 3;

        System.out.println("direct path from " + s + " to " + d);

        List<SimpleGraphEdge<Integer>> path = graph.getPath(s, d);

//        System.out.println(path);

        if (!path.isEmpty()) {
            System.out.print(s + "  ");
        }

        for (SimpleGraphEdge<Integer> edge : path) {
            System.out.print(edge.getFinishVertex() + "  ");
        }

        System.out.println();
        System.out.println();
    }

    private static void testUndirectedSimpleGraph() {
        SimpleGraph<Integer> graph = new UndirectedSimpleGraph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(0, 3);
//        graph.addEdge(2, 0);
//        graph.addEdge(2, 1);
//        graph.addEdge(1, 3);

//        graph.addEdge(2, 0);
        graph.addEdge(0, 3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);

        int s = 2;
        int d = 3;

        System.out.println("undirect path from " + s + " to " + d);

        List<SimpleGraphEdge<Integer>> path = graph.getPath(s, d);

        if (!path.isEmpty()) {
            System.out.print(s + "  ");
        }

        for (SimpleGraphEdge<Integer> edge : path) {
            System.out.print(edge.getFinishVertex() + "  ");
        }

        System.out.println();
        System.out.println();
    }
}