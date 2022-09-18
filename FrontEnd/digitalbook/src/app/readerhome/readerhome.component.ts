import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService, Category } from 'src/service/reader.service';

@Component({
  selector: 'app-readerhome',
  templateUrl: './readerhome.component.html',
  styleUrls: ['./readerhome.component.css']
})
export class ReaderhomeComponent implements OnInit {
  emailblankResponse: string="";
  usernameblankResponse: string="";
  passwordblankResponse: string="";
  constructor(public readerService:ReaderService,public router:Router) { }
  book:any=this.readerService.book;
  readerFormFlag=true;
  buyBookContainerFlag=true;
  cardnumber:string="";
  reader={
    username:"",
    email:"",
    password:"",
    bookid:this.readerService.book.id
  }
  readerblankResponse:any={
    readerusername:"",
    readeremail:"",
    readerpassword:""
  }
  bookPurchaseSuccessMessage:any;
  bookPurchaseFailureMessage:any;

  makepayment(){
    this.readerblankResponse.readerpassword="";
    this.readerblankResponse.readername="";
    this.readerblankResponse.readeremail="";
    this.bookPurchaseFailureMessage="";
    this.bookPurchaseSuccessMessage="";
    if(this.reader.email===""){
      this.emailblankResponse="Email cannot be Blank";
    }
    if(this.reader.password===""){
        this.passwordblankResponse="Password cannot be Blank";
      }
      if(this.reader.username===""){
        this.usernameblankResponse="Username cannot be Blank";
      }
    
      const observable=this.readerService.buyBook(this.reader);
      observable.subscribe((responseBody)=>{
        
        this.readerblankResponse=responseBody;
      },
      (error:any)=>{
        console.log("Response"+JSON.stringify(error.error));
        if(typeof error.error.text==='string'){
          this.readerFormFlag=false;
          this.bookPurchaseSuccessMessage=error.error.text;
          
        }
        else if(typeof error.error==="string" && error.error.includes("Invalid")){
          this.readerblankResponse.readeremail=error.error;
        }
        else{
          this.reader.username="";
          this.reader.email="";
          this.reader.password="";
        this.bookPurchaseFailureMessage=error.error;
        }
      });
    
  }
  cancelpayment(){
    this.readerService.digitalBooksContainerFlag=true;
   
    this.router.navigate(["/"]);
  }
  
  ngOnInit(): void {   
  }

}


