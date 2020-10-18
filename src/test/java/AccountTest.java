import com.ming.model.Account;
import com.ming.service.AccountService;
import com.ming.util.AccountProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountTest {
    @Qualifier("accountFactory")
    @Autowired
    private AccountService accountService;

    @Test
    public void  addTest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService=context.getBean("accountService",AccountService.class);
        Account account = new Account();
        account.setName("ddd");
        account.setMoney(1000f);
        accountService.saveAccount(account);
    }
    @Test
    public void  findAllTest(){
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService=context.getBean("accountService",AccountService.class);*/

        List<Account> li=accountService.findAll();
        System.out.println(li);
    }
    @Test
    public void  addTransef(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService=context.getBean("accountService",AccountService.class);
        accountService.transfer("aaa","bbb",100f);
    }
    @Test
    public void tansefProxyTest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AccountService accountService=context.getBean("accountService",AccountService.class);
        AccountService accountProxy=AccountProxy.createProxy(accountService,AccountService.class);
        accountProxy.transfer("aaa","bbb",100f);

    }
    @Test
    public void addTansef(){
        accountService.transfer("aaa","bbb",100f);
    }
    
}
