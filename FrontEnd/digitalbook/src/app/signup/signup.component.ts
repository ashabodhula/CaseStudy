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
  usernameinvalid:any;
  signupflag:boolean=true;
  flag:any;
  reader={
    username:'',
    email:'',
    password:'',
 
  };
  emailExists:boolean=false;
  signUpStatus:String='';
  blankFields={
    username:'',
    email:'',
    password:'',
    username1:'',
    email1:''
  };


  constructor( public readerService : ReaderService){ }

  registerReader(){
    console.log('Clicked!');
    const c =this.readerService.registerReader(this.reader);
    c.subscribe((response:any)=>{
      console.log("ab"+response );
      
      
    },(error:any)=>{
      console.log("test"+JSON.stringify(error));
      if(error.status===200){
        this.signUpStatus='Reader Registered!';
        this.signupflag=false;

      }
      if(error.error.includes("Username is already taken!")){
        this.blankFields.username1=error.error;
        console.log("check"+this.blankFields.username1);
      }
      if(error.error.includes("Error: Email is already in use!")){
        this.blankFields.email1=error.error;
        console.log("check"+this.blankFields.email1);
      }
      if(error.error=='User is present'){
        this.emailExists=true;
        this.signUpStatus=error.error;
      }
      this.blankFields.username=error.error.username;
      this.blankFields.email=error.error.email;
      this.blankFields.password=error.error.password;
    }
    )
  }
  ngOnInit(): void {
   
  }

 

  
  }


