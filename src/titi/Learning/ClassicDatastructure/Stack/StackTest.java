/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package titi.Learning.ClassicDatastructure.Stack;

import junit.framework.Assert;
import org.junit.Test;

import org.junit.Test;

public class StackTest {

    @Test
    public void test() throws StackException {
        ArrayStack<Integer> myIntStack = new ArrayStack(Integer.class, 5);
        Assert.assertTrue(myIntStack.isEmpty());
        Assert.assertFalse(myIntStack.isFull());
        myIntStack.push(1);
        myIntStack.push(2);
        myIntStack.push(3);
        Assert.assertEquals(myIntStack.size(), 3);
        Assert.assertEquals(myIntStack.top().intValue(), 3);
        myIntStack.pop();
        Assert.assertEquals(myIntStack.pop().intValue(), 2);
        Assert.assertEquals(myIntStack.size(), 1);
    }

}
