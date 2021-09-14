import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
//import 'rxjs/add/operator/map';
import { Observable } from 'rxjs';
import { ParliamentMember } from './models/parliamentmember.model';
@Injectable({

  providedIn: 'root'
})
export class ParliamentMemberService {
  url1 = "https://data.parliament.scot/api/members";
  url2 = "https://data.parliament.scot/api/memberparties";
  url3 = "https://data.parliament.scot/api/parties";
  url4 = "https://data.parliament.scot/api/websites";
  constructor(private http: HttpClient) { }


  
  public getMembers(): Observable<ParliamentMember[]> {
    return this.http.get(this.url1).pipe(map((members: any) => {
      return members.map((member: any) => {
        return {
          personId: member.PersonID,
          name: member.PreferredName,
          photoURL: member.PhotoURL,
          birthDate: member.BirthDate

        }
      })
    }))


  }






  getMemberParties() {
    return this.http.get(this.url2);

  }
  getPartyName(){
    return this.http.get(this.url3);
  }
  getMemberWebsite(){
    return this.http.get(this.url4);
  }



}
