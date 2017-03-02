package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Filters;
import ua.form.filter.FilterFormFilter;

public class FilterFilterAdapter implements Specification<Filters>{

	private final FilterFormFilter form;
	
	private final List<Specification<Filters>> filters = new ArrayList<>();
	
	
	public FilterFilterAdapter(FilterFormFilter form){
		if (form != null) {
			this.form = form;
		} else {
			this.form = new FilterFormFilter();
		}
	}
	
	private void findByCategory(){
		if(!form.getCategorys().isEmpty()){
			filters.add((root, query, cb)->root.get("category").in(form.getCategorys()));
		}
	}
	
	private void findBySerch(){
		if(!form.getSerch().isEmpty()){
			filters.add((root, query, cb)->{
				return  cb.like(cb.upper(root.get("name")), form.getSerch().toUpperCase()+"%");
			});
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Filters> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		findByCategory();
		findBySerch();
		if(!filters.isEmpty()){
			Specifications<Filters> spec = Specifications.where(filters.get(0));
			for(Specification<Filters> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
