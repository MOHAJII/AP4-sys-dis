package net.haji.repositories;

import net.haji.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeynoteRepository extends JpaRepository<Integer, Keynote> {
}
