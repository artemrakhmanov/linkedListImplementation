package interfaces;

/**
 * Interface for transformation objects as used by the IListManipulator.map method.
 *
 */
public interface IMapTransformation {

    /**
     * The method defines how to transform each specified element when performing an IListManipulator map method call.
     * @param element the element on which to perform the transform
     * @return the transformed element
     */
    Object transform(Object element);

}
