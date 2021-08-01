package hu.ulyssys.java.course.database.hibernate.entity;

import hu.ulyssys.java.course.database.hibernate.DatabaseSessionProvider;
import org.hibernate.Session;

import java.util.List;

public class PersonMain {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFriendsNumber(2);
        student.setFirstName("alma");
        student.setLastName("körte");

        insertStudent(student);

        Teacher teacher = new Teacher();
        teacher.setCoursesNumber(2);
        teacher.setFirstName("tanci");
        teacher.setLastName("bácsi");

        insertTeacher(teacher);

        for (AbstractPerson person : findAll(Student.class)) {
            System.out.println("Student:" + " " + person.getId() + " " + person.getFirstName() + " " + person.getLastName());
        }

        for (AbstractPerson person : findAll(Teacher.class)) {
            System.out.println("Teacher:" + " " + person.getId() + " " + person.getFirstName() + " " + person.getLastName());
        }

        Student persistedStudent = findStudentById(student.getId());
        Teacher persistedTeacher = findTeacherById(teacher.getId());
        System.out.println("persistedStudent id: " + persistedStudent.getId());
        System.out.println("persistedTeacher id: " + persistedTeacher.getId());
    }

    private static Long insertStudent(Student student) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getId();
    }

    private static Long insertTeacher(Teacher teacher) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        return teacher.getId();
    }

    private static List<AbstractPerson> findAll(Class clazz) {
        return DatabaseSessionProvider.getInstance().getSessionObj().createQuery("select n from " +
                clazz.getSimpleName() + " n", clazz).getResultList();
    }

    private static Student findStudentById(Long id) {
        return DatabaseSessionProvider.getInstance().getSessionObj().find(Student.class, id);
    }

    private static Teacher findTeacherById(Long id) {
        return DatabaseSessionProvider.getInstance().getSessionObj().find(Teacher.class, id);
    }
}
