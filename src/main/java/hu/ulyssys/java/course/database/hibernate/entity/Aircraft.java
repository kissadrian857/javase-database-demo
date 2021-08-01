package hu.ulyssys.java.course.database.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aircraft")
public class Aircraft extends AbstractEntity implements Serializable {

}
