package basic.proxy.staticproxy;

/**
 * 被代理类
 */
public class HeiHeiHeiMovie implements Movie {

    @Override
    public void play() {
        System.out.println("请欣赏小电影！！！");
    }

}
