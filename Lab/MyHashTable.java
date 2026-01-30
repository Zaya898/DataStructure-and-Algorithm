import dataStructures.HashTable;

public class MyHashTable extends HashTable {

    public MyHashTable(int divisor) {
        super(divisor);
    }

    public Object updateElement(Object theKey, Object theElement) {
        Object elementToUpdate = get(theKey);
        if (elementToUpdate != null) {
            put(theKey, theElement);
            System.out.println("Түлхүүр " + theKey + " шинэчлэгдлээ.");
        } else {
            System.out.println("Түлхүүр " + theKey + " олдсонгүй, шинэчлэх боломжгүй.");
        }
        return elementToUpdate;
    }

    public Object updateKey(Object theKey, Object theNewKey) {
        Object elementToUpdate = get(theKey);
        if (elementToUpdate != null) {
            put(theNewKey, elementToUpdate);
            put(theKey, null);
            System.out.println("Түлхүүр " + theKey + " → " + theNewKey + " болгож шинэчлэгдлээ.");
        } else {
            System.out.println("Хуучин түлхүүр " + theKey + " олдсонгүй, шинэчлэх боломжгүй.");
        }
        return elementToUpdate;
    }

    public void delete(Object theKey) {
        if (get(theKey) != null) {
            put(theKey, null);
            System.out.println("Түлхүүр " + theKey + " устгагдлаа.");
        } else {
            System.out.println("Түлхүүр " + theKey + " олдсонгүй, устгах боломжгүй.");
        }
    }

    public void output() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < divisor; i++) {
            Object value = get(i);
            if (value != null) {
                System.out.println("Түлхүүр " + i + " → Утга = " + value);
            } else {
                System.out.println("Түлхүүр " + i + " → Утга = null");
            }
        }
    }

    public static void main(String[] args) {
        MyHashTable h = new MyHashTable(11);

        System.out.println("Анхны өгөгдөл");
        h.put(1, "Apple");
        h.put(2, "Banana");
        h.put(3, "Cherry");
        h.put(4, "Date");
        h.put(5, "Orange");
        h.output();

        System.out.println("\nТүлхүүр 2-ийн утгыг 'Blueberry'-ээр шинэчлэх");
        h.updateElement(2, "Blueberry");
        h.output();

        System.out.println("\nТүлхүүр 3-г 9 болгож шинэчлэх");
        h.updateKey(3, 9);
        h.output();

        System.out.println("\nТүлхүүр 1-г устгах");
        h.delete(1);
        h.output();

    }
}