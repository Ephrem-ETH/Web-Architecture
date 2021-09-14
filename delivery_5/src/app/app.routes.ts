import {Routes} from '@angular/router';
import {PersonDetailComponent} from './person-detail/person-detail.component';
import { MemberListComponent } from './member-list/member-list.component';

export const appRoutes:Routes = [
    //{path:'home',component:AppComponent},
    {path:'list',component:MemberListComponent},
    { path:'list/detail/:id', component:PersonDetailComponent},
    {path:'', redirectTo:'list',pathMatch:'full'}
    
]