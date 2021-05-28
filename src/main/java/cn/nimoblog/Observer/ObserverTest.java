package cn.nimoblog.Observer;

/**
 * @title: ObserverTest
 * @Author Chuf
 * @Date: 2021/5/17 3:45 下午
 * @Version 1.0
 *
 * https://mp.weixin.qq.com/s/bUUqp7DCVvQPP-H1EIGrnw
 */
public class ObserverTest {

    public static void main(String[] args) {
        // 创建一个观察目标
        ArticleObservable javaStackObservable = new ArticleObservable();

        // 添加观察者
        javaStackObservable.addObserver(new ReaderObserver("小明"));
        javaStackObservable.addObserver(new ReaderObserver("小张"));
        javaStackObservable.addObserver(new ReaderObserver("小爱"));

        // 发表文章
        javaStackObservable.publish("什么是观察者模式？");
    }
}
