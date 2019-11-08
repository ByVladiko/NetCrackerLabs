package utils.TwoWayList;

public class TwoWayList<T> {

    private NodeT<T> head; //Первый элемент
    private NodeT<T> tail; //Последний элемент
    private int count; //Число элементов

//    public TwoWayList(int count) {
//        head = tail;
//        tail = head;
//        this.count = count;
//    }
    
    public int getCount() { // Метод получения общего числа этажей
        return count;
    }
    
    public void add(T item) {
        NodeT<T> newItem = new NodeT<>(item);
        add(newItem);
    }

    public NodeT<T> getNode(int index) { // Метод получения объекта этажа, по его номеру в здании
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return null;
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        NodeT<T> currentItem = head;
        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNext();
        }
        return currentItem;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private void add(NodeT<T> item) {
        if (isEmpty()) {
            head = tail = item;
            head.setNext(tail);
            head.setPrev(tail);
            tail.setNext(head);
            tail.setPrev(head);
        } else {
            tail.setNext(item);
            item.setNext(head);
            tail = item;
            head.setPrev(tail);
        }
        count++;
    }

    public void insertAt(T item, int index) {
        NodeT<T> newItem = new NodeT<>(item);
        if (index == 0) {
            newItem.setNext(head);
            head = newItem;
            tail.setNext(head);
        } else if (index == count - 1) {
            add(item);
        } else {
            getNode(index - 1).setNext(newItem);
            newItem.setNext(getNode(index));
            getNode(index).setPrev(newItem);
            newItem.setPrev(getNode(index - 1));
        }
    }

    private void removeAt(int index) {
        if (isEmpty()) {
            System.out.println("Список пуст!");
            return;
        }
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (count == 1) {
            head = tail = null;
            return;
        }
        if(getNode(index)==head){
            tail.setNext(head.getNext());
            head.setPrev(tail);
            head.getNext().setPrev(head);
        }
        getNode(index-1).setNext(getNode(index).getNext());
        getNode(index).setPrev(getNode(index-1));
    }
}
