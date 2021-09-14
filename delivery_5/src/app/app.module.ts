import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ParliamentMemberService} from './parliament-member.service';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonDetailComponent } from './person-detail/person-detail.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import { appRoutes } from './app.routes';
import { MemberListComponent } from './member-list/member-list.component';
import {HashLocationStrategy,LocationStrategy} from '@angular/common';
@NgModule({
  declarations: [
    AppComponent,
    PersonDetailComponent,
    MemberListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ParliamentMemberService,{ provide:LocationStrategy, useClass:HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
