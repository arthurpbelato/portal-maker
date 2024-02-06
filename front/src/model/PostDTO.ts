import {DocumentSaveDTO} from "./DocumentSaveDTO";

export class PostDTO {

  title?: string;

  description?: string;

  externalReference?: string;

  status?: number;

  subjec?: number;

  models?: DocumentSaveDTO[];

  images?: DocumentSaveDTO[];

  tags?:string[];

}
