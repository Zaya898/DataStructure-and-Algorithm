package BiyDaalt;

import org.junit.Test;
import static org.junit.Assert.*;

public class MajorTest {

    @Test
    public void testMatchesStudent() {
        Major m = new Major("1910", "Computer Science");
        String studentCode = "B241910023";
        assertTrue(m.matchesStudent(studentCode));

        String wrongCode = "B202010912";
        assertFalse(m.matchesStudent(wrongCode));
    }
}
