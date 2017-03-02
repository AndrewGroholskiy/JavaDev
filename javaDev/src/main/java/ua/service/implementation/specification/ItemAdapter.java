package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;












import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;













import ua.entity.Item;
import ua.form.filter.ItemFormFilter;
import ua.service.CategoryService;
import ua.service.StringPropertyService;

public class ItemAdapter implements Specification<Item>{

	
	@Autowired
	private StringPropertyService stringPropertyService;
	
	@Autowired
	private CategoryService categoryService; 
	
	private final ItemFormFilter form;
	
	private  List<Specification<Item>> filters = new ArrayList<>();	

	public ItemAdapter(ItemFormFilter form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new ItemFormFilter();
		}
	}
	
		
	private void findByBrend(){
		if(form.getBrend() !=  null && !form.getBrend().isEmpty()){
			filters.add((root, query, cb)->{
				Expression<String> exp = root.get("brend");
				
				List<Predicate> restrictions = new ArrayList<Predicate>();
	
				Set<String> brendSet = form.getBrend();
					for (String brend :brendSet) {
						restrictions.add(cb.equal(exp, brend)); 
					}
			
				return cb.or(restrictions.toArray(new Predicate[restrictions.size()]));
			});
		}
	}
	
					
	private void findByCategory(){
		if(form.getCategory()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("categoryId");
				return cb.equal(exp, form.getCategory());
			});
		}
	}
	
	private void findByAmount(){
		if(form.getMinInt()!=0&&form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.between(exp, form.getMinInt(), form.getMaxInt());
			});
		}else if(form.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.ge(exp, form.getMinInt());
			});
		}else if(form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("price");
				return cb.le(exp, form.getMaxInt());
			});
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		findByCategory();
		findByAmount();
		findByBrend();
		if(!filters.isEmpty()){
			Specifications<Item> spec = Specifications.where(filters.get(0));
			for(Specification<Item> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
