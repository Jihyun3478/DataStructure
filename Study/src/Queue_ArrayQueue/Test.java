package Queue_ArrayQueue;

import Stack.Stack;

public class Test {
    public static void main(String[] args) {
        ArrayQueue<Student> aq = new ArrayQueue<Student>();

        aq.offer(new Student("김자바", 92));
        aq.offer(new Student("이시플", 72));
        aq.offer(new Student("조시샵", 98));
        aq.offer(new Student("파이손", 51));

        aq.poll();

        System.out.println(aq.peek());
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