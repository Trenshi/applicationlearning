/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.hemcruz
 */
@Entity
@Table(name = "acesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acesso.findAll", query = "SELECT a FROM Acesso a"),
    @NamedQuery(name = "Acesso.findByEmailFuncionario", query = "SELECT a FROM Acesso a WHERE a.emailFuncionario = :emailFuncionario"),
    @NamedQuery(name = "Acesso.findByEmailSenhaFuncionario", query = "SELECT a FROM Acesso a WHERE a.senhaFuncionario = :senhaFuncionario AND a.emailFuncionario = :emailFuncionario"),
    @NamedQuery(name = "Acesso.findBySenhaFuncionario", query = "SELECT a FROM Acesso a WHERE a.senhaFuncionario = :senhaFuncionario")})
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "emailFuncionario")
    private String emailFuncionario;
    @Basic(optional = false)
    @Column(name = "senhaFuncionario")
    private String senhaFuncionario;
    @JoinColumn(name = "emailFuncionario", referencedColumnName = "emailFuncionario", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Funcionario funcionario;

    public Acesso() {
    }

    public Acesso(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public Acesso(String emailFuncionario, String senhaFuncionario) {
        this.emailFuncionario = emailFuncionario;
        this.senhaFuncionario = senhaFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailFuncionario != null ? emailFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.emailFuncionario == null && other.emailFuncionario != null) || (this.emailFuncionario != null && !this.emailFuncionario.equals(other.emailFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Acesso[ emailFuncionario=" + emailFuncionario + " ]";
    }
    
}
