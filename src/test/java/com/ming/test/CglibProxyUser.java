package com.ming.test;

import com.ming.service.IUserService;
import com.ming.service.impl.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于子类的动态代理
 */
public class CglibProxyUser implements MethodInterceptor {
    //被代理的目标对象
    private Object tagert;

    //创建代理对象
    public Object createProxy(Object tagert) {
        //指定被代理的父类
        this.tagert = tagert;
        //创建代理对象
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(this.tagert.getClass());
        //设置回调方法，传入该对象的所有字段和方法信息
        enhancer.setCallback(this);
        //返回代理对象的信息
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String name = objects[0].toString();
        Method work = this.tagert.getClass().getMethod("work", String.class);
        Object resultValue = null;
        if (work != null) {
            //执行代理的方法
            resultValue = method.invoke(this.tagert, name + "先吃饭！！");
        } else {
            System.out.println("cglib动态代理执行失败！！");
        }
//        String  name = objects[0].toString();
//        if("work".equals(method.getName())){
//            System.out.println("cglib基于子类的动态代理");
//            resultValue = method.invoke(this.tagert,name);
//        }
        return resultValue;
    }


    public static void main(String[] args) {
        CglibProxyUser proxyUser = new CglibProxyUser();
        IUserService userService = (IUserService) proxyUser.createProxy(new UserServiceImpl());
        userService.work("cglibProxy");
    }
//       @Test
//       public void userCglibTest(){
//           UserCglib userCglib = new UserCglib();
//
//           UserCglib user = (UserCglib) Enhancer.create(userCglib.getClass(), new MethodInterceptor() {
//               @Override
//               public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                   Object result = null;
//                   String name = objects[0].toString();
//                   if("work".equals(method.getName())){
//                       System.out.println(name+"吃完饭");
//                       result = method.invoke(userCglib,name);
//                   }
//                   return result;
//               }
//           });
//
//           user.work("cglibProxy");
//       }
}
