package com.capgemini.onlinetest.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinetest.entity.Tests;
import com.capgemini.onlinetest.exception.EntityAlreadyExists;
import com.capgemini.onlinetest.service.TestServiceImpl;

/*************************************************************************************************************************************
- File Name        : TestController.java
- Author           : Capgemini
- Creation Date    : 15-08-2020
- Description      : This Controller class act as an end point to manage the entire Test Service
 ************************************************************************************************************************************/


@RestController
@CrossOrigin("*")
public class TestController {

	@Autowired
	TestServiceImpl testService;
	
	Logger logger = LoggerFactory.getLogger(TestController.class);

	@ExceptionHandler(EntityAlreadyExists.class)
	
	
/*************************************************************************************************************************************
    - Method Name      : addTest
    - Input Parameters : Tests test
    - End Point Url    : /addTest
    -Request Method Type: PostMapping
    - Author           : Capgemini
    - Creation Date    : 13-08-2020
    - Description      : Inserting the test information entered by admin into the database.
*************************************************************************************************************************************/

	@PostMapping("/addTest")
	public ResponseEntity<?> addTest(@RequestBody Tests test) {
		
		try {
			
			testService.addTest(test);
			logger.info("Test Added Successfully");
			return new ResponseEntity<String>("Test Added", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

/*************************************************************************************************************************************
    - Method Name      : viewAllTest
    - End Point Url    : /viewTests
    -Request Method Type: GetMapping
    - Author           : Capgemini
    - Creation Date    : 14-08-2020
    - Description      : Displaying all the tests available in the database by admin.
*************************************************************************************************************************************/

	@GetMapping("/viewTests")
	public List<Tests> viewAllTest() {
		
		return testService.viewAll();
	}

/*************************************************************************************************************************************
    - Method Name      : deleteTest
    - Input Parameters : testId
    - End Point Url    : /deleteTest/{id}
    -Request Method Type: DeleteMapping
    - Author           : Capgemini
    - Creation Date    : 13-08-2020
    - Description      : Deleting the test information entered by admin into the database by its id.
*************************************************************************************************************************************/

	@DeleteMapping("/deleteTest/{id}")
	public ResponseEntity<?> deleteTest(@PathVariable(value = "id") int testId) {
		
		try {
			
			testService.deleteTest(testId);
			logger.info("Test Deleted Successfully");
			return new ResponseEntity<>("Test deleted", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

/*************************************************************************************************************************************
    - Method Name      : updateTest
    - Input Parameters : testId,Tests test
    - End Point Url    : /updateTest/{id}
    -Request Method Type: PutMapping
    - Author           : Capgemini
    - Creation Date    : 14-08-2020
    - Description      : Updating the test information entered by admin into the database.
*************************************************************************************************************************************/

	@PutMapping("/updateTest/{id}")
	public ResponseEntity<?> updateTest(@PathVariable(value = "id") int testId, @RequestBody Tests test) {
		
		try {
			
			testService.updateTest(testId, test);
			logger.info("Test Updated Successfully");
			return new ResponseEntity<>("Test updated", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}

	}

/*************************************************************************************************************************************
    - Method Name      : findTestById
    - Input Parameters : testId
    - End Point Url    : /viewTestById/{id}
    -Request Method Type: GetMapping
    - Author           : Capgemini
    - Creation Date    : 15-08-2020
    - Description      : Displaying the test information entered by admin into the database by its id.
************************************************************************************************************************************/

	@GetMapping("/viewTestById/{id}")
	public Optional<Tests> findTestById(@PathVariable(value = "id") int testId) {
		
		return testService.findTestById(testId);
	}

}