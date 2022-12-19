package test;

import common.InvalidIndexException;
import common.ListNode;
import interfaces.IListManipulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AdditionalTests {

    private IListManipulator manipulator;

    private final int element1 = 1;

    private final int element2 = 5;

    private final int element3 = 9;

    private final int element4 = 11;

    private ListNode list1;

    private ListNode list2;

    private ListNode list3;

    private ListNode list4;

    public abstract IListManipulator makeListManipulator();

    @BeforeEach
    public void setup() {
        manipulator = makeListManipulator();
        list1 = new ListNode(element4);
        list2 = new ListNode(element3, list1);
        list3 = new ListNode(element2, list2);
        list4 = new ListNode(element1, list3);
    }


    /*
    Provides additional tests to ensure sort method works correctly:
    - sorting a sorted list
    - sorting a descending sorted list
    - sorting a list with duplicates
     */
    @Test
    public void sort() {

        assertTrue(manipulator.equals(list4, manipulator.sort(list4, int_comparator)));

        ListNode descSortedList = manipulator.reverse(manipulator.sort(list4, int_comparator));
        assertTrue(manipulator.equals(list4, manipulator.sort(descSortedList, int_comparator)));

        ListNode duplicates = new ListNode(element3, new ListNode(element1, new ListNode(element3)));
        ListNode expectedDuplicates = new ListNode(element1, new ListNode(element3, new ListNode(element3)));
        assertTrue(manipulator.equals(expectedDuplicates, manipulator.sort(duplicates, int_comparator)));

    }

    /*
    Checks that negative indices invoke a InvalidIndexException in getFromFront method
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, -4, 10})
    public void getFromFrontNegativeIndices(int index) {
        assertThrows(InvalidIndexException.class, () -> manipulator.getFromFront(list4, index));
        assertThrows(InvalidIndexException.class, () -> manipulator.getFromFront(list3, index));
    }

    /*
    Checks that negative indices invoke a InvalidIndexException in getFromBack method
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, -5, -10})
    public void getFromBackNegativeIndices(int index) {
        assertThrows(InvalidIndexException.class, () -> manipulator.getFromBack(list4, index));
        assertThrows(InvalidIndexException.class, () -> manipulator.getFromBack(list3, index));
    }


    private final Comparator int_comparator = new Comparator() {

        @Override
        public int compare(Object object1, Object object2) {
            return (Integer) object1 - (Integer) object2;
        }
    };

}
