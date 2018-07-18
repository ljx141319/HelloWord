package demo;


public final class Utils {


	/**
	 * 字符串空值检测
	 * @return 字符串
	 */
	public static String toString(Object string){
		if(string==null||string==""){
			StackTraceElement[] ste = new Exception().getStackTrace();
			System.err.println("异常 "+ste[1].getClassName()+" - "+ste[1].getFileName()+" - "+ste[1].getMethodName()+" - "+ste[1].getLineNumber()+" - 此处空值:"+string);
		}
		String newstring=string==null?"":string.toString();
		return newstring;
	}

}
