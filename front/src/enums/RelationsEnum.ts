export class RelationsEnum {

  static NULL_RELATION = new RelationsEnum(-1, "NULL_RELATION");

  static DICENTE = new RelationsEnum(0, "Dicente");
  static DOCENTE = new RelationsEnum(1, "Docente");
  static COLABORADOR_EXTERNO = new RelationsEnum(2, "Colabortador Externo");

  constructor(
    public value: number,
    public label: string
  ) {
    this.label = label;
    this.value = value;
  }

  public static values(): RelationsEnum[] {
    return [
      this.DICENTE,
      this.DOCENTE,
      this.COLABORADOR_EXTERNO,
    ];
  }

  public static getByValue(value?: number): RelationsEnum {
    let result = this.values().find((s: RelationsEnum)=>s.value === value);
    if (result === undefined) {
      return this.NULL_RELATION;
    }
    return result;
  }

}
