import {RoleDTO} from "./RoleDTO";

export class UserRegisterDTO {
  name?: String

  email?: String

  cpf?: String

  roles?: RoleDTO[];
}
