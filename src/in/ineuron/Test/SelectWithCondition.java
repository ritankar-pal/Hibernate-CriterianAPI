package in.ineuron.Test;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;



public class SelectWithCondition {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
			Criteria criteria = session.createCriteria(Product.class); //HQL => from in.ineuron.model.Product
			
			//adding specific criteria::
			Criterion cond1 = Restrictions.ge("price", 7000);
			Criterion cond2 = Restrictions.le("price", 10000);
			
			//adding the condition to the criteria object::
			criteria.add(cond1);
			criteria.add(cond2);
			
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