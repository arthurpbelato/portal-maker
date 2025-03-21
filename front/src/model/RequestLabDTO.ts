import {DocumentSaveDTO} from "./DocumentSaveDTO";

export class RequestLabDTO {
  description?: string;
  name?: string;
  cpf?: string;
  email?: string;
  phone?: string;
  relation?: string;
  title?: string;
  resources?: string;
  files?: DocumentSaveDTO[];
}
