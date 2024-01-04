import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../models/user.model';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private BASE_URL = 'http://localhost:8083';

  constructor(private http: HttpClient) {}

  addEmployee(user: User) {
    return this.http.post(this.BASE_URL + '/addEmployee', user);
  }

  getAllEmployees() {
    return this.http.get<User[]>(this.BASE_URL + '/getAllEmployees');
  }

  deleteEmployee(id: number) {
    return this.http.delete(this.BASE_URL + `/deleteEmployee/${id}`);
  }


}
