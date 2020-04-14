package ru.simplegraph;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractSimpleGraph<T> implements SimpleGraph<T> {
    protected final Map<T, List<SimpleGraphEdge<T>>> vertexes;
    protected final ReentrantLock lock;

    public AbstractSimpleGraph() {
        lock = new ReentrantLock();
        this.vertexes = new LinkedHashMap<>();
    }

    @Override
    public boolean addVertex(T vertex) {
        lock.lock();
        try {
            if (vertex == null || vertexes.containsKey(vertex)) {
                return false;
            }
            vertexes.computeIfAbsent(vertex, k -> new ArrayList<>());
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<SimpleGraphEdge<T>> getPath(T startVertext, T finishVertex) {
        lock.lock();
        try {
            if (!vertexes.containsKey(startVertext) || !vertexes.containsKey(finishVertex)) {
                return new ArrayList<>();
            }

            List<SimpleGraphEdge<T>> path = new ArrayList<>();
            Map<T, Boolean> visited = new LinkedHashMap<>();
            boolean found = getPath(startVertext, finishVertex, path, visited);
            return found ? path : new ArrayList<>();
        } finally {
            lock.unlock();
        }
    }

    protected boolean getPath(T startVertext, T finishVertex, List<SimpleGraphEdge<T>> path, Map<T, Boolean> visited) {
        visited.put(startVertext, true);

        if (startVertext.equals(finishVertex)) {
            //            System.out.println(path);
            visited.put(startVertext, false);
            return true;
        }

        List<SimpleGraphEdge<T>> edges = vertexes.get(startVertext);
        for (SimpleGraphEdge<T> edge : edges) {
            if (!visited.containsKey(edge.getFinishVertex()) || !visited.get(edge.getFinishVertex())) {
                path.add(edge);
                boolean result = getPath(edge.getFinishVertex(), finishVertex, path, visited);

                if (result) {
                    return true;
                }

                path.remove(edge);
            }
        }

        visited.put(startVertext, false);
        return false;
    }
}