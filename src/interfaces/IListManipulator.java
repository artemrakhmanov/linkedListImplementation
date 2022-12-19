package interfaces;

import java.util.Comparator;

import common.InvalidIndexException;
import common.InvalidListException;
import common.ListNode;

/**
 * Interface for an ADT providing operations on linked lists.
 * 
 */
public interface IListManipulator {

    /**
     * Returns the size of a list.
     * 
     * @param head the head of the list
     * @return the size of the list
     */
    int size(ListNode head);

    /**
     * Checks whether a list contains an element equal to the given element.
     * 
     * @param head the head of the list
     * @param element the element to be matched
     * @return true if the list contains an element equal to the given element
     */
    boolean contains(ListNode head, Object element);

    /**
     * Counts the number of occurrences of a given element in a list.
     * 
     * @param head the head of the list
     * @param element the element to be matched
     * @return the number of elements in the list that are equal to the given element
     */
    int count(ListNode head, Object element);

    /**
     * Returns a string representation of a list.
     * 
     * @param head the head of the list
     * @return a string representation comprising the string representations of the list elements, separated by commas
     */
    String convertToString(ListNode head);

    /**
     * Accesses an element of a list, counting from the head of the list.
     * 
     * @param head the head of the list
     * @param n the position of the required element, with zero interpreted as the head
     * @return the element at the specified position
     * @throws InvalidIndexException if the position is not valid
     */
    Object getFromFront(ListNode head, int n) throws InvalidIndexException;

    /**
     * Accesses an element of a list, counting back from the tail of the list.
     * 
     * @param head the head of the list
     * @param n the position of the required element, with zero interpreted as the tail
     * @return the element at the specified position
     * @throws InvalidIndexException if the position is not valid
     */
    Object getFromBack(ListNode head, int n) throws InvalidIndexException;

    /**
     * Checks for equality of two lists.
     * 
     * @param head1 the head of the first list
     * @param head2 the head of the second list
     * @return true if the lists have equal length and the corresponding elements at each position are equal
     */
    boolean equals(ListNode head1, ListNode head2);

    /**
     * Checks whether a list contains duplicate elements that are equal to each other.
     * 
     * @param head the head of the list
     * @return true if the list contains two or more elements that are equal to one another
     */
    boolean containsDuplicates(ListNode head);

    /**
     * Appends one list to the end of another. The resulting list may include parts of the input lists.
     * 
     * @param head1 the head of the first list
     * @param head2 the head of the second list
     * @return a list containing the elements of the first list followed by the elements of the second list
     */
    ListNode append(ListNode head1, ListNode head2);

    /**
     * Reverses a list, the resulting list should be a new list.
     * 
     * @param head the head of the list
     * @return a new list containing the elements of the original list in reverse order
     */
    ListNode reverse(ListNode head);

    /**
     * Splits a non-empty list at node n (with nodes being numbered from the front starting at zero) into two new non-empty sublists, returning the result as a list of two new sublists.
     * Splitting the list [5, 3, 9] at node 2 should give a new list of two new sublists, namely [ [5, 3] , [9] ].
     * The original list [5, 3, 9] should not change as a result of calling this method.
     * 
     * @param head the head of the list to split, the list starting at head should not change as a result of calling this method
     * @param n the node before which to split the list, with nodes being numbered from the front (i.e. head) starting at zero
     * @return the split of the list as a list of two new non-empty sublists
     * @throws InvalidIndexException if the position n is not valid for a split into two non-empty sublists
     * @throws InvalidListException if the list cannot be split into two non-empty sublists
     */
    ListNode split(ListNode head, int n) throws InvalidIndexException, InvalidListException;

    /**
     * Converts a list of lists of elements into a single list of those elements.
     * 
     * @param head the head of a list, each element of which is itself the head of a list
     * @return the result of combining all the sublists into a single list, in which the elements of the first sublist are followed by
     * the elements of each successive sublist, and the ordering within sublists is preserved
     */
    ListNode flatten(ListNode head);

    /**
     * Checks whether a list is circular.
     * 
     * @param head the head of the list
     * @return true if last node of the list points to the head
     */
    boolean isCircular(ListNode head);

    /**
     * Checks whether a list contains one or more cycles.
     * 
     * @param head the head of the list
     * @return true if last node of the list points to some node in the list
     */
    boolean containsCycles(ListNode head);

    /**
     * Sorts the elements of a list according to a given ordering.
     * 
     * @param head the head of the list
     * @param comparator the ordering to be used
     * @return a sorted list, which may share some or all nodes of the input list
     */
    ListNode sort(ListNode head, Comparator comparator);

    /**
     * Creates a new list containing the results of applying the given transformation to all elements of the input list,
     * without affecting the input list.
     * 
     * @param head the head of the list
     * @param transformation the transformation to be applied to each element
     * @return a list containing the transformed elements
     */
    ListNode map(ListNode head, IMapTransformation transformation);

    /**
     * Returns the result of combining the given initial value and all the elements of the input list using the given associative operator.
     * For example, applying an 'add' operator to a list of integers, using the initial value zero, would produce the sum of the list elements.
     * 
     * @param head the head of the list
     * @param operator the operator used to combine elements
     * @param initial the initial value to be combined with the first element
     * @return the result of combining the initial value and all the elements
     */
    Object reduce(ListNode head, IReduceOperator operator, Object initial);

    /**
     * Creates a new filtered list according to the specified filter condition.
     * 
     * @param head the head of the list
     * @param condition the condition for elements to be included in the filtered list
     * @return a new list containing only those elements from the original list meeting the specified filter condition in the same order as in the original list
     */
    ListNode filter(ListNode head, IFilterCondition condition);


}
