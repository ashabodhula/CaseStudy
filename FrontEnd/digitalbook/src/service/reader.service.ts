import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reader } from 'src/model/reader';
export enum Category{
  FICTION,
	FANTASY ,
	SELFHELP,
	HEALTHCARE,
	AUTOBIOGRAPHY
};
const URL = "http://localhost:8085/digitalbooks/"
@Injectable({
  providedIn: 'root'
})
export class ReaderService {
  returnBook(book: any, readeremail: string) {
    throw new Error('Method not implemented.');
  }
  thisdigitalBooksContainerFlag:boolean=true;
  digitalBooksContainerFlag: boolean=true;
  createbooknavFlag:boolean=false;
  createBookContainerFlag:boolean=false;
  authorsignupNavFlag:boolean=true;
  book:any;
  authorBooksContainerFlag: boolean=false;
  editBookContainerFlag:boolean=false;
  createbooksuccessContainerFlag:boolean=false;
  updateBookPageFlag:boolean=false;
  editbooksuccessContainerFlag: boolean=false;
  hastoeditbook={
    id:Number,
    authorid:String,
    title:String,
    category:Category,
    author:String,
    price:Number,
    publisher:String,
    publisheddate:Date,
    chapters:Number,
    bookstatus:Boolean,
    content:String
  };
  book1:any= JSON.parse(JSON.stringify(sessionStorage.getItem("buyingbook")));
  
 constructor(public http: HttpClient) {

  }
  readBookByPaymentID(reader:any) {
    return this.http.post(URL + "read/book", reader);
  }


  buyBook(reader: any) {
    return this.http.post(URL + "books/buy/"+this.book1, reader);
  }

  getMyBooksByReaderEmail(email:any) {
    return this.http.get(URL+"readers/"+email+"/books");
  }
  //reader search books
  searchBooks(params: any) {
    let path: any = "books/search?";
    if (params.author != "") {
      path += "author=" + params.author + "&";
    }
    if (params.price != "") {
      path += "price=" + params.price + "&";
    }
    if (params.category != "") {
      path += "category=" + params.category + "&";
    }
    if (params.publisher != "") {
      path += "publisher=" + params.publisher;
    }

    return this.http.get(URL + path);
  }

  getbooksByAuthorID(): any {
    return this.http.get(URL + "books/" );
  }
  getAllBooks() {
    return this.http.get(URL + "allbooks");
  }
  //need to write for add book
  createBook(book: any) {
    return this.http.post(URL+"author/"+sessionStorage.getItem("authorId")+"/books",book);
  }
  updateBook(book:any){
    return this.http.put(URL+"author/"+book.authorid+"/books/"+book.id,book);
 }



  registerAuthor(author: any) {
    return this.http.post(URL + 'author/signup', author);


  }

  loginAuthor(author: any) {
    return this.http.post(URL + 'author/login', author);
  }
  registerReader(reader: any) {
    return this.http.post(URL + 'reader/signup', reader);
  }

  loginReader(reader: any) {
    return this.http.post(URL + 'reader/login', reader);
  }
  signout(authorid: any) {
    return this.http.post(URL+"author/"+authorid+"/signout",null);
  }
}
function bookId(bookId: any) {
  throw new Error('Function not implemented.');
}

