import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Calculator extends JFrame {
    private JPanel panel;
    private JLabel ans, op, eq;
    private JTextField num1, num2;
    private JButton add, sub, mul, div, ok;

    public Calculator() {
        setBounds(300, 200, 400, 200);
        setTitle("Simple Calculator");

        num1 = new JTextField("12");
        num2 = new JTextField("2");
        op = new JLabel("",JLabel.CENTER);
        eq = new JLabel("=",JLabel.CENTER);
        ans = new JLabel("",JLabel.CENTER);

        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        ok = new JButton("ok");

        panel = new JPanel();

        //2 row 5 col
        panel.setLayout(new GridLayout(2,5));
    }
    private void addButton() {
        panel.add(num1);
        panel.add(op);
        panel.add(num2);
        panel.add(eq);
        panel.add(ans);

        panel.add(add);
        panel.add(sub);
        panel.add(mul);
        panel.add(div);
        panel.add(ok);

        add(panel);
    }
    private boolean isNumeric(String s) {
        int len = s.length();
        boolean hasPoint = false;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '-') {
                if (i != 0)
                    return false;
            } else if (s.charAt(i) == '.'){
                if (hasPoint)
                    return false;
                hasPoint = true;
            } else if (s.charAt(i) < '0' || '9' < s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private class numberListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            ans.setText("");
            String s1 = num1.getText();
            String s2 = num2.getText();
            if (isNumeric(s1) && isNumeric(s2)) {
                double n1 = Double.parseDouble(s1);
                double n2 = Double.parseDouble(s2);
                if (op.getText().equals("+")) {
                    ans.setText("" + (n1 + n2));
                }
                if (op.getText().equals("-")) {
                    ans.setText("" + (n1 - n2));
                }
                if (op.getText().equals("*")) {
                    ans.setText("" + (n1 * n2));
                }
                if (op.getText().equals("/")) {
                    if (n2 == 0) {
                        ans.setText("Error!");
                    } else {
                        ans.setText("" + (n1 / n2));
                    }
                }
            } else {
                ans.setText("Error!");
            }
        }
    }
    private class operatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            op.setText(((JButton)e.getSource()).getText());
        }
    }
    private void inputAndCalculate() {
        operatorListener chop = new operatorListener();
        add.addActionListener(chop);
        sub.addActionListener(chop);
        mul.addActionListener(chop);
        div.addActionListener(chop);

        numberListener res = new numberListener();
        ok.addActionListener(res);
    }
    private void display() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void run() {
        addButton();
        inputAndCalculate();
        display();
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.run();
    }
}
