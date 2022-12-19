package test;

import impl.IterativeListManipulator;
import interfaces.IListManipulator;

/**
 * Concrete JUnit test class (subclass of ListManipulatorTest) for testing the IterativeListManipulator implementation.
 *
 */
public class IterativeListManipulatorTest extends ListManipulatorTest {

    @Override
    public IListManipulator makeListManipulator() {
        return new IterativeListManipulator();
    }

}
