package org.lessons.springblogricette.Repository;

import org.lessons.springblogricette.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categoria, Integer> {
}
