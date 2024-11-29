package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException 
	{
	    String path = ".\\testData\\opencartDataDriverTestingData.xlsx";      //" . " is represent to this project
	    
	    ExcelUtility xlutil = new ExcelUtility(path);      // createing an object for xl file
	    
	    
	    int totalrows=xlutil.getRowCount("Sheet1");
	    int totalcols=xlutil.getCellCount("Sheet1", 1 );
	    
	    String logindata[][] = new String [totalrows][totalcols];
	    
	    for(int i=1;i<totalrows;i++) 
	    {
	    	for(int j=0;j<totalcols;j++) 
	    	{
	    		logindata[i-1][j]=xlutil.getCellData("Sheet1",i,j);
	    	}
	    }
	    return logindata;
	}

}
