package DMteam.Spring_Link_Short;

import org.springframework.data.repository.CrudRepository;

public interface ShorterRepository extends CrudRepository<Shorter, Long> {
    Shorter findByHash(String hash);
}
