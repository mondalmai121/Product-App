package org.jsp.HibernateTemplate.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.jsp.HibernateTemplate.dto.Product;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class ProductDao {
	
	private HibernateTemplate template;
	
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public Product saveProduct(Product p) { 
		template.save(p);
		return p;
	}
	
	@Transactional
	public Product updateProduct(Product p) {
		Product pr=template.get(Product.class, p.getId());
		if(pr!=null) {
			pr.setName(p.getName());
			pr.setBrand(p.getBrand());
			pr.setCategory(p.getCategory());
			pr.setDescrp(p.getCategory());
			pr.setCost(p.getCost());
			template.update(pr);
			return pr;
		}else {
			return null;
		}
	}
	
	public Product findById(int id) {
		return template.get(Product.class, id);
	}
	
	public List<Product> findAll(){
		return template.loadAll(Product.class);
	}
	
	public Product verify(String brand) {
		return null;
	}
	
	@Transactional
	public boolean deleteProduct(int id) {
		Product p=findById(id);
		if(p!=null) {
			template.delete(p);
			return true;
		}
		return false;
	}
}
