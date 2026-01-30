package BiyDaalt;
import java.io.*;
import java.util.*;

public class CarParking {
    public Stack<String> garage;
    public int capacity;

    public CarParking(int capacity) {
        this.capacity = capacity;
        this.garage = new Stack<>();
    }

    public  boolean isFull() {
        return garage.size() >= capacity;
    }

    public void input(String filename) {
        process(filename);
    }

    public void process(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String cmd = sc.next();
                String car = sc.next();

                if (cmd.equals("A")) {
                    arrival(car);
                } else if (cmd.equals("D")) {
                    departure(car);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл олдсонгүй: " + filename);
        }
    }

    public void arrival(String car) {
        if (isFull()) {
            System.out.println("Arrival " + car + " -> Garage full, this car cannot enter.");
        } else {
            garage.push(car);
            System.out.println("Arrival " + car + " -> There is room.");
        }
    }

    public void departure(String car) {
        if (garage.isEmpty()) {
            System.out.println("Departure " + car + " -> This car not in the garage.");
            return;
        }

        if (!garage.contains(car)) {
            System.out.println("Departure " + car + " -> This car not in the garage.");
            return;
        }

        Stack<String> temp = new Stack<>();
        while (!garage.peek().equals(car)) {
            temp.push(garage.pop());
        }

        garage.pop();
        System.out.println("Departure " + car + " -> " + (temp.size() + 1) + " cars moved out.");

        while (!temp.isEmpty()) {
            garage.push(temp.pop());
        }
    }

    public void output() {
        System.out.println("===== Үлдсэн машинууд =====");
        if (garage.isEmpty()) {
            System.out.println("Гараж хоосон байна.");
        } else {
            for (String car : garage) {
                System.out.println(car);
            }
        }
    }

    public static void main(String[] args) {
        CarParking carPark = new CarParking(5);  // гаражийн багтаамж
        carPark.input("cars.txt");
        carPark.output();
    }
}
