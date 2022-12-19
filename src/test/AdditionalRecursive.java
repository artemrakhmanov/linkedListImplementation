package test;

import impl.IterativeListManipulator;
import interfaces.IListManipulator;

public class AdditionalRecursive extends AdditionalTests {

    @Override
    public IListManipulator makeListManipulator() {
        return new IterativeListManipulator();
    }

}
