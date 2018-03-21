package springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint; 

/** 
 * Advice通知�? 
 * 测试after,before,around,throwing,returning Advice. 
 * @author Admin 
 * 
 */  
public class AlarmDefindAdvice {
	  
    /** 
     * 在核心业务执行前执行，不能阻止核心业务的调用�? 
     * @param joinPoint 
     */  
    private void doBefore(JoinPoint joinPoint) {  
        System.out.println("-----doBefore().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑前，做一些安全�?�的判断等等");  
        System.out.println(" 可�?�过joinPoint来获取所�?要的内容");  
        System.out.println("-----End of doBefore()------");  
    }  
      
    /** 
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处�?, 
     *  
     * 注意：当核心业务抛异常后，立即�??出，转向After Advice 
     * 执行完毕After Advice，再转到Throwing Advice 
     * @param pjp 
     * @return 
     * @throws Throwable 
     */  
    private Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        System.out.println("-----doAround().invoke-----");  
        System.out.println(" 此处可以做类似于Before Advice的事�?");  
          
        //调用核心逻辑  
        Object retVal = pjp.proceed();  
          
        System.out.println(" 此处可以做类似于After Advice的事�?");  
        System.out.println("-----End of doAround()------");  
        return retVal;  
    }  
  
    /** 
     * 核心业务逻辑�?出后（包括正常执行结束和异常�?出），执行此Advice 
     * @param joinPoint 
     */  
    private void doAfter(JoinPoint joinPoint) {  
        System.out.println("-----doAfter().invoke-----");  
        System.out.println(" 此处意在执行核心业务逻辑之后，做�?些日志记录操作等�?");  
        System.out.println(" 可�?�过joinPoint来获取所�?要的内容");  
        System.out.println("-----End of doAfter()------");  
        
    }  
      
    /** 
     * 核心业务逻辑调用正常�?出后，不管是否有返回值，正常�?出后，均执行此Advice 
     * @param joinPoint 
     */  
    private void doReturn(JoinPoint joinPoint) {  
        System.out.println("-----doReturn().invoke-----");  
        System.out.println(" 此处可以对返回�?�做进一步处�?");  
        System.out.println(" 可�?�过joinPoint来获取所�?要的内容");  
        System.out.println("-----End of doReturn()------");  
    }  
      
    /** 
     * 核心业务逻辑调用异常�?出后，执行此Advice，处理错误信�? 
     * @param joinPoint 
     * @param ex 
     */  
    private void doThrowing(JoinPoint joinPoint,Throwable ex) {  
        System.out.println("-----doThrowing().invoke-----");  
        System.out.println(" 错误信息�?"+ex.getMessage());  
        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做�?些日志记录操作等�?");  
        System.out.println(" 可�?�过joinPoint来获取所�?要的内容");  
        System.out.println("-----End of doThrowing()------");  
    }  
}
