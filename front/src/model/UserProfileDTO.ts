import {RoleDTO} from "./RoleDTO";

export class UserProfileDTO {
  id?: String

  name?: String

  email?: String

  cpf?: String

  roles?: RoleDTO[];
}
