/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
        } catch (EntityExistsException ex) {
            return 2; // Já cadastrado
        } catch (Exception ex) {
            return 3; //Deu qualquer outro erro
        }

    }
}
