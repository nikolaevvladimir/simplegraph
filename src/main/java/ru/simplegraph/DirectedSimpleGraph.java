package ru.simplegraph;

public class DirectedSimpleGraph<T> extends AbstractSimpleGraph<T> {
    public DirectedSimpleGraph() {
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
            return true;
        } finally {
            lock.unlock();
        }
    }
}