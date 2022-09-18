import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService,Category } from 'src/service/reader.service';

@Component({
  selector: 'app-authorspace',
  templateUrl: './authorspace.component.html',
  styleUrls: ['./authorspace.component.css']
})
export class AuthorspaceComponent implements OnInit {
  createbookFailureMessage: string="";
  bookCreateSuccessMessage: any;
  constructor(public readerService:ReaderService,public router:Router) { }  
  obj=Object.values(Category).filter(value => typeof value==="string");
  book={
    title:"",
    category:"",
    author:""+sessionStorage.getItem("authorName"),
    price:"",
    publisher:"",
    publisheddate:"",
    chapters:"",
    bookstatus:Boolean,
    content:""
  }
  blankResponse={
    title:'',
    category:'',
    author:'',
    price:'',
    publisher:'',
    publisheddate:'',
    chapters:'',
    bookstatus:'',
    content:''
  }
   
  buyBook(book:any){
    this.readerService.digitalBooksContainerFlag=false;
    
    sessionStorage.setItem("creatingbook",book.id);
    this.readerService.book=book;
    this.router.navigate(['/readerhome']);
  }
 
  createBook(){
    console.log(this.book.chapters+"E");
    this.blankResponse.title="";
    this.blankResponse.category="";
    this.blankResponse.publisher="";
    this.blankResponse.publisheddate="";
    this.blankResponse.price="";
    this.blankResponse.chapters="";
    this.blankResponse.content="";
    if(this.book.title===""){
      this.blankResponse.title="Mandatory Field";
    }
    if(this.book.category==="" || this.book.chapters===null){
          this.blankResponse.category="Mandatory Field";
    }
    if(this.book.publisher===""){
          this.blankResponse.publisher="Mandatory Field";
    }
    if(this.book.publisheddate===""){
          this.blankResponse.publisheddate="Mandatory Field";
    }
    if(this.book.price==="" || this.book.price===null){
          this.blankResponse.price="Mandatory Field";
    }
    if(this.book.chapters==="" || this.book.chapters===null){
          this.blankResponse.chapters="Mandatory Field";
    }
    if(this.book.content===""){
          this.blankResponse.content="Mandatory Field";
    }
    else{
        const observable = this.readerService.createBook(this.book);
      observable.subscribe((responseBody:any)=>{ },
      (error:any)=>{
        console.log("E"+error.error.status+JSON.stringify(error));
        if(error.status===406){
          this.blankResponse.category="Category Cannot be Blank";
        }
        if(typeof error.error!=='string' && error.error.status!==500 ){
          this.readerService.createBookContainerFlag=false;
          this.bookCreateSuccessMessage=error.error.text;
          
          this.book={
            title:"",
            category:"",
            author:""+sessionStorage.getItem("authorName"),
            price:"",
            publisher:"",
            publisheddate:"",
            chapters:"",
            bookstatus:Boolean,
            content:""
        }
      }
      else{
        this.book={
          title:"",
          category:"",
          author:""+sessionStorage.getItem("authorName"),
          price:"",
          publisher:"",
          publisheddate:"",
          chapters:"",
          bookstatus:Boolean,
          content:""};
          this.createbookFailureMessage=" Something Went Wrong";
      }
    });
    }
  }
  ngOnInit(): void {
    this.readerService.digitalBooksContainerFlag=false;
    
    this.readerService.createBookContainerFlag=true;
    this.readerService.createbooksuccessContainerFlag=false;
  }

}
