import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by sh00514 on 2017/2/5.
 * Demo
 */

public class DemoBeanFieldExtractor {

    @Test
    public void test(){
        Wheel wheel = new Wheel();
        wheel.setBrand("Dunlop");

        Car car = new Car();
        car.setWheels(new ArrayList<Wheel>(){{add(wheel);}});

        BeanWrapperFieldExtractor<Car> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[]{"wheels[0].brand"});
        Object[] objects = extractor.extract(car);
        System.out.println(objects[0].toString());
    }
}
