package BiyDaalt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testCalculateGPA() {
        Student st = new Student("S001");
        Subject subj1 = new Subject("CS101", "Programming", 3);
        Subject subj2 = new Subject("CS102", "Database", 3);

        // ✅ Lessons рүү шилжүүлсэн
        st.addLesson(new Lessons(subj1, 95));
        st.addLesson(new Lessons(subj2, 85));

        st.calculateGPA();

        // GPA-г шалгах
        assertEquals(3.35f, st.GPA, 0.001f);
    }
}
