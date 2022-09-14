import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-createbook',
  templateUrl: './createbook.component.html',
  styleUrls: ['./createbook.component.css']
})

// export enum category{
//   FICTION,
// 	FANTASY ,
// 	SELFHELP,
// 	HEALTHCARE,
// 	AUTOBIOGRAPHY
// }
export class CreatebookComponent implements OnInit {
 

  book={
    title:'',
    price:0.0,
    category:'',
    author:'',
    publisher:'',
    published_date:new Date(),
    status:Boolean,
    chapters:''
  }
  blankFields={
    title:'',
    price:0.0,
    category:'',
    author:'',
    publisher:'',
    published_date:'',
    status:'',
    chapters:''
  }
  books: any[] = [];
  bookStatus:string='';
 authorId:any;
  selectedFile:any=null;

  constructor(public readerService:ReaderService,private http:HttpClient) { }
  createBook(){
    const con =this.readerService.createBook(this.book,this.authorId);
    con.subscribe((responseBody: any)=>{
      console.log(responseBody);
      this.bookStatus="Book Created Successfully";
    },
    (error:any)=>{

      console.log(error);
      this.blankFields.title=error.error.title;
      this.blankFields.price=error.error.price;
      this.blankFields.category=error.error.category;
      this.blankFields.publisher=error.error.publisher;
      this.blankFields.chapters=error.error.chapter;
      this.blankFields.published_date=error.error.publishedDate;
    }
    );
  }

 
  
  ngOnInit(): void {
  }

}
