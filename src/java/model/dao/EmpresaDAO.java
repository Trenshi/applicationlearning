package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.*;
import model.entity.Acesso;
import model.entity.Departamento;
import model.entity.Funcionario;

public class EmpresaDAO {

    private EntityManager manager;

    private void conectar() {
        manager = Persistence.createEntityManagerFactory("SitePU").createEntityManager();
    }

    public Acesso validarLogin(String u, String s) {
        conectar();
        try {
            TypedQuery<Acesso> query = manager.createNamedQuery("Acesso.findByEmailSenhaFuncionario", Acesso.class);
            query.setParameter("senhaFuncionario", s);
            query.setParameter("emailFuncionario", u);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public <T> int salvar(T obj) {
        conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(obj);
            manager.getTransaction().commit();
            return 1; // Departamento cadastrado
        } catch (RollbackException ex) {
            return 2; // Já cadastrado
        } catch (Exception ex) {
            return 3; //Deu qualquer outro erro
        }
    }

    public List<Departamento> listarDepartamentos() {
        conectar();
        try {
            return manager.createNamedQuery("Departamento.findAll", Departamento.class).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Funcionario> listarFuncionarios() {
        conectar();
        try {
            return manager.createNamedQuery("Funcionario.findAll", Funcionario.class).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public <T> int excluir (T classe, String valor) {
        conectar();
        try {
            T obj = (T) manager.find(classe.getClass(), valor);
            if (obj == null) {
                return 2; // Não existe
            } else {
                manager.getTransaction().begin();
                manager.remove(obj);
                manager.getTransaction().commit();
                return 1; // Excluiu.
            }
        } catch (Exception ex) {
            return 0; // Deu qualquer outro erro
        }
    }
      
    public List<Departamento> consultarDepartamentos(String nomeDep) {
        conectar();
        try {
            return manager.createNamedQuery("Departamento.findByNomeDepartamento", Departamento.class).setParameter("nomeDepartamento", "%" + nomeDep + "%").getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public int alterarDepartamento(String idDep, String nomeDep, String foneDep) {
        conectar();
        try {
            Departamento dep = manager.find(Departamento.class, idDep);
            dep.setNomeDepartamento(nomeDep);
            dep.setFoneDepartamento(foneDep);
            manager.getTransaction().begin();
            manager.merge(dep);
            manager.getTransaction().commit();
            return 1; // Deu certo
        } catch (Exception ex) {
            return 0; //Deu qualquer erro
        }
    }

    public Departamento buscarDepartamento(String idDep) {
        conectar();
        try {
            return manager.find(Departamento.class, idDep);
        } catch (Exception ex) {
            return null;
        }
    }

}
