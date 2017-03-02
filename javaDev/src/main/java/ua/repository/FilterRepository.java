package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Category;
import ua.entity.Filters;

public interface FilterRepository extends JpaRepository<Filters, Integer>, JpaSpecificationExecutor<Filters> {
		
	Filters findByNameAndCategoryId(String name, int id);

	@Query("SELECT v FROM Filters v LEFT JOIN FETCH v.filtersValue WHERE v.name=:name and v.category=:category")
	Filters findOneWhithValues(@Param("name") String name, @Param("category") Category category); 

	@Query("SELECT v FROM Filters v WHERE v.category=:category")
	List<Filters> findByCategoryId(@Param("category") Category category);
}
