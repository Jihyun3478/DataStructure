package List_ArrayList;

import InterfaceForm.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {
    private static final int DEFAULT_CAPACITY = 10; // 최소 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; // 요소 개수
    Object[] array; // 요소를 담을 배열

    // 생성자 1 - 초기 공간 할당 X
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 1 - 초기 공간 할당 O
    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        int arrayCapacity = array.length;

        // capacity가 0이라면
        if(Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 꽉 찰 경우
        if(size == arrayCapacity) {
            int newCapacity = arrayCapacity * 2;

            array = Arrays.copyOf(array, newCapacity);
            return;
        }

        // 용량의 절반 미만으로 요소가 차지하고 있을 경우
        if(size < (arrayCapacity/2)) {
            int newCapacity = arrayCapacity / 2;

            array = Arrays.copyOf(array, Math.max(newCapacity, DEFAULT_CAPACITY));
            return;
        }


    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        // 꽉 차있는 상태라면 용량 재할당
        if(size == array.length) {
            resize();
        }
        array[size] = value; // 마지막 위치에 요소 추가
        size++; // 사이즈 1 증가
    }

    @Override
    public void add(int index, E value) {
        if(index > size || index < 0) { // 영역을 벗어날 경우 예외 발생
            throw new IndexOutOfBoundsException();
        }

        if(index == size) { // index가 마지막 위치라면 addLast 메소드로 요소 추가
            addLast(value);
        }
        else {
            if(size == array.length) { // 꽉 차있다면 용적 재할당
                resize();
            }

            // index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
            for(int i = size; i > index; i--) {
                array[i] = array[i-1];
            }

            array[index] = value; // index 위치에 요소 할당
            size++;
        }
    }

    public void addFirst(E value) {
        add(0, value);
    }

    // Object -> E 타입으로 변환 시 변환할 수 없는 타입일 가능성이 있기에 타입 안정성에 대한 경고가 뜸, 하지만 우리가 add 하는 데이터의 타입은 E 타입만 존재.
    // @SuppressWarnings("unchecked")는 해당 경고를 무시
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException(); // 범위를 벗어날 경우 예외 발생
        }
        else {
            // 해당 위치의 요소 교체
            array[index] = value;
        }
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        // value와 같은 객체일 경우 i 반환
        for (i = 0; i < size; i++) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        // 일치하는 것이 없을 경우 -1 반환
        return -1;
    }

    public int lastIndexOf(Object value) {
       for(int i = size - 1; i >= 0; i--) {
           if(array[i].equals(value)) {
               return i;
           }
       }
        return -1;
    }

    @Override
    public boolean contains(Object value) {
        // 0 이상이면 요소가 존재한다는 뜻
        if(indexOf(value) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @SuppressWarnings("unchecked") // 경고 무시
    @Override
    public E remove(int index) {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index]; // 삭제될 요소를 반환하기 위해 임시로 담아둠
        array[index] = null;

        // 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        size--;
        resize();
        return element;
    }

    @Override
    public boolean remove(Object value) {
        int index = indexOf(value);

        if(index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>)super.clone();

        // 새로운 객체의 배열도 생성해주어야 함 (객체는 얕은복사가 되기 때문)
        cloneList.array = new Object[size];

        // 배열의 값을 복사함
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array,size);
    }

    public <T> T[] toArray(T[] a) {
        if(a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<Integer> original = new ArrayList<Integer>();
        original.add(10);    // original에 10추가

        ArrayList<Integer> copy = original;
        ArrayList<Integer> clone = (ArrayList<Integer>) original.clone();

        copy.add(20);    // copy에 20추가
        clone.add(30);    // clone에 30추가

        System.out.println("original list");
        for(int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for(int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

        System.out.println("\nclone list");
        for(int i = 0; i < clone.size(); i++) {
            System.out.println("index " + i + " data = " + clone.get(i));
        }

        System.out.println("\noriginal list reference : " + original);
        System.out.println("copy list reference : " + copy);
        System.out.println("clone list reference : " + clone);
    }
}
