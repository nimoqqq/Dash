package com.example.demo.ocjp.question1;

public class Test {

    public void updatePrrrice(Product product, double price) {
        price = price * 2;
        product.price = product.price + price;
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.price = 200;
        double newPrice = 100;

        Test test = new Test();
        test.updatePrrrice(product,newPrice);
        System.out.println(product.price + " : " + newPrice);
    }
}
