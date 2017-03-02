package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.DigitValue;
import ua.form.filter.DigitValueFormFilter;


public class DigitValueAdapter implements Specification<DigitValue> {

	private final DigitValueFormFilter form;
	
	private final List<Specification<DigitValue>> filters = new ArrayList<>();	

	public DigitValueAdapter(DigitValueFormFilter form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new DigitValueFormFilter();
		}
	}
	
	private void findByAmount(){
		if(form.getMinInt()!=0&&form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("value");
				return cb.between(exp, form.getMinInt(), form.getMaxInt());
			});
		}else if(form.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("value");
				return cb.ge(exp, form.getMinInt());
			});
		}else if(form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("value");
				return cb.le(exp, form.getMaxInt());
			});
		}
	}
	
	
	@Override
	public Predicate toPredicate(Root<DigitValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		findByAmount();
		if(!filters.isEmpty()){
			Specifications<DigitValue> spec = Specifications.where(filters.get(0));
			for(Specification<DigitValue> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
}
