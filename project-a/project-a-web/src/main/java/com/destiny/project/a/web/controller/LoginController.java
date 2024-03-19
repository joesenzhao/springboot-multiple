package com.destiny.project.a.web.controller;

import com.destiny.project.a.api.LoginServiceFacade;
import com.destiny.project.a.api.param.LoginParam;
import com.destiny.project.a.api.result.LoginResult;
import com.destiny.project.framework.base.api.Result;
import com.destiny.project.framework.base.utils.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    LoginServiceFacade loginServiceFacade;

    @PostMapping("/userLogin")
    public Result<LoginResult> userLogin(@RequestBody LoginParam param) {
        if (StringUtil.isBlank(param.getUserName())) {
            return Result.buildFail("用户名必传");
        } else if (StringUtil.isBlank(param.getPassword())) {
            return Result.buildFail("用户密码必传");
        }
        return loginServiceFacade.userLogin(param);
    }
}
