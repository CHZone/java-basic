package basic.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
        Wine maotai = new Maotai();
        XiaoFei xiaofei1 = new XiaoFei(maotai);
        Wine maotaiProxy = (Wine) Proxy.newProxyInstance(Maotai.class.getClassLoader(),maotai.getClass().getInterfaces(), xiaofei1);
        maotaiProxy.sellWine();
        
        SpecialService dabaojian = new DaBaoJian();
        XiaoFei xiaofei2 = new XiaoFei(dabaojian);
        SpecialService tianShangRenJianProxy = (SpecialService)Proxy.newProxyInstance(DaBaoJian.class.getClassLoader(), 
                DaBaoJian.class.getInterfaces(), xiaofei2);
        tianShangRenJianProxy.server();
    }
}
