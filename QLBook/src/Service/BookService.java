package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Entity.Book;


public class BookService {

	public  String GET_ALL_BOOK="http://localhost:8080/book/getall";
	public  String GET_ONE_BOOK="http://localhost:8080/book/getone";
	public  String POST_BOOK="http://localhost:8080/book/insert"; 
	public  String DELETE_BOOK="http://localhost:8080/book/delete";
	public  String PUT_BOOK="http://localhost:8080/book/update";
	private static BookService instance;
	
	
	public static BookService GetInstance() {
		if(instance==null) {
			instance = new BookService();
		}
		return instance;
	}
	public List<Book>GetAllBook() throws IOException {
		List<Book>getall=new ArrayList<>();
		URL urlForGetRequest = new URL(GET_ALL_BOOK);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			// print result
			System.out.println("JSON String Result " + response);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
			Book[] bookList = gson.fromJson(object, Book[].class);

			for(int i=0;i<bookList.length;i++)
				getall.add(bookList[i]);
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return getall;
	}
	public Book GetOneBook(Long id) throws IOException {
		Book book=new Book();
		URL urlForGetRequest = new URL(GET_ONE_BOOK+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new Gson();
			book = gson.fromJson(response, Book.class);

			
		} else {
			System.out.println(urlForGetRequest);
		}

		return book;
	}
	public  int POSTRequest(Book book) throws IOException {
		Gson gson = new Gson();
		String POST_PARAMS = gson.toJson(book);
	    
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_BOOK);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type", "application/json");


	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return responseCode;
	}
	public int DeleteBook(Long id) throws IOException {
		Book book=new Book();
		URL urlForGetRequest = new URL(DELETE_BOOK+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("DELETE"); // set userId its a sample here
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return responseCode;
	}
	public  int PUTRequest(Book book) throws IOException {

		Gson gson = new Gson();
		String PUT_PARAMS = gson.toJson(book);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_BOOK+"/"+book.getId());
	    HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();
	    putConnection.setRequestMethod("PUT");
	    putConnection.setRequestProperty("Content-Type", "application/json");


	    putConnection.setDoOutput(true);
	    OutputStream os = putConnection.getOutputStream();
	    os.write(PUT_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = putConnection.getResponseCode();
	    String message=putConnection.getResponseMessage();
	    

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            putConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("PUT NOT WORKED");
	    }
	    return responseCode;
	}
}
