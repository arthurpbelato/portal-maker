export class SubjectEnum {

  static NONE = new SubjectEnum(0, "Sem Matéria");
  static MATEMATICA = new SubjectEnum(1, "Matemática");
  static PORTUGUES = new SubjectEnum(2, "Português");
  static BIOLOGIA = new SubjectEnum(3, "Biologia");
  static QUIMICA = new SubjectEnum(4, "Química");
  static FISICA = new SubjectEnum(5, "Física");
  static GEOGRAFIA = new SubjectEnum(6, "Geografia");

  constructor(
    public value: number,
    public label: string
  ) {
    this.value = value;
    this.label = label
  }

  public static values(): SubjectEnum[] {
    return [
      this.NONE,
      this.MATEMATICA,
      this.PORTUGUES,
      this.BIOLOGIA,
      this.QUIMICA,
      this.FISICA,
      this.GEOGRAFIA
    ];
  }

}
