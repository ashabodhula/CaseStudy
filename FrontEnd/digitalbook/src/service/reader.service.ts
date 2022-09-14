import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reader } from 'src/model/reader';

const URL = "http://localhost:8085/digitalbooks/"
@Injectable({
  providedIn: 'root'
})
export class ReaderService {

 constructor(public http: HttpClient) {

  }

  buyBook(reader: any) {
    return this.http.post(URL + "buy/book", reader);
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
  createBook(book: any, authorId: any) {
    return this.http.post(URL + 'author/' + sessionStorage.getItem("authorid") + '/books', book);
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
}
