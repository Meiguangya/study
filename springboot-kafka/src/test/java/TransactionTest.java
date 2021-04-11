import com.cool.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author water33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class TransactionTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    @Transactional(transactionManager = "kafkaTransactionManager",rollbackFor = Exception.class)
    public void testExecuteInTransaction2(){
        kafkaTemplate.send("topic1", "xixi");
        //throw new RuntimeException("fail");
        int a = 1/0;
    }


    @Test
    public void testExecuteInTransaction() throws InterruptedException {
        try {
            kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
                @Override
                public Object doInOperations(KafkaOperations kafkaOperations) {
                    kafkaOperations.send("topic1", "hello");
                    throw new RuntimeException("fail");
                    //return true;
                }
            });
        } catch (Exception e) {
            System.out.println("try to callback... i will do something");
        } finally {

        }

    }

}
