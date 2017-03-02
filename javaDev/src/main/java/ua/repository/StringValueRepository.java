package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.StringProperty;
import ua.entity.StringValue;

public interface StringValueRepository extends JpaRepository<StringValue, Integer> , JpaSpecificationExecutor<StringValue>{

	StringValue findByName(String name);

	StringValue findById(int id);
	
	@Query("SELECT s FROM StringValue s LEFT JOIN FETCH s.stringProperty LEFT JOIN FETCH s.items ss WHERE s.id=:id")
	StringValue findWithProperty(@Param("id") int id);
	
	StringValue findByStringProperty(StringProperty stringProperty);
	
	@Query("SELECT s FROM StringValue s LEFT JOIN s.stringProperty props LEFT JOIN  s.items ss WHERE ss.id=:itemId and props.id=:stringPropertyId")
	StringValue findByStringPropertyIdAndItemId(@Param("itemId") int itemId,@Param("stringPropertyId") int stringPropertyId );

	@Query("SELECT s FROM StringValue s LEFT JOIN FETCH s.items ss LEFT JOIN FETCH s.stringProperty WHERE ss.id=:id")
	List<StringValue> findValueByItemId(@Param("id") int id);
}
