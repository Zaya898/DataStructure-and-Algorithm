import dataStructures.LinkedBinaryTree;
import java.util.Scanner;
import java.util.Stack;

public class Expression extends LinkedBinaryTree {

    static Scanner sc = new Scanner(System.in);

    static boolean isOperator(String s) {
        return "+-*/".contains(s);
    }

    public static Expression fromPostfix(String exp) {
        Stack<Expression> st = new Stack<>();
        String[] t = exp.split("\\s+");

        for (String s : t) {
            Expression e = new Expression();

            if (isOperator(s)) {
                Expression r = st.pop();
                Expression l = st.pop();
                e.makeTree(s, l, r);
            } else {
                e.makeTree(s, new Expression(), new Expression());
            }
            st.push(e);
        }
        return st.pop();
    }

    public static Expression fromPrefix(String exp) {
        Stack<Expression> st = new Stack<>();
        String[] t = exp.split("\\s+");

        for (int i = t.length - 1; i >= 0; i--) {
            Expression e = new Expression();

            if (isOperator(t[i])) {
                Expression l = st.pop();
                Expression r = st.pop();
                e.makeTree(t[i], l, r);
            } else {
                e.makeTree(t[i], new Expression(), new Expression());
            }
            st.push(e);
        }
        return st.pop();
    }

    public static Expression fromInfix(String exp) {
        Stack<Expression> st = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == ' ' || c == '(') continue;

            if (c == ')') {
                Expression r = st.pop();
                Expression op = st.pop();
                Expression l = st.pop();
                op.makeTree(op.root(), l, r);
                st.push(op);
            } else if (isOperator(String.valueOf(c))) {
                Expression e = new Expression();
                e.makeTree(String.valueOf(c), new Expression(), new Expression());
                st.push(e);
            } else {
                Expression e = new Expression();
                e.makeTree(String.valueOf(c), new Expression(), new Expression());
                st.push(e);
            }
        }
        return st.pop();
    }

    public void printPrefix() {
        preOrderOutput();
        System.out.println();
    }

    public void printPostfix() {
        postOrderOutput();
        System.out.println();
    }

    public void printInfix() {
        printInfix(this);
        System.out.println();
    }

    private void printInfix(LinkedBinaryTree t) {
        if (t.isEmpty()) return;

        LinkedBinaryTree l = (LinkedBinaryTree) t.removeLeftSubtree();
        LinkedBinaryTree r = (LinkedBinaryTree) t.removeRightSubtree();

        boolean op = isOperator(t.root().toString());
        if (op) System.out.print("(");

        printInfix(l);
        System.out.print(t.root());
        printInfix(r);

        if (op) System.out.print(")");

        t.makeTree(t.root(), l, r);
    }

    public double evaluate() {
        return eval(this);
    }

    private double eval(LinkedBinaryTree t) {
        Object r = t.root();

        if (!isOperator(r.toString())) {
            if (Character.isLetter(r.toString().charAt(0))) {
                System.out.print(r + " = ");
                return sc.nextDouble();
            }
            return Double.parseDouble(r.toString());
        }

        LinkedBinaryTree l = (LinkedBinaryTree) t.removeLeftSubtree();
        LinkedBinaryTree rr = (LinkedBinaryTree) t.removeRightSubtree();

        double a = eval(l);
        double b = eval(rr);

        t.makeTree(r, l, rr);

        switch (r.toString()) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {

        System.out.println("POSTFIX");
        Expression p = fromPostfix("a b c * +");
        p.printPostfix();
        System.out.println("Value = " + p.evaluate());

        System.out.println("\nPREFIX");
        Expression pr = fromPrefix("+ a * b c");
        pr.printPrefix();
        System.out.println("Value = " + pr.evaluate());

        System.out.println("\nINFIX");
        Expression i = fromInfix("(a+(b*c))");
        i.printPostfix();
        i.printInfix();
        System.out.println("Value = " + i.evaluate());
    }
}
