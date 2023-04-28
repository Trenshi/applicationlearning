/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.*;

public class EmpresaDAO {

    private EntityManagerFactory conn;
    private EntityManager manager;

    public void conectar() {
        conn = Persistence.createEntityManagerFactory("SitePU");
        manager = conn.createEntityManager();
    }

    public Acesso validarLogin(String u, String s) {
        conectar();
        try {
            TypedQuery<Acesso> query = manager.createNamedQuery("Acesso.findByEmailSenhaFuncionario", Acesso.class);
            query.setParameter("senhaFuncionario", s);
            query.setParameter("emailFuncionario", u);
            Acesso acesso = query.getSingleResult();
            return acesso;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public int salvarDepartamento(Departamento dep) {
        conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(dep);
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
            TypedQuery<Departamento> query = manager.createNamedQuery("Departamento.findAll", Departamento.class);
            List<Departamento> departamentos = query.getResultList();
            return departamentos;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Departamento> consultarDepartamentos(String nomeDep) {
        conectar();
        try {
            TypedQuery<Departamento> query = manager.createNamedQuery("Departamento.findByNomeDepartamento", Departamento.class);
            query.setParameter("nomeDepartamento", "%" + nomeDep + "%");
            List<Departamento> departamentos = query.getResultList();
            return departamentos;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public int excluirDepartamento(String idDep) {
        conectar();
        try {
            Departamento dep = manager.find(Departamento.class, idDep);
            if (dep == null) {
                return 2;
            } else {
                manager.getTransaction().begin();
                manager.remove(dep);
                manager.getTransaction().commit();
                return 1; // Deu certo
            }
        } catch (Exception ex) {
            return 0; //Deu qualquer erro
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
            Departamento dep = manager.find(Departamento.class, idDep);
            return dep;
        } catch (Exception ex) {
            return null;
        }
    }
}

