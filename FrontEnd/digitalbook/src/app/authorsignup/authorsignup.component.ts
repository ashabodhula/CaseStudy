import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-authorsignup',
  templateUrl: './authorsignup.component.html',
  styleUrls: ['./authorsignup.component.css']
})
export class AuthorsignupComponent implements OnInit {
 
  flag:any;
  author={
    id:'',
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
  specialEvents=[];

  constructor( public readerService : ReaderService, public router:Router 
    ){ }

  registerAuthor(){
    console.log('Clicked!');
    const c =this.readerService.registerAuthor(this.author);
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
        this.router.navigate(["/authorhome"]);
      }
    });
  }
  ngOnInit(): void {
    
     
  //alert as signup success in author home and navigate to 
    
      // this.router.navigate(["/authorhome"]);
    
    
      // this.readerService.digitalBooksContainerFlag=false;
      
    }
   
    
    
  }


      
//     },(error:any)=>{
//       console.log("test"+JSON.stringify(error));
//       if(error.status===200){
//         this.signUpStatus='Author Registered!';
//         this.signupflag=false;

//       }
//       if(error.error.includes("Username is already taken!")){
//         this.blankFields.username1=error.error;
//         console.log("check"+this.blankFields.username1);
//       }
//       if(error.error.includes("Error: Email is already in use!")){
//         this.blankFields.email1=error.error;
//         console.log("check"+this.blankFields.email1);
//       }
//       if(error.error=='User is present'){
//         this.emailExists=true;
//         this.signUpStatus=error.error;
//       }
//       this.blankFields.username1=error.error.username;
//       this.blankFields.email1=error.error.email;
//       this.blankFields.password=error.error.password;
//     }
//     )
//   }
//   ngOnInit(): void {
   
//   }

// }