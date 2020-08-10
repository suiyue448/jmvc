package seckill.com.seckilljmvc.jingzhi;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lijingzhi
 * @create 2019/5/23
 */
public class ParamFload implements ParamStrategy<Float> {

    @Override
    public Float obtainRealParam(String paramName, HttpServletRequest request) {
        String objString=request.getParameter(paramName);
        return objString==null? null :Float.valueOf(objString);
    }
}
