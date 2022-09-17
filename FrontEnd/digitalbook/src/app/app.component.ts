import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService ,Category} from 'src/service/reader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'digitalbook';

  bookcategory=Object.values(Category).filter(value => typeof value==="string");
  slidingImageFlags=[true,false,false,false];
  slideIndex:any = 0;
  searchbookdata={
    category:"",author:"",price:"",publisher:""
  }
  Books:any=null;
    nobookFoundMessage:any;
    signoutSuccessMessage: string="";
    title1:any;
  constructor(public readerService:ReaderService,public router:Router){}
  searchBooks(){
    this.nobookFoundMessage="";
    if(this.searchbookdata.author==="" && this.searchbookdata.category===null &&
        this.searchbookdata.price==="" &&  this.searchbookdata.publisher===""){
      alert("Search Fields Cannnot be Blank");
    }
    else{
      const observable= this.readerService.searchBooks(this.searchbookdata);
      console.log("test"+JSON.stringify(this.searchbookdata));
      observable.subscribe((responseBody)=>{
        console.log(responseBody);
      },(error:any)=>{
        if(typeof error.error==='string'){
          alert("No Books Found with given filters");
          this.nobookFoundMessage=error.error;
        }
        else{
          this.Books=JSON.parse(JSON.stringify(error.error));
        }
      });
    }
  }
 
  signout(){
    const observable=this.readerService.signout(sessionStorage.getItem("authorId"));
    observable.subscribe((responseBody:any)=>{
      console.log(JSON.stringify(responseBody));
    },
    (error:any)=>{
      console.log(JSON.stringify(error));
    })
    sessionStorage.removeItem('authorId');
    sessionStorage.removeItem("authorName");
    this.readerService.createbooknavFlag=false;
    this.readerService.authorsignupNavFlag=true;
    this.signoutSuccessMessage="Sign Out Success";
    this.ngOnInit();
  }
  buyBook(book:any){
    this.readerService.digitalBooksContainerFlag=false;
 
    sessionStorage.setItem("buyingbook",book);
    this.readerService.book=book;
    this.router.navigate(['/readerhome']);
  }
  ngOnInit() { 
    this.readerService.authorBooksContainerFlag=false; 
    this.readerService.editbooksuccessContainerFlag=false;
    this.readerService.updateBookPageFlag=false;
    this.readerService.editBookContainerFlag=false;
    this.signoutSuccessMessage="";
    
    this.readerService.digitalBooksContainerFlag=true; 
    if(sessionStorage.getItem("authorId")!==null){
      this.readerService.authorsignupNavFlag=false;
      this.readerService.createbooknavFlag=true;
    }
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
