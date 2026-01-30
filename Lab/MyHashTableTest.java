import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MyHashTableTest {

    @Test
    void testPutAndGet() {
        MyHashTable h = new MyHashTable(11);
        h.put(1, "Apple");
        assertEquals("Apple", h.get(1));
    }

    @Test
    void testUpdateElement() {
        MyHashTable h = new MyHashTable(11);
        h.put(2, "Banana");
        h.updateElement(2, "Mango");
        assertEquals("Mango", h.get(2));
    }

    @Test
    void testUpdateKey() {
        MyHashTable h = new MyHashTable(11);
        h.put(3, "Cherry");
        h.updateKey(3, 9);
        assertEquals("Cherry", h.get(9));
        assertNull(h.get(3));
    }

    @Test
    void testDelete() {
        MyHashTable h = new MyHashTable(11);
        h.put(4, "Date");
        h.delete(4);
        assertNull(h.get(4));
    }
}
