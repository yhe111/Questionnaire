package questionnaire.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import questionnaire.database.Choice;
import questionnaire.database.QChoose;
import questionnaire.database.QuestionType;
import questionnaire.database.QuestionnaireTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class QuestionTools {

    public static void createQuestion(QuestionType question) {
        try (Session session = SessionFactorySource.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(question);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuestion(String id) {
        try (Session session = SessionFactorySource.getSessionFactory().openSession()) {
            session.beginTransaction();
            QuestionType question = session.load(QuestionType.class, id);
            session.delete(question);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void updateQuestion(QuestionType question) {
        try (Session session = SessionFactorySource.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(question);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static List<QuestionType> readQuestion(String parentid) {
        List<QuestionType> questions = null;
        try (Session session = SessionFactorySource.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM QuestionType where parentTable='" + parentid + "'";
            questions = session.createQuery(hql).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        if (questions != null) {
            questions.sort(Comparator.comparing(QuestionType::getQuestionOrder));
        }

        return questions;
    }

}
