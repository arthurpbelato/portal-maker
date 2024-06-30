import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {UserService} from "../service/user.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StyleClassModule} from 'primeng/styleclass';
import { ButtonModule } from 'primeng/button';
import { HomeComponent } from './components/home/home.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { MenubarModule } from 'primeng/menubar';
import { LoginComponent } from './components/login/login.component';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { CheckboxModule } from 'primeng/checkbox';
import {AuthInterceptor} from "./components/interceptors/auth.interceptor";
import { MessagesModule } from 'primeng/messages';
import { UserComponent } from './components/user/user.component';
import { TableModule } from 'primeng/table';
import { EditorModule } from 'primeng/editor';
import { TagModule } from 'primeng/tag';
import { UserFormComponent } from './components/user/form/user-form.component';
import { InputMaskModule } from 'primeng/inputmask';
import { MultiSelectModule } from 'primeng/multiselect';
import { PostFormComponent } from './components/post/form/post-form.component';
import { FileUploadModule } from 'primeng/fileupload';
import { DropdownModule } from 'primeng/dropdown';
import { ChipsModule } from 'primeng/chips';
import { GalleriaModule } from 'primeng/galleria';
import { ImageModule } from 'primeng/image';
import { BlockUIModule } from 'primeng/blockui';
import { PanelModule } from 'primeng/panel';
import { RequestLabComponent } from './components/request-lab/request-lab.component';
import {ToastModule} from "primeng/toast";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import { PostDetailComponent } from './components/post/detail/post-detail.component';
import { TabViewModule } from 'primeng/tabview';
import { ReviewPageComponent } from './components/review-page/review-page.component';
import {DialogModule} from "primeng/dialog";
import {SpeedDialModule} from "primeng/speeddial";
import {AccordionModule} from "primeng/accordion";
import {ConfirmDialogModule} from "primeng/confirmdialog";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TopbarComponent,
    LoginComponent,
    UserComponent,
    UserFormComponent,
    PostFormComponent,
    RequestLabComponent,
    PostDetailComponent,
    ReviewPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    MenubarModule,
    PasswordModule,
    InputTextModule,
    CheckboxModule,
    StyleClassModule,
    MessagesModule,
    TableModule,
    EditorModule,
    TagModule,
    InputMaskModule,
    MultiSelectModule,
    FileUploadModule,
    DropdownModule,
    ChipsModule,
    GalleriaModule,
    ImageModule,
    BlockUIModule,
    PanelModule,
    ToastModule,
    ProgressSpinnerModule,
    TabViewModule,
    DialogModule,
    SpeedDialModule,
    AccordionModule,
    ConfirmDialogModule
  ],
  providers: [
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
