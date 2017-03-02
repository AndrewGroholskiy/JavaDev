package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(indexes={@Index(columnList="name")})
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int level;
	
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Category> childs;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category parent;
	
	
	@OneToMany(mappedBy="category")
	private List<StringProperty> stringProperties;
	
	@OneToMany(mappedBy="category")
	private List<DigitProperty> digitProperties;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Category> getChilds() {
		return childs;
	}
	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		if(parent!=null){
			setLevel(parent.level+1);
			
		}else{
			setLevel(0);
		}
		this.parent = parent;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<StringProperty> getStringProperties() {
		return stringProperties;
	}
	public void setStringProperties(List<StringProperty> stringProperties) {
		this.stringProperties = stringProperties;
	}
	public List<DigitProperty> getDigitProperties() {
		return digitProperties;
	}
	public void setDigitProperties(List<DigitProperty> digitProperties) {
		this.digitProperties = digitProperties;
	}
	 
}
