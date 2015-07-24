package Datos;

import Modelo.Relacion;
import Modelo.Tema;
import Modelo.Termino;
import java.util.ArrayList;
import java.util.Random;
import javax.persistence.*;

public class DAO {

    public boolean create(Object entidad) {
        boolean flag = true;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(entidad);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;

    }

    public boolean update(Object entidad) {
        boolean flag = true;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(entidad);
            trans.commit();
        } catch (Exception e) {
            flag = false;
        }
        return flag;

    }

    public boolean delete(Object entidad) {
        boolean flag = true;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(entidad));
            trans.commit();
        } catch (Exception e) {
            flag = false;
        }
        return flag;

    }

    public Termino retrieveTermino(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Termino.class, id);

    }

    public ArrayList<Termino> ListaTerminos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Termino d");
        return new ArrayList<Termino>(query.getResultList());
    }

    public ArrayList<Termino> ListaTerminosTema(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Termino d where d.tema.id=:id");
        query.setParameter("id", id);
        return new ArrayList<Termino>(query.getResultList());
    }

    public ArrayList<Tema> ListaTemas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Tema d");
        return new ArrayList<Tema>(query.getResultList());
    }

    public Termino darTermino() {
        Random r = new Random();
        ArrayList<Termino> terminos = ListaTerminos();
        return ListaTerminos().get(r.nextInt(terminos.size()));
    }

    public static void main(String[] args) {

        DAO dao = new DAO();

        Relacion r = new Relacion();

        r.setConexion("sa");

        dao.create(r);
    }
}
