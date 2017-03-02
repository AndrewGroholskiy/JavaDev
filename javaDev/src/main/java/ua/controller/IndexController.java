package ua.controller;




import java.util.List;


import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;






import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;








import ua.dto.CategoryJson;
import ua.dto.ItemJson;
import ua.dto.ItemJsonMain;
import ua.entity.Item;
import ua.form.filter.IndexSearchForm;
import ua.form.filter.ItemFormFilter;
import ua.service.CategoryService;
import ua.service.FilterService;
import ua.service.ItemService;
import ua.service.StringValueService;



@Controller
public class IndexController extends HttpServlet{


	private static final long serialVersionUID = -5625812573840400468L;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StringValueService stringValueService; 
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private	FilterService filterService;
	
	@ModelAttribute("filter")
	public ItemFormFilter getFilter(){
		return new ItemFormFilter();
	}
	
	@ModelAttribute("search")
	public IndexSearchForm getSearchForm(){
		return new IndexSearchForm();
	}
	
	
	@RequestMapping("/")
	public String showIndex(
			Model model, 
			@ModelAttribute("search") IndexSearchForm form,
			@PageableDefault(5) Pageable pageable
			){
		Page<Item> items = itemService.FindAll(pageable, form); 
		
		if(form.getSerch().length() == 0){
			return "index";			
		}else if(items.hasContent()){
			model.addAttribute("page", items);			
		}else{
			model.addAttribute("pageEmpty", "за вашим запитлм '" + form.getSerch() +"' нічого не знайдено.");		
		}
		
		return "index";
	}
	
	
	@RequestMapping("/admin")
	public String showAdmin(){
		return "adminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/admin/item")
	public String showItem() {
		return "item";
	}

	
	@ResponseBody
	@RequestMapping(value="/index/categoryMain", method=RequestMethod.GET)
	public List<CategoryJson> getCategory(){
		return categoryService.findMainCategoryJson();
	}
	

	@ResponseBody
	@RequestMapping(value="/index/{id}", method=RequestMethod.GET)
	public List<CategoryJson> getSubCategory(@PathVariable int id){
		return categoryService.findSubCategoryJson(id);
	}
	
	@RequestMapping(value="categorys/{id}")
	public String getCategorys(Model model, 
							@PathVariable int id,
							@ModelAttribute("filter") ItemFormFilter form,
							@PageableDefault(5) Pageable pageable
							){
		form.setCategory(id);
		model.addAttribute("categoryName", categoryService.findById(id).getName());
		model.addAttribute("brend", itemService.findByCategoryId(id));	
		model.addAttribute("categoryId", id);
		model.addAttribute("page", itemService.FindAll(pageable, form));
		return  "categorys";
	}
	
	
	@ResponseBody
	@RequestMapping(value="index/items/{id}", method=RequestMethod.GET)
	public List<ItemJsonMain> getItems(@PathVariable int id){
		return itemService.findAllByCategoryId(id);
	}
	
	@ResponseBody
	@RequestMapping(value="product/*/{id}", method=RequestMethod.GET)
	public ItemJson getItem(@PathVariable int id){
		return itemService.findByIdJson(id);
	}
}
