import java.util.LinkedList;

class Library {
  LinkedList<Book> holdings = new LinkedList<Book>();

  // constructor
  Library() {}
  
  
  /* The following contracts are designed for function 'addBook'*/
  //TODO pre-condition: the 'newBook' should not be null
	//@ requires newBook != null;
  
  //TODO post-condition: the newBook should be in the holdings after executing this function
	//@ ensures holdings.contains(newBook);
  
  //TODO post-condition: the size of holdings is increased by 1
	//@ ensures holdings.size() == \old(holdings.size()) + 1 && \result == this;
  
  Library addBook(Book newBook) {// add a new book to the library (assuming there are no duplicate books)
    holdings.add(newBook);
    return this;
  }
  
  /* The following contracts are designed for function 'removeBook'*/
  //TODO pre-condition: the newBook should not be null
	//@ requires newBook != null;
  
  //TODO post-condition: the newBook is in old holdings (i.e., before executing this method) if return true
	//@ ensures \result == true ==> \old(holdings).contains(newBook);
  
  //TODO post-condition: the newBook is not in old holdings if return false
	//@ ensures \result == false ==> \old(holdings.contains(newBook)) == false;
  
  //TODO post-condition: the size of holdings is reduced by 1 if return true
	//@ ensures \result == true ==> holdings.size() == \old(holdings.size()) - 1;
  boolean removeBook(Book newBook) { // remove a new book to the library
	  for (int i =0; i<holdings.size();i++) {
	      if (holdings.get(i).equals(newBook)) {
	    	  holdings.remove(i);
	    	  return true;
	      }
	  }
	  return false;
  }
  
 /* The following contracts are designed for function 'hasBook'*/
  //TODO pre-condition: the newBook should not be null
	//@ requires  newBook != null;
  
  //TODO post-condition: the size of holdings is the same before and after executing this method 
	//@ ensures holdings.size() == \old(holdings.size());
  
  //TODO post-condition: each element in holdings should be the same before and after executing this method
	//@ ensures \forall Book b; holdings.contains(b); b.equals(\old(b));
  
  boolean hasBook(Book newBook) {
	  //TODO invariant: 'i' should decrease after each iteration
	  // @ decreasing 
	  for (int i =0; i<holdings.size();i++) {
		  if (holdings.get(i).equals(newBook)) {
		       return true;
		      }
		  }
		  return false;
   }
  
  /* The following contracts are designed for function 'findByTitle'*/
	  //TODO post-condition: the size of holdings is the same before and after executing this method 
		//@ ensures \old(holdings.size()) == holdings.size();
  	  
      //TODO post-condition: each element in holdings should be the same before and after executing this method
		//@ ensures \forall Book b; b.equals(\old(b)); holdings.contains(b);
  Book findByTitle(String atitle) {// find a book by its title
    for (Book item : holdings) {
      if (item.title.equals(atitle)) {
        return item;
      }
    }
    // If the book isn't found, return null; 
    return null; 
  }
}



// A class for books in a library
class Book {
  String title;
  String callNum;
  int timesOut = 0;
  boolean isAvailable = true;
  
  // constructor
  Book(String title, String callNum) {
    this.title = title;
    this.callNum = callNum;
  }
  
  // mark a book as checked out of the library
  public Book checkOut() {
    this.isAvailable = false;
    this.timesOut = this.timesOut + 1;
    return this;
  }
  
  // mark a book as checked in at the library
  public Book checkIn() {
    this.isAvailable = true;
    return this;
  }

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Book other = (Book) obj;
	if (callNum == null) {
		if (other.callNum != null)
			return false;
	} else if (!callNum.equals(other.callNum))
		return false;
	if (isAvailable != other.isAvailable)
		return false;
	if (timesOut != other.timesOut)
		return false;
	if (title == null) {
		if (other.title != null)
			return false;
	} else if (!title.equals(other.title))
		return false;
	return true;
}
}