package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.DigitProperty;
import ua.form.filter.DigitPropertyFormFilter;

public class DigitPropertyAdapter implements Specification< DigitProperty>{

private final DigitPropertyFormFilter form;
	
	private final List<Specification<DigitProperty>> filters = new ArrayList<>();
	
	
	public DigitPropertyAdapter(DigitPropertyFormFilter form){
		if (form != null) {
			this.form = form;
		} else {
			this.form = new DigitPropertyFormFilter();
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
	public Predicate toPredicate(Root<DigitProperty> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByCategory();
		findBySerch();
		if(!filters.isEmpty()){
			Specifications<DigitProperty> spec = Specifications.where(filters.get(0));
			for(Specification<DigitProperty> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
		
	}

}
