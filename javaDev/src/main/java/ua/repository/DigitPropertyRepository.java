package ua.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.DigitProperty;


public interface DigitPropertyRepository extends JpaRepository<DigitProperty, Integer>, JpaSpecificationExecutor<DigitProperty>{

	
	DigitProperty findByName(String name);
	
	@Query("SELECT c FROM DigitProperty c LEFT JOIN FETCH c.category WHERE c.id=:id")
	DigitProperty findWithCategory(@Param("id")int id);

	@Query("SELECT v FROM DigitProperty v LEFT JOIN FETCH v.values WHERE v.id=:id")
	DigitProperty findWithValue(@Param("id")int id);
	
	@Query("SELECT v FROM DigitProperty v LEFT JOIN FETCH v.values")
	List<DigitProperty> findAllWithValues();
	
	@Query("SELECT s FROM DigitProperty s LEFT JOIN s.category c WHERE s.name=:name and c.name=:categoryName")
	DigitProperty findByNameAndCategory(@Param("name")String name,@Param("categoryName") String categoryName);

	@Query("SELECT d FROM DigitProperty d LEFT JOIN  d.category c WHERE c.id=?1")
	List<DigitProperty> findByCategory(int id);
}

