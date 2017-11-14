package TPR_LAB_4;

import Jama.Matrix;

import java.math.BigDecimal;
import java.text.NumberFormat;

class Main {
    public static void main(String[] args) {
        double[][] matr1 = new double[][]{
                new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.034577309, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.174860278, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.075799126, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.268721123, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.446042164, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0, 0.258284994, 0.163424119, 0.443429114, 0.25, 0.122020192, 1, 0, 0},
                new double[]{0, 0.636985572, 0.296961331, 0.387371012, 0.25, 0.229650794, 0, 1, 0},
                new double[]{0, 0.104729434, 0.53961455, 0.169199874, 0.5, 0.648329014, 0, 0, 1},
        };

        double[][] matr2 = new double[][]{
                new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.034577309, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.174860278, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.075799126, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.268721123, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0.446042164, 0, 0, 0, 0, 0, 0, 0, 0},
                new double[]{0, 0.258284994, 0.163424119, 0.443429114, 0.25, 0.122020192, 1, 0, 0},
                new double[]{0, 0.636985572, 0.296961331, 0.387371012, 0.25, 0.229650794, 0, 1, 0},
                new double[]{0, 0.104729434, 0.53961455, 0.169199874, 0.5, 0.648329014, 0, 0, 1},
        };

        Matrix m1 = new Matrix(matr1);
        Matrix m2 = new Matrix(matr2);

        for (int i = 0; i < 2; ++i) {
            m2 = m2.times(m1);
        }
        m2.print(NumberFormat.getInstance(), 12);

        double sum = 0;
        for (int i = 0; i < 9; ++i) {
            sum += m2.get(i, 0);
        }
        for (int i = 0; i < 9; ++i) {
            BigDecimal bigDecimal = new BigDecimal(m2.get(i, 0) / sum);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            System.out.println(bigDecimal);
        }
    }

}