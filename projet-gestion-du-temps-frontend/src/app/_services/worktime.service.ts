import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TempsTravailRequest} from "../_models/tempsTravailRequest.model";
import {Observable} from "rxjs";
import {TempsTravailResponse} from "../_models/tempsTravailResponse.model";
import {SortDirection} from "@angular/material/sort";
import {TempsPauseResponse} from "../_models/tempsPauseResponse.model";
import {TempsPauseRequest} from "../_models/tempsPauseRequest.model";

@Injectable({
  providedIn: 'root'
})
export class WorktimeService {

  constructor(private http:HttpClient) { }
  private href: string = 'http://localhost:8083/employee/worktime';

  private hreftoday: string = 'http://localhost:8083/employee/worktime/today';

  private hreftodayworktime: string = 'http://localhost:8083/employee/worktime/todayworktime';



  private hrefpause: string = 'http://localhost:8083/employee/breaktime';
  private hrefpausetoday: string = 'http://localhost:8083/employee/breaktime/today';
  private hrefpausetodayonly: string = 'http://localhost:8083/employee/breaktime/todaybreaktime';

  // public getProducts():Observable<Array<Product>>{
  //   return  this.http.get<Array<Product>>("http://localhost:8089/products")
  // }
  //

  // public searchRooms(keyword:string):Observable<Array<Room>>{
  //   return  this.http.get<Array<Room>>(`http://localhost:8089/products?name_like=${keyword}`)
  // }

  // public getRoom(page :number=1, size :number=4){
  //   return  this.http.get(`http://localhost:8089/products?_page=${page}&_limit=${size}`,{observe:'response'})
  // }


  // public deleteRoom(room: Room){
  //   return this.http.delete<any>(`http://localhost:8089/products/${room.id}`)
  // }


  // public getRooms(sort: string, order: SortDirection, page: number):Observable<Article[]>{
  //   const getRoomsUrl = `${this.href}/rooms?sort=${sort}&order=${order}&page=${page + 1}`
  //   return  this.http.get<Article[]>(getRoomsUrl);
  //
  // }



  public getWorkTimes(sort: string, order: SortDirection, page: number):Observable<TempsTravailResponse[]>{
    const getRoomsUrl = `${this.href}?sort=${sort}&order=${order}&page=${page}`
    return  this.http.get<TempsTravailResponse[]>(getRoomsUrl);
  }
  // public getBreakTimes(sort: string, order: SortDirection, page: number) : Observable<TempsPauseResponse[]>{
  //   const getRoomsUrl = `${this.hrefpause}?sort=${sort}&order=${order}&page=${page}`
  //   return  this.http.get<TempsPauseResponse[]>(getRoomsUrl);
  // }





  public getTodayWorkTime(sort: string, order: SortDirection, page: number):Observable<TempsTravailResponse[]>{
    const getRoomsUrl = `${this.hreftoday}?sort=${sort}&order=${order}&page=${page}`
    return  this.http.get<TempsTravailResponse[]>(getRoomsUrl);
  }
  public getTodayBreakTime(sort: string, order: SortDirection, page: number):Observable<TempsPauseResponse[]>{
    const getRoomsUrl = `${this.hrefpausetoday}?sort=${sort}&order=${order}&page=${page}`
    return  this.http.get<TempsPauseResponse[]>(getRoomsUrl);
  }






  public getTodayWorkTimeOnly():Observable<TempsTravailResponse>{
    const getRoomsUrl = `${this.hreftodayworktime}`
    return  this.http.get<TempsTravailResponse>(getRoomsUrl);
  }

  public getTodayBreakTimeOnly() :Observable<TempsPauseResponse>{
    const getRoomsUrl = `${this.hrefpausetodayonly}`
    return  this.http.get<TempsPauseResponse>(getRoomsUrl);
  }


  //
  // public deleteRoom(id : number) : Observable<any>{
  //   const deleteRoomUrl = `${this.href}/articles/${id}`
  //   return  this.http.delete<any>(deleteRoomUrl);
  // }


  public saveWorkTime(wt: TempsTravailRequest):Observable<TempsTravailResponse>{
    return this.http.post<TempsTravailResponse>(`${this.href}`, wt)
  }
  public saveBreakTime(tempsPause: TempsPauseRequest): Observable<TempsPauseResponse>{
    return this.http.post<TempsPauseResponse>(`${this.hrefpause}`, tempsPause)
  }





  public finishWorkTime(id: number, wt: TempsTravailRequest):Observable<TempsTravailResponse>{
    return this.http.put<TempsTravailResponse>(`${this.href}/${id}`, wt)
  }

  public finishBreakTime(id: number, bt: TempsTravailRequest):Observable<TempsPauseResponse>{
    return this.http.put<TempsPauseResponse>(`${this.hrefpause}/${id}`, bt)
  }

  //
  // updateRoom (id : number, room: FormData ):Observable<Article>{
  //   return this.http.put<Article>(`${this.href}/articles/${id}`, room)
  // }



  // public getNbreOfRooms():Observable<number>{
  //   const getNbreUrl = `${this.href}/articles/numberofarticles`;
  //   return this.http.get<number>(getNbreUrl);
  // }






}


