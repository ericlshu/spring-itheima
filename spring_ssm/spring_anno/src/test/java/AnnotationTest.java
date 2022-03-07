import com.eric.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 23:07
 */
public class AnnotationTest {
    @Test
    public void test()
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
        applicationContext.close();
    }
}
