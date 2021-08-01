package hu.ulyssys.java.course.database.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends AbstractPerson{

    @Column(name = "courses_number")
    private Integer coursesNumber;

    public Integer getCoursesNumber() {
        return coursesNumber;
    }

    public void setCoursesNumber(Integer coursesNumber) {
        this.coursesNumber = coursesNumber;
    }
}
