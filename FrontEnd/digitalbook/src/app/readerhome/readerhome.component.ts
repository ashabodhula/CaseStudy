import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-readerhome',
  templateUrl: './readerhome.component.html',
  styleUrls: ['./readerhome.component.css']
})
export class ReaderhomeComponent implements OnInit {
  searchbookdata={
    category:"",
    author:"",
    price:"",
    publisher:""
  }

  nobookFoundMessage:any;
  AllBooksOfAuthoId : any;
  authorBooks:any;
  constructor(public readerSerive:ReaderService) { }
  

  searchBooks(){
    if(this.searchbookdata.author==="" &&
    this.searchbookdata.category==="" &&
    this.searchbookdata.price==="" &&
    this.searchbookdata.publisher===""){
      alert("Search Fields Cannnot be Blank");
    }
    else{
    const observable= this.readerSerive.searchBooks(this.searchbookdata);
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
