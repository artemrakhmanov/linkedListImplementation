package impl;

import java.util.Comparator;

import common.InvalidIndexException;
import common.InvalidListException;
import common.ListNode;
import interfaces.IFilterCondition;
import interfaces.IListManipulator;
import interfaces.IMapTransformation;
import interfaces.IReduceOperator;
import org.junit.jupiter.params.ParameterizedTest;
import org.w3c.dom.ls.LSException;

/**
 * This class represents the iterative implementation of the IListManipulator interface.
 *
 */
public class IterativeListManipulator implements IListManipulator {

    @Override
    public int size(ListNode head) {

        int listSize = 0;
        ListNode root = head;

        if (root != null) {

            //loop through all next nodes & count until the last
            while(root != null) {
                listSize++;
                root = root.next;
            }
        }

        return listSize;
    }

    @Override
    public boolean contains(ListNode head, Object element) {

        boolean containsElement = false;
        ListNode node = head;
        ListNode elementNode = new ListNode(element);

        while(node != null) {

            if (node.equals(elementNode)) {
                containsElement = true;
                break;
            }

            node = node.next;
        }

        return containsElement;
    }

    @Override
    public int count(ListNode head, Object element) {

        int numberOfOccurencies = 0;
        ListNode node = head;
        ListNode elementNode = new ListNode(element);

        while(node != null) {

            if (node.equals(elementNode)) {
                numberOfOccurencies++;
            }

            node = node.next;
        }


        return numberOfOccurencies;
    }

    @Override
    public String convertToString(ListNode head) {

        StringBuilder stringRepresentation = new StringBuilder();
        String separator = ",";
        ListNode node = head;

        while(node != null) {

            stringRepresentation.append(node.element);

            if (node.next != null) {
                stringRepresentation.append(separator);
            } else {
                break;
            }

            node = node.next;
        }

        return stringRepresentation.toString();
    }

    @Override
    public Object getFromFront(ListNode head, int n) throws InvalidIndexException {

        Object element = null;
        ListNode node = head;
        int listIteration = 0;

        //no checking against size cause takes a full loop of work

        if (n < 0) {
            throw new InvalidIndexException();
        }

        while(node != null) {

            if (n == listIteration) {
                element = node.element;
                break;
            }

            node = node.next;
            listIteration++;

        }

        if (element == null) {
            throw new InvalidIndexException();
        }

        return element;
    }

    @Override
    public Object getFromBack(ListNode head, int n) throws InvalidIndexException {

        int indexFromFront = size(head) - 1 - n;

        if (indexFromFront < 0) {
            throw new InvalidIndexException();
        }

        return getFromFront(head, indexFromFront);
    }

    @Override
    public boolean equals(ListNode head1, ListNode head2) {

        ListNode node1 = head1;
        ListNode node2 = head2;

        //loop through lists until one/ both end
        while(node1 != null && node2 != null) {

            //compare elements at each node
            if (!node1.equals(node2)) {
                return false;
            }

            node1 = node1.next;
            node2 = node2.next;
        }

        //lists are equal if both ended
        return node1 == null && node2 == null;
    }

    @Override
    public boolean containsDuplicates(ListNode head) {

        ListNode node = head;

        while(node != null) {

            /*
            shrink the list as searching for current node's value
            exclude self
             */
            if (contains(node.next, node.element)) {
                return true;
            }

            node = node.next;
        }

        return false;
    }

    @Override
    public ListNode append(ListNode head1, ListNode head2) {

        //instantiate with a placeholder
        ListNode result = new ListNode(null);
        ListNode resultRootNode = result;
        ListNode firstList = head1;
        ListNode secondList = head2;

        while(firstList != null) {

            result.next = new ListNode(firstList.element);

            result = result.next;

            firstList = firstList.next;
        }

        while (secondList != null) {

            result.next = new ListNode(secondList.element);

            result = result.next;

            secondList = secondList.next;
        }

        //return without a placeholder
        return (resultRootNode.next != null) ? resultRootNode.next : null;
    }

    @Override
    public ListNode reverse(ListNode head) {

        //instantiate with a placeholder
        ListNode reversedList = new ListNode(null);
        ListNode reversedRootNode = reversedList;

        if (head != null) {

            for (int i = size(head) - 1; i >= 0; i-- ) {

                try {

                    reversedList.next = new ListNode(getFromFront(head, i));
                    reversedList = reversedList.next;

                } catch (InvalidIndexException e){
                    return null;
                }
            }
        }

        //return without a placeholder
        return (reversedRootNode.next != null) ? reversedRootNode.next : null;
    }

    @Override
    public ListNode split(ListNode head, int n) throws InvalidIndexException, InvalidListException {

        ListNode node = head;
        //instantiate with placeholders
        ListNode firstList = new ListNode(null);
        ListNode secondList = new ListNode(null);
        int listSize = size(node);  //to perform calculation only once
        int listIteration = 0;

        ListNode splitRootNode = new ListNode(firstList, new ListNode(secondList));

        //before calculations check for list validity
        if (head == null || listSize == 1) {
            throw new InvalidListException();
        }

        //before calculations check for index validity
        if (n <= 0 || n > listSize - 1) {
            throw new InvalidIndexException();
        }

        while(node != null) {

            if (listIteration < n) {

                firstList.next = new ListNode(node.element);
                firstList = firstList.next;

            } else {

                secondList.next = new ListNode(node.element);
                secondList = secondList.next;

            }

            listIteration++;
            node = node.next;
        }

        //remove placeholders
        if (splitRootNode.element instanceof ListNode) {
            splitRootNode.element = ((ListNode) splitRootNode.element).next;
        }

        if (splitRootNode.next.element instanceof ListNode) {
            splitRootNode.next.element = ((ListNode) splitRootNode.next.element).next;
        }

        return splitRootNode;
    }

    @Override
    public ListNode flatten(ListNode head) {

        ListNode node = head;
        //instantiate with a placeholder
        ListNode flatList = new ListNode(null);
        ListNode flatRootNode = flatList;

        while(node != null) {

            if (node.element instanceof ListNode) {

                node = append((ListNode) node.element, node.next);
                continue;

            } else {

                flatList.next = new ListNode(node.element);
                flatList = flatList.next;

            }

            node = node.next;
        }

        //return without a placeholder
        return (flatRootNode.next != null) ? flatRootNode.next : null;
    }

    @Override
    public boolean isCircular(ListNode head) {

        boolean isCircular = false;
        ListNode headNext = null;

        if (head != null && containsCycles(head)) {

            headNext = head.next;
            head.next = null;

            if (!containsCycles(headNext)) {
                isCircular = true;
            }

            head.next = headNext;
        }

        return isCircular;
    }

    @Override
    public boolean containsCycles(ListNode head) {

        boolean containsCycles = false;
        ListNode firstNode = head;
        ListNode secondNode = head;

        if (head != null) {

            while(firstNode.next != null && secondNode.next != null && secondNode.next.next != null) {

                firstNode = firstNode.next;
                secondNode = secondNode.next.next;

                if (firstNode == secondNode) {

                    containsCycles = true;
                    break;
                }

            }
        }


        return containsCycles;
    }

    @Override
    public ListNode sort(ListNode head, Comparator comparator) {

        ListNode node = head;
        ListNode nodeRootNode = node;
        ListNode sortedList = null;
        ListNode currentLargestNode = null;
        int listSize = size(node);
        int sortedListSize = 0;

        //already sorted
        if (listSize <= 1) {
            return node;
        }

        while(sortedListSize != listSize) {

            //iterate to find the largest element
            //put it in the back of the list
            //repeat

            nodeRootNode = node;
            currentLargestNode = null;

            while(node != null) {

                if (currentLargestNode != null) {

                    //compare & pick larger & substitute
                    int comparisonResult = comparator.compare(currentLargestNode.element, node.element);

                    if (comparisonResult <= 0) {
                        //largest is smaller
                        currentLargestNode = node;
                    }

                } else {

                    currentLargestNode = node;

                }

                node = node.next;
            }


            node = nodeRootNode;
            ListNode finalCurrentLargestNode = currentLargestNode;
            //filter out the element that was picked as largest (only one instance)
            node = filterFirst(node, element -> (Integer) element != finalCurrentLargestNode.element);

            //add to the sorted list and shift
            if (sortedList != null) {
                sortedList = new ListNode(currentLargestNode.element, sortedList);
            } else {
                sortedList = new ListNode(currentLargestNode.element);
            }

            sortedListSize++;
        }

        return sortedList;
    }

    @Override
    public ListNode map(ListNode head, IMapTransformation transformation) {

        ListNode node = head;
        //instantiate with a placeholder
        ListNode transformedList = new ListNode(null);
        ListNode transformedRoot = transformedList;

        while(node != null) {

            ListNode transformedNode = new ListNode(transformation.transform(node.element));

            transformedList.next = transformedNode;
            transformedList = transformedList.next;

            node = node.next;
        }

        //remove without a placeholder
        return (transformedRoot.next != null) ? transformedRoot.next : null;
    }

    @Override
    public Object reduce(ListNode head, IReduceOperator operator, Object initial) {

        Object result = initial;
        ListNode node = head;

        while(node != null) {

            result = operator.operate(result, node.element);

            node = node.next;

        }

        return result;
    }

    @Override
    public ListNode filter(ListNode head, IFilterCondition condition) {
        //instantiate with a placeholder
        ListNode filteredList = new ListNode(null);
        ListNode filteredRootNode = filteredList;
        ListNode node = head;

        while(node != null) {

            if (condition.isSatisfied(node.element)) {

                filteredList.next = new ListNode(node.element);
                filteredList = filteredList.next;

            }

            node = node.next;
        }

        //return without a placeholder
        return (filteredRootNode.next != null) ? filteredRootNode.next : null;
    }

    /*
    This method performs similarly to the filter() but executes filtering only on the first satisfied
    condition
     */
    private ListNode filterFirst(ListNode head, IFilterCondition condition) {

        //instantiate with a placeholder
        ListNode filteredList = new ListNode(null);
        ListNode filteredRootNode = filteredList;
        ListNode node = head;
        boolean satisfied = false;

        while(node != null) {

            if (condition.isSatisfied(node.element) || satisfied) {

                filteredList.next = new ListNode(node.element);
                filteredList = filteredList.next;

            } else {
                satisfied = true;
            }

            node = node.next;
        }

        return (filteredRootNode.next != null) ? filteredRootNode.next : null;
    }

}
