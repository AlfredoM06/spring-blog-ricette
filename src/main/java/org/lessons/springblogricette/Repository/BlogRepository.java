package org.lessons.springblogricette.Repository;

import org.lessons.springblogricette.Model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Ricetta, Integer> {
}
