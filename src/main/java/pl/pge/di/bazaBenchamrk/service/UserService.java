package pl.pge.di.bazaBenchamrk.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pge.di.bazaBenchamrk.model.RoleEnum;
import pl.pge.di.bazaBenchamrk.model.User;

import pl.pge.di.bazaBenchamrk.utils.HibernateUtils;

import java.util.List;


public class UserService {


    public static int addToDB(String login, String pass)
    {
    User user = new User(login, pass, RoleEnum.ROLE_STANDARD_USER, true);

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

       int id = (int) session.save(user);

        transaction.commit();
        session.close();
        return id;
    }

    public static int addToDBAdmin(String login, String pass)
    {
        User user = new User(login, pass, RoleEnum.ROLE_ADMIN, true);

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        int id = (int) session.save(user);

        transaction.commit();
        session.close();
        return id;
    }

    public void update(User user){

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
    }


    public List<User> getAllAdmin(){
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Select u from User u where u.role ='ROLE_ADMIN'");
        List<User> admins = query.list();

        transaction.commit();
        session.close();

        return admins;
    }

//    public List<StudentDTO> getAllStudent(){
//        Session session = HibernateUtils.getSessionFactory().openSession();
//
//        Transaction trx = session.beginTransaction();
//        //JPQL hibernate daje mozliwosc do zmapowania go od razu na klase ktora nie jest encja
//      String hql = "Select new pl.pge.di.bazaBenchamrk.model.dto.StudentDTO(s.id,u.login,u.active, s.firstName, s.lastName, s.address.street,s.address.city) from " +
//              "User u inner join u.student s where u.role = ROLE_STANDARD_USER";
//
//      Query query = session.createQuery(hql);
//      List<StudentDTO> students = query.list();
//
//        trx.commit();
//        session.close();
//        return students;
//    }



}
