package com.ase.dao;

import com.ase.domain.Employee;
import com.ase.domain.User;
import com.ase.query.EmployeeQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Created by Gurrala on 2/24/2015.
 */
@Component
public class EmployeeDAO extends BaseDAO<Employee,EmployeeQuery> {
    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    public Employee verifyEmployee(String email, String password){
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        criteria.setMaxResults(1);
        return (Employee) criteria.uniqueResult();
    }
}
