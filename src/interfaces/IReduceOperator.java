package interfaces;

/**
 * Interface for operator objects used by the IListManipulator.reduce method.
 *
 */
public interface IReduceOperator {

    /**
     * The method defines how to combine the two specified elements when performing an IListManipulator reduce method call.
     * @param element1 the first element for the reduce
     * @param element2 the second element for the reduce
     * @return the result of combining element1 and element2
     */
    Object operate(Object element1, Object element2);

}
