package questionnaire.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import questionnaire.database.Choice;
import questionnaire.database.QChoose;

import java.util.List;

public class ChoiceTools {

    public static void createChoice(Choice choice){
        try (Session session = SessionFactorySource.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(choice);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void deleteChoice(String id){
        try(Session session = SessionFactorySource.getSessionFactory().openSession()){
            session.beginTransaction();
            Choice choice = session.load(Choice.class, id);
            session.delete(choice);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void updateChoice(Choice choice){
        try(Session session = SessionFactorySource.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(choice);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static List<Choice> readChoice(String parentid){
        List<Choice> choices = null;
        try(Session session = SessionFactorySource.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "FROM Choice where parentQ='" + parentid + "'";
            choices = session.createQuery(hql).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return choices;
    }
}
