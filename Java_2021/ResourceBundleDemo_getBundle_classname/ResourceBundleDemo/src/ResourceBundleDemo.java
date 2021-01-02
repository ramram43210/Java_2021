import java.util.Enumeration;
import java.util.ResourceBundle;

public class ResourceBundleDemo
{

	public static void main(String[] args)
	{

		ResourceBundle rb = ResourceBundle.getBundle("MessageBundle");

		System.out.println(rb.getString("Hello"));
		System.out.println(rb.getString("Goodbye"));
		
		Enumeration<String> enumerationOfKeys = rb.getKeys();
		
		/*
		 * Print all the keys and corresponding values
		 */
		while (enumerationOfKeys.hasMoreElements())
		{
			Object key = enumerationOfKeys.nextElement();
			Object value = rb.getObject(key.toString());
			System.out.println(key+" = "+value);
		}

	}

}
