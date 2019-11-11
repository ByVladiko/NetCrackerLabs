package utils.OneWayList;

import java.io.Serializable;

public class OneWayList<T> implements Serializable {

    private Node<T> head; //Первый элемент
    private Node<T> tail; //Последний элемент
    private int count; //Число элементов

    public Node<T> getHead() {
        return head;
    }

    public int getCount() {
        return count;
    }

//    public void setNode(int index, T item) {
//        Node<T> newItem = new Node<>(item);
//        getNode(index).setValue(item);
//    }
    public void setCount(int count) {
        this.count = count;
    }

    public void add(T item) {
        Node<T> newItem = new Node<>(item);
        add(newItem);
    }

    public void insertAt(T item, int index) {  // Метод добавления нового элемента по будущему номеру
        Node<T> newItem = new Node<>(item);
        if (index == 0) {
            newItem.setNext(head);
            head = newItem;
            count++;
        } else if (index == count - 1) {
            add(item);
        } else {
            getNode(index - 1).setNext(newItem);
            newItem.setNext(getNode(index));
            count++;
        }
    }

    private boolean isEmpty() {
        return head == null;
    }

    public Node<T> getNode(int index) {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return null;
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentItem = head;
        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }
        return currentItem;
    }

    private void add(Node<T> item) {
        if (isEmpty()) {
            head = tail = item;
            head.setNext(tail);
            tail.setNext(head);
        } else {
            tail.setNext(item);
            tail = item;
            tail.setNext(head);
        }
        count++;
    }

    public void removeAt(int index) { // Метод удаления элемента по его номеру
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пуст!");
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (count == 1) {
            head = tail = null;
            head.setNext(tail);
            tail.setNext(head);
            count--;
            return;
        }
        if (getNode(index) == head) {
            tail.setNext(head.getNext());
            count--;
            return;
        }
        getNode(index - 1).setNext(getNode(index).getNext());
        count--;
    }
}
