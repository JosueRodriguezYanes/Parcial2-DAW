package libcode.webapp.controladores;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import libcode.webapp.entidades.Alumno;
import libcode.webapp.entidades.Materia;
import libcode.webapp.negocio.DataService;

@Named
@RequestScoped
public class IndexController {
    private List<Alumno> alumnosList = new ArrayList<>();
    private List<Materia> materiasList = new ArrayList<>();
    
    private Alumno alumno = new Alumno();
    private Materia materia = new Materia();
    
    @EJB
    DataService servicio;
    
    @PostConstruct
    public void cargarDatos()
    {
        cargarAlumnos();
        cargarMaterias();
    }
    
    public void cargarAlumnos()
    {
        alumnosList = servicio.getAlumnos();
    }
    
    public void cargarMaterias()
    {
        materiasList = servicio.getMaterias();
    }
    
    public void guardarAlumno()
    {
        if(alumno.getId()!=null)
        {
            servicio.editAlumno(alumno);
        }else{
            servicio.saveAlumno(alumno);
        }
        alumno = new Alumno();
        cargarAlumnos();
    }
    
    public void guardarMateria()
    {
        if(materia.getId() != null)
        {
            servicio.editMateria(materia);
        }
        else
        {
            servicio.saveMateria(materia);
        }
        materia = new Materia();
        cargarMaterias();
    }
    
    public void llenarFormEditarAlumno(Alumno alumno)
    {
        this.alumno.setId(alumno.getId());
        this.alumno.setNombre(alumno.getNombre());
        this.alumno.setCarnet(alumno.getCarnet());
    }
    
    public void llenarFormEditarMateria(Materia materia)
    {
        this.materia.setId(materia.getId());
        this.materia.setNombre(materia.getNombre());
        this.materia.setDescripcion(materia.getDescripcion());
        this.materia.setCodigo(materia.getCodigo());
    }
    
    public void eliminarAlumno(Alumno alumno)
    {
        servicio.deleteAlumno(alumno);
        cargarAlumnos();
    }
    
    public void eliminarMateria(Materia materia)
    {
        servicio.deleteMateria(materia);
        cargarMaterias();
    }
    
    // Getters y Setters
    public List<Alumno> getAlumnosList() {
        return alumnosList;
    }

    public void setAlumnosList(List<Alumno> alumnosList) {
        this.alumnosList = alumnosList;
    }
    
    public List<Materia> getMateriasList() {
        return materiasList;
    }

    public void setMateriasList(List<Materia> materiasList) {
        this.materiasList = materiasList;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
