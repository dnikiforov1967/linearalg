/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author dima
 */
class DescendingMinor {
    
    private final int[][] matrix;
    private final Set<Integer> colExclusions;
    private final int top;

    DescendingMinor(int[][] matrix) {
        this.matrix = matrix;
        this.colExclusions = new HashSet<>();
        this.top = 0;
    }    
    
    DescendingMinor(int[][] matrix, Set<Integer> colExclusions, int top) {
        this.matrix = matrix;
        this.colExclusions = new HashSet<>(colExclusions);
        this.top = top;
    }
    
    public void addExclusion(int i) {
        this.colExclusions.add(i);
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Set<Integer> getColExclusions() {
        return colExclusions;
    }

    public int getTop() {
        return top;
    }
    
}
