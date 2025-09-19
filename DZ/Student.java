public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int compare(Student other) {
        return this.lastName.compareTo(other.lastName);
    }

    public int compare(String lastName) {
        return this.lastName.compareTo(lastName);
    }

    public static void main(String[] args) {
        Student student1 = new Student("Иван", "Иванов");
        Student student2 = new Student("Петр", "Петров");

        System.out.println("Compare students: " + student1.compare(student2));
        System.out.println("Compare with string: " + student1.compare("Петров"));
    }
}
