import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Café Order GUI using JTextField and JTextArea */
@SuppressWarnings("serial")
public class CafeOrderDemo extends JFrame {
    JTextField coffeeField;
    JTextField sandwichField;
    JTextField pastryField;
    JTextArea tArea;
    JFormattedTextField dateField;
     JButton btnCalculate, btnClear, btnExit;

    public CafeOrderDemo() {
        JPanel tfPanel = new JPanel(new GridLayout(4, 2, 10, 2));
        tfPanel.setBorder(BorderFactory.createTitledBorder("Order Items: "));
        //Coffee
        tfPanel.add(new JLabel("  Coffee (qty): "));
        coffeeField = new JTextField(5);
        tfPanel.add(coffeeField);
        coffeeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tArea.append("\nCoffee ordered: " + coffeeField.getText());
            }
        });

        // Sandwich 
        tfPanel.add(new JLabel("  Sandwich (qty): "));
        sandwichField = new JTextField(5);
        tfPanel.add(sandwichField);
        sandwichField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tArea.append("\nSandwich ordered: " + sandwichField.getText());
            }
        });

        //Pastry 
        tfPanel.add(new JLabel("  Pastry (qty): "));
        pastryField = new JTextField(5);
        tfPanel.add(pastryField);
        pastryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tArea.append("\nPastry ordered: " + pastryField.getText());
            }
        });

        // Date
        tfPanel.add(new JLabel("  Order Date: "));
        dateField = new JFormattedTextField(java.util.Calendar.getInstance().getTime());
        tfPanel.add(dateField);

        //Text 
        tArea = new JTextArea(
            "Welcome to the Café!\n"
        );
        tArea.setFont(new Font("Serif", Font.ITALIC, 13));
        tArea.setLineWrap(true);
        tArea.setWrapStyleWord(true);
        tArea.setBackground(new Color(240, 248, 255)); 
        JScrollPane tAreaScrollPane = new JScrollPane(tArea);
        tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        //button
        btnCalculate = new JButton("Calculate");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);
        btnClear.addActionListener(e -> {
            coffeeField.setText("");
            sandwichField.setText("");
            pastryField.setText("");
            dateField.setValue(null);
            tArea.setText("");
        });
      btnCalculate.addActionListener(e ->
            tArea.append("\nCalculation done for Coffee: " + coffeeField.getText() + ", Pastry: " + pastryField.getText()));
        
        btnExit.addActionListener(e -> System.exit(0));
        cp.setLayout(new BorderLayout(10, 10));
        cp.add(tfPanel, BorderLayout.WEST);
        cp.add(tAreaScrollPane, BorderLayout.CENTER);
        cp.add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Café Order System+Button Demo");
        setSize(400, 400);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CafeOrderDemo();
            }
        });
    }
}

