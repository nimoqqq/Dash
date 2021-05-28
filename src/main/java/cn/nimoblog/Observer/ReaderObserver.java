package cn.nimoblog.Observer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * @title: ReaderObserver
 * @Author Chuf
 * @Date: 2021/5/17 3:34 下午
 * @Version 1.0
 */
@RequiredArgsConstructor
public class ReaderObserver implements Observer {

    @NonNull
    private String name;

    private String article;


    @Override
    public void update(Observable o, Object arg) {
        updateArticle(o);
    }

    private void updateArticle(Observable o){
        ArticleObservable articleObservable = (ArticleObservable)o;
        this.article = articleObservable.getArticle();
        System.out.printf("我是读者：%s，文章已更新为：%s\n", this.name, this.article);
    }
}
