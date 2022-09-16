import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-readerhome',
  templateUrl: './readerhome.component.html',
  styleUrls: ['./readerhome.component.css']
})
export class ReaderhomeComponent implements OnInit {

  book:any=this.readerSerive.book;
  readerFormFlag=true;
  buyBookContainerFlag=true;
  reader={
    readername:"",
    readeremail:"",
    bookid:this.readerSerive.book.id
  }
  readerblankResponse:any={
    readername:"",
    readeremail:""
  }
  bookPurchaseSuccessMessage:any;
  bookPurchaseFailureMessage:any;
  
  nobookFoundMessage:any;
  AllBooksOfAuthoId : any;
  authorBooks:any;
  constructor(public readerSerive:ReaderService) { }
  

  
 
  
  ngOnInit(): void {  
     
  }

}


