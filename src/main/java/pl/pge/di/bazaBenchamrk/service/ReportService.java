package pl.pge.di.bazaBenchamrk.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.pge.di.bazaBenchamrk.model.Report_1;
import pl.pge.di.bazaBenchamrk.utils.HibernateUtils;

import java.util.List;

public class ReportService {

    public void save(Report_1 report1){

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction trx = session.beginTransaction();

        session.save(report1);
        trx.commit();
        session.close();

    }


    public void update(Report_1 report1){

        Session session = HibernateUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.update(report1);

        transaction.commit();
        session.close();
    }

    public List<Report_1> getAllReports(){

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction trx = session.beginTransaction();

        String sql = "Select r from Report_1 r";
        Query query = session.createQuery(sql);
        List<Report_1> raporty = query.list();

        trx.commit();
        session.close();

        return raporty;
    }




}
