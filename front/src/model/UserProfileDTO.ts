import {RoleDTO} from "./RoleDTO";

export class UserProfileDTO {
  name?: String

  email?: String

  cpf?: String

  roles?: RoleDTO[];
}
