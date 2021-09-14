import { Component, OnInit } from '@angular/core';
import {ParliamentMemberService} from './parliament-member.service';
import {ParliamentMember} from './models/parliamentmember.model';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Scottish Parliament';
  memberList:ParliamentMember [] = [];
  constructor(private parliamentMemberService:ParliamentMemberService ){
  
}
ngOnInit(){
   //this.getParliamentMembers();
  // this.parliamentMemberService.getMembers().subscribe( (members:any) => this.memberList = members);

}

}
