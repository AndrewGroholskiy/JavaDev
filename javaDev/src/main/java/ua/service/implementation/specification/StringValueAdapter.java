package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.StringValue;
import ua.form.filter.StringValueFormFilter;

public class StringValueAdapter implements Specification<StringValue>{

	private String serch= "";
	
	
	public StringValueAdapter(StringValueFormFilter form) {
		serch = form.getSerch();
	}




	@Override
	public Predicate toPredicate(Root<StringValue> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), serch.toUpperCase()+"%");
	}

}
