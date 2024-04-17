package com.example.laba3spring.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://service.ws.sample/", name = "HelloService")
public interface DataService {

	@WebResult(name = "bookInfo", targetNamespace = "")
	@RequestWrapper(
			localName = "getBookInfo",
			targetNamespace = "http://service.ws.sample/",
			className = "sample.ws.service.RequestGetBookInfo")
	@WebMethod(action = "urn:GetBookInfo")
	@ResponseWrapper(
			localName = "getBookInfoResponse",
			targetNamespace = "http://service.ws.sample/",
			className = "sample.ws.service.GetBookInfoResponse")
	String getBookInfo(@WebParam(name = "bookId", targetNamespace = "") int bookId);

	@WebResult(name = "allBooks", targetNamespace = "")
	@RequestWrapper(
			localName = "getAllBooks",
			targetNamespace = "http://service.ws.sample/",
			className = "sample.ws.service.RequestGetAllBooks")
	@WebMethod(action = "urn:GetAllBooks")
	@ResponseWrapper(
			localName = "getAllBooksResponse",
			targetNamespace = "http://service.ws.sample/",
			className = "sample.ws.service.GetAllBooksResponse")
	String getAllBooks();
}
