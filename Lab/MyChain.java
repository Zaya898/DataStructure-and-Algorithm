import dataStructures.Chain;

public class MyChain extends Chain {
    public Object[] toArray() {
        Object[] arr = new Object[this.size];

        for (int i = 0; i < this.size; ++i) {
            arr[i] = this.get(i);
        }

        return arr;
    }

    public void addRange(Object[] elements) {
        for (int i = 0; i < elements.length; ++i) {
            this.add(this.size, elements[i]);
        }

    }

    public MyChain union(MyChain other) {
        MyChain result = new MyChain();

        for (int i = 0; i < this.size; ++i) {
            result.add(result.size, this.get(i));
        }

        for (int i = 0; i < other.size; ++i) {
            Object element = other.get(i);
            if (result.indexOf(element) == -1) {
                result.add(result.size, element);
            }
        }

        return result;
    }

    public MyChain intersection(MyChain other) {
        MyChain result = new MyChain();

        for (int i = 0; i < this.size; ++i) {
            Object element = this.get(i);
            if (other.indexOf(element) != -1 && result.indexOf(element) == -1) {
                result.add(result.size, element);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MyChain list1 = new MyChain();
        list1.add(0, 1);
        list1.add(1, 2);
        list1.add(2, 3);
        MyChain list2 = new MyChain();
        list2.add(0, 3);
        list2.add(1, 4);
        list2.add(2, 5);
        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        MyChain u = list1.union(list2);
        System.out.println("Union: " + u);
        MyChain i = list1.intersection(list2);
        System.out.println("Intersection: " + i);
        Object[] arr = list1.toArray();
        System.out.print("List1 to array: ");

        for (Object o : arr) {
            System.out.print(o + " ");
        }

    }
}
