package com.capgemini.tlta.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.capgemini.tlta.exception.ActivityException;
import com.capgemini.tlta.model.LearningActivity;

import com.capgemini.tlta.sevice.LearningActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/learningActivity")
public class LearningActivityController {
	@Autowired(required = false)
	@Qualifier(value = "learningActivityService")
	private LearningActivityService learningService;

	//get learningActivity by Id
	//http://localhost:8081/springfox/api/learningActivity/1
	@ApiOperation(value = "Get Learning Activities By Id",
			response = LearningActivity.class,
			tags = "get-Learning-Activity",
			consumes = "learningActivityId",
			httpMethod = "GET") 
	@GetMapping("/{id}")
	public ResponseEntity<LearningActivity> getLearningActivityById(@PathVariable Integer id){
		try {
			LearningActivity learningActivity= learningService.searchLearningActivityById(id);
//			log.info("Product added"+ product);
			return new ResponseEntity<>(learningActivity,HttpStatus.OK);
		}catch(ActivityException e) {
//			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	//get all learning activities
		//http://localhost:8081/springfox/api/learningActivity/
		@ApiOperation(value = "Get All learning activities",
				response = LearningActivity.class,
				tags = "get-LearningActivity",
				httpMethod = "GET") 
		
		@GetMapping("/")
		public ResponseEntity<List<LearningActivity>> getAllLearningActivity(){
			try {
				List<LearningActivity> learningActivityList = learningService.getAllLearningActivity();
//				log.info("Returning all product details");
				return new ResponseEntity<>(learningActivityList,HttpStatus.OK);
			}catch(ActivityException e) {
//				log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			} 
		}
		
		//http://localhost:8081/springfox/api/learningActivity/
		//add product	
		@ApiOperation(value = "Add a learning activity",
				response = String.class,
				tags = "get-learningActivity",
				consumes = "receives learningActivity object as request body",
				httpMethod = "POST") 
		
		@PostMapping("/{id}")
		public String addLearningActivity(@RequestBody LearningActivity learningActivity, @PathVariable Integer id) {
			try {
				LearningActivity status= learningService.addLearningActivity(learningActivity, id);
				if(status != null) {
//					log.info("product:"+product.getProductName()+" added to database");
					return "Learning Activity "+learningActivity.getId()+" added to database";
				}else {
//					log.debug("Unable to add product");
					return "Unable to add product to database";
				}

			}catch(ActivityException e) {
//				log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			} 
		}

		
		//http://localhost:8081/springfox/api/learningActivity/1
		//delete product
		@ApiOperation(value = "Delete learning activity By Id",
				response = String.class,
				tags = "delete-learning-activity",
				consumes = "learning activity Id",
				httpMethod = "DELETE") 
		
		@DeleteMapping("/{id}")
		public String deleteAssessment(@PathVariable Integer id) {
			try {
				Integer status= learningService.deleteLearningActivity(id);
				if(status ==1) {
//					log.info("product: "+id+" deleted from database");
					return "Activity : "+id+" deleted from database";
				}else {
//					log.debug("Unable to delete product from database");
					return "Unable to delete activity from database";
				}

			}catch(ActivityException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			} 
		}

		//http://localhost:8081/springfox/api/learningActivity/
		//update product
		@ApiOperation(value = "Update learning activity",
				response = LearningActivity.class,
				tags = "update-learning-activity",
				consumes = "learningActivity object sents as response body",
				httpMethod = "PUT") 
		@PutMapping("/")
		public ResponseEntity<LearningActivity> updateAssessment(@RequestBody LearningActivity learningActivity) {
			try {
				LearningActivity updatedActivity= learningService.updateLearningActivity(learningActivity);
//				log.info("Product: "+ product.getProductId()+ " updated");
				return new ResponseEntity<>(updatedActivity,HttpStatus.OK);

			}catch(ActivityException e) {
//				log.error(e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			} 
		}
}
