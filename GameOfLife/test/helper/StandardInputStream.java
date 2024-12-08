package helper;

import java.io.InputStream;

public class StandardInputStream extends InputStream {
	  private final StringBuilder sb = new StringBuilder();
	  private final String lf = System.getProperty("line.separator");
	
	  /*
	   * @param str
	   */
	  public void inputln(String str) {
	    sb.append(str).append(lf);
	  }
	
	  @Override
	  public int read() {
	    if (sb.length() == 0) return -1;
	    int result = sb.charAt(0);
	    sb.deleteCharAt(0);
	    return result;
	  }
}
