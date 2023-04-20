package List_DoubllyLinkedList;

import InterfaceForm.List;

public class DLinkedList<E> implements List {
    private Node<E> head; // 노드의 첫 부분
    private Node<E> tail; // 노드의 마지막 부분
    private int size; // 요소 개수

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환
    private Node<E> search(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 뒤에서부터 검색
        if(index + 1 > size / 2) {
            Node<E> x = tail;
            for(int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
        // 앞에서부터 검색
        else {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value); // 새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드 연결

        // head가 null이 아닐 경우에만 기존 head 노드의 prev 변수가 새 노드를 가리키도록 함
        if(head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;

        // 데이터가 새 노드밖에 없는 경우
        if(head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(Object value) {
        addLast((E) value);
        return true;
    }

    public void addLast(E value) {
        Node <E> newNode = new Node<>(value);

        if(size == 0) {
            addFirst(value);
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    public void add(int index, E value) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(index == 0) {
            addFirst(value);
            return;
        }
        if(index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(value);

        prevNode.next = null;
        nextNode.prev = null;

        prevNode.next = newNode;

        newNode.prev = prevNode;
        newNode.next = nextNode;

        nextNode.prev = newNode;
        size++;
    }
}
