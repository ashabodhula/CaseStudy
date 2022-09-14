import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReaderService } from 'src/service/reader.service';

@Component({
  selector: 'app-authorlogin',
  templateUrl: './authorlogin.component.html',
  styleUrls: ['./authorlogin.component.css']
})
export class AuthorloginComponent implements OnInit {
 
  usernameinvalid:any;
  loginflag:boolean=true;
  flag:any;
author={
    
    email: '',
    username : '',
    password :''
    
   }
   emailExists:boolean=false;

  authorLoginStatus:String='';
  blankFields={
    username:'',
    email:'',
    password:'',
    email1:'',
    password1:''
  }

  constructor(public readerService : ReaderService, private router : Router) { }

  ngOnInit(): void {
   
  
  }

 
  loginAuthor(){
   
    const c=this.readerService.loginAuthor(this.author);
    c.subscribe((responseBody: any)=>{
      this.authorLoginStatus="Author logged In";
      this.router.navigate(["/authorhome"]);
     
      console.log(this.authorLoginStatus);
    },(error:any)=>{
      console.log("test"+JSON.stringify(error));
      if(error.status===200){

        sessionStorage.setItem("authorid",error.error.text.replace("author Login success",""));
        this.authorLoginStatus='Author logged In,Please navigate to your home';
      //  this.router.navigate(["/authorhome"]);
        this.loginflag=false;
      }
      if(error.error.includes("Error: Invalid Credential")){
        this.blankFields.email1=error.error;
        console.log("check"+this.blankFields.email1);
      }
    
      

      if(error.error=='Invalid Creds'){
        this.emailExists=true;
        this.authorLoginStatus=error.error;
      }
      this.blankFields.username=error.error.username;
      this.blankFields.email=error.error.email;
      this.blankFields.password=error.error.password;
    });
  }
 
  onLogout(){
    console.log('Logged Out!');
    this.router.navigateByUrl('/');
  }
      
       
}
