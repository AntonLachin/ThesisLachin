package calculations;

import calculations.model.MaterialsAndProperties;
import com.work.diploma.Connect;
import jakarta.persistence.EntityManager;

public class ParameterSaver {
    double M1 = 0, M2 = 0, M3 = 0, M4 = 0, M5 = 0, Mk = 0;
    double N1 = 0, N2 = 0, N3 = 0, N4 = 0, N5 = 0, Nk = 0;
    double q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, qk = 0;
    double a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, ak = 0;
    double c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, ck = 0;
    double L1, L2, L3, L4, L5, Lk;
    double h = 0, b = 0, t = 0, r = 0;

    MaterialsAndProperties resistances;

    double accuracy = 100;

    double stretchingMoment = 0;
    double compressingMoment = 0;
    double stretchingForce = 0;
    double compressingForce = 0;

    double stretchingTense = 0;
    double compressingTense = 0;

    double Bf1 = 0;
    double Bf2 = 0;
    double Bf3 = 0;
    double Bf4 = 0;
    double Af2 = 0;
    double Af3 = 0;
    double Af4 = 0;

    double Mx0 = 0;
    double Mx1 = 0;
    double Mx2 = 0;
    double Mx3 = 0;
    double Mx4 = 0;

    public void setResistances(int materialID) {
        EntityManager entityManager = Connect.getInstance().createEntityManager();
        entityManager.getTransaction().begin();
        resistances = entityManager.find(MaterialsAndProperties.class, materialID);
        entityManager.getTransaction().commit();
    }

    public double getStretchingTense() {
        return stretchingTense;
    }

    public void setStretchingTense(double stretchingTense) {
        this.stretchingTense = stretchingTense;
    }

    public double getCompressingTense() {
        return compressingTense;
    }

    public void setCompressingTense(double compressingTense) {
        this.compressingTense = compressingTense;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getStretchingMoment() {
        return stretchingMoment;
    }

    public void setStretchingMoment(double stretchingMoment) {
        this.stretchingMoment = stretchingMoment;
    }

    public double getCompressingMoment() {
        return compressingMoment;
    }

    public void setCompressingMoment(double compressingMoment) {
        this.compressingMoment = compressingMoment;
    }

    public double getStretchingForce() {
        return stretchingForce;
    }

    public void setStretchingForce(double stretchingForce) {
        this.stretchingForce = stretchingForce;
    }

    public double getCompressingForce() {
        return compressingForce;
    }

    public void setCompressingForce(double compressingForce) {
        this.compressingForce = compressingForce;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getMx0() {
        return Mx0;
    }

    public void setMx0(double mx0) {
        Mx0 = mx0;
    }

    public double getMx1() {
        return Mx1;
    }

    public void setMx1(double mx1) {
        Mx1 = mx1;
    }

    public double getMx2() {
        return Mx2;
    }

    public void setMx2(double mx2) {
        Mx2 = mx2;
    }

    public double getMx3() {
        return Mx3;
    }

    public void setMx3(double mx3) {
        Mx3 = mx3;
    }

    public double getMx4() {
        return Mx4;
    }

    public void setMx4(double mx4) {
        Mx4 = mx4;
    }

    public double getBf1() {
        return Bf1;
    }

    public void setBf1(double bf1) {
        Bf1 = bf1;
    }

    public double getBf2() {
        return Bf2;
    }

    public void setBf2(double bf2) {
        Bf2 = bf2;
    }

    public double getBf3() {
        return Bf3;
    }

    public void setBf3(double bf3) {
        Bf3 = bf3;
    }

    public double getBf4() {
        return Bf4;
    }

    public void setBf4(double bf4) {
        Bf4 = bf4;
    }

    public double getAf2() {
        return Af2;
    }

    public void setAf2(double af2) {
        Af2 = af2;
    }

    public double getAf3() {
        return Af3;
    }

    public void setAf3(double af3) {
        Af3 = af3;
    }

    public double getAf4() {
        return Af4;
    }

    public void setAf4(double af4) {
        Af4 = af4;
    }

    public double getM1() {
        return M1;
    }

    public void setM1(double m1) {
        M1 = m1;
    }

    public double getM2() {
        return M2;
    }

    public void setM2(double m2) {
        M2 = m2;
    }

    public double getM3() {
        return M3;
    }

    public void setM3(double m3) {
        M3 = m3;
    }

    public double getM4() {
        return M4;
    }

    public void setM4(double m4) {
        M4 = m4;
    }

    public double getM5() {
        return M5;
    }

    public void setM5(double m5) {
        M5 = m5;
    }

    public double getMk() {
        return Mk;
    }

    public void setMk(double mk) {
        Mk = mk;
    }

    public double getN1() {
        return N1;
    }

    public void setN1(double n1) {
        N1 = n1;
    }

    public double getN2() {
        return N2;
    }

    public void setN2(double n2) {
        N2 = n2;
    }

    public double getN3() {
        return N3;
    }

    public void setN3(double n3) {
        N3 = n3;
    }

    public double getN4() {
        return N4;
    }

    public void setN4(double n4) {
        N4 = n4;
    }

    public double getN5() {
        return N5;
    }

    public void setN5(double n5) {
        N5 = n5;
    }

    public double getNk() {
        return Nk;
    }

    public void setNk(double nk) {
        Nk = nk;
    }

    public double getQ1() {
        return q1;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }

    public double getQ2() {
        return q2;
    }

    public void setQ2(double q2) {
        this.q2 = q2;
    }

    public double getQ3() {
        return q3;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

    public double getQ4() {
        return q4;
    }

    public void setQ4(double q4) {
        this.q4 = q4;
    }

    public double getQ5() {
        return q5;
    }

    public void setQ5(double q5) {
        this.q5 = q5;
    }

    public double getQk() {
        return qk;
    }

    public void setQk(double qk) {
        this.qk = qk;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double getA4() {
        return a4;
    }

    public void setA4(double a4) {
        this.a4 = a4;
    }

    public double getA5() {
        return a5;
    }

    public void setA5(double a5) {
        this.a5 = a5;
    }

    public double getAk() {
        return ak;
    }

    public void setAk(double ak) {
        this.ak = ak;
    }

    public double getC1() {
        return c1;
    }

    public void setC1(double c1) {
        this.c1 = c1;
    }

    public double getC2() {
        return c2;
    }

    public void setC2(double c2) {
        this.c2 = c2;
    }

    public double getC3() {
        return c3;
    }

    public void setC3(double c3) {
        this.c3 = c3;
    }

    public double getC4() {
        return c4;
    }

    public void setC4(double c4) {
        this.c4 = c4;
    }

    public double getC5() {
        return c5;
    }

    public void setC5(double c5) {
        this.c5 = c5;
    }

    public double getCk() {
        return ck;
    }

    public void setCk(double ck) {
        this.ck = ck;
    }

    public double getL1() {
        return L1;
    }

    public void setL1(double l1) {
        L1 = l1;
    }

    public double getL2() {
        return L2;
    }

    public void setL2(double l2) {
        L2 = l2;
    }

    public double getL3() {
        return L3;
    }

    public void setL3(double l3) {
        L3 = l3;
    }

    public double getL4() {
        return L4;
    }

    public void setL4(double l4) {
        L4 = l4;
    }

    public double getL5() {
        return L5;
    }

    public void setL5(double l5) {
        L5 = l5;
    }

    public double getLk() {
        return Lk;
    }

    public void setLk(double lk) {
        Lk = lk;
    }
}
