import {DocumentSaveDTO} from "./DocumentSaveDTO";
import {PostUserDTO} from "./PostUserDTO";

export class PostDTO {

  title?: string;

  description?: string;

  externalReference?: string;

  status?: number;

  subjec?: number;

  models?: DocumentSaveDTO[];

  images?: DocumentSaveDTO[];

  tags?:string[];

  user?: PostUserDTO;

  // @ts-ignore
  postDate: string;

}
