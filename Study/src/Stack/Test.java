package Stack;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Stack<Student> stack = new Stack<Student>();

        stack.push(new Student("김자바", 92));
        stack.push(new Student("이시플", 72));
        stack.push(new Student("조시샵", 98));
        stack.push(new Student("파이손", 51));

        stack.pop();

        System.out.println(stack.peek());
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