/**
 * 
 */
package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;

/**
 * @author domes
 *
 */
@RestController
public class BorrowerService extends BaseController {
	@Autowired
	AuthorDAO adao;
	@Autowired
	BookDAO bdao;
	@Autowired
	BookCopiesDAO bcdao;
	@Autowired
	BookLoansDAO bldao;
	@Autowired
	BorrowerDAO brdao;
	@Autowired
	BranchDAO bradao;
	@Autowired
	GenreDAO gdao;
	@Autowired
	PublisherDAO pdao;
	
	//validate the borrower's card input cardNo
	@Transactional
	@RequestMapping(value="validateBorrower/{cardNo}", method=RequestMethod.GET, produces="application/json")
	public Boolean checkBorrowerCardNo(@PathVariable int cardNo) {
		Boolean result = false;
		try {
			result=brdao.validateCard(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//get book borrowed by a specific borrower input branchId and cardNo
	@Transactional
	@RequestMapping(value="booksBorrowed/{branchId}/{cardNo}", method=RequestMethod.GET, produces="application/json" )
	public List<Book> booksBorrowed (@PathVariable int branchId,@PathVariable int cardNo){
		List<Book>books = new ArrayList<>();
		try {
			books=bdao.ListOfBooksBorrowed(branchId, cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
