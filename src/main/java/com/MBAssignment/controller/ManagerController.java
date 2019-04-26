package com.MBAssignment.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.MBAssignment.bo.Response;
import com.MBAssignment.dao.ObjectDao;
import com.MBAssignment.model.Manager;
import com.MBAssignment.utility.MD5Hash;

@RequestMapping("/rest")
@RestController
public class ManagerController {

	@Autowired
	private ObjectDao objectDao;
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/** Manager Register service
	 * @author Mayur
	 * @since 08/07/2018
	 */
	@CrossOrigin
	@RequestMapping(value="/register",method=RequestMethod.POST)
	private Response managerRegister(@RequestBody Manager manager){
		Response response = new Response();
		try{
			if(null !=manager.getPassword() && null != manager.getEmail()){
				manager.setPassword(MD5Hash.getHash(manager.getPassword()));
				manager.setDob(format.parse(manager.getDateOfBirth()));
				objectDao.saveObject(manager);
				response.setStatus("200");
				response.setMessage("Register Successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
		}
		return response;
	}
	
	
	/** Manager login service
	 * @author Mayur
	 * @since 08/07/2018
	 */
	@CrossOrigin
	@RequestMapping(value="/login",method=RequestMethod.POST)
	private Response login(@RequestBody Manager manager, HttpServletRequest request){
		Response response = new Response();
		try{
			HttpSession session = request.getSession();
			manager.setPassword(MD5Hash.getHash(manager.getPassword()));
			Manager man = objectDao.managerLogin(manager);
			if(null != man){
				session.setAttribute("manager",man);
                response.setStatus("200");
                response.setMessage("Login Successfull.");
                man.setPassword(null);
                response.setResult(man);
			}else{
                response.setStatus("300");
                response.setMessage("Invalid Credentials.");
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
	 * Service For logout
	 * @since 09/07/2018
	 * @param request
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	private Response userLogout(HttpServletRequest request) {
		Response response = new Response();
		try {
			HttpSession session = request.getSession();
			if (null != session.getAttribute("currentUser"))
				session.invalidate();
			response.setStatus("200");
			response.setMessage("Logged out Successfully.");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
			return response;
		}
	}
	/**
	 * Service for check email available or not
	 * @since 09/07/2018
	 * @param manager
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/check_email", method = RequestMethod.POST)
	private Response checkProjectName(@RequestBody Manager man) {
		Response response = new Response();
		try {
			Manager manager = objectDao.getObjectByParam(Manager.class, "email", man.getEmail());
			if (null != manager) {
				response.setStatus("300");
				response.setMessage("email entered is already available..");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Couldn't perform the request.");
			response.setResult(e.getMessage());
			
		}
		return response;
	}
}
