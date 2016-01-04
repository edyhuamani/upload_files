package pe.com.foxdev.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pe.com.foxdev.beans.MultipleFileUploadForm;

@Controller
public class FileUploadController {

	@RequestMapping(value="/redirect.htm",method={RequestMethod.GET})
	public ModelAndView uploadRedirect(){
		ModelAndView model=new ModelAndView();
		try{
			model.setViewName("uploadMultiple");
		}catch(Exception e){
			
		}
		return model;
	}
	
	/** **/
	
	@RequestMapping(value="/uploadFile.htm",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String uploadSingleFile(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file
			){
		try{
			if (!file.isEmpty()) {
				 byte[] bytes = file.getBytes();
				 
				String rootPath = System.getProperty("catalina.home");
				System.out.println(rootPath);
				
				 // Creating the directory to store file
				File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
				
			}
			
		}catch(Exception e){
			
		}
		return null;
	}
	@RequestMapping(value="/uploadMultipleFile.htm",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String uploadMultipleFile(
			@RequestParam("name") String[] names, 
			@RequestParam("file") MultipartFile[] files
			){
		try{
			if (files.length!=names.length){
				return "Mandatory information missing";
			}
			String message="";
			for(int i=0;i<files.length;i++){
				
				MultipartFile file=files[i];
				byte[] bytes =file.getBytes();
				String nameFile=names[i];
				String rootPath = System.getProperty("catalina.home");
				/**Se esta repitiendo no deberia repetirse por cada iteracion **/
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
                    dir.mkdirs();
				
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nameFile);
				BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
				stream.write(bytes);
	            stream.close();
			}
		}catch(Exception e){
			
		}
		return null;
	}
		/**version 2 **/
	
		@RequestMapping(value="/uploadMultipleFilesMA", method=RequestMethod.POST)
		public String handleFileUploadMA(@ModelAttribute("multipleFileUploadForm") MultipleFileUploadForm multipleFileUploadForm, Model model){
	 
	    List<MultipartFile> files=multipleFileUploadForm.getFiles();   // Storing all Multipart files in a List
	 
	    System.out.println(" Files count " + files.size());
	 
	        try {
	            String filePath="c:/temp/kk/";
	            for (int i=0;i<files.size();i++)
	                files.get(i).transferTo(new File(filePath+files.get(i).getOriginalFilename()));  // Transfer the content of each file to  the file Path (i.e. c:\temp\kk)
	        } catch (Exception e) {
	            return "Error While uploading your files " +  e.getMessage();
	        }
	 
	        model.addAttribute("files", files);
	        return "result";
	    } 
	
		@RequestMapping(value = "/loadMultipleFileUploadMA")
		public String loadMultipleFileUploadMA(Map<String, Object> model) {
			return "MultipleFileUploadMA";
		}	
	
		@RequestMapping(value="/loadMultipleFileUpload")
		public String loadMultipleFileUpload(Map<String, Object> model) {
		    return "MultipleFileUpload";
		}
	
	   @RequestMapping(value="/uploadMultipleFiles", method=RequestMethod.POST)
	    public @ResponseBody String handleFileUpload( @RequestParam("files") MultipartFile files[]){
	            try {
	                String filePath="c:/temp/kk/";
	                StringBuffer result=new StringBuffer();
	                byte[] bytes=null;
	                result.append("Uploading of File(s) ");
	 
	                for (int i=0;i<files.length;i++) {
	                    if (!files[i].isEmpty()) {
	                        bytes = files[i].getBytes();
	                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+files[i].getOriginalFilename())));
	                        stream.write(bytes);
	                        stream.close();
	 
	                       result.append(files[i].getOriginalFilename() + " Ok. ") ;
	                    }
	                    else
	                        result.append( files[i].getOriginalFilename() + " Failed. ");
	 
	            }
	                return result.toString();
	 
	            } catch (Exception e) {
	                return "Error Occured while uploading files." + " => " + e.getMessage();
	            }
	 
	    } 
	
	
}
