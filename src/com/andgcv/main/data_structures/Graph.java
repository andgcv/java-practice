package com.andgcv.main.data_structures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Implemented with an Adjacency List
// Unweighted
public class Graph {
    HashMap<Integer, Vertex> vertexLookup = new HashMap<>();

    public static class Vertex {
        private int id;
        LinkedList<Vertex> adjacent = new LinkedList<>();

        Vertex(int id) {
            this.id = id;
        }
    }

    private Vertex getVertex(int id) {
        return vertexLookup.get(id);
    }

    // Add an edge between the Vertex source and the Vertex destination
    public void addEdge(int source, int destination) {
        Vertex s = getVertex(source);
        Vertex d = getVertex(destination);
        s.adjacent.add(d);
    }

    // Performs a Depth-First Search and returns whether there's a path between source and destination or not
    public boolean hasPathDFS(int source, int destination) {
        Vertex s = getVertex(source);
        Vertex d = getVertex(destination);
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(s, d, visited);
    }

    // HELPER METHOD - DFS -> O(V + E) time, O(n) space
    private boolean hasPathDFS(Vertex source, Vertex destination, HashSet<Integer> visited) {
        if (visited.contains(source.id)) return false;
        visited.add(source.id);
        if (source == destination) return true;

        // Will perform a Depth-First Search on each Vertex adjacent to the original Vertex source
        for (Vertex adjacent : source.adjacent) {
            if (hasPathDFS(adjacent, destination, visited)) return true;
        }

        return false;
    }

    // Performs a Breath-First Search and returns whether there's a path between source and destination or not
    public boolean hasPathBFS(int source, int destination) {
        Vertex s = getVertex(source);
        Vertex d = getVertex(destination);
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Vertex> next = new LinkedList<>();   // Queue to store the next Vertices to visit
        return hasPathBFS(s, d, visited, next);
    }

    // HELPER METHOD - BFS -> O(V + E) time, O(n) space
    private boolean hasPathBFS(Vertex source, Vertex destination, HashSet<Integer> visited, LinkedList<Vertex> next) {
        next.add(source);

        while (!next.isEmpty()) {
            Vertex current = next.poll();
            if (visited.contains(current.id)) continue;
            visited.add(current.id);
            if (current == destination) return true;

            for (Vertex adjacent : current.adjacent) {
                if (!visited.contains(adjacent.id)) next.add(adjacent);
            }
        }
        return false;
    }
}
