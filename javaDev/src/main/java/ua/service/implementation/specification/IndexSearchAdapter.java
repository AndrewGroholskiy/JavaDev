package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Item;
import ua.form.filter.IndexSearchForm;

public class IndexSearchAdapter implements Specification<Item>{

	private String serch = "";
	
	public IndexSearchAdapter(IndexSearchForm form) {
		serch = form.getSerch();
	}


	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		Expression<String> exp = root.get("model");
		Expression<String> exp1 = root.get("brend");
		
		List<Predicate> predicate = new ArrayList<Predicate>();
		String str[]= serch.split(" "); 
		
		for (String string : str) {
			
			Predicate like =  cb.like(cb.upper(exp), string.toUpperCase()+"%");
			Predicate like1 =  cb.like(cb.upper(exp1), string.toUpperCase()+"%");
			predicate.add(cb.or(like,like1));
		}
		
		
		
		return cb.or(predicate.toArray(new Predicate[predicate.size()]));
	}

}
