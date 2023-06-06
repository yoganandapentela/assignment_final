package java_final_assignment;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Question18 {
    public static void main(String[] args) {
     
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        
        Session session = sessionFactory.openSession();

      
        List<Student> students = session.createQuery("FROM Student").list();

       
        for (Student student : students) {
            System.out.println(student);
        }

      
        session.close();
        sessionFactory.close();
    }
}
