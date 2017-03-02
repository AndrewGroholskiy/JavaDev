package ua.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.DigitValue;



public interface DigitValueRepository extends JpaRepository<DigitValue, Integer>, JpaSpecificationExecutor<DigitValue> {

	
	DigitValue findByValue(BigDecimal value);
	
	@Query("SELECT s FROM DigitValue s LEFT JOIN FETCH s.digitProperty LEFT JOIN FETCH s.items WHERE s.id=:id")
	DigitValue findWithValue(@Param("id") int id);
	
	@Query("SELECT d FROM DigitValue d LEFT JOIN d.digitProperty props LEFT JOIN  d.items dd WHERE dd.id=:itemId and props.id=:digitPropertyId")
	DigitValue findByDigitPropertyIdAndItemId(@Param("itemId") int itemId,@Param("digitPropertyId") int digitPropertyId );

	@Query("SELECT s FROM DigitValue s LEFT JOIN FETCH s.items ss LEFT JOIN FETCH s.digitProperty WHERE ss.id=:id")
	List<DigitValue> findValueByItemId(@Param("id") int id);
}
