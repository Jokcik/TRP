package TPR_LAB_2;

import Jama.Matrix;

public class MAIValue {
    private double lambda;
    private Matrix vector;
    private double IS;
    private double SI = 1.12;
    private double OS;

    double getLambda() {
        return lambda;
    }

    void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public Matrix getVector() {
        return vector;
    }

    void setVector(Matrix vector) {
        this.vector = vector;
    }

    void update() {
        if (vector.getRowDimension() == 3) {
            SI = 0.58;
        }

        this.IS = (lambda - vector.getRowDimension()) / (vector.getRowDimension() - 1);
        this.OS = IS / SI;
    }

    double getIS() {
        return IS;
    }

    double getSI() { return SI; }

    double getOS() {
        return OS;
    }
}
