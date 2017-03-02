package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Category;
import ua.form.filter.CategoryFormFilter;

public class CategoryAdapter implements Specification<Category>{

	private String serch = "";
	
	public CategoryAdapter(CategoryFormFilter form){
		serch = form.getSerch();
	}




	@Override
	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			
		}
		
		Expression<String> exp = root.get("name");
		Predicate like =  cb.like(cb.upper(exp), serch.toUpperCase()+"%");

		Expression<Category> exp2 = root.get("parent");
		Predicate sss = cb.isNull(exp2);
		
		
		return cb.and(like, sss);
	}

}
