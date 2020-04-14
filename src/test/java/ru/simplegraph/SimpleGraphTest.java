package ru.simplegraph;

import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class SimpleGraphTest {
    @Test
    public void testUndirectedSimpleGraph() {
        SimpleGraph<String> graph = new UndirectedSimpleGraph<>();
        assertTrue(graph.addVertex("aaa"));
        assertTrue(graph.addVertex("bbb"));
        assertFalse(graph.addVertex("aaa"));

        assertTrue(graph.addEdge("aaa", "bbb"));
        assertFalse(graph.addEdge("aaa1", "bbb1"));
    }

    private static class CustomVertex {
        public CustomVertex(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String name;
        public Integer weight;

        @Override
        public String toString() {
            return "CustomVertex{name: " + name + ", weight:" + weight + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomVertex that = (CustomVertex) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    @Test
    public void testDirectedSimpleGraph() {
        SimpleGraph<CustomVertex> graph = new DirectedSimpleGraph<>();
        assertTrue(graph.addVertex(new CustomVertex("v1", 1)));
        assertTrue(graph.addVertex(new CustomVertex("v2", 2)));
        assertFalse(graph.addVertex(new CustomVertex("v2", 3)));

        assertTrue(graph.addEdge(new CustomVertex("v1", 1), new CustomVertex("v2", 2)));
        assertFalse(graph.addEdge(new CustomVertex("v2", 5), new CustomVertex("v3", 4)));
    }

    @Test
    public void testDirectedSimpleGraphPath() {
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

        int start = 2;
        int finish = 3;

        System.out.println("direct path from " + start + " to " + finish);

        List<SimpleGraphEdge<Integer>> path = graph.getPath(start, finish);

        if (!path.isEmpty()) {
            System.out.print(start + "  ");
        }

        for (SimpleGraphEdge<Integer> edge : path) {
            System.out.print(edge.getFinishVertex() + "  ");
        }

        System.out.println();
        System.out.println();

        assertFalse(path.isEmpty());
        System.out.println(path);

        assertEquals("[2 : 1, 1 : 3]", path.toString());
    }

    @Test
    public void testUndirectedSimpleGraphPath() {
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

        int start = 2;
        int finish = 3;

        System.out.println("undirect path from " + start + " to " + finish);

        List<SimpleGraphEdge<Integer>> path = graph.getPath(start, finish);

        if (!path.isEmpty()) {
            System.out.print(start + "  ");
        }

        for (SimpleGraphEdge<Integer> edge : path) {
            System.out.print(edge.getFinishVertex() + "  ");
        }

        System.out.println();
        System.out.println();

        assertFalse(path.isEmpty());
        System.out.println(path);

        assertEquals("[2 : 0, 0 : 3]", path.toString());

        List<SimpleGraphEdge<Integer>> revertedPath = graph.getPath(finish, start);

        assertFalse(revertedPath.isEmpty());
    }

    @Test
    public void testDirectedSimpleGraphWrongPath() {
        SimpleGraph<Integer> graph = new DirectedSimpleGraph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(0, 3);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);

        int start = 3;
        int finish = 2;

        System.out.println("wrong direct path from " + start + " to " + finish);

        List<SimpleGraphEdge<Integer>> path = graph.getPath(start, finish);

        if (!path.isEmpty()) {
            System.out.print(start + "  ");
        }

        for (SimpleGraphEdge<Integer> edge : path) {
            System.out.print(edge.getFinishVertex() + "  ");
        }

        System.out.println();
        System.out.println();

        assertTrue(path.isEmpty());
        System.out.println(path);
    }
}