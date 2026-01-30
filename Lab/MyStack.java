import dataStructures.Stack;

import java.util.Scanner;

public class MyStack implements Stack {
    Object[] elements;
    int top;
    int capacity;

    public MyStack(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        top = 0;
    }

    public boolean empty() {
        return top == 0;
    }

    public Object peek() {
        if (empty()) return null;
        return elements[top - 1];
    }

    public void push(Object x) {
        if (top == capacity) {
            System.out.println("Стек дүүрсэн!");
            return;
        }
        elements[top] = x;
        top++;
    }

    public Object pop() {
        if (empty()) {
            System.out.println("Стек хоосон!");
            return null;
        }
        top--;
        return elements[top];
    }

    public int size() {
        return top;
    }

    public void inputStack() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Хэдэн элемент оруулах вэ? ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "-р элемент: ");
            String val = sc.nextLine();
            push(val);
        }
        sc.close();
    }

    public void printStack() {
        if (empty()) {
            System.out.println("Стек хоосон!");
            return;
        }
        System.out.print("Стек: ");
        for (int i = 0; i < top; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    public MyStack[] splitStack() {
        int mid = size() / 2;
        MyStack first = new MyStack(mid);
        MyStack second = new MyStack(size() - mid);

        for (int i = 0; i < mid; i++) {
            first.push(elements[i]);
        }
        for (int i = mid; i < top; i++) {
            second.push(elements[i]);
        }
        return new MyStack[]{first, second};
    }

    public static MyStack combineStack(MyStack s1, MyStack s2) {
        MyStack combined = new MyStack(s1.size() + s2.size());
        for (int i = 0; i < s1.top; i++) {
            combined.push(s1.elements[i]);
        }
        for (int i = 0; i < s2.top; i++) {
            combined.push(s2.elements[i]);
        }
        return combined;
    }

    public static void main(String[] args) {
        MyStack st = new MyStack(10);

        st.inputStack();
        st.printStack();

        MyStack[] parts = st.splitStack();
        System.out.print("Эхний хэсэг: ");
        parts[0].printStack();
        System.out.print("Хоёр дахь хэсэг: ");
        parts[1].printStack();

        MyStack combined = combineStack(parts[0], parts[1]);
        System.out.print("Нийлүүлсэн стек: ");
        combined.printStack();
    }
}
