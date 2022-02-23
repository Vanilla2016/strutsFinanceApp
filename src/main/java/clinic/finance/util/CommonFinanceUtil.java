package clinic.finance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public abstract class CommonFinanceUtil {

	protected String USER = "user";
	protected String PASSKEY = "passWord";
	
	protected File propFile;
	protected BufferedReader propBufferReader;
	protected InputStream inputStream;
	protected Properties prop = null;
	protected String propsPrefix;
		
	protected void loadPropertiesFromResource(String filePath) {
		
		try {
		
			inputStream = new FileInputStream(filePath);

			if (inputStream!=null) {
				/*FOR FR - NULLPOINTER WAS COMING FROM Properties still being null 
				 * (not instantiated , 
				 * not bcos couldn't access props file!!!)
				 */
				prop = new Properties();
				prop.load(inputStream);
			}
		} catch (IOException | NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	protected abstract Properties getProperties();
}
