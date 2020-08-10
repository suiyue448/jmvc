package seckill.com.seckilljmvc.jingzhi;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class GetParamters {

	public static <T>  T obtainParam(Class<T> obj,HttpServletRequest request){
		FieldAccess fieldAccess = FieldAccess.get(obj);
		ConstructorAccess<T> css= ConstructorAccess.get(obj);
		T realObj=css.newInstance();
		String[] fieldArr=fieldAccess.getFieldNames();
		Class[] classArr=fieldAccess.getFieldTypes();
		for (int i = 0; i <fieldArr.length ; i++) {
			String fname=fieldArr[i];
			int index=fieldAccess.getIndex(fname);
			String valStr = request.getParameter(fname);
			if(valStr==null||"".equals(valStr)) continue;
			Class ctype = classArr[i];
			String classType=ctype.getSimpleName();
			System.out.println(fname+"=="+ctype.getSimpleName()+"=="+ctype.getTypeName());
			switch (classType){
				case "int" : {
					int valInt=Integer.valueOf(valStr);
					fieldAccess.setInt(realObj,index,valInt);
					break;
				}
				case "Integer" : {
					Integer valInteger=Integer.valueOf(valStr);
					fieldAccess.set(realObj,index,valInteger);
					break;
				}
				case "String" : {
					String valString=String.valueOf(valStr);
					fieldAccess.set(realObj,index,valString);
					break;
				}
				case "long" : {
					long vallong=Long.valueOf(valStr);
					fieldAccess.setLong(realObj,index,vallong);
				}
				case "Long" : {
					Long valLong=Long.valueOf(valStr);
					fieldAccess.setLong(realObj,index,valLong);
					break;
				}
				case "short" : {
					short valshort=Short.valueOf(valStr);
					fieldAccess.setShort(realObj,index,valshort);
					break;
				}
				case "Short" : {
					Short valShort=Short.valueOf(valStr);
					fieldAccess.set(realObj,index,valShort);
					break;
				}
				case "fload" : {
					float valfload=Float.valueOf(valStr);
					fieldAccess.setFloat(realObj,index,valfload);
					break;
				}
				case "Fload" : {
					Float valFload=Float.valueOf(valStr);
					fieldAccess.setFloat(realObj,index,valFload);
					break;
				}
				case "double" : {
					double valdouble=Double.valueOf(valStr);
					fieldAccess.setDouble(realObj,index,valdouble);
					break;
				}
				case "Double" : {
					Double valDouble=Double.valueOf(valStr);
					fieldAccess.set(realObj,index,valDouble);
					break;
				}
				default:
					throw new JingException("Parameter resolution is not supported:  "+classType+"  "+fname);
			}
		}
		return realObj;

	}
	public static String obtainString(String paramName,HttpServletRequest request){
		return new ParamString().obtainRealParam(paramName, request);
	}
	public static String obtainString(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			Object valueObj = entry.getValue();
			if(valueObj!=null){ //&&!(valueObj instanceof String[])
				String[] arr = (String[]) valueObj;
				String realStr = arr[0];
				boolean lastYN = false;
				boolean numYN=realStr.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
				if(numYN){
					boolean numYN2=realStr.startsWith("0");
					int index = realStr.indexOf(".");
					if(numYN2&&index!=-1) lastYN=true;
				}else lastYN=true;
				if(lastYN){
					return realStr;
				}
			}
		}
		return null;
	}
	public static Integer obtainInteger(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			String[] valueObj = (String[]) entry.getValue();
			if(valueObj==null) continue;
			//if(valueObj instanceof String[])continue;
			String realStr = valueObj[0];
			boolean numYN=realStr.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
			//boolean numYN2=realStr.startsWith("0");
			if(numYN){
				int index=realStr.indexOf(".");
				if(index==-1){
					return Integer.valueOf(realStr);
				}
			}
		}
		return null;
	}
	public static Float obtainFload(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			Object valueObj = entry.getValue();
			if(valueObj!=null&&!(valueObj instanceof String[])){
				String realStr=valueObj.toString();
				boolean numYN=realStr.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
				boolean numYN2=realStr.startsWith("0");
				if(numYN&&!numYN2){
					int index=realStr.indexOf(".");
					if(index!=-1){
						return Float.valueOf(realStr);
					}
				}
			}
		}
		return null;
	}
	public static Double obtainDouble(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			Object valueObj = entry.getValue();
			if(valueObj!=null&&!(valueObj instanceof String[])){
				String realStr=valueObj.toString();
				boolean numYN=realStr.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
				boolean numYN2=realStr.startsWith("0");
				if(numYN&&!numYN2){
					int index=realStr.indexOf(".");
					if(index!=-1){
						return Double.valueOf(realStr);
					}
				}
			}
		}
		return null;
	}
	public static Long obtainLong(HttpServletRequest request){
		Map properties = request.getParameterMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			Object valueObj = entry.getValue();
			if(valueObj!=null&&!(valueObj instanceof String[])){
				String realStr=valueObj.toString();
				boolean numYN=realStr.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
				boolean numYN2=realStr.startsWith("0");
				if(numYN&&!numYN2){
					int index=realStr.indexOf(".");
					if(index==-1){
						return Long.valueOf(realStr);
					}
				}
			}
		}
		return null;
	}
	public static Integer obtainInteger(String paramName,HttpServletRequest request){
		return new ParamInteger().obtainRealParam(paramName, request);
	}

	public static Long obtainLong(String paramName,HttpServletRequest request){
		return new ParamLong().obtainRealParam(paramName, request);
	}
	public static Float obtainFload(String paramName,HttpServletRequest request){
		return new ParamFload().obtainRealParam(paramName, request);
	}
	public static Double obtainDouble(String paramName,HttpServletRequest request){
		return new ParamDouble().obtainRealParam(paramName, request);
	}

}
