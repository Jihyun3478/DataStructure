package ArrayList;

import InterfaceForm.List;

import java.util.Arrays;

public class ArrayList2<E> implements List<E>, Cloneable {
    private static final int DEFAULT_CAPACITY = 10; // 최소 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; // 요소 개수
    Object[] array; // 요소를 담을 배열

    // 생성자 1 - 초기 공간 할당 X
    public ArrayList2() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 1 - 초기 공간 할당 O
    public ArrayList2(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        int arrayCapacity = array.length;
        if(Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
        }

        if(size == arrayCapacity) {
            int newCapacity = arrayCapacity*2;
            array = Arrays.copyOf(array, newCapacity);
        }

        if(size < (arrayCapacity/2)) {
            int newCapacity = arrayCapacity/2;
            array = Arrays.copyOf(array, Math.max(newCapacity, DEFAULT_CAPACITY));
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        if(size == array.length) {
            resize();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if(index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if(index == size) {
            addLast(value);
        }
        else {
            if(size == array.length) {
                resize();
            }
            for(int i = size; i > index; i--) {
                array[i] = array[i-1];
            }
            array[index] = value;
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
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if(index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            array[index] = value;
        }
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        for(i = 0; i < size; i++) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        int i = 0;
        for(i = size-1; i >= 0; i--) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @SuppressWarnings("unchecked") // 경고 무시
    @Override
    public E remove(int index) {
        if(index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for(int i = index; i < size-1; i++) {
            array[i] = array[i+1];
            array[i+1] = null;
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
//        for(int i = 0; i < array.length; i++) {
//            array[i] = null;
//        }
        Arrays.fill(array, null);
        size = 0;
        resize();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 객체 생성
        ArrayList2<?> cloneList = (ArrayList2<?>)super.clone();

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
        ArrayList2<Integer> original = new ArrayList2<Integer>();
        original.add(10);    // original에 10추가

        ArrayList2<Integer> copy = original;
        ArrayList2<Integer> clone = (ArrayList2<Integer>) original.clone();

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
