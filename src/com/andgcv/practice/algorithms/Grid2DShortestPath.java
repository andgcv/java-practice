package com.andgcv.practice.algorithms;

import java.util.LinkedList;

public class Grid2DShortestPath {
    private int R, C;
    private int[][] matrix;
    private int startRow, startColumn;

    private LinkedList<Integer> rowQueue = new LinkedList<>();
    private LinkedList<Integer> columnQueue = new LinkedList<>();
    private boolean[][] visited;

    private int moveCount = 0;                  // Number of steps taken to destination node
    private int nodesCurrentLayer = 0;          // How many nodes I need to dequeue to add a step
    private int nodesNextLayer = 0;             // How many nodes I added while expanding BFS, to update previous var

    private boolean reachedDestination = false;

    // Directions of adjacent nodes to current node
    private int[] dRow = {-1, 1, 0, 0};        // North, South
    private int[] dColumn = {0, 0, -1, 1};     // West, East

    public int shortestPath() {
        rowQueue.add(startRow);
        columnQueue.add(startColumn);
        visited[startRow][startColumn] = true;

        while (!rowQueue.isEmpty()) {           // or !columnQueue.isEmpty()
            int currentRow = rowQueue.poll();
            int currentColumn = columnQueue.poll();

            // Check if we're at the destination, assuming our destination is the number 2 in the matrix
            if (matrix[currentRow][currentColumn] == 2) {
                reachedDestination = true;
                break;
            }

            // Add the current node's neighbours to the queue
            addNeighbours(currentRow, currentColumn);

            // Leaving current node, decrement current layer's nodes
            nodesCurrentLayer--;
            // Move on to the next layer, increment the move count
            if (nodesCurrentLayer == 0) {
                nodesCurrentLayer = nodesNextLayer;
                nodesNextLayer = 0;
                moveCount++;
            }
        }

        // Check if we reached the destination
        if (reachedDestination) return moveCount;

        // Didn't reach destination, return -1
        return -1;
    }

    private void addNeighbours(int row, int column) {
        for (int i = 0; i < 4; i++) {
            int adjRow = row + dRow[i];
            int adjColumn = column + dColumn[i];

            // Check if it's out of bounds
            if (adjRow < 0 || adjColumn < 0) continue;
            if (adjRow >= R || adjColumn >= C) continue;

            // Check if it's already visited, or if it's an obstacle (0 -> obstacle)
            if (visited[adjRow][adjColumn]) continue;
            if (matrix[adjRow][adjColumn] == 0) continue;

            // Passed all tests, add current cell to queue and increment next layer's nodes
            rowQueue.add(adjRow);
            columnQueue.add(adjColumn);
            nodesNextLayer++;
        }
    }
}
