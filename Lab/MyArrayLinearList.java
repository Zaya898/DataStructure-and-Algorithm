import java.util.Scanner;

public class MyArrayLinearList {
    int[] data;
    int size = 0;

    public MyArrayLinearList(int a) {
        data = new int[a];
    }

    public void add(int value) {
        data[size] = value;
        size++;
    }

    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public int max() {
        int maxVal = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i] > maxVal) {
                maxVal = data[i];
            }
        }
        return maxVal;
    }

    public int min() {
        int minVal = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i] < minVal) {
                minVal = data[i];
            }
        }
        return minVal;
    }

    public int sum() {
        int s = 0;
        for (int i = 0; i < size; i++) {
            s += data[i];
        }
        return s;
    }

    public double average() {
        return (double) sum() / size;
    }

    public void removeOdd() {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (data[i] % 2 == 0) {
                data[j] = data[i];
                j++;
            }
        }
        size = j;
    }

    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int t = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a;
        while (true) {
            System.out.print("Массивийн хэмжээг оруулна уу: ");
            if (sc.hasNextInt()) {
                a = sc.nextInt();
                if (a > 0) {
                    break;
                } else {
                    System.out.println("Алдаа: Хэмжээ 0-ээс их байх ёстой!");
                }
            } else {
                System.out.println("Алдаа: зөвхөн бүхэл тоо оруулна уу!");
                sc.next();
            }
        }

        MyArrayLinearList list = new MyArrayLinearList(a);

        for (int i = 0; i < a; i++) {
            int x;
            while (true) {
                System.out.print((i + 1) + "-р тоо: ");
                if (sc.hasNextInt()) {
                    x = sc.nextInt();
                    break;
                } else {
                    System.out.println("Алдаа: зөвхөн бүхэл тоо оруулна уу!");
                    sc.next();
                }
            }
            list.add(x);
        }

        System.out.print("Жагсаалт: ");
        list.printList();

        System.out.println("Хамгийн их: " + list.max());
        System.out.println("Хамгийн бага: " + list.min());
        System.out.println("Нийлбэр: " + list.sum());
        System.out.println("Дундаж: " + list.average());

        list.removeOdd();
        System.out.print("Сондгойг устгасны дараа: ");
        list.printList();

        list.sort();
        System.out.print("Эрэмбэлсний дараа: ");
        list.printList();
    }
}
