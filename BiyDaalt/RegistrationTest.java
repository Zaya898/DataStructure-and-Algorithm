package BiyDaalt;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationTest {

    @Test
    public void testFindSubjectAndStudent() {
        Registration reg = new Registration();

        Subject s1 = new Subject("CS101", "Programming", 3);
        reg.subjectList.add(s1);

        Student st1 = new Student("ST1001");
        reg.studentList.add(st1);

        assertEquals(s1, reg.findSubject("CS101"));

        assertEquals(st1, reg.findStudent("ST1001"));
    }
}
