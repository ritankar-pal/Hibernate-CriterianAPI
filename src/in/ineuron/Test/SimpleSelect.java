package in.ineuron.Test;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;



public class SimpleSelect {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
			Criteria criteria = session.createCriteria(Product.class); //HQL => from in.ineuron.model.Product
			
			List<Product> products = criteria.list();
			
			products.forEach(System.out::println);
			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}
}