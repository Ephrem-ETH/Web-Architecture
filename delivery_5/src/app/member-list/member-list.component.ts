import { Component, OnInit } from '@angular/core';
import {ParliamentMemberService} from '../parliament-member.service';
import {ParliamentMember} from '../models/parliamentmember.model';

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {
  title: any="Members list";
  memberList:ParliamentMember [] = [];
  constructor(private parliamentMemberService:ParliamentMemberService ){
}

  ngOnInit(): void {
    this.parliamentMemberService.getMembers().subscribe( (members:any) => this.memberList = members);

  }

}
