package com.work.diploma;

import calculations.SectionChecker;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerForSelector extends Controller {

    SectionChecker calculator = new SectionChecker();

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
    private TextArea results;

    @FXML
    private RadioMenuItem steel, wood, concrete, square, rectangle, circle, low, medium, high;

    @FXML
    private Text informationText;

    @FXML
    private Button launchCalc;

    @Override
    public void printDiagrams() {
        super.printDiagrams();
    }

    @Override
    public void onMaterialAndShapeChosen() {
        if (wood.isSelected() || concrete.isSelected()) {
            if (square.isSelected()) {
                setPictures(1);
            } else if (rectangle.isSelected()) {
                setPictures(2);
            } else if (circle.isSelected()) {
                setPictures(3);
            }
        } else if (steel.isSelected()) {
            if (circle.isSelected()) {
                setPictures(4);
            } else if (square.isSelected()) {
                setPictures(5);
            } else if (rectangle.isSelected()) {
                setPictures(6);
            }
        }
    }

    @Override
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
            parseToDouble();
            if (parseToDouble()) {
                informationText.setVisible(true);
                informationText.setText("Расчёт начат!");
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void checkSection() {
        if (steel.isSelected() && circle.isSelected()) {
            //calculator.setResistances(1);
            calculator.setR(0.1);
            calculator.setT(0.005);
            double radius = calculator.getR();
            double thin = calculator.getT();
            calculator.durabilityForSteel(1);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                if (calculator.getT() >= calculator.getR() * 0.2) {
                    radius += 0.01d;
                    calculator.setR(radius);
                    calculator.durabilityForSteel(1);
                } else if (calculator.getT() < calculator.getR() * 0.2) {
                    thin += 0.005d;
                    calculator.setT(thin);
                    calculator.durabilityForSteel(1);
                }
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано стальное трубчатое сечение размерами: радиус = " + calculator.getR() + "м, толщина стенки = " + calculator.getT() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (steel.isSelected() && square.isSelected()) {
            //calculator.setResistances(1);
            calculator.setB(0.1);
            calculator.setT(0.005);
            double width = calculator.getB();
            double thin = calculator.getT();
            calculator.durabilityForSteel(2);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                if (calculator.getT() >= calculator.getB() * 0.2) {
                    width += 0.01d;
                    calculator.setB(width);
                    calculator.durabilityForSteel(2);
                } else if (calculator.getT() < calculator.getB() * 0.2) {
                    thin += 0.005d;
                    calculator.setT(thin);
                    calculator.durabilityForSteel(2);
                }
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано стальное квадратное сечение размерами: ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (steel.isSelected() && rectangle.isSelected()) {
            //calculator.setResistances(1);
            calculator.setB(0.05);
            calculator.setH(0.1);
            calculator.setT(0.005);
            double width = calculator.getB();
            double height = calculator.getH();
            double thin = calculator.getT();
            calculator.durabilityForSteel(3);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                if (calculator.getT() >= calculator.getB() * 0.2) {
                    width += 0.005;
                    height += 0.01;
                    calculator.setB(width);
                    calculator.setH(height);
                    calculator.durabilityForSteel(3);
                } else if (calculator.getT() < calculator.getB() * 0.2) {
                    thin += 0.005;
                    calculator.setT(thin);
                    calculator.durabilityForSteel(3);
                }
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано стальное прямоугольное сечение размерами: высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м, толщина стенки = " + calculator.getT() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (wood.isSelected() && circle.isSelected()) {
            calculator.setR(0.1);
            double radius = calculator.getR();
            calculator.durabilityForWood(1);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                radius += 0.005d;
                calculator.setR(radius);
                calculator.durabilityForWood(1);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано деревянное круглое сечение радиусом = " + calculator.getR() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (wood.isSelected() && square.isSelected()) {
            calculator.setB(0.1);
            double width = calculator.getB();
            calculator.durabilityForWood(2);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                width += 0.01d;
                calculator.setB(width);
                calculator.durabilityForWood(2);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано деревянное квадратное сечение шириной\\высотой = " + calculator.getB() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (wood.isSelected() && rectangle.isSelected()) {
            calculator.setB(0.05);
            calculator.setH(0.1);
            double width = calculator.getB();
            double height = calculator.getH();
            calculator.durabilityForWood(3);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                width += 0.005d;
                height += 0.01d;
                calculator.setB(width);
                calculator.setH(height);
                calculator.durabilityForWood(3);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано деревянное прямоугольное сечение размерами: высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (concrete.isSelected() && circle.isSelected()) {
            calculator.setR(0.1);
            double radius = calculator.getR();
            calculator.durabilityForConcrete(1);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                radius += 0.005d;
                calculator.setR(radius);
                calculator.durabilityForConcrete(1);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано бетонное круглое сечение радиусом = " + calculator.getR() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (concrete.isSelected() && square.isSelected()) {
            calculator.setB(0.1);
            double width = calculator.getB();
            calculator.durabilityForConcrete(2);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                width += 0.01d;
                calculator.setB(width);
                calculator.durabilityForConcrete(2);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано бетонное квадратное сечение шириной\\высотой = " + calculator.getB() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");

        } else if (concrete.isSelected() && rectangle.isSelected()) {
            calculator.setB(0.05);
            calculator.setH(0.1);
            double width = calculator.getB();
            double height = calculator.getH();
            calculator.durabilityForConcrete(3);

            while (calculator.getStretchingTense() < 1 && calculator.getCompressingTense() < 1) {
                width += 0.005d;
                height += 0.01d;
                calculator.setB(width);
                calculator.setH(height);
                calculator.durabilityForConcrete(3);
            }

            double stretchingRatio = Math.ceil((calculator.getStretchingTense()) * 1000) / 1000;
            double compressingRatio = Math.ceil((calculator.getCompressingTense()) * 1000) / 1000;

            results.setText("В результате расчёта подобрано бетонное прямоугольное сечение размерами: высота = " + calculator.getH() + "м, ширина = " + calculator.getB() + "м.\n\n" +
                    "Максимальное значение растягивающего момента при заданных условиях нагружения = " + calculator.getStretchingMoment() + "кН*м, " +
                    "максимальное значение растягивающей поперечной силы = " + calculator.getStretchingForce() + "кН.\n" +
                    "Максимальное значение сжимающего момента при заданных условиях нагружения = " + calculator.getCompressingMoment() + "кН*м, " +
                    "максимальное значение сжимающей поперечной силы = " + calculator.getCompressingForce() + "кН.\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях растягивающих усилий = " + stretchingRatio + ".\n" +
                    "Коэффициент запаса прочности заданного сечения при максимальных значениях сжимающих усилий = " + compressingRatio + ".\n");
        }
    }

    public void launchSectionSelector() {
        validator();

        setAccuracy();

        calculateMomentsAndStrengths();

        printDiagrams();

        checkSection();
    }
}
