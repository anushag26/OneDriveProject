package com.qa.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



import com.qa.base.TestBase;


public class FileUtility extends TestBase {

	public static void CompareTextFiles() throws IOException
	{
		
		 File dir = new File(pro.getProperty("downloadFilepath"));
		 File[] files = dir.listFiles();
		
		BufferedReader reader1 = new BufferedReader(new FileReader(files[0]));
        
        BufferedReader reader2 = new BufferedReader(new FileReader(files[1]));
         
        String line1 = reader1.readLine();
         
              
        String line2 = reader2.readLine();
         
        boolean areEqual = true;
         
        int lineNum = 1;
         
        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                areEqual = false;
                 
                break;
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                 
                break;
            }
             
            line1 = reader1.readLine();
             
            line2 = reader2.readLine();
             
            lineNum++;
        }
         
        if(areEqual)
        {
            System.out.println("Two files have same content.");
        }
        else
        {
            System.out.println("Two files have different content. They differ at line "+lineNum);
             
            System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
        }
         
        reader1.close();
         
        reader2.close();
    }
	
	public static boolean verify_fileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length;i++)
	    {
	        if (dir_contents[i].getName().equals(fileName))
	        	return flag=true;
	     }
	   
	    return flag;
	}
	
	public static File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	
	public static void Delete_File_From_Downloads_Folder()
	{
     
		File file = new File(pro.getProperty("downloadFilepath"));      
		   String[] myFiles;    
		       if(file.isDirectory())
		       {
		           myFiles = file.list();
		           for (int i=0; i<myFiles.length; i++)
		           {
		               File myFile = new File(file, myFiles[i]); 
		               myFile.delete();
		           }
		        }
		       System.out.println("deleted files in downloads folder");
	}

}
	



