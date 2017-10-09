package TPR_LAB_2;

import Jama.Matrix;

import java.util.*;

class Main {
    public static void main(String[] args) {
        double[][] matr1 = new double[][] {
          new double[]{1.00, 1/7f, 1/3f, 1/5f, 1/9f},
          new double[]{7.00, 1.00, 3.00, 1/2f, 1/3f},
          new double[]{3.00, 1/3f, 1.00, 1/4f, 1/7f},
          new double[]{5.00, 2.00, 4.00, 1.00, 1/2f},
          new double[]{9.00, 3.00, 7.00, 2.00, 1.00},
        };


        double[][] matr2 = new double[][] {
                new double[]{1.00, 1/3f, 3.00},
                new double[]{3.00, 1.00, 5.00},
                new double[]{1/3f, 1/5f, 1.00},
        };

        double[][] matr3 = new double[][] {
                new double[]{1.00, 1/2f, 1/3f},
                new double[]{2.00, 1.00, 1/2f},
                new double[]{3.00, 2.00, 1.00},
        };

        double[][] matr4 = new double[][] {
                new double[]{1.00, 1.00, 3.00},
                new double[]{1.00, 1.00, 2.00},
                new double[]{1/3f, 1/2f, 1.00},
        };

        double[][] matr5 = new double[][] {
                new double[]{1.00, 1.00, 1/2f},
                new double[]{1.00, 1.00, 1/2f},
                new double[]{2.00, 2.00, 1.00},
        };

        double[][] matr6 = new double[][] {
                new double[]{1.00, 1/2f, 1/2f},
                new double[]{2.00, 1.00, 1/2f},
                new double[]{2.00, 2.00, 1.00},
        };

        MAI mai = new MAI();
        List<MAIValue> solve = mai.solve(new Matrix[]{
                new Matrix(matr1), new Matrix(matr2), new Matrix(matr3),
                new Matrix(matr4), new Matrix(matr5), new Matrix(matr6)
        });

        for (MAIValue aSolve : solve) {
            System.out.println("lambdaMax: " + aSolve.getLambda());
            System.out.println("ИС: " + aSolve.getIS());
            System.out.println("СИ: " + aSolve.getSI());
            System.out.println("ОС: " + aSolve.getOS());

            System.out.println();
        }
        System.out.println();
        System.out.println();

        Double[] weight9 = mai.getWeight9(solve);
        for (int i = 0; i < weight9.length; ++i) {
            System.out.println("Z" + (i + 1) + ": " + weight9[i]);
        }

        System.out.println("Согласованность: " + mai.getSogl(solve, weight9));
    }

}