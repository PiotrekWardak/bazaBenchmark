package pl.pge.di.bazaBenchamrk.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pge.di.bazaBenchamrk.model.Survey;
import pl.pge.di.bazaBenchamrk.utils.HibernateUtils;

import java.util.List;

public class SurveyService {

    public void save(Survey survey) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.save(survey);

        trx.commit();
        session.close();

    }

    public void delete(Survey survey) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        session.delete(survey);

        trx.commit();
        session.close();

    }

    public List<Survey> get20lastSurveys() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        String sql = "Select s from Survey s";
        Query query = session.createQuery(sql).setMaxResults(20);
        List<Survey> surveys = query.list();

        trx.commit();
        session.close();

        return surveys;


    }

    public List<Survey> getlastSurveysRecord() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        String sql = "Select s from Survey s";
        Query query = session.createQuery(sql).setMaxResults(1);
        List<Survey> surveys = query.list();

        trx.commit();
        session.close();

        return surveys;


    }
}
