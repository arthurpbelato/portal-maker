import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class CPFValidator {

   validate(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const cpf = control.value;
      if (!cpf) return { cpfInvalid: true };

      const cleanedCpf = cpf.replace(/[\.\-]/g, '');

      if (cleanedCpf.length !== 11 || /^(\d)\1{10}$/.test(cleanedCpf)) {
        return { cpfInvalid: true };
      }

      const digits = cleanedCpf.split('').map(Number);

      for (let i = 9; i < 11; i++) {
        let sum = 0;
        for (let j = 0; j < i; j++) {
          sum += digits[j] * (i + 1 - j);
        }

        const remainder = (sum * 10) % 11;

        if (remainder === 10 || remainder === 11) {
          if (digits[i] !== 0) {
            return { cpfInvalid: true };
          }
        } else if (digits[i] !== remainder) {
          return { cpfInvalid: true };
        }
      }

      return null;
    };
  }

}


