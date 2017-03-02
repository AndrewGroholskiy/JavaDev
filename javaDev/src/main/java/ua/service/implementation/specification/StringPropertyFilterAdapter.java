package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.StringProperty;
import ua.form.filter.StringPropertyFormFilter;

public class StringPropertyFilterAdapter implements Specification<StringProperty>{

	private final StringPropertyFormFilter form;
	
	private final List<Specification<StringProperty>> filters = new ArrayList<>();
	
	
	public StringPropertyFilterAdapter(StringPropertyFormFilter form){
		if (form != null) {
			this.form = form;
		} else {
			this.form = new StringPropertyFormFilter();
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
				return  cb.like(cb.upper(root.get("name")), form.getSerch()+"%");
			});
		}
	}
	
	@Override
	public Predicate toPredicate(Root<StringProperty> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByCategory();
		findBySerch();
		if(!filters.isEmpty()){
			Specifications<StringProperty> spec = Specifications.where(filters.get(0));
			for(Specification<StringProperty> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
		
	}

}
