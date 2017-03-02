package ua.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.StringProperty;

public interface StringPropertyRepository extends JpaRepository<StringProperty, Integer>, JpaSpecificationExecutor<StringProperty>{

	StringProperty findByName(String name);

	@Query("SELECT s FROM StringProperty s LEFT JOIN FETCH s.category WHERE s.id=:id")
	StringProperty findWithCategory(@Param("id")int id);

	
	@Query("SELECT v FROM StringProperty v LEFT JOIN FETCH v.values WHERE v.id=:id")
	StringProperty findWithValue(@Param("id")int id);	
	
	@Query("SELECT v FROM StringProperty v LEFT JOIN FETCH v.values")
	List<StringProperty> findAllWhithValues();
	
	@Query("SELECT s FROM StringProperty s LEFT JOIN s.category c WHERE s.name=:name and c.id=:categoryId")
	StringProperty findByNameAndCategory(@Param("name")String name,@Param("categoryId") int categoryId);
	
	@Query("SELECT s FROM StringProperty s LEFT JOIN s.category c WHERE c.id=?1")
	List<StringProperty> findByCategory(int id);
	
	
}
