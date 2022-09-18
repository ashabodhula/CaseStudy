import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reader } from 'src/model/reader';
import { ReaderService } from 'src/service/reader.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  flag:any;
  reader={
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

  constructor( public readerService : ReaderService,public router:Router){ }

  loginReader(){
    console.log('Clicked!');
    const c =this.readerService.loginReader(this.reader);
    c.subscribe((response:any)=>{
    
    this.blankFields.email1=response.email;
    this.blankFields.username1=response.username;
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
        this.router.navigate(["/readerbooks"]);
      }
    });
  }
  ngOnInit(): void {
   
    
    
  }     
       
     }
     
    

  
  

