package typeconverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class TimestampTypeConverter extends DefaultTypeConverter {
	
//	@Override  
//    public Object convertValue(Map<String, Object> context, Object value, Class toType) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        try {   
//            if(toType == Timestamp.class){//当字符串向Date类型转换�?  
//                String params = (String) value;// request.getParameterValues()
//                
//                Date date = dateFormat.parse(params);
//                return new Timestamp(date.getTime());
//            }else if(toType == String.class){//当Date转换成字符串�?  
//                //Date date = (Date) value;  
//                return dateFormat.format(value);  
//            }
//
//        } catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//        return null;
//    }

	@Override
	public Object convertValue(Object value, Class toType) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        try {   
            if(toType == Timestamp.class){//当字符串向Date类型转换�?  
                String[] params = (String[]) value;// request.getParameterValues()
                
                if (!"".equals(params[0])) { 
                	Date date = dateFormat.parse(params[0].replaceAll("-", "/"));
                	return new Timestamp(date.getTime());
                }
            }else if(toType == String.class){//当Date转换成字符串�?  
                //Date date = (Date) value;  
                return dateFormat.format(value);  
            }

        } catch (java.text.ParseException e) {
        	 try {   
                 if(toType == Timestamp.class){//当字符串向Date类型转换�?  
                     String[] params = (String[]) value;// request.getParameterValues()
                     
                     if (!"".equals(params[0])) { 
                     	Date date = dateFormat1.parse(params[0].replaceAll("-", "/"));
                     	return new Timestamp(date.getTime());
                     }
                 }else if(toType == String.class){//当Date转换成字符串�?  
                     //Date date = (Date) value;  
                     return dateFormat1.format(value);  
                 }

             } catch (java.text.ParseException e1) {
             	
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}  
			e.printStackTrace();
		}  
		return super.convertValue(value, toType);
	}  


}
