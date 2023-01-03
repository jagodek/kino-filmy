package pl.edu.agh.to.kinofilmy.model.roles;

import javax.persistence.*;

/**
 * The data model class for a record in the Roles database, containing info about permissions assigned to each employee role.
 *
 */
@Entity
public class Roles {
    private Long id;

    private String roleName;

    private boolean manageUsers;

    private boolean sellTickets;

    private boolean checkTickets;

    private boolean manageRoles;

    private boolean manageCinema;

    private boolean getStatistics;

    public Roles(String roleName, boolean manageUsers, boolean sellTickets, boolean manageCinema,
                 boolean checkTickets, boolean manageRoles, boolean getStatistics) {
        this.roleName = roleName;
        this.manageUsers = manageUsers;
        this.sellTickets = sellTickets;
        this.checkTickets = checkTickets;
        this.manageRoles = manageRoles;
        this.manageCinema = manageCinema;
        this.getStatistics = getStatistics;
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

    @Column(unique = true)
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

    public boolean isSellTickets() {
        return sellTickets;
    }

    public boolean isCheckTickets() {
        return checkTickets;
    }

    public void setSellTickets(boolean sellTickets) {
        this.sellTickets = sellTickets;
    }
    public void setCheckTickets(boolean checkTickets) {
        this.checkTickets = checkTickets;
    }


    public boolean isManageCinema() {
        return manageCinema;
    }

    public void setManageCinema(boolean manageCinema) {
        this.manageCinema = manageCinema;
    }

    public void setManageRoles(boolean manageRoles){this.manageRoles = manageRoles;}

    public boolean isManageRoles(){return this.manageRoles;}

    public boolean isGetStatistics() {
        return getStatistics;
    }

    public void setGetStatistics(boolean getStatistics) {
        this.getStatistics = getStatistics;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
