package org.jsp.HibernateTemplate.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.HibernateTemplate.dao.ProductDao;
import org.jsp.HibernateTemplate.dto.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductApp {

	static ProductDao dao;
	static {
		ApplicationContext con = new ClassPathXmlApplicationContext("product-cfg.xml");
		dao = con.getBean(ProductDao.class);
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean f = true;

		while (f) {
			System.out.println("< ===== 1.Save Product ===== >");
			System.out.println("< ===== 2.Update Product ===== >");
			System.out.println("< ===== 3.Find Product by id ===== >");
			System.out.println("< ===== 4.Find All product ===== >");
			System.out.println("< ===== 5.Find product using brand ===== >");
			System.out.println("< ===== 6.Delete Product ===== >");

			switch (sc.nextInt()) {

			case 1: {
				System.out.println("Enter name, brand, category, descrp, cost to save: ");
				Product p = new Product();
				p.setName(sc.next());
				p.setBrand(sc.next());
				p.setCategory(sc.next());
				p.setDescrp(sc.next());
				p.setCost(sc.nextDouble());
				p = dao.saveProduct(p);
				System.out.println("Product saved successfully.......");
				break;
			}

			case 2: {
				System.out.println("Enter id to update: ");
				int id = sc.nextInt();
				System.out.println("Enter name, brand, category, descrp, cost to save: ");
				Product p = new Product();
				p.setId(id);
				p.setName(sc.next());
				p.setBrand(sc.next());
				p.setCategory(sc.next());
				p.setDescrp(sc.next());
				p.setCost(sc.nextDouble());
				p = dao.updateProduct(p);

				if (p != null) {
					System.out.println("Product updated successfully.......");
				} else {
					System.err.println("You have entered invalid id........!!!");
				}
				break;
			}

			case 3: {
				System.out.println("Enter id to update: ");
				int id = sc.nextInt();
				Product p = dao.findById(id);

				if (p != null) {
					System.out.println("Product found successfully........");
					System.out.println("Product id: " + p.getId());
					System.out.println("Product name: " + p.getName());
					System.out.println("Product brand: " + p.getBrand());
					System.out.println("Product category: " + p.getCategory());
					System.out.println("Product descrp: " + p.getDescrp());
					System.out.println("Product cost: " + p.getCost());
					System.out.println("==========================");
				} else {
					System.err.println("You have entered invalid id........!!!");
				}
				break;
			}

			case 4: {
				List<Product> product = dao.findAll();
				for (Product p : product) {
					System.out.println("====================");
					System.out.println("Product id: " + p.getId());
					System.out.println("Product name: " + p.getName());
					System.out.println("Product brand: " + p.getBrand());
					System.out.println("Product category: " + p.getCategory());
					System.out.println("Product descrp: " + p.getDescrp());
					System.out.println("Product cost: " + p.getCost());
					System.out.println("===========================");
				}
				break;
			}
			
			case 5: {
				System.out.println("Enter brand to find: ");
				String brand=sc.next();
				Product p=dao.verify(brand);
				if (p != null) {
					System.out.println("Product found successfully........");
					System.out.println("Product id: " + p.getId());
					System.out.println("Product name: " + p.getName());
					System.out.println("Product brand: " + p.getBrand());
					System.out.println("Product category: " + p.getCategory());
					System.out.println("Product descrp: " + p.getDescrp());
					System.out.println("Product cost: " + p.getCost());
					System.out.println("==========================");
				} else {
					System.err.println("You have entered invalid id........!!!");
				}
				break;
			}

			case 6: {
				System.out.println("Enter id to delete: ");
				int id = sc.nextInt();
				boolean delet = dao.deleteProduct(id);

				if (delet) {
					System.out.println("product deleted successfully.....");
				} else {
					System.err.println("You have entered invalid id........!!!");
				}
				break;
			}

			default:
				System.err.println("< ***** INAVLID CHOICE ***** >");
				System.exit(0);
			}
		}
	}
}
