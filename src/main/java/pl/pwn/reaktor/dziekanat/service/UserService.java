package pl.pwn.reaktor.dziekanat.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

import java.io.Serializable;
import java.util.List;

import static pl.pwn.reaktor.dziekanat.model.RoleEnum.ROLE_ADMIN;


public class UserService {


    public static int addToDB(String login, String pass)
    {
    User user = new User(login, pass, RoleEnum.ROLE_STUDENT, true);

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

    public List<StudentDTO> getAllStudent(){
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction trx = session.beginTransaction();
        //JPQL hibernate daje mozliwosc do zmapowania go od razu na klase ktora nie jest encja
      String hql = "Select new pl.pwn.reaktor.dziekanat.model.dto.StudentDTO(s.id,u.login,u.active, s.firstName, s.lastName, s.address.street,s.address.city) from " +
              "User u inner join u.student s where u.role = 'ROLE_STUDENT'";

      Query query = session.createQuery(hql);
      List<StudentDTO> students = query.list();

        trx.commit();
        session.close();
        return students;
    }



}
