package bstorm.akimts.api.repository;

import bstorm.akimts.api.models.entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
