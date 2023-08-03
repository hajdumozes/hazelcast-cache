package poc.hazelcastcache.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import poc.hazelcastcache.entity.Entity;
import poc.hazelcastcache.repository.EntityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CacheConfig(cacheNames = "entities")
public class EntityService {
    EntityRepository repository;

    @Cacheable
    public List<Entity> findAll() {
        simulateSlowService();
        return repository.findAll();
    }

    @Cacheable
    public Optional<Entity> findById(Integer id) {
        simulateSlowService();
        return repository.findById(id);
    }

    public void persist(Entity entity) {
        repository.save(entity);
    }

    public void update(Integer id, Entity entity) {
        repository.findById(id)
                .ifPresent(existing -> repository.save(entity));
    }

    public void delete(Integer id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
