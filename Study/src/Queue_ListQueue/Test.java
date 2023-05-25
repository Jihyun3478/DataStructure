package Queue_ListQueue;

import Queue_ArrayQueue.ArrayQueue;

public class Test {
    public static void main(String[] args) {
        LinkedListQueue<Student> lq = new LinkedListQueue<>();

        lq.offer(new Student("김자바", 92));
        lq.offer(new Student("이시플", 72));
        lq.offer(new Student("조시샵", 98));
        lq.offer(new Student("파이손", 51));

        lq.poll();

        System.out.println(lq.peek());
    }
}

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String toString() {
        return "이름 : " + name + "\t성적 : " + score;
    }
}