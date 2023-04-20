package SinglyLinkedList;//package 무제.DataStructure.SinglyLinkedList;
//
//import 무제.DataStructure.InterfaceForm.List;
//
//import java.util.NoSuchElementException;
//
//public class SLinkedList<E> implements List<E> {
//    private Node<E> head; // 노드의 첫 부분
//    private Node<E> tail; // 노드의 마지막 부분
//    private int size; // 요소 개수
//
//    public SLinkedList() {
//        this.head = null;
//        this.tail = null;
//        this.size = 0;
//    }
//
//    // 특정 위치의 노드를 반환하는 메소드
//    private Node<E> search(int index) {
//
//        // 범위 밖(잘못된 위치)일 경우 예외 던지기
//        if(index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException();
//        }
//
//        Node<E> x = head; // head가 가리키는 노드부터 시작
//
//        for(int i = 0; i < index; i++) {
//            x = x.next; // x 노드의 다음 노드를 x에 저장함
//        }
//        return x;
//    }
//
//    public void addFirst(E value) {
//        Node<E> newNode = new Node<E>(value); // 새 노드 생성
//        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
//        head = newNode; // head가 가리키는 노드를 새 노드로 변경
//        size++;
//
//        // 다음에 가리킬 노드가 없는 경우, 데이터가 한 개밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드임
//        if(head.next == null) {
//            tail = head;
//        }
//    }
//
//    @Override
//    public boolean add(E value) {
//        addLast(value);
//        return true;
//    }
//
//    public void addLast(E value) {
//        Node<E> newNode = new Node<E>(value); // 새 노드 생성
//
//        if(size == 0) { // 처음 넣는 노드일 경우 addFirst로 추가
//            addFirst(value);
//            return;
//        }
//
//        // 마지막 노드의 다음 노드가 새 노드를 가리키도록 함, tail이 가리키는 노드를 새 노드로 바꿔줌
//        tail.next = newNode;
//        tail = newNode;
//        size++;
//    }
//
//    @Override
//    public void add(int index, E value) {
//        // 질못된 인덱스를 참조할 경우 예외 발생
//        if(index > size || index < 0) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
//        // 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
//        if(index == 0) {
//            addFirst(value);
//            return;
//        }
//
//        if(index == size) {
//            addLast(value);
//            return;
//        }
//
//        Node<E> prevNode = search(index-1);
//        Node<E> nextNode = prevNode.next;
//        Node<E> newNode = new Node<E>(value);
//
//        // 이전 노드가 가리키는 노드를 끊은 뒤 새 노드로 변경해줌, 새 노드가 가리키는 노드는 다음 노드로 설정해줌
//        prevNode.next = null;
//        prevNode.next = newNode;
//        newNode.next = nextNode;
//        size++;
//    }
//
//    public E remove() {
//        Node<E> headNode = head;
//
//        if(headNode == null)
//            throw new NoSuchElementException();
//
//        // 삭제된 노드를 반환하기 위한 임시 변수
//        E element = headNode.data;
//
//        // head의 다음 노드
//        Node<E> nextNode = head.next;
//
//        // head 노드의 데이터들을 모두 삭제
//        head.data = null;
//        head.next = null;
//
//        // head가 다음 노드를 가리키도록 업데이트
//        head = nextNode;
//        size--;
//
//        // 삭제된 요소가 리스트의 유일한 요소였을 경우 그 요소는 head이자 tail이므로 삭제되면서 tail도 가리킬 요소가 없기 때문에 tail도 null로 변환
//        if(size == 0) {
//            tail = null;
//        }
//        return element;
//    }
//
//    @Override
//    public E remove(int index) {
//        // 삭제하려는 노드가 첫 번째 원소일 경우
//        if(index == 0) {
//            return remove();
//        }
//
//        // 잘못된 범위에 대한 예외
//        if(index >= size || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
//
//        Node<E> prevNode = search(index-1); // 삭제할 노드의 이전 노드
//        Node<E> removeNode = prevNode.next; // 삭제할 노드
//        Node<E> nextNode = removeNode.next; // 삭제할 노드의 다음 노드
//
//        E element = removeNode.data; // 삭제할 노드의 데이터를 반환하기 위한 임시변수
//
//        // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음 노드로 변경
//        prevNode.next = nextNode;
//
//        // 만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
//        if(prevNode.next == null) {
//            tail = prevNode;
//        }
//        // 데이터 삭제
//        removeNode.next = null;
//        removeNode.data = null;
//        size--;
//
//        return element;
//    }
//
//    @Override
//    public boolean remove(Object value) {
//        Node<E> prevNode = head;
//        boolean hasValue = false;
//        Node<E> x = head;
//
//        for(; x != null; x = x.next) {
//            if(value.equals(x.data)) {
//                hasValue = true;
//                break;
//            }
//            prevNode = x;
//        }
//
//        if(x == null) {
//            return false;
//        }
//
//        if(x.equals(head)) {
//            remove();
//            return true;
//        }
//
//        else {
//            prevNode.next = x.next;
//
//            if(prevNode.next == null) {
//                tail = prevNode;
//            }
//            x.data =  null;
//            x.next = null;
//            size--;
//            return true;
//        }
//    }
//}
