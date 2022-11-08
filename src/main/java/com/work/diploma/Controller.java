package com.work.diploma;

import calculations.SectionChecker;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    SectionChecker calculator = new SectionChecker();

    //TODO Separate SecCh and ParSr
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab conditionTab;
    @FXML
    private Tab resultTab;

    @FXML
    private ProgressBar progress;

    @FXML
    private AreaChart<Double, Double> momentDiagram;
    @FXML
    private AreaChart<Double, Double> forceDiagram;

    @FXML
    private NumberAxis momentDiagramView;
    @FXML
    private NumberAxis momentDiagramLength;
    @FXML
    private NumberAxis forceDiagramView;
    @FXML
    private NumberAxis forceDiagramLength;

    @FXML
    public RadioButton fixedSupportOne, fixedSupportTwo, fixedSupportThree, fixedSupportFour, fixedSupportFive;
    @FXML
    public RadioButton hardTerminationOne, hardTerminationTwo, hardTerminationThree, hardTerminationFour, hardTerminationFive;

    @FXML
    private CheckBox consoleOrNot;

    @FXML
    private ImageView supOne, supTwo, supThree, supFour, supFive;
    @FXML
    private ImageView hardOne, hardTwo, hardThree, hardFour, hardFive;
    @FXML
    private ImageView shapeView;

    @FXML
    private TextField n1f, n2f, n3f, n4f, n5f, nKf, m1f, m2f, m3f, m4f, m5f, mKf, q1f, q2f, q3f, q4f, q5f, qKf;
    @FXML
    private TextField a1f, a2f, a3f, a4f, a5f, aKf, c1f, c2f, c3f, c4f, c5f, cKf, L1f, L2f, L3f, L4f, L5f, Lkf;
    @FXML
    private TextField height, width, radius, depth;

    @FXML
    private TextArea results;

    @FXML
    private RadioMenuItem steel, wood, concrete, square, rectangle, circle, low, medium, high;

    @FXML
    private Text informationText;

    @FXML
    private Button launchCalc;

    public void setAccuracy() {
        informationText.setText("Вычисление изгибающих моментов и поперечных сил.");
        progress.setProgress(0.5);

        if (low.isSelected()) {
            calculator.setAccuracy(100);
        } else if (medium.isSelected()) {
            calculator.setAccuracy(1000);
        } else if (high.isSelected()) {
            calculator.setAccuracy(10000);
        }
    }

    public void setSchemaFieldsToDefault() {
        n2f.setDisable(true);
        a2f.setDisable(true);
        m2f.setDisable(true);
        c2f.setDisable(true);
        q2f.setDisable(true);
        L2f.setDisable(true);

        n3f.setDisable(true);
        a3f.setDisable(true);
        m3f.setDisable(true);
        c3f.setDisable(true);
        q3f.setDisable(true);
        L3f.setDisable(true);

        n4f.setDisable(true);
        a4f.setDisable(true);
        m4f.setDisable(true);
        c4f.setDisable(true);
        q4f.setDisable(true);
        L4f.setDisable(true);

        n5f.setDisable(true);
        a5f.setDisable(true);
        m5f.setDisable(true);
        c5f.setDisable(true);
        q5f.setDisable(true);
        L5f.setDisable(true);
    }

    public void setPictures(int i) {

        switch (i) {
            case 0 -> {
                supOne.setImage(new Image("com/work/diploma/images/первый_пролёт_шарнирно-неподвижная_заделка.JPG"));
                supTwo.setImage(new Image("com/work/diploma/images/второй_пролёт.JPG"));
                supThree.setImage(new Image("com/work/diploma/images/третий_пролёт.JPG"));
                supFour.setImage(new Image("com/work/diploma/images/четвёртый_пролёт.JPG"));
                supFive.setImage(new Image("com/work/diploma/images/пятый_пролёт.JPG"));
                hardTwo.setImage(new Image("com/work/diploma/images/второй_пролёт.JPG"));
                hardThree.setImage(new Image("com/work/diploma/images/третий_пролёт.JPG"));
                hardFour.setImage(new Image("com/work/diploma/images/четвёртый_пролёт.JPG"));
                hardFive.setImage(new Image("com/work/diploma/images/пятый_пролёт.JPG"));
            }
            case 1 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_квадрат.JPG"));
            }
            case 2 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_прямоугольник.JPG"));
            }
            case 3 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_круг.JPG"));
            }
            case 4 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_круг_сталь.JPG"));
            }
            case 5 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_квадрат_сталь.JPG"));
            }
            case 6 -> {
                shapeView.setImage(new Image("com/work/diploma/images/сечение_прямоугольник_сталь.JPG"));
            }
            default -> {
            }
        }
    }

    public void onConsoleSet() {
        if (consoleOrNot.isSelected()) {
            if (fixedSupportOne.isSelected() || fixedSupportTwo.isSelected() || fixedSupportThree.isSelected() ||
                    fixedSupportFour.isSelected() || fixedSupportFive.isSelected() || hardTerminationOne.isSelected() ||
                    hardTerminationTwo.isSelected() || hardTerminationThree.isSelected() || hardTerminationFour.isSelected() ||
                    hardTerminationFive.isSelected()) {
                setPictures(0);
            }
        } else {
            if (fixedSupportOne.isSelected()) {
                setPictures(0);
                supOne.setImage(new Image("com/work/diploma/images/первый_пролёт_шарнирно-неподвижная_заделка_консоль.JPG"));
            } else if (fixedSupportTwo.isSelected()) {
                setPictures(0);
                supTwo.setImage(new Image("com/work/diploma/images/второй_пролёт_консоль.JPG"));
            } else if (fixedSupportThree.isSelected()) {
                setPictures(0);
                supThree.setImage(new Image("com/work/diploma/images/третий_пролёт_консоль.JPG"));
            } else if (fixedSupportFour.isSelected()) {
                setPictures(0);
                supFour.setImage(new Image("com/work/diploma/images/четвёртый_пролёт_консоль.JPG"));
            } else if (fixedSupportFive.isSelected()) {
                setPictures(0);
                supFive.setImage(new Image("com/work/diploma/images/пятый_пролёт_консоль.JPG"));
            } else if (hardTerminationTwo.isSelected()) {
                setPictures(0);
                hardTwo.setImage(new Image("com/work/diploma/images/второй_пролёт_консоль.JPG"));
            } else if (hardTerminationThree.isSelected()) {
                setPictures(0);
                hardThree.setImage(new Image("com/work/diploma/images/третий_пролёт_консоль.JPG"));
            } else if (hardTerminationFour.isSelected()) {
                setPictures(0);
                hardFour.setImage(new Image("com/work/diploma/images/четвёртый_пролёт_консоль.JPG"));
            } else if (hardTerminationFive.isSelected()) {
                setPictures(0);
                hardFive.setImage(new Image("com/work/diploma/images/пятый_пролёт_консоль.JPG"));
            }
        }
        onConsoleChosenSwitcher();
    }

    public void onSchemaChosenSwitcher() {
        if (fixedSupportFive.isSelected() || hardTerminationFive.isSelected()) {
            n2f.setDisable(false);
            a2f.setDisable(false);
            m2f.setDisable(false);
            c2f.setDisable(false);
            q2f.setDisable(false);
            L2f.setDisable(false);

            n3f.setDisable(false);
            a3f.setDisable(false);
            m3f.setDisable(false);
            c3f.setDisable(false);
            q3f.setDisable(false);
            L3f.setDisable(false);

            n4f.setDisable(false);
            a4f.setDisable(false);
            m4f.setDisable(false);
            c4f.setDisable(false);
            q4f.setDisable(false);
            L4f.setDisable(false);

            n5f.setDisable(false);
            a5f.setDisable(false);
            m5f.setDisable(false);
            c5f.setDisable(false);
            q5f.setDisable(false);
            L5f.setDisable(false);
        } else if (fixedSupportFour.isSelected() || hardTerminationFour.isSelected()) {
            setSchemaFieldsToDefault();

            n2f.setDisable(false);
            a2f.setDisable(false);
            m2f.setDisable(false);
            c2f.setDisable(false);
            q2f.setDisable(false);
            L2f.setDisable(false);

            n3f.setDisable(false);
            a3f.setDisable(false);
            m3f.setDisable(false);
            c3f.setDisable(false);
            q3f.setDisable(false);
            L3f.setDisable(false);

            n4f.setDisable(false);
            a4f.setDisable(false);
            m4f.setDisable(false);
            c4f.setDisable(false);
            q4f.setDisable(false);
            L4f.setDisable(false);
        } else if (fixedSupportThree.isSelected() || hardTerminationThree.isSelected()) {
            setSchemaFieldsToDefault();

            n2f.setDisable(false);
            a2f.setDisable(false);
            m2f.setDisable(false);
            c2f.setDisable(false);
            q2f.setDisable(false);
            L2f.setDisable(false);

            n3f.setDisable(false);
            a3f.setDisable(false);
            m3f.setDisable(false);
            c3f.setDisable(false);
            q3f.setDisable(false);
            L3f.setDisable(false);
        } else if (fixedSupportTwo.isSelected() || hardTerminationTwo.isSelected()) {
            setSchemaFieldsToDefault();

            n2f.setDisable(false);
            a2f.setDisable(false);
            m2f.setDisable(false);
            c2f.setDisable(false);
            q2f.setDisable(false);
            L2f.setDisable(false);
        } else {
            setSchemaFieldsToDefault();
        }

    }

    public void onConsoleChosenSwitcher() {
        if (!consoleOrNot.isSelected()) {
            nKf.setDisable(false);
            aKf.setDisable(false);
            mKf.setDisable(false);
            cKf.setDisable(false);
            qKf.setDisable(false);
            Lkf.setDisable(false);
        } else {
            nKf.setDisable(true);
            aKf.setDisable(true);
            mKf.setDisable(true);
            cKf.setDisable(true);
            qKf.setDisable(true);
            Lkf.setDisable(true);
        }
    }

    //TODO лучше использовать вложенные if!!!

    public void onMaterialAndShapeChosen() {
        if ((wood.isSelected() || concrete.isSelected()) && square.isSelected()) {
            setPictures(1);
            radius.setDisable(true);
            height.setDisable(true);
            width.setDisable(false);
            depth.setDisable(true);
        } else if ((wood.isSelected() || concrete.isSelected()) && rectangle.isSelected()) {
            setPictures(2);
            radius.setDisable(true);
            height.setDisable(false);
            width.setDisable(false);
            depth.setDisable(true);
        } else if ((wood.isSelected() || concrete.isSelected()) && circle.isSelected()) {
            setPictures(3);
            radius.setDisable(false);
            height.setDisable(true);
            width.setDisable(true);
            depth.setDisable(true);
        } else if (steel.isSelected() && circle.isSelected()) {
            setPictures(4);
            radius.setDisable(false);
            height.setDisable(true);
            width.setDisable(true);
            depth.setDisable(false);
        } else if (steel.isSelected() && square.isSelected()) {
            setPictures(5);
            radius.setDisable(true);
            height.setDisable(true);
            width.setDisable(false);
            depth.setDisable(false);
        } else if (steel.isSelected() && rectangle.isSelected()) {
            setPictures(6);
            radius.setDisable(true);
            height.setDisable(false);
            width.setDisable(false);
            depth.setDisable(false);
        }
    }

    public boolean parseToDouble() {

        try {
            if (Double.parseDouble(L1f.getText()) == 0.0 || (!L2f.isDisable() && Double.parseDouble(L2f.getText()) == 0.0) ||
                    (!L3f.isDisable() && Double.parseDouble(L3f.getText()) == 0.0) || (!L4f.isDisable() && Double.parseDouble(L4f.getText()) == 0.0) ||
                    (!L5f.isDisable() && Double.parseDouble(L5f.getText()) == 0.0) || (!Lkf.isDisable() && Double.parseDouble(Lkf.getText()) == 0.0)) {
                informationText.setVisible(true);
                informationText.setText("Длина пролёта не может быть равной 0!");
                return false;
            } else if ((Double.parseDouble(a1f.getText()) > Double.parseDouble(L1f.getText())) ||
                    (Double.parseDouble(c1f.getText()) > Double.parseDouble(L1f.getText())) ||
                    !L2f.isDisable() && (Double.parseDouble(a2f.getText()) > Double.parseDouble(L2f.getText())) ||
                    !L2f.isDisable() && (Double.parseDouble(c2f.getText()) > Double.parseDouble(L2f.getText())) ||
                    !L3f.isDisable() && (Double.parseDouble(a3f.getText()) > Double.parseDouble(L3f.getText())) ||
                    !L3f.isDisable() && (Double.parseDouble(c3f.getText()) > Double.parseDouble(L3f.getText())) ||
                    !L4f.isDisable() && (Double.parseDouble(a4f.getText()) > Double.parseDouble(L4f.getText())) ||
                    !L4f.isDisable() && (Double.parseDouble(c4f.getText()) > Double.parseDouble(L4f.getText())) ||
                    !L5f.isDisable() && (Double.parseDouble(a5f.getText()) > Double.parseDouble(L5f.getText())) ||
                    !L5f.isDisable() && (Double.parseDouble(c5f.getText()) > Double.parseDouble(L5f.getText())) ||
                    !Lkf.isDisable() && (Double.parseDouble(aKf.getText()) > Double.parseDouble(Lkf.getText())) ||
                    !Lkf.isDisable() && (Double.parseDouble(cKf.getText()) > Double.parseDouble(Lkf.getText()))) {
                informationText.setVisible(true);
                informationText.setText("Сила или момент приложены вне пролёта! Убедитесь что значения \"а\" и \"с\" меньше соответсвующих им длин пролётов \"L\"!");
                return false;
            } else if (!n2f.isDisable() && !n3f.isDisable() && !n4f.isDisable() && !n5f.isDisable() && !nKf.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));

                calculator.setN4(Double.parseDouble(n4f.getText()));
                calculator.setM4(Double.parseDouble(m4f.getText()));
                calculator.setQ4(Double.parseDouble(q4f.getText()));
                calculator.setA4(Double.parseDouble(a4f.getText()));
                calculator.setC4(Double.parseDouble(c4f.getText()));
                calculator.setL4(Double.parseDouble(L4f.getText()));

                calculator.setN5(Double.parseDouble(n5f.getText()));
                calculator.setM5(Double.parseDouble(m5f.getText()));
                calculator.setQ5(Double.parseDouble(q5f.getText()));
                calculator.setA5(Double.parseDouble(a5f.getText()));
                calculator.setC5(Double.parseDouble(c5f.getText()));
                calculator.setL5(Double.parseDouble(L5f.getText()));

                calculator.setNk(Double.parseDouble(nKf.getText()));
                calculator.setMk(Double.parseDouble(mKf.getText()));
                calculator.setQk(Double.parseDouble(qKf.getText()));
                calculator.setAk(Double.parseDouble(aKf.getText()));
                calculator.setCk(Double.parseDouble(cKf.getText()));
                calculator.setLk(Double.parseDouble(Lkf.getText()));
                return true;
            } else if (!n2f.isDisable() && !n3f.isDisable() && !n4f.isDisable() && !n5f.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));

                calculator.setN4(Double.parseDouble(n4f.getText()));
                calculator.setM4(Double.parseDouble(m4f.getText()));
                calculator.setQ4(Double.parseDouble(q4f.getText()));
                calculator.setA4(Double.parseDouble(a4f.getText()));
                calculator.setC4(Double.parseDouble(c4f.getText()));
                calculator.setL4(Double.parseDouble(L4f.getText()));

                calculator.setN5(Double.parseDouble(n5f.getText()));
                calculator.setM5(Double.parseDouble(m5f.getText()));
                calculator.setQ5(Double.parseDouble(q5f.getText()));
                calculator.setA5(Double.parseDouble(a5f.getText()));
                calculator.setC5(Double.parseDouble(c5f.getText()));
                calculator.setL5(Double.parseDouble(L5f.getText()));
                return true;
            } else if (!n2f.isDisable() && !n3f.isDisable() && !n4f.isDisable() && !nKf.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));

                calculator.setN4(Double.parseDouble(n4f.getText()));
                calculator.setM4(Double.parseDouble(m4f.getText()));
                calculator.setQ4(Double.parseDouble(q4f.getText()));
                calculator.setA4(Double.parseDouble(a4f.getText()));
                calculator.setC4(Double.parseDouble(c4f.getText()));
                calculator.setL4(Double.parseDouble(L4f.getText()));

                calculator.setNk(Double.parseDouble(nKf.getText()));
                calculator.setMk(Double.parseDouble(mKf.getText()));
                calculator.setQk(Double.parseDouble(qKf.getText()));
                calculator.setAk(Double.parseDouble(aKf.getText()));
                calculator.setCk(Double.parseDouble(cKf.getText()));
                calculator.setLk(Double.parseDouble(Lkf.getText()));
                return true;
            } else if (!n2f.isDisable() && !n3f.isDisable() && !n4f.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));

                calculator.setN4(Double.parseDouble(n4f.getText()));
                calculator.setM4(Double.parseDouble(m4f.getText()));
                calculator.setQ4(Double.parseDouble(q4f.getText()));
                calculator.setA4(Double.parseDouble(a4f.getText()));
                calculator.setC4(Double.parseDouble(c4f.getText()));
                calculator.setL4(Double.parseDouble(L4f.getText()));
                return true;
            } else if (!n2f.isDisable() && !n3f.isDisable() && !nKf.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));

                calculator.setNk(Double.parseDouble(nKf.getText()));
                calculator.setMk(Double.parseDouble(mKf.getText()));
                calculator.setQk(Double.parseDouble(qKf.getText()));
                calculator.setAk(Double.parseDouble(aKf.getText()));
                calculator.setCk(Double.parseDouble(cKf.getText()));
                calculator.setLk(Double.parseDouble(Lkf.getText()));
                return true;
            } else if (!n2f.isDisable() && !n3f.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setN3(Double.parseDouble(n3f.getText()));
                calculator.setM3(Double.parseDouble(m3f.getText()));
                calculator.setQ3(Double.parseDouble(q3f.getText()));
                calculator.setA3(Double.parseDouble(a3f.getText()));
                calculator.setC3(Double.parseDouble(c3f.getText()));
                calculator.setL3(Double.parseDouble(L3f.getText()));
                return true;
            } else if (!n2f.isDisable() && !nKf.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));

                calculator.setNk(Double.parseDouble(nKf.getText()));
                calculator.setMk(Double.parseDouble(mKf.getText()));
                calculator.setQk(Double.parseDouble(qKf.getText()));
                calculator.setAk(Double.parseDouble(aKf.getText()));
                calculator.setCk(Double.parseDouble(cKf.getText()));
                calculator.setLk(Double.parseDouble(Lkf.getText()));
                return true;
            } else if (!n2f.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setN2(Double.parseDouble(n2f.getText()));
                calculator.setM2(Double.parseDouble(m2f.getText()));
                calculator.setQ2(Double.parseDouble(q2f.getText()));
                calculator.setA2(Double.parseDouble(a2f.getText()));
                calculator.setC2(Double.parseDouble(c2f.getText()));
                calculator.setL2(Double.parseDouble(L2f.getText()));
                return true;
            } else if (!nKf.isDisable()) {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));

                calculator.setNk(Double.parseDouble(nKf.getText()));
                calculator.setMk(Double.parseDouble(mKf.getText()));
                calculator.setQk(Double.parseDouble(qKf.getText()));
                calculator.setAk(Double.parseDouble(aKf.getText()));
                calculator.setCk(Double.parseDouble(cKf.getText()));
                calculator.setLk(Double.parseDouble(Lkf.getText()));
                return true;
            } else {
                calculator.setN1(Double.parseDouble(n1f.getText()));
                calculator.setM1(Double.parseDouble(m1f.getText()));
                calculator.setQ1(Double.parseDouble(q1f.getText()));
                calculator.setA1(Double.parseDouble(a1f.getText()));
                calculator.setC1(Double.parseDouble(c1f.getText()));
                calculator.setL1(Double.parseDouble(L1f.getText()));
                return true;
            }
        } catch (NumberFormatException e) {
            informationText.setVisible(true);
            informationText.setText("Значения нагрузок, длин и расстояний должны быть введены корректно," +
                    " в качестве разделителя разряда необходимо использовать \".\"!");
            return false;
        }
    }

    public boolean shapeParametersToDouble() {

        try {
            if ((!height.isDisable() && (Double.parseDouble(height.getText())) == 0.0) || (!width.isDisable() && Double.parseDouble(width.getText()) == 0.0) ||
                    (!depth.isDisable() && Double.parseDouble(depth.getText()) == 0.0) || (!radius.isDisable() && Double.parseDouble(radius.getText()) == 0.0)) {
                informationText.setVisible(true);
                informationText.setText("Параметры сечения должны быть отличными от 0!");
                return false;
            } else if (!height.isDisable() && !width.isDisable() && !depth.isDisable()) {
                calculator.setH(Double.parseDouble(height.getText()));
                calculator.setB(Double.parseDouble(width.getText()));
                calculator.setT(Double.parseDouble(depth.getText()));
                return true;
            } else if (!height.isDisable() && !width.isDisable()) {
                calculator.setH(Double.parseDouble(height.getText()));
                calculator.setB(Double.parseDouble(width.getText()));
                return true;
            } else if (!radius.isDisable() && !depth.isDisable()) {
                calculator.setR(Double.parseDouble(radius.getText()));
                calculator.setT(Double.parseDouble(depth.getText()));
                return true;
            } else if (!radius.isDisable()) {
                calculator.setR(Double.parseDouble(radius.getText()));
                return true;
            } else if (!width.isDisable() && !depth.isDisable()) {
                calculator.setB(Double.parseDouble(width.getText()));
                calculator.setT(Double.parseDouble(depth.getText()));
                return true;
            } else if (!width.isDisable()) {
                calculator.setB(Double.parseDouble(width.getText()));
                return true;
            }
        } catch (NumberFormatException e) {
            informationText.setVisible(true);
            informationText.setText("Значения параметров сечения должны быть введены корректно, в качестве разделителя разряда необходимо использовать \".\"!");
            return false;
        }
        return false;
    }

    public boolean validator() {
        progress.setProgress(0.0);

        if (!fixedSupportOne.isSelected() && !fixedSupportTwo.isSelected() && !fixedSupportThree.isSelected() &&
                !fixedSupportFour.isSelected() && !fixedSupportFive.isSelected() && !hardTerminationOne.isSelected() &&
                !hardTerminationTwo.isSelected() && !hardTerminationThree.isSelected() && !hardTerminationFour.isSelected() &&
                !hardTerminationFive.isSelected()) {

            informationText.setVisible(true);
            informationText.setText("Выберите одну из расчётных схем!");
            return false;
        } else if (!steel.isSelected() && !concrete.isSelected() && !wood.isSelected()) {
            informationText.setVisible(true);
            informationText.setText("Выберите материал сечения!");
            return false;
        } else if (!square.isSelected() && !rectangle.isSelected() && !circle.isSelected()) {
            informationText.setVisible(true);
            informationText.setText("Выберите форму сечения!");
            return false;
        } else {
            shapeParametersToDouble();
            parseToDouble();
            if (shapeParametersToDouble() && parseToDouble()) {
                informationText.setVisible(true);
                informationText.setText("Расчёт начат!");
                return true;
            } else {
                return false;
            }
        }
    }

    public void calculateMomentsAndStrengths() {
        informationText.setText("Проверка прочности выбранного сечения.");
        progress.setProgress(0.75);

        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Callable<Object>> threads = new ArrayList<>();

        if (validator()) {
            try {
                if ((fixedSupportFive.isSelected() || hardTerminationFive.isSelected()) && consoleOrNot.isSelected()) {

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsFourth());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.calculateMomentsNoMomentWithConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getC5(), calculator.getL5(), calculator.getM5(), calculator.getQ5(), calculator.getN5(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                    calculator.missingMomentsCalculationFive(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), calculator.getMx4(), calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsFourth());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsWithConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getC5(), calculator.getL5(), calculator.getM5(), calculator.getMx4(), 0.0, calculator.getQ5(), calculator.getN5(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getTransverseForcesThird());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), calculator.getMx4(), calculator.getQ4(), calculator.getN4(), calculator.getTransverseForcesFourth());
                            calculator.calculateForcesWithConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getL5(), calculator.getM5(), calculator.getMx4(), 0.0, calculator.getQ5(), calculator.getN5(), calculator.getAk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if (fixedSupportFive.isSelected() || hardTerminationFive.isSelected()) {

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsFourth());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getC5(), calculator.getL5(), calculator.getM5(), calculator.getQ5(), calculator.getN5(), calculator.getBindingMomentsFifth());
                    calculator.missingMomentsCalculationFive(hardTerminationOne);

                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                        }
                    });
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), calculator.getMx4(), calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsFourth());
                        }
                    });
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getC5(), calculator.getL5(), calculator.getM5(), calculator.getMx4(), 0.0, calculator.getQ5(), calculator.getN5(), calculator.getBindingMomentsFifth());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                        }
                    });
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getTransverseForcesThird());
                        }
                    });
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), calculator.getMx4(), calculator.getQ4(), calculator.getN4(), calculator.getTransverseForcesFourth());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA5(), calculator.getL5(), calculator.getM5(), calculator.getMx4(), 0.0, calculator.getQ5(), calculator.getN5(), calculator.getTransverseForcesFifth());
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if ((fixedSupportFour.isSelected() || hardTerminationFour.isSelected()) && consoleOrNot.isSelected()) {
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoMomentWithConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getQ4(), calculator.getN4(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.missingMomentsCalculationFour(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsWithConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), 0.0, calculator.getQ4(), calculator.getN4(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getTransverseForcesThird());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesWithConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), 0.0, calculator.getQ4(), calculator.getN4(), calculator.getAk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if (fixedSupportFour.isSelected() || hardTerminationFour.isSelected()) {
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.missingMomentsCalculationFour(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getC4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), 0.0, calculator.getQ4(), calculator.getN4(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getTransverseForcesThird());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA4(), calculator.getL4(), calculator.getM4(), calculator.getMx3(), 0.0, calculator.getQ4(), calculator.getN4(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if ((fixedSupportThree.isSelected() || hardTerminationThree.isSelected()) && consoleOrNot.isSelected()) {
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentWithConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.missingMomentsCalculationThree(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsWithConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), 0.0, calculator.getQ3(), calculator.getN3(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesWithConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), 0.0, calculator.getQ3(), calculator.getN3(), calculator.getAk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();

                } else if (fixedSupportThree.isSelected() || hardTerminationThree.isSelected()) {
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.missingMomentsCalculationThree(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getC3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getBindingMomentsThird());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), calculator.getMx2(), calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA3(), calculator.getL3(), calculator.getM3(), calculator.getMx2(), calculator.getMx3(), calculator.getQ3(), calculator.getN3(), calculator.getTransverseForcesThird());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if ((fixedSupportTwo.isSelected() || hardTerminationTwo.isSelected()) && consoleOrNot.isSelected()) {
                    calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                    calculator.calculateMomentsNoMomentWithConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());

                    calculator.missingMomentsCalculationTwo(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsWithConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), 0.0, calculator.getQ2(), calculator.getN2(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            calculator.calculateForcesWithConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), 0.0, calculator.getQ2(), calculator.getN2(), calculator.getAk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();

                } else if (fixedSupportTwo.isSelected() || hardTerminationTwo.isSelected()) {
                    calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                    calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());

                    calculator.missingMomentsCalculationTwo(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getC2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), 0.0, calculator.getQ2(), calculator.getN2(), calculator.getBindingMomentsSecond());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), calculator.getMx1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA2(), calculator.getL2(), calculator.getM2(), calculator.getMx1(), 0.0, calculator.getQ2(), calculator.getN2(), calculator.getTransverseForcesSecond());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);

                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();

                } else if (fixedSupportOne.isSelected() && consoleOrNot.isSelected()) {
                    calculator.calculateMomentsNoMomentWithConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());

                    calculator.missingMomentsCalculationOne(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsWithConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), 0.0, calculator.getQ1(), calculator.getN1(), calculator.getAk(), calculator.getCk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getBindingMomentsConsole());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesWithConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), 0.0, calculator.getQ1(), calculator.getN1(), calculator.getAk(), calculator.getLk(), calculator.getMk(), calculator.getQk(), calculator.getNk(), calculator.getTransverseForcesConsole());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else if (fixedSupportOne.isSelected()) {
                    calculator.calculateMomentsNoMomentNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());

                    calculator.missingMomentsCalculationOne(hardTerminationOne);

                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateMomentsNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), 0.0, calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsFirst());
                            return null;
                        }
                    });
                    threads.add(new Callable() {
                        @Override
                        public Object call() {
                            calculator.calculateForcesNoConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getMx0(), 0.0, calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesFirst());
                            return null;
                        }
                    });

                    pool.invokeAll(threads);
                    threads.clear();

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();


                } else {
                    calculator.calculateMomentsOnlyConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getC1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getBindingMomentsConsole());
                    calculator.calculateForcesOnlyConsole(calculator.getAccuracy(), calculator.getA1(), calculator.getL1(), calculator.getM1(), calculator.getQ1(), calculator.getN1(), calculator.getTransverseForcesConsole());

                    calculator.fillMomentDiagram();
                    calculator.fillForceDiagram();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                pool.shutdown();
            }
        }
    }

    public void printDiagrams() {
        XYChart.Series<Double, Double> bindingMoment = new XYChart.Series<>();
        XYChart.Series<Double, Double> bindingStrength = new XYChart.Series<>();

        List<Double> BMS = calculator.getBindingMomentsSummary();
        List<Double> TFS = calculator.getTransverseForcesSummary();
        if (validator()) {
            if (low.isSelected()) {
                for (int i = 0; i <= BMS.size(); i += 10) {
                    bindingMoment.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / BMS.size()), BMS.get(i)));
                    bindingStrength.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / TFS.size()), TFS.get(i)));
                }
            } else if (medium.isSelected()) {
                for (int i = 0; i <= BMS.size(); i += 50) {
                    bindingMoment.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / BMS.size()), BMS.get(i)));
                    bindingStrength.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / TFS.size()), TFS.get(i)));
                }
            } else if (high.isSelected()) {
                for (int i = 0; i <= BMS.size(); i += 250) {
                    bindingMoment.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / BMS.size()), BMS.get(i)));
                    bindingStrength.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / TFS.size()), TFS.get(i)));
                }
            } else {
                for (int i = 0; i <= BMS.size(); i += 10) {
                    bindingMoment.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / BMS.size()), BMS.get(i)));
                    bindingStrength.getData().add(new XYChart.Data<>((calculator.lengthSummary() * i / TFS.size()), TFS.get(i)));
                }
            }

            momentDiagram.getData().clear();
            forceDiagram.getData().clear();

            momentDiagram.getData().addAll(bindingMoment);
            forceDiagram.getData().addAll(bindingStrength);

            informationText.setText("Расчёт окончен. Для просмотра результатов перейдите во вкладку \"Результаты расчёта\"!");
            progress.setProgress(1.0);
        }
    }

    public void checkSection() {
        if (steel.isSelected() && circle.isSelected()) {
            //calculator.setResistances(1);
            calculator.durabilityForSteel(1);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального трубчатого сечения (радиус = " + calculator.getR() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального трубчатого сечения (радиус = " + calculator.getR() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального трубчатого сечения (радиус = " + calculator.getR() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного стального трубчатого сечения (радиус = " + calculator.getR() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (steel.isSelected() && square.isSelected()) {
            //calculator.setResistances(1);
            calculator.durabilityForSteel(2);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения квадратной формы (ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения квадратной формы (ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения квадратной формы (ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения квадратной формы (ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (steel.isSelected() && rectangle.isSelected()) {
            //calculator.setResistances(1);
            calculator.durabilityForSteel(3);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения прямоугольной формы (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения прямоугольной формы (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения прямоугольной формы (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного стального коробчатого сечения прямоугольной формы (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (wood.isSelected() && circle.isSelected()) {
            calculator.durabilityForWood(1);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного деревянного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (wood.isSelected() && square.isSelected()) {
            calculator.durabilityForWood(2);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного деревянного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (wood.isSelected() && rectangle.isSelected()) {
            calculator.durabilityForWood(3);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного деревянного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного деревянного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (concrete.isSelected() && circle.isSelected()) {
            calculator.durabilityForConcrete(1);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного бетонного круглого сечения (радиус = " + calculator.getR() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (concrete.isSelected() && square.isSelected()) {
            calculator.durabilityForConcrete(2);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного бетонного квадратного сечения (ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        } else if (concrete.isSelected() && rectangle.isSelected()) {
            calculator.durabilityForConcrete(3);
            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;
            double strFail = Math.ceil(((1 - stretchingRatio) * 100) * 10) / 10;
            double compFail = Math.ceil(((1 - compressingRatio) * 100) * 10) / 10;

            if (stretchingRatio > 1 && compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else if (stretchingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                        "Сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            } else if (compressingRatio > 1) {
                results.setText("Результаты расчёта для выбранного бетонного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%.\nРекомендуется изменить габариты сечения!\n" +
                        "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
            } else {
                results.setText("Результаты расчёта для выбранного бетонного прямоугольного сечения (высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м):\n\n" +
                        "Максимальное по модулю значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                        "Максимальное по модулю значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                        "соответстветствующее ему значение поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                        "Растягивающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + strFail + "%, " +
                        "сжимающие усилия, возникающие в сечении, превосходят расчётное сопротивление на " + compFail + "%.\nРекомендуется изменить габариты сечения!");
            }
        }
    }

    public void launchSectionChecker() {
        validator();

        setAccuracy();

        calculateMomentsAndStrengths();

        printDiagrams();

        checkSection();
    }

    public void tabSwitcher() {
        tabPane.getSelectionModel().select(conditionTab);
    }

    public void reportSaver() {
        Stage stage = new Stage();

        FileChooser newFile = new FileChooser();
        newFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text", "*.txt")
        );
        File sectionCheckerReport = newFile.showSaveDialog(stage);
        try {
            FileWriter writer = new FileWriter(sectionCheckerReport, true);
            writer.write(results.getText());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
