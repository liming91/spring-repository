package com.ming.test;

import com.ming.service.IUserService;
import com.ming.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于接口的动态代理JDk
 */
public class UserJDKProxy implements InvocationHandler {
    private Object target;//定义代理的目标对象
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = args[0].toString();
                        Object result = null;
                        if ("work".equals(method.getName())) {
                            System.out.println(name + "工作前先吃饭。。");
                            result = method.invoke(target, name);
                        }
                        return result;
    }

    //生成代理对象
    public Object getJDkProxy(Object target){
        this.target = target;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        UserJDKProxy userJDKProxy = new UserJDKProxy();
       IUserService userService= (IUserService) userJDKProxy.getJDkProxy(new UserServiceImpl());
       userService.work("jdkProxy");
    }

//    @Test
//    public void JDKIntefaceProxyUser() {
//        //1.被代理的对象，要求至少实现一个接口,匿名内部内访问外部成员要求是最终的用final
//        final UserServiceImpl user = new UserServiceImpl();
//        //2.Proxy.newInstance()生成代理对象
//        IUserService iUserService = (IUserService) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    //3.InvocationHandler的invoke()需要增强的代码
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        String name = args[0].toString();
//                        Object result = null;
//                        if ("work".equals(method.getName())) {
//                            System.out.println(name + "工作前先吃饭。。");
//                            result = method.invoke(user, name);
//                        }
//                        return result;
//                    }
//                });
//
//        iUserService.work("jdkProxy代理");
//    }
}
