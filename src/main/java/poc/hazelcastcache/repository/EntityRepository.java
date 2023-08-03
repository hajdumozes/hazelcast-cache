package poc.hazelcastcache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.hazelcastcache.entity.Entity;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {
}
