package ma.xproce.repositories;

import ma.xproce.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Optional<Creator> findByEmail(String email);
}
