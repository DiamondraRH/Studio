package application.models;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
public class Role {
    @Id
    @Column(name = "id_role")
    private int idRole;

    @Column(name = "libelle_role")
    private String libelleRole;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getLibelleRole() {
        return libelleRole;
    }

    public void setLibelleRole(String libelleRole) {
        this.libelleRole = libelleRole;
    }
}
