import java.util.ArrayList;
import java.util.List;

/**
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 *
 * For this challenge, we'll assume the following Stack structure:
 *
 * public class Stack<T> {
 *   private Node<T> top;
 *
 *   private int size;
 *
 *   private static class Node<T> {
 *     private final T data;
 *     private Node<T> next;
 *
 *     public Node(T data) {
 *       this.data = data;
 *     }
 *   }
 *
 *   public void push(T data) {
 *     Node<T> node = new Node<>(data);
 *     node.next = top;
 *     top = node;
 *
 *     size++;
 *   }
 *
 *   public int getSize() {
 *     return size;
 *   }
 *
 *   public boolean isEmpty() {
 *     return top == null;
 *   }
 *
 *   public T pop() {
 *     if (top == null) {
 *       throw new EmptyStackException();
 *     }
 *
 *     T data = top.data;
 *     top = top.next;
 *
 *     size--;
 *
 *     return data;
 *   }
 * }
 *
 * The stack above is extended to support getSize() method which will help us in the implementation below.
 * The outcome should be a SetOfStacks that increase/decrease its size of stacks when pop()/push() methods are called.
 *
 * Among other viable data structures to hold the stacks, we've chosen <b>List</b> which has dynamic size allocation (to hold the usable amount)
 * and will always give the occupied size only (unlike Array which will give us the allocated slots)
 *
 * The amount of stacks that could be created is delegated to the maximum elements supported from java.util.ArrayList or
 * limited by another memory constraint.
 *
 * By specifying the <b>capacity</b> limit for each <b>Stack</b>, all that's left for us is to make sure
 * we always work with the right <b>Stack</b> (the last one in the list) for <b>push()<b/> and <b>pop()</b>
 * and to guarantee that whenever an element is added and the capacity of the current <b>Stack</b> is exceeded,
 * another will be created and used instead. Accordingly, whenever an element is removed and was the last one,
 * we should get rid of this <b>Stack</b> and start using the previous one instead.
 */
public class SetOfStacks {
  private final int capacity;

  private final List<Stack<Integer>> stacks;

  public SetOfStacks(int capacity) {
    this.capacity = capacity;
    stacks = new ArrayList<>();

    // should contain at least 1 stack
    stacks.add(new Stack<>());
  }

  public void push(int data) {
    // ensure capacity by providing another stack
    // if necessary
    if (getStack().getSize() == capacity) {
      stacks.add(new Stack<>());
    }

    getStack().push(data);
  }

  public Integer pop() {
    // remove empty stack (without the first one)
    // to always hold occupied ones
    if (stacks.size() > 1 && getStack().isEmpty()) {
      stacks.remove(stacks.size() - 1);
    }

    return getStack().pop();
  }

  private Stack<Integer> getStack() {
    // Current stack will be the last one
    return stacks.get(stacks.size() - 1);
  }

}
