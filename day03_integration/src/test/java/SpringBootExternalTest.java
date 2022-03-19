import com.eric.Day03IntegrationApplication;
import com.eric.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description :
 *
 * @author Eric L SHU
 * @ SpringBootTest
 * @ ContextConfiguration(classes = MySpringBootApplication.class)
 * @date 2022-03-19 19:52
 */
@SpringBootTest(classes = Day03IntegrationApplication.class)
public class SpringBootExternalTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads()
    {
        System.out.println("com.eric.MySpringBootApplicationTest1.contextLoads ...");
        bookDao.save();
    }

}

