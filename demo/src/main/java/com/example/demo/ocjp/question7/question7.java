package com.example.demo.ocjp.question7;

public class question7 {
    String type = "4W";
    int maxSpeed = 100;

    question7(String type, int maxSpeed) {
        this.type = type;
        this.maxSpeed = maxSpeed;
    }

    question7() {
    }
}

class Car extends question7 {
    String trans;

    Car(String trans) {
        this.trans = trans;
    }

    Car(String type, int maxSpeed, String trans) {
        super(type, maxSpeed);
        this.trans = trans;
    }
}

class test {
    public static void main(String[] args) {
        Car car1 = new Car("auto");
        Car car2 = new Car("4w", 150, "manual");

        System.out.println(car1.type + car1.maxSpeed + car1.trans);
        System.out.println(car2.type + car2.maxSpeed + car2.trans);
    }
}
