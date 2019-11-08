package utils.TwoWayList;

public class NodeT<T> {

    private NodeT<T> prev;
    private NodeT<T> next;
    private T value;

    public NodeT(T value) {
        this.value = value;
    }

    public NodeT(NodeT<T> prev, NodeT<T> next, T value) {
        this.next = next;
        this.value = value;
        this.prev = prev;
    }

    public NodeT<T> getPrev() {
        return prev;
    }

    public void setPrev(NodeT<T> prev) {
        this.prev = prev;
    }

    public NodeT<T> getNext() {
        return next;
    }

    public void setNext(NodeT<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
