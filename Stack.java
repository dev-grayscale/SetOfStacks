import java.util.EmptyStackException;

public class Stack<T> {
  private Node<T> top;

  private int size;

  private static class Node<T> {
    private final T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public void push(T data) {
    Node<T> node = new Node<>(data);
    node.next = top;
    top = node;

    size++;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public T pop() {
    if (top == null) {
      throw new EmptyStackException();
    }

    T data = top.data;
    top = top.next;

    size--;

    return data;
  }
}
