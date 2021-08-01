package hu.ulyssys.java.course.database.hibernate;

import hu.ulyssys.java.course.database.hibernate.entity.Aircraft;
import hu.ulyssys.java.course.database.hibernate.entity.Tank;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        //Save aircraft
        Aircraft aircraft = new Aircraft();
        aircraft.setName("Szuper repcsi");
        aircraft.setCreatedDate(new Date());
        aircraft.setLastModifiedDate(new Date());
        insertAircraft(aircraft);
        //Save tank
        Tank tank = new Tank();
        tank.setName("Tigris tank");
        tank.setType("tankkk");
        tank.setCreatedDate(new Date());
        tank.setLastModifiedDate(new Date());
//        Long id = insertTank(tank);

//        Tank persisTank = findById(id);
        //Find by id

//
//        persisTank.setName("test");
//        persisTank.setCreatedDate(new Date());
//        update(persisTank);

        for (Tank t : findAll()) {
            System.out.println(t.getId() + " " + t.getName());
        }

        DatabaseSessionProvider.getInstance().getSessionObj().close();
    }

    private static Long insertTank(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        Transaction transaction = session.beginTransaction();

        session.save(tank);
        transaction.commit();
        return tank.getId();
    }

    private static Tank findById(Long id) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
//        Itt adjuk meg melyik táblában keresünk
//                        |
//                        |
//                        ˇ
        Tank tank = session.find(Tank.class, id);
        return tank;
    }

    private static void update(Tank tank) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();

        session.update(tank);
        session.getTransaction().commit();
    }

    private static List<Tank> findAll() {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        //JPQL szintaxis
        Query<Tank> query = session.createQuery("select n from Tank n", Tank.class);
        return query.getResultList();
    }

    private static Long insertAircraft(Aircraft aircraft){
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        Transaction transaction = session.beginTransaction();

        session.save(aircraft);
        transaction.commit();
        return aircraft.getId();
    }
}
