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
  usernameinvalid:any;
  loginflag:boolean=true;
  flag:any;
reader={
    email: '',
    username : '',
    password :'',
    role:''
   }
   emailExists:boolean=false;

  readerLoginStatus:String='';
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

 
  loginReader(){
   
    const c=this.readerService.loginReader(this.reader);
    c.subscribe((responseBody: any)=>{
      this.readerLoginStatus="Reader logged In";
      this.router.navigate(["/authorhome"]);
     
      console.log(this.readerLoginStatus);
    },(error:any)=>{
      console.log("test"+JSON.stringify(error));
      if(error.status===200){
        this.readerLoginStatus='Reader logged In,Please navigate to your home';
      //  this.router.navigate(["/authorhome"]);
        this.loginflag=false;
      }
      if(error.error.includes("Error: Invalid Credential")){
        this.blankFields.email1=error.error;
        console.log("check"+this.blankFields.email1);
      }
    
      

      if(error.error=='Invalid Creds'){
        this.emailExists=true;
        this.readerLoginStatus=error.error;
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
     
    

  
  

