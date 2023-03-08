package org.example;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main implements ActionListener {
    private static final JFrame frame = new JFrame("Calculator");
    private static JTextArea textArea;
    static Map<String,JButton> functionalButton = new HashMap<>();
    static Map<String,JButton> buttonNumber = new HashMap<>();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        createTextArea();
        createFunctionalButton();
        createNumberButton();
        addButtonOnFrame();
        addButtonListener();

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void createTextArea() {
        textArea = new JTextArea();
        frame.add(textArea);

        textArea.setFont(new Font("TimesRoman", Font.BOLD, 16));
        textArea.setMargin(new Insets(15,10,10,10));
        textArea.setBounds(40,20,320,60);
    }

    private static void createFunctionalButton(){
        functionalButton.put("Reset",Button.createButton("AC",40,100));
        functionalButton.put("Delete",Button.createButton("<-",120,100));
        functionalButton.put("Remainder",Button.createButton("%",200,100));
        functionalButton.put("Division",Button.createButton("/",280,100));
        functionalButton.put("Multiply",Button.createButton("*",280,150));
        functionalButton.put("Minus",Button.createButton("-",280,200));
        functionalButton.put("Plus",Button.createButton("+",280,250));
        functionalButton.put("Equals",Button.createButton("=",280,300));
        functionalButton.put("Point",Button.createButton(",",200,300));
    }

    private static void createNumberButton(){
        buttonNumber.put("Zero",Button.createButton("0",120,300));
        buttonNumber.put("One",Button.createButton("1",40,250));
        buttonNumber.put("Two",Button.createButton("2",120,250));
        buttonNumber.put("Three",Button.createButton("3",200,250));
        buttonNumber.put("Four",Button.createButton("4",40,200));
        buttonNumber.put("Five",Button.createButton("5",120,200));
        buttonNumber.put("Six",Button.createButton("6",200,200));
        buttonNumber.put("Seven",Button.createButton("7",40,150));
        buttonNumber.put("Eight",Button.createButton("8",120,150));
        buttonNumber.put("Nine",Button.createButton("9",200,150));
    }

    private static void addButtonOnFrame(){
        functionalButton.forEach((k,v) -> {frame.add(v);});
        buttonNumber.forEach((k,v) -> {frame.add(v);});
    }

    private static void addButtonListener(){
        addNumberButtonListener();
        addFunctionalButtonListener();
    }

    private static void addFunctionalButtonListener() {
        functionalButton.get("Reset").addActionListener(e -> textArea.setText(""));
        functionalButton.get("Delete").addActionListener(e -> {
            try {
                int l = textArea.getText().length();
                if (l > 0)
                    textArea.setText(textArea.getText(0,(l-1)));
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        functionalButton.get("Remainder").addActionListener(e -> textArea.setText(textArea.getText() + "%"));
        functionalButton.get("Division").addActionListener(e -> textArea.setText(textArea.getText() + "/"));
        functionalButton.get("Multiply").addActionListener(e -> textArea.setText(textArea.getText() + "*"));
        functionalButton.get("Minus").addActionListener(e -> textArea.setText(textArea.getText() + "-"));
        functionalButton.get("Plus").addActionListener(e -> textArea.setText(textArea.getText() + "+"));
        functionalButton.get("Point").addActionListener(e -> textArea.setText(textArea.getText() + ","));

        functionalButton.get("Equals").addActionListener(e -> calculate());
    }

    private static void calculate() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object result = engine.eval(textArea.getText());
            textArea.setText(result.toString());
        } catch (ScriptException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void addNumberButtonListener(){
        buttonNumber.get("Zero").addActionListener(e -> textArea.setText(textArea.getText() + "0"));
        buttonNumber.get("One").addActionListener(e -> textArea.setText(textArea.getText() + "1"));
        buttonNumber.get("Two").addActionListener(e -> textArea.setText(textArea.getText() + "2"));
        buttonNumber.get("Three").addActionListener(e -> textArea.setText(textArea.getText() + "3"));
        buttonNumber.get("Four").addActionListener(e -> textArea.setText(textArea.getText() + "4"));
        buttonNumber.get("Five").addActionListener(e -> textArea.setText(textArea.getText() + "5"));
        buttonNumber.get("Six").addActionListener(e -> textArea.setText(textArea.getText() + "6"));
        buttonNumber.get("Seven").addActionListener(e -> textArea.setText(textArea.getText() + "7"));
        buttonNumber.get("Eight").addActionListener(e -> textArea.setText(textArea.getText() + "8"));
        buttonNumber.get("Nine").addActionListener(e -> textArea.setText(textArea.getText() + "9"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


//    String str = textArea.getText();
//            System.out.println(str);
//
//                    String[] numbers = str.split("[+*/%-]");
//
//                    for (String st : numbers){
//                    System.out.println(st);
//                    }
//
//                    char[] arr = str.toCharArray();
//                    char[] symbols = new char[numbers.length-1];
//                    for (int i=0,j=0;i<arr.length;i++){
//        if (arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*' || arr[i] == '%'){
//        symbols[j] = arr[i];
//        System.out.println(symbols[j]);
//        j++;
//        }
//        }
//
//        double res = 0;
//        String symbolsStr = Arrays.toString(symbols);
//        if (symbolsStr.contains("*")){
//        int index = symbolsStr.indexOf("*");
//        res = Double.parseDouble(numbers[index]) * Double.parseDouble(numbers[index+1]);
//
//        }