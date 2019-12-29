/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dima
 */
public class Node {
    
    private double income = 0;
    private double delta = 0;
    private final List<NextNode> nextLayer = new ArrayList<>();

    public void resetIncome() {
        income = 0;
    }

    public double getDelta() {
        return delta;
    }
    
    public void calcDelta(double expected) {
        double o = Node.result(income);
        if (nextLayer.isEmpty()) {
            delta = o*(1-o)*(o-expected);
        } else {
            double sum = 0;
            for(NextNode nn : nextLayer) {
                sum += nn.next.delta*nn.weight;
            }
            delta = o*(1-o)*sum;
        }
    }

    public double getIncome() {
        return income;
    }

    public void addIncome(double income) {
        this.income += income;
    }
    
    public static double result(double signal) {
        return Math.exp(signal)/(1+Math.exp(signal));
    }
    
    public static double derive(double signal) {
        final double result = result(signal);
        return result*(1-result);
    }
    
    public static class NextNode {
        private double weight;
        private double weight_delta;
        private final Node next;

        public NextNode(double weight, Node next) {
            this.weight = weight;
            this.next = next;
            this.weight_delta = 0;
        }

        public double getWeight_delta() {
            return weight_delta;
        }

        public void setWeight_delta(double weight_delta) {
            this.weight_delta = weight_delta;
        }
        
        

        public double getWeight() {
            return weight;
        }
        
        public void setWeight(double weight) {
            this.weight = weight;
        }        

        public Node getNext() {
            return next;
        }
        
        
        
    }
    
    
    
    public void addNext(double weight, Node node) {
        nextLayer.add(new NextNode(weight, node));
    } 

    public List<NextNode> getNextLayer() {
        return nextLayer;
    }
    
    
   
}
