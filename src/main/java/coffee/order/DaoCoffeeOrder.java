package coffee.order;

import java.util.List;

public interface DaoCoffeeOrder<T> {

    T getOrderByID(int orderNumber);

    List<T> getAllOrders();

    void addOrder(T t);

    void updateOrder(T t);

    void deleteOrder(T t);
}
