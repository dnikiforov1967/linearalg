/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import java.util.Set;

/**
 *
 * @author dima
 */
public class MatrixHelper {

    public static int det(int[][] matrix) {
        final DescendingMinor minor = new DescendingMinor(matrix);
        return det(minor);
    }

    public static int det(DescendingMinor minor) {
        int val = 0;
        int sign = 1;
        int[][] matrix = minor.getMatrix();
        final Set<Integer> colExclusions = minor.getColExclusions();
        int top = minor.getTop();
        if (top >= matrix.length) {
            return 1;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (!colExclusions.contains(i)) {
                int a1i = matrix[top][i];
                if (a1i != 0) {
                    final DescendingMinor mnr = new DescendingMinor(matrix, colExclusions, top + 1);
                    mnr.addExclusion(i);
                    val += a1i * sign * det(mnr);
                }
                sign = -sign;
            }
        }
        return val;
    }

}
