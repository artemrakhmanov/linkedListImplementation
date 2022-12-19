package common;

/**
 * This class represents nodes in a single linked list.
 *
 */
public class ListNode {

    /**
     * The element attribute represents the actual element stored in a linked list.
     */
    public Object element;

    /**
     * The next attribute represents the reference to the next node in the singly linked list.
     */
    public ListNode next;

    /**
     * Constructor to permit instantiation of a list node containing the specified element but without linking the node to any other node.
     * @param element the element to hold in this node
     */
    public ListNode(Object element) {

        this(element, null);
    }

    /**
     * Constructor to permit instantiation of a list node containing the specified element linked to the specified next node.
     * @param element the element to hold in this node
     * @param next the reference to the next node in the singly linked list
     */
    public ListNode(Object element, ListNode next) {

        this.element = element;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListNode) {
            ListNode other = (ListNode) o;
            return this.element.equals(other.element);
        }
        return false;
    }
}
