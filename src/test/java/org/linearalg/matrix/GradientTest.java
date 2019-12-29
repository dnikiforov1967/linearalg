/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import org.junit.Test;

/**
 *
 * @author dima
 */
public class GradientTest {
    
    private double func(double x) {
        return x*x;
    }
    
    private double derivation(double x) {
        return 2*x;
    }
    
    private double gradientDown(double x0, double precise, double rate) {
        double y = func(x0);
        double x = x0 - rate*derivation(x0);
        while(Math.abs(y-func(x)) > precise) {
            y = func(x);
            System.out.println(y);
            x = x - rate*derivation(x);
        }
        return y;
    }
    
    @Test
    public void test() {
        gradientDown(2, 0.0001, 0.002);
    }
    
}
