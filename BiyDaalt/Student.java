package BiyDaalt;

import java.util.ArrayList;

public class Student {
    public String code;
    public float GPA;
    public ArrayList<Lessons> lessons = new ArrayList<>();

    public Student(String code) {
        this.code = code;
        this.GPA = 0.0F;
    }

    public void addLesson(Lessons l) {
        this.lessons.add(l);
    }

    private float convertToPoint(int score) {
        if (score >= 96 && score <= 100) return 4.0f;
        else if (score >= 91) return 3.7f;
        else if (score >= 88) return 3.4f;
        else if (score >= 84) return 3.0f;
        else if (score >= 81) return 2.7f;
        else if (score >= 78) return 2.4f;
        else if (score >= 74) return 2.0f;
        else if (score >= 71) return 1.7f;
        else if (score >= 68) return 1.3f;
        else if (score >= 64) return 1.0f;
        else if (score >= 60) return 0.7f;
        else return 0.0f;
    }

    public void calculateGPA() {
        if (!this.lessons.isEmpty()) {
            float total = 0.0F;
            float totalCredit = 0.0F;

            for (Lessons l : this.lessons) {
                float point = convertToPoint(l.score);
                total += l.learned.credit * point;
                totalCredit += l.learned.credit;
            }

            this.GPA = total / totalCredit;
        }
    }

    public static float calculateAverageGPA(ArrayList<Student> students) {
        if (students.isEmpty()) return 0.0f;
        float total = 0.0f;
        for (Student s : students) {
            total += s.GPA;
        }
        return total / students.size();
    }

    @Override
    public String toString() {
        return "Оюутан: " + this.code + ", GPA = " + String.format("%.2f", this.GPA);
    }
}
