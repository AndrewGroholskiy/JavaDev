package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>,JpaSpecificationExecutor<Item>{

	@Query("SELECT v FROM Item v LEFT jOIN FETCH v.digitValues WHERE v.id=:id")
	Item findOneWithValues(@Param("id")int id);

	
	@Query("SELECT v FROM Item v LEFT jOIN FETCH v.stringValues s LEFT jOIN s.stringProperty str WHERE v.id=:id and str.name=:srtingProperty")
	Item findByStringProperty(@Param("id")int id, @Param("srtingProperty")String srtingProperty);
	
	@Query("SELECT v FROM Item v LEFT JOIN FETCH v.digitValues d LEFT JOIN d.digitProperty digit WHERE v.id=:id and digit.name=:digitProperty")
	Item findByDigitProperty(@Param("id")int id, @Param("digitProperty")String digitProperty);
	
	
	@Query("SELECT c FROM Item c WHERE c.categoryId=:id")
	List<Item> findByCategoryId(@Param("id") int id);
	
}