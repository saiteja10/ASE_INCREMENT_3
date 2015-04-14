package com.ase.api.service.rest.implementation;

import com.ase.api.aop.MyTransaction;
import com.ase.api.service.LoginService;
import com.ase.bean.BaseBean;
import com.ase.bean.RegisterBean;
import com.ase.bean.UserBean;
import com.ase.handler.LoginHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Gurrala on 2/21/2015.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Inject
    LoginHandler loginHandler;

    @MyTransaction
    @Override
    public UserBean login(String username, String password, Boolean isEmployee) throws Exception {
        return loginHandler.login(username, password, isEmployee);
    }

    @MyTransaction
    @Override
    public BaseBean register(RegisterBean registerBean) {
        return loginHandler.register(registerBean);
    }
}
