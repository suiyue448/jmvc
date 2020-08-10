package seckill.com.seckilljmvc.jingzhi;

import javax.servlet.http.HttpServletRequest;

public interface ParamStrategy<T> {
    public  T obtainRealParam(String paramName, HttpServletRequest request);
}


