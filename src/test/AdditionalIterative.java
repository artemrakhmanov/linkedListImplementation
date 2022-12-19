package test;

import impl.IterativeListManipulator;
import interfaces.IListManipulator;

public class AdditionalIterative extends AdditionalTests {

    @Override
    public IListManipulator makeListManipulator() {
        return new IterativeListManipulator();
    }

}
