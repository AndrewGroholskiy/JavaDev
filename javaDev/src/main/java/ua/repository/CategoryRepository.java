package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
	
    List<Category> findByParentIsNull();
	
	List<Category> findByParentId(int id);
	
	 List<Category> findByChildsIsNull();
	
	 @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.id=:id")
	Category findWithParent(@Param("id")int id);
	 
	 @Query("SELECT c FROM Category c LEFT JOIN FETCH c.childs WHERE c.id=:id")
	 Category findWithChilds(@Param("id")int id);
	 
	 List<Category> findByParent(Category parent);
	 
	 @Query("SELECT c FROM Category c WHERE c.name=:name")
	 Category findByName(@Param("name")String name);
	 
	 @Query("SELECT c FROM Category c LEFT JOIN FETCH c.stringProperties WHERE c.id=:id")
	 Category findWithStringProperty(@Param("id")int id);
	
	 @Query("SELECT c FROM Category c LEFT JOIN FETCH c.digitProperties WHERE c.id=:id")
	 Category findWithDigitProperty(@Param("id")int id);
	 
//	 @Query("SELECT c FROM Category c LEFT JOIN FETCH c.childs ch WHERE ch.parent.id=:id")
//	 Category findChildsByParentId(@Param("id")int id);
}
