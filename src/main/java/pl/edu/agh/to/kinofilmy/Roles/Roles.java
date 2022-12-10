package pl.edu.agh.to.kinofilmy.Roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roles {
    private Long id;

    private String roleName;

    private boolean manageUsers;

    private boolean manageTickets;

    private boolean manageDatabase;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isManageUsers() {
        return manageUsers;
    }

    public void setManageUsers(boolean manageUsers) {
        this.manageUsers = manageUsers;
    }

    public boolean isManageTickets() {
        return manageTickets;
    }

    public void setManageTickets(boolean manageTickets) {
        this.manageTickets = manageTickets;
    }

    public boolean isManageDatabase() {
        return manageDatabase;
    }

    public void setManageDatabase(boolean manageDatabase) {
        this.manageDatabase = manageDatabase;
    }
}