package com.ase.dao;

import com.ase.domain.User;
import com.ase.query.UserQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Created by Gurrala on 2/23/2015.
 */
@Component
public class UserDAO extends BaseDAO<User, UserQuery> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public User verifyUser(String email, String password){
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("password", password));
        criteria.setMaxResults(1);
        return (User) criteria.uniqueResult();
    }
}
