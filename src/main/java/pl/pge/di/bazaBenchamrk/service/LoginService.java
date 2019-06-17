package pl.pge.di.bazaBenchamrk.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pge.di.bazaBenchamrk.utils.HibernateUtils;
import pl.pge.di.bazaBenchamrk.model.User;

public class LoginService {

    public User login (String login, String password){
        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        String queryString = "select u from User u where u.login=:login and u.password=:pass and u.active=1";
        Query query = session.createQuery(queryString);
        query.setParameter("login",login);
        query.setParameter("pass",password);
        query.setMaxResults(1);
        User logedUser = (User) query.uniqueResult();

        transaction.commit();
        session.close();
        return logedUser;

    }




    //Zrobic rejestracje, zrobic formularz
}
