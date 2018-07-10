package basic.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class XiaoFei implements InvocationHandler {
    public Object xiangshou;
    
    public XiaoFei(Object o){
        this.xiangshou = o;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        select();
        method.invoke(xiangshou, args);
        pay();
        return null;
    }

    public void select(){
        System.out.println("预定");
    }
    
    public void pay(){
        System.out.println("付款");
    }
}
