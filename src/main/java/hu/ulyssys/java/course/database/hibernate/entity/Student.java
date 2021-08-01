package hu.ulyssys.java.course.database.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student extends AbstractPerson implements Serializable {

    @Column(name = "friends_number")
    private Integer friendsNumber;

    public Integer getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(Integer friendsNumber) {
        this.friendsNumber = friendsNumber;
    }
}
