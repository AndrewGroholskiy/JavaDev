package ua.service.implementation;



import java.util.ArrayList;
import java.util.List;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.CategoryJson;
import ua.entity.Category;
import ua.form.CategoryForm;
import ua.form.filter.CategoryFormFilter;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;
import ua.service.implementation.specification.CategoryAdapter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public void save(CategoryForm categoryForm) {
			Category cat = new Category();
			cat.setId(categoryForm.getId());	
			cat.setName(categoryForm.getName());
			cat.setParent(categoryForm.getParent());
		categoryRepository.save(cat);
	}

	

	@Override
	@Transactional
	public void changeParent(int catId, int newParentId) {
		Category category = categoryRepository.findOne(catId);
		if(category.getParent()!=null){
			category.getParent().getChilds().removeIf(c->c.getId()==catId);
		}
		category.setParent(categoryRepository.findOne(newParentId));
		changeChildsLevel(category.getChilds(), category.getLevel());
	}
	
	private void changeChildsLevel(List<Category> list, int parentLevel){
		for (Category category : list) {
			category.setLevel(parentLevel+1);
			changeChildsLevel(categoryRepository
					.findByParentId(category.getId()), category.getLevel());
		}
	}

	@Override
	public Category findOne(int id) {
		Category category = categoryRepository.findOne(id);
		category = categoryRepository.findOne(findParent(id, category.getLevel()));
		return category;
	}
	

	private Specification<Category> findParent(int id, int level){
		return (root, query, cb)-> {
			Expression<Integer> exp = root.get("id");
			Fetch<Category, Category> fetch = root.fetch("parent");
			for(int i = 0; i < level-1; i++){
				fetch = getFetch(fetch);
			}
			return cb.equal(exp, id);
		};
	}
	
	private Fetch<Category, Category> getFetch(Fetch<Category, Category> fetch){
		return fetch.fetch("parent");
	}
	@Transactional
	@Override
	public void delete(int id) {
		
			if(categoryRepository.findWithParent(id).getParent() != null ){
				Category category = categoryRepository.findWithChilds(id);
				List<Category> list = category.getParent().getChilds();
				list.removeIf((p)-> p.getId() == id);
				category.setChilds(list);
				categoryRepository.save(category);
				categoryRepository.delete(id);
			}
			else categoryRepository.delete(id);		
			
		
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}
	@Transactional
	@Override
	public List<Category> findWithoutChildren() {
		return categoryRepository.findByChildsIsNull();
	}
	@Transactional
	@Override
	public CategoryForm findForForm(int id) {
		CategoryForm categoryForm = new CategoryForm();
		Category category = categoryRepository.findOne(id);
		categoryForm.setId(category.getId());
		categoryForm.setName(category.getName());
		
		categoryForm.setParent(categoryRepository.findWithParent(id).getParent());
		return categoryForm;
	}

	@Override
	public Category findOneWithParent(int id) {
		return categoryRepository.findWithParent(id);
	}

	@Override
	public List<Category> findChilds(int id) {
		Category category = categoryRepository.findOne(id);
		List<Category> list = categoryRepository.findByParent(category);
		System.out.println("-------------------------------------------------------------------------list");
		System.out.println(list.size());
		return list;
		
		
	}

	@Override
	public Category findById(int id){
		return categoryRepository.findOne(id);
	}

	@Override
	public Category findOneWithChilds(int id) {
		return categoryRepository.findWithChilds(id);
		
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {

		return categoryRepository.findAll(pageable);
	}

	
	@Override
	@Transactional
	public List<Category> findAllMainCategory() {
		List<Category> parents = categoryRepository.findByParentIsNull();
		initChilds(parents);
		return parents;
	}
	
	private void initChilds(List<Category> parents){
		for (Category category : parents) {
			List<Category> childs = categoryRepository.findByParentId(category.getId());
			category.setChilds(childs);
			initChilds(childs);
		}
	}
	@Transactional
	@Override
	public Page<Category> findAll(Pageable pageable, CategoryFormFilter form) {
		Page<Category> page = categoryRepository.findAll(new CategoryAdapter(form), pageable);	
		initChilds(page.getContent());	
		return page;
	}

	@Override
	public Category findByCategoryName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Category findWithStringProperty(int id) {
		return categoryRepository.findWithStringProperty(id);
	}



	@Override
	public List<CategoryJson> findAllCategoryJson() {
		List<CategoryJson> jsons = new ArrayList<CategoryJson>();
		for (Category category : categoryRepository.findByChildsIsNull()) {
			CategoryJson json = new CategoryJson();
			json.setId(category.getId());
			json.setName(category.getName());
			jsons.add(json);
		}
		return jsons;
	}

	@Override
	public List<CategoryJson> findMainCategoryJson() {
		List<CategoryJson> jsons = new ArrayList<CategoryJson>();
		for (Category category : categoryRepository.findByParentIsNull()) {
			CategoryJson json = new CategoryJson();
			json.setId(category.getId());
			json.setName(category.getName());
			json.setLevel(category.getLevel());
			jsons.add(json);
		}
		return jsons;
	}



	@Override
	public List<CategoryJson> findSubCategoryJson(int id) {
		List<CategoryJson> jsons = new ArrayList<CategoryJson>();
		for (Category category : categoryRepository.findWithChilds(id).getChilds()) {
			CategoryJson json = new CategoryJson();
			json.setId(category.getId());
			json.setName(category.getName());
			json.setLevel(category.getLevel());
			jsons.add(json);
		}
		return jsons;
	}
	
	
}