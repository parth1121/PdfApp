package com.codeplanet.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.codeplanet.model.User;
import com.codeplanet.model.UserFile;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String home(User user) throws IOException, SQLException {
		System.out.println("controller cALLED");
		return "index1";
	}
	@RequestMapping(value="/index1")
	public String index1() {
		System.out.println("controller cALLED");
		return "index1";
	}
	@RequestMapping(value="/merge")
	public String merge() {
		return "merge";
	}
	@RequestMapping(value="/mergePdf",method=RequestMethod.POST)
	public String mergePdf(HttpServletRequest req,UserFile user) throws SQLException, IOException {
		String s=user.getName();
		ArrayList<String> filePath=uploadFileOnServer(user);
		merger(filePath,req);
		System.out.println("test controller cALLED"+s);
		return "download";
	}
	
	public void merger(ArrayList<String> filePath,HttpServletRequest req) throws IOException {
		PDFMergerUtility pdf= new PDFMergerUtility();
		pdf.setDestinationFileName("F:\\planet2.pdf");
		for(String s:filePath)
		{
			File f= new File(s);
			pdf.addSource(f);
		}	
	pdf.mergeDocuments(null);
	System.out.println("pdf merged");
	req.setAttribute("file", "F:\\planet2.pdf");
	
	}
	

	private ArrayList<String> uploadFileOnServer(UserFile user) {
	String rootdirectory="F:/files/merge";
	File directory= new File(rootdirectory);
	if(!directory.exists())
		directory.mkdirs();
	MultipartFile[] f=user.getUserfiles();
	String filepath=null;
	ArrayList<String> list= new ArrayList<String>();
	for(MultipartFile filedata:f) {
	String filename=filedata.getOriginalFilename();
	
	if(filename!=null && filename.length()>0)
	{
		try {
		filepath=directory.getCanonicalPath() + File.separator+filename;
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(filepath));
		bos.write(filedata.getBytes());
		bos.close();
		list.add(filepath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	}
		return list;
	}
	
	@RequestMapping(value="download",method=RequestMethod.POST)
	public String download(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String mimeType=null;
		String file=req.getParameter("filepath");
		File f= new File(file);
		mimeType=getMimeType(f.getCanonicalPath());
		res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "attachement;filename=\""+f.getName()+"\"");
		res.setContentLength((int)f.length());
		InputStream is =new FileInputStream(f);
		ServletOutputStream out=res.getOutputStream();
		IOUtils.copy(is,out);
		is.close();
		out.flush();
		out.close();
	return "index";
	}
	private String getMimeType(String canonicalPath) {
		canonicalPath=canonicalPath.toLowerCase();
		if(canonicalPath.endsWith(".jpg")||canonicalPath.endsWith(".jpeg")||canonicalPath.endsWith(".jpe"))
		return "image/jpeg";
		else if(canonicalPath.endsWith(".pdf"))
			return "application/pdf";
		else
			return "application/pdf";
	}
	
	@RequestMapping(value="/splitter", produces="appication/zip")
	public String splitter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = new File("F:/files/merge/CodePlanetWorkshopProposal.pdf"); 
		PDDocument pd =PDDocument.load(file);
		 Splitter sp=new Splitter(); 
		 List<PDDocument> pd1=sp.split(pd);
		Iterator<PDDocument> it=pd1.listIterator();
		int i=1;
		List<String> filepaths=new ArrayList<String>();
		while(it.hasNext())
		{
			String x="f:/split"+i+".pdf";
		PDDocument pd2=it.next(); pd2.save(x);
		i++;
		filepaths.add(x);
		}
		pd.close();
		zipFiles(filepaths,response);

		return "index1";
	}
		private void zipFiles(List<String> filepaths, HttpServletResponse res) throws IOException {
				String zipFileName="f:/splitterzip.zip";
				FileOutputStream fos= new FileOutputStream(zipFileName);
				ZipOutputStream zos= new ZipOutputStream(fos);
				for(String s:filepaths)
				{
					zos.putNextEntry(new ZipEntry(new File(s).getName()));
					byte[] bytes=Files.readAllBytes(Paths.get(s));
					zos.write(bytes);
					zos.closeEntry();
					
				}
				zos.close();
				
				File f= new File(zipFileName);
				res.setContentType("application/zip");
				res.setHeader("Content-Disposition", "attachement;filename=\""+f.getName()+"\"");
				res.setContentLength((int)f.length());
				InputStream is =new FileInputStream(f);
				ServletOutputStream out=res.getOutputStream();
				IOUtils.copy(is,out);
				is.close();
				out.flush();
				out.close();
	
	}
		@RequestMapping(value="/extractData")
	public String ectractData(HttpServletRequest req) throws IOException {
		String password=req.getParameter("password");
		if(password==null) {
		File file = new File("F:/files/merge/11.pdf");
		PDDocument pd=null;
		try{
		pd =PDDocument.load(file);
		}catch(org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException e) {
			req.setAttribute("filepath","F:/files/merge/11.pdf");
			return "requestPassword";
					
		}
		PDFTextStripper pdf=new PDFTextStripper();
		String s=pdf.getText(pd);
		req.setAttribute("data", s);
		System.out.println(s);
		return "extractedData";}
		else {
			File file = new File(req.getParameter("filePath"));
			PDDocument pd=null;
			try{
				pd =PDDocument.load(file,password);
				}catch(org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException e) {
					req.setAttribute("filepath","F:/files/merge/11.pdf");
					req.setAttribute("error", "password is not correct");
					return "requestPassword";
							
				}
			PDFTextStripper pdf=new PDFTextStripper();
			String s=pdf.getText(pd);
			req.setAttribute("data", s);
			System.out.println(s);
			return "extractedData";
		}
	}
		
		@RequestMapping(value="/removePage")
		public String removePage() throws IOException {
			File file = new File("F:/planet2.pdf");
			PDDocument pd =PDDocument.load(file);
			
			int totalpage=pd.getNumberOfPages();
			System.out.println(totalpage);
			pd.removePage(2);
			pd.save("F:/planet2.pdf");
			pd.close();
			
			return "index1";
		}
		@RequestMapping("/pdftoImage")
		public String pdftoImage() throws IOException {
			File file = new File("F:/files/merge/16.pdf");
			PDDocument pd =PDDocument.load(file);
			int totalpage=pd.getNumberOfPages();
			PDFRenderer  re=new PDFRenderer(pd);
			int i=0;
			while( i<totalpage) {
			BufferedImage img= re.renderImage(i);
			ImageIO.write(img, "JPEG", new File("f:/files/img"+i+".jpg"));
			i++;
			}
			pd.close();
			
			return "index1";
		}
		@RequestMapping("/protect")
		public String protect() throws IOException {
			File file = new File("F:/files/merge/11.pdf");
			PDDocument pd =PDDocument.load(file);
			AccessPermission ap=new AccessPermission();
			
			StandardProtectionPolicy policy=new StandardProtectionPolicy("code21@11#","parth11@!", ap);
			policy.setEncryptionKeyLength(256);
			policy.setPermissions(ap);
			pd.protect(policy);
			pd.save("F:/files/merge/11.pdf");
			pd.close();
			
			return "index1";
				
		}
		@RequestMapping("unlock")
		public String unlock() throws IOException {
			File file = new File("F:/files/merge/11.pdf");
			PDDocument	pd =PDDocument.load(file,"code21@11#");
			pd.setAllSecurityToBeRemoved(true);
			pd.save("F:/files/merge/11.pdf");
			pd.close();
			return  "index1";
		}
		
	
			
	}



