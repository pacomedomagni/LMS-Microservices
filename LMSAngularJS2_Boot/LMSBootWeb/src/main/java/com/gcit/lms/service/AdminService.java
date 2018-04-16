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
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;

@RestController
public class AdminService extends BaseController {

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

	@RequestMapping(value="initAuthor", method=RequestMethod.GET, produces="application/json" )
	public Author initAuthor() throws SQLException {
		return new Author();
	}
	@RequestMapping(value="initBook", method=RequestMethod.GET, produces="application/json" )
	public Book initBook() throws SQLException {
		return new Book();
	}
	@Transactional
	@RequestMapping(value = "deleteBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBorrower(@RequestBody Borrower borrower) {
		try {
			brdao.deleteBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// update borrower information input the CardNo and update name phone and
	// address
	@Transactional
	@RequestMapping(value = "updateBorrowerInfo", method = RequestMethod.POST, consumes = "application/json")
	public void updateBorrowerInfo(@RequestBody Borrower borrower) {
		try {
			brdao.updateBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "searhcBorrower/{search}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> searchBorrowerByName(@PathVariable String search) {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = brdao.searchBorrowerByName(search);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowers;
	}

	// create new borrower account input name, address, phone
	@Transactional
	@RequestMapping(value = "createNewBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void createBorrowerNewAccount(@RequestBody Borrower borrower) {

		try {
			brdao.createBorrowerAccount(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get the list of all borrowers no input
	@Transactional
	@RequestMapping(value = "getAllBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowers() {
		List<Borrower> borrowers = new ArrayList<>();

		try {
			borrowers = brdao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowers;
	}
	
	// Everything about Publisher follow
	@RequestMapping(value = "searchPublishers/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> searchPublisherByName(@PathVariable("searchString") String searchString) {
		List<Publisher> publishers = new ArrayList<>();
		try {
			publishers = pdao.searchPublisherByName(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publishers;
	}

	@RequestMapping(value = "deletePublisher", method = RequestMethod.POST, consumes = "application/json")
	public void deletePublisher(@RequestBody Publisher publisher) {
		try {
			pdao.deletePublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "updatePublisher", method = RequestMethod.POST, consumes = "application/json")
	public void updatePublisher(@RequestBody Publisher publisher) {

		try {
			pdao.updatePublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "createPublisher", method = RequestMethod.POST, consumes = "application/json")
	public void createNewPublisher(@RequestBody Publisher publisher) {
		try {
			pdao.createPublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "listPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> listOfAllPublisher() {
		List<Publisher> publishers = new ArrayList<>();
		try {
			publishers = pdao.readPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publishers;
	}

	/*// everything about branches follow below
	@Transactional
	@RequestMapping(value = "createNewBranch", method = RequestMethod.POST, consumes = "application/json")
	public void createNewBranch(@RequestBody Branch branch) {

		try {
			bradao.createBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	@RequestMapping(value = "updateBranchInfo", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateBranchInfo(@RequestBody Branch branch) {
		try {
			bradao.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	@Transactional
	@RequestMapping(value = "deleteBranch", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBranch(@RequestBody Branch branch) {

		try {
			bradao.deleteBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	}*/

	/*@Transactional
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
	}*/

	/*// needed to be improved
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

	// Override due date input bookId, branchId and cardNo
	@RequestMapping(value = "overideDueDate", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void overrideDueDate(@RequestBody BookLoans bookLoan) {

		try {
			bldao.overrideDueDate(bookLoan);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/*@RequestMapping(value = "updateAuthor", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateAutho(@RequestBody Author author) throws SQLException {
		try {
			if (author.getAuthorId() != null && author.getAuthorName() != null) {
				adao.updateAuthor(author);
			} else if (author.getAuthorId() == null && author.getAuthorName() != null) {
				adao.createAuthor(author);
			} else {
				adao.deleteAuthor(author);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}*/
	@RequestMapping(value = "createAuthor", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void createAuthor(@RequestBody Author author) {
		try {
			adao.createAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "updateAuthor", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateAuthor(@RequestBody Author author) {
		try {
			adao.updateAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "deleteAuthor", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void deleteAuthor(@RequestBody Integer authorId) {
		try {
			adao.deleteAuthor(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "deleteBook", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void deleteBook(@RequestBody Integer bookId) {
		try {
			bdao.deleteBook(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "createBook", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void createBook(@RequestBody String title, @RequestBody Integer pubId) {
		try {
			bdao.createBook(title, pubId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "updateBook", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateBook(@RequestBody Book book) {
		try {
			bdao.updateBook(book);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*@RequestMapping(value = "updateBook", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateBook(@RequestBody Book book) throws SQLException {

		try {

			if (book.getBookId() != null && book.getTitle() != null) {
				bdao.updateBook(book);
				bdao.deleteBookAuthor(book);
				if (book.getAuthors() != null) {
					bdao.updateBookAuthor(book);
				}
				// call update book authors
				if(book.getGenres()!=null) {
					bdao.updateBookGenres(book);
				}
				// caa update book genres
			} else if (book.getBookId() == null && book.getTitle() != null) {
				Integer bookId = bdao.createBookWithPK(book);
				book.setBookId(bookId);
				if (book.getAuthors() != null) {
					bdao.updateBookAuthor(book);
				}
				// call update book authors
				if(book.getGenres()!=null) {
					bdao.updateBookGenres(book);
				}
				
			} else {
				bdao.deleteBook(book);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}*/

	@Transactional
	@RequestMapping(value = "listAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> readAllAuthors() {
		List<Author> authors = new ArrayList<>();
		;

		try {
			authors = adao.readAuthors();
			for (Author a : authors) {
				a.setBooks(bdao.searchBookByAuthorId(a));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authors;
	}

	@RequestMapping(value = "readAuthorsByName/{searchString}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public List<Author> searchAuthorsByName(@PathVariable("searchString") String searchString) {
		List<Author> authors = new ArrayList<>();
		;
		try {
			authors = adao.searchAuthorsByName(searchString);
			for (Author a : authors) {
				a.setBooks(bdao.searchBookByAuthorId(a));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}
	@RequestMapping(value = "getAuthorByPk/{authorId}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Author getAuthorByPk(@PathVariable ("authorId")Integer authorId) {
		Author author =null;
		
		try {
			author= adao.getAuthorByPk(authorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return author;
	}
	
	@RequestMapping(value = "getBookByPk/{bookId}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public Book getBookByPk(@PathVariable Integer bookId) {
		Book book=null;
		try {
			book=bdao.getBookByPK(bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	@RequestMapping(value ="listOfBooks", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public List<Book>listOfBooks(){
		List<Book>books = new ArrayList<>();
		
		try {
			books= bdao.readBooks();
			for(Book b:books) {
				b.setAuthors(adao.getAuthorsWithBookid(b.getBookId()));	
				b.setGenres(gdao.getGenresWithBookid(b.getBookId()));
				b.setPublisher(pdao.getPublisherByBook(b.getBookId()));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	
	@RequestMapping(value ="listOBooks", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public List<Book>listOBooks(){
		List<Book>books = new ArrayList<>();
		
		try {
			books= bdao.readBooks();
			/*for(Book b:books) {
				b.setAuthors(adao.getAuthorsWithBookid(b.getBookId()));	
			}*/
			for(Book b:books) {
				b.setGenres(gdao.getGenresWithBookid(b.getBookId()));	
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	
	
	@RequestMapping(value = "searchBookByTitle/{searchString}", method = RequestMethod.GET, produces = "application/json")
	@Transactional
	public List<Book>searchBookByTitle(@PathVariable ("searchString")String searchString){
		List<Book>books = new ArrayList<>();
		
		try {
			books= bdao.searchBookByTitle(searchString);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
}
