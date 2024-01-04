import {TempsPauseResponse} from "./tempsTravailResponse.model";
import {TempsPauseRequest} from "./tempsPauseRequest.model";
import {Role} from "../_enums/role.enum";

export interface userResponse {

  id:number;
  nom:string;
  prenom:string;
  email:string;
  password:string;

  role : Role


  tempsTravailDTOS : TempsPauseRequest[];
  tempsPauseDTOS : TempsPauseResponse[];


}
