import java.util.*;

class Student {
    private String name;
    private List<String> grades = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private List<Parent> parents = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addParent(Parent parent) {
        parents.add(parent);
    }

    public void addGrade(String subject, int grade, String teacher) {
        String gradeInfo = subject + ": " + grade + " (" + teacher + ")";
        grades.add(gradeInfo);
        notifyParents("ОЦЕНКА: " + gradeInfo);
    }

    public void addComment(String subject, String comment, String teacher) {
        String commentInfo = subject + " - " + teacher + ": " + comment;
        comments.add(commentInfo);
        notifyParents("КОММЕНТАРИЙ: " + commentInfo);
    }

    private void notifyParents(String message) {          //!!!
        for (Parent parent : parents) {
            parent.notify(message, this.name);
        }
    }

    public void displayInfo() {
        System.out.println("Ученик: " + name);
        System.out.println("Оценки: " + grades);
        System.out.println("Комментарии: " + comments);
        System.out.println("---");
    }

    public String getName() {
        return name;
    }
}

class StudentFactory {          //!!
    public static Student createStudent(String name) {
        return new Student(name);
    }
}

class Parent {
    private String name;

    public Parent(String name) {
        this.name = name;
    }

    public void notify(String message, String studentName) {
        System.out.println("УВЕДОМЛЕНИЕ для " + name + ": " + studentName + " - " + message);
    }
}