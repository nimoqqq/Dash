package com.example.demo.ocjp.question6;

/**
 * 子类继承父类方法，但是访问权限小于父类导致报错
 * 修改：
 *  将权限改为 public 或 protected
 */
public abstract class Planet {

    protected void revolve() {

    }

    abstract void rotate();
}

class Earth extends Planet{

//    @Override
//    void revolve() {
//        super.revolve();
//    }

    @Override
    protected void rotate() {

    }
}
