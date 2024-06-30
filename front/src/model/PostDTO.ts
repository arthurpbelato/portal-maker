import {DocumentSaveDTO} from "./DocumentSaveDTO";
import {PostUserDTO} from "./PostUserDTO";
import {PostReviewDTO} from "./PostReviewDTO";

export class PostDTO {

  title?: string;

  description?: string;

  externalReference?: string;

  status?: number;

  subject?: number;

  models?: DocumentSaveDTO[];

  images?: DocumentSaveDTO[];

  tags?:string[];

  user?: PostUserDTO;

  // @ts-ignore
  postDate: string;

  reviews?: PostReviewDTO[] ;

}
