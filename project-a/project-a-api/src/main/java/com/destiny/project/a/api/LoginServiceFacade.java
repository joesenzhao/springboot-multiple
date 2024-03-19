package com.destiny.project.a.api;

import com.destiny.project.a.api.param.LoginParam;
import com.destiny.project.a.api.result.LoginResult;
import com.destiny.project.framework.base.api.Result;

public interface LoginServiceFacade {
    Result<LoginResult> userLogin(LoginParam param);
}
