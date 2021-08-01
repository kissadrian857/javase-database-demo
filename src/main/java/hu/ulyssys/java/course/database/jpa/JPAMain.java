package hu.ulyssys.java.course.database.jpa;

import hu.ulyssys.java.course.database.jpa.dao.CustomerDAO;
import hu.ulyssys.java.course.database.jpa.entity.Customer;

public class JPAMain {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = new Customer();
        customer.setCash(1);
        customer.setFullName("KAI");
        customer.setUsername("kisswadrian857");

        customerDAO.save(customer);
    }
}
