package my.cats.service;

import my.cats.model.Cat;
import my.cats.store.HibernateStore;

import java.util.List;

public class MyService {

    private final HibernateStore hibernateStore = new HibernateStore();

    public Cat save(Cat cat) {
        return hibernateStore.save(cat);
    }

    public List<Cat> getAll() {
        return hibernateStore.getAll();
    }
}
