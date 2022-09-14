import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'digitalbook';

Books:any;
nobookFoundMessage:any;
constructor(public readerService:ReaderService,public router:Router){}
signout(){
  sessionStorage.removeItem('authorId');
  
  console.log("x"+sessionStorage.getItem('authorId')+this.Books);
  this.ngOnInit();
}

ngOnInit(): void {
  const observable=this.readerService.getAllBooks();
  observable.subscribe((responseBody:any)=>{
    console.log("R"+JSON.stringify(responseBody));
    this.Books=JSON.parse(JSON.stringify(responseBody));
  },
  (error:any)=>{
    
  });
  const digitalBooksContainer:any=document.getElementById("digitalBooksContainer");
  digitalBooksContainer.style.display="block";
  if(sessionStorage.getItem('authorId')!==null){//authorhome
    console.log("a"+sessionStorage.getItem('authorId'));
  const authorhomeContainer:any = document.getElementById("authorhomeContainer");
  authorhomeContainer.style.display="block";
    const authorloginContainer:any=document.getElementById("authorloginContainer");
    authorloginContainer.style.display="none";
   
}
else{//Home

  const authorhomeContainer:any = document.getElementById("authorhomeContainer");
  authorhomeContainer.style.display="none";
    const authorloginContainer:any=document.getElementById("authorloginContainer");
    authorloginContainer.style.display="block";
   
    
}

} 
}
