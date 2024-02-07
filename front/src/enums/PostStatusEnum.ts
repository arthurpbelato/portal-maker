export class PostStatusEnum {

  static NULL_STATUS = new PostStatusEnum(-1, "NULL_STATUS");

  static WAITING_REVIEW = new PostStatusEnum(0, "Aguardando revisão");
  static WAITING_EDIT = new PostStatusEnum(1, "Aguardando edição");
  static APPROVED = new PostStatusEnum(2, "Aprovado");
  static DENIED = new PostStatusEnum(3, "Negado");

  constructor(
    public value: number,
    public label: string
  ) {
    this.label = label;
    this.value = value;
  }

  public static values(): PostStatusEnum[] {
    return [
      this.WAITING_REVIEW,
      this.WAITING_EDIT,
      this.APPROVED,
      this.DENIED
    ];
  }

  public static getByValue(value: number): PostStatusEnum {
    let result = this.values().find((s: PostStatusEnum)=>s.value === value);
    if (result === undefined) {
      return this.NULL_STATUS;
    }
    return result;
  }

}
