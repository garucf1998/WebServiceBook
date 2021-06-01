package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SinhVien;


@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
	
	private List<SinhVien> list= new ArrayList<SinhVien>();
	
	public SinhVienController() {
		list = new ArrayList<SinhVien>();
		list.add(new SinhVien("1", "Vien", "10-02-1998", "Nam", "vien@gmail.com"));
		list.add(new SinhVien("2", "Khoa", "12-12-1998", "Nam", "khoa@gmail.com"));
		list.add(new SinhVien("3", "Thu", "15-05-1998", "Nu", "thu@gmail.com"));
		list.add(new SinhVien("4", "Trinh", "1-1-1998", "Nu", "trinh@gmail.com"));
		list.add(new SinhVien("5", "Tuan", "21-2-1998", "Nam", "tuan@gmail.com"));
	}
	@GetMapping("/")
	public void GetInstance(){
		SinhVienController controller = new SinhVienController();
	}
	@GetMapping("/getall")
	public List<SinhVien>GetAll(){
		return list;
	}
	@GetMapping("/getone/{id}")
    public SinhVien getSinhVienById(@PathVariable(value = "id") String id)
            {
		for (SinhVien sv : list) {
			if (sv.getMaso().equals(id)) { 
				return sv;
			} 
		}
		return null;
    }
	@PostMapping("/insert")
    public void createSinhVien(@RequestBody SinhVien sv) {
       list.add(sv);
    }
	@PutMapping("/update/{id}")
    public boolean updateSinhVien(@PathVariable(value = "id") String id,
                                                   @RequestBody SinhVien SinhVienDetais) throws ResourceNotFoundException {
		for (SinhVien bookItem : list) {
			if (bookItem.getMaso().equals(id)) {
				bookItem.setHoten(SinhVienDetais.getHoten());
				bookItem.setNgaysinh(SinhVienDetais.getNgaysinh());
				bookItem.setGioitinh(SinhVienDetais.getGioitinh());
				bookItem.setEmail(SinhVienDetais.getEmail());
				return true;
			}
		}
		return false;
    }

}
