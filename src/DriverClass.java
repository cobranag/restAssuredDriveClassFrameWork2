import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import CommonfunctionPackage.utility_Common_Function;
//import TestClassPackage.post_tc_1;

public class DriverClass {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		//post_tc_1.execute();
		ArrayList<String> testcaserun=utility_Common_Function.readdataexcel("Test_runner","TestCaseNameToExecute");
		int count=testcaserun.size();
		System.out.println(count);
		
		for (int i=1; i<count; i++)
		{
			String testcasename=testcaserun.get(i);
			System.out.println(testcasename);
			
			// call the testcaseclass on runtime by using java.lang.reflect package
			Class<?>testclassname=Class.forName("TestClassPackage."+testcasename);
			
			// call the execute method belonging to test class captured in variable testclassname by using java.lang.reflect.method class
			Method executeMethod=testclassname.getDeclaredMethod("execute");
			
			// set the accessibility of method true 
			executeMethod.setAccessible(true);
			
			// create the instance of testclass captured in variable name testclassname
			Object instanceOfTesClass=testclassname.getDeclaredConstructor().newInstance();
			
			// execute the testclass captured in variable name testclass name
			executeMethod.invoke(instanceOfTesClass);
		}

	}

}
