package ua.controller;




import java.io.IOException;
import java.util.List;











import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;











import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;















import org.springframework.web.bind.annotation.RestController;












import ua.dto.CategoryJson;
import ua.dto.DigitPropertyJson;
import ua.dto.DigitValueJson;
import ua.dto.ItemJson;
import ua.dto.ItemsJson;
import ua.dto.StringPropertyJson;
import ua.dto.StringValueJson;
import ua.form.filter.ItemAdminFormFilter;
import ua.service.CategoryService;
import ua.service.DigitPropertyService;
import ua.service.DigitValueService;
import ua.service.FileWriter;
import ua.service.ItemService;
import ua.service.StringPropertyService;
import ua.service.StringValueService;


@RestController
@MultipartConfig
public class ItemController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ItemService itemService;
	@Autowired
	private DigitPropertyService digitPropertyService;
	
	@Autowired
	private CategoryService categoryService; 
	
	@Autowired
	private StringPropertyService stringPropertyService;
	
	@Autowired
	private StringValueService stringValueService;
	
	@Autowired
	private DigitValueService digitValueService;
	
	
	@Autowired
	private FileWriter fileWriter;
	
	@ModelAttribute("form")
	public ItemAdminFormFilter getItemAdminFormFilter(){
		return new ItemAdminFormFilter();
	}
	
	@RequestMapping(value="/item/category", method=RequestMethod.GET)
	public List<CategoryJson> getCategory(){
		return categoryService.findAllCategoryJson();
	}
	

	@RequestMapping(value="/item/stringProperty/{id}", method=RequestMethod.GET)
	public List<StringPropertyJson> getStringProperty(@PathVariable int id){
		return stringPropertyService.findByCategoryId(id);
	}
	
	@RequestMapping(value="/item/digitProperty/{id}", method=RequestMethod.GET)
	public List<DigitPropertyJson> getDigitProperty(@PathVariable int id){
		return digitPropertyService.findByCategoryId(id);
	}
	


	@RequestMapping(value="/item/all/{page}/{size}/{sort}", method=RequestMethod.GET)
	public ItemsJson getAllItems(
			@PathVariable("page")int page,
			@PathVariable("size") int size,
			@PathVariable("sort") String sort,
			@PageableDefault(10)
			@ModelAttribute(value="form") ItemAdminFormFilter form
			){
		Direction sortPage = sort.endsWith("asc") ? Direction.ASC : Direction.DESC;
		Pageable pageable = new PageRequest(page,size, sortPage, "model");
		return itemService.findAll(pageable, form);
	}
	
	@RequestMapping(value="/item/{id}", method=RequestMethod.GET)
	public ItemJson getItem(@PathVariable int id){
		return itemService.findByIdJson(id);
	}
	

	@RequestMapping(value="/item", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpStatus update(@RequestBody  String json){
		itemService.save(json);
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/item", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpStatus save(@RequestBody  String json){
		itemService.save(json);
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/item/{id}", method=RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable int id){
		itemService.delete(id);
		return HttpStatus.OK;
	}
	
	@RequestMapping(value="/item/stringValue/{itemId}/{stringPropertyId}", method=RequestMethod.GET)
	public StringValueJson getStringValue(@PathVariable int itemId, @PathVariable int stringPropertyId){
		return stringValueService.findByStringPropertyIdAndItemId(stringPropertyId, itemId);
	}
	
	@RequestMapping(value="/item/digitValue/{itemId}/{digitPropertyId}", method=RequestMethod.GET)
	public DigitValueJson getDigitValue(@PathVariable int itemId, @PathVariable int digitPropertyId){
		return digitValueService.findByDigitPropertyIdAndItemId(digitPropertyId, itemId);
	}


	@RequestMapping(value="/item/image", method=RequestMethod.POST)
	public HttpStatus uploadImgage(HttpServletRequest request) throws IOException, ServletException, FileUploadException {
	  fileWriter.write(request);
		return HttpStatus.OK;
	}
}