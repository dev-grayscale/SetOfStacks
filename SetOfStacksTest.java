import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.EmptyStackException;
import java.util.List;

public class SetOfStacksTest {

  @Test
  public void test() {
    SetOfStacks setOfStacks = new SetOfStacks(5);

    Assertions.assertEquals(1, getStacks(setOfStacks).size());

    Assertions.assertThrows(EmptyStackException.class, setOfStacks::pop);

    setOfStacks.push(10);

    Assertions.assertEquals(1, getStacks(setOfStacks).size());

    Assertions.assertEquals(10, setOfStacks.pop());

    setOfStacks.push(10);
    setOfStacks.push(11);
    setOfStacks.push(12);
    setOfStacks.push(13);
    setOfStacks.push(14);

    // should increment size on next push()
    Assertions.assertEquals(1, getStacks(setOfStacks).size());

    setOfStacks.push(15);

    Assertions.assertEquals(2, getStacks(setOfStacks).size());

    Assertions.assertEquals(15, setOfStacks.pop());

    // should decrement size on next pop()
    Assertions.assertEquals(2, getStacks(setOfStacks).size());

    Assertions.assertEquals(14, setOfStacks.pop());

    Assertions.assertEquals(1, getStacks(setOfStacks).size());

    Assertions.assertEquals(13, setOfStacks.pop());
    Assertions.assertEquals(12, setOfStacks.pop());
    Assertions.assertEquals(11, setOfStacks.pop());
    Assertions.assertEquals(10, setOfStacks.pop());

    Assertions.assertEquals(1, getStacks(setOfStacks).size());

    Assertions.assertThrows(EmptyStackException.class, setOfStacks::pop);

    setOfStacks.push(10);
    setOfStacks.push(11);
    setOfStacks.push(12);
    setOfStacks.push(13);
    setOfStacks.push(14);
    setOfStacks.push(15);
    setOfStacks.push(16);
    setOfStacks.push(17);
    setOfStacks.push(18);
    setOfStacks.push(19);
    setOfStacks.push(20);
    setOfStacks.push(21);
    setOfStacks.push(22);
    setOfStacks.push(23);
    setOfStacks.push(24);
    setOfStacks.push(25);
    setOfStacks.push(26);
    setOfStacks.push(27);
    setOfStacks.push(28);

    Assertions.assertEquals(4, getStacks(setOfStacks).size());

    Assertions.assertEquals(28, setOfStacks.pop());
    Assertions.assertEquals(27, setOfStacks.pop());
    Assertions.assertEquals(26, setOfStacks.pop());
    Assertions.assertEquals(25, setOfStacks.pop());
    Assertions.assertEquals(24, setOfStacks.pop());
    Assertions.assertEquals(23, setOfStacks.pop());
    Assertions.assertEquals(22, setOfStacks.pop());
    Assertions.assertEquals(21, setOfStacks.pop());
  }

  private List<Stack<Integer>> getStacks(SetOfStacks setOfStacks) {
    Try<Object> stacksField = ReflectionUtils.tryToReadFieldValue(SetOfStacks.class, "stacks", setOfStacks);

    try {
      return (List<Stack<Integer>>) stacksField.get();
    } catch (Exception ignored) {
    }

    return null;
  }
}
