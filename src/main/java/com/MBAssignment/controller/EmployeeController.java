package com.MBAssignment.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MBAssignment.bo.Response;
import com.MBAssignment.dao.ObjectDao;
import com.MBAssignment.model.Employee;

@RequestMapping("/rest")
@RestController
public class EmployeeController {

	@Autowired
	private ObjectDao objectDao;
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@CrossOrigin
	@RequestMapping(value="/add-edit-employee",method=RequestMethod.POST)
	private Response managerRegister(@RequestBody Employee employee){
		Response response = new Response();
		try{
			if(null !=employee.getEmployeeId() && employee.getEmployeeId() >0){
				employee.setDob(format.parse(employee.getDateOfBirth()));
				objectDao.updateObject(employee);
				response.setStatus("200");
				response.setMessage("Employee Details Update Successfully");
			}else{
				employee.setDob(format.parse(employee.getDateOfBirth()));
				objectDao.saveObject(employee);
				response.setStatus("200");
				response.setMessage("Employee Details Saved Successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
		}
		return response;
	}
	
	/**
	 * @author Mayur
	 * @param id
	 * @return
	 * Delete Employee by employee id
	 */
	@CrossOrigin
	@RequestMapping(value = "/delete-employee-by-id/{employeeId}", method = RequestMethod.GET)
	private Response deleteEmployee(@PathVariable("employeeId") Long id) {
		Response response = new Response();
		try {
			Employee employee = objectDao.getObjectById(Employee.class, id);
			if (null != employee) {
				objectDao.deleteObject(employee);
				response.setStatus("200");
				response.setMessage("Employee Deleted Successfully");
				return response;
			} else {
				response.setStatus("300");
				response.setMessage("Employee not found");
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
			return response;
		}
	}
	
	/**
	 * @author Mayur
	 * @since 09-07-2018
	 * Employee Details List
	 */
	
	@CrossOrigin
	@RequestMapping(value = "/list-employee", method = RequestMethod.GET)
	private Response listEmployee() {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Employee List");
			response.setResult(objectDao.listObject(Employee.class, "firstName"));
			return response;
		} catch (Exception e) {
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
			return response;
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/employee-details-by-id/{employeeId}", method = RequestMethod.GET)
	private Response employeeDetailsById(@PathVariable("employeeId") Long id) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("employee by id.");
			Employee employee = objectDao.getObjectById(Employee.class, id);
			response.setResult(employee);
			return response;
		} catch (Exception e) {
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
			return response;
		}
	}
}
