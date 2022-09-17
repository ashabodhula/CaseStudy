import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService, Category } from 'src/service/reader.service';

@Component({
  selector: 'app-readerhome',
  templateUrl: './readerhome.component.html',
  styleUrls: ['./readerhome.component.css']
})
export class ReaderhomeComponent implements OnInit {
  cardnumberblankResponse: string="";
  constructor(public readerService:ReaderService,public router:Router) { }
  book:any=this.readerService.book;
  readerFormFlag=true;
  buyBookContainerFlag=true;
  cardnumber:string="";
  reader={
    username:"",
    email:"",
    bookid:this.readerService.book.id
  }
  readerblankResponse:any={
    readerusername:"",
    readeremail:""
  }
  bookPurchaseSuccessMessage:any;
  bookPurchaseFailureMessage:any;
  
  ngOnInit(): void {   
  }

}


