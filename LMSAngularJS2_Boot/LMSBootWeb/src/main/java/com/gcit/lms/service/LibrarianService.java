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
import com.gcit.lms.entity.Branch;

/**
 * @author domes
 *
 */
@RestController
public class LibrarianService extends BaseController{
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
	
	
	@RequestMapping(value="initBranch", method=RequestMethod.GET, produces="application/json" )
	public Branch initBranch() throws SQLException {
		return new Branch();
	}
	@RequestMapping(value="initBookLoans", method=RequestMethod.GET, produces="application/json" )
	public BookLoans initBookLoans() throws SQLException {
		return new BookLoans();
	}
	@RequestMapping(value="initBookCopies", method=RequestMethod.GET, produces="application/json" )
	public BookCopies initBookCopies() throws SQLException {
		return new BookCopies();
	}
	
	//function to checkOut a book input branchId, bookId and cardNo
		@Transactional
		@RequestMapping(value="checkOutBook", method=RequestMethod.POST, consumes="application/json" )
		public void checkOutBook(@RequestBody BookLoans bookLoan, @RequestBody BookCopies bookCopie) {
			
			try {
				bldao.createBookLoans(bookLoan);
				bcdao.checkOutBookCopies(bookCopie);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//function to return a book input branchId, bookId and cardNo,
		@Transactional
		@RequestMapping(value="returnBook", method=RequestMethod.POST, consumes="application/json" )
		public void returnBook(@RequestBody BookLoans bookLoan, @RequestBody BookCopies bookCopie) {
			
			try {
				bldao.updateDateInBookLoans(bookLoan);
				bcdao.returnBookCopies(bookCopie);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	// everything about branches follow below
	//create a new branch input branch name and address
	@Transactional
	@RequestMapping(value = "createBranch", method = RequestMethod.POST, consumes = "application/json")
	public void createNewBranch(@RequestBody Branch branch) {

		try {
			bradao.createBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// output all the branchs available no input needed
	@Transactional
	@RequestMapping(value = "listBranchs", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranchs() {
		List<Branch> branchs = new ArrayList<>();
		try {
			branchs = bradao.readBranchs();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branchs;
	}
	// update branch info name and address while the branchId is set
	@RequestMapping(value = "updateBranch", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateBranchInfo(@RequestBody Branch branch) {
		try {
			bradao.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// search branch by name
	@Transactional
	@RequestMapping(value = "readBranchByName/{search}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> searchBranchByName(@PathVariable("search") String search) {
		List<Branch> branchs = new ArrayList<>();
		try {
			branchs = bradao.SearchBranchByName(search);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branchs;
	}
	// delete a branch by input branch id
	@Transactional
	@RequestMapping(value = "deleteBranch", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBranch(@RequestBody Integer branchId) {

		try {
			bradao.deleteBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional
	@RequestMapping(value = "getBranchByPk/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public  Branch getBranchByPk(@PathVariable Integer branchId) {
		Branch branch = null;
		try {
			 branch= bradao.getBranchByPk(branchId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return branch;
	}
	// output the numeric number of the total branchs
	@Transactional
	@RequestMapping(value = "totalBranchs", method = RequestMethod.GET, produces = "application/json")
	public Integer totalNumberOfBranchs() {

		Integer total = 0;
		try {
			total = bradao.getBranchesCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
	// list of all the books available in a certain branch
	@Transactional
	@RequestMapping(value = "listOfBooksByBranchId/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> listOfBooksByBranchId(@PathVariable("branchId") int branchId) {
		List<Book> books = new ArrayList<>();
		try {
			books = bdao.getBooksFromBranchId(branchId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	// numeric number of a certain book at a certain branch
	@Transactional
	@RequestMapping(value = "getAvailableBookCopie/{branchId}/{bookId}", method = RequestMethod.GET, produces = "application/json")
	public Integer getAvailableBookCopie(@PathVariable int branchId, @PathVariable int bookId) {
		Integer totals = 0;
		try {
			totals = bcdao.countBookCopies(branchId, bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totals;
	}

	// needed to be improved
	@Transactional
	@RequestMapping(value = "addBookCopies", method = RequestMethod.POST, consumes = "application/json")
	public void addBookCopies(@RequestBody BookCopies bookCopie) {
		try {
			bcdao.updateBookCopies(bookCopie);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
