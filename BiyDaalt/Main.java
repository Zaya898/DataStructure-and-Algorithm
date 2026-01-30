package BiyDaalt;

public class Main {
    public static void main(String[] args) {
        Registration reg = new Registration();
        reg.loadSubjects("Subjects.txt");
        reg.loadMajors("Professions.txt");
        reg.loadExams("Exams.txt");
        reg.printSubjects();
        reg.printMajors();
        reg.printStudents();
        reg.printAverageGPA();
        reg.printFailedStudents();
        reg.printGradesBySubject();
        reg.printStudentsByMajor();
    }
}
