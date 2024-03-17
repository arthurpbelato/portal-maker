import {DocumentSaveDTO} from "./DocumentSaveDTO";
import {PostUserDTO} from "./PostUserDTO";

export class PostListDTO {

  id?: string;

  postDate?: string;

  title?: string;

  description?: string;

  score?: string;

  user?: PostUserDTO;

  subject?: number;

  status?: number;

  tags?:string[];

  images?: DocumentSaveDTO[];

}
