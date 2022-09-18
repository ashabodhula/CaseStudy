import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-authorlogin',
  templateUrl: './authorlogin.component.html',
  styleUrls: ['./authorlogin.component.css']
})
export class AuthorloginComponent implements OnInit {
 
  flag:any;
  author={
    username:'',
    email:'',
    password:'',
 
  };
 
  blankFields={
    username1:'',
    email1:'',
    password:'',
   
  };
  loginuserNameExists:any;
  loginEmailExists:any;
  validCreds:any;
  loginStatus:any;
  users:any[]=[];
  successMessage:any;
  failureMessage:any;
  loginContainerFlag:boolean=true;
  loginflag: boolean=true;
  emailExists: any;

  constructor( public readerService : ReaderService, public router:Router){ }

  loginAuthor(){
    console.log('Clicked!');
    const c =this.readerService.loginAuthor(this.author);
    c.subscribe((response:any)=>{
    this.blankFields.username1=response.username1
    this.blankFields.email1=response.email;
    this.blankFields.password=response.password;

   
      console.log("ab"+response );
    },
    (error:any)=>{
      console.log(JSON.stringify(error.error));
      
      this.loginEmailExists="";
      
      console.log("X"+error.error);
      if(typeof error.error==='string'){
       
        if(error.error.includes("Invalid") ){
          this.loginEmailExists=error.error;
        }
        
       
      }
      else{
        this.loginContainerFlag=false;
      
        this.successMessage=error.error.text;
        this.router.navigate(["/authorhome"]);
      }
    });
  }
  ngOnInit(): void {
  

    
    
  }    
}
