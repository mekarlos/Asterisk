package Datos;

import Modelo.Relacion;
import Modelo.Tema;
import Modelo.Termino;
import Modelo.Titulo;
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

    public Relacion obtenerRelacion(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Relacion.class, id);
    }

    public Tema obtenerTema(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Tema.class, id);
    }

    public Titulo obtenerTitulo(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Titulo.class, id);
    }

    public Termino obtenerTermino(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Termino.class, id);

    }

    public Termino obtenerTermino(String termino) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Termino d where lower(d.termino) like :t");
        query.setParameter("t", "" + termino.toLowerCase() + "");
        ArrayList<Termino> ts = new ArrayList<>(query.getResultList());
        if (ts.size() > 0) {
            return ts.get(0);
        } else {
            return null;
        }
    }

    public ArrayList<Termino> obtenerListaTerminos(String termino, Tema tema) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select d from Termino d where d.temas.tema=:tema and lower(d.termino) like :t");
        query.setParameter("t", "" + termino.toLowerCase() + "");
        query.setParameter("tema", tema);
        ArrayList<Termino> ts = new ArrayList<>(query.getResultList());
        return ts;
    }

    public ArrayList<Termino> obtenerListaTerminos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Termino d");
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Tema> obtenerListaTemas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Tema d");
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Relacion> obtenerListaRelaciones() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Relacion d");
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Titulo> obtenerListaTitulos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Titulo d");
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Titulo> obtenerListaTitulosTema(Tema tema) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select d from Titulo d where d.tema=:t and d.principal is null");
        query.setParameter("t", tema);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Termino> obtenerListaTerminosTema(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AsteriskPU");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select d from Termino d where d.tema.id=:id");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public Termino darTerminoAleatorio() {
        Random r = new Random();
        ArrayList<Termino> terminos = obtenerListaTerminos();
        return obtenerListaTerminos().get(r.nextInt(terminos.size()));
    }

    public static void main(String[] args) {

        DAO dao = new DAO();

        Relacion r = new Relacion();

        r.setConexion("sa");

        dao.create(r);
    }
}
