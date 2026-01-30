import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyChainTest {

    @Test
    public void testToArray() {
        MyChain list = new MyChain();
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");

        assertArrayEquals(new Object[]{"A", "B", "C"}, list.toArray());
    }

    @Test
    public void testAddRange() {
        MyChain list = new MyChain();
        list.add(0, "X");
        list.addRange(new Object[]{"Y", "Z"});

        assertEquals(3, list.size());
        assertEquals("X", list.get(0));
        assertEquals("Y", list.get(1));
        assertEquals("Z", list.get(2));
    }

    @Test
    public void testUnion() {
        MyChain list1 = new MyChain();
        list1.add(0, 1);
        list1.add(1, 2);
        list1.add(2, 3);

        MyChain list2 = new MyChain();
        list2.add(0, 3);
        list2.add(1, 4);
        list2.add(2, 5);

        MyChain u = list1.union(list2);

        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, u.toArray());
    }

    @Test
    public void testIntersection() {
        MyChain list1 = new MyChain();
        list1.add(0, 1);
        list1.add(1, 2);
        list1.add(2, 3);

        MyChain list2 = new MyChain();
        list2.add(0, 3);
        list2.add(1, 2);
        list2.add(2, 5);

        MyChain i = list1.intersection(list2);

        assertArrayEquals(new Object[]{2, 3}, i.toArray());
    }
}
