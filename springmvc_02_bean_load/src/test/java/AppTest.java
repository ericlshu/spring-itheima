import com.eric.config.SpringConfig;
import com.eric.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric SHU
 */
public class AppTest
{
    @Test
    public void test()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = context.getBean(UserController.class);
        System.out.println("userController = " + userController);
    }
}
