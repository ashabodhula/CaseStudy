import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorserviceService } from 'src/service/authorservice.service';

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
  constructor(public userService: AuthorserviceService,public router:Router) { }
  readBook(book:any){
    this.userService.createBookContainerFlag=false;
    this.readcontentbook=book;
    this.bookcontentFlag=true;
  }
  
  editbook(book:any){
    this.userService.hastoeditbook=book;
    console.log(JSON.stringify(book));
    this.userService.createBookContainerFlag=false;
    this.userService.hastoeditbook=book;
    this.userService.editBookContainerFlag=true;
    this.userService.updateBookPageFlag=true;
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
    const observable= this.userService.searchBooks(this.searchbookdata);
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
   
    }
  }



