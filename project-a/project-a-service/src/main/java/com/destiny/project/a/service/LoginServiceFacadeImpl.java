package com.destiny.project.a.service;


import com.destiny.project.a.api.LoginServiceFacade;
import com.destiny.project.a.api.param.LoginParam;
import com.destiny.project.a.api.result.LoginResult;
import com.destiny.project.a.dao.mapper.UserMapper;
import com.destiny.project.a.dao.model.User;
import com.destiny.project.a.dao.model.UserExample;
import com.destiny.project.framework.base.api.Result;
import com.destiny.project.framework.base.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "loginFacade")
public class LoginServiceFacadeImpl implements LoginServiceFacade {
    @Resource
    private UserMapper userMapper;
    @Override
    public Result<LoginResult> userLogin(LoginParam param) {
        LoginResult loginResult = new LoginResult();
        loginResult.setToken("123456");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(StringUtil.isNotBlank(param.getUserName())){
            criteria.andUserIdEqualTo(param.getUserName());
        }
        if(StringUtil.isNotBlank(param.getPassword())){
            criteria.andUserPwdEqualTo(param.getPassword());
        }
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList != null || userList.size() > 0){
            User user = userList.get(0);
            LoginResult.UserInfo userInfo = new LoginResult.UserInfo();
            userInfo.setUsername(user.getUserName());
            loginResult.setUserInfo(userInfo);
            return Result.buildSuccess(loginResult);
        }

        return Result.buildFail("用户不存在");
    }
}
