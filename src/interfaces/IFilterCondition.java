package interfaces;

/**
 * Interface for filter objects as used by the IListManipulator.filter method.
 *
 */
public interface IFilterCondition {

    /**
     * The method defines whether each specified element satisfies the condition to be included in the result when performing an IListManipulator filter method call.
     * @param element the element to check for inclusion in the filtered list
     * @return true if element should be included in the filtered list and false otherwise
     */
    boolean isSatisfied(Object element);

}
