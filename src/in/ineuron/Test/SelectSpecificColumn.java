package in.ineuron.Test;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.build.AllowSysOut;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;



public class SelectSpecificColumn {

	public static void main(String[] args) {
		
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			
			Criteria criteria = session.createCriteria(Product.class); //HQL => from in.ineuron.model.Product
			
			//Selecting only 2 columns::
			ProjectionList list = Projections.projectionList();
			list.add(Projections.property("pname"));
			list.add(Projections.property("price"));
			
			criteria.setProjection(list);
			
			//adding specific criteria::
			Criterion cond1 = Restrictions.ge("price", 7000);
			Criterion cond2 = Restrictions.le("price", 10000);
			
			//adding the condition to the criteria object::
			criteria.add(cond1);
			criteria.add(cond2);
			
			//Arranging the pname ascending order::
			Order asc = Order.asc("pname");
			criteria.addOrder(asc);
			
			List<Object[]> products = criteria.list();
			
			products.forEach(row ->{
				for (Object objects : row) {
					System.out.print(objects + "\t\t");
				}
				System.out.println();
			});
			
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