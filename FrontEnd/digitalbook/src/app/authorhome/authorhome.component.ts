import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService,Category } from 'src/service/reader.service';

@Component({
  selector: 'app-authorhome',
  templateUrl: './authorhome.component.html',
  styleUrls: ['./authorhome.component.css']
})
export class AuthorhomeComponent implements OnInit {
  searchbookdata={
    category:"",
    author:"",
    price:"",
    publisher:""
  }
  nobookFoundMessage:any;
  AllBooksOfAuthoId : any;
  authorBooks:any;
  updatebookblankResponse={
    title:'',
    category:'',
    author:'',
    price:'',
    publisher:'',
    publisheddate:'',
    chapters:'',
    active:'',
  }
  authorName=sessionStorage.getItem("authorName");
  getmyBooksContainerFlag: boolean=true;
  readcontentbook: any;
  bookcontentFlag: boolean=false;
  constructor(public readerService: ReaderService,public router:Router) { }
  readBook(book:any){
    this.readerService.authorBooksContainerFlag=false;
    this.readcontentbook=book;
    this.bookcontentFlag=true;
  }
  goBack(){
    this.readerService.authorBooksContainerFlag=true;
    this.bookcontentFlag=false;
  }
  editbook(book:any){
    this.readerService.hastoeditbook=book;
    console.log(JSON.stringify(book));
    this.readerService.authorBooksContainerFlag=false;
    this.readerService.hastoeditbook=book;
    this.readerService.editBookContainerFlag=true;
    this.readerService.updateBookPageFlag=true;
    this.router.navigate(["/updatebook"]);
  }

  searchBooks(){
    if(this.searchbookdata.author==="" &&
    this.searchbookdata.category==="" &&
    this.searchbookdata.price==="" &&
    this.searchbookdata.publisher===""){
      alert("Search Fields Cannnot be Blank");
    }
    else{
    const observable= this.readerService.searchBooks(this.searchbookdata);
    observable.subscribe((responseBody:any)=>{
     },
    (error:any)=>{
      if(typeof error.error==='string'){
        this.nobookFoundMessage=error.error;
      }
      else{
      this.authorBooks=JSON.parse(JSON.stringify(error.error));
      }
    });
  }
  }
  ngOnInit(): void {
    if(sessionStorage.getItem("authorName")===null){
     this.router.navigate(["/"]);
    }
    else{
      this.readerService.authorBooksContainerFlag=true;
      this.readerService.digitalBooksContainerFlag=false;
      
      this.readerService.createBookContainerFlag=false;
      const observable=this.readerService.getbooksByAuthorID();
      observable.subscribe((responseBody:any)=>{
        if(responseBody.length===0){
          this.nobookFoundMessage="No Books in your profile, Please Click on Create Books button and Create your books";
        }
        else{
          this.nobookFoundMessage="";
          this.authorBooks=responseBody;
        }
      });
    }
  }
}

