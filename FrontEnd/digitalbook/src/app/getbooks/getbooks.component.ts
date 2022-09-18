import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService,Category } from 'src/service/reader.service';



@Component({
  selector: 'app-getbooks',
  templateUrl: './getbooks.component.html',
  styleUrls: ['./getbooks.component.css']
})
export class GetbooksComponent implements OnInit {
 
  
  bookcategory=Object.values(Category).filter(value => typeof value==="string");
  search = {
    "author": "",
    "category": "",
    "publisher": "",
    "price": ""

  }
  Books:any=null;
  nobookFoundMessage:any;
 
  
  getmyBooksContainerFlag:boolean=true;
  bookcontentFlag:boolean=false;
  constructor(public readerService:ReaderService , public router:Router) { }
  
  searchBooks(){
    this.nobookFoundMessage="";
    if(this.search.author==="" && this.search.category===null &&
        this.search.price==="" &&  this.search.publisher===""){
      alert("Search Fields Cannnot be Blank");
    }
    else{
      const observable= this.readerService.searchBooks(this.search);
      console.log("test"+JSON.stringify(this.search));
      observable.subscribe((responseBody)=>{
        console.log(responseBody);
      },(error:any)=>{
        if(typeof error.error==='string'){
          alert("No Books Found with Your Filter....!!!!!");
          this.nobookFoundMessage=error.error;
        }
        else{
          this.Books=JSON.parse(JSON.stringify(error.error));
        }
      });
    }
  }

  buyBook(book:any){
    this.readerService.digitalBooksContainerFlag=false;
    
    sessionStorage.setItem("buyingbook",book.id);
    this.readerService.book=book;
    this.router.navigate(['/readerhome']);
  }
  ngOnInit():void  {
    this.readerService.digitalBooksContainerFlag=false;
    
    this.bookcontentFlag=false;
    const observable=this.readerService.getAllBooks();
    observable.subscribe((responseBody:any)=>{
      if(responseBody.length===0){
        
        this.nobookFoundMessage="No Books Avalable in the Store";
      }
      else{
        this.Books=responseBody;
      }
    });
  }
}
