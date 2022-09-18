import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Reader } from 'src/model/reader';
import { ReaderService } from 'src/service/reader.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
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
  signUpuserNameExists:any;
  signUpEmailExists:any;
  validCreds:any;
  signUpStatus:any;
  users:any[]=[];
  successMessage:any;
  failureMessage:any;
  signupContainerFlag:boolean=true;
  signupflag: boolean=true;
  emailExists: any;

  constructor( public readerService : ReaderService, public router:Router){ }

  registerReader(){
    console.log('Clicked!');
    const c =this.readerService.registerReader(this.reader);
    c.subscribe((response:any)=>{
    this.blankFields.username1=response.username;
    this.blankFields.email1=response.email;
    this.blankFields.password=response.password;

   
      console.log("ab"+response );
    },
    (error:any)=>{
      console.log(JSON.stringify(error.error));
      this.signUpuserNameExists="";
      this.signUpEmailExists="";
      
      console.log("X"+error.error);
      if(typeof error.error==='string'){
        if(error.error.includes("Invalid")){
          this.signUpuserNameExists=error.error;
        }
        if(error.error.includes("Invalid") ){
          this.signUpEmailExists=error.error;
        }
        
       
      }
      else{
        this.signupContainerFlag=false;
        this.successMessage=error.error.text;
        this.router.navigate(["/readerbooks"]);
      }
    });
  }
  ngOnInit(): void {
   
    
    
  }
 

  
  }

