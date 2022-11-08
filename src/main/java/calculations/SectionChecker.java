package calculations;

import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class SectionChecker extends ParameterSaver {

    ArrayList<Double> bindingMomentsSummary = new ArrayList<>();
    ArrayList<Double> bindingMomentsFirst = new ArrayList<>();
    ArrayList<Double> bindingMomentsSecond = new ArrayList<>();
    ArrayList<Double> bindingMomentsThird = new ArrayList<>();
    ArrayList<Double> bindingMomentsFourth = new ArrayList<>();
    ArrayList<Double> bindingMomentsFifth = new ArrayList<>();
    ArrayList<Double> bindingMomentsConsole = new ArrayList<>();

    ArrayList<Double> transverseForcesSummary = new ArrayList<>();
    ArrayList<Double> transverseForcesFirst = new ArrayList<>();
    ArrayList<Double> transverseForcesSecond = new ArrayList<>();
    ArrayList<Double> transverseForcesThird = new ArrayList<>();
    ArrayList<Double> transverseForcesFourth = new ArrayList<>();
    ArrayList<Double> transverseForcesFifth = new ArrayList<>();
    ArrayList<Double> transverseForcesConsole = new ArrayList<>();

    public ArrayList<Double> getBindingMomentsSummary() {
        return bindingMomentsSummary;
    }

    public void setBindingMomentsSummary(ArrayList<Double> bindingMomentsSummary) {
        this.bindingMomentsSummary = bindingMomentsSummary;
    }

    public ArrayList<Double> getBindingMomentsFirst() {
        return bindingMomentsFirst;
    }

    public void setBindingMomentsFirst(ArrayList<Double> bindingMomentsFirst) {
        this.bindingMomentsFirst = bindingMomentsFirst;
    }

    public ArrayList<Double> getBindingMomentsSecond() {
        return bindingMomentsSecond;
    }

    public void setBindingMomentsSecond(ArrayList<Double> bindingMomentsSecond) {
        this.bindingMomentsSecond = bindingMomentsSecond;
    }

    public ArrayList<Double> getBindingMomentsThird() {
        return bindingMomentsThird;
    }

    public void setBindingMomentsThird(ArrayList<Double> bindingMomentsThird) {
        this.bindingMomentsThird = bindingMomentsThird;
    }

    public ArrayList<Double> getBindingMomentsFourth() {
        return bindingMomentsFourth;
    }

    public void setBindingMomentsFourth(ArrayList<Double> bindingMomentsFourth) {
        this.bindingMomentsFourth = bindingMomentsFourth;
    }

    public ArrayList<Double> getBindingMomentsFifth() {
        return bindingMomentsFifth;
    }

    public void setBindingMomentsFifth(ArrayList<Double> bindingMomentsFifth) {
        this.bindingMomentsFifth = bindingMomentsFifth;
    }

    public ArrayList<Double> getBindingMomentsConsole() {
        return bindingMomentsConsole;
    }

    public void setBindingMomentsConsole(ArrayList<Double> bindingMomentsConsole) {
        this.bindingMomentsConsole = bindingMomentsConsole;
    }

    public ArrayList<Double> getTransverseForcesSummary() {
        return transverseForcesSummary;
    }

    public void setTransverseForcesSummary(ArrayList<Double> transverseForcesSummary) {
        this.transverseForcesSummary = transverseForcesSummary;
    }

    public ArrayList<Double> getTransverseForcesFirst() {
        return transverseForcesFirst;
    }

    public void setTransverseForcesFirst(ArrayList<Double> transverseForcesFirst) {
        this.transverseForcesFirst = transverseForcesFirst;
    }

    public ArrayList<Double> getTransverseForcesSecond() {
        return transverseForcesSecond;
    }

    public void setTransverseForcesSecond(ArrayList<Double> transverseForcesSecond) {
        this.transverseForcesSecond = transverseForcesSecond;
    }

    public ArrayList<Double> getTransverseForcesThird() {
        return transverseForcesThird;
    }

    public void setTransverseForcesThird(ArrayList<Double> transverseForcesThird) {
        this.transverseForcesThird = transverseForcesThird;
    }

    public ArrayList<Double> getTransverseForcesFourth() {
        return transverseForcesFourth;
    }

    public void setTransverseForcesFourth(ArrayList<Double> transverseForcesFourth) {
        this.transverseForcesFourth = transverseForcesFourth;
    }

    public ArrayList<Double> getTransverseForcesFifth() {
        return transverseForcesFifth;
    }

    public void setTransverseForcesFifth(ArrayList<Double> transverseForcesFifth) {
        this.transverseForcesFifth = transverseForcesFifth;
    }

    public ArrayList<Double> getTransverseForcesConsole() {
        return transverseForcesConsole;
    }

    public void setTransverseForcesConsole(ArrayList<Double> transverseForcesConsole) {
        this.transverseForcesConsole = transverseForcesConsole;
    }

    public void calculateMomentsNoMomentNoConsole(double accuracy, double a, double c, double L, double M, double q, double N, ArrayList<Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ra = (N * (L - a) + q * L * L / 2 - M) / L;
        double Rb = (N * a + q * L * L / 2 + M) / L;

        for (int i = 1, j = 0; j < accuracy; i++, j++) {
            double qLoad = q * L * i * L * i / sqrtAccuracy / 2;
            double nLoad = N * (L * i / accuracy - a);
            double raLoad = Ra * L * i / accuracy;

            if (j == 0) {
                arrayList.add(0, 0.0);
            } else if (c > (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (raLoad - qLoad));
            } else if (c > (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(j, (raLoad - qLoad - nLoad));
            } else if (c <= (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (raLoad - qLoad + M));
            } else
                arrayList.add(j, (raLoad - qLoad + M - nLoad));
        }
        //System.out.println(Ra + " " + Rb);
    }

    public void calculateMomentsNoMomentWithConsole(double accuracy, double a, double c, double L, double M, double q, double N, double ak, double ck, double Lk, double Mk, double qk, double Nk, ArrayList<Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ra = (N * (L - a) + q * L * L / 2 - M - Mk - qk * Lk * Lk / 2 - Nk * ak) / L;
        double Rb = (N * a + q * L * L / 2 + M + Mk + qk * Lk * (L + Lk / 2) + Nk * (L + ak)) / L;

        for (int i = 1, j = 0; i < accuracy * 2; i++) {
            double raLoad = Ra * L * i / accuracy;
            double qLoad = q * L * i * L * i / sqrtAccuracy / 2;
            double nLoad = N * (L * i / accuracy - a);
            double ra1Load = Ra * (L + Lk * j / accuracy);
            double q1Load = q * L * ((L + Lk * j / accuracy) / 2);
            double n1Load = N * (L - a + Lk * j / accuracy);
            double rbLoad = Rb * Lk * j / accuracy;
            double qkLoad = qk * Lk * j * Lk * j / sqrtAccuracy / 2;
            double nkLoad = Nk * (Lk * j / accuracy - ak);

            if (j == 0) {
                arrayList.add(0, 0.0);
                j++;
                i--;
            } else if (L * i / accuracy < L && c > (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(i, (raLoad - qLoad));
            } else if (L * i / accuracy < L && c > (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(i, (raLoad - qLoad - nLoad));
            } else if (L * i / accuracy < L && c <= (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(i, (raLoad - qLoad + M));
            } else if (L * i / accuracy < L && c <= (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(i, (raLoad - qLoad + M - nLoad));

            } else if (ck > Lk * j / accuracy && ak > (Lk * j / accuracy)) {
                arrayList.add(i, (ra1Load - q1Load + M - n1Load + rbLoad - qkLoad));
                j++;
            } else if (ck > Lk * j / accuracy && ak <= (Lk * j / accuracy)) {
                arrayList.add(i, (ra1Load - q1Load + M - n1Load + rbLoad - qkLoad - nkLoad));
                j++;
            } else if (ck <= Lk * j / accuracy && ak > (Lk * j / accuracy)) {
                arrayList.add(i, (ra1Load - q1Load + M - n1Load + rbLoad - qkLoad + Mk));
                j++;
            } else if (ck <= Lk * j / accuracy && ak <= (Lk * j / accuracy)) {
                arrayList.add(i, (ra1Load - q1Load + M - n1Load + rbLoad - qkLoad + Mk - nkLoad));
                j++;
            }
        }
        //System.out.println(Ra + " " + Rb);
    }

    public void calculateMomentsNoConsole(double accuracy, double a, double c, double L, double M, double Mx1, double Mx2, double q, double N, ArrayList<Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ra = (N * (L - a) + q * L * L / 2 - M - Mx1 - Mx2) / L;
        double Rb = (N * a + q * L * L / 2 + M + Mx1 + Mx2) / L;
        double scale = Math.pow(10, 2);

        for (int i = 1, j = 0; j < accuracy; i++, j++) {
            double qLoad = q * L * i * L * i / sqrtAccuracy / 2;
            double nLoad = N * (L * i / accuracy - a);
            double raLoad = Ra * L * i / accuracy;

            if (j == 0) {
                arrayList.add(0, 0.0);
            } else if (c > (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad + Mx1) * scale) / scale));
            } else if (c > (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad - nLoad + Mx1) * scale) / scale));
            } else if (c <= (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad + M + Mx1) * scale) / scale));
            } else
                arrayList.add(j, (Math.ceil((raLoad - qLoad + M - nLoad + Mx1 + Mx2) * scale) / scale));
        }
    }

    public void calculateMomentsOnlyConsole(double accuracy, double a, double c, double L, double M, double q, double N, ArrayList<Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ma = -N * a - q * L * L / 2 - M;
        double Ra = (N * (L - a) + q * L * L / 2 - M - Ma) / L;
        double scale = Math.pow(10, 2);

        for (int i = 1, j = 0; j < accuracy; i++, j++) {
            double qLoad = q * L * i * L * i / sqrtAccuracy / 2;
            double nLoad = N * (L * i / accuracy - a);
            double raLoad = Ra * L * i / accuracy;

            if (j == 0) {
                arrayList.add(0, 0.0);
            } else if (c > (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad + Ma) * scale) / scale));
            } else if (c > (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad - nLoad + Ma) * scale) / scale));
            } else if (c <= (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((raLoad - qLoad + M + Ma) * scale) / scale));
            } else
                arrayList.add(j, (Math.ceil((raLoad - qLoad + M - nLoad + Ma) * scale) / scale));
        }
    }

    public void calculateForcesOnlyConsole(double accuracy, double a, double L, double M, double q, double N, ArrayList<Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ma = -N * a - q * L * L / 2 - M;
        double Ra = (N * (L - a) + q * L * L / 2 - M - Ma) / L;
        double scale = Math.pow(10, 2);

        for (int i = 1, j = 0; j < accuracy; i++, j++) {
            double qLoad = q * L * i / sqrtAccuracy;

            if (a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((Ra - qLoad) * scale) / scale));
            } else if (a <= (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((Ra - qLoad - N) * scale) / scale));
            }
        }
    }

    public void calculateMomentsWithConsole(double accuracy, double a, double c, double L, double M, double Mx1,
                                            double Mx2, double q, double N, double ak, double ck, double Lk, double Mk, double qk, double Nk, ArrayList<
            Double> arrayList) {
        arrayList.clear();
        double sqrtAccuracy = accuracy * accuracy;

        double Ra = (N * (L - a) + q * L * L / 2 - M - Mx1 - Mx2 - Mk - qk * Lk * Lk / 2 - Nk * ak) / L;
        double Rb = (N * a + q * L * L / 2 + M + Mx1 + Mx2 + Mk + qk * Lk * (L + Lk / 2) + Nk * (L + ak)) / L;
        double scale = Math.pow(10, 2);

        for (int i = 0, j = 1; i < accuracy * 2; i++) {
            double raLoad = Ra * L * i / accuracy;
            double qLoad = q * L * i * L * i / sqrtAccuracy / 2;
            double nLoad = N * (L * i / accuracy - a);
            double ra1Load = Ra * (L + Lk * j / accuracy);
            double q1Load = q * L * ((L + Lk * j / accuracy) / 2);
            double n1Load = N * (L - a + Lk * j / accuracy);
            double rbLoad = Rb * Lk * j / accuracy;
            double qkLoad = qk * Lk * j * Lk * j / sqrtAccuracy / 2;
            double nkLoad = Nk * (Lk * j / accuracy - ak);

            if (L * i / accuracy < L && c > (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((raLoad - qLoad + Mx1) * scale) / scale));
            } else if (L * i / accuracy < L && c > (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((raLoad - qLoad - nLoad + Mx1) * scale) / scale));
            } else if (L * i / accuracy < L && c <= (L * i / accuracy) && a > (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((raLoad - qLoad + M + Mx1) * scale) / scale));
            } else if (L * i / accuracy < L && c <= (L * i / accuracy) && a <= (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((raLoad - qLoad + M - nLoad + Mx1 + Mx2) * scale) / scale));

            } else if (ck > Lk * j / accuracy && ak > (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((ra1Load - q1Load + M - n1Load + rbLoad - qkLoad + Mx1 + Mx2) * scale) / scale));
                j++;
            } else if (ck > Lk * j / accuracy && ak <= (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((ra1Load - q1Load + M - n1Load + rbLoad - qkLoad - nkLoad + Mx1 + Mx2) * scale) / scale));
                j++;
            } else if (ck <= Lk * j / accuracy && ak > (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((ra1Load - q1Load + M - n1Load + rbLoad - qkLoad + Mk + Mx1 + Mx2) * scale) / scale));
                j++;
            } else if (ck <= Lk * j / accuracy && ak <= (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((ra1Load - q1Load + M - n1Load + rbLoad - qkLoad + Mk - nkLoad + Mx1 + Mx2) * scale) / scale));
                j++;
            }
        }
    }

    public void calculateForcesNoConsole(double accuracy, double a, double L, double M, double Mx1, double Mx2,
                                         double q, double N, ArrayList<Double> arrayList) {
        arrayList.clear();

        double Ra = (N * (L - a) + q * L * L / 2 - M - Mx1 - Mx2) / L;
        double Rb = (N * a + q * L * L / 2 + M + Mx1 + Mx2) / L;
        double scale = Math.pow(10, 2);

        for (int i = 1, j = 0; j < accuracy; i++, j++) {

            double qLoad = q * L * i / accuracy;

            if (a > (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((Ra - qLoad) * scale) / scale));
            } else if (a <= (L * i / accuracy)) {
                arrayList.add(j, (Math.ceil((Ra - qLoad - N) * scale) / scale));
            }
        }
    }

    public void calculateForcesWithConsole(double accuracy, double a, double L, double M, double Mx1, double Mx2,
                                           double q, double N, double ak, double Lk, double Mk, double qk, double Nk, ArrayList<Double> arrayList) {
        arrayList.clear();

        double Ra = (N * (L - a) + q * L * L / 2 - M - Mx1 - Mx2 - Mk - qk * Lk * Lk / 2 - Nk * ak) / L;
        double Rb = (N * a + q * L * L / 2 + M + Mx1 + Mx2 + Mk + qk * Lk * (L + Lk / 2) + Nk * (L + ak)) / L;
        double scale = Math.pow(10, 2);

        for (int i = 0, j = 1; i < accuracy * 2; i++) {
            double qLoad = q * L * i / accuracy;
            double qkLoad = qk * Lk * j / accuracy;

            if (L * i / accuracy < L && a > (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((Ra - qLoad) * scale) / scale));
            } else if (L * i / accuracy < L && a <= (L * i / accuracy)) {
                arrayList.add(i, (Math.ceil((Ra - qLoad - N) * scale) / scale));

            } else if (ak > (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((Ra - qLoad - N + Rb - qkLoad) * scale) / scale));
                j++;
            } else if (ak <= (Lk * j / accuracy)) {
                arrayList.add(i, (Math.ceil((Ra - qLoad - N + Rb - qkLoad - Nk) * scale) / scale));
                j++;
            }
        }
    }

    public void fillMomentDiagram() {
        bindingMomentsSummary.clear();
        bindingMomentsSummary.addAll(bindingMomentsFirst);
        bindingMomentsSummary.addAll(bindingMomentsSecond);
        bindingMomentsSummary.addAll(bindingMomentsThird);
        bindingMomentsSummary.addAll(bindingMomentsFourth);
        bindingMomentsSummary.addAll(bindingMomentsFifth);
        bindingMomentsSummary.addAll(bindingMomentsConsole);
        bindingMomentsSummary.add(0.0);
    }

    public void fillForceDiagram() {
        transverseForcesSummary.clear();
        transverseForcesSummary.addAll(transverseForcesFirst);
        transverseForcesSummary.addAll(transverseForcesSecond);
        transverseForcesSummary.addAll(transverseForcesThird);
        transverseForcesSummary.addAll(transverseForcesFourth);
        transverseForcesSummary.addAll(transverseForcesFifth);
        transverseForcesSummary.addAll(transverseForcesConsole);
        transverseForcesSummary.add(0.0);
    }

    public double findBiggest(ArrayList<Double> arrayList) {
        double maxMoment = 0;
        for (double i : arrayList
        ) {
            if (Math.abs(i) > Math.abs(maxMoment)) {
                maxMoment = i;
            }
        }
        return maxMoment;
    }

    //TODO Подумать над влиянием момента и силы на нагрузку и выбором сочетаний нагрузок!

    public void findAndSetExtremeValues(ArrayList<Double> moments, ArrayList<Double> forces) {
        int j = 0;
        int k = 0;
        for (double i : moments
        ) {
            if ((Math.abs(i) >= Math.abs(compressingMoment)) && i < 0) {
                setCompressingMoment(i);
            } else if ((i > stretchingMoment) && i > 0) {
                setStretchingMoment(i);
            }
        }
        for (double i : forces
        ) {
            if ((Math.abs(i) >= Math.abs(compressingForce)) && i < 0) {
                setCompressingForce(i);
            } else if ((i > stretchingForce) && i > 0) {
                setStretchingForce(i);
            }
        }
    }

    public void fictiveB1() {
        setBf1(findBiggest(bindingMomentsFirst) * getL1() / 4);
    }

    public void fictiveB2andA2() {
        setBf2(findBiggest(bindingMomentsSecond) * getL1() / 4);
        setAf2(findBiggest(bindingMomentsSecond) * getL1() / 4);
    }

    public void fictiveB3andA3() {
        setBf3(findBiggest(bindingMomentsThird) * getL1() / 4);
        setAf3(findBiggest(bindingMomentsThird) * getL1() / 4);
    }

    public void fictiveB4andA4() {
        setBf4(findBiggest(bindingMomentsFourth) * getL1() / 4);
        setAf4(findBiggest(bindingMomentsFourth) * getL1() / 4);
    }

    public void missingMomentsCalculationOne(RadioButton button) {
        fictiveB1();

        if (button.isSelected()) {
            setMx0(-6 * getBf1());
        }
    }

    public void missingMomentsCalculationTwo(RadioButton button) {
        fictiveB1();

        double x1 = 2 * (getL1() + getL2());
        double x5 = -6 * getBf1();

        if (button.isSelected()) {
            setMx0(-6 * getBf1());
        }
        setMx1(x5 / 2 / x1);
    }

    public void missingMomentsCalculationThree(RadioButton button) {
        fictiveB1();
        fictiveB2andA2();

        double x1 = 2 * (getL1() + getL2());
        double x2 = 2 * (getL2() + getL3());
        double x5 = -6 * (getBf1() + getAf2());
        double x6 = -6 * getBf2();

        double y1 = 2 * x2 * x5 / getL2();
        double y2 = 4 * x1 * x2 / getL2();

        if (button.isSelected()) {
            setMx0(-6 * getBf1());
        }
        setMx1((x6 - y1) / (getL2() - y2));
        setMx2((x5 - getMx1() * 2 * x1) / getL2());
    }

    public void missingMomentsCalculationFour(RadioButton button) {
        fictiveB1();
        fictiveB2andA2();
        fictiveB3andA3();

        double x1 = 2 * (getL1() + getL2());
        double x2 = 2 * (getL2() + getL3());
        double x3 = 2 * (getL3() + getL4());
        double x5 = -6 * (getBf1() + getAf2());
        double x6 = -6 * (getBf2() + getAf3());
        double x7 = -6 * getBf3();

        double y1 = getL3() * x5 / getL2();
        double y2 = 2 * x1 * getL3() / getL2();
        double y3 = 2 * x3 * x6 / getL3();
        double y4 = 2 * x3 * getL2() / getL3();
        double y5 = 4 * x2 * x3 * x5 / getL2() / getL3();
        double y6 = 8 * x1 * x2 * x3 / getL2() / getL3();

        if (button.isSelected()) {
            setMx0(-6 * getBf1());
        }
        setMx1((x7 - y1 - y3 + y5) / (y6 - y2 - y4));
        setMx2((x5 - getMx1() * 2 * x1) / getL2());
        setMx3((x6 - 2 * x2 * getMx2() - getL2() * getMx1()) / getL3());
    }

    public void missingMomentsCalculationFive(RadioButton button) {
        fictiveB1();
        fictiveB2andA2();
        fictiveB3andA3();
        fictiveB4andA4();

        double x1 = 2 * (getL1() + getL2());
        double x2 = 2 * (getL2() + getL3());
        double x3 = 2 * (getL3() + getL4());
        double x4 = 2 * (getL4() + getL5());
        double x5 = -6 * (getBf1() + getAf2());
        double x6 = -6 * (getBf2() + getAf3());
        double x7 = -6 * (getBf3() + getAf4());
        double x8 = -6 * getBf4();

        double y1 = getL3() * x5 / getL2();
        double y2 = 2 * x1 * getL3() / getL2();
        double y3 = 2 * x3 * x6 / getL3();
        double y4 = 2 * x3 * getL2() / getL3();
        double y5 = 4 * x2 * x3 * x5 / getL2() / getL3();
        double y6 = 8 * x1 * x2 * x3 / getL2() / getL3();
        double y7 = getL4() * x8 / 2 / x4;
        double y8 = getL4() * getL4() * x6 / 2 / getL3() / x4;
        double y9 = getL4() * getL4() * getL2() / 2 / getL3() / x4;
        double y10 = 2 * getL4() * getL4() * x2 * x5 / 2 / getL2() / getL3() / x4;
        double y11 = 4 * getL4() * getL4() * x2 * x1 / 2 / getL2() / getL3() / x4;

        if (button.isSelected()) {
            setMx0(-6 * getBf1());
        }
        setMx1((x7 - y1 - y3 + y5 - y7 + y8 - y10) / (y6 - y2 - y4 + y9 - y11));
        setMx2((x5 - getMx1() * 2 * x1) / getL2());
        setMx3((x6 - 2 * x2 * getMx2() - getL2() * getMx1()) / getL3());
        setMx4((x8 - getMx3() * getL4()) / 2 / x4);
    }

    public double lengthSummary() {
        return (getL1() + getL2() + getL3() + getL4() + getL5() + getLk());
    }

    /*TODO Если не происходит растяжения (момент меньше нуля в любой точке балки), то проверка прочности по нему
           для деревянных сечений даёт результатом бесконечный запас прочности! Это корректно, но нужно обработать покрасивей!*/

    public void durabilityForSteel(int shapeCase) {
        findAndSetExtremeValues(bindingMomentsSummary, transverseForcesSummary);
        double CompForce = getCompressingForce();
        double StrMoment = getStretchingMoment();
        double StrForce = getStretchingForce();
        double strRatio = 220000 * 1.3 / 0.87 / 1.05;
        double compRatio = 220000 * 1.3 * 0.58 / 1.05;
        //resistances.getStretchingResistance(), resistances.getCompressiveResistance()

        if (shapeCase == 1) {
            double diameter = getR() * 2;
            double smallRadius = getR() - getT() * 2;
            double smallDiameter = smallRadius * 2;
            double c = smallDiameter / diameter;
            double cQuadruple = Math.pow(c, 4);
            double diameterDouble = Math.pow(diameter, 2);
            double smallDiameterDouble = Math.pow(smallDiameter, 2);
            double diameterCube = Math.pow(diameter, 3);
            double diameterQuadruple = Math.pow(diameter, 4);
            double W = Math.PI * diameterCube * (1 - cQuadruple) / 32;
            double I = Math.PI * diameterQuadruple * (1 - cQuadruple) / 64;
            double S = Math.PI * getR() * (diameterDouble - smallDiameterDouble) / 2;

            double momentTenseDouble = Math.pow((Math.abs(StrMoment) / W), 2);
            double strForceTenseDouble = Math.pow((Math.abs(StrForce) * S / I / getT()), 2);
            double compForceTense = Math.abs(CompForce) * S / I / getT();

            setStretchingTense(strRatio / (Math.sqrt((momentTenseDouble + 3 * strForceTenseDouble))));
            setCompressingTense(compRatio / compForceTense);

        } else if (shapeCase == 2) {
            double thin = getT() * 2;
            double smallSide = getB() - thin;
            double sideDouble = Math.pow(getB(), 2);
            double smallSideDouble = Math.pow(smallSide, 2);
            double sideQuadruple = Math.pow(getB(), 4);
            double smallSideQuadruple = Math.pow(smallSide, 4);
            double W = (sideQuadruple - smallSideQuadruple) / (6 * getB());
            double I = (sideQuadruple - smallSideQuadruple) / 12;
            double S = getB() * (sideDouble - smallSideDouble) / 2;

            double momentTenseDouble = Math.pow((Math.abs(StrMoment) / W), 2);
            double strForceTenseDouble = Math.pow((Math.abs(StrForce) * S / I / getT()), 2);
            double compForceTense = Math.abs(CompForce) * S / I / getT();

            setStretchingTense(strRatio / (Math.sqrt((momentTenseDouble + 3 * strForceTenseDouble))));
            setCompressingTense(compRatio / compForceTense);

        } else if (shapeCase == 3) {
            double smallHeight = getH() - getT() * 2;
            double smallWidth = getB() - getT() * 2;
            double sideCube = Math.pow(getH(), 3);
            double smallSideCube = Math.pow(smallHeight, 3);
            double W = (getB() * sideCube - smallWidth * smallSideCube) / (6 * getH());
            double I = (getB() * sideCube - smallWidth * smallSideCube) / 12;
            double S = (getB() * getH() - smallWidth * smallHeight) * getH() / 2;

            double momentTenseDouble = Math.pow((Math.abs(StrMoment) / W), 2);
            double strForceTenseDouble = Math.pow((Math.abs(StrForce) * S / I / getT()), 2);
            double compForceTense = Math.abs(CompForce) * S / I / getT();

            setStretchingTense(strRatio / (Math.sqrt(momentTenseDouble + 3 * strForceTenseDouble)));
            setCompressingTense(compRatio / compForceTense);
        }
    }

    public void durabilityForWood(int shapeCase) {
        findAndSetExtremeValues(bindingMomentsSummary, transverseForcesSummary);
        double StrMoment = getStretchingMoment();
        double StrForce = getStretchingForce();
        double strRatio = 19500 * 0.53 * 1 * 1 * 1 / 1.2;
        double compRatio = 2400 * 0.53 * 1 * 1 * 1 / 1.2;

        if (shapeCase == 1) {
            double radiusCube = Math.pow(getR(), 3);
            double radiusQuadruple = Math.pow(getR(), 4);
            double W = Math.PI * radiusCube / 4;
            double I = Math.PI * radiusQuadruple / 4;
            double S = Math.PI * radiusCube / 2;

            double momentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getR() / 2;

            setStretchingTense(strRatio / momentTense);
            setCompressingTense(compRatio / strForceTense);

        } else if (shapeCase == 2) {
            double sideCube = Math.pow(getB(), 3);
            double sideQuadruple = Math.pow(getB(), 4);
            double W = sideCube / 6;
            double I = sideQuadruple / 12;
            double S = sideCube / 2;

            double momentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getB();

            setStretchingTense(strRatio / momentTense);
            setCompressingTense(compRatio / strForceTense);

        } else if (shapeCase == 3) {
            double sideCube = Math.pow(getH(), 3);
            double sideDouble = Math.pow(getH(), 2);
            double W = sideDouble * getB() / 6;
            double I = sideCube * getB() / 12;
            double S = sideDouble * getB() / 2;

            double momentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getB();

            setStretchingTense(strRatio / momentTense);
            setCompressingTense(compRatio / strForceTense);
        }
    }

    public void durabilityForConcrete(int shapeCase) {
        findAndSetExtremeValues(bindingMomentsSummary, transverseForcesSummary);
        double CompMoment = getCompressingMoment();
        double CompForce = getCompressingForce();
        double StrMoment = getStretchingMoment();
        double StrForce = getStretchingForce();
        double strRatio = 1100 / 1.3;
        double compRatio = 11000 * 0.9 / 1.3;

        if (shapeCase == 1) {
            double radiusCube = Math.pow(getR(), 3);
            double radiusQuadruple = Math.pow(getR(), 4);
            double W = Math.PI * radiusCube / 4;
            double I = Math.PI * radiusQuadruple / 4;
            double S = Math.PI * radiusCube / 2;

            double strMomentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getR() / 2;
            double compMomentTense = Math.abs(CompMoment) / W;
            double compForceTense = Math.abs(CompForce) * S / I / getR() / 2;


            setStretchingTense(strRatio / (strMomentTense + strForceTense));
            setCompressingTense(compRatio / (compMomentTense + compForceTense));

        } else if (shapeCase == 2) {
            double sideCube = Math.pow(getB(), 3);
            double sideQuadruple = Math.pow(getB(), 4);
            double W = sideCube / 6;
            double I = sideQuadruple / 12;
            double S = sideCube / 2;

            double strMomentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getB() / 2;
            double compMomentTense = Math.abs(CompMoment) / W;
            double compForceTense = Math.abs(CompForce) * S / I / getB() / 2;

            setStretchingTense(strRatio / (strMomentTense + strForceTense));
            setCompressingTense(compRatio / (compMomentTense + compForceTense));

        } else if (shapeCase == 3) {
            double sideCube = Math.pow(getH(), 3);
            double sideDouble = Math.pow(getH(), 2);
            double W = sideDouble * getB() / 6;
            double I = sideCube * getB() / 12;
            double S = sideDouble * getB() / 2;

            double strMomentTense = Math.abs(StrMoment) / W;
            double strForceTense = Math.abs(StrForce) * S / I / getH() / 2;
            double compMomentTense = Math.abs(CompMoment) / W;
            double compForceTense = Math.abs(CompForce) * S / I / getH() / 2;

            setStretchingTense(strRatio / (strMomentTense + strForceTense));
            setCompressingTense(compRatio / (compMomentTense + compForceTense));
        }
    }
}
