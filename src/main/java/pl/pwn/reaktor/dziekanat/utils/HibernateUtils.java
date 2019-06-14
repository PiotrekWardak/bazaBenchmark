package pl.pwn.reaktor.dziekanat.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.service.ServiceRegistry;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.User;


public class HibernateUtils {

    //ta klasa sluzy do ostatecznego nawiazania polaczenia z baza
    // singleton (wzorzec/antywzorzec) jedna instancja klasy dla 1 systemu


    private static final SessionFactory SESSION_FACTORY;
// blok statyczny jest wywolywany przed konstruktorem

    private HibernateUtils(){

        throw new IllegalStateException("HibernateUtils is only utility class");
    }


    static{

        try{

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            //dzieki addAnnotatedClass mamy automatyczne mapowanie danej klasy na tabele w bazie danych
            //musimy dla każdej Entity (encji) dodawac nowy wpis zeby dzialalo/
//            MetadataSources sources = new MetadataSources(serviceRegistry).addAnnotatedClass("nasz model".class)

            MetadataSources sources = new MetadataSources(serviceRegistry).addAnnotatedClass(User.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Survey.class);

            Metadata metadata = sources.getMetadataBuilder().build();

            SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();


        } catch (Exception e){

            System.err.println("Session Factory could not be created\n"+e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void initSessionFactory(){
        System.out.println("Initialize Hibernate session factory");
    }

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    public static void destroySessionFactory(){
        System.out.println("Destroy Hibernate session factory");
        SESSION_FACTORY.close();
    }


}
