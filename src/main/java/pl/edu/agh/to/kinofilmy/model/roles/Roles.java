package pl.edu.agh.to.kinofilmy.model.roles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The data model class for a record in the Roles database, containing info about permissions assigned to each employee role.
 *
 */
@Entity
public class Roles {
    private Long id;

    private String roleName;

    private boolean manageUsers;

    private boolean manageTickets;

    private boolean manageDatabase;

    public Roles(String roleName, boolean manageUsers, boolean manageTickets, boolean manageDatabase) {
        this.roleName = roleName;
        this.manageUsers = manageUsers;
        this.manageTickets = manageTickets;
        this.manageDatabase = manageDatabase;
    }

    public Roles() {

    }

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

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' + '}';
    }
}
