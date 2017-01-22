package com.leo;

import com.leo.model.Person;
import org.springframework.batch.item.ItemWriter;

import java.util.List;


/**
 * Created by sh00514 on 2017/1/22.
 * batch reader
 */
public class DemoWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> items) throws Exception {
        items.forEach(person -> System.out.println(person.getName() + " is " + person.getAge() + " is married:" + person.getMarried()));
    }
}
