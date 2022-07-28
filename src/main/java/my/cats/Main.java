package my.cats;

import my.cats.model.Cat;
import my.cats.store.HibernateStore;

public class Main {

    public static void main(String[] args) {
        HibernateStore hibernateStore = new HibernateStore();
        hibernateStore.save(new Cat("Киса"));
        hibernateStore.save(new Cat("Кузя"));
        System.out.println(hibernateStore.getAll().toString());
    }
}
