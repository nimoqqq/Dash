package cn.nimoblog.Observer;



import lombok.Getter;

import java.util.Observable;

/**
 * @title: ArticleObservable
 * @Author Chuf
 * @Date: 2021/5/17 3:26 下午
 * @Version 1.0
 */

@Getter
public class ArticleObservable extends Observable {

    private String article;

    public void publish(String article){
        //发表文章
        this.article =article;

        //改变状态
        this.setChanged();

        //通知所有观察者
        this.notifyObservers();
    }
}
