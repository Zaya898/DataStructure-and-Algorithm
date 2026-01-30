import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    @Test
    public void testEmptyStack() {
        MyStack st = new MyStack(5);
        assertTrue(st.empty());
        assertEquals(0, st.size());
    }

    @Test
    public void testPushAndPeek() {
        MyStack st = new MyStack(5);
        st.push("A");
        assertFalse(st.empty());
        assertEquals("A", st.peek());
    }

    @Test
    public void testPushAndPop() {
        MyStack st = new MyStack(5);
        st.push("A");
        st.push("B");
        assertEquals("B", st.pop());
        assertEquals("A", st.pop());
        assertTrue(st.empty());
    }

    @Test
    public void testSize() {
        MyStack st = new MyStack(5);
        st.push(1);
        st.push(2);
        st.push(3);
        assertEquals(3, st.size());
    }
}
