import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthorserviceService {
 
  
  createBookContainerFlag:boolean=false;
  createbooksuccessContainerFlag:boolean=false;
  authorsignupNavFlag:boolean=true;
  
  editBookContainerFlag:boolean=false;
  editbooksuccessContainerFlag:boolean=false;
  updateBookPageFlag:boolean=false;
  hastoeditbook: any;
  loginUrl : string = '';
  signUpUrl : string = '';
  book: any;
  digitalBooksContainerFlag: boolean=false;

  constructor(public http :HttpClient) {
    
    this.loginUrl = "http://localhost:8085/digitalbooks/author/login";
    this.signUpUrl = "http://localhost:8085/digitalbooks/author/signup";
   }
   saveAuthor(author: any) {
    return this.http.post(URL+"author/signup",author);
  }
  searchBooks(params:any) {
    let path:any="books/search?";
    if(params.author!=""){
      path+="author="+params.author+"&";
    }
    if(params.price!=""){
      path+="price="+params.price+"&";
    }
    if(params.category!=""){
      path+="category="+params.category+"&";
    }
    if(params.publisher!=""){
      path+="publisher="+params.publisher;
    }
    return this.http.get(URL+path);
  }
  createBook(book: any) {
    return this.http.post(URL+"author/"+sessionStorage.getItem("authorId")+"/books",book);
  }
  updateBook(book:any){
     return this.http.put(URL+"author/"+book.authorid+"/books/"+book.id,book);
    
  }
  getbooksByAuthorID(): any {
    return this.http.get(URL+"books/"+sessionStorage.getItem('authorId'));
   }
  registerAuthor(author : any)  {
    return this.http.post(this.signUpUrl,author);
  }

  loginAuthor(author : any)  {
    return this.http.post(this.loginUrl,author);
  }
}
