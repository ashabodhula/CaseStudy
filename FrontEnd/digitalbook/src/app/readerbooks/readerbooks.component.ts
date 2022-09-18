import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-readerbooks',
  templateUrl: './readerbooks.component.html',
  styleUrls: ['./readerbooks.component.css']
})
export class ReaderbooksComponent implements OnInit {

  reader={
    email:"",
    paymentid:""
  }
  readerBooks:any;
  readerblankResponse="";
  readcontentbook={
    id:"",
    authorid:"",
    title:'',
    category:'',
    author:"",
    price:'',
    publisher:'',
    publisheddate:'',
    chapters:'',
    bookstatus:"",
  }
  Books:any=null;
  nobookFoundMessage:any;
  bookUnpurchaseSuccesMessage:any;
  getmyBooksContainerFlag:boolean=true;
  bookcontentFlag:boolean=false;
  constructor(public readerService:ReaderService) { }
  
 
  
  getmybooks(){
    this.readerBooks=null;
    this.bookUnpurchaseSuccesMessage="";
    this.readerblankResponse="";
    if(this.reader.email!=="" && this.reader.paymentid===""){
      const observable=this.readerService.getMyBooksByReaderEmail(this.reader.email);
      observable.subscribe((responseBody)=>{
        console.log("response"+JSON.stringify(responseBody));
      this.getmyBooksContainerFlag=true;
        this.readerBooks=responseBody;
      },
      (error:any)=>{
        console.log("E1"+JSON.stringify(error.error));
        if(typeof error.error==="string"){
          this.readerblankResponse=error.error;
        }
      }
      );
    }
    else if(this.reader.email!=="" && this.reader.paymentid!==""){
      const observable=this.readerService.readBookByPaymentID(this.reader);
      observable.subscribe((responseBody)=>{
        console.log("R"+JSON.stringify(responseBody));
        this.getmyBooksContainerFlag=true;
        this.readerBooks=[responseBody];
      },
      (error:any)=>{
        console.log("E"+JSON.stringify(error.error));
        if(typeof error.error==="string"){
          this.readerblankResponse=error.error;
        }
      }
      );
    }
    else{
    this.readerblankResponse="Please search with your email along with paymentid ";
    }   
  }
  ngOnInit(): void {
    this.readerService.digitalBooksContainerFlag=false;
   
    this.bookcontentFlag=false;
  }

}
