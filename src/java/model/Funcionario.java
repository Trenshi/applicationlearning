package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByEmailFuncionario", query = "SELECT f FROM Funcionario f WHERE f.emailFuncionario = :emailFuncionario"),
    @NamedQuery(name = "Funcionario.findByNomeFuncionario", query = "SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nomeFuncionario"),
    @NamedQuery(name = "Funcionario.findByCargoFuncionario", query = "SELECT f FROM Funcionario f WHERE f.cargoFuncionario = :cargoFuncionario"),
    @NamedQuery(name = "Funcionario.findBySalarioFuncionario", query = "SELECT f FROM Funcionario f WHERE f.salarioFuncionario = :salarioFuncionario")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "emailFuncionario")
    private String emailFuncionario;
    @Basic(optional = false)
    @Column(name = "nomeFuncionario")
    private String nomeFuncionario;
    @Basic(optional = false)
    @Column(name = "cargoFuncionario")
    private String cargoFuncionario;
    @Basic(optional = false)
    @Column(name = "salarioFuncionario")
    private double salarioFuncionario;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.EAGER)
    private Acesso acesso;
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Departamento idDepartamento;

    public Funcionario() {
    }

    public Funcionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public Funcionario(String emailFuncionario, String nomeFuncionario, String cargoFuncionario, double salarioFuncionario) {
        this.emailFuncionario = emailFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.emailFuncionario == null && other.emailFuncionario != null) || (this.emailFuncionario != null && !this.emailFuncionario.equals(other.emailFuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Funcionario[ emailFuncionario=" + emailFuncionario + " ]";
    }
    
}
