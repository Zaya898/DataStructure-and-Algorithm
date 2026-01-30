package BiyDaalt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Registration {
    public ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList<Subject> subjectList = new ArrayList<>();
    public ArrayList<Major> majorList = new ArrayList<>();

    // Хичээлийн мэдээллийг файлнаас унших
    public void loadSubjects(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split("/");
                String code = values[0];
                String name = values[1];
                float credit = Float.parseFloat(values[2]);
                this.subjectList.add(new Subject(code, name, credit));
            }
        } catch (Exception e) {
            System.out.println("Хичээлийн файл уншихад алдаа гарлаа: " + e);
        }
    }

    // Мэргэжлийн мэдээллийг файлнаас унших
    public void loadMajors(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split("/");
                String code = values[0];
                String name = values[1];
                this.majorList.add(new Major(code, name));
            }
        } catch (Exception e) {
            System.out.println("Мэргэжлийн файл уншихад алдаа гарлаа: " + e);
        }
    }

    // Шалгалтын мэдээллийг файлнаас уншиж Student объект руу Lessons нэмэх
    public void loadExams(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split("/");
                String studentCode = values[0];
                String subjectCode = values[1];
                int score = Integer.parseInt(values[2]);

                Subject subj = this.findSubject(subjectCode);
                if (subj != null) {
                    Student st = this.findStudent(studentCode);
                    if (st == null) {
                        st = new Student(studentCode);
                        this.studentList.add(st);
                    }

                    st.addLesson(new Lessons(subj, score)); // Exam → Lessons
                }
            }
        } catch (Exception e) {
            System.out.println("Шалгалтын файл уншихад алдаа гарлаа: " + e);
        }
    }

    // Subject олж буцаах
    public Subject findSubject(String code) {
        for (Subject s : this.subjectList) {
            if (s.code.equals(code)) return s;
        }
        return null;
    }

    // Student олж буцаах
    public Student findStudent(String code) {
        for (Student s : this.studentList) {
            if (s.code.equals(code)) return s;
        }
        return null;
    }

    // Хичээлийн жагсаалт хэвлэх
    public void printSubjects() {
        System.out.println("Хичээлийн жагсаалт:");
        for (Subject s : this.subjectList) {
            System.out.println(s);
        }
    }

    // Мэргэжлийн жагсаалт хэвлэх
    public void printMajors() {
        System.out.println("Мэргэжлийн жагсаалт:");
        for (Major m : this.majorList) {
            System.out.println(m);
        }
    }

    // Оюутны жагсаалт хэвлэх
    public void printStudents() {
        System.out.println("Оюутны жагсаалт:");
        for (Student s : this.studentList) {
            s.calculateGPA();
            System.out.println(s);
        }
    }

    // Дундаж GPA хэвлэх
    public void printAverageGPA() {
        if (this.studentList.isEmpty()) {
            System.out.println("Оюутан байхгүй байна.");
            return;
        }

        for (Student s : this.studentList) {
            s.calculateGPA();
        }

        float avg = Student.calculateAverageGPA(this.studentList);
        System.out.println("\nОюутнуудын дундаж GPA = " + String.format("%.2f", avg));
    }

    // F үнэлгээ авсан оюутнуудыг хэвлэх
    public void printFailedStudents() {
        System.out.println("F үнэлгээ авсан оюутнууд:");
        for (Student s : this.studentList) {
            for (Lessons l : s.lessons) {
                if (l.score < 60) {
                    System.out.println(s.code + " -> " + l.learned.name + ": " + l.score);
                }
            }
        }
    }

    // Хичээл бүрээр оюутнуудын дүнг хэвлэх
    public void printGradesBySubject() {
        System.out.println("Хичээл бүрээр оюутнуудын дүн:");
        for (Subject subj : this.subjectList) {
            System.out.println("\n" + subj.name + ":");

            for (Student s : this.studentList) {
                for (Lessons l : s.lessons) {
                    if (l.learned.code.equals(subj.code)) {
                        System.out.println("   " + s.code + " -> " + l.score);
                    }
                }
            }
        }
    }

    // Мэргэжил бүрээр оюутнууд болон дүнг хэвлэх
    public void printStudentsByMajor() {
        System.out.println("Мэргэжил бүрээр оюутнууд болон дүн:");

        for (Major m : this.majorList) {
            System.out.println("\n" + m.name + ":");

            for (Student s : this.studentList) {
                if (m.matchesStudent(s.code)) {
                    s.calculateGPA();
                    System.out.println("   " + s.code + " (GPA: " + String.format("%.2f", s.GPA) + ")");

                    for (Lessons l : s.lessons) {
                        System.out.println("       " + l.learned.code + " - " + l.learned.name + ": " + l.score);
                    }
                }
            }
        }
    }
}
