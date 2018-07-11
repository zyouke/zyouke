package test.com.zyouke.spi;

import com.zyouke.spi.IHelloSpi;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @Author: zhoujun
 */
public class SpiTest {

    @Test
    public void test(){
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        for (IHelloSpi helloSpi : serviceLoader){
            helloSpi.print();
        }
    }

}
