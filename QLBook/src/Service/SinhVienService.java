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

import Entity.SinhVien;


public class SinhVienService {

	public  String GET_ALL_SINH_VIEN="http://localhost:8080/sinhvien/getall";
	public  String GET_ONE_SINH_VIEN="http://localhost:8080/sinhvien/getone";
	public  String POST_SINH_VIEN="http://localhost:8080/sinhvien/insert"; 
	public  String PUT_SINH_VIEN="http://localhost:8080/sinhvien/update";
	private static SinhVienService instance;
	
	
	public static SinhVienService GetInstance() {
		if(instance==null) {
			instance = new SinhVienService();
		}
		return instance;
	}
	public List<SinhVien>GetAllSinhVien() throws IOException {
		List<SinhVien>getall=new ArrayList<>();
		URL urlForGetRequest = new URL(GET_ALL_SINH_VIEN);
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
			SinhVien[] svList = gson.fromJson(object, SinhVien[].class);

			for(int i=0;i<svList.length;i++)
				getall.add(svList[i]);
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return getall;
	}
	public SinhVien GetOneSinhVien(int id) throws IOException {
		SinhVien sinhvien=new SinhVien();
		URL urlForGetRequest = new URL(GET_ONE_SINH_VIEN+"/"+id);
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
			sinhvien = gson.fromJson(response, SinhVien.class);

			
		} else {
			System.out.println(urlForGetRequest);
		}

		return sinhvien;
	}
	public  int POSTRequest(SinhVien sv) throws IOException {
		Gson gson = new Gson();
		String POST_PARAMS = gson.toJson(sv);
	    
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_SINH_VIEN);
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
	
	public  int PUTRequest(SinhVien sv) throws IOException {

		Gson gson = new Gson();
		String PUT_PARAMS = gson.toJson(sv);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_SINH_VIEN+"/"+sv.getMaso());
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
