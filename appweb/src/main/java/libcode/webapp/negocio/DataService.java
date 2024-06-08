package libcode.webapp.negocio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import libcode.webapp.entidades.Alumno;
import libcode.webapp.entidades.Materia;

@Stateless
public class DataService {
    
    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;  
    
    // Operaciones CRUD para Alumnos
    
    public List<Alumno> getAlumnos(){
        Query query = entityManager.createQuery("SELECT e FROM Alumno e ORDER BY e.id DESC");
        return query.getResultList();
    }
    
    @Transactional
    public void saveAlumno(Alumno alumno){
        entityManager.persist(alumno);
    }
    
    @Transactional
    public void editAlumno(Alumno alumno){
        entityManager.merge(alumno);
    }
    
    @Transactional
    public void deleteAlumno(Alumno alumno){
        Alumno alumnoEliminar = entityManager.find(Alumno.class, alumno.getId());
        entityManager.remove(alumnoEliminar);
    }
    
    // Operaciones CRUD para Materias
    
    public List<Materia> getMaterias(){
        Query query = entityManager.createQuery("SELECT e FROM Materia e ORDER BY e.id DESC");
        return query.getResultList();
    }
    
    @Transactional
    public void saveMateria(Materia materia){
        entityManager.persist(materia);
    }
    
    @Transactional
    public void editMateria(Materia materia){
        entityManager.merge(materia);
    }
    
    @Transactional
    public void deleteMateria(Materia materia){
        Materia materiaEliminar = entityManager.find(Materia.class, materia.getId());
        entityManager.remove(materiaEliminar);
    }
}
