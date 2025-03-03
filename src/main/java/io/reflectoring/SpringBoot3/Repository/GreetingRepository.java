package io.reflectoring.SpringBoot3.Repository;

import io.reflectoring.SpringBoot3.Entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
