package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Item;
import ua.form.filter.ItemAdminFormFilter;

public class ItemAdminFilterAdapter implements Specification<Item> {

	private final ItemAdminFormFilter form;
	
	
	
	public ItemAdminFilterAdapter(ItemAdminFormFilter form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new ItemAdminFormFilter();
		}
	}



	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		return null;
	}

}
