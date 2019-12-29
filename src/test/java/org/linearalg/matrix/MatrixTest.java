/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author dima
 */
public class MatrixTest {
    
    @Test
    public void testDet() {
        int[][] m = new int[][] {{4,5},{1,2}};
        assertEquals(3,MatrixHelper.det(m));
        m = new int[][] {{4,5,1},{1,2,1},{1,7,2}};
        assertEquals(-12,MatrixHelper.det(m));
    }
    
}
