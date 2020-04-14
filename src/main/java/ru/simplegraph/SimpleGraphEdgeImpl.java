package ru.simplegraph;

public class SimpleGraphEdgeImpl<T> implements SimpleGraphEdge<T> {
    protected T startVertext;
    protected T finishVertex;

    public SimpleGraphEdgeImpl(T startVertext, T finishVertex) {
        this.startVertext = startVertext;
        this.finishVertex = finishVertex;
    }

    public T getStartVertext() {
        return startVertext;
    }

    public void setStartVertext(T startVertext) {
        this.startVertext = startVertext;
    }

    public T getFinishVertex() {
        return finishVertex;
    }

    public void setFinishVertex(T finishVertex) {
        this.finishVertex = finishVertex;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (getStartVertext().hashCode());
        hash = 31 * hash + (getFinishVertex().hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof SimpleGraphEdge)) {
            return false;
        }

        SimpleGraphEdge<T> simpleGraphEdge = (SimpleGraphEdge<T>) o;
        return simpleGraphEdge.getStartVertext().equals(getStartVertext())
                && simpleGraphEdge.getFinishVertex().equals(getFinishVertex());
    }

    @Override
    public String toString() {
        return startVertext + " : " + finishVertex;
    }
}