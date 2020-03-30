package Part1.lab3.gui;

import Part1.lab3.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static Part1.lab3.Iterations.countXByIterations;
import static Part1.lab3.Newton.countXByNewton;

public class UserInterface {
    public static int tmpCounter;
    private JFrame mainFrame;
    private Function baseFunction;
    private JPanel mainPanel, graphPanel, controlPanel;
    private double[] xData;
    private int width, height;
    private final String errorTitle = "Ошибка";

    public UserInterface(Function baseFunction) {
        this.baseFunction = baseFunction;
    }

    public void draw(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            createMainFrame(width, height);
            mainFrame.setVisible(true);
            mainFrame.setResizable(false);
        });
    }

    public void createMainFrame(int width, int height)  {
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
        AtomicReference<JPanel> argsPanel = createGraphFields(firstXValueCount, xAmount);
        JTextField changeField = createChangeYValuePanel();
        createMakeGraphButton(changeField);
       // createInterpolatedValueField();
    }

    private JPanel getGraphPanel(int width, int height, double changeX) {

        Arrays.sort(xData);
        double[] yData = Arrays.stream(xData).map(x -> Double.compare(x, changeX) == 0 ?
                baseFunction.getValue(x) : baseFunction.getValue(x)).toArray();



        JPanel graphPanel = new GraphMaker(baseFunction, xData).
                getChart(width, height, changeX, baseFunction.getValue(changeX) );
        graphPanel.setLocation(0, 0);
        graphPanel.setSize(width, height);
        return graphPanel;
    }

    private void createSelectFunctionPanel() {
        JPanel selectFunctionPanel = new JPanel();
        JLabel label = new JLabel("Выберите функцию");
        selectFunctionPanel.add(label);
        JComboBox<String> selectedFunction = new JComboBox<>();
        selectedFunction.addItem("|x^3 - x| - 2*x = -1");
        selectedFunction.addItem("x^3 - 3*x^2 - 6*x = -3");
        selectedFunction.addItem("-5*x^2 - 2*x = 0");
        selectFunctionPanel.add(selectedFunction);
        controlPanel.add(selectFunctionPanel);
        selectedFunction.addActionListener(e -> {
            String function = (String)(((JComboBox)e.getSource()).getSelectedItem());
            switch (function) {
                case "|x^3 - x| - 2*x = -1":
                    this.baseFunction = arg -> Math.abs(Math.pow(arg, 3) - arg) - 2*arg +2;
                    tmpCounter = 1;
                    break;
                case "x^3 - 3*x^2 - 6*x = -3":
                    this.baseFunction = arg -> Math.pow(arg, 3) - 3 * Math.pow(arg, 2) + 6 * arg + 3;
                    tmpCounter = 2;
                    break;
                case "-5*x^2 - 2*x = 0":
                    this.baseFunction = arg -> arg*arg;
                    tmpCounter = 3;
                    break;
            }
        });
    }

    private AtomicReference<JPanel> createGraphFields(int firstXValue, int xAmount) {
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
                            if(Double.isInfinite(baseFunction.getValue(xData[index]))){
                                xData[index] = 0;
                                throw new IllegalArgumentException();
                            }
                        }
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(mainFrame, "Значения Х должны быть числами",
                                "Ошибка", JOptionPane.WARNING_MESSAGE);
                    }
                    catch (IllegalArgumentException e1){
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
        controlPanel.add(changePanel);
        JTextField changeField = new JTextField(7);
        return changeField;
    }

    private void createMakeGraphButton(JTextField changeField) {
        JButton iterationsButton = new JButton("Решить методом простых итераций");
        JButton newtonButton = new JButton("Решить методом касательных");
        newtonButton.addActionListener(event -> {
            double solutionNX;

            solutionNX = countXByNewton();
            mainPanel.remove(graphPanel);
            graphPanel = getGraphPanel(width, height, solutionNX);
            mainPanel.add(graphPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            JPanel findValuePanel = new JPanel();
            controlPanel.add(findValuePanel);
            JTextField findValueField = new JTextField(3);
            //findValuePanel.add(findValueField);
            JLabel valueLabel = new JLabel(String.format("f(%s)=%s", "?", "?"));
            findValuePanel.add(valueLabel);
            valueLabel.setText(String.format("f(%s)=%.3f", findValueField.getText(), solutionNX));

        });
        iterationsButton.addActionListener(event -> {
            double solutionX;

            solutionX = countXByIterations();
            mainPanel.remove(graphPanel);
            graphPanel = getGraphPanel(width, height, solutionX);
            mainPanel.add(graphPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            JPanel findValuePanel = new JPanel();
            controlPanel.add(findValuePanel);
            JTextField findValueField = new JTextField(3);
            JLabel valueLabel = new JLabel(String.format("f(%s)=%s", "?", "?"));
            findValuePanel.add(valueLabel);
            valueLabel.setText(String.format("f(%s)=%.3f", findValueField.getText(), solutionX));


        });
        controlPanel.add(newtonButton);
        controlPanel.add(iterationsButton);
    }

}
