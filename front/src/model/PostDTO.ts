import {DocumentSaveDTO} from "./DocumentSaveDTO";

export class PostDTO {

  title?: string;

  description?: string;

  externalReference?: string;

  uploadedFiles ?: any[];

  models?: DocumentSaveDTO[];

  images?: DocumentSaveDTO[];

}
