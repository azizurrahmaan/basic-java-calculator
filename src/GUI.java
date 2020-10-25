
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azizu
 */
public class GUI {

    JFrame fr;
    JTextField tfSmall;
    JTextField tfLarge;
    LinkedHashMap buttons;
    JButton btnMC;
    JButton btnMR;
    JButton btnMPlus;
    JButton btnMMinus;
    JButton btnMS;
    JButton btnM;
    MouseHandler mouseHnd;
    ButtonHandler hnd;
    String memory;

    public GUI() {
        initGUI();
    }

    public void initGUI() {
        fr = new JFrame("Calculator");
        fr.setLayout(null);
        memory = null;

        tfSmall = new JTextField();
        tfSmall.setBounds(0, 0, 330, 30);
        tfSmall.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 20));
        tfSmall.setHorizontalAlignment(JTextField.TRAILING);
        tfSmall.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tfSmall.setForeground(new java.awt.Color(94, 94, 94));
        tfSmall.setEditable(false);

        tfLarge = new JTextField("0");
        tfLarge.setBounds(0, 31, 330, 80);
        tfLarge.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 8, 5, 8));
        tfLarge.setHorizontalAlignment(JTextField.TRAILING);
        tfLarge.setFont(new Font("Segoe UI", Font.BOLD, 50));
        tfLarge.setEditable(false);

        mouseHnd = new MouseHandler(this);
        hnd = new ButtonHandler(this);

        btnMC = new JButton("MC");
        btnMC.setBounds(3, 131, 53, 30);
        btnMC.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnMC.setBackground(new java.awt.Color(238, 238, 238));
        btnMC.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnMC.setFocusable(false);
        btnMC.addActionListener(hnd);
        btnMC.setEnabled(false);
        btnMC.addMouseListener(mouseHnd);
        fr.add(btnMC);

        btnMR = new JButton("MR");
        btnMR.setBounds(58, 131, 53, 30);
        btnMR.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnMR.setBackground(new java.awt.Color(238, 238, 238));
        btnMR.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnMR.setFocusable(false);
        btnMR.addActionListener(hnd);
        btnMR.setEnabled(false);
        btnMR.addMouseListener(mouseHnd);
        fr.add(btnMR);

        btnMPlus = new JButton("M+");
        btnMPlus.setBounds(113, 131, 53, 30);
        btnMPlus.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnMPlus.setBackground(new java.awt.Color(238, 238, 238));
        btnMPlus.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnMPlus.setFocusable(false);
        btnMPlus.addActionListener(hnd);
        btnMPlus.addMouseListener(mouseHnd);
        fr.add(btnMPlus);

        btnMMinus = new JButton("M-");
        btnMMinus.setBounds(168, 131, 53, 30);
        btnMMinus.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnMMinus.setBackground(new java.awt.Color(238, 238, 238));
        btnMMinus.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnMMinus.setFocusable(false);
        btnMMinus.addActionListener(hnd);
        btnMMinus.addMouseListener(mouseHnd);
        fr.add(btnMMinus);

        btnMS = new JButton("MS");
        btnMS.setBounds(222, 131, 53, 30);
        btnMS.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnMS.setBackground(new java.awt.Color(238, 238, 238));
        btnMS.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnMS.setFocusable(false);
        btnMS.addActionListener(hnd);
        btnMS.addMouseListener(mouseHnd);
        fr.add(btnMS);

        btnM = new JButton("M");
        btnM.setBounds(276, 131, 53, 30);
        btnM.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnM.setBackground(new java.awt.Color(238, 238, 238));
        btnM.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        btnM.setFocusable(false);
        btnM.setEnabled(false);
        btnM.addActionListener(hnd);
        btnM.addMouseListener(mouseHnd);
        fr.add(btnM);

        buttons = new LinkedHashMap();

        initializeButtons();

        Iterator iterator = buttons.entrySet().iterator();
        int i = 1, x = 3, y = 163, btnHeight = 50, bntWidth = 79;
        while (iterator.hasNext()) {

            Map.Entry entry = (Map.Entry) iterator.next();
            JButton btn = (JButton) entry.getValue();
            btn.setBounds(x, y, bntWidth, btnHeight);
            btn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            btn.setFocusable(false);
            btn.setActionCommand((String) entry.getKey());

            if (entry.getKey() == "0" || entry.getKey() == "1" || entry.getKey() == "2" || entry.getKey() == "3" || entry.getKey() == "4" || entry.getKey() == "5" || entry.getKey() == "6" || entry.getKey() == "7" || entry.getKey() == "8" || entry.getKey() == "9") {
                btn.setBackground(new java.awt.Color(251, 251, 251));
                btn.setFont(new Font("Segoe UI", Font.BOLD, 25));
            } else {
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 19));
                btn.setBackground(new java.awt.Color(244, 244, 244));
            }

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (entry.getKey() == "/" || entry.getKey() == "+" || entry.getKey() == "*" || entry.getKey() == "-" || entry.getKey() == "=") {
                        btn.setBackground(new java.awt.Color(85, 123, 114));
                    } else {
                        btn.setBackground(new java.awt.Color(219, 218, 218));
                    }
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (entry.getKey() == "0" || entry.getKey() == "1" || entry.getKey() == "2" || entry.getKey() == "3" || entry.getKey() == "4" || entry.getKey() == "5" || entry.getKey() == "6" || entry.getKey() == "7" || entry.getKey() == "8" || entry.getKey() == "9") {
                        btn.setBackground(new java.awt.Color(251, 251, 251));
                    } else {
                        btn.setBackground(new java.awt.Color(244, 244, 244));
                    }
                }
            });
            btn.addActionListener(hnd);

            x = x + bntWidth + 3;
            if (i % 4 == 0) {
                y = y + btnHeight + 3;
                x = 3;
            }
            i = i + 1;
        }
        
        fr.add(tfLarge);
        fr.add(tfSmall);
        addButtonsToFrame();

        fr.setMinimumSize(new Dimension(337, 510));
        fr.setResizable(false);
        fr.setLocation(300, 200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    private void initializeButtons() {
        Icon ico = new ImageIcon("percentage.png");
        buttons.put("%", new JButton(ico));

        ico = new ImageIcon("sqrt.png");
        buttons.put("sqrt", new JButton(ico));

        ico = new ImageIcon("sqr.png");
        buttons.put("x^2", new JButton(ico));

        ico = new ImageIcon("resiprocal.png");
        buttons.put("1/x", new JButton(ico));

        buttons.put("CE", new JButton("CE"));
        buttons.put("C", new JButton("C"));

        ico = new ImageIcon("backspace.png");
        buttons.put("<-X", new JButton(ico));

        ico = new ImageIcon("divide.png");
        buttons.put("/", new JButton(ico));

        buttons.put("7", new JButton("7"));
        buttons.put("8", new JButton("8"));
        buttons.put("9", new JButton("9"));

        ico = new ImageIcon("multilpy.png");
        buttons.put("*", new JButton(ico));

        buttons.put("4", new JButton("4"));
        buttons.put("5", new JButton("5"));
        buttons.put("6", new JButton("6"));

        ico = new ImageIcon("minus.png");
        buttons.put("-", new JButton(ico));

        buttons.put("1", new JButton("1"));
        buttons.put("2", new JButton("2"));
        buttons.put("3", new JButton("3"));

        ico = new ImageIcon("plus.png");
        buttons.put("+", new JButton(ico));

        ico = new ImageIcon("plus-minus.png");
        buttons.put("+/-", new JButton(ico));

        buttons.put("0", new JButton("0"));
        buttons.put(".", new JButton("."));
        ico = new ImageIcon("equal.png");
        buttons.put("=", new JButton(ico));
    }

    private void addButtonsToFrame() {
        fr.add((JButton) buttons.get("%"));
        fr.add((JButton) buttons.get("sqrt"));
        fr.add((JButton) buttons.get("x^2"));
        fr.add((JButton) buttons.get("1/x"));
        fr.add((JButton) buttons.get("CE"));
        fr.add((JButton) buttons.get("C"));
        fr.add((JButton) buttons.get("<-X"));
        fr.add((JButton) buttons.get("/"));
        fr.add((JButton) buttons.get("7"));
        fr.add((JButton) buttons.get("8"));
        fr.add((JButton) buttons.get("9"));
        fr.add((JButton) buttons.get("*"));
        fr.add((JButton) buttons.get("4"));
        fr.add((JButton) buttons.get("5"));
        fr.add((JButton) buttons.get("6"));
        fr.add((JButton) buttons.get("-"));
        fr.add((JButton) buttons.get("1"));
        fr.add((JButton) buttons.get("2"));
        fr.add((JButton) buttons.get("3"));
        fr.add((JButton) buttons.get("+"));
        fr.add((JButton) buttons.get("+/-"));
        fr.add((JButton) buttons.get("0"));
        fr.add((JButton) buttons.get("."));
        fr.add((JButton) buttons.get("="));
    }
}
