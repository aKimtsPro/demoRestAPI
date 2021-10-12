package bstorm.akimts.api.repository;

import bstorm.akimts.api.models.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, String> {
}
