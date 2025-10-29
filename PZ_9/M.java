public class M {
    public static void main(String[] args) {
        ElectronicDiary diary = ElectronicDiary.getInstance();

        Student student1 = StudentFactory.createStudent("Иван Петров");
        Student student2 = StudentFactory.createStudent("Анна Сидорова");

        diary.addStudent(student1);
        diary.addStudent(student2);

        Parent parent1 = new Parent("Мария Петрова");
        Parent parent2 = new Parent("Ольга Сидорова");

        student1.addParent(parent1);
        student2.addParent(parent2);

        student1.addGrade("Математика", 5, "Иванова О.П.");
        student1.addGrade("Русский язык", 4, "Петрова М.И.");
        student1.addComment("Математика", "Отлично подготовился", "Иванова О.П.");

        student2.addGrade("Физика", 3, "Сидоров А.В.");
        student2.addComment("Физика", "Нужно больше заниматься", "Сидоров А.В.");

        diary.showAllStudents();
    }
}