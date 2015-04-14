package com.ase.handler;

import com.ase.bean.BaseBean;
import com.ase.bean.RegisterBean;
import com.ase.bean.UserBean;
import com.ase.dao.EmployeeDAO;
import com.ase.dao.UserDAO;
import com.ase.domain.Employee;
import com.ase.domain.User;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Gurrala on 2/21/2015.
 */
@Component
public class LoginHandler {
    @Inject
    UserDAO userDAO;
    @Inject
    EmployeeDAO employeeDAO;

    public UserBean login(String username, String password, Boolean isEmployee) throws Exception {
        String pwd = encodePassword(password, username);
        if (isEmployee != null && isEmployee) {
            Employee employee = employeeDAO.verifyEmployee(username, pwd);
            if (employee != null) {
                UserBean userBean = new UserBean();
                userBean.setId(employee.getId());
                if(employee.getIsAdmin())
                    userBean.setType("admin");
                else
                userBean.setType("employee");
                userBean.setFirstName(employee.getFirstName());
                userBean.setLastName(employee.getLastName());
                userBean.setEmail(employee.getEmail());
                return userBean;
            } else
                throw new Exception("Unauthorized Employee");
        } else {
            User user = userDAO.verifyUser(username, pwd);
            if (user != null) {
                UserBean userBean = new UserBean();
                userBean.setId(user.getId());
                userBean.setFirstName(user.getFirstName());
                userBean.setLastName(user.getLastName());
                userBean.setEmail(user.getEmail());
                userBean.setType("user");
                return userBean;
            } else
                throw new Exception("Unauthorized User");
        }
    }

    public BaseBean register(RegisterBean registerBean) {
        User user = new User();
        user.setEmail(registerBean.getEmail());
        user.setFirstName(registerBean.getFirstName());
        user.setLastName(registerBean.getLastName());
        user.setPassword(encodePassword(registerBean.getPassword(), registerBean.getEmail()));
        BaseBean baseBean = new BaseBean();
        baseBean.setId(userDAO.save(user));
        return baseBean;
    }

    private String encodePassword(String password, String email) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        password = encoder.encodePassword(password, email);
        return password;
    }
}
