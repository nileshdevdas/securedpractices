package com.vinsys.securitylabs.xxe.problem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vinsys.securitylabs.xxe.Book;

@Controller(value = "xxeproblem")
@RequestMapping(path = "/xxe/problem")
public class XXEProblemController {

	@PostMapping("/xmlupload")
	public ModelAndView handleFileUpload(@RequestParam(name = "file", required = true) MultipartFile file) {
		List<Book> books = new ArrayList<Book>();
		ModelAndView view = new ModelAndView();
		try {
			InputStream is = file.getInputStream();
			File fileToSave = new File("d:/temp/test.xml");
			Files.copy(is, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			view.addObject("status", "success");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(fileToSave);
			NodeList list = doc.getElementsByTagName("book");
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				System.out.println(node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					Book book = new Book();
					book.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
					book.setPublisher(element.getElementsByTagName("publisher").item(0).getTextContent());
					book.setAuthor(element.getElementsByTagName("author").item(0).getTextContent());
					book.setIsbn(element.getElementsByTagName("isbn").item(0).getTextContent());
					books.add(book);
				}
			}
			view.addObject("books", books);
		} catch (IOException e) {
			view.addObject("status", "failed reading");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			view.addObject("status", "failed parsing1");
			e.printStackTrace();
		} catch (SAXException e) {
			view.addObject("status", "failed parsing2");
			e.printStackTrace();
		}
		view.setViewName("xmlupload");
		return view;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/xmlupload")
	public String showFileUpload() {
		return "xmlupload";
	}
}
