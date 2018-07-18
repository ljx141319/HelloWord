package demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo {

	public static void main(String[] args) throws Exception {

		Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-06-13 15:58:11");
		
		System.out.println(new Date().getTime()>begin.getTime());
		
	}

}
