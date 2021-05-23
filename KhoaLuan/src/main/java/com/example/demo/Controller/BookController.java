package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/getall")
	public List<Book>GetAll(){
		return bookRepository.findAll();
	}
	@GetMapping("/getone/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " ));
        return ResponseEntity.ok().body(book);
    }
	@PostMapping("/insert")
    public Book createTaiKhoan(@RequestBody Book book) {
        return bookRepository.save(book);
    }
	@DeleteMapping("/delete/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found "));

        bookRepository.delete(book);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	@PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long id,
                                                   @RequestBody Book bookDetais) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));

        book.setName(bookDetais.getName());
        
        final Book updated = bookRepository.save(book);
        return ResponseEntity.ok(updated);
    }


}
