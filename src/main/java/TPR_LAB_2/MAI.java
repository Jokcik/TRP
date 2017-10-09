package TPR_LAB_2;

import Jama.Matrix;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class MAI {

    List<MAIValue> solve(Matrix[] matrices) {
        List<Entry<Double, Matrix>> entries = new ArrayList<>();
        Arrays.stream(matrices).forEach(matrix -> entries.add(getEigenvalueMatrix(matrix)));

        List<MAIValue> mais = entries.stream().map(doubleMatrixEntry -> {
           MAIValue value = new MAIValue();
           value.setLambda(doubleMatrixEntry.getKey());
           value.setVector(doubleMatrixEntry.getValue());
           value.update();

           return value;
        }).collect(Collectors.toList());

        if (matrices.length == 6) {
            getWeight9(mais);
        }

        return mais;
    }

    double getSogl(List<MAIValue> maiValues, Double[] weights) {
        double IS = 0;
        for (int i = 0; i < maiValues.size(); ++i) {
            IS += maiValues.get(i).getIS() * weights[i];
        }

        double SI = 0;
        for (int i = 0; i < maiValues.size(); ++i) {
            SI += maiValues.get(i).getSI() * weights[i];
        }

        return IS / SI;
    }

    Double[] getWeight9(List<MAIValue> maiValues) {
        Double[] weights = new Double[9];
        weights[0] = 1.0;

        weights[1] = weights[0] * maiValues.get(0).getVector().get(0, 0);
        weights[2] = weights[0] * maiValues.get(0).getVector().get(1, 0);
        weights[3] = weights[0] * maiValues.get(0).getVector().get(2, 0);
        weights[4] = weights[0] * maiValues.get(0).getVector().get(3, 0);
        weights[5] = weights[0] * maiValues.get(0).getVector().get(4, 0);

        weights[6] = 0.0;
        for (int i = 1; i <= 5; ++i) {
            weights[6] += weights[i] * maiValues.get(i).getVector().get(0, 0);
        }

        weights[7] = 0.0;
        for (int i = 1; i <= 5; ++i) {
            weights[7] += weights[i] * maiValues.get(i).getVector().get(1, 0);
        }

        weights[8] = 0.0;
        for (int i = 1; i <= 5; ++i) {
            weights[8] += weights[i] * maiValues.get(i).getVector().get(2, 0);
        }

        return weights;
    }

    private Entry<Double, Matrix> getEigenvalueMatrix(Matrix matrix) {
        Matrix matrixOne = matrix.copy();
        Matrix vector = new Matrix(matrixOne.getRowDimension(), 1,1);
        List<Matrix> eigenvector = new ArrayList<>();

        int k = 0;
        while (k < 4 || vector.transpose().times(absMatrix(eigenvector.get(k - 1).minus(eigenvector.get(k - 2)))).get(0, 0) > 0.01) {

            Matrix times = matrix.times(vector);
            double times1 = vector.transpose().times(matrix).times(vector).get(0, 0);

            eigenvector.add(times.times(1/ times1));

            matrix = matrix.times(matrixOne);
            k += 1;
        }

        double v = vector.transpose().times(matrixOne).times(eigenvector.get(eigenvector.size() - 1)).get(0, 0);
        return new SimpleEntry<>(v, eigenvector.get(eigenvector.size() - 1));
    }


    private Matrix absMatrix(Matrix m) {
        for (int i = 0; i < m.getRowDimension(); ++i) {
            for (int j = 0; j < m.getColumnDimension(); ++j) {
                m.set(0, 0, Math.abs(m.get(0, 0)));
            }
        }

        return m;
    }
}
