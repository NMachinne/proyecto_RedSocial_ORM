package proyecto.RedSocial.proyecto.model.DAO;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class ADAO <T>{
	
	protected static EntityManager manager;
	protected static EntityManagerFactory emf;
	
	public boolean create(T o) {
        boolean added = false;
       
        if(!manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.persist(o);
                manager.getTransaction().commit();
                manager.close();
                added = true;
            } catch (Exception e) {
                e.printStackTrace();
                added = false;
            }
        }
        return added;
    }
    public boolean update(T o) {
        boolean updated = false;
        if(manager.contains(o)) {
            try {
                manager.getTransaction().begin();
                manager.merge(o);
                manager.getTransaction().commit();
                manager.close();
                updated = true;
            } catch (Exception e) {
                e.printStackTrace();
                updated = false;
            }
        }
        return updated;
    }
    public boolean delete(T o, Class<T> c,int id) {
        boolean removed = false;
            o=manager.find(c,id);
        if(manager.contains(o)){
            try {
                manager.getTransaction().begin();
                manager.remove(o);
                manager.getTransaction().commit();
                removed = true;
            } catch (Exception e) {
                e.printStackTrace();
                removed = false;
            }
        }
        return removed;

    }
    public T find(int id, Class<T> c) {
        try {
            manager.getTransaction().begin();
            T o = manager.find(c, id);
            manager.getTransaction().commit();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
}