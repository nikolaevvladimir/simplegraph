package ru.simplegraph;

import java.util.List;

public interface SimpleGraph<T> {
    boolean addVertex(T vertex);

    boolean addEdge(T startVertext, T finishVertex);

    List<SimpleGraphEdge<T>> getPath(T startVertext, T finishVertex);
}