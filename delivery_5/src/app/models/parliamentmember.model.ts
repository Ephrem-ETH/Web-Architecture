export class ParliamentMember{
    constructor(personId:number,name:string,photoURL:string,birthDate:string){
        this.personId=personId;
        this.name = name;
        this.photoURL = photoURL;
        this.birthDate = birthDate;
    }
    personId: number;
    name: string;
    photoURL?: string;
    birthDate: string;
   

    
}