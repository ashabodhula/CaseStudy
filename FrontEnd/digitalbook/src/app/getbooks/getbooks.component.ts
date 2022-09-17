import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService,Category } from 'src/service/reader.service';



@Component({
  selector: 'app-getbooks',
  templateUrl: './getbooks.component.html',
  styleUrls: ['./getbooks.component.css']
})
export class GetbooksComponent implements OnInit {
 
  
  
 
  Books:any=null;
  nobookFoundMessage:any;
 
  
  getmyBooksContainerFlag:boolean=true;
  bookcontentFlag:boolean=false;
  constructor(public readerService:ReaderService , public router:Router) { }
  
  buyBook(book:any){
    this.readerService.digitalBooksContainerFlag=false;
    
    sessionStorage.setItem("buyingbook",book);
    this.readerService.book=book;
    this.router.navigate(['/readerhome']);
  }
  ngOnInit():void  {
   
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
