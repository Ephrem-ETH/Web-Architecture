import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {ParliamentMember} from '../models/parliamentmember.model';
import {ParliamentMemberService} from '../parliament-member.service';


@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.css']
})
export class PersonDetailComponent implements OnInit {
  parliamentMembers:any;
  id!:number;
  selectedPerson:any = [];
  parites:any = [];
  memberParty:any;
  memberPartyId: any;
  partyNames:any;
  personalWebsites:any;
  personalWebsite:any;
  
  constructor(private parliamentMemberService:ParliamentMemberService, private activatedRoute:ActivatedRoute, private _router:Router ) { }
  
  ngOnInit(){

  this.getPerson();
  }

  getPerson(){
    
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
    this.parliamentMemberService.getMembers().subscribe(data =>{
      this.parliamentMembers=data;
      this.selectedPerson = this.parliamentMembers.find((item:any) =>{
        if(item.personId==this.id){
          return true;
        }
        
          return false;
        }
      )
      this.getParty(this.id);
      this.getPersonalWebsite(this.id);
      
    return this.selectedPerson;
    })
    
  }
  getParty(id:any){
    this.parliamentMemberService.getMemberParties().subscribe(parties =>{
      this.parites = parties;
      this.memberPartyId = this.parites.find((item:any) =>{
        if(item.PersonID == id){
          return true;
        }
        return false;
      })
      this.parliamentMemberService.getPartyName().subscribe(partyNames =>{
        this.partyNames = partyNames;
        this.memberParty =this.partyNames.find((item:any)=>{
          if(item.ID==this.memberPartyId.PartyID){
            return true;
          }
          return false;
        })
      })
      return this.memberParty;
    })
  }
  getPersonalWebsite(id:any){
  this.parliamentMemberService.getMemberWebsite().subscribe(websites =>{
    this.personalWebsites = websites;
    this.personalWebsite =this.personalWebsites.find((item:any) =>{
      if(item.PersonID ==id){
        return true;
      }
      return false;
    })
    return this.personalWebsite;
  })
  }

}
