package pl.edu.agh.to.kinofilmy.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Repository for the Employee database, allowing for operations on said database,
 * such as getting records and persisting changes.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
