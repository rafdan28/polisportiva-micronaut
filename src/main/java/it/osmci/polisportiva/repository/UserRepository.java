package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
