package impl;

import java.util.Comparator;
import java.util.zip.CheckedOutputStream;

import common.InvalidIndexException;
import common.InvalidListException;
import common.ListNode;
import interfaces.IFilterCondition;
import interfaces.IListManipulator;
import interfaces.IMapTransformation;
import interfaces.IReduceOperator;

/**
 * This class represents the recursive implementation of the IListManipulator interface.
 *
 */
public class RecursiveListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {

        if (head != null) {
            return 1 + size(head.next);
        } else {
            return 0;
        }

    }

    @Override
    public boolean contains(ListNode head, Object element) {

        if (head != null) {
            if (head.element.equals(element)) {
                return true;
            } else {
                return contains(head.next, element);
            }
        } else {
            return false;
        }

    }

    @Override
    public int count(ListNode head, Object element) {

        if (head != null) {
            if (head.element.equals(element)) {
                return 1 + count(head.next, element);
            } else {
                return count(head.next, element);
            }
        } else {
            return 0;
        }
    }

    @Override
    public String convertToString(ListNode head) {

        if (head != null) {

            if (head.next != null) {

                return head.element.toString() + "," + convertToString(head.next);

            } else {
                return head.element.toString();
            }

        } else {
            return "";
        }
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {

        //index larger than the number of elements
        if (n > size(head) - 1 || n < 0) {
            throw new InvalidIndexException();
        }

        if (head != null) {

            if (n == 0) {
                return head.element;
            } else {
                //reduce n until its zero, as n implies number of iterations
                return getFromFront(head.next, n - 1);
            }
        } else {

            return null;

        }

    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {

        int listSize = size(head);
        int indexFromFront = listSize - 1 - n;

        if (n > listSize - 1 || n < 0) {
            throw new InvalidIndexException();
        }

        return getFromFront(head, indexFromFront);
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {

        if (head1 != null && head2 != null) {
            if (head1.element.equals(head2.element)) {
                return equals(head1.next, head2.next);
            } else {
                return false;
            }
        } else {
            return head1 == null && head2 == null;
        }
    }

    @Override
    public boolean containsDuplicates(ListNode head) {

        if (head != null) {

            if (!contains(head.next, head.element)) {

                return containsDuplicates(head.next);

            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {

        //instantiate with a placeholder
        ListNode root = new ListNode(null);

        append(root, head1, head2);

        //return without a placeholder
        return (root.next != null) ? root.next : null;
    }

    private void append(ListNode result, ListNode head1, ListNode head2) {

        if (head1 != null) {

            result.next = new ListNode(head1.element);

            append(result.next, head1.next, head2);

        } else if (head2 != null) {

            result.next = new ListNode(head2.element);

            append(result.next, null, head2.next);
        }
    }

    @Override
    public ListNode reverse(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        //instantiate with a placeholder
        ListNode root = new ListNode(null);

        root = insertAfter(root, head);

        //return without the placeholder
        return root.next;
    }

    /*
    This method insers elements from the ListNode head as next of the root
    ListNode. (method of reversing as root contains a placeholder)
     */
    public ListNode insertAfter(ListNode root, ListNode head) {

        if (head != null) {

            ListNode successiveNodes = root.next;

            root.next = new ListNode(head.element);

            root.next.next = successiveNodes;

            return insertAfter(root, head.next);

        } else {
            return root;
        }
    }

    @Override
    public ListNode split(ListNode head, int n) throws InvalidIndexException, InvalidListException {

        int listSize = size(head);

        //condition for invalid list exception
        if (head == null || listSize == 1) {
            throw new InvalidListException();
        }

        //condition for invalid index exception
        if (n > listSize - 1 || n <= 0) {
            throw new InvalidIndexException();
        }

        //instantiate with placeholders
        ListNode firstList = new ListNode(null);
        ListNode secondList = new ListNode(null);

        //get all elements until the index
        appendElementsUntilIndex(firstList, head, n - 1);
        //get all elements after & including at the index
        appendElementsUntilIndex(secondList, getNodeAtIndex(head, n), listSize - 1 - n);

        //return without placeholders in a specified format
        return new ListNode(firstList.next, new ListNode(secondList.next));
    }

    private ListNode getNodeAtIndex(ListNode head, int index) {

        if (head != null) {
            if (index > 0) {
                return getNodeAtIndex(head.next, index - 1);
            } else {
                return head;
            }
        } else {
            return null;
        }
    }

    private void appendElementsUntilIndex(ListNode result, ListNode head, int n) {

        if (head != null) {

            if (n >= 0) {

                result.next = new ListNode(head.element);

                appendElementsUntilIndex(result.next, head.next, n - 1);
            }
        }
    }

    @Override
    public ListNode flatten(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode result = new ListNode(null);

        flatten(result, head);

        return result.next;
    }

    private void flatten(ListNode result, ListNode head) {

        if (head != null) {

            if (head.element instanceof ListNode) {
                head = append((ListNode) head.element, head.next);
                flatten(result, head);
            } else {
                result.next = new ListNode(head.element);
            }

            flatten(result.next, head.next);
        }
    }

    @Override
    public boolean isCircular(ListNode head) {

        ListNode nextHead;

        if (head != null && head.next != null && containsCycles(head)) {

            nextHead = head.next;

            head.next = null;

            if (!containsCycles(nextHead)) {

                head.next = nextHead;

                return true;

            } else {

                head.next = nextHead;

                return false;
            }

        } else {
            return false;
        }

    }

    @Override
    public boolean containsCycles(ListNode head) {

        if (head != null && head.next != null && head.next.next != null) {

            return compare(head.next, head.next.next);

        } else {
            return false;
        }
    }

    private boolean compare(ListNode slowerNode, ListNode fasterNode) {

        if (slowerNode.next != null && fasterNode.next != null && fasterNode.next.next != null) {
            if (slowerNode == fasterNode) {
                return true;
            } else {
                return compare(slowerNode.next, fasterNode.next.next);
            }
        } else {
            return false;
        }
    }

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {

        int listSize = size(head);

        //already sorted
        if (listSize <= 1) {
            return head;
        }

        //instantiate with a placeholder
        ListNode sortedList = new ListNode(null);

        performSorting(sortedList, head, comparator, size(head));

        //return without a placeholder & reverse from a descending into ascending order
        return reverse(sortedList.next);
    }

    /*
    Recursive sorting into the ListNode sortedList from ListNode head applying a Comparator comparator.
    Sorts in a descending order.
     */
    private void performSorting(ListNode sortedList, ListNode head, Comparator comparator, int listSize) {

        if (listSize > 0) {

            ListNode largestNode = getLargestElementNode(head, comparator, null);

            //head = filterFirst();
            head = filterFirst(head, element -> (Integer) element != largestNode.element);

            //sorting in descending order
            sortedList.next = new ListNode(largestNode.element);

            performSorting(sortedList.next, head, comparator, listSize - 1);

        }
    }

    /*
    Recursive search for the largest element in the list
     */
    private ListNode getLargestElementNode(ListNode head, Comparator comparator, ListNode currentLargestNode) {

        if (head != null) {

            if (currentLargestNode != null) {

                //compare & pick larger & substitute
                int comparisonResult = comparator.compare(currentLargestNode.element, head.element);

                if (comparisonResult <= 0) {
                    //largest is smaller
                    currentLargestNode = head;
                }

            } else {
                currentLargestNode = head;
            }

            return getLargestElementNode(head.next, comparator, currentLargestNode);

        } else {
            return currentLargestNode;
        }
    }

    @Override
    public ListNode map(ListNode head, IMapTransformation transformation) {

        //return empty lists
        if (head == null) {
            return null;
        }

        //instantiate with a placeholder
        ListNode result = new ListNode(null);

        map(result, head, transformation);

        return result.next;
    }

    private void map (ListNode result, ListNode head, IMapTransformation transformation) {

        if (head != null) {

            result.next = new ListNode(transformation.transform(head.element));

            map(result.next, head.next, transformation);

        }
    }

    @Override
    public Object reduce(ListNode head, IReduceOperator operator, Object initial) {

        Object result = initial;

        if (head == null) {
            return result;
        } else {
            result = operator.operate(result, head.element);
            return reduce(head.next, operator, result);
        }

    }

    @Override
    public ListNode filter(ListNode head, IFilterCondition condition) {

        //instantiate with a placeholder
        ListNode result = new ListNode(null);

        filter(result, head, condition, false);

        //remove the placeholder
        return (result.next != null) ? result.next : null;

    }

    /*
    Method that performs filtering recursively. Boolean filterFirst if true will make the method filter
    after only first !satisfied condition, while if false will filter all out.
     */
    private void filter(ListNode filteredList, ListNode head, IFilterCondition condition, boolean filterFirst) {

        if (head != null) {

            if (condition.isSatisfied(head.element)) {

                filteredList.next = new ListNode(head.element);

                filter(filteredList.next, head.next, condition, filterFirst);

            } else {

                filter(filteredList, head.next, (filterFirst) ? (element -> true) : condition, filterFirst);

            }

        }
    }

    /*
    Method for filtering out only the first instance that satisfies the condition
     */
    private ListNode filterFirst(ListNode head, IFilterCondition condition) {

        //instantiate with a placeholder
        ListNode result = new ListNode(null);

        filter(result, head, condition, true);

        //remove the placeholder
        return (result.next != null) ? result.next : null;
    }

}
