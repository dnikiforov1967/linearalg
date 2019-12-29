/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linearalg.matrix;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author dima
 */
public class NetworkTest {

    @Test
    public void testNetwork() {

        double EX = 0;
        double speed = 1;

        List<List<Node>> net = new ArrayList<>();
        List<Node> layer0 = new ArrayList<>(3);
        layer0.add(new Node());
        layer0.add(new Node());
        layer0.add(new Node());
        List<Node> layer1 = new ArrayList<>(2);
        layer1.add(new Node());
        layer1.add(new Node());
        List<Node> layer2 = new ArrayList<>(1);
        layer2.add(new Node());

        layer0.get(0).addNext(0.79, layer1.get(0));
        layer0.get(0).addNext(0.85, layer1.get(1));
        layer0.get(1).addNext(0.44, layer1.get(0));
        layer0.get(1).addNext(0.43, layer1.get(1));
        layer0.get(2).addNext(0.43, layer1.get(0));
        layer0.get(2).addNext(0.29, layer1.get(1));

        layer1.get(0).addNext(0.5, layer2.get(0));
        layer1.get(1).addNext(0.52, layer2.get(0));

        net.add(layer0);
        net.add(layer1);
        net.add(layer2);

        final Node neuron0 = layer0.get(0);
        final Node neuron1 = layer0.get(1);
        final Node neuron2 = layer0.get(2);
        for (int i = 1; i <= 8000; i++) {
            neuron0.resetIncome();
            neuron1.resetIncome();
            neuron2.resetIncome();

            if (i % 8 == 1) {
                neuron0.addIncome(0);
                neuron1.addIncome(0);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 2) {
                neuron0.addIncome(0);
                neuron1.addIncome(0);
                neuron2.addIncome(1);
                EX = 1;
            } else if (i % 8 == 3) {
                neuron0.addIncome(0);
                neuron1.addIncome(1);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 4) {
                neuron0.addIncome(0);
                neuron1.addIncome(1);
                neuron2.addIncome(1);
                EX = 0;
            } else if (i % 8 == 5) {
                neuron0.addIncome(1);
                neuron1.addIncome(0);
                neuron2.addIncome(0);
                EX = 1;
            } else if (i % 8 == 6) {
                neuron0.addIncome(1);
                neuron1.addIncome(0);
                neuron2.addIncome(1);
                EX = 1;
            } else if (i % 8 == 7) {
                neuron0.addIncome(1);
                neuron1.addIncome(1);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 0) {
                neuron0.addIncome(1);
                neuron1.addIncome(1);
                neuron2.addIncome(0);
                EX = 0;
            }

            final double output = direct(net);
            //System.out.println(output);
            reverse(net, EX, speed);
        }

        for (int i = 1; i <= 8; i++) {
            neuron0.resetIncome();
            neuron1.resetIncome();
            neuron2.resetIncome();

            if (i % 8 == 1) {
                neuron0.addIncome(0);
                neuron1.addIncome(0);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 2) {
                neuron0.addIncome(0);
                neuron1.addIncome(0);
                neuron2.addIncome(1);
                EX = 1;
            } else if (i % 8 == 3) {
                neuron0.addIncome(0);
                neuron1.addIncome(1);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 4) {
                neuron0.addIncome(0);
                neuron1.addIncome(1);
                neuron2.addIncome(1);
                EX = 0;
            } else if (i % 8 == 5) {
                neuron0.addIncome(1);
                neuron1.addIncome(0);
                neuron2.addIncome(0);
                EX = 1;
            } else if (i % 8 == 6) {
                neuron0.addIncome(1);
                neuron1.addIncome(0);
                neuron2.addIncome(1);
                EX = 1;
            } else if (i % 8 == 7) {
                neuron0.addIncome(1);
                neuron1.addIncome(1);
                neuron2.addIncome(0);
                EX = 0;
            } else if (i % 8 == 0) {
                neuron0.addIncome(1);
                neuron1.addIncome(1);
                neuron2.addIncome(1);
                EX = 1;
            }

            final double output = direct(net);
            System.out.println((output>=0.5 ? true : false) + " : " + (EX==0 ? false : true));

        }

    }

    public void reverse(List<List<Node>> net, double EX, double speed) {
        List<Node> layer0 = net.get(0);
        List<Node> layer1 = net.get(1);
        List<Node> layer2 = net.get(2);

        //Calc Delta
        for (Node n : layer2) {
            n.calcDelta(EX);
        }
        for (Node n : layer1) {
            n.calcDelta(EX);
        }

        for (Node node : layer1) {
            final List<Node.NextNode> nextLayer = node.getNextLayer();
            final double income = node.getIncome();
            double oi = Node.result(income);
            for (Node.NextNode next : nextLayer) {
                final Node nodeI = next.getNext();
                final double deltaj = nodeI.getDelta();
                double valij = -speed * deltaj * oi;
                next.setWeight_delta(valij);
            }
        }

        for (Node node : layer0) {
            final List<Node.NextNode> nextLayer = node.getNextLayer();
            final double oi = node.getIncome();
            for (Node.NextNode next : nextLayer) {
                final Node nodeJ = next.getNext();
                final double deltaj = nodeJ.getDelta();
                final double valij = -speed * deltaj * oi;
                next.setWeight_delta(valij);
            }
        }
    }

    public double direct(List<List<Node>> net) {
        List<Node> layer0 = net.get(0);
        List<Node> layer1 = net.get(1);
        List<Node> layer2 = net.get(2);
        for (Node n : layer1) {
            n.resetIncome();
        }
        for (Node n : layer2) {
            n.resetIncome();
        }

        for (Node n : layer0) {
            final List<Node.NextNode> nextLayer = n.getNextLayer();
            final double income = n.getIncome();
            for (Node.NextNode next : nextLayer) {
                final double weight = next.getWeight() + next.getWeight_delta();
                //System.out.println("Weight "+weight);
                next.setWeight_delta(0);
                next.setWeight(weight);
                final Node nextNode = next.getNext();
                nextNode.addIncome(income * weight);
            }
        }

        for (Node n : layer1) {
            final List<Node.NextNode> nextLayer = n.getNextLayer();
            final double income = n.getIncome();
            final double result = Node.result(income);
            for (Node.NextNode next : nextLayer) {
                final double weight = next.getWeight() + next.getWeight_delta();
                //System.out.println("Weight "+weight);
                next.setWeight_delta(0);
                next.setWeight(weight);
                final Node nextNode = next.getNext();
                nextNode.addIncome(result * weight);
            }
        }
        final double income = net.get(2).get(0).getIncome();
        return Node.result(income);
    }

}
