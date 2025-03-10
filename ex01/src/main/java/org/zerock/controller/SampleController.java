package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {

		log.info("basic get.........................");
	}

	@RequestMapping("/basicOnlyGet")
	public void basicGet2() {

		log.info("basic get only get.........................");
	}

	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {

		log.info("" + dto);

		return "ex01";
	}

//http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {

		log.info("name : " + name);
		log.info("age : " + age);

		return "ex02";
	}

//http://localhost:8080/sample/ex02?name=AAA&age=10
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : " + ids);

		return "ex02List";
	}

	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));

		return "ex02Array";

	}

//http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=BBB&list%5B2%5D.name=CCC
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);

		return "ex02Bean";

	}

	// http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}

	// http://localhost:8080/sample/ex04?name=aaa&age=11&page=9
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

		log.info("dto : " + dto);
		log.info("page : " + page);

		return "/sample/ex04";

	}

	// http://localhost:8080/sample/ex05
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05..........");
	}

	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06...............");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
		
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07....................");
		
		//{"name":"홍길동"}
		String msg = "{\"name\" : \"홍길동\"}";
			
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("-----------------------------");
			log.info("name:"+file.getOriginalFilename());
			log.info("size:"+file.getSize());
		});
	}
	
}
