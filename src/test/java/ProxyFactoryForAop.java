import com.ming.service.UserService;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
public class ProxyFactoryForAop {

    @Test
    public void test1(){
        //定义目标对象
         UserService  userService = new UserService();
        //定义joinPoint连接点是指程序执行过程中明确的点，如方法的调用或特定异常的抛出，
        // 一般由两个信息确定：方法：表示程序执行点，即在哪个目标方法2，相对点：表示方位，即目标方法的什么位置，比如调用前，调用后
        //连接点就是被拦截到的程序执行点，spring只支持方法类型的连接点，所以在spring中连接点就是被拦截到的方法。

        Pointcut pointcut = new Pointcut(){

            public ClassFilter getClassFilter() {
                return clazz ->UserService.class.isAssignableFrom(clazz);
            }

            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> aClass) {
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> aClass, Object... objects) {
                        return false;
                    }
                };
            }
        };
        //创建通知在方法执行前通知
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("您好！"+objects[0]+"在工作前和您打招呼了");
            }
        };
        //将切入点和通知组装
        DefaultPointcutAdvisor  defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut,methodBeforeAdvice);
        //创建工程代理对象
        ProxyFactory proxyFactoryBean = new ProxyFactory();
        //设置代理的目标类
        proxyFactoryBean.setTarget(userService);
        //为目标添加增添功能
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor);

        UserService  pro= (UserService) proxyFactoryBean.getProxy();
        pro.work("路人");
    }
}
