package ru.simplegraph;

public class UndirectedSimpleGraph<T> extends AbstractSimpleGraph<T> {
    public UndirectedSimpleGraph() {
        super();
    }

    @Override
    public boolean addEdge(T startVertext, T finishVertex) {
        lock.lock();
        try {
            if (startVertext == null || finishVertex == null ||
                    !vertexes.containsKey(startVertext) || !vertexes.containsKey(finishVertex)
                    || startVertext.equals(finishVertex)) {
                return false;
            }
            vertexes.get(startVertext).add(new SimpleGraphEdgeImpl<>(startVertext, finishVertex));
            vertexes.get(finishVertex).add(new SimpleGraphEdgeImpl<>(finishVertex, startVertext));
            return true;
        } finally {
            lock.unlock();
        }
    }
}