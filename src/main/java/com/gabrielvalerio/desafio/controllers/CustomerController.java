package com.gabrielvalerio.desafio.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielvalerio.desafio.model.AuditLog;
import com.gabrielvalerio.desafio.model.AuditOperation;
import com.gabrielvalerio.desafio.model.Customer;
import com.gabrielvalerio.desafio.repository.AuditRepository;
import com.gabrielvalerio.desafio.repository.CustomerRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AuditRepository auditRepository;

	@PostMapping
	@RolesAllowed("ADMIN")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			UsernamePasswordAuthenticationToken currentUser = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			customer.createdAt = new Date();
			customer.updatedAt = customer.createdAt;
			final Customer savedCustomer = customerRepository.save(customer);
			auditRepository.save(
					new AuditLog(
							AuditOperation.CREATE.toString(),
							"customer",
							customer,
							currentUser.getName()
							)
					);
			return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value="/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<Customer> update(@PathVariable("id") String id,
			@Valid @RequestBody Customer customer) {
		UsernamePasswordAuthenticationToken currentUser = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		return customerRepository.findById(id)
				.map(record -> {
					record.setName(customer.getName());
					record.setAddress(customer.getAddress());
					record.setCpf(customer.getCpf());
					record.setPhones(customer.getPhones());
					record.setEmails(customer.getEmails());
					record.updatedAt = new Date();
					customer.id = record.id;
					auditRepository.save(
							new AuditLog(
									AuditOperation.UPDATE.toString(),
									"customer",
									customer,
									currentUser.getName()
									)
							);
					Customer updated = customerRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value="/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity delete(@PathVariable("id") String id) {
		UsernamePasswordAuthenticationToken currentUser = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		auditRepository.save(
				new AuditLog(
						AuditOperation.DELETE.toString(),
						"customer",
						id,
						currentUser.getName()
						)
				);
		customerRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	@RolesAllowed({"ADMIN", "COMMON"})
	public ResponseEntity<List<Customer>> getCustomers() {
		try {
			final List<Customer> customers = customerRepository.findAll();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = {"/{id}"})
	@RolesAllowed({"ADMIN", "COMMON"})
	public ResponseEntity<Customer> getById(@PathVariable String id){
		return customerRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
