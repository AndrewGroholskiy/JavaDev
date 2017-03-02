package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Filters;
import ua.entity.FiltersValue;

public interface FiltersValueRepository extends JpaRepository<FiltersValue, Integer>{

	FiltersValue findByValueAndFiltersValue(String value, Filters filtersValue);
}
