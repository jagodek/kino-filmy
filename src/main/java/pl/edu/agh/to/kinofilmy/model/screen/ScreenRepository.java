package pl.edu.agh.to.kinofilmy.model.screen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    Optional<Screen> findScreenById(Long id);
}
