import java.util.*;

public class ElectronicDiary {
    private static ElectronicDiary instance;
    private List<Student> students = new ArrayList<>();

    private ElectronicDiary() {}

    public static ElectronicDiary getInstance() { //!
        if (instance == null) {
            instance = new ElectronicDiary();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void showAllStudents() {
        for (Student student : students) {
            student.displayInfo();
        }
    }
}

//Singleton (Одиночка) — гарантирует, что у класса есть только один
//экземпляр, и предоставляет к нему глобальную точку доступа.

//Factory Method (Фабричный метод) — определяет интерфейс для создания
//объектов, позволяя подклассам решать, какой класс инстанцировать.

// Observer (Наблюдатель) — создаёт зависимость «один ко многим» между
//объектами, так что при изменении одного уведомляются все зависимые.