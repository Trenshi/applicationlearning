/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.hemcruz
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByIdDepartamento", query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Departamento.findByNomeDepartamento", query = "SELECT d FROM Departamento d WHERE d.nomeDepartamento LIKE :nomeDepartamento"),
    @NamedQuery(name = "Departamento.findByFoneDepartamento", query = "SELECT d FROM Departamento d WHERE d.foneDepartamento = :foneDepartamento")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDepartamento")
    private String idDepartamento;
    @Basic(optional = false)
    @Column(name = "nomeDepartamento")
    private String nomeDepartamento;
    @Basic(optional = false)
    @Column(name = "foneDepartamento")
    private String foneDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamento", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarioList;

    public Departamento() {
    }

    public Departamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Departamento(String idDepartamento, String nomeDepartamento, String foneDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.foneDepartamento = foneDepartamento;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getFoneDepartamento() {
        return foneDepartamento;
    }

    public void setFoneDepartamento(String foneDepartamento) {
        this.foneDepartamento = foneDepartamento;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Departamento[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
