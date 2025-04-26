import java.util.Comparator;

public class Student {
    private String name;
    private double gpa;

    public static boolean compareGpa(Student student1, Student student2) {
        return student1.gpa == student2.gpa;
    }

    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();

        System.out.println(compareGpa(student1, student2));
    }
}
