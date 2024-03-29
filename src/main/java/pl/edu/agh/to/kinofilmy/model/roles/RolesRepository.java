package pl.edu.agh.to.kinofilmy.model.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {


    Optional<Roles> findRolesByRoleName(String name);
    Roles findByRoleName(String roleName);

}
