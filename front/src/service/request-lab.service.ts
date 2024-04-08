import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RequestLabDTO} from "../model/RequestLabDTO";

const ResponseType = 'text' as 'json';

@Injectable({
  providedIn: 'root'
})
export class RequestLabService {

  constructor(private router: Router, private httpClient: HttpClient) { }

  send(dto: RequestLabDTO): Observable<String> {
    return this.httpClient.post<String>(`api/request-lab/public/send`, dto, {responseType: ResponseType});
  }
}
