package Part2.lab3.gui;



import Part2.lab3.Function;
import Part2.lab3.Iterations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static Part2.lab3.Iterations.countX;

public class UserInterface {
        private JFrame mainFrame;
        private Function baseFunction1;
        private Function baseFunction2;
        private Function baseFunction3;
        private JPanel mainPanel, graphPanel, controlPanel;
        private double[] xData;
        private int width, height;
        public static int tmpCount;
        private final String errorTitle = "Ошибка";

        public UserInterface(Function baseFunction1, Function baseFunction2) {
            this.baseFunction1 = baseFunction1;
        }

        public void draw(int width, int height) {
            SwingUtilities.invokeLater(() -> {
                createMainFrame(width, height);
                mainFrame.setVisible(true);
                mainFrame.setResizable(false);
            });
        }

        public void createMainFrame(int width, int height) {
            this.height = height;
            this.width = width;
            final int firstXValueCount = 4;
            final int xAmount = 5;

            mainFrame = new JFrame("Лабораторная работа №3");
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.setSize(width, height);
            mainPanel = new JPanel(new GridLayout(0, 2));
            mainFrame.setContentPane(mainPanel);
            controlPanel = new JPanel(new GridLayout(10, 1, 0, 20));
            mainPanel.add(controlPanel);
            createGraphPanel();
            createSelectFunctionPanel();
            AtomicReference<JPanel> argsPanel = createInterpolationNodeFields(firstXValueCount, xAmount);
            JTextField changeField = createChangeYValuePanel();
            createMakeGraphButton(xAmount, argsPanel, changeField);
            //createInterpolatedValueField();
        }

        private JPanel getGraphPanel(int width, int height, double changeX) {

            Arrays.sort(xData);
            double[] yData1 = Arrays.stream(xData).map(x -> Double.compare(x, changeX) == 0 ?
                    baseFunction1.getValue(x) : baseFunction1.getValue(x)).toArray();

            double[] yData2 = Arrays.stream(xData).map(x -> Double.compare(x, changeX) == 0 ?
                    baseFunction2.getValue(x) : baseFunction2.getValue(x)).toArray();

            JPanel graphPanel = new GraphMaker(baseFunction1, baseFunction2, xData).
                    getChart(width, height, changeX, baseFunction1.getValue(changeX));
            graphPanel.setLocation(0, 0);
            graphPanel.setSize(width, height);
            return graphPanel;
        }

        private void createSelectFunctionPanel() {
            JPanel selectFunctionPanel = new JPanel();
            JLabel label = new JLabel("Выберите функцию");
            selectFunctionPanel.add(label);
            JComboBox<String> selectedFunction = new JComboBox<>();
            selectedFunction.addItem("1");
            selectedFunction.addItem("2");
            selectedFunction.addItem("3");
            selectFunctionPanel.add(selectedFunction);
            controlPanel.add(selectFunctionPanel);
            selectedFunction.addActionListener(e -> {
                String function = (String) (((JComboBox) e.getSource()).getSelectedItem());
                switch (function) {
                    case "1":
                        this.baseFunction1 = arg -> Math.pow(arg,5);
                        this.baseFunction2 = arg -> Math.pow(arg,3) - 2*arg + 1;
                        tmpCount = 1;
                        break;
                    case "2":
                        this.baseFunction1 = arg -> (Math.pow(arg, 2) + 5);
                        this.baseFunction2 = arg -> Math.pow(arg*arg + arg*3,1.5) + 2;
                        tmpCount = 2;
                        break;
                    case "3":
                        this.baseFunction1 = arg -> arg * arg;
                        this.baseFunction2 = arg -> (arg*arg*arg)+arg;
                        tmpCount = 3;
                        break;

                }
            });
        }

        private AtomicReference<JPanel> createInterpolationNodeFields(int firstXValue, int xAmount) {
            JPanel selectPanel = new JPanel();
            controlPanel.add(selectPanel);

            JLabel labelAmount = new JLabel("Выберите левую и правую границу сетки графика");
            selectPanel.add(labelAmount);
            AtomicReference<JPanel> argsPanel = new AtomicReference<>(generateButtons(4, mainFrame));
            controlPanel.add(argsPanel.get());

            controlPanel.remove(argsPanel.get());
            argsPanel.set(generateButtons(2, mainFrame));
            controlPanel.add(argsPanel.get(), 2);
            controlPanel.revalidate();
            controlPanel.repaint();

            return argsPanel;
        }

        private JPanel generateButtons(int argsAmount, JFrame mainFrame) {
            JPanel argsPanel = new JPanel(new GridLayout(1, argsAmount));
            xData = new double[argsAmount];
            for (int i = 0; i < argsAmount; i++) {
                int index = i;
                JTextField xValue = new JTextField();
                xValue.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        try {
                            if (!xValue.getText().equals("")) {
                                xData[index] = Double.parseDouble(xValue.getText().replace(',', '.'));
                                if (Double.isInfinite(baseFunction1.getValue(xData[index]))) {
                                    xData[index] = 0;
                                    throw new IllegalArgumentException();
                                }
                            }
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(mainFrame, "Значения Х должны быть числами",
                                    "Ошибка", JOptionPane.WARNING_MESSAGE);
                        } catch (IllegalArgumentException e1) {
                            JOptionPane.showMessageDialog(mainFrame, "Значение Х выходит за допустимые пределы",
                                    "Ошибка", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                xValue.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        int keyCode = e.getKeyCode();
                        int caretPosition = xValue.getCaretPosition();
                        if (keyCode == KeyEvent.VK_LEFT && index != 0 && caretPosition == 0)
                            argsPanel.getComponent(index - 1).requestFocus();
                        else if (keyCode == KeyEvent.VK_RIGHT && index != argsAmount - 1
                                && caretPosition == xValue.getText().length())
                            argsPanel.getComponent(index + 1).requestFocus();
                    }
                });
                argsPanel.add(xValue);
            }
            return argsPanel;
        }

        private void createGraphPanel() {
            graphPanel = new JPanel(null);
            graphPanel.setSize(width, height);
            mainPanel.add(graphPanel);
        }

        private JTextField createChangeYValuePanel() {
            JPanel changePanel = new JPanel();

            JTextField changeField = new JTextField(7);
            return changeField;
        }

        private void createMakeGraphButton(int xAmount, AtomicReference<JPanel> argsPanel, JTextField changeField) {
            JButton mainButton = new JButton("Построить график");
            mainButton.addActionListener(event -> {

//todo пофиксить решения
                double changeX = countX();
//                try {
//                    changeX = Double.parseDouble(changeField.getText().replace(',', '.'));
//                } catch (NumberFormatException e) {
//                    JOptionPane.showMessageDialog(mainFrame, "Выберите узел, в котором нужно заменить значение функции",
//                            errorTitle, JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                if (Arrays.stream(xData).noneMatch(n -> Double.compare(n, changeX) == 0)) {
//                    JOptionPane.showMessageDialog(mainFrame, "Точка, в которой нужно заменить " +
//                            "значение функции должна быть узлом интерполяции", errorTitle, JOptionPane.ERROR_MESSAGE);
//                } else {
                    mainPanel.remove(graphPanel);
                    graphPanel = getGraphPanel(width, height, changeX);
                    mainPanel.add(graphPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
               // }
            });
            controlPanel.add(mainButton);
        }


//        private void createInterpolatedValueField() {
//            JPanel findValuePanel = new JPanel();
//            controlPanel.add(findValuePanel);
//            JLabel findValueLabel = new JLabel("Введите значение х, в котором нужно найти значение функции");
//            findValuePanel.add(findValueLabel);
//            JTextField findValueField = new JTextField(3);
//            findValuePanel.add(findValueField);
//            JLabel valueLabel = new JLabel(String.format("f(%s)=%s", "?", "?"));
//            JButton findValueButton = new JButton("Найти");
//            findValuePanel.add(valueLabel);
//            findValuePanel.add(findValueButton);
//            findValueButton.addActionListener(e -> {
//                if (baseFunction2 == null)
//                    JOptionPane.showMessageDialog(mainFrame, "Сначала интерполируйте функцию",
//                            errorTitle, JOptionPane.ERROR_MESSAGE);
//                else {
//                    try {
//                        double value = baseFunction2.getValue(
//                                Double.parseDouble(findValueField.getText().replace(',', '.')));
//                        valueLabel.setText(String.format("f(%s)=%.3f", findValueField.getText(), value));
//                    } catch (NumberFormatException e1) {
//                        JOptionPane.showMessageDialog(mainFrame, "Значения Х должно быть числом",
//                                errorTitle, JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            });
//        }

    }
