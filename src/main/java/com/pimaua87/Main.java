package com.pimaua87;

import coffee.order.CoffeeOrderBoard;
import entity.Order;

public class Main {

    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.add(new Order("Leo", "coffee"));
        coffeeOrderBoard.deliver();
        coffeeOrderBoard.deliver(3);
        coffeeOrderBoard.draw();
    }
}
