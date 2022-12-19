package test;

import impl.RecursiveListManipulator;
import interfaces.IListManipulator;

/**
 * Concrete JUnit test class (subclass of ListManipulatorTest) for testing the RecursiveListManipulator implementation.
 *
 */
public class RecursiveListManipulatorTest extends ListManipulatorTest {

    @Override
    public IListManipulator makeListManipulator() {
        return new RecursiveListManipulator();
    }

}
