package coffee.order;

import entity.Order;
import hibernate.HibernateSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.List;

public class CoffeeOrderBoard implements DaoCoffeeOrder<Order> {
    private final SessionFactory sessionFactory = HibernateSession.getSessionFactory();
    private static final Logger LOGGER = LogManager.getLogger(CoffeeOrderBoard.class);

    //main methods
    public int add(Order order) {
        addOrder(order);
        LOGGER.info("Order with number " + order.getOrderNumber() + " have been added to the system");
        return order.getOrderNumber();
    }

    public void deliver() {
        Order order = getOrderByMaxID();
        LOGGER.info("Got order with max number " + order.getOrderNumber());
        deleteOrder(order);
        LOGGER.info("Order with number " + order.getOrderNumber() + " have been removed from the system");
        LOGGER.info("Latest order with number " + order.getOrderNumber() + " delivered");
    }

    public void deliver(int orderNumber) {
        Order order = getOrderByID(orderNumber);
        LOGGER.info("Got order with number " + order.getOrderNumber());
        deleteOrder(order);
        LOGGER.info("Order with number " + order.getOrderNumber() + " have been removed from the system");
        LOGGER.info("Order with number " + order.getOrderNumber() + " delivered");
    }

    public void draw() {
        List<Order> orders = getAllOrders();
        LOGGER.info("List of all orders have been received");
        System.out.println("=========\n" + "Num | Name");
        String list = Arrays.toString(orders.toArray())
                .replace("[", " ").replace("]", "").replace(",", "");
        System.out.println(list);
        LOGGER.info("Current state of queue have been received as per request");
    }

    //Auxiliary methods
    @Override
    public Order getOrderByID(int orderNumber) {
        return sessionFactory.openSession().get(Order.class, orderNumber);
    }

    @Override
    public List<Order> getAllOrders() {
        return sessionFactory.openSession().createQuery("From Order", Order.class).list();
    }

    @Override
    public void addOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(order);
        transaction.commit();
        session.close();
    }

    public Order getOrderByMaxID() {
        Session session = sessionFactory.openSession();
        int maxId = (int) session.createQuery("SELECT max(orderNumber) from Order").getSingleResult();
        return session.get(Order.class, maxId);
    }
}
