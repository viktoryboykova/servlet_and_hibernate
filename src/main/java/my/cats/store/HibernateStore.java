package my.cats.store;

import my.cats.model.Cat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

public class HibernateStore {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T function(final Function<Session, T> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (HibernateException e) {
            throw e;
        }
    }

    public Cat save(Cat cat) {
        return this.function(
                session -> {
                    session.save(cat);
                    return cat;
                }
        );
    }

    public List<Cat> getAll() {
        return this.function(
                session -> session.createQuery("from Cat").list()
        );
    }
}
