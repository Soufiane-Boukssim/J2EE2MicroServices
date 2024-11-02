package ma.xproce.inventoryservice.repositories;

import ma.xproce.inventoryservice.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {


}
